package game;

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
		for (int i = 0; i < 4; i++) {
			planesList.add(new Plane(this));
		}
	}

	@Override
	public void createCollision() {

	}

	@Override
	public void update(float gap) {
		if (produce > 1000) {
			planesList.add(new Plane(this));
			produce = 0;
		}
		if (tuioReference != null) {
			this.position.x = tuioReference.getX();
			this.position.y = tuioReference.getY();
			this.angle = tuioReference.getAngle();
		}
		produce += gap;
	}
}
