package com.augmentum.exam.json;

public class ReturnJson {
    private String result;
    private String errorCode;
    private Object data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ReturnJson(String result, Object data, String errorCode) {
        super();
        this.result = result;
        this.errorCode = errorCode;
        this.data = data;
    }
}
