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
    private Color borderColor =new Color(227, 179, 80);
    private Color innerColor =new Color(227, 179, 80);
    private Circle circle;
    private int diameter;
    private float posFactor = 1.25f;
    
    protected BorderMenu(Color color, int diameter){
        super();
        this.diameter = diameter;
       
    }
    
     private void createBorderMenu(Color color) {        
        
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        circle = new Circle(OpusApplication.getInstance().getAssetManager(), diameter+(borderWidth), borderWidth, borderColor, borderAngle, innerColor, innerAngle);
        circle.setLocalTranslation(0,-diameter/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0, posFactor*((float) Math.PI)};
        circle.setLocalRotation(new Quaternion(angles));
    }
    
    @Override
    public void update(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}

