/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameMenu extends Node implements Updateable {
    protected AbstractUserFrame frame;
    private ArrayList<AbstractFrameMenuEntry> frameMenuEntrys = new ArrayList<AbstractFrameMenuEntry>();

    
    public AbstractFrameMenu(AbstractUserFrame frame) {
        super();
        this.frame = frame;
        
    }

    @Override
    public void update(float tpf) {
       for (AbstractFrameMenuEntry afme : frameMenuEntrys) {
           afme.update(tpf);
       }
    }
    
    public abstract void createMenu();
    
    
    public AbstractUserFrame getFrame() {
        return frame;
    }

    public ArrayList<AbstractFrameMenuEntry> getFrameMenuEntrys() {
        return new ArrayList<AbstractFrameMenuEntry>(frameMenuEntrys);
    }
    
    public void addMenuEntry(AbstractFrameMenuEntry entry) {
        frameMenuEntrys.add(entry);
        this.attachChild(entry);
    }
    public void removeMenuEntry(AbstractFrameMenuEntry entry) {
        this.detachChild(entry);
        frameMenuEntrys.remove(entry);
    }
    
    
}
