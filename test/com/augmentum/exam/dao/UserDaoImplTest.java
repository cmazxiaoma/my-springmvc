package com.augmentum.exam.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.augmentum.exam.utils.SpringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoImplTest {
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = (UserDao) SpringUtil.getBean("userDao");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserByName() {
        userDao.getUserByName("augmentum");
    }
}
