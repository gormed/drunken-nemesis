/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

/**
 *
 * @author hans
 */
public class CalendarManager {

    private CalendarManager() {
        try {
            initCalenderVariations();
        } catch (Exception e) {
            System.out.println("CalendarManager(): " + e);
        }
    }

    public static CalendarManager getInstance() {
        return CalendarManagerHolder.INSTANCE;
    }
    
    private static class CalendarManagerHolder {

        private static final CalendarManager INSTANCE = new CalendarManager();
    }
    /////////
    //CLASS//
    /////////
    
    //Datenfelder    
    static HashMap correlation = new HashMap();
    static ArrayList<Calendar> calendarVariations = new ArrayList<Calendar>(0);
    
    //Methoden
    public static void addUser(int ID) {
        try{
        Calendar calendar = getCalender();
        correlation.put(ID, calendar); 
        } catch (Exception e){
            System.out.println("CalenderManager - addUser: " + e);
        }
    }

    public static Calendar getUserCalendar(int ID) {
        Calendar result = null;
        if (correlation.containsKey(ID)) {
            result = (Calendar) correlation.get(ID);
        }
        return result;
    }
    
    

    private void initCalenderVariations() throws FileNotFoundException, IOException, ParserException {
        /*URI uri = new URI("https://www.google.com/calendar/ical/kbdmt5sgd7s3gb8ire77jbn19g@group.calendar.google.com/public/basic.ics");
        File file = new File(uri);
        FileInputStream fin = new FileInputStream(file);*/
        Calendar calendar;
        FileInputStream fin;
        CalendarBuilder builder;
        
        fin = new FileInputStream("assets/Ical/basic.ics");
        builder = new CalendarBuilder();
        calendar = builder.build(fin);
        calendarVariations.add(calendar);
        
        fin = new FileInputStream("assets/Ical/basic2.ics");
        builder = new CalendarBuilder();
        calendar = builder.build(fin);
        calendarVariations.add(calendar);
    }


    private static Calendar getCalender() {
        Random rnd = new Random();
        Calendar calendar = calendarVariations.get(rnd.nextInt(calendarVariations.size() + 1));        
        return calendar;
    }

    public static void calendarOutput(Calendar calendar) {
        
        //Iteriert die einzelnen Event-Komponenten
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next();
            System.out.println("Component [" + component.getName() + "]");
            //Iteriert die einzelnen Termine
            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            }
        }

    }
}
