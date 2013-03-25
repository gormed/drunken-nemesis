package com.reactplane.game;

import java.util.ArrayList;

import com.reactplane.data.LevelData;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class Level implements Updateable {

	public Collision collision;
	public enum LevelState {
		PREPARE, STARTED, ENDED
	}

	public static final int LEVEL_HEIGHT = 600;

	public static final int LEVEL_WIDTH = 800;

	private static Level instance;

	private static boolean isInistialized = false;

	private boolean isOver = false;

	private LevelState state = LevelState.PREPARE;

	private LevelData levelData;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	
	/**
	 * Instantiates a new level.
	 */
	private Level() {

	}

	/**
	 * Gets the single instance of Level.
	 * 
	 * @return single instance of Level
	 */
	public static Level getInstance() {
		if (instance != null)
			return instance;
		return instance = new Level();
	}

	/**
	 * Initializes the level with the given data.
	 * 
	 * @param data
	 *            the data
	 */
	public void initialize(LevelData data) {
		if (isInistialized)
			return;

		levelData = data;
		state = LevelState.PREPARE;
		/*
		 * This code works only on windows/not with the openJDK for linux
		 * com.sun.security.auth.module.NTSystem NTSystem = new
		 * com.sun.security.auth.module.NTSystem(); localPlayer = new
		 * Player(System.getProperty(NTSystem.getName()), this);
		 */
		//playerList = new HashMap<Integer, Player>();

		isInistialized = true;
	}

	/**
	 * Destorys the level instance so it can be reinitialized.
	 */
	public void destory() {
		if (isInistialized)
			return;

		levelData = null;
		//playerList = null;
		state = LevelState.ENDED;

		isInistialized = false;
	}

	@Override
	public void update(float gap) {
		for (Plane p : planes) {
			p.updateObservers(gap);
		}
	}

	@Override
	public void updateObservers(float gap) {
		// TODO Auto-generated method stub
		
	}
	public boolean isOver() {
		return isOver;
	}

	public LevelState getState() {
		return state;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
}//end Level