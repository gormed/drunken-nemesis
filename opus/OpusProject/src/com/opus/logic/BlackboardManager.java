/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

/**
 *
 * @author hans
 */
public class BlackboardManager {
    
    private BlackboardManager() {
    }
    
    public static BlackboardManager getInstance() {
        return BlackboardManagerHolder.INSTANCE;
    }
    
    private static class BlackboardManagerHolder {

        private static final BlackboardManager INSTANCE = new BlackboardManager();
    }
}
