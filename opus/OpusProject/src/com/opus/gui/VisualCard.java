/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Transform;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.opus.controller.TuioInputListener;
import com.opus.gui.frames.SampleBoardUserFrame;
import com.opus.gui.frames.SampleCalendarUserFrame;
import com.opus.gui.frames.SampleNewsUserFrame;
import com.opus.gui.frames.StartFrameContent;
import com.opus.gui.frames.StartUserFrame;
import com.opus.logic.Card;

/**
 *
 * @author hans
 */
public class VisualCard extends Node implements Updateable {

    static final int SCREEN_WIDHT = 1024;
    static final int SCREEN_HEIGHT = 768;
    AbstractUserFrame currentFrame,startUserFrame, boardUserFrame, newsUserFrame, calendarUserFrame;
    Card card;
    Geometry cardGeom;
    private FrameChooser frameChooser;
    private boolean frameChanged;
    private StartFrameContent startFrameContent;

    public VisualCard(Card card, AssetManager assetManager) {
        this.card = card;
        initVisualCard(assetManager);
  
    }
    
    
    private void initVisualCard(AssetManager assetManager){
        cardGeom = new Geometry("Card_" + card.getOwner().getTuioSymbolID(), new Box(Vector3f.ZERO, 1, 1, 1));

        boardUserFrame = new SampleBoardUserFrame(card);
        newsUserFrame = new SampleNewsUserFrame(card);
        calendarUserFrame = new SampleCalendarUserFrame(card);
        startUserFrame = new StartUserFrame(card);
        this.setFrame(startUserFrame);
        
        startFrameContent = new StartFrameContent(this.currentFrame);
        this.setFrameContent(startFrameContent);
        
        
        //Card Geometry
        Material mat = new Material(OpusApplication.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        cardGeom.setMaterial(mat);
        cardGeom.setLocalScale(10);
        this.attachChild(cardGeom);
        
        
        frameChooser = new FrameChooser();
        this.attachChild(frameChooser);
    }

    public void setFrame(AbstractUserFrame frame) {
        if (this.currentFrame != null) {
            this.detachChild(this.currentFrame);
        }
        this.currentFrame = frame;
        this.currentFrame.createFrame();
        this.attachChildAt(this.currentFrame, 0);
    }

    @Override
    public void update(float tpf) {
        float Xpos = card.getX() * SCREEN_WIDHT;
        float Ypos = card.getY() * SCREEN_HEIGHT;
        float scale = SCREEN_HEIGHT / (float) TuioInputListener.table_size;

        Transform transFC = new Transform();

        float[] angles = { 0,0,card.getAngle() };
        transFC.setTranslation(0,-100,0);
        transFC.setRotation(new Quaternion(angles));
        //System.out.println(card.getAngle());


        
        this.setLocalTransform(rotateUI(Xpos - card.getX(), Ypos - card.getY(), scale));
        for(BorderMenu bm : frameChooser.getBorderMenus()){
            bm.setLocalTransform(transFC);
         }
        if (currentFrame != null) {
            currentFrame.update(tpf);
        }
        // System.out.println(trans.toString());
        // System.out.println("Updated " + card.getOwner().getTuioSymbolID());
    }
 

    private Transform rotateUI(float x, float y, float scale) {
//        Vector3f middle = new Vector3f(SCREEN_WIDHT*0.5f, SCREEN_HEIGHT*0.5f, 0);
        Transform trans = new Transform();
        Vector2f mid = new Vector2f(SCREEN_WIDHT * 0.5f, SCREEN_HEIGHT * 0.5f);
        Vector2f p = new Vector2f(x, y);
        Vector2f midp = p.subtract(mid);
        float[] angles = {0, 0, midp.getAngle() - (float) Math.PI / 2};
        midp.normalizeLocal();
        midp.multLocal(350);
        midp.addLocal(mid);
        trans.setTranslation(midp.x, midp.y, 0);
        trans.setRotation(new Quaternion(angles));
        trans.setScale(scale);
        return trans;
    }

    private void setFrameContent(AbstractFrameContent frameContent) {
        currentFrame.setContent(frameContent);
    }
}
