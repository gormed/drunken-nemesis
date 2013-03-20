package game;

import game.Level.LevelState;
import gui.GameWindow;
import gui.VisualPlane;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import TUIO.TuioObject;

public class Airport extends Entity {

	public static final int AIRPORT_HEIGHT = 100;

	public static final int AIRPORT_WIDTH = 100;

	Player owner;

	TuioObject tuioReference;

	private ArrayList<Plane> planesList = new ArrayList<Plane>();

	private float produce;

	public Airport(Player player, TuioObject object) {
		this.tuioReference = object;
		this.width = AIRPORT_WIDTH;
		this.height = AIRPORT_HEIGHT;
		this.position = new Point2D.Float(object.getX(), object.getY());
		this.owner = player;
		// for (int i = 0; i < 4; i++) {
		// planesList.add(new Plane(this));
		// }
	}

	@Override
	public void createCollision(Shape shape) {
		collision = shape;
	}

	@Override
	public void update(float gap) {

		if (tuioReference != null
				&& Level.getInstance().getState() != LevelState.STARTED) {
			this.position.x = tuioReference.getX();
			this.position.y = tuioReference.getY();
			this.angle = tuioReference.getAngle();
		}
		for (Plane p : planesList) {
			p.updateObservers(gap);
		}
		produce += gap;
		// do not produce if not started
		if (Level.getInstance().getState() != LevelState.STARTED)
			return;
		if (produce > 3) {
			Plane p = new Plane(this);
			GameWindow.getInstance().addElement(new VisualPlane(p), p);
			planesList.add(p);
			produce = 0;
		}

	}

	public Player getOwner() {
		return owner;
	}
	
	ArrayList<Plane> getPlanesList() {
		return planesList;
	}
}
