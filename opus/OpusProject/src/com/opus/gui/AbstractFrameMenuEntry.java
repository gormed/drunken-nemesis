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
public abstract class AbstractFrameMenuEntry extends Node implements Updateable {
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
}
