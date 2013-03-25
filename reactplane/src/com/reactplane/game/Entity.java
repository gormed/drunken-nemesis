package com.reactplane.game;

import java.awt.Shape;
import java.util.Observable;

import com.reactplane.util.Vector2D;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public abstract class Entity extends Observable implements Updateable {

	Vector2D position = new Vector2D();

	float width = 0;

	float height = 0;

	float angle = 0;

	Shape collision;

	public Entity() {
		super();
	}

	public abstract void createCollision(Shape shape);

	@Override
	public abstract void update(float gap);

	@Override
	public void updateObservers(float gap) {
		update(gap);
		setChanged();
		notifyObservers(gap);
	}

	public Shape getCollision() {
		return collision;
	}

	public float getAngle() {
		return angle;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public Vector2D getPosition() {
		return position;
	}
}// end Entity