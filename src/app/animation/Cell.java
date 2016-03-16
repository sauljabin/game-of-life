/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of GameOfLife.
 * 
 * GameOfLife is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Cell extends Animated {

	public static final int STATE_OFF = 0;
	public static final int STATE_ON = 1;
	private int state;
	private int nextState;
	private int initState;
	private int x;
	private int y;
	private int w;
	private int h;
	private Double shape;

	private int m;
	private int n;

	private Cell[][] neighbors;

	public Cell[][] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(Cell[][] neighbors) {
		this.neighbors = neighbors;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Cell() {
		setAnimatedMouseListener(new CellMouseListener());
	}

	public Cell(int m, int n) {
		this();
		this.n = n;
		this.m = m;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNextState() {
		return nextState;
	}

	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getInitState() {
		return initState;
	}

	public void setInitState(int initState) {
		this.initState = initState;
	}

	@Override
	public void init() {
		shape = new Rectangle2D.Double();
		x = n * w;
		y = m * h;
		nextState = initState;
	}

	@Override
	public Rectangle2D getShape() {
		shape.setFrame(x, y, w, h);
		return shape;
	}

	@Override
	public void paint(Graphics g) {
		state = nextState;
		if (state == STATE_OFF)
			g.setColor(Color.WHITE);
		if (state == STATE_ON)
			g.setColor(Color.BLACK);
		g.fillRect(x, y, w, h);
	}

	@Override
	public void animate() {
		int up = m - 1 < 0 ? neighbors.length - 1 : m - 1;
		int down = m + 1 > neighbors.length - 1 ? 0 : m + 1;
		int left = n - 1 < 0 ? neighbors[0].length - 1 : n - 1;
		int right = n + 1 > neighbors[0].length - 1 ? 0 : n + 1;

		int totalON = 0;

		if (neighbors[up][left].getState() == STATE_ON)
			totalON++;

		if (neighbors[up][n].getState() == STATE_ON)
			totalON++;

		if (neighbors[up][right].getState() == STATE_ON)
			totalON++;

		if (neighbors[m][left].getState() == STATE_ON)
			totalON++;

		if (neighbors[m][right].getState() == STATE_ON)
			totalON++;

		if (neighbors[down][left].getState() == STATE_ON)
			totalON++;

		if (neighbors[down][n].getState() == STATE_ON)
			totalON++;

		if (neighbors[down][right].getState() == STATE_ON)
			totalON++;

		// RULES
		if (state == STATE_ON && totalON < 2) {
			nextState = STATE_OFF;
		} else if (state == STATE_ON && totalON > 3) {
			nextState = STATE_OFF;
		} else if (state == STATE_ON && (totalON == 3 || totalON == 2)) {
			nextState = STATE_ON;
		} else if (state == STATE_OFF && totalON == 3) {
			nextState = STATE_ON;
		}
	}

}
