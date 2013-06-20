/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Hans
 */
public class FrameChooserMenu extends Node implements Updateable {

    private ArrayList<BorderMenu> borderMenus = new ArrayList<BorderMenu>();
    private static final int diameter = 300;
    protected AbstractUserFrame currentFrame;
    BorderMenu borderMenuBoard;
    BorderMenu borderMenuNews;
    BorderMenu borderMenuCalendar;
    float[] angleStages = {0.5f, 0.8333f , 0.1666f};
    float[] menuStages = {0, 0.666f, 0.333f};
    VisualCard visualCard;
    int currentActive = 0;
    // Animation
    boolean animate;
    float animationSpeed = 1f;
    float animationTime = 0.4f;
    float currentAnimationTime;
    float animationAngle = 0.5f;
    float desiredAngle = 1.5f;
    boolean restart = false;
    private Quaternion menuRotation;
    
    private boolean startPosition = true;
    private float interpolation;

    public FrameChooserMenu(VisualCard card) {
        super();
        this.visualCard = card;
        this.restart = false;
        // TODO get concrete USerFrames
        //attachCHild Node
        createFrameChooser();

    }
    
    

    @Override
    public void update(float tpf) {
        if (animate) {
            currentAnimationTime += tpf;
            animationAngle += tpf * (animationSpeed / animationTime);
            
            //System.out.println(animationAngle);
            if (currentAnimationTime > animationTime) {
                animate = false;
                startPosition = false;
                restart = false;
                animationAngle = desiredAngle;
                currentAnimationTime = 0;
            }
            float[] angles = {0, 0, (animationAngle) * 2f * ((float) Math.PI)};
            menuRotation.fromAngles(angles);
            setLocalRotation(menuRotation);
            if(startPosition){
                interpolation = currentAnimationTime/animationTime;
                ArrayList<BorderMenu> borderMenus = getBorderMenus();
                 for(BorderMenu bm :borderMenus ){
                     bm.setLocalTranslation(0, interpolation * 90 + 80, 0.5f);
                 }
                 setLocalTranslation(0, interpolation * (-100), 0);
            }           
            if(restart){
                interpolation = (1- (currentAnimationTime/animationTime));
                ArrayList<BorderMenu> borderMenus = getBorderMenus();
                 for(BorderMenu bm :borderMenus ){
                     bm.setLocalTranslation(0, interpolation * 90 + 80, 0.5f);
                 }
                 setLocalTranslation(0, interpolation * (-100), 0);
            }
              
        }
       

    }

    private void createFrameChooser() {
       //TODO Parameter anpassen
        this.borderMenuCalendar = new BorderMenu(this, visualCard.calendarUserFrame, 0, new Color(227, 179, 80), 0); // yellow
        this.borderMenuNews = new BorderMenu(this, visualCard.newsUserFrame, 1, new Color(203, 75, 59), 0); // red
        this.borderMenuBoard = new BorderMenu(this, visualCard.boardUserFrame, 2, new Color(120, 168, 199 ), 0); // blue
        addBorderMenu(borderMenuCalendar, angleStages[0]);
        addBorderMenu(borderMenuNews, angleStages[1]);
        addBorderMenu(borderMenuBoard, angleStages[2]);
        setLocalTranslation(0, 0, 0);
        float[] angles = {0, 0, 0};
        menuRotation = new Quaternion(angles);
        setLocalRotation(menuRotation);
    }

    public VisualCard getVisualCard() {
        return visualCard;
    }
    
    public AbstractUserFrame getCurrentFrame() {
        return currentFrame;
    }

    public ArrayList<BorderMenu> getBorderMenus() {
        return new ArrayList<BorderMenu>(borderMenus);
    }

    public void addBorderMenu(BorderMenu entry, float posFactor) {
        borderMenus.add(entry);

        float[] angles = {0, 0, posFactor * 2f * ((float) Math.PI)};
//        setLocalRotation(new Quaternion(angles));

        Node n = new Node();
        n.setLocalRotation(new Quaternion(angles));
        n.attachChild(entry);
        this.attachChild(n);
    }
//    public void removeBorderMenu(BorderMenu entry) {
//        this.detachChild(entry);
//        borderMenus.remove(entry);
//    }
}


