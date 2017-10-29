package ru.gdgkazan.pictureofdaymvvm.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Alexey Pakhtusov
 */
public class Dates {

//    private static boolean isDayValid(int day) {
//        return day <= getDayOfMonth() && day >= 1;
//    }
//
//    private static boolean isMonthValid(int month) {
//        return month <= getMonth() && month >= 0;
//    }
//
//    public static int getDayOfMonth() {
//        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//    }
//
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getDayOfYear() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }
//
//    public static String getDayOfCurrentYearAndMonth() {
//        return getDayOfCurrentYearAndMonth(getDayOfMonth());
//    }
//
//    public static String getDayOfCurrentYearAndMonth(int day) {
//        return getDayOfCurrentYear(getMonth(), day);
//    }
//
//    public static String getDayOfCurrentYear(int month, int day) {
    private static String getDayOfCurrentYear(int month, int day) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(calendar.get(Calendar.YEAR), month, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        return dateFormat.format(calendar.getTime());
    }
//
//    public static List<String> getDaysOfCurrentYearAndMonth() {
//        return getDaysOfCurrentYearAndMonth(1, getDayOfMonth());
//    }
//
//    public static List<String> getDaysOfCurrentYearAndMonth(int startDay, int endDay) {
//        return getDaysOfCurrentYear(getMonth(), getMonth(), startDay, endDay);
//    }
//
//    public static List<String> getDaysOfCurrentYear(int startMonth, int endMonth, int startDay, int endDay) {
//        if (isMonthValid(startMonth) && isMonthValid(endMonth) && startMonth <= endMonth &&
//            isDayValid(startDay) && isDayValid(endDay) && startDay <= endDay) {
//            List<String> days = new ArrayList<>();
//
//            for (int month = startMonth; month <= endMonth; ++month) {
//                for (int day = startDay; day <= endDay; ++day) {
//                    days.add(getDayOfCurrentYear(month, day));
//                }
//            }
//
//            return days;
//        } else {
//            return null;
//        }
//    }

    public static List<String> getDaysOfCurrentYear() {
        List<String> days = new ArrayList<>();

        for (int month = 0; month <= getMonth(); ++month) {
            Calendar calendar = new GregorianCalendar(getYear(), month, 1);

            for (int day = 1; day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); ++day) {
                days.add(getDayOfCurrentYear(month, day));
            }
        }

        return days;
    }
}
