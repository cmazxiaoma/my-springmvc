package com.augmentum.exam.constant;


public class ServletConstant {
    //controller requestMapping
    public static final String PAGE_USER_MAPPING = "/page/user";
    public static final String PAGE_QUESTION_MAPPING = "/page/question";
    public static final String PAGE_JSON_MAPPING = "/page/json";

    //action requestMapping
    public static final String QUESTION_DEL_MAPPING = "/del";
    public static final String QUESTION_CREATE_MAPPING = "/create";
    public static final String QUESTION_QUERY_MAPPING = "/query";
    public static final String QUESTION_SEARCH_MAPPING = "/search";
    public static final String QUESTION_EDIT_MAPPING = "/edit";
    public static final String QUESTION_INDEX_MAPPING = "/index";
    public static final String QUESTION_PAGESIZE_MAPPING = "/pagesize";
    public static final String USER_LOGIN_MAPPING = "/login";
    public static final String USER_LOGOUT_MAPPING = "/logout";
    public static final String QUESTION_DETAIL_MAPPING = "/detail";

    //redirect to action requestMapping
    public static final String REDIRECT_TO_QUESTION_QUERY = "question/query";
    public static final String REDIRECT_TO_USER_LOGIN = "user/login";
    public static final String REDIRECT_TO_QUESTION_SEARCH = "question/search";

    //redirect to dashboard
    public static final String REDIRECT_TO_DASHBOARD_INDEX = "dashboard/index";
    public static final String REDIRECT_TO_DASHBOARD_CREATE = "dashboard/create";
    public static final String REDIRECT_TO_DASHBOARD_EDIT = "dashboard/edit";

    //view
    public static final String QUESTION_INDEX_PAGE = "home/index";
    public static final String QUESTION_CREATE_PAGE = "question/create";
    public static final String QUESTION_EDIT_PAGE = "question/edit";
    public static final String QUESTION_DETAIL_PAGE = "question/detail";
    public static final String USER_LOGIN_PAGE = "login";
    public static final String NO_FOUND_PAGE = "static/404.html";
    public static final String DATA_ERROR_PAGE = "static/data_error.html";
    public static final String VICIOUS_OPERATION_PAGE = "error/error";

    //AppContext
    public static final String APP_CONTEXT_REQUEST = "APP_CONTEXT_REQUEST";
    public static final String APP_CONTEXT_RESPONSE = "APP_CONTEXT_RESPONSE";
    public static final String APP_CONTEXT_THREAD_CONNECTION = "APP_CONTEXT_THREAD_CONNECTION";
    public static final String APP_CONTEXT_USER = "APP_CONTEXT_USER";
    public static final String APP_CONTEXT_SESSION = "APP_CONTEXT_SESSION";

    //requestMapping prefix
    public static final String APP_URL_PREFIX = "page";

    //CRUD result
    public static final String EDIT_QUESTION_RESULT = "editResult";
    public static final String CREATE_QUESTION_RESULT = "createResult";
    public static final String DEL_QUESTION_RESULT = "delResult";

    //ajax check question code id
    public static final String CHECK_QUESTION_CODE_ID = "checkCodeId";
}
