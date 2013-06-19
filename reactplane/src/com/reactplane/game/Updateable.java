package com.reactplane.game;

public interface Updateable {
	/**
	 * Updates the object itself.
	 * 
	 * @param gap
	 *            the gap between the last update call, the delta of time
	 */
	public void update(float gap);

	/**
	 * Updates the observers of the object by sending the notifications.
	 * 
	 * @param gap
	 *            the gap between the last update call, the delta of time
	 */
	public void updateObservers(float gap);
}
