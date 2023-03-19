package com.example.springbatch.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    private static final List<Integer> NON_BUSINESS_DAYS = Arrays.asList(
            Calendar.SATURDAY,
            Calendar.SUNDAY
    );

    /**
     * format Empty.
     *
     * @param o the str
     * @return str
     */
    public static String formatEmpty(Object o) {
        if (o == null) {
            return "";
        } else
            return o.toString();

    }

    /**
     * format Null.
     *
     * @param o the str
     * @return str
     */
    public static String formatNull(Object o) {
        if (o == null) {
            return (String) null;
        } else
            return o.toString();

    }

    /**
     * format Int.
     *
     * @param o the str
     * @return str
     */
    public static int formatInt(Object o) {
        if (o == null) {
            return 0;
        } else {
            try {
                return Integer.parseInt(o.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    /**
     * format Long.
     *
     * @param o the str
     * @return Long
     */
    public static long formatLong(Object o) {
        if (o == null) {
            return 0;
        } else
            return Long.parseLong(o.toString());
    }

    /**
     * format Double.
     *
     * @param o the str
     * @return str
     */
    public static double formatDouble(Object o) {
        if (o == null) {
            return 0;
        } else {
            try {
                return Double.parseDouble(o.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    /**
     * format Date (MM/dd/YYYY).
     *
     * @param o the date object
     * @return str formatted to MM/dd/YYYY
     */
    public static String formatDate(Object o) {
        if (o == null) {
            return "12/31/4000";
        } else {
            Timestamp ts = (Timestamp) o;
            return new SimpleDateFormat("MM/dd/yyyy").format(new Date(ts.getTime()));
        }
    }

    /**
     * Returns past or future business date
     *
     * @param date         starting date
     * @param businessDays number of business days to add/subtract
     *                     <br/>note: set this as negative value to get past date
     * @return past or future business date by the number of businessDays value
     */
    public static Date businessDaysFrom(Date date, int businessDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < Math.abs(businessDays); ) {
            calendar.add(Calendar.DAY_OF_MONTH, businessDays > 0 ? 1 : -1);
            if (!NON_BUSINESS_DAYS.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                i++;
            }
        }
        return calendar.getTime();
    }

    /**
     * Returns next month Date (MM/dd/YYYY).
     *
     * @param day the day to set for next month
     * @return str formatted to MM/dd/YYYY
     */
    public static String getNextMonthDate(int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return new SimpleDateFormat("MM/dd/yyyy").format(c.getTime());
    }

    /**
     * Returns next pay Date (MM/dd/YYYY) from the given date.
     *
     * @param day the day to make payment each month
     * @return str formatted to MM/dd/YYYY
     */
    public static String getNextPayDate(Date fromDate, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);

        if (day <= c.get(Calendar.DAY_OF_MONTH)) {
            // Get the next month date as pay day for from date has passed
            c.add(Calendar.MONTH, 1);
            int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (day > lastDay) {
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                c.set(Calendar.DAY_OF_MONTH, day);
            }
        } else {
            // Get the pay date for the current month as it has not passed the next pay date
            int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (day > lastDay) {
                c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                c.set(Calendar.DAY_OF_MONTH, day);
            }
        }

        return new SimpleDateFormat("MM/dd/yyyy").format(c.getTime());
    }

    /**
     * Returns whether the day is last of the month.
     *
     * @param dateStr the day to set
     * @return true or false
     */
    public static Boolean lastDayOfMonth(String dateStr) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        cal.setTime(sdf.parse(dateStr));
        int day = cal.get(Calendar.DAY_OF_MONTH);
        boolean endDay = false;
        int noOfLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day == noOfLastDay) {
            endDay = true;
        }
        return endDay;
    }

    /**
     * Check if code exists in list.
     *
     * @param code  the code
     * @param codes the list of codes
     * @return true or false
     */
    public static Boolean codeExists(String code, String[] codes) {
        List<String> list = Arrays.asList(codes);
        return list.contains(code);
    }

    /**
     * get String last x letter.
     *
     * @param str    the String
     * @param number the last x number
     * @return String
     */
    public static String getLastXLetter(String str, int number) {
        if (str == null) {
            return "";
        } else {
            return str.substring(str.length() - number);
        }
    }

}

