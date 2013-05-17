/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import TUIO.TuioObject;
import com.opus.controller.TuioInputListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author hans
 */
public class UserManager {
       
    // Singleton
    private UserManager() {
    }
    
    public static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }
    
    private static class UserManagerHolder {

        private static final UserManager INSTANCE = new UserManager();
    }
    
    // Class
    
    private TuioInputListener tuioInputListener = new TuioInputListener();
    
    private Hashtable<Integer, User> userList = new Hashtable<Integer, User>();
    private Hashtable<Integer, User> userSymbolList = new Hashtable<Integer, User>();
    
    private ArrayList<User> newUsers = new ArrayList<User>();
    private ArrayList<User> removedUsers = new ArrayList<User>();
    
    
    public void addUserSymbol(TuioObject object) {
        if (userSymbolList.containsKey(object.getSymbolID())) {
            User u = userSymbolList.get(object.getSymbolID());
            userList.put(u.getUserSessionID(), u);
            //newUsers.add(u);
        } else {
            User newUser = new User(object);
            userList.put(newUser.userSessionID, newUser);
            userSymbolList.put(newUser.tuioSymbolID, newUser);
            newUsers.add(newUser);
        }
    }
//    
//    public void updateCards() {
//        for (Map.Entry<Integer, User> entry : userList.entrySet()) {
//            TuioObject tuioObject = tuioInputListener.getSymbolList().get(entry.getValue().getTuioSymbolID());
//            entry.getValue().update(tuioObject);
//        }
//    }
    
    public void updateUserSymbol(TuioObject object) {
        if (userSymbolList.containsKey(object.getSymbolID())) {
            User u = userSymbolList.get(object.getSymbolID());
            u.update(object);
        }
    }
    
    public void removeUserSymbol(TuioObject object) {
        if (!userSymbolList.containsKey(object.getSymbolID())) {
            System.err.println("User is not registered!");
        } else {
            User u = userSymbolList.get(object.getSymbolID());
            userList.remove(u.userSessionID);
            removedUsers.add(u);
        }
    }
    
    public void logoutUser(int symbolid) {
        userSymbolList.remove(symbolid);
    }
    
    public ArrayList<User> getNewUsers() {
        ArrayList l = new ArrayList<User>(newUsers);
        newUsers.clear();
        return l;
    }
    
    public ArrayList<User> getRemovedUsers() {
        ArrayList l = new ArrayList<User>(removedUsers);
        removedUsers.clear();
        return l;
    }

    public TuioInputListener getTuioInputListener() {
        return tuioInputListener;
    }
    
}
