package com.coderZsq._23_i18n;

import org.junit.Test;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleTest {
    @Test
    public void test1() throws Exception {
        // zh_CN
        System.out.println(Locale.CHINA);
        System.out.println(Locale.US);
        System.out.println(Locale.FRANCE);
        System.out.println(Locale.TAIWAN);
    }

    @Test
    public void testFormat() throws Exception {
        System.out.println(new Date());
        System.out.println(DateFormat.getInstance().format(new Date()));
    }

    // CNY123,456,789.79
    // 123 456 789,79 €
    // £123,456,789.79
    // $123,456,789.79
    @Test
    public void testNumberFormat() throws Exception {
        Double money = 123456789.79;
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(format.format(money));
    }
}
