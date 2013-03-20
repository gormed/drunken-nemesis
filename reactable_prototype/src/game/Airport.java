package game;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Airport extends Entity {

	public static final int AIRPORT_HEIGHT = 100;

	public static final int AIRPORT_WIDTH = 100;

	Player owner;

	private ArrayList<Plane> planesList = new ArrayList<Plane>();

	private float produce;

	public Airport(Player player, Point2D.Float position) {
		this.width = AIRPORT_WIDTH;
		this.height = AIRPORT_HEIGHT;
		this.position = position;
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
		produce += gap;
	}
}
