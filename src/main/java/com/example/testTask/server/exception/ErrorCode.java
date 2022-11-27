package com.example.testTask.server.exception;

public enum  ErrorCode {
    INVALID_ACCOUNT_ID_ERROR,
    ACCOUNT_NOT_EXIST_ERROR;

    private String errorString;

    String getErrorString() {
        return errorString;
    }

    void setErrorString(String errorString) {
        this.errorString = errorString;
    }


}
