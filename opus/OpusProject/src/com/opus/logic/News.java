package com.opus.logic;

/**
 *
 * @author Senju
 */
public class News {
   int id = 0;
   String title = ""; 
   String link = "";
   String description = "";
   String pubDate = "";
   String guid = "";
   String qrSrc = "";
   
// TO-DO  User-Datenfeld
   
   public News(int i, String t, String l, String d, String p, String g, String q){
       
       setId(i);
       setTitle(t);
       setLink(l);
       setDescription(d);
       setPubDate(p);
       setGuid(g);
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

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }  

    public String getQrSrc() {
        return qrSrc;
    }

    public void setQrSrc(String qrSrc) {
        this.qrSrc = qrSrc;
    }
    
}
