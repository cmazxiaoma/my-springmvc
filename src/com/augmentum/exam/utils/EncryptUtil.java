package com.augmentum.exam.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class EncryptUtil {

    public static String makeToken() {
        String token = "USER" + (System.currentTimeMillis() + new Random().nextInt(999999999));
        try {
            MessageDigest mDigest = MessageDigest.getInstance("md5");
            byte[] md5 = mDigest.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isRepeatCommit(String serverToken, String clientToken) {
        if (serverToken == null) {
            return true;
        }

        if (clientToken == null) {
            return true;
        }

        if (!clientToken.equals(serverToken)) {
            return true;
        }

        return false;
    }
}
