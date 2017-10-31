package com.augmentum.exam.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.augmentum.exam.dao.UserDao;
import com.augmentum.exam.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_USER_GET_USER_BY_NAME = ".getUserByName";

    @Override
    public User getUserByName(String userName) throws DataAccessException {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_USER_BY_NAME, userName);
    }
}
