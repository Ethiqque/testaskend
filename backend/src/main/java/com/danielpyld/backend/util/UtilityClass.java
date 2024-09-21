package com.danielpyld.backend.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityClass {

    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";

    /**
     * Converts a String to a Date object based on a given format.
     *
     * @param dateStr The string representation of the date.
     * @param format  The date format.
     * @return The Date object or null if the string can't be parsed.
     */
    public static Date stringToDate(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a String to a Date object using the default format "dd-MM-yyyy".
     *
     * @param dateStr The string representation of the date.
     * @return The Date object or null if the string can't be parsed.
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DEFAULT_DATE_FORMAT);
    }

    /**
     * Converts a Date object to a String using the given format.
     *
     * @param date   The Date object.
     * @param format The date format.
     * @return The formatted date string.
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * Converts a Date object to a String using the default format "dd-MM-yyyy".
     *
     * @param date The Date object.
     * @return The formatted date string.
     */
    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_DATE_FORMAT);
    }
}
