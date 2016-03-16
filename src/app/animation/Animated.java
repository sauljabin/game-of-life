/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of GameOfLife.
 * 
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
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
