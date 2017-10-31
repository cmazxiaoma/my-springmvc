package com.augmentum.exam.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        DBUtil.getInstance().getConnection();
    }
}
