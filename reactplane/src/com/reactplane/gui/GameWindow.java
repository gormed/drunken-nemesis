package com.reactplane.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.text.Position;

import TUIO.TuioObject;

import com.reactplane.controller.GameController;
import com.reactplane.controller.TuioInputListener;
import com.reactplane.data.LevelData;
import com.reactplane.game.Entity;
import com.reactplane.game.Level;
import com.reactplane.gui.elements.VisualElement;
import com.reactplane.gui.elements.VisualText;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class GameWindow extends JFrame {
	// =========================================================
	// Singleton
	// =========================================================
	private static GameWindow instance;

	public static GameWindow getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new GameWindow();
	}

	// =========================================================
	// Fields
	// =========================================================
	private static final long serialVersionUID = -5097177280076223641L;

	private GameController controller;

	private Level level;

	private TuioInputListener tuioInputListener;

	private GameInputListener gameInputListener;

	private Runnable updater;

	private Thread updateThread;

	private GameWindowListener windowListener;

	private Canvas canvas;

	private GraphicsDevice device;

	private boolean active = false;

	private double UPS = 0;
	private double ups = 0;
	private double FPS = 0;
	private double fps = 0;
	private double second = 0;
	private double gap = 0;
	private double time = System.currentTimeMillis();
	private double updateTime = time;
	private double drawTime = time;

	// =========================================================
	// Methods
	// =========================================================

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
		controller = GameController.getInstance();
		controller.initialize(level, this);
		loadLevel();
		// update loop
		updater = new Runnable() {

			@Override
			public void run() {
				// init time
				time = System.currentTimeMillis();
				updateTime = time;
				drawTime = time;
				// sync the key input with thread
				synchronized (gameInputListener) {
					// loop endless until exit request
					while (!gameInputListener.exit) {
						if (!active)
							updateTime = System.currentTimeMillis();
						if (second >= 1000) {
							UPS = ups;
							FPS = fps;
							second = 0;
							ups = 0;
							fps = 0;
						}

						// get current time
						time = System.currentTimeMillis();
						gap = (double) time - (double) updateTime;
						second += gap;
						ups++;
						// update level elements
						level.update((float) (((double) gap) * 0.001));
						if ((time - drawTime) >= 16) {
							// draw synchronized all 16 ms
							canvas.repaint();
							drawTime = time;
							fps++;
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
		addElement(new VisualText("FPS: ", 10, 20) {
			@Override
			public void draw(Graphics2D g, int width, int height) {
				if (isVisible) {
					g.setColor(Color.black);
						String s = String.format("%s %2.1f DT: %3.0f UPS: %2.1f",
								this.getText(), FPS, ((double) time - (double) drawTime), UPS);
						g.drawString(s, position.x, position.y);
				}
			}
		}, null);

	}

	public void addElement(VisualElement element, Entity entity) {
		// add the observer
		if (entity != null) {
			entity.addObserver(element);
			// add entity to list
			level.getEntityList().put(entity.getId(), entity);
		}
		// add the according gui element to draw list
		canvas.addElement(element);
	}

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

	public Level getLevel() {
		return level;
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
		// if (level.getPlayers().containsKey(object.getSymbolID()))
		// return;
		// Player p = new Player("Player" + level.getPlayers().size(),
		// object.getSymbolID());
		//
		// level.addPlayer(object.getSymbolID(), p);
		// addElement(new VisualAirport(p.getAirport()), p.getAirport());

	}
}// end GameWindow