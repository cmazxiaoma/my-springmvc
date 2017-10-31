package com.augmentum.exam.dao;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;

public interface QuestionDao {

    PageBean<Question> findByPage(String sort, int currentpage, int pageSize) throws DataAccessException;

    Question findOne(String questionId) throws DataAccessException;

    int insert(Question question) throws DataAccessException;

    boolean update(Question question) throws DataAccessException;

    boolean  delete(String id) throws DataAccessException;

    PageBean<Question> findByFuzzy (String sort, int currentpage, String key, int pageSize) throws DataAccessException;

    boolean deleteByBatch (String[] questionIds) throws DataAccessException;

    boolean checkQuestionCodeIdisExist (String questionCodeId) throws DataAccessException;
}
