package com.example.no0001.Core.Except;

public enum ApiError {
    //操作失败
    Fail("0001","操作失败"),
    //账号停用
    UserNotAllowed("0003","账号已停用"),
    //登录失败
    LoginFailed("0004","登录失败");
    private final String errorCode;
    private final String errorMessage;
    ApiError(String errorCode, String errorMessage) {
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

}
