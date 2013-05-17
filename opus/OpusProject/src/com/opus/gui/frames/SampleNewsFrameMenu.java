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
import com.opus.gui.elements.Text;

/**
 *
 * @author Hans
 */
public class SampleNewsFrameMenu extends AbstractFrameMenu {

    public SampleNewsFrameMenu(AbstractUserFrame frame) {
        super(frame);
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
                createRotated(this, (float) (0.5f * Math.PI), "News");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.2f * Math.PI), "...");
            }
        });
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
        if (dist <= SampleCalendarUserFrame.diameter && dist >= 0) {
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
