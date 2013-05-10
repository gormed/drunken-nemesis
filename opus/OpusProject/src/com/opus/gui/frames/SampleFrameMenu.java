/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.opus.gui.AbstractFrameMenu;
import com.opus.gui.AbstractFrameMenuEntry;
import com.opus.gui.AbstractUserFrame;

/**
 *
 * @author Hans
 */
public class SampleFrameMenu extends AbstractFrameMenu {

    public SampleFrameMenu(AbstractUserFrame frame) {
        super(frame);
        frameMenuEntrys.add(new AbstractFrameMenuEntry(frame) {});
    }
    
}
