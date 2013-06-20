/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

/**
 *
 * @author Senju
 */
public class Properti{
    //Datenfelder
    String DTSTART;
    String DTEND;
    String SUMMARY;
    String LOCATION;
    
    
public Properti(String dtstart, String dtend, String summary, String location){
    this.DTSTART = dtstart;
    this.DTEND = dtend;
    this.SUMMARY = summary;
    this.LOCATION = location;
}

    public String getDTSTART() {
        return DTSTART;
    }

    public String getDTEND() {
        return DTEND;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public String getLOCATION() {
        return LOCATION;
    }
}

