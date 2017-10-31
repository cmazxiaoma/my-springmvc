package com.augmentum.exam.utils;

import java.util.Random;

public class RandomUtil {

    public static String makeQuestionCodeId() {
        int otherStr = new Random().nextInt(99999999);
        return "Q" + otherStr;
    }
}
