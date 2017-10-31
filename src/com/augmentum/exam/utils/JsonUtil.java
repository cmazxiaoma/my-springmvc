package com.augmentum.exam.utils;

import java.util.HashMap;
import java.util.Map;

import com.augmentum.exam.constant.JsonCodeConstant;

public class JsonUtil {

    public static Map<String, Object> toMap(String result, String data, String errorCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(JsonCodeConstant.RETURN_JSON_RESULT, result);
        map.put(JsonCodeConstant.RETURN_JSON_DATA, data);
        map.put(JsonCodeConstant.RETURN_JSON_ERRORCODE, errorCode);
        return map;
    }
}
