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
    
    private static int generalSessionID = 0;
    private boolean startScreen = true;
    
    private static int getSessionID() {
        return generalSessionID++;
    }
    
    int tuioSymbolID = -1;
    int userSessionID = getSessionID();
    long lastActiveTime = 0;
    Card card;
    private boolean loggedIn = false;
    private boolean restart = false;
    
    public User(TuioObject object) {
        this.tuioSymbolID = object.getSymbolID();
        this.card = new Card(this, object);
        lastActiveTime = System.currentTimeMillis();
        loggedIn = true;
    }
    
    public void update(TuioObject object) {
        card.update(object);
        lastActiveTime = System.currentTimeMillis();
    }

    public int getTuioSymbolID() {
        return tuioSymbolID;
    }

    public int getUserSessionID() {
        return userSessionID;
    }

    public Card getCard() {
        return card;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isRestart() {
        return restart;
    }
    
    void restart() {
        restart = true;
    }
    
    void logout() {
        loggedIn = false;
    }

    public boolean isStartScreen() {
        return startScreen;
    }

    public void setStartScreen(boolean startScreen) {
        this.startScreen = startScreen;
    }
    
    
}
