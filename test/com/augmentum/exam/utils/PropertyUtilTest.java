package com.augmentum.exam.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.augmentum.exam.jdbc.PropertyUtil;

public class PropertyUtilTest {

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void test() {
        PropertyUtil propertyUtil = new PropertyUtil();
        String username = propertyUtil.getProperty("username");
        System.out.println(username);
    }
}
