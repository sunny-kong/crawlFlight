package com.sunnykong.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-7-12.
 */
public class DateUtils {
    public static List<Date> getTimeRange(Date startTime, Date endTime) {
        List<Date> timeRanges = new ArrayList();
        for (; startTime.before(endTime) || startTime.equals(endTime); ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            timeRanges.add(cal.getTime());
            cal.add(Calendar.DAY_OF_YEAR, 1);
            startTime.setTime(cal.getTimeInMillis()); int totalNum=timeRanges.size();
        }
        return timeRanges;
    }
    public static String getFirstDayOfPreviousMonth() {
        String strFirstDay = "";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONDAY, -1);  // 减一个月
        strFirstDay = sDateFormat.format(calendar.getTime());
        return strFirstDay;
    }
    public static String getPreviousMonth() {
        String strFirstDay = getFirstDayOfPreviousMonth();
        String strEndDay= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return strFirstDay+" to "+strEndDay;
    }

}
