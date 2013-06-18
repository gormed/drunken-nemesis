/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.elements;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.opus.gui.OpusApplication;

/**
 *
 * @author Hans
 */
public class Heading extends BitmapText {

    public Heading(boolean rightToLeft, boolean arrayBased) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft, arrayBased);
    }

    public Heading( boolean rightToLeft) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft);
    }
    
    public Heading( boolean rightToLeft, ColorRGBA color) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft);
        this.setColor(color);
        }
    
    public void alignCenter() {
        setBoundRefresh();
        setAlignment(BitmapFont.Align.Center);
    }
    
}
