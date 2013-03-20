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
 * File: Player.java
 * Type: Player
 *
 * Documentation created: 14.03.2013 - 14:27:14 by Hans Ferchland
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package game;

import game.Level.LevelState;
import gui.GameWindow;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.Observable;

import TUIO.TuioObject;

/**
 * The Class Player represents the player in simulation and holds the paddle as
 * well as the ball references.
 */
public class Player extends Observable implements Updateable {

	/** The has lost flag indicates if the player has lost the game. */
	private boolean hasLost = false;

	/** The has won flag indicates if the player has won the game. */
	private boolean hasWon = false;

	/** The lives of the player. */
	private int lives = 3;

	/** The name of the player. */
	private String name = "Player";

	/** The points of the player. */
	private int points = 0;

	private Airport airport;

	private TuioObject playerTUIOObject;

	/**
	 * Instantiates a new player.
	 * 
	 * @param name
	 *            the name
	 */
	public Player(String name, TuioObject object) {
		this.name = name;
		this.playerTUIOObject = object;
		this.airport = new Airport(this, object);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arkaneud.game.Updateable#update(long)
	 */
	@Override
	public void update(float gap) {
		// dont do anything if the player has lost or won
		if (hasWon || hasLost)
			return;
		if (playerTUIOObject != null) {
			airport.updateObservers(gap);
			if (Level.getInstance().getState() != LevelState.STARTED)
				return;
			ArrayList<Player> players = new ArrayList<Player>(Level
					.getInstance().getPlayerList());
			players.remove(this);
			for (Player p : players) {
				for (Plane plane : p.getAirport().getPlanesList()) {
					if (plane != null && plane.getCollision().contains(
							new Point2D.Float(playerTUIOObject.getX(),
									playerTUIOObject.getY()))) {
						plane.setCarrier(getAirport());
					}
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arkaneud.game.Updateable#updateObservers(long)
	 */
	@Override
	public void updateObservers(float gap) {
		// first update the object
		update(gap);
		// set changed flag
		setChanged();
		// notify the observing objects (gui) that there are changes to display
		notifyObservers(gap);
	}

	/**
	 * Checks if player has won.
	 * 
	 * @return true, if won
	 */
	public boolean hasWon() {
		return hasWon;
	}

	/**
	 * Checks if player has lost.
	 * 
	 * @return true, if lost
	 */
	public boolean hasLost() {
		return hasLost;
	}

	public Airport getAirport() {
		return this.airport;
	}

	/**
	 * Gets the lives of the player.
	 * 
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Gets the name of the player.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the points of the player.
	 * 
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Adds the points recieved for destroying a brick.
	 * 
	 * @param points
	 *            the points
	 */
	void addPoints(int points) {
		this.points += points;
	}

}