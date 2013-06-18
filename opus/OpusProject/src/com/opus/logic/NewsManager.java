package com.opus.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Senju
 */
public class NewsManager {

    // Singleton    
    public static NewsManager getInstance() {
        return NewsManager.NewsManagerHolder.INSTANCE;
    }

    private static class NewsManagerHolder {

        private static final NewsManager INSTANCE = new NewsManager();
    }
    // Class
    static ArrayList<String> titles = new ArrayList<String>(0);
    static ArrayList<String> pubDates = new ArrayList<String>(0);
    static ArrayList<String> qrcs = new ArrayList<String>(0);
    static HashMap correlation = new HashMap();
    private static int hshlNewsCounter = 0;
    private static int googleNewsCounter = 0;

    private NewsManager() {
        readAll("http://www.hshl.de/news/rss");
        hshlNewsCounter = titles.size();

        readAll("http://www.news.google.de/?output=rss");
        googleNewsCounter = titles.size() - hshlNewsCounter;      
    }

    public static void addUser(int ID) {
        ArrayList<News> news = new ArrayList<News>(0);
        createItem(news);
        correlation.put(ID, news);
    }

    public static ArrayList<News> getUserNews(int ID) {
        ArrayList<News> result = null;
        if (correlation.containsKey(ID)) {
            result = (ArrayList<News>) correlation.get(ID);
        }
        return result;
    }

    public static void createItem(ArrayList<News> ar) {
        for (int i = 0; i < titles.size(); i++) {
            try {
                ar.add(new News(i, titles.get(i), pubDates.get(i), qrcs.get(i)));
            } catch (Exception e) {
                System.out.println("NewsManager - createItem: " + e);
            }
        }
    }

    public static void readAll(String url) {
        readTitle(url);
        readPubDate(url);
        createQRCs(url);
    }

    public static void readTitle(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            if (url == "http://www.news.google.de/?output=rss") {
                String input = BR.readLine();
                int start = input.indexOf("<item>");
                input = input.substring(start);
                String[] lines = input.split("<title>");

                for (int i = 1; i < lines.length; i++) {
                    int positionEnd = lines[i].indexOf("</title>");
                    String help = lines[i].substring(0, positionEnd);
                    help = help.replace("<title>", "");
                    String path = help.replace("</title>", "");
                    titles.add(path);
                }
            } else {
            
            String oneLine;
            boolean item = false;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<item>")) {
                    item = true;
                }
                if (item == true) {
                    if (oneLine.contains("<title>")) {
                        int positionStart = oneLine.indexOf("<title>");
                        int positionEnd = oneLine.indexOf("</title>");
                        String help = oneLine.substring(positionStart, positionEnd);
                        help = help.replace("<title>", "");
                        help = help.replace("</title>", "");
                        titles.add(help);
                    }
                }
            }}
            BR.close();
            //Probleme mit dem URL-Pfad
        } catch (MalformedURLException e) {
            System.out.println("URL inkorrekt!");

        } catch (IOException ee) {
            System.out.println("Probleme im BR");
        }
    }

    public static void readPubDate(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            
            if (url == "http://www.news.google.de/?output=rss") {
                String input = BR.readLine();
                int start = input.indexOf("<item>");
                input = input.substring(start);
                String[] lines = input.split("<pubDate>");

                for (int i = 1; i < lines.length; i++) {
                    int positionEnd = lines[i].indexOf("</pubDate>");
                    String help = lines[i].substring(0, positionEnd);
                    help = help.replace("<pubDate>", "");
                    String path = help.replace("</pubDate>", "");
                    pubDates.add(path);
                }
            } else {
            String oneLine;
            boolean item = false;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<item>")) {
                    item = true;
                }
                if (item == true) {
                    if (oneLine.contains("<pubDate>")) {
                        int positionStart = oneLine.indexOf("<pubDate>");
                        int positionEnd = oneLine.indexOf("</pubDate>");
                        String help = oneLine.substring(positionStart, positionEnd);
                        help = help.replace("<pubDate>", "");
                        help = help.replace("</pubDate>", "");
                        pubDates.add(help);
                    }
                }
            }}
            BR.close();
            //Probleme mit dem URL-Pfad
        } catch (MalformedURLException e) {
            System.out.println("URL inkorrekt!");

        } catch (IOException ee) {
            System.out.println("Probleme im BR");
        }
    }

    public static void createQRCs(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));

            //SONDERBEHANDLUNG GOOGLE NEWS - ONE LINE!
            if (url == "http://www.news.google.de/?output=rss") {
                String input = BR.readLine();
                int start = input.indexOf("<item>");
                input = input.substring(start);
                String[] lines = input.split("<link>");

                for (int i = 1; i < lines.length; i++) {
                    int positionEnd = lines[i].indexOf("</link>");
                    String help = lines[i].substring(0, positionEnd);
                    help = help.replace("<link>", "");
                    int ndPart = help.indexOf("url=");
                    help = help.substring(ndPart);
                    help = help.replace("url=", "");
                    String path = help.replace("</link>", "");
                    QRC.getInstance().createQR(path);
                    String source = path.replaceAll("[^a-zA-Z]", "");
                    while (source.length() < 25) {
                        source = source + "x";
                    }
                    source = source.substring(0, 25);
                    qrcs.add(source);
                }
            } else {
                String oneLine;
                boolean item = false;
                //Solange Zeilen im SourceCode existieren
                while ((oneLine = BR.readLine()) != null) {
                    if (oneLine.contains("<item>")) {
                        item = true;
                    }
                    if (item == true) {
                        if (oneLine.contains("<link>")) {
                            int positionStart = oneLine.indexOf("<link>");
                            int positionEnd = oneLine.indexOf("</link>");
                            String help = oneLine.substring(positionStart, positionEnd);
                            help = help.replace("<link>", "");
                            String path = help.replace("</link>", "");
                            QRC.getInstance().createQR(path);
                            String source = path.replaceAll("[^a-zA-Z]", "");
                            while (source.length() < 25) {
                                source = source + "x";
                            }
                            source = source.substring(0, 25);
                            qrcs.add(source);
                        }
                    }
                }
            }




            BR.close();
            //Probleme mit dem URL-Pfad
        } catch (MalformedURLException e) {
            System.out.println("URL inkorrekt!");

        } catch (IOException ee) {
            System.out.println("Probleme im BR");
        }


    }

    public static int getHshlNewsCounter() {
        return hshlNewsCounter;
    }

    public static int getGoogleNewsCounter() {
        return googleNewsCounter;
    }
}
