/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

/**
 *
 * @author hans
 */
public class DashboardManager {
    
    private DashboardManager() {
    }
    
    public static DashboardManager getInstance() {
        return DashboardManagerHolder.INSTANCE;
    }
    
    private static class DashboardManagerHolder {

        private static final DashboardManager INSTANCE = new DashboardManager();
    }
}
