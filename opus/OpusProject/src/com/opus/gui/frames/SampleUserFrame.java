/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.OpusApplication;
import com.opus.gui.elements.Heading;
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
    public static final int diameter = 300;

    public SampleUserFrame(Card card) {
        super(card);
        setMenu(new SampleFrameMenu(this));
        setContent(new SampleFrameContent(this));
    }
    
    @Override
    public void createFrame() {
        menu.createMenu();
        content.createContent();
        createSampleBackground();
        createBorderMenu();
        
        this.attachChild(background);
        this.attachChild(borderMenu);
        background.attachChild(content);
        background.attachChild(menu);
    }
    
    private void createBorderMenu() {        
        Random randomGenerator = new Random(System.currentTimeMillis());
        int borderAngle = 22;
        int innerAngle = 0;
        //Circle 1
        Color randomBorderColor =new Color(227, 179, 80);
        Color innerColor = new Color(227, 179, 80);
        float borderWidth= 30f;
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        borderMenu = new Circle(OpusApplication.getInstance().getAssetManager(), diameter+(borderWidth), borderWidth, randomBorderColor, borderAngle, innerColor, innerAngle);
        borderMenu.setLocalTranslation(0,-diameter/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0, 1.25f*((float) Math.PI)};
        borderMenu.setLocalRotation(new Quaternion(angles));
    }
    
    private void createSampleBackground() {        
        Random randomGenerator = new Random(System.currentTimeMillis());
        int borderAngle = 0;
        int innerAngle = 360;
        //Circle 1
        Color randomBorderColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
        Color innerColor = new Color(255, 255, 255);
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        background = new Circle(OpusApplication.getInstance().getAssetManager(), diameter, 10, randomBorderColor, borderAngle, innerColor, innerAngle);
        background.setLocalTranslation(0,-diameter/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,(float) Math.PI};
        background.setLocalRotation(new Quaternion(angles));
    }

    @Override
    public void update(float tpf) {
    }

    
}
