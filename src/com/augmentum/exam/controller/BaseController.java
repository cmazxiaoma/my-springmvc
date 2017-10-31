package com.augmentum.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.User;
import com.augmentum.exam.utils.JsonUtil;
import com.augmentum.exam.utils.PathUtil;
import com.augmentum.exam.utils.SessionUtil;

public class BaseController {
    private final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        if (e instanceof ServiceException) {
            logger.error(e.getMessage() + "[" + ((ServiceException) e).getCode() + "]");
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, e.getMessage(), ((ServiceException) e).getCode()));
        } else if (e instanceof DataAccessException) {
            logger.error(JsonCodeConstant.PARAMETER_ERROR_MESSAGE + "[" + JsonCodeConstant.PARAMETER_ERROR_CODE + "]");
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.DAO_MYBATIS_EXCEPTION_MESSAGE, JsonCodeConstant.DAO_MYBATIS_EXCEPTION_CODE));
        }

        return new ModelAndView(new RedirectView(AppContext.getContextPath() + "/static/500.html"));
    }

    public User getUser() {
        return AppContext.getContext().getUser();
    }

    public String getUserName() {
        User user = getUser();
        if (user != null) {
            return user.getName();
        }

        return null;
    }

    public RedirectView getRedirectView(String path) {
        return new RedirectView(PathUtil.getFullPath(path));
    }

    public void addSession(String key, Object value) {
        SessionUtil.addSession(key, value);
    }

    public void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    public Object getSession(String key) {
        return SessionUtil.getSession(key);
    }

    public void invalidateSession() {
        SessionUtil.invalidate();
    }
}
