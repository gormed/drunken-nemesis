/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

/**
 *
 * @author hans
 */
public class CalendarManager {
    
    private CalendarManager() {
    }
    
    public static CalendarManager getInstance() {
        return CalendarManagerHolder.INSTANCE;
    }
    
    private static class CalendarManagerHolder {

        private static final CalendarManager INSTANCE = new CalendarManager();
    }
}
