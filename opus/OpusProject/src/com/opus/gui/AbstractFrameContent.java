/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameContent extends Node implements Updateable {
    private AbstractUserFrame frame;
    private AbstractUserFrame accordingFrame;
    private ClickArea clickArea;
    private boolean clickable = false;
    public AbstractFrameContent(AbstractUserFrame frame) {
        super();
        this.frame = frame;
    }
    
    public boolean isClickable(){
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    
    
    public AbstractUserFrame getFrame() {
        return frame;
    }
    
    public abstract void createContent();

    public void destroyContent(){
        this.detachAllChildren();
    }
    
    public AbstractUserFrame getAccordingFrame() {
        return accordingFrame;
    }

    public void setAccordingFrame(AbstractUserFrame according) {
        this.accordingFrame = according;
    }

    public void createClickArea() {
        this.clickArea = new ClickArea(this);
        this.attachChild(clickArea);
    }
    
    public abstract void changeContent(int rotation); 
}
