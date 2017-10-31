package com.augmentum.exam.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.constant.ServletConstant;
import com.augmentum.exam.utils.PathUtil;
import com.augmentum.exam.utils.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/springexam");
        AppContext context = AppContext.getContext();
        context.addObject(ServletConstant.APP_CONTEXT_SESSION, new MockHttpSession());
        userController = (UserController) this.applicationContext.getBean("userController");
    }

    @After
    public void tearDown() throws Exception {
        AppContext context = AppContext.getContext();
        context.clear();
    }

    @Test
    public void login() {
        ModelAndView modelAndView = userController.loginPost("augmentum", "augmentum", "", "no");
        RedirectView redirectView = (RedirectView) modelAndView.getView();
        System.out.println("url=" + redirectView.getUrl());
        Assert.assertEquals(PathUtil.getFullPath(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX), redirectView.getUrl());
        Assert.assertNotNull(SessionUtil.getSession(JsonCodeConstant.USER));
    }

    @Test
    public void logout() {
        ModelAndView modelAndView = userController.logout();
        String viewName = modelAndView.getViewName();
        Assert.assertEquals(ServletConstant.USER_LOGIN_PAGE, viewName);
    }
}
