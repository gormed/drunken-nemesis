/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.scene.Node;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameContent extends Node implements Updateable {
    private AbstractUserFrame frame;

    public AbstractFrameContent(AbstractUserFrame frame) {
        super();
        this.frame = frame;
    }

    public AbstractUserFrame getFrame() {
        return frame;
    }
    
    public abstract void createContent();
}
