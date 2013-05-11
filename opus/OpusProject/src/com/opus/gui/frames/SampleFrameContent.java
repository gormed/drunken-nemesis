/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.Rectangle;
import com.jme3.math.Quaternion;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.elements.Heading;
import com.opus.gui.elements.Text;

/**
 *
 * @author Hans
 */
public class SampleFrameContent extends AbstractFrameContent {

    public SampleFrameContent(AbstractUserFrame parent) {
        super(parent);
    }
    
    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
        Heading h1 = new Heading(false);
        h1.setText("My Heading");
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 100, 0);
        attachChild(h1);
        Text message = new Text(false);
        message.setBox(new Rectangle(-100, 60, 200, 200));
        message.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr"
                + ", sed diam nonumy eirmod tempor invidunt ut labore et dolore ");
       // message.setLocalTranslation(-message.getLineWidth()*0.5f, -message.getLineHeight()*message.getLineCount()*0.5f, 0);
        attachChild(message);
    }
    
}
