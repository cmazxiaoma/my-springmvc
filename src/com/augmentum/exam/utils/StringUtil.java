package com.augmentum.exam.utils;

import org.springframework.web.util.HtmlUtils;

import com.augmentum.exam.model.Question;
import com.augmentum.exam.model.User;

public class StringUtil {

    public static synchronized boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static synchronized String html2String(String str) {
        return HtmlUtils.htmlEscape(str);
    }

    public static synchronized Question EscapedQuestion(Question question) {
        question.setAnswer(html2String(question.getAnswer()));
        question.setQuestionAChoose(html2String(question.getQuestionAChoose()));
        question.setQuestionBChoose(html2String(question.getQuestionBChoose()));
        question.setQuestionCChoose(html2String(question.getQuestionCChoose()));
        question.setQuestionDChoose(html2String(question.getQuestionDChoose()));
        return question;
    }

    public static synchronized User EscapedUser(User user) {
        user.setName(html2String(user.getName()));
        user.setPassword(html2String(user.getPassword()));
        return user;
    }
}
