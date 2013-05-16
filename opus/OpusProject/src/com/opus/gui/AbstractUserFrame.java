/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.scene.Node;
import com.opus.gui.frames.SampleFrameContent;
import com.opus.logic.Card;

/**
 *
 * @author Hans
 */
public abstract class AbstractUserFrame extends Node implements Updateable {
    
    protected Card card;
    protected AbstractFrameContent content;
    protected AbstractFrameMenu menu;
    protected Node background;
    protected Node borderMenu;
    
    public AbstractUserFrame(Card card) {
        super();
        this.card = card;
    }
    
    public abstract void createFrame();

    @Override
    public void update(float tpf) {
        menu.update(tpf);
        content.update(tpf);
    }

    public Node getBackground() {
        return background;
    }

    public AbstractFrameMenu getMenu() {
        return menu;
    }

    public AbstractFrameContent getContent() {
        return content;
    }

    protected void setMenu(AbstractFrameMenu menu) {
        this.menu = menu;
    }

    protected void setContent(AbstractFrameContent content) {
        this.content = content;
    }
    
    
}
