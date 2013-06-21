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
public class NewsFrameContentFirstHSHL extends AbstractFrameContent {

    private int userID = -1;
    private int newsID = -1;
    private AbstractUserFrame according;
    public NewsFrameContentFirstHSHL(AbstractUserFrame parent) {
        super(parent);
        userID = parent.getCard().getCard().getOwner().getUserSessionID();
        
        according = new NewsUserFrameHSHL(parent.getCard());
        // !!!!!!!!!!! newsID:
        // NewsManager.getInstance().getHshlNewsCounter() -1 = letzte HSHL news
        // NewsManager.getInstance().getHshlNewsCounter()    = erste Google news
        // NewsManager.getInstance().getHshlNewsCounter() + NewsManager.getInstance().getGoogleNewsCounter() - 1 = letzte google news
        newsID = NewsManager.getInstance().getHshlNewsCounter();
        setAccordingFrame(according);
        setClickable(true);
        createClickArea();
    }
    
    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
         Heading h1 = new Heading(false,  new ColorRGBA(203/255f, 75/255f, 59/255f,1f));
         h1.setText("100 neue HSHL News");
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 0, 1.0f);
        attachChild(h1);
    }

    @Override
    public void changeContent(int rotation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

