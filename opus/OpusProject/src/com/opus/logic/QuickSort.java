/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Senju
 */
public class QuickSort {

    // Singleton    
    public static QuickSort getInstance() {
        return QuickSort.QRCHolder.INSTANCE;
    }

    private static class QRCHolder {

        private static final QuickSort INSTANCE = new QuickSort();
    }

    // Class
    public ArrayList<Integer> sortArray(ArrayList<Integer> aL, int start, int end) {
        int links = start;
        int rechts = end;
        int pivot = aL.get((start + end) / 2);
        do {
            while (aL.get(links) < pivot) {
                links++;
            }
            while (pivot < aL.get(rechts)) {
                rechts--;
            }
            if (links <= rechts) {
                int help = aL.get(links);
                aL.set(links ,aL.get(rechts));
                aL.set(rechts, help);
                links++;
                rechts--;
            }
        } while (links <= rechts);
        if (start < end) {
            aL = sortArray(aL, start, rechts);
        }
        if (links < end) {
            aL = sortArray(aL, links, end);
        }
        return aL;
    }
}