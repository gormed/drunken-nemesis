/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import java.util.ArrayList;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameMenu {
    protected AbstractUserFrame frame;
    protected ArrayList<AbstractFrameMenuEntry> frameMenuEntrys = new ArrayList<AbstractFrameMenuEntry>();
    
    public AbstractFrameMenu(AbstractUserFrame frame) {
        this.frame = frame;
    }
    
}
