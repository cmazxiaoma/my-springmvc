package com.augmentum.exam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.constant.ServletConstant;
import com.augmentum.exam.constant.SessionContant;
import com.augmentum.exam.exception.ParameterException;
import com.augmentum.exam.json.LoginJson;
import com.augmentum.exam.json.ReturnJson;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;
import com.augmentum.exam.model.User;
import com.augmentum.exam.service.QuestionService;
import com.augmentum.exam.service.UserService;
import com.augmentum.exam.utils.StringUtil;

@Controller
@RequestMapping(value = ServletConstant.PAGE_JSON_MAPPING)
public class JsonController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;
    private final Logger logger = Logger.getLogger(JsonController.class);

    @Resource(name = "questionService")
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = ServletConstant.QUESTION_SEARCH_MAPPING, method = RequestMethod.GET)
    @ResponseBody
    public ReturnJson search(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "searchkey") String key, @RequestParam(value = "sort", defaultValue = "DESC") String sort, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        int searchPage = 0;
        try {
            searchPage = Integer.parseInt(page);
        } catch (Exception e) {
            logger.error("parse page error");
            return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
        }

        if (pageSize != 5 && pageSize != 10) {
            pageSize = 10;
        }

        if (!sort.equalsIgnoreCase("DESC") && !sort.equalsIgnoreCase("ASC")) {
            return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
        }

        PageBean<Question> pageBean = null;
        pageBean = questionService.queryByFuzzy(sort, searchPage, key, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SessionContant.SORT, sort);
        map.put(SessionContant.SEARCH_KEY, key);
        map.put(SessionContant.IS_QUERY, false);
        map.put(SessionContant.PAGEBEAN, pageBean);
        return new ReturnJson(JsonCodeConstant.SUCCESS, map, "");
    }

    @RequestMapping(value = ServletConstant.QUESTION_DEL_MAPPING + "/{questionIds}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnJson del(@PathVariable String questionIds) {
        if (StringUtil.isEmpty(questionIds)) {
            return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
        }

        String[] delQuestionIds = questionIds.split("-");

        for (String id : delQuestionIds) {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                logger.error("parse questionId error");
                return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
            }
        }

        questionService.deleteByBatch(delQuestionIds);
        return new ReturnJson(JsonCodeConstant.SUCCESS, "", "");
    }

    @RequestMapping(value = ServletConstant.QUESTION_QUERY_MAPPING, method = RequestMethod.GET)
    @ResponseBody
    public ReturnJson query (@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "sort", defaultValue = "DESC") String sort, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        int curPage = 0;
        try {
            curPage = Integer.parseInt(page);
        } catch (Exception e) {
            logger.error("parse page error");
            return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
        }

        if (pageSize != 5 && pageSize != 10) {
            pageSize = 10;
        }

        if (!sort.equalsIgnoreCase("DESC") && !sort.equalsIgnoreCase("ASC")) {
            return new ReturnJson(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE);
        }

        PageBean<Question> pageBean = null;
        pageBean = questionService.queryBypage(sort, curPage, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SessionContant.PAGEBEAN, pageBean);
        map.put(SessionContant.SORT, sort);
        map.put(SessionContant.IS_QUERY, true);
        map.put(SessionContant.SEARCH_KEY, "");
        return new ReturnJson(JsonCodeConstant.SUCCESS, map, "");
    }


    @RequestMapping(value = ServletConstant.QUESTION_DETAIL_MAPPING + "/{questionId}")
    @ResponseBody
    public ReturnJson detail(@PathVariable String questionId) {
        Question question = questionService.queryOne(questionId);
        return new ReturnJson(JsonCodeConstant.SUCCESS, question, "");
    }

    @RequestMapping(value = ServletConstant.USER_LOGIN_MAPPING, method = RequestMethod.POST)
    @ResponseBody
    public ReturnJson login(@RequestBody LoginJson json) {
        User user = null;
        try {
            user = userService.login(json.getUsername(), json.getPassword());
            user.setPassword(null);
        } catch(ParameterException e) {
            if (e.isErrorField()) {
                return new ReturnJson(JsonCodeConstant.ERROR, e.getErrorFields(), JsonCodeConstant.USER_LOGIN_PARAMETER_ERROR_CODE);
            }
        }
        return new ReturnJson(JsonCodeConstant.SUCCESS, "", "");
    }

    @RequestMapping(value = ServletConstant.CHECK_QUESTION_CODE_ID, method = RequestMethod.GET)
    @ResponseBody
    public ReturnJson checkCodeId(@RequestParam(value = "questionCodeId") String questionCodeId) {
        questionService.checkQuestionCodeIdisExist(questionCodeId);
        return new ReturnJson(JsonCodeConstant.SUCCESS, "", "");
    }
}
