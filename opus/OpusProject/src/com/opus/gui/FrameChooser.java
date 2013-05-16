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
    protected ArrayList<AbstractUserFrame> frames = new ArrayList<AbstractUserFrame>();
    protected AbstractUserFrame currentFrame;
    
    protected BorderMenu borderMenuBoard;
    protected BorderMenu borderMenuNews;
    protected BorderMenu borderMenuCalendar;
    private int diameter;
    
    
    public FrameChooser() {
        super();
        // TODO get concrete USerFrames
        //attachCHild Node
        this.diameter = currentFrame.getDiameter();
        createFrameChooser();
    }

    @Override
    public void update(float tpf) {
       for (AbstractUserFrame af : frames) {
           af.update(tpf);
       }
    }
    
    public void createFrameChooser(){
        //TODO Parameter anpassen
        this.borderMenuCalendar = new BorderMenu(new Color(227, 179, 80),diameter); // yellow
        this.borderMenuBoard = new BorderMenu(new Color(227, 179, 80),diameter); // yellow
        this.borderMenuNews = new BorderMenu(new Color(227, 179, 80),diameter); // yellow
    };
     
    public AbstractUserFrame getFrame() {
        return currentFrame;
    }

    public ArrayList<AbstractUserFrame> getFrames() {
        return new ArrayList<AbstractUserFrame>(frames);
    }
    
    public void addMenuEntry(AbstractUserFrame frame) {
        frames.add(frame);
        this.attachChild(frame);
    }
    public void removeMenuEntry(AbstractUserFrame frame) {
        this.detachChild(frame);
        frames.remove(frame);
    }
}