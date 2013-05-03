package com.opus.gui;

import TUIO.TuioClient;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.opus.logic.Card;
import com.opus.logic.User;
import com.opus.logic.UserManager;
import com.opus.shape.Circle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;


/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    static UserManager userManager;
    Hashtable<Card, VisualCard> visualCards = new Hashtable<Card, VisualCard>();

    public static void main(String[] args) {
        TuioClient client = null;
        userManager = UserManager.getInstance();
        
        switch (args.length) {
            case 1:
                try {
                    int port = Integer.parseInt(args[0]);
                    client = new TuioClient(port);
                    System.out.println("Connecting to port " + port);
                } catch (Exception e) {
                    System.out.println("usage: java <appname> [port]");
                    System.exit(0);
                }
                break;
            case 0:
                client = new TuioClient();
                break;
            default:
                System.out.println("usage: java <appname> [port]");
                System.exit(0);
                break;
        }

        if (client != null) {
            client.addTuioListener(userManager.getTuioInputListener());
            client.connect();
            System.out.println("...successful connected!");
        } else {
            System.out.println("usage: java <appname> [port]");
            System.exit(0);
        }
        
        Main app = new Main();
        app.start();

    }

    @Override
    public void simpleInitApp() {

        inputManager.setCursorVisible(true);
        setPauseOnLostFocus(false);

        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(ColorRGBA.Gray);
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
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize());
        ch.setText("OPUS Circle Test"); // crosshairs
        ch.setColor(new ColorRGBA(1f, 0.8f, 0.1f, 1f));
        ch.setLocalTranslation(settings.getWidth() * 0.3f, settings.getHeight() * 0.1f, 0);
        guiNode.attachChild(ch);
    }
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        ArrayList<User> newUsers = userManager.getNewUsers();
        if (!newUsers.isEmpty()) {
            for (User u : newUsers) {
                VisualCard c = new VisualCard(u.getCard(), assetManager);
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
