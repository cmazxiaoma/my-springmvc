package com.augmentum.exam.utils;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.ServletConstant;

public class PathUtil {

    public static String getFullPath(String path) {
        if (path == null) {
            path = "";
        }
        String urlPrefix = ServletConstant.APP_URL_PREFIX;
        if (!StringUtil.isEmpty(urlPrefix)) {
            urlPrefix += "/";
        }
        return AppContext.getContextPath() + "/" + urlPrefix + path;
    }
}
