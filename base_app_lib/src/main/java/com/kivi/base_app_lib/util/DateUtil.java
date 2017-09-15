package com.kivi.base_app_lib.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 */

public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");


    public static String formatDateTime(long date) {
        return DATE_FORMAT_DATETIME.format(new Date(date));
    }

    public static String formatDate(long date) {
        return DATE_FORMAT_DATE.format(new Date(date));
    }

    public static String formatTime(long date) {
        return DATE_FORMAT_TIME.format(new Date(date));
    }

    public static String formatDateCustom(long beginDate, String format) {
        return new SimpleDateFormat(format).format(new Date(beginDate));
    }

    public static String formatDateCustom(Date beginDate, String format) {
        return new SimpleDateFormat(format).format(beginDate);
    }


}
