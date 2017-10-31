package com.augmentum.exam.utils;

import java.lang.reflect.Method;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.ServletConstant;

public class SessionUtil {

    public static Object getSessionInThread() {
        Object session = AppContext.getContext().getObject(ServletConstant.APP_CONTEXT_SESSION);
        return session;
    }

    public static void addSession(String key, Object value) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }

        try {
            Class<?>[] params = new Class[2];
            params[0] = String.class;
            params[1] = Object.class;
            Method method = session.getClass().getMethod("setAttribute", params);
            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = value;
            method.invoke(session, objects);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return null;
        }
        try {
            Class<?>[] params = new Class[1];
            params[0] = String.class;
            Method method = session.getClass().getMethod("getAttribute", params);
            Object[] objects = new Object[1];
            objects[0] = key;
            Object returnValue = method.invoke(session, objects);
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void removeSession(String key) {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }

        try {
            Class<?>[] params = new Class[1];
            params[0] = String.class;
            Method method = session.getClass().getMethod("removeAttribute", params);
            Object[] objects = new Object[1];
            objects[0] = key;
            method.invoke(session, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }

        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
