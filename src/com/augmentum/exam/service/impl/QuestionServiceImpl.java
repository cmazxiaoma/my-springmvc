package com.augmentum.exam.service.impl;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.dao.QuestionDao;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;
import com.augmentum.exam.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public PageBean<Question> queryBypage(String sort, int currentPage, int pageSize) throws RuntimeException {
        PageBean<Question> pageBean = questionDao.findByPage(sort, currentPage, pageSize);
        if (pageBean.getData().isEmpty()) {
            throw new ServiceException(JsonCodeConstant.QUESTION_QUERY_BY_PAGE_ERROR_CODE, JsonCodeConstant.QUESTION_QUERY_BY_PAGE_ERROR_MESSAGE);
        }
        return pageBean;
    }

    @Override
    public Question queryOne(String questionId) throws RuntimeException {
        Question question = questionDao.findOne(questionId);
        if (question == null) {
            throw new ServiceException(JsonCodeConstant.QUESTION_QUERY_BY_ONE_ERROR_CODE, JsonCodeConstant.QUESTION_QUERY_BY_ONE_ERROR_MESSAGE);
        }
        return question;
    }

    @Override
    public int insert(Question question) throws RuntimeException {
        int result = questionDao.insert(question);
        if (result < 0) {
            throw new ServiceException(JsonCodeConstant.QUESTION_INSERT_ERROR_CODE, JsonCodeConstant.QUESTION_INSERT_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public boolean update(Question question) throws RuntimeException {
        boolean result = questionDao.update(question);
        if (!result) {
            throw new ServiceException(JsonCodeConstant.QUESTION_UPDATE_ERROR_CODE, JsonCodeConstant.QUESTION_UPDATE_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public boolean delete(String id) throws RuntimeException {
        boolean result = questionDao.delete(id);
        if (!result) {
            throw new ServiceException(JsonCodeConstant.QUESTION_UPDATE_ERROR_CODE, JsonCodeConstant.QUESTION_UPDATE_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public PageBean<Question> queryByFuzzy(String sort, int searchPage, String key, int pageSize) throws RuntimeException {
        PageBean<Question> pageBean = questionDao.findByFuzzy(sort, searchPage, key, pageSize);

        if (pageBean.getData().isEmpty()) {
            throw new ServiceException(JsonCodeConstant.QUESTION_QUERY_BY_FUZZY_ERROR_CODE, JsonCodeConstant.QUESTION_QUERY_BY_FUZZY_ERROR_MESSAGE);
        }
        return pageBean;
    }

    @Override
    public boolean deleteByBatch(String[] questionIds) throws RuntimeException {
        boolean result = questionDao.deleteByBatch(questionIds);
        if (!result) {
            throw new ServiceException(JsonCodeConstant.QUESTION_DELETE_BY_BATCH_ERROR_CODE, JsonCodeConstant.QUESTION_DELETE_BY_BATCH_ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public boolean checkQuestionCodeIdisExist(String questionCodeId) throws DataAccessException {
        boolean result = questionDao.checkQuestionCodeIdisExist(questionCodeId);
        if (result) {
            throw new ServiceException(JsonCodeConstant.QUESTION_CODEID_REPEAT_ERROR_CODE, JsonCodeConstant.QUESTION_CODEID_REPEAT_ERROR_MESSAGE);
        }
        return result;
    }
}
