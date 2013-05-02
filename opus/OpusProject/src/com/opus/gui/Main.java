package com.opus.gui;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.opus.svgloader.SVGLoader;
import com.opus.svgloader.SVGTextureKey;
import java.awt.Dimension;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    Node n, n2;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
         flyCam.setMoveSpeed(10);
         assetManager.registerLoader(SVGLoader.class.getName(), "svg");

        Quad q = new Quad(2, 2);
        Geometry g = new Geometry("Quad", q);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        Texture texBackground = assetManager.loadTexture(new SVGTextureKey("assets/img/background.svg", new Dimension(800, 800)));
        mat.setTexture("ColorMap", texBackground);
        g.setMaterial(mat);

        Quad q2 = new Quad(1, 1);
        Geometry g3 = new Geometry("Quad2", q2);
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Yellow);
        Texture texUser = assetManager.loadTexture(new SVGTextureKey("assets/img/user.svg", new Dimension(400, 400)));
        mat2.setTexture("ColorMap", texUser);
        g3.setMaterial(mat2);
        g3.setLocalTranslation(.5f, .5f, .01f);

        Box b = new Box(new Vector3f(0, 0, 3), .25f, .5f, .25f);
        Geometry g2 = new Geometry("Box", b);
        g2.setMaterial(mat);

        Node bb = new Node("billboard");

        BillboardControl control=new BillboardControl();
        
        bb.addControl(control);
        bb.attachChild(g);
        bb.attachChild(g3);       
        

        n=new Node("parent");
        n.attachChild(g2);
        n.attachChild(bb);
        rootNode.attachChild(n);

        n2=new Node("parentParent");
        n2.setLocalTranslation(Vector3f.UNIT_X.mult(5));
        n2.attachChild(n);

        rootNode.attachChild(n2);

//        Box bg = new Box(Vector3f.ZERO, 1, 1, 1);
//        Box user = new Box(new Vector3f(0, 1, 1), 1,1, 1);
//        Geometry bgGeom = new Geometry("Box", bg);
//        
//
//        Geometry userGeom = new Geometry("Box", user);
//        
//        assetManager.registerLoader(SVGLoader.class.getName(), "svg");
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        Texture texBackground = assetManager.loadTexture(new SVGTextureKey("assets/img/background.svg", new Dimension(800, 800)));
//        mat.setTexture("ColorMap", texBackground);
//        //mat.setColor("Color", ColorRGBA.Blue);
//        bgGeom.setMaterial(mat);
//        
//        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        Texture texUser = assetManager.loadTexture(new SVGTextureKey("assets/img/user.svg", new Dimension(400, 400)));
//        mat2.setTexture("ColorMap", texUser);
//        //mat.setColor("Color", ColorRGBA.Blue);
//
//        userGeom.setQueueBucket(Bucket.Transparent); 
//        userGeom.setMaterial(mat2);
//
//        rootNode.attachChild(bgGeom);
//        rootNode.attachChild(userGeom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
