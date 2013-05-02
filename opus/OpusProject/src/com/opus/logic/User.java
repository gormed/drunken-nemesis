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
public class User {
    
    private static int generalSessionID = -1;
    
    private static int getSessionID() {
        return generalSessionID++;
    }
    
    int tuioSymbolID = -1;
    int userSessionID = getSessionID();
    Card card;
    
    public User(TuioObject object) {
        this.tuioSymbolID = object.getSymbolID();
        this.card = new Card(this, object);
        
    }
    
    public void update(TuioObject object) {
        card.update(object);
    }

    public int getTuioSymbolID() {
        return tuioSymbolID;
    }

    public static int getGeneralSessionID() {
        return generalSessionID;
    }

    public Card getCard() {
        return card;
    }
    
}
