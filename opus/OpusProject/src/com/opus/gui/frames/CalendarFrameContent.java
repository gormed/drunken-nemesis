/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.gui.frames;

import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.opus.gui.AbstractFrameContent;
import com.opus.gui.AbstractUserFrame;
import com.opus.gui.elements.Heading;
import com.opus.gui.elements.Text;
import com.opus.logic.CalendarManager;
import com.opus.logic.Compare;
import com.opus.logic.QuickSort;
import java.util.ArrayList;
import java.util.Iterator;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import com.opus.logic.Properti;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Senju
 */
public class CalendarFrameContent extends AbstractFrameContent {

    private int userID = -1;
    private int dayID = 0;
    private int dayComponentNumber = 0;
    
    private ArrayList<String> propertiesDTSTART = new ArrayList<String>(0);
    private ArrayList<String> propertiesEND = new ArrayList<String>(0);
    private ArrayList<String> propertiesSUMMARY = new ArrayList<String>(0);
    private ArrayList<String> propertiesLOCATION = new ArrayList<String>(0);
    /*private ArrayList<Integer> propertiesDTSTARTint = new ArrayList<Integer>(0);*/
    private ArrayList<Properti> properties = new ArrayList<Properti>(0);

    public CalendarFrameContent(AbstractUserFrame parent) {
        super(parent);
        userID = parent.getCard().getOwner().getUserSessionID();
    }

    @Override
    public void update(float tpf) {
    }

    @Override
    public void createContent() {
        //Calendar auf der Console ausgeben
        //CalendarManager.getInstance().calendarOutput(CalendarManager.getInstance().getUserCalendar(userID));
        Calendar calendar = CalendarManager.getInstance().getUserCalendar(userID);

        initContent(calendar);
        /*initDTSTARTint();*/
        initProperties();
        sortProperties();
        /*testOutput();*/
        //QuickSort.getInstance().sortArray(propertiesDTSTART, 0, propertiesDTSTART.size()-1);
        
        //Initialisieren wie viele Termine es an diesem Tag gibt
        String day = properties.get(dayID).getDTSTART();
        day = day.substring(0, 8);
        for(Properti prop : properties){
          String date = prop.getDTSTART();
          date = date.substring(0,8);
            if(day.equals(date)){
              dayComponentNumber++; 
          }             
        }
        
        //Die Termine in einen String verknüpfen
        String input = "";
        for(int i = 0; i < dayComponentNumber; i++){
          String start = properties.get(dayID + i).getDTSTART();
          start = start.substring(9,11) + ":" + start.substring(11,13);
          String end = properties.get(dayID + i).getDTEND();
          end = end.substring(9,11) + ":" + end.substring(11,13);;
          input = input + properties.get(dayID + i).getSUMMARY() + ":  " + start + " - " + end + " - " + properties.get(dayID + i).getLOCATION() + "\n";  
        }
        
        Heading h1 = new Heading(false,  new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        h1.setText(day);
        h1.setLocalTranslation(-h1.getLineWidth()*0.5f, 100, 0);
        attachChild(h1);
        Text message = new Text(false, new ColorRGBA(42f/255f, 101f/255f, 137f/255f,1f));
        message.setBox(new Rectangle(-100, 60, 200, 200));
        System.out.println(input);
        message.setText(input);
       // message.setLocalTranslation(-message.getLineWidth()*0.5f, -message.getLineHeight()*message.getLineCount()*0.5f, 0);
        attachChild(message);
        
                
        
        dayComponentNumber = 0;
        input = "";
    }

    public void initContent(Calendar calendar) {
        //Iteriert die einzelnen Termine
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next();
            //Iteriert durch die Informationen eines Termines
            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                if (property.getName() == "DTSTART") {
                    propertiesDTSTART.add(property.getValue());
                    //System.out.println("add-DTSTART: " + property.getName() + " " + property.getValue());
                } else if (property.getName() == "DTEND") {
                    propertiesEND.add(property.getValue());
                    //System.out.println("add-DTEND: " + property.getName() + " " + property.getValue());
                } else if (property.getName() == "SUMMARY") {
                    propertiesSUMMARY.add(property.getValue());
                    //System.out.println("add-SUMMARY: " + property.getName() + " " + property.getValue());
                } else if (property.getName() == "LOCATION") {
                    propertiesLOCATION.add(property.getValue());
                    //System.out.println("add-LOCATION: " + property.getName() + " " + property.getValue());
                } else {
                    System.out.println("CalendarFrameContent - init Content: Properti " + property.getName() + " " + property.getValue());
                }
            }
        }
    }
    /*
     private int stringToInt(String input) {
     int result = Integer.parseInt(input.replaceAll("[a-zA-Z]", ""));
     return result;
     }

     private void initDTSTARTint() {
     for (int i = 0; i < propertiesDTSTART.size(); i++){
     int help = stringToInt(propertiesDTSTART.get(i));
     propertiesDTSTARTint.set(i , help);
     }
     }*/

    private void initProperties() {
        for (int i = 0; i < propertiesDTSTART.size(); i++) {
            String propStart = propertiesDTSTART.get(i);
            String propEnd = propertiesEND.get(i);
            String propDes = propertiesSUMMARY.get(i);
            String propLoc = propertiesLOCATION.get(i);
            Properti property = new Properti(propStart, propEnd, propDes, propLoc);
            properties.add(property);
        }
    }

    private void sortProperties() {
        Comparator<Properti> comp = new Compare();
        Collections.sort(properties, comp);
    }

    private void testOutput() {
        for(Properti pro : properties){
            System.out.println(pro.getDTSTART());
        }
    }
}
