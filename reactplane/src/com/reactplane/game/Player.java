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
	
	String name;

	public Player(String name, int tuioSymbolID){
		this.name = name;
		this.tuioSymbolID = tuioSymbolID;
		
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

	@Override
	public void update(float gap) {
		
	}

	@Override
	public void updateObservers(float gap) {
		
	}

}//end Player