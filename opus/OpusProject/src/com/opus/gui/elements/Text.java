/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.elements;

import com.jme3.font.BitmapText;
import com.opus.gui.OpusApplication;

/**
 *
 * @author Hans
 */
public class Text extends BitmapText {

    public Text(boolean rightToLeft) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/NexaLightText.fnt"), rightToLeft);
    }

    public Text(boolean rightToLeft, boolean arrayBased) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("Interface/Fonts/NexaLightText.fnt"), rightToLeft, arrayBased);
    }
    
}
