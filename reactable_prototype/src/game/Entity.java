/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * arkaneud Project (c) 2013 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Email me for any questions: hans.ferchland[at]gmx.de
 *
 * Project: arkaneud
 * File: Entity.java
 * Type: Entity
 *
 * Documentation created: 14.03.2013 - 13:47:09 by Hans Ferchland
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package game;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

/**
 * The Class Entity discribes the abstract base class that is the parent of all
 * game objects that can be observed and updated.
 */
public abstract class Entity extends Observable implements Updateable {

	/** The position of the entity. */
	Point2D.Float position;

	/** The width of the entity. */
	float width = 0;

	/** The height of the entity. */
	float height = 0;

	/** The collision rectangle of this entity. */
	Rectangle2D.Float collision;

	/**
	 * Instantiates a new entity.
	 */
	public Entity() {
		super();
		position = new Point2D.Float(0, 0);
	}

	/**
	 * Creates the collision rectangle for the entity.
	 */
	public abstract void createCollision();

	/**
	 * Gets the collision of the entity.
	 * 
	 * @return the collision
	 */
	public Rectangle2D getCollision() {
		return collision;
	}

	/**
	 * Sets the position of the entity. Position of entitys are always the
	 * center of it.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setPosition(float x, float y) {
		position.setLocation(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arkaneud.game.Updateable#updateObservers(float)
	 */
	@Override
	public void updateObservers(float gap) {
		update(gap);
		setChanged();
		notifyObservers(gap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arkaneud.game.Updateable#update(long)
	 */
	@Override
	public abstract void update(float gap);

	/**
	 * Gets the x pos of the entity. Position of entitys are always the center
	 * of it.
	 * 
	 * @return the x pos
	 */
	public float getXPos() {
		return position.x;
	}

	/**
	 * Gets the y pos of the entity. Position of entitys are always the center
	 * of it. This method flips the original coordinate to a y-axis that goes
	 * from top to bottom of the window/level!
	 * To obtain the real position in simulation, please use the position-field.
	 * 
	 * @return the y pos
	 */
	public float getYPos() {
		return Level.LEVEL_HEIGHT - position.y;
	}

	/**
	 * Gets the width of the entity.
	 * 
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Gets the height of the entity.
	 * 
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

}