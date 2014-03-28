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

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Animated {
	private AnimatedMouseListener animatedMouseListener;
	private Animator animator;

	public Animator getAnimator() {
		return animator;
	}

	public void setAnimator(Animator animator) {
		this.animator = animator;
	}

	public AnimatedMouseListener getAnimatedMouseListener() {
		return animatedMouseListener;
	}

	public void setAnimatedMouseListener(AnimatedMouseListener animatedMouseListener) {
		this.animatedMouseListener = animatedMouseListener;
	}

	public abstract void init();

	public abstract Rectangle2D getShape();

	public abstract void paint(Graphics g);

	public abstract void animate();
}
