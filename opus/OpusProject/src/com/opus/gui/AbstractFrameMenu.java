/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hans
 */
public abstract class AbstractFrameMenu extends Node implements Updateable {
    protected AbstractUserFrame frame;
    private ArrayList<AbstractFrameMenuEntry> frameMenuEntrys = new ArrayList<AbstractFrameMenuEntry>();
    private Circle background;
    private final int DIAMETER = 300;

    
    public AbstractFrameMenu(AbstractUserFrame frame) {
        super();
        this.frame = frame;
        
    }

    @Override
    public void update(float tpf) {
       for (AbstractFrameMenuEntry afme : frameMenuEntrys) {
           afme.update(tpf);
       }
    }
    
    public abstract void createMenu();
    
    
    public AbstractUserFrame getFrame() {
        return frame;
    }

    public ArrayList<AbstractFrameMenuEntry> getFrameMenuEntrys() {
        return new ArrayList<AbstractFrameMenuEntry>(frameMenuEntrys);
    }
    
    public void addMenuEntry(AbstractFrameMenuEntry entry) {
        frameMenuEntrys.add(entry);
        this.attachChild(entry);
    }
    public void removeMenuEntry(AbstractFrameMenuEntry entry) {
        this.detachChild(entry);
        frameMenuEntrys.remove(entry);
    }
    
    protected void createBackground(float angle, Color color){
         Random randomGenerator = new Random(System.currentTimeMillis());
        
        int borderAngle = ((100/this.getFrameMenuEntrys().size())+10);
        int innerAngle = 0;
        //Circle 1
        Color outerBorderColor = color;
        Color innerColor = color ;
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        background = new Circle(OpusApplication.getInstance().getAssetManager(), (DIAMETER-5), 40, outerBorderColor, borderAngle, innerColor, innerAngle);
        background.setLocalTranslation(0,-DIAMETER/3,1);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,(angle *(float) Math.PI)};
        background.setLocalRotation(new Quaternion(angles));
        frame.attachChild(background);
    }
    
    
}
