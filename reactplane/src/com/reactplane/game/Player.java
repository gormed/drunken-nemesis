package com.reactplane.game;

import TUIO.TuioObject;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class Player implements Updateable {

	int tuioSymbolID = -1;
	TuioObject tuioObject;
	Cursor cursor = new Cursor();
	Airport airport;

	Level level;

	String name;

	public Player(String name, int tuioSymbolID, Level level) {
		this.name = name;
		this.tuioSymbolID = tuioSymbolID;
		this.level = level;
		this.airport = new Airport();
	}

	public Airport getAirport() {
		return airport;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public String getName() {
		return name;
	}

	public int getTuioSymbolID() {
		return tuioSymbolID;
	}

	public TuioObject getTuioObject() {
		return tuioObject;
	}

	public void setTuioObject(TuioObject object) {
		this.tuioObject = object;
	}

	@Override
	public void update(float gap) {
		if (tuioObject != null) {
			switch (level.getState()) {
			case PREPARE:
				cursor.position.x = tuioObject.getX();
				cursor.position.y = tuioObject.getY();
				cursor.angle = tuioObject.getAngle();
				airport.position.x = tuioObject.getX();
				airport.position.y = tuioObject.getY();
				airport.angle = tuioObject.getAngle();
				break;
			case STARTED:
				cursor.position.x = tuioObject.getX();
				cursor.position.y = tuioObject.getY();
				cursor.angle = tuioObject.getAngle();
				break;
			case ENDED:
				
				break;
			default:
				break;
			}
			airport.updateObservers(gap);
			cursor.updateObservers(gap);
		}
	}

	@Override
	public void updateObservers(float gap) {

	}

}// end Player