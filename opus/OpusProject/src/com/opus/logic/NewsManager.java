package com.opus.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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

    static String htmlCode = "";
    static ArrayList<String> titles = new ArrayList<String>(0);
    static ArrayList<String> links = new ArrayList<String>(0);
    static ArrayList<String> descriptions = new ArrayList<String>(0);
    static ArrayList<String> pubDates = new ArrayList<String>(0);
    static ArrayList<String> guides = new ArrayList<String>(0);
    static ArrayList<String> qrcs = new ArrayList<String>(0);
    
    static HashMap correlation = new HashMap();

    private NewsManager() {
        readAll();
        /*createItem();
        createInput();
        htmlToFile();
        htmlInWindow2();*/
    }
    
    public static void addUser(int ID){
        ArrayList<News> news = new ArrayList<News>(0);
        createItem(news);
        correlation.put(ID, news);
    }
    
    public static ArrayList<News> getUserNews(int ID){
        ArrayList<News> result = null;                
        if (correlation.containsKey(ID)){
            result = (ArrayList<News>) correlation.get(ID);
        }        
        return result;
    }

    public static void readAll() {
        readTitle("http://www.hshl.de/news/rss");
        readLink("http://www.hshl.de/news/rss");
        readDescription("http://www.hshl.de/news/rss");
        readPubDate("http://www.hshl.de/news/rss");
        readGuid("http://www.hshl.de/news/rss");
        createQRCs("http://www.hshl.de/news/rss");
    }

    public static void createItem(ArrayList<News> ar) {
        for (int i = 0; i < titles.size(); i++) {
            try {
                ar.add(new News(i, titles.get(i), links.get(i), descriptions.get(i), pubDates.get(i), guides.get(i), qrcs.get(i)));
            } catch (Exception e) {
                System.out.println();
            }
        }
    }

    /*public static void createInput() {
        String result = "";
        for (int i = 0; i < news.size(); i++) {
            result = result + "TITLE: " + "\t" + "\t" + news.get(i).getTitle() + "\n";
            result = result + "LINK: " + "\t" + "\t" + news.get(i).getLink() + "\n";
            result = result + "DESCRIPTION: " + "\t" + news.get(i).getDescription() + "\n";
            result = result + "PUBLICATION DATE: " + "\t" + news.get(i).getPubDate() + "\n";
            result = result + "GUIDE: " + "\t" + "\t" + news.get(i).getGuid() + "\n" + "\n" + "\n" + "\n";
        }
        htmlCode = result;
    }*/

    public static void htmlToFile() {
        // File anlegen
        File file = new File("rss.html");
        try {

            Writer writer = new FileWriter(file);

            // Text schreiben
            writer.write(htmlCode);

            // Stream schließen
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void htmlInWindow2() {
        JFrame window = new JFrame();
        JTextPane pane = new JTextPane();
        pane.setText(htmlCode);
        JScrollPane jsp = new JScrollPane(pane);
        window.add(jsp);
        window.setExtendedState(window.MAXIMIZED_BOTH);
        window.setVisible(true);
    }

    public static void readTitle(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<title>")) {
                    int positionStart = oneLine.indexOf("<title>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<title>", "");
                    int positionEnd = help.indexOf("</title>");
                    help = help.replace("</title>", "");
                    titles.add(help);
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

    public static void readLink(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<link>")) {
                    int positionStart = oneLine.indexOf("<link>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<link>", "");
                    int positionEnd = help.indexOf("</link>");
                    help = help.replace("</link>", "");
                    links.add(help);
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

    public static void readDescription(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<description>")) {
                    int positionStart = oneLine.indexOf("<description>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<description>", "");
                    int positionEnd = help.indexOf("</description>");
                    help = help.replace("</description>", "");
                    help = help.replace("<![CDATA[<p>", "");
                    help = help.replace("</p>", "");
                    help = help.replace("<a href=", "");
                    help = help.replace("</a>", "");
                    help = help.replace(">", " ");
                    descriptions.add(help);
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

    public static void readPubDate(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<pubDate>")) {
                    int positionStart = oneLine.indexOf("<pubDate>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<pubDate>", "");
                    int positionEnd = help.indexOf("</pubDate>");
                    help = help.replace("</pubDate>", "");
                    pubDates.add(help);
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

    public static void readGuid(String url) {
        try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<guid>")) {
                    int positionStart = oneLine.indexOf("<guid>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<guid>", "");
                    int positionEnd = help.indexOf("</guid>");
                    help = help.replace("</guid>", "");
                    guides.add(help);
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
    
    public static void createQRCs(String url){
       try {
            URL rssUrl = new URL(url);
            BufferedReader BR = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String result = "";
            String oneLine;
            //Solange Zeilen im SourceCode existieren
            while ((oneLine = BR.readLine()) != null) {
                if (oneLine.contains("<link>")) {
                    int positionStart = oneLine.indexOf("<link>");
                    String help = oneLine.substring(positionStart);
                    help = help.replace("<link>", "");
                    int positionEnd = help.indexOf("</link>");
                    String path = help.replace("</link>", "");
                    QRC.getInstance().createQR(path);
                    String source = path.replaceAll("[^a-zA-Z]", "");
                    qrcs.add(source);
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
}
