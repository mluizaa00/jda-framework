package com.celeste.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String transformDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
        return sdf.format(date);
    }

    public static String transformHour(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
        return sdf.format(data);
    }

    public static int parseTimeAmount(String time) {
        char[] t = time.toCharArray();

        int breakPoint = 0;
        String amount = "";

        for (int i=0; i < t.length; i++) {
            switch (t[i]) {
                case 's':
                case 'm':
                case 'h':
                case 'd':
                    breakPoint = i;
                    break;
            }
        }
        for (int i = 0; i < breakPoint; i++) {
            amount += t[i];
        }
        return Integer.parseInt(amount);
    }

    public static TimeUnit parseTimeUnit(String time) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        char[] timeChar = time.toCharArray();

        for (char c : timeChar) {
            switch (c) {
                case 's':
                    timeUnit = TimeUnit.SECONDS;
                    break;
                case 'm':
                    timeUnit = TimeUnit.MINUTES;
                    break;
                case 'h':
                    timeUnit = TimeUnit.HOURS;
                    break;
                case 'd':
                    timeUnit = TimeUnit.DAYS;
                    break;
            }
        }
        return timeUnit;
    }
}
