package com.opus.gui;

import TUIO.TuioClient;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Box;
import com.opus.logic.Card;
import com.opus.logic.User;
import com.opus.logic.UserManager;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

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
        
//        Box b = new Box(Vector3f.ZERO, 1, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(mat);
//
//        rootNode.attachChild(geom);
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
