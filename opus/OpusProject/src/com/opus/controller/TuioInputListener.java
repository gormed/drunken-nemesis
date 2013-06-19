package com.opus.controller;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;
import com.opus.logic.UserManager;
import java.util.HashMap;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class TuioInputListener implements TuioListener {

    public static final int finger_size = 15;
    public static final int object_size = 60;
    public static final int table_size = 760;
    private HashMap<Long, TuioInputObject> objectList = new HashMap<Long, TuioInputObject>();
    private HashMap<Long, TuioCursor> cursorList = new HashMap<Long, TuioCursor>();
    private HashMap<Integer, TuioObject> symbolList = new HashMap<Integer, TuioObject>();
    public boolean verbose = false;

    @Override
    public void addTuioObject(TuioObject tobj) {
        TuioInputObject demo = new TuioInputObject(tobj);
        objectList.put(tobj.getSessionID(), demo);
        symbolList.put(tobj.getSymbolID(), tobj);

        UserManager.getInstance().addUserSymbol(tobj);
        //GameController.getInstance().addTuioObject(tobj);

        if (verbose) {
            System.out.println("add obj " + tobj.getSymbolID() + " ("
                    + tobj.getSessionID() + ") " + tobj.getX() + " "
                    + tobj.getY() + " " + tobj.getAngle());
        }
    }

    @Override
    public void updateTuioObject(TuioObject tobj) {

        TuioInputObject demo = (TuioInputObject) objectList.get(tobj
                .getSessionID());
        demo.update(tobj);

        UserManager.getInstance().updateUserSymbol(tobj);
        //GameController.getInstance().updateTuioObjects(symbolList);

        if (verbose) {
            System.out.println("set obj " + tobj.getSymbolID() + " ("
                    + tobj.getSessionID() + ") " + tobj.getX() + " "
                    + tobj.getY() + " " + tobj.getAngle() + " "
                    + tobj.getMotionSpeed() + " " + tobj.getRotationSpeed()
                    + " " + tobj.getMotionAccel() + " "
                    + tobj.getRotationAccel());
        }
    }

    @Override
    public void removeTuioObject(TuioObject tobj) {
        objectList.remove(tobj.getSessionID());
        symbolList.remove(tobj.getSymbolID());

        UserManager.getInstance().removeUserSymbol(tobj);
        //GameController.getInstance().removeTuioObject(tobj);

        if (verbose) {
            System.out.println("del obj " + tobj.getSymbolID() + " ("
                    + tobj.getSessionID() + ")");
        }
    }

    @Override
    public void addTuioCursor(TuioCursor tcur) {

        if (!cursorList.containsKey(tcur.getSessionID())) {
            cursorList.put(tcur.getSessionID(), tcur);
        }

        if (verbose) {
            System.out.println("add cur " + tcur.getCursorID() + " ("
                    + tcur.getSessionID() + ") " + tcur.getX() + " "
                    + tcur.getY());
        }
    }

    @Override
    public void updateTuioCursor(TuioCursor tcur) {
        // GameController.getInstance().updateTuioCursors(cursorList, tcur);
        if (verbose) {
            System.out.println("set cur " + tcur.getCursorID() + " ("
                    + tcur.getSessionID() + ") " + tcur.getX() + " "
                    + tcur.getY() + " " + tcur.getMotionSpeed() + " "
                    + tcur.getMotionAccel());
        }
    }

    @Override
    public void removeTuioCursor(TuioCursor tcur) {

        cursorList.remove(tcur.getSessionID());
        
        if (verbose) {
            System.out.println("del cur " + tcur.getCursorID() + " ("
                    + tcur.getSessionID() + ")");
        }
    }

    @Override
    public void refresh(TuioTime frameTime) {
        // repaint();
    }

    public HashMap<Long, TuioInputObject> getObjectList() {
        return objectList;
    }

    public HashMap<Long, TuioCursor> getCursorList() {
        return cursorList;
    }

    public HashMap<Integer, TuioObject> getSymbolList() {
        return symbolList;
    }
}