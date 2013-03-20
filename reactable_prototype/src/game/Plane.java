package game;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Plane extends Entity {

	Airport owner;
	Point2D.Float flightDirection = new Point2D.Float();
	float velocity = 1;
	

	public Plane(Airport airport) {
		this.owner = airport;
		this.position = new Point2D.Float(airport.position.x,
				airport.position.y);
		this.width = 20;
		this.height = 50;
		float rand = (float) (Math.random() * Math.PI * 2);
		flightDirection.x = (float) Math.cos(rand);
		flightDirection.y = (float) Math.sin(rand);
	}

	@Override
	public void createCollision() {
		this.collision = new Rectangle2D.Float(position.x, position.y, this.width, this.height);
	}

	@Override
	public void update(float gap) {
		this.position.x = flightDirection.x * gap * velocity;
	}

}
