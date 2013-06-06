/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;
import com.opus.controller.Clickable3D;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameMenuEntry extends Node implements Updateable, Clickable3D {
    private AbstractFrameMenu menu;

    public AbstractFrameMenuEntry(AbstractFrameMenu menu) {
        super();
        this.menu = menu;
        create();
    }
    
    private void create() {
        createEntry();
    }
    
    protected abstract void createEntry();

    @Override
    public void update(float tpf) {
        
    }
    
    public AbstractFrameMenu getMenu() {
        return menu;
    }

    @Override
    public void onRayCastClick(Vector2f mouse, CollisionResult result) {
        System.err.println("Override me! Says: " + name + " onRayCastClick");
    }

    @Override
    public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
        System.err.println("Override me! Says: " + name + " onRayCastMouseLeft");
    }

    @Override
    public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
        System.err.println("Override me! Says: " + name + " onRayCastMouseOver");
    }
    
    
}
