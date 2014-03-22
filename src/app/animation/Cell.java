package app.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Cell extends Animated {

	public static final int STATE_OFF = 0;
	public static final int STATE_ON = 1;
	private int state;
	private int nextState;
	private int x;
	private int y;
	private int w;
	private int h;

	public Cell() {
		setAnimatedMouseListener(new CellMouseListener());
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

	@Override
	public void init() {
		x = 100;
		y = 100;
		w = 50;
		h = 50;
	}

	@Override
	public Rectangle2D shape() {
		return null;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, w, h);
	}

	@Override
	public void animate() {
		x++;
	}

}
