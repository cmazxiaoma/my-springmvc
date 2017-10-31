package com.augmentum.exam.dao;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.model.User;

public interface UserDao {

    User getUserByName(String userName) throws DataAccessException;
}
