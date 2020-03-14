package com.community.exception;

import com.community.exception.emuns.ICustomizeErrorCode;
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        this.message = iCustomizeErrorCode.getMessage();
    }
}
