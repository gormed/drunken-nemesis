/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.Quaternion;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.OpusApplication;
import com.opus.logic.Card;
import com.opus.logic.User;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Hans
 */
public class SampleUserFrame extends AbstractUserFrame {

    public SampleUserFrame(Card card) {
        super(card);
    }
    
    @Override
    public void createFrame() {
        Random randomGenerator = new Random(System.currentTimeMillis());
        int borderAngle = 360;
        int innerAngle = 360;
        
        int diameter = 300;
        //Circle 1
        Color randomBorderColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
        Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        Circle circle = new Circle(OpusApplication.getInstance().getAssetManager(), diameter, 10, randomBorderColor, borderAngle, randomInnerColor, innerAngle);
        circle.setLocalTranslation(0,-diameter/3,0);
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,0};
        circle.setLocalRotation(new Quaternion(angles));
        
        this.attachChild(circle);
    }
    
}
