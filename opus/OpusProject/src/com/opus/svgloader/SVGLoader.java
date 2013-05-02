/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.svgloader;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.texture.plugins.AWTLoader;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 * A texture loader for svg images.
 * @author pgi
 */
public class SVGLoader implements AssetLoader {
    private static final class BufferedImageTranscoderOutput extends TranscoderOutput {
   private BufferedImage buffer;
    }
    private static final class BufferedImageTranscoder extends ImageTranscoder {

   @Override
   public BufferedImage createImage(int w, int h) {
       return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
   }

   @Override
   public void writeImage(BufferedImage bi, TranscoderOutput to) throws TranscoderException {
       ((BufferedImageTranscoderOutput) to).buffer = bi;
   }

    }

    /**
     * Transforms a svg image in a jme texture
     * @param assetInfo the resource to load (better if it is a svg image)
     * @return a jme3 texture object
     * @throws IOException if something goes really bad.
     */
    public Object load(AssetInfo assetInfo) throws IOException {
   TranscoderInput input = new TranscoderInput(assetInfo.openStream());
   BufferedImageTranscoderOutput output = new BufferedImageTranscoderOutput();
   BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
   if(assetInfo.getKey() instanceof SVGTextureKey) {
       SVGTextureKey key = (SVGTextureKey) assetInfo.getKey();
       Dimension size = key.getRequiredSize();
       if(size != null) {
      transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(size.width));
      transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, new Float(size.height));
       }
   }
   try {
       transcoder.transcode(input, output);
       return new AWTLoader().load(output.buffer, true);
   } catch (TranscoderException ex) {
       throw new IOException(ex);
   }
    }

}
