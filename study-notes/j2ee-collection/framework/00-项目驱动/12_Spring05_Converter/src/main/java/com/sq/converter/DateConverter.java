package com.sq.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateConverter implements Converter<String, Date> {
    private List<String> formats;

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

    @Override
    public Date convert(String s) {
        for (String format : formats) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat(format);
                return fmt.parse(s);
            } catch (ParseException e) {
                // e.printStackTrace();
            }
        }
        return null;
    }
}
