package com.reactplane.game;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class Player {

	Cursor cursor;
	Airport airport;
	
	String name;

	public Player(String name){
		this.name = name;
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

}//end Player