package com.example.testTask.exception;

public enum  ErrorCode {
    INVALID_ACCOUNT_ID_ERROR;

    private String errorString;

    String getErrorString() {
        return errorString;
    }

    void setErrorString(String errorString) {
        this.errorString = errorString;
    }


}
