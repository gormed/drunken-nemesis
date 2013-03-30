package com.reactplane.game;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class Cursor extends Entity {

	public static final int CURSOR_WIDTH = 40;
	public static final float CURSOR_HEIGHT = 40;
	
	public Cursor(){
		width = CURSOR_WIDTH;
		height = CURSOR_HEIGHT;
		createCollision(null);
	}

	@Override
	public void createCollision(Shape shape) {
		collision = new Ellipse2D.Float(-width / 2, -height / 2, width,
				height);
	}

	@Override
	public void update(float gap) {
		
	}
}//end Cursor