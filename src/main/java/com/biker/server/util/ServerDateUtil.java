package com.biker.server.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ServerDateUtil {

  public static Date getDateStrippedOfTime(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Date strippedDate = new Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
    return strippedDate;
  }

  public static Date getDateStrippedOfTime(Timestamp time){
    return getDateStrippedOfTime(new Date(time.getTime()));
  }

  public static Date getPreviousDateWithTime(Date date){
    return getDateNumPreviousDaysWithTime(date, 1);
  }

  public static Date getPreviousDateStrippedOfTime(Date date){
    return getDateStrippedOfTime(getPreviousDateWithTime(date));
  }

  public static Date getDateNumPreviousDaysWithTime(Date date, int num_days_prior){

    Calendar beforeCal = Calendar.getInstance();
    beforeCal.setTime(date);
    beforeCal.setLenient(true);

    int diff = 0 - num_days_prior;
    beforeCal.add(Calendar.DATE, diff);

    return beforeCal.getTime();
  }

  public static Date getDateNumDaysAheadWithTime(Date date, int num_days_ahead){

    Calendar beforeCal = Calendar.getInstance();
    beforeCal.setTime(date);
    beforeCal.setLenient(true);

    beforeCal.add(Calendar.DATE, num_days_ahead);

    return beforeCal.getTime();
  }

  public static int getNumDaysBetweenWithTime(Date chk, Date main){

    long t = main.getTime() - chk.getTime();

    int d = 1000 * 60 *60 *24;


    int f = (int) t/d;

    return f;

  }

  public static int getNumDaysBetweenWithoutTime(Date chk, Date main){
    return getNumDaysBetweenWithTime(getDateStrippedOfTime(chk), getDateStrippedOfTime(main));
  }



}
