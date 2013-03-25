package com.reactplane.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;

import TUIO.TuioCursor;
import TUIO.TuioPoint;


/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:24
 */
public class Canvas extends JComponent {

	private GameWindow mainWindow;

	private HashMap<Long, VisualElement> visualElements = new HashMap<Long, VisualElement>();
	private HashMap<Long, VisualElement> addElements = new HashMap<Long, VisualElement>();
	private HashMap<Long, VisualElement> removeElements = new HashMap<Long, VisualElement>();

	public Canvas(GameWindow window) {
		mainWindow = window;
	}

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
		if (mainWindow.getGameInputListener().debug)
			drawDebug(g2);

		g2.setColor(Color.black);
		drawGUIElements(g2);
	}

	private void drawGUIElements(Graphics2D g2) {
		for (Map.Entry<Long, VisualElement> entry : addElements.entrySet()) {
			visualElements.put(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<Long, VisualElement> entry : removeElements.entrySet()) {
			visualElements.remove(entry.getKey());
		}
		for (Map.Entry<Long, VisualElement> entry : visualElements.entrySet()) {

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

		int w = (int) Math.round(width - scale * TuioInputListener.finger_size
				/ 2.0f);
		int h = (int) Math.round(height - scale * TuioInputListener.finger_size
				/ 2.0f);
		Enumeration<TuioCursor> cursors = mainWindow.getTuioInputListener()
				.getCursorList().elements();
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
							next_point.getScreenX(w), next_point.getScreenY(h));
					current_point = next_point;
				}
			}

			// draw the finger tip
			g2.setPaint(Color.lightGray);
			int s = (int) (scale * TuioInputListener.finger_size);
			g2.fillOval(current_point.getScreenX(w - s / 2),
					current_point.getScreenY(h - s / 2), s, s);
			g2.setPaint(Color.black);
			g2.drawString(tcur.getCursorID() + "", current_point.getScreenX(w),
					current_point.getScreenY(h));
		}

		// draw the objects
		Enumeration<TuioInputObject> objects = mainWindow
				.getTuioInputListener().getObjectList().elements();
		while (objects.hasMoreElements()) {
			TuioInputObject tobj = objects.nextElement();
			if (tobj != null)
				tobj.paint(g2, width, height);
		}
	}
}