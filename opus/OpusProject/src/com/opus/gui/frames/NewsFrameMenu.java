/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.opus.gui.AbstractFrameMenu;
import com.opus.gui.AbstractFrameMenuEntry;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.OpusApplication;
import com.opus.gui.elements.Text;
import com.opus.logic.Card;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Senju
 */
public class NewsFrameMenu extends AbstractFrameMenu {
    private final Card card;
    private Circle background;
    private final int DIAMETER = 300;

    public NewsFrameMenu(AbstractUserFrame frame) {
        super(frame);
        this.card = this.getFrame().getCard();

    }

    @Override
    public void createMenu() {
       
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.333f * Math.PI), "HSHL");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.666f * Math.PI), "Google");
            }
        });
       
        createBackground(0.333f, new Color(200, 200, 200));
        createBackground(0.666f, new Color(100, 100, 100));
    }
    protected void createBackground(float angle, Color color){
         Random randomGenerator = new Random(System.currentTimeMillis());
        
        int borderAngle = ((100/this.getFrameMenuEntrys().size())+10);
        int innerAngle = 0;
        //Circle 1
        Color outerBorderColor = new Color(165, 87, 75);
        Color innerColor = color;
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        background = new Circle(OpusApplication.getInstance().getAssetManager(), (DIAMETER-5), 40, outerBorderColor, borderAngle, innerColor, innerAngle);
        background.setLocalTranslation(0,-DIAMETER/3,1);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,(angle *(float) Math.PI)};
        background.setLocalRotation(new Quaternion(angles));
        frame.attachChild(background);
    }
    private BitmapText createRotated(AbstractFrameMenuEntry menuentry, float angle, String title) {
        Text entry = new Text(false);
        entry.setColor(ColorRGBA.Black);
        entry.setText(title);

        float[] angles1 = {0, 0, (float) -(Math.PI / 2)};
        entry.setLocalRotation(new Quaternion(angles1));
        entry.setLocalTranslation(new Vector3f(0, entry.getLineWidth() * 0.5f, 2));

        Vector3f rot = getAngleCoords(angle, 140);
        menuentry.setLocalTranslation(rot);

        float[] angles2 = {0, 0, (float) (angle)};
        menuentry.setLocalRotation(new Quaternion(angles2));
        menuentry.attachChild(entry);
        return entry;
    }

    public Vector3f getAngleCoords(float angle, float dist) {
        Vector3f v = new Vector3f();
        if (dist <= SampleUserFrame.diameter && dist >= 0) {
            Vector2f v2 = new Vector2f(dist, 0);
            v2.rotateAroundOrigin(angle, false);
            v.x = v2.x;
            v.y = v2.y;
        } else {
            v = null;
        }
        return v;
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        
    }
    
    
}
