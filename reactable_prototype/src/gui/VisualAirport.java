package gui;

import game.Airport;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;

public class VisualAirport extends LevelElement {

	Airport airport;
	
	public VisualAirport(Airport airport) {
		this.airport = airport;
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public void draw(Graphics2D g) {

	}

}
