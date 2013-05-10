/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.scene.Node;
import com.opus.logic.Card;

/**
 *
 * @author Hans
 */
public abstract class AbstractUserFrame extends Node {
    
    Card card;
    AbstractFrameContent content;
    
    public AbstractUserFrame(Card card) {
        this.card = card;
    }
    
    public abstract void createFrame();
    
}
