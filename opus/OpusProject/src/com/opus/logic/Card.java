/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import TUIO.TuioObject;

/**
 *
 * @author hans
 */
public class Card {

    User owner;
    TuioObject tuioObject;
    float xposition;
    float yposition;
    float angle;
    private boolean startScreen;

    public Card(User owner, TuioObject object) {
        this.owner = owner;
        startScreen = true;
        update(object);
    }

    void update(TuioObject object) {
        this.tuioObject = object;
        if (tuioObject != null) {
            xposition = tuioObject.getX();
            yposition = 1 - tuioObject.getY();
            angle = tuioObject.getAngle();
            //System.out.println("Pos: " + xposition + "/" + yposition + " Angle: " + angle);
            //System.out.println("Updated TuioObject " + owner.getTuioSymbolID() + " for Card " + owner.userSessionID);
        } else {
            System.err.println("Tuio Object not set for card from user " + owner.userSessionID + " with SymbolID " + owner.tuioSymbolID);
        }
    }

    public User getOwner() {
        return owner;
    }

    public float getAngle() {
        return angle;
    }

    public float getX() {
        return xposition;
    }

    public float getY() {
        return yposition;
    }

    public boolean isStartScreen() {
        return startScreen;
    }
    
    
}
