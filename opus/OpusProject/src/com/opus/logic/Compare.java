/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opus.logic;

import java.util.Comparator;

/**
 *
 * @author Senju
 */
public class Compare implements Comparator<Properti>{

    @Override
    public int compare(Properti t, Properti t1) {
        if (t.getDTSTART() == null && t1.getDTSTART() == null) {
      return 0;
    }
    if (t.getDTSTART() == null) {
      return 1;
    }
    if (t1.getDTSTART() == null) {
      return -1;
    }
    return t.getDTSTART().compareTo(t1.getDTSTART());
  }
}
