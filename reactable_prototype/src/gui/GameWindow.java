package gui;

import game.Airport;
import game.Entity;
import game.Level;
import game.Player;

import input.TuioInputListener;
import input.TuioInputObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Point2D;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JComponent;
import javax.swing.JFrame;

import TUIO.TuioCursor;
import TUIO.TuioObject;
import TUIO.TuioPoint;

import data.LevelData;

/**
 * The Class GameWindow is the main game JFrame window of the game.
 */
public class GameWindow extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5097177280076223641L;

	/** The drawn elements list. */
	private HashMap<Long, GUIElement> drawnElementsList = new HashMap<Long, GUIElement>();
	private HashMap<Long, GUIElement> addElements = new HashMap<Long, GUIElement>();
	private HashMap<Long, GUIElement> removeElements = new HashMap<Long, GUIElement>();

	/** The entity list. */
	private HashMap<Long, Entity> entityList = new HashMap<Long, Entity>();

	/** The level reference. */
	private Level level;

	/** The game key listener. */
	private GameInputListener gameInputListener;

	/** The update method task. */
	private Runnable updater;

	/** The update thread. */
	private Thread updateThread;

	/** The window listener. */
	@SuppressWarnings("unused")
	private GameWindowListener windowListener;

	/** The game buffer. */
	private Canvas canvas;

	private GraphicsDevice device;

	/**
	 * The active flag that indicates if the window has focus. Pauses the game
	 * if false.
	 */
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
		add(canvas = new Canvas());
		canvas.setSize(Level.LEVEL_WIDTH, Level.LEVEL_HEIGHT);
		// add the listeners for user input and exit handling
		addKeyListener(gameInputListener = new GameInputListener());
		addWindowListener(windowListener = new GameWindowListener());
		// create level from data
		level = Level.getInstance();
		level.initialize(new LevelData());
		// load level, create ball and paddle
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
						// inject inputs manually

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
				level.destory();
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
		addElement(new TextElement("Hello World: ", 50, 100) {
			@Override
			public void draw(Graphics2D g, int width, int height) {
				if (isVisible) {
					g.setColor(Color.black);
					g.drawString(this.getText() + " ", x, y);
				}
			}
		}, null);

	}

	/**
	 * Adds the element.
	 * 
	 * @param element
	 *            the element
	 * @param entity
	 *            the entity
	 * @return the gUI element
	 */
	public GUIElement addElement(GUIElement element, Entity entity) {
		// add the observer
		if (entity != null)
			entity.addObserver(element);
		// add entity to list
		entityList.put(element.getElementID(), entity);
		// add the according gui element to draw list
		return addElements.put(element.getElementID(), element);
	}

	/**
	 * Gets the draw element.
	 * 
	 * @param id
	 *            the id
	 * @return the draw element
	 */
	public GUIElement getDrawElement(long id) {
		return drawnElementsList.get(id);
	}

	/**
	 * Gets the entity.
	 * 
	 * @param id
	 *            the id
	 * @return the entity
	 */
	public Entity getEntity(long id) {
		return entityList.get(id);
	}

	public GameInputListener getTuioInputListener() {
		return gameInputListener;
	}

	public int getCanvasWidth() {
		return canvas.width;
	}

	public int getCanvasHeight() {
		return canvas.height;
	}

	/**
	 * Removes the gui element.
	 * 
	 * @param id
	 *            the id
	 */
	public void removeGUIElement(long id) {
		Entity e = entityList.get(id);
		GUIElement g = drawnElementsList.get(id);
		e.deleteObserver(drawnElementsList.remove(id));

		removeElements.put(id, g);
		entityList.remove(id);
	}
	
	public boolean getDebugFlag() {
		return gameInputListener.debug;
	}
	
	public Hashtable<Long,TuioCursor> getCursorList() {
		return gameInputListener.getCursorList();
	}
	
	public Hashtable<Long, TuioInputObject> getObjectList() {
		return gameInputListener.getObjectList();
	}

	public void connectPlayer(TuioObject object) {
		Player p = new Player("Player" + level.getPlayers().size(), object);

		level.addPlayer(object, p);
		addElement(new VisualAirport(p.getAirport()), p.getAirport());
	}

	@SuppressWarnings("serial")
	private class Canvas extends JComponent {

		public void setSize(int w, int h) {
			super.setSize(w, h);
			width = w;
			height = h;
			scale = height / (float) TuioInputListener.table_size;
		}

		public void update(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);

			g2.setColor(Color.white);
			g2.fillRect(0, 0, width, height);
			if (gameInputListener.debug)
				drawDebug(g2);

			g2.setColor(Color.black);
			drawGUIElements(g2);
		}

		private void drawGUIElements(Graphics2D g2) {
			for (Map.Entry<Long, GUIElement> entry : addElements.entrySet()) {
				drawnElementsList.put(entry.getKey(), entry.getValue());
			}
			for (Map.Entry<Long, GUIElement> entry : removeElements.entrySet()) {
				drawnElementsList.remove(entry.getKey());
			}
			for (Map.Entry<Long, GUIElement> entry : drawnElementsList
					.entrySet()) {

				entry.getValue().draw(g2, width, height);
			}
			addElements.clear();
			removeElements.clear();
		}

		public void paint(Graphics g) {
			update(g);
		}

		// DEBUG

		public int width;
		public int height;
		private float scale = 1.0f;

		private void drawDebug(Graphics2D g2) {

			int w = (int) Math.round(width - scale
					* TuioInputListener.finger_size / 2.0f);
			int h = (int) Math.round(height - scale
					* TuioInputListener.finger_size / 2.0f);
			Enumeration<TuioCursor> cursors = gameInputListener.getCursorList()
					.elements();
			while (cursors.hasMoreElements()) {
				TuioCursor tcur = cursors.nextElement();
				if (tcur == null)
					continue;
				Vector<TuioPoint> path = tcur.getPath();
				TuioPoint current_point = path.elementAt(0);
				if (current_point != null) {
					// draw the cursor path
					g2.setPaint(Color.blue);
					for (int i = 0; i < path.size(); i++) {
						TuioPoint next_point = path.elementAt(i);
						g2.drawLine(current_point.getScreenX(w),
								current_point.getScreenY(h),
								next_point.getScreenX(w),
								next_point.getScreenY(h));
						current_point = next_point;
					}
				}

				// draw the finger tip
				g2.setPaint(Color.lightGray);
				int s = (int) (scale * TuioInputListener.finger_size);
				g2.fillOval(current_point.getScreenX(w - s / 2),
						current_point.getScreenY(h - s / 2), s, s);
				g2.setPaint(Color.black);
				g2.drawString(tcur.getCursorID() + "",
						current_point.getScreenX(w),
						current_point.getScreenY(h));
			}

			// draw the objects
			Enumeration<TuioInputObject> objects = gameInputListener
					.getObjectList().elements();
			while (objects.hasMoreElements()) {
				TuioInputObject tobj = objects.nextElement();
				if (tobj != null)
					tobj.paint(g2, width, height);
			}
		}
	}

	/**
	 * The listener class for receiving key events.
	 */
	private class GameInputListener extends TuioInputListener implements
			KeyListener {

		/** The left movement flag for the left arrow key. */
		boolean left;

		/** The right movement flag for the right arrow key. */
		boolean right;

		/** The exit flag if ESC was pressed. */
		boolean exit;

		boolean debug = true;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				debug = !debug;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = false;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = false;
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				exit = true;
			}
		}

		@Override
		public void addTuioObject(TuioObject tobj) {
			super.addTuioObject(tobj);
			connectPlayer(tobj);
		}
		
		@Override
		public void updateTuioObject(TuioObject tobj) {
			super.updateTuioObject(tobj);
			if (level.getPlayers().containsKey(tobj)) {
				level.getPlayers().get(tobj).getAirport().update(0);
			}
		}
	}

	/**
	 * The listener interface for receiving window events.
	 */
	private class GameWindowListener implements WindowListener {

		private boolean fullscreen;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent
		 * )
		 */
		@Override
		public void windowOpened(WindowEvent e) {
			if (fullscreen) {
				int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
						.getWidth();
				int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
						.getHeight();
				canvas.setSize(width, height);

				setSize(width, height);
				setUndecorated(true);
				device.setFullScreenWindow(e.getWindow());
			} else {
				canvas.setSize(Level.LEVEL_WIDTH, Level.LEVEL_HEIGHT);

			}

			// setVisible(true);
			// repaint();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent
		 * )
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			gameInputListener.exit = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent
		 * )
		 */
		@Override
		public void windowClosed(WindowEvent e) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent
		 * )
		 */
		@Override
		public void windowIconified(WindowEvent e) {
			active = false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.
		 * WindowEvent)
		 */
		@Override
		public void windowDeiconified(WindowEvent e) {
			active = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent
		 * )
		 */
		@Override
		public void windowActivated(WindowEvent e) {
			active = true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.
		 * WindowEvent)
		 */
		@Override
		public void windowDeactivated(WindowEvent e) {

		}

	}

	// /**
	// * The Class GameBuffer.
	// */
	// private class GameBuffer extends DoubleBuffer {
	//
	// /** The Constant serialVersionUID. */
	// private static final long serialVersionUID = 6402460431074494210L;
	//
	// /**
	// * Instantiates a new game buffer.
	// */
	// public GameBuffer() {
	// setBackground(Color.WHITE);
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see com.arkaneud.gui.DoubleBuffer#paintBuffer(java.awt.Graphics)
	// */
	// @Override
	// public void paintBuffer(Graphics g) {
	// for (Map.Entry<Long, GUIElement> entry : drawnElementsList
	// .entrySet()) {
	// entry.getValue().draw(g);
	// }
	// }
	// }

	public boolean isDebug() {
		return gameInputListener.debug;
	}
}