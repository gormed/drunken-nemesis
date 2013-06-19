/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui;

import TUIO.TuioClient;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.opus.controller.ScreenRayCast3D;
import com.opus.logic.Card;
import com.opus.logic.NewsManager;
import com.opus.logic.User;
import com.opus.logic.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
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
        newsManager = NewsManager.getInstance();
    }
    
    public static OpusApplication getInstance() {
        return OpusApplicationHolder.INSTANCE;
    }
    
    private static class OpusApplicationHolder {

        private static final OpusApplication INSTANCE = new OpusApplication();
    }
    
    static TuioClient client;
    static UserManager userManager;
    static NewsManager newsManager;
    static ScreenRayCast3D rayCast3D;
    HashMap<Card, VisualCard> visualCards = new HashMap<Card, VisualCard>();
    static Node cardNode = new Node("Cards");

    @Override
    public void stop() {
        super.stop(); 
        client.disconnect();
        client = null;
    }
    
    
    
    public void initialize(TuioClient tc) {
        client = tc;   
        client.addTuioListener(userManager.getTuioInputListener());
        client.addTuioListener(rayCast3D = ScreenRayCast3D.getInstance());
    }

    
    @Override
    public void simpleInitApp() {
        
        
        inputManager.setCursorVisible(true);
        setPauseOnLostFocus(false);

        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        Random randomGenerator = new Random();
	
	guiNode.attachChild(cardNode);
        
	rayCast3D.initialize(getCamera(), cardNode);
        
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
        
        userManager.logoutUsers(tpf);
	
	rayCast3D.update(tpf);
	
        if (!newUsers.isEmpty()) {
            for (User u : newUsers) {
                VisualCard c = new VisualCard(u.getCard(), assetManager);
                
                cardNode.attachChild(c);
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