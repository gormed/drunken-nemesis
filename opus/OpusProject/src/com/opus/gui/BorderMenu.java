/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.opus.controller.Clickable3D;
import com.opus.logic.UserManager;
import com.opus.shape.Circle;
import java.awt.Color;

/**
 *
 * @author hady
 */
public class BorderMenu extends Circle implements Clickable3D {

    public static final float DIAMETER = 20f;
    FrameChooserMenu frameChooserMenu;
    AbstractUserFrame accordingFrame;
    private int menuId;

    protected BorderMenu(FrameChooserMenu card, AbstractUserFrame accordingFrame, int menuId, Color borderColor, int borderAngle) {
        super(OpusApplication.getInstance().getAssetManager(), (DIAMETER + 10), DIAMETER, new Color(255, 255, 255), borderAngle, borderColor, 360);
        this.frameChooserMenu = card;
        this.accordingFrame = accordingFrame;
        this.menuId = menuId;
        createBorderMenu();
    }

    private void createBorderMenu() {

        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        //circle = new Circle(OpusApplication.getInstance().getAssetManager(), diameter + (borderWidth), borderWidth, borderColor, borderAngle, innerColor, innerAngle);
        //circle.setLocalTranslation(0,-diameter/3,0);

        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate

        setLocalTranslation(0, 100, 0.5f);
    }

    @Override
    public void onRayCastClick(Vector2f mouse, CollisionResult result) {
        frameChooserMenu.animate = true;      
        if (!frameChooserMenu.visualCard.frame.equals(accordingFrame)) {
            accordingFrame.animate = true;
            frameChooserMenu.visualCard.setFrame(accordingFrame);
            frameChooserMenu.currentActive = menuId;
            frameChooserMenu.desiredAngle = frameChooserMenu.menuStages[menuId];
            frameChooserMenu.animationSpeed = (frameChooserMenu.desiredAngle - frameChooserMenu.animationAngle);
            
        }
    }

    @Override
    public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
    }

    @Override
    public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
    }
    
    
}
