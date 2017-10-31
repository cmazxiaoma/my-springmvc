package com.augmentum.exam.utils;

import com.augmentum.exam.model.Question;
import com.augmentum.exam.model.QuestionEnum;

public class QuestionUtil {

    public static String getAnswer(Question question) {
        String answer = question.getAnswer().trim();
        if (answer.equals(question.getQuestionAChoose().trim())) {
            answer = QuestionEnum.A.getValue();
        } else if (answer.equals(question.getQuestionBChoose().trim())) {
            answer = QuestionEnum.B.getValue();
        } else if (answer.equals(question.getQuestionCChoose().trim())) {
            answer = QuestionEnum.C.getValue();
        } else {
            answer = QuestionEnum.D.getValue();
        }
        return answer;
    }
}
