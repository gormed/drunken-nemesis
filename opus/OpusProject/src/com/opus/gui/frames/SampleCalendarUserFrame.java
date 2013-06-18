/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.FrameChooserMenu;
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
public class SampleCalendarUserFrame extends AbstractUserFrame {
    public static final int diameter = 300;
   

    public SampleCalendarUserFrame(Card card) {
        super(card);
        setMenu(new SampleCalendarFrameMenu(this));
        setContent(new SampleCalendarFrameContent(this));
    }
    
    @Override
    public void createFrame() {
        menu.createMenu();
        content.createContent();
        createSampleBackground();
        
        this.attachChild(background);
        background.attachChild(content);
        background.attachChild(menu);
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
        super.update(tpf);
        
//        if(card.getAngle()>=(0.8333f * 2f * ((float) Math.PI)) || card.getAngle()< (0.1666f * 2f * ((float) Math.PI))){
//               if(!this.getContent().equals(startFrameContentCalendar)) {
//                this.changeContent(startFrameContentCalendar);
//            }
//        } 
//        else if(card.getAngle()>=(0.1666f * 2f * ((float) Math.PI)) && card.getAngle()< (0.5f * 2f * ((float) Math.PI))){
//            if(!this.getContent().equals(startFrameContentBoard)) {
//                this.changeContent(startFrameContentBoard);
//                
//            }
//        } 
//        else if(card.getAngle()>=(0.5f * 2f * ((float) Math.PI)) && card.getAngle()< (0.8333f * 2f * ((float) Math.PI))){
//            if(!this.getContent().equals(startFrameContentNews)) {
//                this.changeContent(startFrameContentNews);
//        
//            }
//        }
    }

    @Override
    protected int getDiameter() {
        return diameter;
    }
    


    
}
