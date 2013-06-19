package com.reactplane.gui.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import com.reactplane.controller.TuioInputListener;
import com.reactplane.game.Cursor;
import com.reactplane.gui.GameWindow;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class VisualCursor extends VisualElement {

	Cursor cursor;

	public VisualCursor(Cursor cursor) {
		this.cursor = cursor;
		visual = cursor.getCollision();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Cursor) {
			Cursor c = (Cursor) o;
			this.position.x = c.getPosition().x;
			this.position.y = c.getPosition().y;
			this.angle = c.getAngle();
		}
	}

	@Override
	public void draw(Graphics2D g2, int width, int height) {
		
		float Xpos = position.x * width;
		float Ypos = position.y * height;
		float scale = height / (float) TuioInputListener.table_size;

		AffineTransform trans = new AffineTransform();
		trans.translate(-position.x, -position.y);
		trans.translate(Xpos, Ypos);
		trans.scale(scale, scale);
		AffineTransform rot = AffineTransform.getRotateInstance(angle, position.x, position.y);
		worldVisual = rot.createTransformedShape(visual);
		worldVisual = trans.createTransformedShape(worldVisual);

		g2.setPaint(Color.black);
		g2.draw(worldVisual);
		if (GameWindow.getInstance().getGameInputListener().isDebug()) {
			g2.setPaint(Color.red);
			g2.drawString(getName() + "", Xpos - 10, Ypos);
		}
	}
}// end VisualCursor