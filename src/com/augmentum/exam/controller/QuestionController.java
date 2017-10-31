package com.augmentum.exam.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.constant.ServletConstant;
import com.augmentum.exam.constant.SessionContant;
import com.augmentum.exam.exception.ServiceException;
import com.augmentum.exam.model.PageBean;
import com.augmentum.exam.model.Question;
import com.augmentum.exam.service.QuestionService;
import com.augmentum.exam.utils.EncryptUtil;
import com.augmentum.exam.utils.JsonUtil;
import com.augmentum.exam.utils.QuestionUtil;
import com.augmentum.exam.utils.StringUtil;

@Controller
@RequestMapping(value = ServletConstant.PAGE_QUESTION_MAPPING)
public class QuestionController extends BaseController {

    @Resource(name = "questionService")
    private QuestionService questionService;
    private final Logger logger = Logger.getLogger(QuestionService.class);

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = ServletConstant.QUESTION_DETAIL_MAPPING + "/{questionId}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable String questionId) {
        ModelAndView modelAndView = new ModelAndView();
        Question question = questionService.queryOne(questionId);
        this.addSession(SessionContant.QUESTION, question);
        modelAndView.setViewName(ServletConstant.QUESTION_DETAIL_PAGE);
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.QUESTION_PAGESIZE_MAPPING, method = RequestMethod.GET)
    public ModelAndView changePageSize(@RequestParam(value = "pagesize", defaultValue = "10") String str, @RequestParam(value = "isquery", defaultValue = "true") String isQuery) {
        ModelAndView modelAndView = new ModelAndView();
        int pagesize;
        try {
            pagesize = Integer.parseInt(str);
            if (pagesize != 5 && pagesize != 10) {
                pagesize = 10;
            }
        } catch (Exception e) {
            logger.error("parse pagesize error");
            pagesize = 10;
        }

        this.addSession(SessionContant.SELECT_TEXT, pagesize);
        if (isQuery.equals("true")) {
            modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_QUESTION_QUERY + "?pagesize=" + pagesize));
        } else {
            modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_QUESTION_SEARCH + "?searchkey=" + this.getSession(SessionContant.SEARCH_KEY)+ "&pagesize=" + pagesize));
        }

        return modelAndView;
    }


    @RequestMapping(value = ServletConstant.QUESTION_DEL_MAPPING + "/{questionIds}", method = RequestMethod.GET)
    public ModelAndView del(@PathVariable String questionIds) {
        ModelAndView modelAndView = new ModelAndView();

        if (StringUtil.isEmpty(questionIds)) {
            modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_QUESTION_QUERY));
            return modelAndView;
        }

        String[] delQuestionIds = questionIds.split("-");

        for (String id : delQuestionIds) {
            try {
                Integer.parseInt(id);
            } catch (Exception e) {
                logger.error("parse questionId error");
                return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE));
            }
        }

        questionService.deleteByBatch(delQuestionIds);
        this.addSession(ServletConstant.DEL_QUESTION_RESULT, JsonCodeConstant.SUCCESS);
        modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_QUESTION_QUERY));
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.QUESTION_CREATE_MAPPING, method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ServletConstant.QUESTION_CREATE_PAGE);
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.QUESTION_EDIT_MAPPING, method = RequestMethod.POST)
    public ModelAndView editPost(Question question){
        System.out.println(question.toString());
        ModelAndView modelAndView = new ModelAndView();
        question.setAnswer(QuestionUtil.getAnswer(question));
        questionService.update(question);
        this.addSession(ServletConstant.EDIT_QUESTION_RESULT, JsonCodeConstant.SUCCESS);
        modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_DASHBOARD_EDIT));
        return modelAndView;

    }

    @RequestMapping(value = ServletConstant.QUESTION_EDIT_MAPPING + "/{questionId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String questionId) {
        ModelAndView modelAndView = new ModelAndView();
        Question question = null;
        question = questionService.queryOne(questionId);
        this.addSession(SessionContant.QUESTION, question);
        modelAndView.setViewName(ServletConstant.QUESTION_EDIT_PAGE);
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.QUESTION_SEARCH_MAPPING, method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "searchkey") String key, @RequestParam(value = "sort", defaultValue = "DESC") String sort, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        int searchPage = 0;
        try {
            searchPage = Integer.parseInt(page);
        } catch (Exception e) {
            logger.error("parse page error");
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE));
        }

        if (StringUtil.isEmpty(sort)) {
            if(this.getSession(SessionContant.SORT) != null) {
                sort = (String) this.getSession(SessionContant.SORT);
            }
        }

        if (!sort.equalsIgnoreCase("DESC") && !sort.equalsIgnoreCase("ASC")) {
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE));
        }

        if (this.getSession(SessionContant.SELECT_TEXT) != null) {
            pageSize = (Integer) this.getSession(SessionContant.SELECT_TEXT);
        }

        PageBean<Question> pageBean = null;
        try {
            pageBean = questionService.queryByFuzzy(sort, searchPage, key, pageSize);
        } catch (ServiceException e) {
            logger.error(e.getMessage() + "[" + e.getCode() + "]");
        }
        this.addSession(SessionContant.SORT, sort);
        this.addSession(SessionContant.SEARCH_KEY, key);
        this.addSession(SessionContant.PAGEBEAN, pageBean);
        this.addSession(SessionContant.IS_QUERY, false);
        modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX));
        return modelAndView;
    }

    @RequestMapping(value = ServletConstant.QUESTION_CREATE_MAPPING, method = RequestMethod.POST)
    public ModelAndView createPost(Question question, @RequestParam(value = "token", defaultValue = "") String clientToken) {
        ModelAndView modelAndView = new ModelAndView();
        String serverToken = (String) this.getSession(clientToken);
        if (EncryptUtil.isRepeatCommit(serverToken, clientToken)) {
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.RESUBMIT_FORM_ERROR_MESSAGE, JsonCodeConstant.RESUBMIT_FORM_ERROR_CODE));
        }
        this.removeSession(clientToken);
        questionService.checkQuestionCodeIdisExist(question.getQuestionCodeId());
        question.setAnswer(QuestionUtil.getAnswer(question));
        questionService.insert(question);
        this.addSession(ServletConstant.CREATE_QUESTION_RESULT, JsonCodeConstant.SUCCESS);
        modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_DASHBOARD_CREATE));
        return modelAndView;
    }


    @RequestMapping(value = ServletConstant.QUESTION_QUERY_MAPPING, method = RequestMethod.GET)
    public ModelAndView query (@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "sort", defaultValue = "DESC") String sort, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        this.removeSession(SessionContant.SEARCH_KEY);
        int curPage = 0;
        try {
            curPage = Integer.parseInt(page);
        } catch (Exception e) {
            logger.error("parse page error");
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE));
        }

        if (StringUtil.isEmpty(sort)) {
            if(this.getSession(SessionContant.SORT) != null) {
                sort = (String) this.getSession(SessionContant.SORT);
            }
        }

        if (!sort.equalsIgnoreCase("DESC") && !sort.equalsIgnoreCase("ASC")) {
            return new ModelAndView(new MappingJacksonJsonView(), JsonUtil.toMap(JsonCodeConstant.ERROR, JsonCodeConstant.PARAMETER_ERROR_MESSAGE, JsonCodeConstant.PARAMETER_ERROR_CODE));
        }

        if (this.getSession(SessionContant.SELECT_TEXT) != null) {
            pageSize = (Integer) this.getSession(SessionContant.SELECT_TEXT);
        }

        PageBean<Question> pageBean = null;
        pageBean = questionService.queryBypage(sort, curPage, pageSize);
        this.addSession(SessionContant.PAGEBEAN, pageBean);
        this.addSession(SessionContant.SORT, sort);
        this.addSession(SessionContant.IS_QUERY, true);
        modelAndView.setView(this.getRedirectView(ServletConstant.REDIRECT_TO_DASHBOARD_INDEX));
        return modelAndView;
    }
}
