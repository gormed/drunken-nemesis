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
import com.opus.logic.Card;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author hans
 */
public class VisualCard extends Node implements Updateable {

    static final int SCREEN_WIDHT = 1024;
    static final int SCREEN_HEIGHT = 768;
    AbstractUserFrame frame;
    Card card;
    Geometry cardGeom;
     private FrameChooser frameChooser;

    public VisualCard(Card card, AssetManager assetManager) {

        cardGeom = new Geometry("Card_" + card.getOwner().getTuioSymbolID(), new Box(Vector3f.ZERO, 1, 1, 1));

        this.card = card;

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        cardGeom.setMaterial(mat);
        cardGeom.setLocalScale(10);

        this.attachChild(cardGeom);
        frameChooser = new FrameChooser();
        this.attachChild(frameChooser);
    }

    public void setFrame(AbstractUserFrame frame) {
        if (this.frame != null) {
            this.detachChild(this.frame);
        }
        this.frame = frame;
        this.frame.createFrame();
        this.attachChildAt(this.frame, 0);
    }

    @Override
    public void update(float tpf) {
        float Xpos = card.getX() * SCREEN_WIDHT;
        float Ypos = card.getY() * SCREEN_HEIGHT;
        float scale = SCREEN_HEIGHT / (float) TuioInputListener.table_size;

        Transform trans = new Transform();
        Transform transFC = new Transform();
        trans.setTranslation(Xpos - card.getX(), Ypos - card.getY(), 0);

        float[] angles = { 0,0,card.getAngle() };
        transFC.setTranslation(0,-100,0);
        transFC.setRotation(new Quaternion(angles));
        
        trans.setRotation(rotateUI(trans.getTranslation()));

        trans.setScale(scale);

        this.setLocalTransform(trans);
        for(BorderMenu bm : frameChooser.getBorderMenus()){
            bm.setLocalTransform(transFC);
         }
        if (frame != null) {
            frame.update(tpf);
        }
        // System.out.println(trans.toString());
        // System.out.println("Updated " + card.getOwner().getTuioSymbolID());
    }

    private Quaternion rotateUI(Vector3f pos) {
//        Vector3f middle = new Vector3f(SCREEN_WIDHT*0.5f, SCREEN_HEIGHT*0.5f, 0);
        Vector2f mid = new Vector2f(SCREEN_WIDHT * 0.5f, SCREEN_HEIGHT * 0.5f);
        Vector2f p = new Vector2f(pos.x, pos.y);
        Vector2f midp = p.subtract(mid);
        float[] angles = {0, 0, midp.getAngle() - (float) Math.PI / 2};
        return new Quaternion(angles);
    }
}
