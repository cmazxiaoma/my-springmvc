package com.augmentum.exam.model;

public enum QuestionEnum {
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private String value;

    public String getValue() {
        return value;
    }

    private QuestionEnum(String value) {
        this.value = value;
    }
}
