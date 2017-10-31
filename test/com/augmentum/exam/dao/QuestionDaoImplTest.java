package com.augmentum.exam.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;
import com.augmentum.exam.utils.SpringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class QuestionDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests{
    private QuestionDao questionDao;

    @Before
    public void setUp() throws Exception {
        questionDao = (QuestionDao) SpringUtil.getBean("questionDao");
    }

    @After
    public void tearDown() throws Exception {
        AppContext context = AppContext.getContext();
        context.clear();
    }

    @Test
    public void findOne() {
        questionDao.findOne("1");
    }

    @Test
    public void findByFuzzy() {
        PageBean<Question> pageBean = questionDao.findByFuzzy("ASC", 1, "WT", 10);
        System.out.println("data=" + pageBean.getData());
    }

    @Test
    public void insert() {
        Question question = new Question();
        question.setQuestionId(null);
        question.setQuestionCodeId("WT12347");
        question.setQuestionDesc("Which is not a method of the class InputStream?");
        question.setQuestionAChoose("A.int read(byte[])");
        question.setQuestionBChoose("B.void flush()");
        question.setQuestionCChoose("C.void close()");
        question.setQuestionDChoose("D.int available()");
        question.setAnswer("B");
        questionDao.insert(question);
    }

    @Test
    public void delete() {
        questionDao.delete("12");
    }

    @Test
    public void query() {
        PageBean<Question> pageBean = questionDao.findByPage("DESC", 1, 10);
        System.out.println(pageBean.toString());
    }

    @Test
    public void deleteByBatch() {
        questionDao.deleteByBatch(new String[]{"55", "23"});
    }

    @Test
    public void update() {
        Question question = new Question();
        question.setQuestionId("11");
        question.setQuestionCodeId("WT12347");
        question.setQuestionDesc("Which is not a method of the class InputStream?");
        question.setQuestionAChoose("A.int read(byte[])update");
        question.setQuestionBChoose("B.void flush()update");
        question.setQuestionCChoose("C.void close()update");
        question.setQuestionDChoose("D.int available()update");
        question.setAnswer("C");
        boolean result = questionDao.update(question);
        System.out.println(result);
    }

    @Test
    public void checkCodeId() {
        boolean flag = questionDao.checkQuestionCodeIdisExist("WQE787872145");
        System.out.println("flag=" + flag);
    }
}
