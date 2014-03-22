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

package app.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.Log;
import app.Translate;
import app.animation.Cell;

public class ControllerViewApp extends WindowAdapter implements ActionListener, ChangeListener {

	private ViewApp viewApp;
	private Cell[][] cells;

	public ControllerViewApp() {
		viewApp = new ViewApp();
		viewApp.setController(this);
		Log.setLogTextArea(viewApp.getTarConsole());

		viewApp.getBtnClear().setEnabled(false);
		viewApp.getBtnNext().setEnabled(false);
		viewApp.getBtnPause().setEnabled(false);
		viewApp.getBtnRestart().setEnabled(false);
		viewApp.getBtnResume().setEnabled(false);

		generateCells();
	}

	public void generateCells() {
		int size = 10;
		int colum = viewApp.getAnimator().getWidth() / size;
		int row = viewApp.getAnimator().getHeight() / size;
		cells = new Cell[row][colum];

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i, j);
				cells[i][j].setW(size);
				cells[i][j].setH(size);
				cells[i][j].setNeighbors(cells);
				cells[i][j].setInitState(Math.random() > .5 ? Cell.STATE_ON : Cell.STATE_OFF);
				viewApp.getAnimator().addAnimated(cells[i][j]);
			}
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		close();
	}

	public void close() {
		viewApp.getAnimator().stop();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		viewApp.dispose();
		System.exit(0);
	}

	public void about() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewAbout();
			}
		});
	}

	public void showConfig() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ViewConfig();
			}
		});
	}

	public void start() {
		setDelay();
		viewApp.getAnimator().start();
		Log.info(getClass(), Translate.get("INFO_STARTED"));

		viewApp.getBtnStart().setEnabled(false);
		viewApp.getBtnClear().setEnabled(false);
		viewApp.getBtnNext().setEnabled(false);
		viewApp.getBtnPause().setEnabled(true);
		viewApp.getBtnRestart().setEnabled(false);
		viewApp.getBtnResume().setEnabled(false);
	}

	public void restart() {
		viewApp.getAnimator().restart();

		resume();
	}

	public void pause() {
		viewApp.getAnimator().pause();
		Log.info(getClass(), Translate.get("INFO_PAUSED"));

		viewApp.getBtnStart().setEnabled(false);
		viewApp.getBtnClear().setEnabled(true);
		viewApp.getBtnNext().setEnabled(true);
		viewApp.getBtnPause().setEnabled(false);
		viewApp.getBtnRestart().setEnabled(true);
		viewApp.getBtnResume().setEnabled(true);
	}

	public void resume() {
		setDelay();
		viewApp.getAnimator().resume();
		Log.info(getClass(), Translate.get("INFO_RESUME"));

		viewApp.getBtnStart().setEnabled(false);
		viewApp.getBtnClear().setEnabled(false);
		viewApp.getBtnNext().setEnabled(false);
		viewApp.getBtnPause().setEnabled(true);
		viewApp.getBtnRestart().setEnabled(false);
		viewApp.getBtnResume().setEnabled(false);
	}

	public void clear() {
		viewApp.getAnimator().clear();
		Log.info(getClass(), Translate.get("INFO_CLEARED"));
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].setNextState(Cell.STATE_OFF);
			}
		}
	}

	public void next() {
		viewApp.getAnimator().next();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(viewApp.getMenuItemClose()))
			close();
		else if (source.equals(viewApp.getMenuItemAbout()))
			about();
		else if (source.equals(viewApp.getMenuItemShowConfig()))
			showConfig();
		else if (source.equals(viewApp.getBtnStart()))
			start();
		else if (source.equals(viewApp.getBtnPause()))
			pause();
		else if (source.equals(viewApp.getBtnResume()))
			resume();
		else if (source.equals(viewApp.getBtnClose()))
			close();
		else if (source.equals(viewApp.getBtnClear()))
			clear();
		else if (source.equals(viewApp.getBtnNext()))
			next();
		else if (source.equals(viewApp.getBtnRestart()))
			restart();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		if (source.equals(viewApp.getSpinDelay()))
			setDelay();

	}

	public void setDelay() {
		viewApp.getAnimator().setDelay(((Integer) viewApp.getSpinDelay().getValue()).longValue());
	}

}
