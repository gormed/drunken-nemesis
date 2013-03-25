package com.reactplane.gui;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.reactplane.game.Level;


/**
 * The listener interface for receiving window events.
 */
public class GameWindowListener implements WindowListener {

	boolean fullscreen;
	boolean active;
	
	private GameWindow mainWindow;
	
	public GameWindowListener(GameWindow window) {
		mainWindow = window;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		if (fullscreen) {
			int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getWidth();
			int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getHeight();
			mainWindow.getCanvas().setSize(width, height);

			mainWindow.setSize(width, height);
			mainWindow.setUndecorated(true);
			mainWindow.getDevice().setFullScreenWindow(e.getWindow());
		} else {
			mainWindow.getCanvas().setSize(Level.LEVEL_WIDTH, Level.LEVEL_HEIGHT);

		}

		// setVisible(true);
		// repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		mainWindow.getGameInputListener().exit = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowClosed(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		active = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		active = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent
	 * )
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		active = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}
