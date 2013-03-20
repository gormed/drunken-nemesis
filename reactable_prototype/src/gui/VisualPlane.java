package gui;

import game.Plane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;

public class VisualPlane extends LevelElement {

	Plane plane;
	
	public VisualPlane(Plane plane) {
		this.plane = plane;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Plane) {
			Plane p = (Plane) o;
		}
	}

	@Override
	public void draw(Graphics2D g) {

	}

}
