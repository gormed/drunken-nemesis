/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractFrameMenu;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.FrameChooserMenu;
import com.opus.gui.OpusApplication;
import com.opus.gui.VisualCard;
import com.opus.gui.VisualCard.QuadrantListener;
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
public class NewsUserFrameFirst extends AbstractUserFrame implements QuadrantListener{
    public static final int diameter = 300;
    private  AbstractFrameContent newsFrameContentFirstHSHL;
    private  AbstractFrameContent newsFrameContentFirstGoogle;
    private AbstractFrameMenu newsFrameMenuFirst;

    public NewsUserFrameFirst(VisualCard card) {
        super(card);
        newsFrameContentFirstHSHL = new NewsFrameContentFirstHSHL(this);
        newsFrameContentFirstGoogle = new NewsFrameContentFirstGoogle(this);
        newsFrameContentFirstHSHL.createContent();
        newsFrameContentFirstGoogle.createContent();       
        setContent(newsFrameContentFirstHSHL);
        
        newsFrameMenuFirst = new NewsFrameMenuFirst(this);
        setMenu(newsFrameMenuFirst);
        
    }
    
    @Override
    public void createFrame() {
        menu.createMenu();
        createSampleBackground();
        
        this.attachChild(background);
        background.attachChild(content);
        background.attachChild(menu);
        
        card.setMaxQuadrants(2);
        card.addQuadrantListener(this);  
    }
    
   
    
    private void createSampleBackground() {        
        int borderAngle = 0;
        int innerAngle = 360;
        //Circle 1
        Color randomBorderColor = new Color(255, 255, 255);
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
    }

    @Override
    protected int getDiameter() {
        return diameter;
    }

    @Override
    public void changeQuadrant(int quad) {
        
       switch(quad){
           case 0:
              if(!this.getContent().equals(newsFrameContentFirstHSHL)) {
                this.changeContent(newsFrameContentFirstHSHL);
                //this.menu.setEntryBackground(quad, new Color(222,222,222));
                
            }
               break;
           case 1: 
              if(!this.getContent().equals(newsFrameContentFirstGoogle)) {
                this.changeContent(newsFrameContentFirstGoogle);
                //this.menu.setEntryBackground(quad, new Color(222,222,222));
            }
               break;
           default:
               break;
       }
    }
    


    
}