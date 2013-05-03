/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Transform;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.opus.controller.TuioInputListener;
import com.opus.logic.Card;
import org.lwjgl.Sys;

/**
 *
 * @author hans
 */
public class VisualCard extends Geometry {
    static final int SCREEN_WIDHT = 1024;
    static final int SCREEN_HEIGHT = 768;
    
    Card card;

    public VisualCard(Card card, AssetManager assetManager) {
        
        super("Card_"+card.getOwner().getTuioSymbolID(), new Box(Vector3f.ZERO, 1, 1, 1));
        
        this.card = card;
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        this.setMaterial(mat);
        
    }
    
    public void update(float tpf) {
        float Xpos = card.getX() * SCREEN_WIDHT;
        float Ypos = card.getY() * SCREEN_HEIGHT;
        float scale = SCREEN_HEIGHT / (float) TuioInputListener.table_size;

        Transform trans = new Transform();
        trans.setTranslation(-card.getX(), -card.getY(),0);
        trans.setTranslation(Xpos, Ypos,0);
        trans.setRotation(new Quaternion(0,0,1,card.getAngle()));
        trans.setScale(20* scale);
        
        this.setLocalTransform(trans);
        //System.out.println(trans.toString());
        //System.out.println("Updated " + card.getOwner().getTuioSymbolID());
    }
}
