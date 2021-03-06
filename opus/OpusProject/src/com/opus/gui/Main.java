package com.opus.gui;

import TUIO.TuioClient;
import com.opus.logic.QRC;
/**
 * test
 *
 * @author normenhansen
 */
public class Main  {

    public static void main(String[] args) {
        TuioClient client = null;
        OpusApplication app = OpusApplication.getInstance();
        
        switch (args.length) {
            case 1:
                try {
                    int port = Integer.parseInt(args[0]);
                    client = new TuioClient(port);
                    System.out.println("Connecting to port " + port);
                } catch (Exception e) {
                    System.out.println("usage: java <appname> [port]");
                    System.exit(0);
                }
                break;
            case 0:
                client = new TuioClient();
                break;
            default:
                System.out.println("usage: java <appname> [port]");
                System.exit(0);
                break;
        }

        if (client != null) {
            app.initialize(client);
            client.connect();
            System.out.println("...successful connected!");
        } else {
            System.out.println("usage: java <appname> [port]");
            System.exit(0);
        }
        
        
        app.start();
        
    }
}
