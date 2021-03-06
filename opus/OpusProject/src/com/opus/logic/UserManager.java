/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import TUIO.TuioObject;
import com.opus.controller.TuioInputListener;
import com.opus.gui.VisualCard;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer, User> userList = new HashMap<Integer, User>();
    private HashMap<Integer, User> userSymbolList = new HashMap<Integer, User>();
    private ArrayList<User> newUsers = new ArrayList<User>();
    private ArrayList<User> removedUsers = new ArrayList<User>();
    private ArrayList<Integer> logoutUsers = new ArrayList<Integer>();
    float logoutCounter = 0;
    float logoutCheckTime = 1;

    public void addUserSymbol(TuioObject object) {
        if (userSymbolList.containsKey(object.getSymbolID())) {
            User u = userSymbolList.get(object.getSymbolID());
            long currentTime = System.currentTimeMillis();
            float diff = ((u.lastActiveTime - currentTime) / 1000f);
            if (diff > 10) {
                logoutUser(object.getSymbolID());
            } else {
                userList.put(u.getUserSessionID(), u);
            System.out.println("User recovered!");
            }
            //newUsers.add(u);
        } else {
            User newUser = new User(object);
            userList.put(newUser.userSessionID, newUser);
            //User an die Hashmap vom NewsManager
            NewsManager.addUser(newUser.userSessionID);
            //User an die Hashmap vom CalendarManager
            CalendarManager.addUser(newUser.userSessionID);
            //User an die Hashmap vom BlackboardManager
            BlackboardManager.addUser(newUser.userSessionID);
            userSymbolList.put(newUser.tuioSymbolID, newUser);
            newUsers.add(newUser);
            System.out.println("User logged in!");
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
            u.restart();
            System.out.println("User Symbol removed!");
        }
    }

    public void logoutUser(int symbolid) {
        //User u = userSymbolList.remove(symbolid);
        logoutUsers.add(symbolid);
    }

    public void logoutUsers(float tpf) {
        logoutCounter += tpf;
        if (logoutCounter > logoutCheckTime) {
            for (Map.Entry<Integer, User> entry : userSymbolList.entrySet()) {
                if (entry.getValue() != null && !userList.containsValue(entry.getValue())) {
                    long currentTime = System.currentTimeMillis();
                    float diff = ((currentTime - entry.getValue().lastActiveTime) / 1000f);                   
                    if (diff > 5) {
                        logoutUser(entry.getValue().getTuioSymbolID());  
                    }
                   
                }
            }
            if (logoutUsers.size() > 0) {
                for (Integer id : logoutUsers) {
                    User u = userSymbolList.remove(id);
                    u.logout();
                    System.out.println("User logged out!");
                }
                logoutUsers.clear();
            }
            logoutCounter = 0;
        }
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
