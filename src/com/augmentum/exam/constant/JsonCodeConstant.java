package com.augmentum.exam.constant;

public class JsonCodeConstant {
    //question
    public static final String QUESTION_QUERY_BY_PAGE_ERROR_CODE = "1000";
    public static final String QUESTION_QUERY_BY_PAGE_ERROR_MESSAGE = "query by page no data";
    public static final String QUESTION_QUERY_BY_ONE_ERROR_CODE = "1001";
    public static final String QUESTION_QUERY_BY_ONE_ERROR_MESSAGE = "query by one no data";
    public static final String QUESTION_INSERT_ERROR_CODE = "1002";
    public static final String QUESTION_INSERT_ERROR_MESSAGE = "insert error";
    public static final String QUESTION_UPDATE_ERROR_CODE = "1003";
    public static final String QUESTION_UPDATE_ERROR_MESSAGE = "update error";
    public static final String QUESTION_DELETE_ERROR_CODE = "1004";
    public static final String QUESTION_DELETE_ERROR_MESSAGE = "delete error";
    public static final String QUESTION_QUERY_BY_FUZZY_ERROR_CODE = "1005";
    public static final String QUESTION_QUERY_BY_FUZZY_ERROR_MESSAGE = "query by fuzzy no data";
    public static final String QUESTION_DELETE_BY_BATCH_ERROR_CODE = "1006";
    public static final String QUESTION_DELETE_BY_BATCH_ERROR_MESSAGE = "delete by batch error";

    //user
    public static final String USER_LOGIN_ERROR_CODE = "1007";
    public static final String USER_LOGIN_ERROR_MESSAGE = "password wrong";
    public static final String USER_NO_REGISTER_CODE = "1008";
    public static final String USER_NO_REGISTER_MESSAGE = "User no register";
    public static final String TIP_MESSAGE = "TIP_MESSAGE";
    public static final String ERROR_FIELDS = "ERROR_FIELDS";
    public static final String USER = "USER";
    public static final String USER_ERROR_USERNAME = "ERROR_USERNAME";
    public static final String USER_ERROR_PASSWORD = "ERROR_PASSWORD";
    public static final String USER_NAME_IS_NULL_MESSAGE = "User Name is required";
    public static final String USER_PWD_IS_NULL_MESSAGE = "password is required";
    public static final String USER_LOGOUT_SUCCESS_MESSAGE = "log out success";
    public static final String USER_LOGIN_PARAMETER_ERROR_CODE = "1009";

    //success and error
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    //resubmmit form error
    public static final String RESUBMIT_FORM_ERROR_CODE = "9999";
    public static final String RESUBMIT_FORM_ERROR_MESSAGE = "operation frequently";


    //global
    public static final String PARAMETER_ERROR_CODE = "1010";
    public static final String PARAMETER_ERROR_MESSAGE = "parameter error";

    //pagesize
    public static final String PAGE_SIZE = "pageSize";

    //return json fields
    public static final String RETURN_JSON_RESULT = "result";
    public static final String RETURN_JSON_ERRORCODE = "errorCode";
    public static final String RETURN_JSON_DATA = "data";

    //mybatis exception
    public static final String DAO_MYBATIS_EXCEPTION_CODE = "9998";
    public static final String DAO_MYBATIS_EXCEPTION_MESSAGE = "mysql error";

    //questionCodeId repeat
    public static final String QUESTION_CODEID_REPEAT_ERROR_CODE = "9997";
    public static final String QUESTION_CODEID_REPEAT_ERROR_MESSAGE = "question code id repeat";
}
