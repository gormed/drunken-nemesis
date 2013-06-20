/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.opus.controller.Clickable3D;
import com.opus.shape.Circle;
import java.awt.Color;

/**
 *
 * @author hady
 */
public class ClickArea extends Circle implements Clickable3D {

    private AbstractFrameContent content;
    private  Geometry geom;
    public ClickArea(AbstractFrameContent content){
        super(OpusApplication.getInstance().getAssetManager(), 250, 0, new Color(255,255,255), 0,  new Color(255,255,255), 360 );
        
        this.setLocalTranslation(0f, 0f, 0.2f);
       
        this.content = content;
        this.setName("ClickArea");
    }
    
    
    @Override
    public void onRayCastClick(Vector2f mouse, CollisionResult result) {
        content.getFrame().animate = true;
        if (!content.getAccordingFrame().equals(null)) {
            content.getAccordingFrame().animate = true;
            
            content.getFrame().getCard().setFrame(content.getAccordingFrame());
           
        }
    }

    @Override
    public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
    }

    @Override
    public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
    }

   

   
    
}
