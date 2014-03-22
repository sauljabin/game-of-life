/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		2014
 */

package app.animation;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Animator extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Vector<Animated> animateds;
	private long delay;
	private boolean pause;
	private Thread thread;
	private boolean stop;
	private Image image;

	public void addAnimated(Animated animated) {
		animateds.add(animated);
		animated.setAnimator(this);
		animated.init();
	}

	public void removeAnimated(Animated animated) {
		animateds.remove(animated);
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public Animator() {
		delay = 40l;
		animateds = new Vector<Animated>();
		thread = new Thread(this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				animatorMouseDragged(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				animatorMousePressed(e);
			}
		});
	}

	public void makeImage() {
		image = createImage(getWidth(), getHeight());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	public void animatorMousePressed(MouseEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			if (animateds.get(i).shape() != null && animateds.get(i).getAnimatedMouseListener() != null) {
				if (animateds.get(i).shape().contains(e.getPoint())) {
					animateds.get(i).getAnimatedMouseListener().mousePressed(e);
				}
			}
		}
	}

	public void animatorMouseDragged(MouseEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			if (animateds.get(i).shape() != null && animateds.get(i).getAnimatedMouseListener() != null) {
				if (animateds.get(i).shape().contains(e.getPoint())) {
					animateds.get(i).getAnimatedMouseListener().mouseDragged(e);
					;
				}
			}
		}
	}

	public void start() {
		makeImage();
		thread.start();
		stop = false;
		pause = false;
	}

	public void stop() {
		stop = true;
		pause = true;
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		pause = false;
	}

	@Override
	public void run() {
		while (!stop) {
			rendering();

			if (!pause) {
				animate();
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void next() {
		rendering();
		animate();
	}

	public void animate() {
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).animate();
		}
	}

	public void rendering() {
		image.getGraphics().clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).paint(image.getGraphics());
		}
		getGraphics().drawImage(image, 0, 0, this);
	}

	public void clear() {

	}

	public void restart() {

	}
}
