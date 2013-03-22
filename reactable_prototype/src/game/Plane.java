package game;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Plane extends Entity {

	public static final int PLANE_HEIGHT = 50;
	public static final int PLANE_WIDTH = 20;
	Airport owner;
	Airport carrier;
	float velocity = 0.075f;

	public Plane(Airport airport) {
		this.owner = airport;
		this.position = new Point2D.Float(airport.position.x,
				airport.position.y);
		this.width = (float) PLANE_WIDTH / Level.LEVEL_WIDTH;
		this.height = (float) PLANE_HEIGHT / Level.LEVEL_HEIGHT;
		float rand = (float) (Math.random() * Math.PI * 2);
		this.angle = rand;
		createCollision(null);
	}

	@Override
	public void createCollision(Shape shape) {
		this.collision = new Rectangle2D.Float(position.x, position.y,
				this.width, this.height);
	}

	@Override
	public void update(float gap) {

		if (carrier == null) {
			this.position.x += Math.cos(angle) * gap * velocity;
			this.position.y += Math.sin(angle) * gap * velocity;
		} else {
			this.position.x = carrier.tuioObject.getX();
			this.position.y = carrier.tuioObject.getY();
			this.angle = carrier.tuioObject.getAngle();
		}
		createCollision(null);
	}

	public Airport getOwner() {
		return owner;
	}

	public void setCarrier(Airport carrier) {
		this.carrier = carrier;
	}

	public Airport getCarrier() {
		return carrier;
	}
}
