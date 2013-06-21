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
public class NewsUserFrameGoogle extends AbstractUserFrame implements QuadrantListener{
    public static final int diameter = 300;
      private  AbstractFrameContent newsFrameContentGoogle;
        private AbstractFrameMenu newsFrameMenuGoogle;

    public NewsUserFrameGoogle(VisualCard card) {
        super(card);
        
        newsFrameContentGoogle = new NewsFrameContentGoogle(this);   
        setContent(newsFrameContentGoogle);
        newsFrameMenuGoogle = new NewsFrameMenuGoogle(this);
        setMenu(newsFrameMenuGoogle);
        
    }
    
    @Override
    public void createFrame() {
        detachAllChildren();
        menu.createMenu();
        content.createContent();
        createSampleBackground();
        
        this.attachChild(background);
        background.attachChild(content);
        background.attachChild(menu);
        
        card.setMaxQuadrants(4);
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
    public void changeQuadrant(int currentQuad, int lastQuad) {
        //Marker wird nach rechts gedreht, außer Sonderfall: Sprung
        if (currentQuad > lastQuad) {
            //Check, ob gesprungen wird von 0 nach 3
            if (currentQuad == 3 && lastQuad == 0) {
                newsFrameContentGoogle.changeContent(-1);
            } else {
                newsFrameContentGoogle.changeContent(1);
            }
        } //Marker wird nach links gedreht, außer Sonderfall: Sprung 
        else {
            if(currentQuad < lastQuad){
                //Check, ob gesprungen wird von 3 nach 0
                if(currentQuad == 0 && lastQuad == 3){
                    newsFrameContentGoogle.changeContent(1);
                }
                else{
                    newsFrameContentGoogle.changeContent(-1);
                }
            }
        }
    }   
}
