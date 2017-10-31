package com.augmentum.exam.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.constant.ServletConstant;
import com.augmentum.exam.exception.ParameterException;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.User;
import com.augmentum.exam.service.UserService;
import com.augmentum.exam.utils.CookieUtil;
import com.augmentum.exam.utils.StringUtil;

@Controller
@RequestMapping(value = ServletConstant.PAGE_USER_MAPPING)
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;
    private final Logger logger = Logger.getLogger(UserController.class);

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = ServletConstant.USER_LOGOUT_MAPPING, method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        this.invalidateSession();
        modelAndView.addObject(JsonCodeConstant.TIP_MESSAGE, JsonCodeConstant.USER_LOGOUT_SUCCESS_MESSAGE);
        modelAndView.setViewName(ServletConstant.USER_LOGIN_PAGE);
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.USER_LOGIN_MAPPING, method = RequestMethod.GET)
    public ModelAndView loginGet(@RequestParam(value = "lastpage", defaultValue = "") String lastpage) {
        ModelAndView modelAndView = new ModelAndView();
        if (this.getSession(JsonCodeConstant.USER) == null) {
            if (!StringUtil.isEmpty(lastpage)) {
                modelAndView.addObject("lastpage", lastpage);
            }
            modelAndView.setViewName(ServletConstant.USER_LOGIN_PAGE);
        } else {
            modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_QUESTION_QUERY));
        }
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.USER_LOGIN_MAPPING, method = RequestMethod.POST)
    public ModelAndView loginPost(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "lastpage") String lastpage, @RequestParam(value = "remeber", defaultValue = "no") String isRemeber) {
        ModelAndView modelAndView = new ModelAndView();
        User user = null;
        try {
            user = userService.login(userName, password);
            user.setPassword(null);
            this.addSession(JsonCodeConstant.USER, user);

            if (!StringUtil.isEmpty(lastpage)) {
                modelAndView.setView(this.getRedirectView(lastpage));
            } else {
                logger.info("login success");
                if (isRemeber.equals("yes")) {
                    CookieUtil.addCookies(userName, password);
                } else {
                    logger.info("clear cookies");
                    CookieUtil.removeCookies();
                }
                modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX));
            }
        } catch(ParameterException e) {
            if (e.isErrorField()) {
                this.addSession(JsonCodeConstant.ERROR_FIELDS, e.getErrorFields());
                modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_USER_LOGIN));
                return modelAndView;
            }
        } catch (ServiceException exception) {
            this.addSession("USERNAME", userName);
            this.addSession("PASSWORD", password);
            this.addSession(JsonCodeConstant.TIP_MESSAGE, exception.getMessage() + "[" + exception.getCode() + "]");
            modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_USER_LOGIN));
            return modelAndView;
        }
        return modelAndView;
    }
}
