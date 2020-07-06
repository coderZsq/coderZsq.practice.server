package com.sq.resume.util;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {
    @Test
    public void underlineCase() {
        Assert.assertTrue(Strings.underlineCase("MyFirstAge").equals("my_first_age"));
        Assert.assertTrue(Strings.underlineCase("myFirstAge").equals("my_first_age"));
    }
}
