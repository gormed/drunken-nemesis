package gui;

import game.Airport;

import input.TuioInputListener;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public class VisualAirport extends LevelElement {

	Airport airport;

	public VisualAirport(Airport airport) {
		this.airport = airport;

		visual = new Rectangle2D.Float(-Airport.AIRPORT_WIDTH / 2,
				-Airport.AIRPORT_HEIGHT / 2, Airport.AIRPORT_HEIGHT,
				Airport.AIRPORT_HEIGHT);
		worldVisual = visual;
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void draw(Graphics2D g, int width, int height) {
		float Xpos = x * width;
		float Ypos = y * height;
		float scale = height / (float) TuioInputListener.table_size;

		AffineTransform trans = new AffineTransform();
		trans.translate(-x, -y);
		trans.translate(Xpos, Ypos);
		trans.scale(scale, scale);
		worldVisual = trans.createTransformedShape(visual);

		g.setPaint(Color.black);
		g.fill(worldVisual);
		g.setPaint(Color.white);
		g.drawString(getName()+"",Xpos-10,Ypos);
	}

}
