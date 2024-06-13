package com.example.no0001.Core.Except;

public class ApiExcept extends RuntimeException {
    String errorCode;
    String errorMessage;
    public ApiExcept(String errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
    public ApiExcept(ApiError apiError){
        this.errorCode=apiError.getErrorCode();
        this.errorMessage=apiError.getErrorMessage();
    }
}
