package com.reactplane.controller;

import java.util.Hashtable;

import TUIO.TuioCursor;
import TUIO.TuioObject;

import com.reactplane.game.Level;
import com.reactplane.game.Player;
import com.reactplane.game.Level.LevelState;
import com.reactplane.gui.GameWindow;
import com.reactplane.gui.elements.VisualAirport;
import com.reactplane.gui.elements.VisualCursor;

public class GameController {

	private Hashtable<Integer, Player> playerSymbols = new Hashtable<Integer, Player>();
	private Level level;
	private GameWindow window;
	private TuioInputListener tuioInputListener;
	private boolean initialized = false;

	private static GameController instance;

	public static GameController getInstance() {
		if (instance != null)
			return instance;
		return instance = new GameController();
	}

	private GameController() {
		super();
	}

	public void initialize(Level level, GameWindow window) {
		if (initialized)
			return;
		this.level = level;
		this.window = window;
		this.tuioInputListener = window.getTuioInputListener();

		initialized = true;
	}

	public void destroy() {
		if (!initialized)
			return;
		this.level = null;
		this.window = null;

		initialized = false;
	}

	public Hashtable<Integer, Player> getPlayerSymbols() {
		return playerSymbols;
	}

	void addTuioObject(TuioObject object) {
		switch (level.getState()) {
		case PREPARE:

			if (level.getPlayers().size() < 3) {
				if (!playerSymbols.containsKey(object.getSymbolID())) {
					int id = object.getSymbolID();
					Player joins = new Player("Player" + id, id, level);
					playerSymbols.put(id, joins);
					level.getPlayers().add(joins);
					GameWindow.getInstance().addElement(
							new VisualCursor(joins.getCursor()),
							joins.getCursor());
				}
			}

			if (playerSymbols.containsKey(object.getSymbolID())) {
				Player p = playerSymbols.get(object.getSymbolID());
				if (p.getTuioObject() == null)
					p.setTuioObject(object);
			}
			break;
		case STARTED:

			break;
		case ENDED:

			break;

		default:
			break;
		}

	}

	void removeTuioObject(TuioObject object) {
		switch (level.getState()) {
		case PREPARE:

			if (playerSymbols.containsKey(object.getSymbolID())) {
				Player p = playerSymbols.get(object.getSymbolID());
				if (p.getTuioObject() != null)
					p.setTuioObject(null);
			}

			break;
		case STARTED:

			break;
		case ENDED:

			break;

		default:
			break;
		}
	}

	void updateTuioObjects(Hashtable<Integer, TuioObject> symbolList) {
		for (Player p : level.getPlayers()) {

		}
	}

	void updateTuioCursors(Hashtable<Long, TuioCursor> cursorList, TuioCursor cursor) {
		level.updateCursor(cursorList, cursor);
		if (cursorList.size() == 1 && level.getState() == LevelState.PREPARE) {
			level.setState(LevelState.STARTED);
			for (Player p : level.getPlayers()) {
				if (p != null) {
					window.addElement(new VisualAirport(p.getAirport()), p.getAirport());
				}
					
			}
		}
	}

	public TuioInputListener getTuioInputListener() {
		return tuioInputListener;
	}
}
