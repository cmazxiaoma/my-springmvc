package com.augmentum.exam.service;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;

public interface QuestionService {

    PageBean<Question> queryBypage(String sort, int currentPage, int pageSize) throws ServiceException, DataAccessException;

    Question queryOne(String questionId) throws ServiceException, DataAccessException;

    int insert(Question question) throws ServiceException, DataAccessException;

    boolean update(Question question) throws ServiceException, DataAccessException;

    boolean  delete(String id) throws ServiceException, DataAccessException;

    PageBean<Question> queryByFuzzy(String sort, int searchPage, String key, int pageSize) throws ServiceException, DataAccessException;

    boolean deleteByBatch (String[] questionIds) throws ServiceException, DataAccessException;

    boolean checkQuestionCodeIdisExist (String questionCodeId) throws ServiceException, DataAccessException;
}
