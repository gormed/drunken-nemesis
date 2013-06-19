package com.reactplane.game;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:24
 */
public class Airport extends Entity {

	public static final int AIRPORT_HEIGHT = 40;
	public static final int AIRPORT_WIDTH = 100;

	public Airport() {
		width = AIRPORT_WIDTH;
		height = AIRPORT_HEIGHT;
		createCollision(null);
	}

	@Override
	public void createCollision(Shape shape) {
		collision = new Rectangle2D.Float(-width / 2, -height / 2, width,
				height);
	}

	@Override
	public void update(float gap) {

	}
}// end Airport