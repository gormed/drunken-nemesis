/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import com.opus.controller.Clickable3D;
import com.opus.gui.frames.SampleFrameContent;
import com.opus.logic.Card;

/**
 *
 * @author Hans
 */
public abstract class AbstractUserFrame extends Node implements Updateable {
    
    protected VisualCard card;
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
    
    public AbstractUserFrame(VisualCard card) {
        super();
        this.card = card;
    }
    
    public abstract void createFrame();

    @Override
    public void update(float tpf) {
        menu.update(tpf);
        content.update(tpf);
        if (animate) {
            currentAnimationTime += tpf;
            animationAngle += tpf * (animationSpeed / animationTime);
            
            if (currentAnimationTime > animationTime) {
                animate = false;
                animationAngle = desiredAngle;
                currentAnimationTime = 0;
            }
            float[] angles = {0, (animationAngle) * 2f * ((float) Math.PI), 0 };
            setLocalRotation(new Quaternion(angles));
        }
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
    public VisualCard getCard(){
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
