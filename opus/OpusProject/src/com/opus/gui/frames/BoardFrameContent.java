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
import com.opus.gui.elements.TwitterText;
import com.opus.logic.BlackboardManager;
import com.opus.logic.BlackboardManager.BlackboardTweet;
import com.opus.logic.NewsManager;
import java.util.ArrayList;

/**
 *
 * @author Senju
 */
public class BoardFrameContent extends AbstractFrameContent {

    private int userID = -1;
    private int tweetID = -1;
    private int initValue;
    private ArrayList<BlackboardTweet> blackboardTweets;
    
    public BoardFrameContent(AbstractUserFrame parent) {
        super(parent);
        userID = parent.getCard().getCard().getOwner().getUserSessionID();
        tweetID = 0;
        initValue = tweetID;
    }
    
    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
        blackboardTweets = BlackboardManager.getInstance().getUserTweets(userID);
        System.out.println("tweetID: " + tweetID);
        Heading h1 = new Heading(false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        
        String heading = blackboardTweets.get(tweetID).username +" schreibt:";
        
        h1.setText(heading);
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 100, 0);
        attachChild(h1);
        System.out.println(blackboardTweets.size());
        TwitterText text = new TwitterText(blackboardTweets.get(tweetID).tweet, false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        text.setBox(new Rectangle(-100, 60, 200, 200));    
            // message.setLocalTranslation(-message.getLineWidth()*0.5f, -message.getLineHeight()*message.getLineCount()*0.5f, 0);
        attachChild(text);
        
    }
    

    public void changeContent(int rotation) {
            destroyContent();
            switch(rotation){
                case 1: 
                    if(tweetID == (blackboardTweets.size()-1)){
                        tweetID = initValue;
                    } else{
                        tweetID++;
                    }
                    break;
                case -1:
                    if (tweetID == initValue){
                        tweetID = blackboardTweets.size()-1;
                    } else{
                        tweetID--;
                    }
                    break;
                default:
                    System.out.print("BoardFrameContent - changeContent: DEFAULT CASE" + "\n");
                    break;
            }
            createContent();
    }     
}

