/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of GameOfLife.
 * 
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

public class CellMouseListener implements AnimatedMouseListener {

	@Override
	public void animatedMousePressed(Animated source) {
		((Cell) source).setNextState(Cell.STATE_ON);
	}

	@Override
	public void animatedMouseDragged(Animated source) {
		animatedMousePressed(source);
	}

}
