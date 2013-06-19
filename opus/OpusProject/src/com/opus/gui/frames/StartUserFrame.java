/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.Quaternion;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.OpusApplication;
import com.opus.gui.VisualCard;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Hans
 */
public class StartUserFrame extends AbstractUserFrame {
    public static final int diameter = 300;
    private final StartFrameContent startFrameContent;
    private final StartFrameContentBoard startFrameContentBoard;
    private final StartFrameContentCalendar startFrameContentCalendar;
    private final StartFrameContentNews startFrameContentNews;
    private final StartFrameMenu startFrameMenu;
    
   
    public StartUserFrame(VisualCard card) {
        super(card);
        startFrameContent = new StartFrameContent(this);
        startFrameContentBoard = new StartFrameContentBoard(this);
        startFrameContentCalendar = new StartFrameContentCalendar(this);
        startFrameContentNews = new StartFrameContentNews(this);
        startFrameMenu = new StartFrameMenu(this);

        startFrameContentBoard.createContent();
        startFrameContentCalendar.createContent();
        startFrameContentNews.createContent();
        startFrameMenu.createMenu();
        
        
        setContent(startFrameContent);
        setMenu(startFrameMenu);

        
        
    }
    
    
    @Override
    public void createFrame() {
        content.createContent();
        createSampleBackground();
        
        this.attachChild(background);
        background.attachChild(content);
    }
    
    private void createSampleBackground() {        
        Random randomGenerator = new Random(System.currentTimeMillis());
        int borderAngle = 0;
        int innerAngle = 360;
        //Circle 1
        Color randomBorderColor = new Color(42, 101, 137);
        Color innerColor = new Color(42, 101, 137);
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
        if(card.getCard().getAngle()>=(0.8333f * 2f * ((float) Math.PI)) || card.getCard().getAngle()< (0.1666f * 2f * ((float) Math.PI))){
               if(!this.getContent().equals(startFrameContentCalendar)) {
                this.changeContent(startFrameContentCalendar);
            }
        } 
        else if(card.getCard().getAngle()>=(0.1666f * 2f * ((float) Math.PI)) && card.getCard().getAngle()< (0.5f * 2f * ((float) Math.PI))){
            if(!this.getContent().equals(startFrameContentBoard)) {
                this.changeContent(startFrameContentBoard);
                
            }
        } 
        else if(card.getCard().getAngle()>=(0.5f * 2f * ((float) Math.PI)) && card.getCard().getAngle()< (0.8333f * 2f * ((float) Math.PI))){
            if(!this.getContent().equals(startFrameContentNews)) {
                this.changeContent(startFrameContentNews);
        
            }
        }
    }

    @Override
    protected int getDiameter() {
        return diameter;
    }

    


    
}
