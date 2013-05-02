package com.opus.svgloader;

import com.jme3.asset.TextureKey;
import java.awt.Dimension;

/**
 * A texture key that can be used to specify the output dimension of a svg image.
 * Using this key is required only if you want to scale the image specified in
 * a svg document.
 * @author pgi
 */
public class SVGTextureKey extends TextureKey {
    private final Dimension SIZE;
    private final int hash;

    /**
     * Initializes this SVGTextureKey
     * @param name the name of the svg resource to load
     * @param outputImageSize the size of the image produced by the svg loader
     */
    public SVGTextureKey(String name, Dimension outputImageSize) {
   super(name);
   this.SIZE = new Dimension(outputImageSize);
   hash = new Long((((long)name.hashCode()) << 32) + (long) SIZE.hashCode()).hashCode();
    }

    /**
     * Returns the hash code of this key, based on the name of the resource and
     * the size of the requested image
     * @return the hash code of this key.
     */
    @Override
    public int hashCode() {
   return hash;
    }

    /**
     * True if the given object is a SVGTextureKey instance denoting the same
     * image at the same size
     * @param obj the object to test for equality
     * @return true if that object equals this.
     */
    @Override
    public boolean equals(Object obj) {
   if (obj == null) {
       return false;
   }
   if (getClass() != obj.getClass()) {
       return false;
   }
   final SVGTextureKey other = (SVGTextureKey) obj;
   if (this.hash != other.hash) {
       return false;
   }
   return true;
    }

    public Dimension getRequiredSize() {
   return SIZE;
    }
}