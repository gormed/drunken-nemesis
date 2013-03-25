package com.reactplane.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The listener class for receiving key events.
 */
public class GameInputListener implements
		KeyListener {

	/** The left movement flag for the left arrow key. */
	boolean left;

	/** The right movement flag for the right arrow key. */
	boolean right;

	/** The exit flag if ESC was pressed. */
	boolean exit;

	boolean debug = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			setDebug(!isDebug());
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			exit = true;
		}
	}

	public boolean isDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public boolean isExit() {
		return exit;
	}
}
