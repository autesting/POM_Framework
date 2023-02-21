package com.appinventive.qa.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.appinventive.qa.log4j.LogManager;
import com.appinventive.qa.log4j.Logger;

public class DateUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    public DateUtils() {
    }

    public static String getCurrentDateString() {
        return getCurrentDate("yyyy/MM/dd HH:mm:ss:SSS");
    }

    public static String getCurrentDate(String patternFormat) {
        return (new SimpleDateFormat(patternFormat)).format(new Date());
    }

    public static String getDateString(Date date) {
        return (new SimpleDateFormat("yyMMdd_HHmmss")).format(date);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date fromLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String getOffsetDate(String patternFormat, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, date);
        return (new SimpleDateFormat(patternFormat)).format(calendar.getTime());
    }

    public static Long timeDiffMs(Date startDate, Date endDate) {
        return Math.abs(endDate.getTime() - startDate.getTime());
    }

    public static String timeDiff(Date startDate, Date endDate) {
        long differenceInMilliSeconds = Math.abs(endDate.getTime() - startDate.getTime());
        long differenceInHours = differenceInMilliSeconds / 3600000L % 24L;
        long differenceInMinutes = differenceInMilliSeconds / 60000L % 60L;
        long differenceInSeconds = differenceInMilliSeconds / 1000L % 60L;
        if (differenceInHours > 0L) {
            return differenceInHours + " hours " + differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
        } else if (differenceInMinutes > 0L) {
            return differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
        } else if (differenceInSeconds > 0L) {
            return differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
        } else {
            return differenceInMilliSeconds > 0L ? differenceInMilliSeconds + " MilliSeconds. " : "";
        }
    }

    public static String timeDiff(String startDate, String endDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss:SSS");
            Date date1 = null;
            date1 = simpleDateFormat.parse(startDate);
            Date date2 = simpleDateFormat.parse(endDate);
            long differenceInMilliSeconds = Math.abs(date2.getTime() - date1.getTime());
            long differenceInHours = differenceInMilliSeconds / 3600000L % 24L;
            long differenceInMinutes = differenceInMilliSeconds / 60000L % 60L;
            long differenceInSeconds = differenceInMilliSeconds / 1000L % 60L;
            if (differenceInHours > 0L) {
                return differenceInHours + " hours " + differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
            } else if (differenceInMinutes > 0L) {
                return differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
            } else if (differenceInSeconds > 0L) {
                return differenceInMinutes + " minutes " + differenceInSeconds + " Seconds. " + differenceInMilliSeconds + " MilliSeconds. ";
            } else {
                return differenceInMilliSeconds > 0L ? differenceInMilliSeconds + " MilliSeconds. " : "";
            }
        } catch (ParseException var13) {
            LOGGER.error("Parsing exception of time difference", var13);
            return "Parsing exception of time difference";
        }
    }
}

