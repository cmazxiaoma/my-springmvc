package com.augmentum.exam.service;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.exception.ParameterException;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.User;

public interface UserService {

    User login(String userName, String password) throws ParameterException, ServiceException, DataAccessException;
}
