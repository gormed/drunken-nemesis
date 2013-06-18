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

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Senju
 */
public class QRC {

    // Singleton    
    public static QRC getInstance() {
        return QRC.QRCHolder.INSTANCE;
    }

    private static class QRCHolder {

        private static final QRC INSTANCE = new QRC();
    }

    // Class
    public void createQR(String url) {
        try {
            ByteArrayOutputStream out = QRCode.from(url)
                    .to(ImageType.PNG).stream();

            String name = url.replaceAll("[^a-zA-Z]", "");
            while (name.length() < 25) {
                name = name + "x";
            }
            name = name.substring(0, 25);
            name = "assets/qrcodes/" + name + ".png";
            try {
                FileOutputStream fout = new FileOutputStream(new File(
                        name));

                fout.write(out.toByteArray());

                fout.flush();
                fout.close();

            } catch (FileNotFoundException e) {
                System.out.println(e + " - File not found! - in QRC");
            } catch (IOException e) {
                System.out.println(e + " - in QRC");
            }

        } catch (Exception e) {
            System.out.println(e + " - Failed to create QR image from text due to underlying exception: URL too long.");
        }
    }
}