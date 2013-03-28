package com.reactplane.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JFrame;

import TUIO.TuioCursor;
import TUIO.TuioObject;

import com.reactplane.data.LevelData;
import com.reactplane.game.Entity;
import com.reactplane.game.Level;
import com.reactplane.game.Player;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class GameWindow extends JFrame {

	private static final long serialVersionUID = -5097177280076223641L;

	private Level level;

	private TuioInputListener tuioInputListener;

	private GameInputListener gameInputListener;

	private Runnable updater;

	private Thread updateThread;

	private GameWindowListener windowListener;

	private Canvas canvas;

	private GraphicsDevice device;

	private boolean active = false;

	private static GameWindow instance;

	public static GameWindow getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new GameWindow();
	}

	/**
	 * Instantiates a new game window.
	 */
	private GameWindow() {
		// Frame setup
		super("ReactPlane");
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		setSize(Level.LEVEL_WIDTH, Level.LEVEL_HEIGHT);
		setResizable(false);
		setLocationByPlatform(true);
		// drawing logic
		add(canvas = new Canvas(this));
		canvas.setSize(Level.LEVEL_WIDTH, Level.LEVEL_HEIGHT);
		// add the listeners for user input and exit handling
		tuioInputListener = new TuioInputListener();
		addKeyListener(gameInputListener = new GameInputListener());
		addWindowListener(windowListener = new GameWindowListener(this));
		// create level from data
		level = Level.getInstance();
		level.initialize(new LevelData());
		loadLevel();
		// update loop
		updater = new Runnable() {

			@Override
			public void run() {
				// init time
				long time = System.currentTimeMillis();
				long updateTime = time;
				long drawTime = time;
				long gap = 0;
				// sync the key input with thread
				synchronized (gameInputListener) {
					// loop endless until exit request
					while (!gameInputListener.exit) {
						if (!active)
							updateTime = System.currentTimeMillis();
						// get current time
						time = System.currentTimeMillis();
						gap = time - updateTime;
						// update level elements
						level.update((float) (((double) gap) * 0.001));
						if ((time - drawTime) > 16) {
							// draw synchronized all 16 ms
							drawTime = time;
							canvas.repaint();
						}
						updateTime = time;
					}
				}
				// level.destory();
				System.exit(0);
			}
		};
		// update thread
		updateThread = new Thread(updater);
		updateThread.start();
	}

	/**
	 * Load level.
	 */
	private void loadLevel() {

		// add text for lives
		// addElement(new TextElement("Hello World: ", 50, 100) {
		// @Override
		// public void draw(Graphics2D g, int width, int height) {
		// if (isVisible) {
		// g.setColor(Color.black);
		// g.drawString(this.getText() + " ", x, y);
		// }
		// }
		// }, null);

	}

	// public GUIElement addElement(GUIElement element, Entity entity) {
	// // add the observer
	// if (entity != null)
	// entity.addObserver(element);
	// // add entity to list
	// entityList.put(element.getElementID(), entity);
	// // add the according gui element to draw list
	// return addElements.put(element.getElementID(), element);
	// }

	public TuioInputListener getTuioInputListener() {
		return tuioInputListener;
	}

	public GameInputListener getGameInputListener() {
		return gameInputListener;
	}

	public GameWindowListener getWindowListener() {
		return windowListener;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public GraphicsDevice getDevice() {
		return device;
	}

	// public void removeGUIElement(long id) {
	// Entity e = entityList.get(id);
	// GUIElement g = drawnElementsList.get(id);
	// e.deleteObserver(drawnElementsList.remove(id));
	//
	// removeElements.put(id, g);
	// entityList.remove(id);
	// }

	public void connectPlayer(TuioObject object) {
//		if (level.getPlayers().containsKey(object.getSymbolID()))
//			return;
//		Player p = new Player("Player" + level.getPlayers().size(),
//				object.getSymbolID());
//
//		level.addPlayer(object.getSymbolID(), p);
//		addElement(new VisualAirport(p.getAirport()), p.getAirport());

	}
}// end GameWindow