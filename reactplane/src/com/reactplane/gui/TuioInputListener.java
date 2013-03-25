package com.reactplane.gui;

import java.util.Hashtable;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class TuioInputListener implements TuioListener {

	public static final int finger_size = 15;
	public static final int object_size = 60;
	public static final int table_size = 760;

	private Hashtable<Long,TuioInputObject> objectList = new Hashtable<Long,TuioInputObject>();
	private Hashtable<Long,TuioCursor> cursorList = new Hashtable<Long,TuioCursor>();

	public boolean verbose = false;

	public void addTuioObject(TuioObject tobj) {
		TuioInputObject demo = new TuioInputObject(tobj);
		objectList.put(tobj.getSessionID(),demo);
		if (verbose) 
			System.out.println("add obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+") "+tobj.getX()+" "+tobj.getY()+" "+tobj.getAngle());	
	}

	public void updateTuioObject(TuioObject tobj) {

		TuioInputObject demo = (TuioInputObject)objectList.get(tobj.getSessionID());
		demo.update(tobj);

		if (verbose) 
			System.out.println("set obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+") "+tobj.getX()+" "+tobj.getY()+" "+tobj.getAngle()+" "+tobj.getMotionSpeed()+" "+tobj.getRotationSpeed()+" "+tobj.getMotionAccel()+" "+tobj.getRotationAccel()); 	
	}

	public void removeTuioObject(TuioObject tobj) {
		objectList.remove(tobj.getSessionID());

		if (verbose) 
			System.out.println("del obj "+tobj.getSymbolID()+" ("+tobj.getSessionID()+")");	
	}

	public void addTuioCursor(TuioCursor tcur) {

		if (!cursorList.containsKey(tcur.getSessionID())) {
			cursorList.put(tcur.getSessionID(), tcur);

		}

		if (verbose) 
			System.out.println("add cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+") "+tcur.getX()+" "+tcur.getY());	
	}

	public void updateTuioCursor(TuioCursor tcur) {


		if (verbose) 
			System.out.println("set cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+") "+tcur.getX()+" "+tcur.getY()+" "+tcur.getMotionSpeed()+" "+tcur.getMotionAccel()); 
	}

	public void removeTuioCursor(TuioCursor tcur) {

		cursorList.remove(tcur.getSessionID());	


		if (verbose) 
			System.out.println("del cur "+tcur.getCursorID()+" ("+tcur.getSessionID()+")"); 
	}

	public void refresh(TuioTime frameTime) {
		//repaint();
	}

	public Hashtable<Long, TuioInputObject> getObjectList() {
		return objectList;
	}

	public Hashtable<Long, TuioCursor> getCursorList() {
		return cursorList;
	}


}