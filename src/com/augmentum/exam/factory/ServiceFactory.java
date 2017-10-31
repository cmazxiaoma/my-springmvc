package com.augmentum.exam.factory;

import com.augmentum.exam.service.QuestionService;
import com.augmentum.exam.service.UserService;
import com.augmentum.exam.service.impl.QuestionServiceImpl;
import com.augmentum.exam.service.impl.UserServiceImpl;

public class ServiceFactory {

    public static UserService getUserSerivce() {
        return new UserServiceImpl();
    }

    public static QuestionService getQuestionService() {
        return new QuestionServiceImpl();
    }
}
