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
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Hans
 */
public class SampleCalendarFrameMenu extends AbstractFrameMenu {
    private Circle background;
    private final float DIAMETER = 300;

    public SampleCalendarFrameMenu(AbstractUserFrame frame) {
        super(frame);
        createSampleBackground();
    }

    @Override
    public void createMenu() {
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.8f * Math.PI), "...");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.5f * Math.PI), "Calendar");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.2f * Math.PI), "...");
            }
        });
    }

    private void createSampleBackground() {        
        int borderAngle = 30;
        int innerAngle = 0;
        //Circle 1
        Color outerBorderColor = new Color(42, 101, 137);
        Color innerColor = new Color(42, 101, 137);
        //Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        background = new Circle(OpusApplication.getInstance().getAssetManager(), DIAMETER, 10, outerBorderColor, borderAngle, innerColor, innerAngle);
        background.setLocalTranslation(0,-DIAMETER/3,0);
        
        //circle.setLocalTranslation(100, 100, 0);
        // use z-axis to rotate
        float[] angles = {0,0,(float) Math.PI};
        background.setLocalRotation(new Quaternion(angles));
    }
    
    private BitmapText createRotated(AbstractFrameMenuEntry menuentry, float angle, String title) {
        Text entry = new Text(false);
        entry.setColor(new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        entry.setText(title);

        float[] angles1 = {0, 0, (float) -(Math.PI / 2)};
        entry.setLocalRotation(new Quaternion(angles1));
        entry.setLocalTranslation(new Vector3f(0, entry.getLineWidth() * 0.5f, 0));

        Vector3f rot = getAngleCoords(angle, 140);
        menuentry.setLocalTranslation(rot);

        float[] angles2 = {0, 0, (float) (angle)};
        menuentry.setLocalRotation(new Quaternion(angles2));
        menuentry.attachChild(entry);
        return entry;
    }

    public Vector3f getAngleCoords(float angle, float dist) {
        Vector3f v = new Vector3f();
        if (dist <= SampleNewsUserFrame.diameter && dist >= 0) {
            Vector2f v2 = new Vector2f(dist, 0);
            v2.rotateAroundOrigin(angle, false);
            v.x = v2.x;
            v.y = v2.y;
        } else {
            v = null;
        }
        return v;
    }
}
