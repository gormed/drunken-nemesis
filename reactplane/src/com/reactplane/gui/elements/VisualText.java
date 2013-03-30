package com.reactplane.gui.elements;

import java.awt.Graphics2D;
import java.util.Observable;

/**
 * The Class TextElement displays text on JAVA awt surfaces.
 */
public abstract class VisualText extends VisualElement {

	/** The text. */
	private String text = "";

	/**
	 * Instantiates a new text element.
	 * 
	 * @param text
	 *            the text
	 */
	public VisualText(String text, int x, int y){
		this.text = text;
		this.isVisible = true;
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see com.arkaneud.gui.GUIElement#draw(java.awt.Graphics)
	 */
	@Override
	public abstract void draw(Graphics2D g, int width, int height);

	/* (non-Javadoc)
	 * @see com.arkaneud.gui.GUIElement#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

	}

}