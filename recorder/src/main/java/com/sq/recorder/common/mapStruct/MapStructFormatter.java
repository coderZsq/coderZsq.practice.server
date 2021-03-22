package com.sq.recorder.common.mapStruct;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

public class MapStructFormatter {
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface Date2Millis {
    }

    @Date2Millis
    public static Long date2millis(Date date) {
        if (date == null) return null;
        return date.getTime();
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface Mills2Date {
    }

    @Mills2Date
    public static Date millis2date(Long mills) {
        if (mills == null) return null;
        return new Date(mills);
    }
}
