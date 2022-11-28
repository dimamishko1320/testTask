package com.example.testTask.server.exception;



public class ServerException extends Exception{
    private ErrorCode errorCode;
    private String message;

    public ServerException(ErrorCode code) {
        this.errorCode = code;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
