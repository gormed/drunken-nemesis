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
public class NewsFrameContent extends AbstractFrameContent {

    private int userID = -1;
    private int newsID = -1;
    
    public NewsFrameContent(AbstractUserFrame parent) {
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
        Heading h1 = new Heading(false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        //get(0) bedeutet, der erste Newseintrag - Überschrift
        String heading = NewsManager.getInstance().getUserNews(userID).get(newsID).getTitle();
        
        String headingLine1 = "";
        String headingLine2 = "";
        int headingLength = heading.length();
        if(headingLength > 75){
            headingLength = 75;
        }
        if(headingLength < 30){
           int headingMinLength = headingLength;
           headingLine1 = heading.substring(0, headingMinLength);
        } else{
            headingLine1 = heading.substring(0, 30);
            headingLine2 = heading.substring(31, headingLength);
        }                     
        
        String headingComplete = headingLine1 +" -\n" + headingLine2;
        h1.setText(headingComplete + "..");
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 100, 0);
        attachChild(h1);
        //Text message = new Text(false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        //message.setBox(new Rectangle(-100, 60, 200, 200));
            //get(0) bedeutet, der erste Newseintrag - Inhalt
        //String text = NewsManager.getInstance().getUserNews(userID).get(newsID).getDescription();
        //text = text.substring(0, 150);
        //message.setText(text + "..");
            // message.setLocalTranslation(-message.getLineWidth()*0.5f, -message.getLineHeight()*message.getLineCount()*0.5f, 0);
        //attachChild(message);
        
        String inputSource = "qrcodes/" + (NewsManager.getInstance().getUserNews(userID).get(newsID).getQrSrc()) + ".png";
        Vector2f vec = new Vector2f(-65f, -75f);
        Image qrTest = new Image(inputSource, vec);
        attachChild(qrTest);
    }
    
}

