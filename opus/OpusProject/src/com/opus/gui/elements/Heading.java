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
public class Heading extends BitmapText {

    public Heading(boolean rightToLeft, boolean arrayBased) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("NexaBold"), rightToLeft, arrayBased);
    }

    public Heading( boolean rightToLeft) {
        super(OpusApplication.getInstance().getAssetManager().loadFont("NexaBold"), rightToLeft);
    }
    
}
