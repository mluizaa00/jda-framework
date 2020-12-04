package com.luizaprestes.framework.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static int parseTimeAmount(String time) {
        final char[] t = time.toCharArray();
        final StringBuilder amount = new StringBuilder();

        int breakPoint = 0;

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
            amount.append(t[i]);
        }
        return Integer.parseInt(amount.toString());
    }

    public static TimeUnit parseTimeUnit(String time) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        final char[] timeChar = time.toCharArray();

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
