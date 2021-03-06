/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of GameOfLife.
 * <p>
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

public interface AnimatedMouseListener {

    public void animatedMousePressed(Animated source);

    public void animatedMouseDragged(Animated source);
}
