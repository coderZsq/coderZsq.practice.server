package cn.wolfcode.netty.im.webserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 *
 * @author Leon
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            return new SimpleDateFormat(pattern).format(date);
        }

        return null;
    }

    public static Date parse(String dateStr) throws ParseException {
        return new SimpleDateFormat(DATE_TIME_PATTERN).parse(dateStr);
    }
}
