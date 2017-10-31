package com.augmentum.exam.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.ServletConstant;

public class CookieUtil {

    public static void addCookies(String name, String password) {
        HttpServletResponse response = (HttpServletResponse) AppContext.getContext().getObject(ServletConstant.APP_CONTEXT_RESPONSE);
        Cookie nameCookie = new Cookie("username", name);
        Cookie passwordCookie = new Cookie("password", password);
        nameCookie.setMaxAge(24 * 60 * 60);
        passwordCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(nameCookie);
        response.addCookie(passwordCookie);
    }

    public static void removeCookies() {
        HttpServletRequest request = (HttpServletRequest) AppContext.getContext().getObject(ServletConstant.APP_CONTEXT_REQUEST);
        HttpServletResponse response = (HttpServletResponse) AppContext.getContext().getObject(ServletConstant.APP_CONTEXT_RESPONSE);
        if (request != null && response != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
