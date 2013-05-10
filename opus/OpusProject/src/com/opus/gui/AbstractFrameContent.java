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
public abstract class AbstractFrameContent extends Node {
    private AbstractUserFrame parent;

    public AbstractFrameContent(AbstractUserFrame parent) {
        this.parent = parent;
    }
    
}
