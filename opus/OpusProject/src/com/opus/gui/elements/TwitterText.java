/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.elements;

import com.jme3.collision.CollisionResult;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.opus.controller.Clickable3D;
import com.opus.gui.OpusApplication;

/**
 *
 * @author hans
 */
public class TwitterText extends Text implements Clickable3D {

    private QRPopup qrCodeNode;
    
    public TwitterText(String text, boolean rightToLeft, ColorRGBA color) {
        super(rightToLeft, color);
        setText(text);
        //qrCodeNode = new QRPopup("hhh", Vector2f.ZERO);
    }

    public TwitterText(String text, boolean rightToLeft) {
        super(rightToLeft);
        setText(text);
    }

    public TwitterText(String text, boolean rightToLeft, boolean arrayBased) {
        super(rightToLeft, arrayBased);
        setText(text);
    }

    @Override
    public void onRayCastClick(Vector2f mouse, CollisionResult result) {
        //this.attachChild(qrCodeNode);
        //qrCodeNode.setLocalTranslation(x, y, z);
    }

    @Override
    public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    private class QRPopup extends Image implements Clickable3D {

        public QRPopup(String path, Vector2f pos) {
            super(path, pos);
        }        
        
        @Override
        public void onRayCastClick(Vector2f mouse, CollisionResult result) {
            getParent().detachChild(this);
        }

        @Override
        public void onRayCastMouseOver(Vector2f mouse, CollisionResult result) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void onRayCastMouseLeft(Vector2f mouse, CollisionResult result) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
