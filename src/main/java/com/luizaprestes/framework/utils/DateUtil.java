package com.luizaprestes.framework.utils;

import lombok.AllArgsConstructor;

import java.text.SimpleDateFormat;

public class DateUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm");

    public static String convertToString(long timestamp) {
        return SIMPLE_DATE_FORMAT.format(timestamp);
    }

    public static Long convertToTimestamp(String string) {
        string = string.toUpperCase();

        long time;

        try {
            time = Long.parseLong(string.substring(0, string.length() - 1));
        } catch (Exception e) {
            return null;
        }

        for(MuteTime value : MuteTime.values()) {
            if(!string.endsWith(String.valueOf(value.name().charAt(0)))) continue;

            return time * value.multiplier;
        }

        return null;
    }

    @AllArgsConstructor
    public enum MuteTime {

        SECONDS(1000),
        MINUTES(60 * 1000),
        HOURS(60 * 60 * 1000),
        DAYS(24 * 60 * 60 * 1000),
        WEEKS(7 * 24 * 60 * 60 * 1000),
        YEARS((long) 365 * 24 * 60 * 60 * 1000);

        public final long multiplier;

    }

}