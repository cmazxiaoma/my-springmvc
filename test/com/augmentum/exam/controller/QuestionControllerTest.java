package com.augmentum.exam.controller;

import javax.servlet.http.HttpSession;

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
import com.augmentum.exam.model.Question;
import com.augmentum.exam.model.User;
import com.augmentum.exam.utils.PathUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class QuestionControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private QuestionController questionController;
    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/springexam");
        AppContext context = AppContext.getContext();
        User user = new User();
        user.setName("augmentum");
        HttpSession session = new MockHttpSession();
        session.setAttribute(JsonCodeConstant.USER, user);
        session.setAttribute("garrett", "garrett");
        context.addObject(ServletConstant.APP_CONTEXT_SESSION, session);
        context.addObject(ServletConstant.APP_CONTEXT_USER, user);
        questionController = (QuestionController) this.applicationContext.getBean("questionController");
    }

    @After
    public void tearDown() throws Exception {
        AppContext context = AppContext.getContext();
        context.clear();
    }

    @Test
    public void query() {
        ModelAndView modelAndView = questionController.query("1", "DESC", 10);
        RedirectView redirectView = (RedirectView) modelAndView.getView();
        System.out.println("url=" + redirectView.getUrl());
        Assert.assertEquals(PathUtil.getFullPath(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX), redirectView.getUrl());
    }

    @Test
    public void createPost() {
        Question question = new Question();
        question.setQuestionId(null);
        question.setQuestionCodeId("WT123907");
        question.setQuestionDesc("Which is not a method of the class InputStream?");
        question.setQuestionAChoose("A.int read(byte[])");
        question.setQuestionBChoose("B.void flush()");
        question.setQuestionCChoose("C.void close()");
        question.setQuestionDChoose("D.int available()");
        question.setAnswer("B");
        ModelAndView modelAndView = questionController.createPost(question, "garrett");
        Assert.assertEquals(PathUtil.getFullPath(ServletConstant.REDIRECT_TO_DASHBOARD_CREATE), ((RedirectView) modelAndView.getView()).getUrl());
    }

    @Test
    public void detail() {
        ModelAndView modelAndView = questionController.detail("186");
        Assert.assertEquals(ServletConstant.QUESTION_DETAIL_PAGE, modelAndView.getViewName());
    }

    @Test
    public void del() {
        ModelAndView modelAndView = questionController.del("186");
        Assert.assertEquals(((RedirectView) modelAndView.getView()).getUrl(), PathUtil.getFullPath(ServletConstant.REDIRECT_TO_QUESTION_QUERY));
    }

    @Test
    public void editPost() {
        Question question = new Question();
        question.setQuestionId("186");
        question.setQuestionCodeId("WT123907");
        question.setQuestionDesc("Which is not a method of the class InputStream?");
        question.setQuestionAChoose("A.int read(byte[])");
        question.setQuestionBChoose("B.void flush()");
        question.setQuestionCChoose("C.void close()");
        question.setQuestionDChoose("D.int available()");
        question.setAnswer("B");
        ModelAndView modelAndView = questionController.editPost(question);
        Assert.assertEquals(((RedirectView) modelAndView.getView()).getUrl(), PathUtil.getFullPath(ServletConstant.REDIRECT_TO_DASHBOARD_EDIT));
    }

    @Test
    public void search() {
        ModelAndView modelAndView = questionController.search("10", "q", "DESC", 10);
        Assert.assertEquals(((RedirectView) modelAndView.getView()).getUrl(), PathUtil.getFullPath(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX));
    }

}
