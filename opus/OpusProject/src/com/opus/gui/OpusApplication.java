/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import com.opus.gui.frames.SampleUserFrame;
import TUIO.TuioClient;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.opus.gui.frames.SampleFrameContent;
import com.opus.gui.frames.SampleFrameMenu;
import com.opus.logic.Card;
import com.opus.logic.User;
import com.opus.logic.UserManager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Hans
 */
public class OpusApplication extends SimpleApplication {

        
    private OpusApplication() {
        super();
        userManager = UserManager.getInstance();
        
    }
    
    public static OpusApplication getInstance() {
        return OpusApplicationHolder.INSTANCE;
    }
    
    private static class OpusApplicationHolder {

        private static final OpusApplication INSTANCE = new OpusApplication();
    }
    
    static TuioClient client;
    static UserManager userManager;
    HashMap<Card, VisualCard> visualCards = new HashMap<Card, VisualCard>();

    @Override
    public void stop() {
        super.stop(); 
        client.disconnect();
        client = null;
    }
    
    
    
    public void initialize(TuioClient tc) {
        client = tc;   
        client.addTuioListener(userManager.getTuioInputListener());
    }

    
    @Override
    public void simpleInitApp() {
        
        
        inputManager.setCursorVisible(true);
        setPauseOnLostFocus(false);

        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        Random randomGenerator = new Random();
        
//        int borderAngle = 360;
//        int innerAngle = 360;
//        //Circle 1
//        Color randomBorderColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
//        Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
//        Circle circle = new Circle(assetManager, 400, 10, randomBorderColor, borderAngle, randomInnerColor, innerAngle);
//        circle.setLocalTranslation(100, 100, 0);
//        guiNode.attachChild(circle);
//        // use z-axis to rotate
//        circle.rotate(90, 0,0);
           
//        //CIrcle 2
//        Color randomBorderColor2 = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
//        Color randomInnerColor2 = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));     
//        Circle circle2 = new Circle(assetManager, 200, 10, randomBorderColor2, borderAngle, randomInnerColor2, innerAngle);
//        circle2.setLocalTranslation(100, 100, 0);
//        circle.attachChild(circle2);
        
        //Using BitmapText
//        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
//        BitmapText ch = new BitmapText(guiFont, false);
//        ch.setSize(guiFont.getCharSet().getRenderedSize());
//        ch.setText("OPUS Circle Test"); // crosshairs
//        ch.setColor(new ColorRGBA(1f, 0.8f, 0.1f, 1f));
//        ch.setLocalTranslation(settings.getWidth() * 0.3f, settings.getHeight() * 0.1f, 0);
//        guiNode.attachChild(ch);
    }
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        ArrayList<User> newUsers = userManager.getNewUsers();
        if (!newUsers.isEmpty()) {
            for (User u : newUsers) {
                VisualCard c = new VisualCard(u.getCard(), assetManager);
                AbstractUserFrame frame = new SampleUserFrame(u.getCard());
                c.setFrame(frame);
                guiNode.attachChild(c);
                visualCards.put(u.getCard(), c);
            }
        }
        
        for (Map.Entry<Card, VisualCard> entry : visualCards.entrySet()) {
            entry.getValue().update(tpf);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
        
    }


}