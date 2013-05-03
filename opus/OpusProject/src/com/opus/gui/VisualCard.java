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
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.opus.controller.TuioInputListener;
import com.opus.logic.Card;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author hans
 */
public class VisualCard extends Node {
    static final int SCREEN_WIDHT = 1024;
    static final int SCREEN_HEIGHT = 768;
    
    Card card;
    Geometry cardGeom;
    
    public VisualCard(Card card, AssetManager assetManager) {
        
        cardGeom = new Geometry("Card_"+card.getOwner().getTuioSymbolID(), new Box(Vector3f.ZERO, 1, 1, 1));
        
        this.card = card;
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        cardGeom.setMaterial(mat);
        cardGeom.setLocalScale(10);
        
        Random randomGenerator = new Random(System.currentTimeMillis());
        int borderAngle = 360;
        int innerAngle = 360;
        
        int diameter = 300;
        //Circle 1
        Color randomBorderColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
        Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        Circle circle = new Circle(assetManager, diameter, 10, randomBorderColor, borderAngle, randomInnerColor, innerAngle);
        circle.setLocalTranslation(0,-diameter/3,0);
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        //circle.rotate(90, 0,0);
        
        this.attachChild(circle);
        this.attachChild(cardGeom);
    }
    
    public void update(float tpf) {
        float Xpos = card.getX() * SCREEN_WIDHT;
        float Ypos = card.getY() * SCREEN_HEIGHT;
        float scale = SCREEN_HEIGHT / (float) TuioInputListener.table_size;

        Transform trans = new Transform();
        trans.setTranslation(-card.getX(), -card.getY(),0);
        trans.setTranslation(Xpos, Ypos,0);
        
        float[] angles = { 0,0,card.getAngle() };
        trans.setRotation(new Quaternion(angles));
        trans.setScale(scale);
        
        this.setLocalTransform(trans);
        System.out.println(trans.toString());
        //System.out.println("Updated " + card.getOwner().getTuioSymbolID());
    }
}
