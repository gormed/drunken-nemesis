package gui;

import game.Airport;
import game.Plane;

import input.TuioInputListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

public class VisualPlane extends LevelElement {

	Plane plane;

	public VisualPlane(Plane plane) {
		this.plane = plane;

		visual = new Rectangle2D.Float(-Plane.PLANE_WIDTH / 2,
				-Plane.PLANE_HEIGHT / 2, Plane.PLANE_WIDTH, Plane.PLANE_HEIGHT);
		worldVisual = visual;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Plane) {
			Plane p = (Plane) o;
			x = p.getXPos();
			y = p.getYPos();
			angle = p.getAngle();
		}
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
		AffineTransform rot = AffineTransform.getRotateInstance(angle, x, y);
		worldVisual = trans.createTransformedShape(rot.createTransformedShape(visual));

		g.setPaint(Color.black);
		g.fill(worldVisual);
		if (GameWindow.getInstance().getDebugFlag()) {
			g.setPaint(Color.red);
			g.drawString(getName() + "", Xpos - 10, Ypos);
		}
	}
}
