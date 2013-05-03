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

    public Card(User owner, TuioObject object) {
        this.owner = owner;
        this.tuioObject = object;
        update(tuioObject);
    }

    public void update(TuioObject object) {
        this.tuioObject = object;
        if (tuioObject != null) {
            xposition = tuioObject.getX();
            yposition = tuioObject.getY();
            angle = tuioObject.getAngle();
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

    public float getXposition() {
        return xposition;
    }

    public float getYposition() {
        return yposition;
    }
    
}
