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
import com.opus.gui.frames.StartUserFrame;
import com.opus.logic.Card;
import java.util.ArrayList;

/**
 *
 * @author hans
 */
public class VisualCard extends Node implements Updateable {

    static final int SCREEN_WIDHT = 1024;
    static final int SCREEN_HEIGHT = 768;
    private FrameChooserMenu frameChooser;
    private boolean frameChanged;
    private QuadrantControl quadrantControl;
    AbstractUserFrame frame, startUserFrame, boardUserFrame, newsUserFrame, calendarUserFrame;
    Card card;
    Geometry cardGeom;

    public VisualCard(Card card, AssetManager assetManager) {

        cardGeom = new Geometry("Card_" + card.getOwner().getTuioSymbolID(), new Box(Vector3f.ZERO, 1, 1, 1));

        this.card = card;

        boardUserFrame = new SampleBoardUserFrame(card);
        newsUserFrame = new SampleNewsUserFrame(card);
        calendarUserFrame = new SampleCalendarUserFrame(card);
        startUserFrame = new StartUserFrame(card);
        this.setFrame(startUserFrame);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        cardGeom.setMaterial(mat);
        cardGeom.setLocalScale(10);

        this.attachChild(cardGeom);
        frameChooser = new FrameChooserMenu(this);
        this.attachChild(frameChooser);
        quadrantControl = new QuadrantControl();
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
        if (!card.getOwner().isLoggedIn()) {
            logout();
            return;
        }
        float Xpos = card.getX() * SCREEN_WIDHT;
        float Ypos = card.getY() * SCREEN_HEIGHT;
        float scale = SCREEN_HEIGHT / (float) TuioInputListener.table_size;

        this.setLocalTransform(rotateUI(Xpos - card.getX(), Ypos - card.getY(), scale));
        frameChooser.update(tpf);
        if (frame != null) {
            if (frame.equals(startUserFrame)) {
                float[] angles = {0f, 0f, card.getAngle()};
                frameChooser.setLocalRotation(new Quaternion(angles));
            }

            frame.update(tpf);
        }
        quadrantControl.update(tpf);
    }

    private Transform rotateUI(float x, float y, float scale) {
//        Vector3f middle = new Vector3f(SCREEN_WIDHT*0.5f, SCREEN_HEIGHT*0.5f, 0);
        Transform trans = new Transform();
        Vector2f mid = new Vector2f(SCREEN_WIDHT * 0.5f, SCREEN_HEIGHT * 0.5f);
        Vector2f p = new Vector2f(x, y);
        Vector2f midp = p.subtract(mid);
        float[] angles = {0, 0, midp.getAngle() - (float) Math.PI / 2};
        midp.normalizeLocal();
        midp.multLocal(300);
        midp.addLocal(mid);
        trans.setTranslation(midp.x, midp.y, 0);
        trans.setRotation(new Quaternion(angles));
        trans.setScale(scale);
        return trans;
    }

    public Card getCard() {
        return card;
    }

    public FrameChooserMenu getFrameChooser() {
        return frameChooser;
    }

    public AbstractUserFrame getFrame() {
        return frame;
    }

    public void logout() {
        if (parent != null) {
            parent.detachChild(this);
        }
    }

    public void addQuadrantListener(QuadrantListener listener) {
        quadrantControl.quadrantListeners.clear();
        quadrantControl.quadrantListeners.add(listener);
    }

    public interface QuadrantListener {

        public void changeQuadrant(int quad);
    }

    public class QuadrantControl {

        int maxQuadrants = 0;
        int currentQuadrant = 0;
        ArrayList<QuadrantListener> quadrantListeners = new ArrayList<QuadrantListener>();

        public QuadrantControl() {
            //setMaxQuadrants(4);
        }

        public void setMaxQuadrants(int maxQuadrants) {
            this.maxQuadrants = maxQuadrants;
        }

        public void update(float tpf) {
            if (maxQuadrants <= 0) {
                return;
            }
            float angle = card.getAngle();
            int deg = ((int) Math.toDegrees(angle)) % 180;
            int seg = 180 / maxQuadrants;
            for (int i = 1; i < maxQuadrants + 1; i++) {
                //System.out.println("Quad: " + (i * seg) + " i: " + i);
                if (deg / ((i * seg)) == 0 && currentQuadrant != (i - 1)) {
                    currentQuadrant = i - 1;
                    for (QuadrantListener l : quadrantListeners) {
                        l.changeQuadrant(currentQuadrant);
                    }
                    break;
                }
            }
        }
    }
}
