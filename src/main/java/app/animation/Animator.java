/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of GameOfLife.
 * <p>
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import java.awt.*;
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

    public Animator() {
        delay = 40l;
        animateds = new Vector<Animated>();
        thread = new Thread(this);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                animatorMouseDragged(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                animatorMousePressed(e);
            }
        });
    }

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

    public void makeImage() {
        image = createImage(getWidth(), getHeight());
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    public void animatorMousePressed(MouseEvent e) {
        for (int i = 0; i < animateds.size(); i++) {
            if (animateds.get(i).getShape() != null && animateds.get(i).getAnimatedMouseListener() != null) {
                if (animateds.get(i).getShape().contains(e.getPoint())) {
                    animateds.get(i).getAnimatedMouseListener().animatedMousePressed(animateds.get(i));
                }
            }
        }
    }

    public void animatorMouseDragged(MouseEvent e) {
        for (int i = 0; i < animateds.size(); i++) {
            if (animateds.get(i).getShape() != null && animateds.get(i).getAnimatedMouseListener() != null) {
                if (animateds.get(i).getShape().contains(e.getPoint())) {
                    animateds.get(i).getAnimatedMouseListener().animatedMouseDragged(animateds.get(i));
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
        image.getGraphics().clearRect(0, 0, getWidth(), getHeight());
        getGraphics().drawImage(image, 0, 0, this);
    }

    public void restart() {
        initAnimateds();
    }

    public void initAnimateds() {
        for (int i = 0; i < animateds.size(); i++) {
            animateds.get(i).init();
        }
    }
}
