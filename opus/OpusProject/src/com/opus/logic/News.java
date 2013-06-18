package com.opus.logic;

/**
 *
 * @author Senju
 */
public class News {
   int id = 0;
   String title = "";
   String pubDate = "";
   String qrSrc = "";
   
// TO-DO  User-Datenfeld
   
   public News(int i, String t, String p, String q){
       
       setId(i);
       setTitle(t);
       setPubDate(p);
       setQrSrc(q);
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
   
    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    } 

    public String getQrSrc() {
        return qrSrc;
    }

    public void setQrSrc(String qrSrc) {
        this.qrSrc = qrSrc;
    }
    
}
