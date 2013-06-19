/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractUserFrame;
import com.opus.logic.CalendarManager;

/**
 *
 * @author Senju
 */
public class CalendarFrameContent extends AbstractFrameContent {

    private int userID = -1;
    
    public CalendarFrameContent(AbstractUserFrame parent) {
        super(parent);
        userID = parent.getCard().getCard().getOwner().getUserSessionID();
    }
    
    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
        //Calendar auf der Console ausgeben
        //CalendarManager.getInstance().calendarOutput(CalendarManager.getInstance().getUserCalendar(userID));
    }    
}

