package com.augmentum.exam.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.augmentum.exam.constant.MyBatisMapperContant;
import com.augmentum.exam.dao.QuestionDao;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {
    private static final String CLASS_NAME = Question.class.getName();

    @Override
    public boolean checkQuestionCodeIdisExist(String questionCodeId) throws DataAccessException {
        return getSqlSession().selectOne(CLASS_NAME + ".checkCodeId", questionCodeId) != null;
    }

    @Override
    public Question findOne(String id) {
        return getSqlSession().selectOne(CLASS_NAME + ".getById", id);
    }

    @Override
    public PageBean<Question> findByPage (String sort, int currentPage, int pageSize) {
        int offSet = (currentPage - 1) * pageSize;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(MyBatisMapperContant.PAGE_SIZE, pageSize);
        params.put(MyBatisMapperContant.OFFSET, offSet);
        params.put(MyBatisMapperContant.SORT, sort);
        List<Question> data = getSqlSession().selectList(CLASS_NAME + ".queryByPage", params);
        PageBean<Question> pageBean = new PageBean<Question>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setData(data);
        pageBean.setPageSize(pageSize);
        int count = getSqlSession().selectOne(CLASS_NAME + ".getCount");
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public int insert(Question question) {
       return getSqlSession().insert(CLASS_NAME + ".add", question);
    }

    @Override
    public boolean update(Question question) {
        return getSqlSession().update(CLASS_NAME + ".update", question) > 0;
    }

    @Override
    public boolean delete(String id) {
        return getSqlSession().delete(CLASS_NAME + ".delete", id) > 0;
    }

    @Override
    public PageBean<Question> findByFuzzy(String sort, int currentPage, String key, int pageSize) {
        int offSet = (currentPage - 1) * pageSize;
        String conditionOne = "%" + key + "%";
        String conditionTwo = "%" + key + "%";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(MyBatisMapperContant.PAGE_SIZE, pageSize);
        params.put(MyBatisMapperContant.OFFSET, offSet);
        params.put(MyBatisMapperContant.QUESTION_CODE_ID, conditionOne);
        params.put(MyBatisMapperContant.QUESTION_DESC, conditionTwo);
        List<Question> data = getSqlSession().selectList(CLASS_NAME + ".queryByFuzzy", params);
        PageBean<Question> pageBean = new PageBean<Question>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setData(data);
        pageBean.setPageSize(pageSize);
        int count = getSqlSession().selectOne(CLASS_NAME + ".getCountBySearch", params);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public boolean deleteByBatch(String[] questionIds) {
        return getSqlSession().delete(CLASS_NAME + ".deleteByBatch", questionIds) > 0;
    }
}
