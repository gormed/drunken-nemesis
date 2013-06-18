/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
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
        // Animation
    protected boolean animate;
    protected float animationSpeed = 1f;
    protected float animationTime = 0.4f;
    protected float currentAnimationTime;
    protected float animationAngle;
    protected float desiredAngle;
    protected Quaternion rotation;
    
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
    public Card getCard(){
        return this.card;
    }  
    
    protected abstract int getDiameter();
    
    public void changeContent(AbstractFrameContent c){
            if (this.content != null) {
            this.getBackground().detachChild(this.content);
        }
        this.content = c;
        this.content.createContent();
        this.getBackground().attachChild(this.content);
    }
}
