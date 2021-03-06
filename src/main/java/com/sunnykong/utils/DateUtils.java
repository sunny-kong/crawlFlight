package com.sunnykong.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xj on 15-7-12.
 */
public class DateUtils {
    public static List<Timestamp> getTimeRange(Timestamp startTime, Timestamp endTime) {
        List<Timestamp> timeRanges = new ArrayList();
        for (; startTime.before(endTime) || startTime.equals(endTime); ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            timeRanges.add(new Timestamp(cal.getTimeInMillis()));
            cal.add(Calendar.HOUR_OF_DAY, 1);
            startTime.setTime(cal.getTimeInMillis()); int totalNum=timeRanges.size();
        }
        return timeRanges;
    }
    public static String getFirstDayOfPreviousMonth() {
        String strFirstDay = "";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -7);  // 减一个月
//        calendar.add(Calendar.MONDAY, -1);  // 减一个月
        strFirstDay = sDateFormat.format(calendar.getTime());
        return strFirstDay;
    }
    public static String getPreviousMonth() {
        String strFirstDay = getFirstDayOfPreviousMonth();
        String strEndDay= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return strFirstDay+"~"+strEndDay;
    }

}
