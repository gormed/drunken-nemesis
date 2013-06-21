/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.elements.Heading;
import com.opus.gui.elements.Text;

/**
 *
 * @author Hans
 */
public class StartFrameContentCalendar extends AbstractFrameContent {

    public StartFrameContentCalendar(AbstractUserFrame parent) {
        super(parent);
    }
    
    @Override
    public void update(float tpf) {
        
    }

    @Override
    public void createContent() {
        Heading h1 = new Heading(false,  new ColorRGBA(227/255f, 179/255f, 80/255f,1f));
        h1.setText("Calendar");
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 80, 0);
        attachChild(h1);
        
//        Text message = new Text(false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
//        message.setBox(new Rectangle(-100, 60, 200, 200));
//        message.setText("dies sind 140 Zeichen um die ausmaße auf dieser Seite zu testen wir dies passen sollte können wir bald ganze Tweets auf dem OPUS anzeigen..");
//       // message.setLocalTranslation(-message.getLineWidth()*0.5f, -message.getLineHeight()*message.getLineCount()*0.5f, 0);
//        attachChild(message);
    }

    @Override
    public void changeContent(int rotation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
