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
import com.opus.gui.VisualCard.QuadrantListener;
import com.opus.gui.elements.Text;
import java.awt.Color;

/**
 *
 * @author Senju
 */
public class CalendarFrameMenu extends AbstractFrameMenu  {

    public CalendarFrameMenu(AbstractUserFrame frame) {
        super(frame);
    }

    @Override
    public void createMenu() {
        
        this.detachAllChildren();
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0f * Math.PI), "Sonntag");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.1666f * Math.PI), "Samstag");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.333f * Math.PI), "Freitag");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.5f * Math.PI), "Donnerstag");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.6666f * Math.PI), "Mittwoch");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (0.8333f * Math.PI), "Dienstag");
            }
        });
        addMenuEntry(new AbstractFrameMenuEntry(this) {

            @Override
            protected void createEntry() {
                createRotated(this, (float) (1.0f * Math.PI), "Montag");
            }
        });
       getFrameMenuEntrys().get(0).setBackground(createBackground(0f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(1).setBackground(createBackground(0.1666f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(2).setBackground(createBackground(0.333f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(3).setBackground(createBackground(0.5f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(4).setBackground(createBackground(0.666f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(5).setBackground(createBackground(0.8333f, new Color(227, 179, 80)));
       getFrameMenuEntrys().get(6).setBackground(createBackground(1.0f, new Color(227, 179, 80)));
       showMenuBackground(1);
        
      
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

   
}
