package com.example.alex.testtask.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final Locale DEFAULT_LOCALE = Locale.UK;

    private static final String SERIALIZE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String DESERIALIZE_DATE_FORMAT = "dd MMM yyyy 'at' HH:mm:ss";

    private static final SimpleDateFormat SERIALIZE = new SimpleDateFormat(SERIALIZE_DATE_FORMAT, DEFAULT_LOCALE);
    private static final SimpleDateFormat DESERIALIZE = new SimpleDateFormat(DESERIALIZE_DATE_FORMAT, DEFAULT_LOCALE);

    public static String getCurrentDate() {
        return SERIALIZE.format(new Date());
    }

    public static String normalizeDate(String date) {
        if (date == null || "".equals(date)) {
            return null;
        }

        try {
            return DESERIALIZE.format(SERIALIZE.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
