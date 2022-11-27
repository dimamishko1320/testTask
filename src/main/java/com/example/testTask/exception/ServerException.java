package com.example.testTask.exception;



public class ServerException extends Exception{
    private ErrorCode errorCode;
    private String message;

    public ServerException(ErrorCode code) {
        this.errorCode = code;
    }

    public ServerException(ErrorCode code, String message){
        this.errorCode=code;
        this.message=message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
