/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.elements;

import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector2f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.opus.gui.OpusApplication;

/**
 *
 * @author hans
 */
public class Image extends Node {

    Material material;
    
    public Image(String path, Vector2f pos) {
	material = new Material(OpusApplication.getInstance().getAssetManager(),
                "Common/MatDefs/Misc/Unshaded.j3md");
	Texture t = OpusApplication.getInstance().getAssetManager().
                loadTexture(path);

        material.setTexture("ColorMap", t);
        //material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        Quad q = new Quad(t.getImage().getWidth(), t.getImage().getHeight());
	Geometry geometry = new Geometry("noname", q);
	
	geometry.setMaterial(material);
//        geometry.setCullHint(CullHint.Never);
//        geometry.setQueueBucket(RenderQueue.Bucket.Translucent);
	
	this.attachChild(geometry);
        this.setLocalTranslation(pos.x, pos.y, 0);
    }
    
}
