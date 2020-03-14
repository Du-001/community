package com.community.exception.emuns;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在，换一个试试");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
