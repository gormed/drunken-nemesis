package com.reactplane.gui;

import java.awt.Graphics2D;
import java.util.Observer;

import com.reactplane.util.Vector2D;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public abstract class VisualElement implements Observer {

	public Canvas canvas;
	
	/** The unique element id. */
	private static long uniqueElementID = 0;

	/**
	 * Gets the id internally.
	 * 
	 * @return the id
	 */
	private static long getID() {
		return uniqueElementID++;
	}

	/** The name of the element. */
	private String name = "element";

	Vector2D position;

	float angle;

	/** The visiblity flag. */
	boolean isVisible;

	/** The id. */
	private long id = getID();

	/**
	 * Instantiates a new gUI element.
	 * 
	 * @param name
	 *            the name
	 */
	public VisualElement(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new gUI element.
	 */
	public VisualElement() {
		name = name + "_" + id;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public abstract void draw(Graphics2D g2, int width, int height);
}// end VisualElement