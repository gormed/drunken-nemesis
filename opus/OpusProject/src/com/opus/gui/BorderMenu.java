/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author hady
 */
public class BorderMenu extends Node implements Updateable {

    private int borderAngle  = 22;
    private int innerAngle = 0;
    private float borderWidth= 30f;
    private Color borderColor;
    private Color innerColor =new Color(255, 255, 255);
    private Circle circle;
    private int diameter;
    
    protected BorderMenu(Color color, int diameter, float posFactor){
        super();
        this.diameter = diameter;
        borderColor = color;
        createBorderMenu(borderColor,posFactor);
    }
    
     private void createBorderMenu(Color color, float posFactor) {        
        
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        circle = new Circle(OpusApplication.getInstance().getAssetManager(), diameter+(borderWidth), borderWidth, borderColor, borderAngle, innerColor, innerAngle);
        //circle.setLocalTranslation(0,-diameter/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0, posFactor*((float) Math.PI)};
        circle.setLocalRotation(new Quaternion(angles));
        this.attachChild(circle);
    }
    
    @Override
    public void update(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}

