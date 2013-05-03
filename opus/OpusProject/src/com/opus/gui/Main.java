package com.opus.gui;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.opus.shape.Circle;
import com.opus.svgloader.SVGLoader;
import com.opus.svgloader.SVGTextureKey;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.sound.SoundSystem;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Random;


/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

        /** The app states. */
    private HashMap<String, AbstractAppState> appStates =
            new HashMap<String, AbstractAppState>();
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(ColorRGBA.Gray);
        Random randomGenerator = new Random();
        
        int borderAngle = 360;
        int innerAngle = 360;
        //Circle 1
        Color randomBorderColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
        Color randomInnerColor = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));       
        Circle circle = new Circle(assetManager, 400, 10, randomBorderColor, borderAngle, randomInnerColor, innerAngle);
        circle.setLocalTranslation(100, 100, 0);
        guiNode.attachChild(circle);
        // use z-axis to rotate
        circle.rotate(90, 0,0);
           
        //CIrcle 2
        Color randomBorderColor2 = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));
        Color randomInnerColor2 = new Color(randomGenerator.nextInt(255), randomGenerator.nextInt(255), randomGenerator.nextInt(255));     
        Circle circle2 = new Circle(assetManager, 200, 10, randomBorderColor2, borderAngle, randomInnerColor2, innerAngle);
        circle2.setLocalTranslation(100, 100, 0);
        circle.attachChild(circle2);
        
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
        //circle.generateImage();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }


}
