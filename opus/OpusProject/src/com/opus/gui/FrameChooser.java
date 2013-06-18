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
public class FrameChooser extends Node implements Updateable {
    protected AbstractUserFrame currentFrame;
    private ArrayList<BorderMenu> borderMenus = new ArrayList<BorderMenu>();
    
    protected BorderMenu borderMenuBoard;
    protected BorderMenu borderMenuNews;
    protected BorderMenu borderMenuCalendar;
    private static final int diameter = 300;
    
    
    
    public FrameChooser() {
        super();
        // TODO get concrete USerFrames
        //attachCHild Node
        createFrameChooser();
        
    }

    @Override
    public void update(float tpf) {
        
       
    }
    
    public void createFrameChooser(){
        //TODO Parameter anpassen
        
        this.borderMenuCalendar = new BorderMenu(new Color(120, 168, 199),diameter, 0.5f, 60); // blue
        this.borderMenuBoard = new BorderMenu(new Color(227, 179, 80),diameter, 1.75f, 60); // yellow
        this.borderMenuNews = new BorderMenu(new Color(203, 75, 59),diameter, 1.25f, 60); // red
        addBorderMenu(borderMenuCalendar);
        addBorderMenu(borderMenuBoard); 
        addBorderMenu(borderMenuNews); 
    }
    
    public AbstractUserFrame getCurrentFrame() {
        return currentFrame;
    }
     
    public ArrayList<BorderMenu> getBorderMenus() {
        return new ArrayList<BorderMenu>(borderMenus);
    }
    
    public void addBorderMenu(BorderMenu entry) {
        borderMenus.add(entry);
        this.attachChild(entry);
    }
    public void removeBorderMenu(BorderMenu entry) {
        this.detachChild(entry);
        borderMenus.remove(entry);
    }
    
     
}