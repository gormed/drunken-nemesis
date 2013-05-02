package com.reactplane.gui.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import com.reactplane.controller.TuioInputListener;
import com.reactplane.game.Airport;
import com.reactplane.game.Cursor;
import com.reactplane.gui.GameWindow;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class VisualAirport extends VisualElement {

	Airport airport;
	
	public VisualAirport(Airport airport){
		this.airport = airport;
		visual = airport.getCollision(); 
	}

	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof Airport) {
			Airport a = (Airport) o;
			this.position.x = a.getPosition().x;
			this.position.y = a.getPosition().y;
			this.angle = a.getAngle();
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
}//end VisualAirport