/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.VisualCard;

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
        createSampleBackground();
        
        
    }
    
     private void createSampleBackground() {        
        int borderAngle = 0;
        int innerAngle = 360;
        background = new Node();
        //Circle 1
        background.setLocalTranslation(0,-diameter/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,(float) Math.PI};
        background.setLocalRotation(new Quaternion(angles));
    }
    
    
    @Override
    public void createFrame() {
        this.detachAllChildren();
       this.attachChild(background);
        background.attachChild(content);
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
