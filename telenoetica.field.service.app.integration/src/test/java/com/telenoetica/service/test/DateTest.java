package com.telenoetica.service.test;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateTest {

  @Test
  public void test() {

    Date startDate;
    Date endDate;
    Calendar currentDate = Calendar.getInstance(); // Get the current date
    endDate = new Date();
    endDate = DateUtils.addDays(endDate, -1);
    endDate = DateUtils.truncate(endDate, Calendar.DATE);;
    Date finalEndDate = DateUtils.addMinutes(endDate, 59);;
    finalEndDate = DateUtils.addSeconds(finalEndDate, 59);
    finalEndDate = DateUtils.addHours(finalEndDate, 23);
    currentDate.setTime(endDate);
    currentDate.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
    startDate = currentDate.getTime();

    System.err.println(startDate + " TO " + finalEndDate);
  }

}
