package com.augmentum.exam.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.augmentum.exam.constant.ServletConstant;

@Controller
@RequestMapping(value="/page/dashboard")
public class DashboardController extends BaseController {

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView index (String queryparams, Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ServletConstant.QUESTION_INDEX_PAGE);
        return modelAndView;
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public org.springframework.web.servlet.ModelAndView create (String queryparams, Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();;
        modelAndView.setViewName(ServletConstant.QUESTION_CREATE_PAGE);
        return modelAndView;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public ModelAndView edit (String queryparams, Map<String, String> request, Map<String, Object> session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ServletConstant.QUESTION_EDIT_PAGE);
        return modelAndView;
    }
}
