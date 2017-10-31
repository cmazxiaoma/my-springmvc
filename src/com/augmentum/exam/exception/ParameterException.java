package com.augmentum.exam.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception {
    private static final long serialVersionUID = -8536438195192095468L;
    private Map<String, String> errorFields;

    public ParameterException() {
        errorFields = new HashMap<String, String>();
    }

    public Map<String, String> getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(Map<String, String> errorFields) {
        this.errorFields = errorFields;
    }

    public boolean isErrorField() {
        return !errorFields.isEmpty();
    }

    public void addErrorFields(String errorName, String errorMessage) {
        errorFields.put(errorName, errorMessage);
    }
}
