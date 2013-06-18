/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.elements;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.opus.gui.OpusApplication;

/**
 *
 * @author Hans
 */
public class Text extends BitmapText {

    public Text(boolean rightToLeft) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft);
    }
    
    public Text(boolean rightToLeft,ColorRGBA color) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft);
        this.setColor(color);
    }
    
    public Text(boolean rightToLeft, boolean arrayBased) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/Avenir12.fnt"), rightToLeft, arrayBased);
    }
    
    public void alignCenter() {
        this.setBox(new Rectangle(0, 0, getLineWidth(), getLineHeight()));
        setAlignment(BitmapFont.Align.Center);
    }
}