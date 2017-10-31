package com.augmentum.exam.factory;

import com.augmentum.exam.dao.QuestionDao;
import com.augmentum.exam.dao.UserDao;
import com.augmentum.exam.dao.impl.QuestionDaoImpl;
import com.augmentum.exam.dao.impl.UserDaoImpl;

public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static QuestionDao getQuestionDao() {
        return new QuestionDaoImpl();
    }
}
