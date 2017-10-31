package com.augmentum.exam.service.impl;

import org.springframework.dao.DataAccessException;

import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.dao.UserDao;
import com.augmentum.exam.exception.ParameterException;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.User;
import com.augmentum.exam.service.UserService;
import com.augmentum.exam.utils.StringUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password) throws ParameterException, RuntimeException, DataAccessException {
        ParameterException parameterException = new ParameterException();
        if (StringUtil.isEmpty(userName)) {
            parameterException.addErrorFields(JsonCodeConstant.USER_ERROR_USERNAME, JsonCodeConstant.USER_NAME_IS_NULL_MESSAGE);
        }

        if (StringUtil.isEmpty(password)) {
            parameterException.addErrorFields(JsonCodeConstant.USER_ERROR_PASSWORD, JsonCodeConstant.USER_PWD_IS_NULL_MESSAGE);
        }

        if (parameterException.isErrorField()) {
            throw parameterException;
        }

        User user = userDao.getUserByName(userName);

        if (user == null) {
            throw new ServiceException(JsonCodeConstant.USER_NO_REGISTER_CODE, JsonCodeConstant.USER_NO_REGISTER_MESSAGE);
        }

        if (!user.getPassword().equals(password)) {
            throw new ServiceException(JsonCodeConstant.USER_LOGIN_ERROR_CODE, JsonCodeConstant.USER_LOGIN_ERROR_MESSAGE);
        }
        return user;
    }
}
