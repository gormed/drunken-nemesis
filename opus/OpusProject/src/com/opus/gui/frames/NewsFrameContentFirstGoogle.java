/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.elements.Heading;
import com.opus.gui.elements.Image;
import com.opus.gui.elements.Text;
import com.opus.logic.NewsManager;

/**
 *
 * @author Senju
 */
public class NewsFrameContentFirstGoogle extends AbstractFrameContent {

    private int userID = -1;
    private int newsID = -1;
    
    public NewsFrameContentFirstGoogle(AbstractUserFrame parent) {
        super(parent);
        userID = parent.getCard().getOwner().getUserSessionID();
        // !!!!!!!!!!! newsID:
        // NewsManager.getInstance().getHshlNewsCounter() -1 = letzte HSHL news
        // NewsManager.getInstance().getHshlNewsCounter()    = erste Google news
        // NewsManager.getInstance().getHshlNewsCounter() + NewsManager.getInstance().getGoogleNewsCounter() - 1 = letzte google news
        newsID = NewsManager.getInstance().getHshlNewsCounter();
    }
    
    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
         Heading h1 = new Heading(false,  new ColorRGBA(203/255f, 75/255f, 59/255f,1f));
        h1.setText("20 neue Google News");
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 100, 0);
        attachChild(h1);
    }
    
}

