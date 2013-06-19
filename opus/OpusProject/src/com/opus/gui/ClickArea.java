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
        super(OpusApplication.getInstance().getAssetManager(), 200, 50, new Color(222,222,222), 50,  new Color(222,222,222), 360 );
        
        this.setLocalTranslation(0f, 0f, 0.2f);
       
        this.content = content;
        this.setName("ClickArea");
    }
    
    
    @Override
    public void onRayCastClick(Vector2f mouse, CollisionResult result) {
        content.getFrame().animate = true;
        if (!content.getAccordingContent().equals(null)) {
            //accordingFrame.animate = true;
            
            content.getFrame().changeContent(content.getAccordingContent());
           
        }
    }

    @Override
    public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
    }

    @Override
    public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
    }

   

   
    
}
