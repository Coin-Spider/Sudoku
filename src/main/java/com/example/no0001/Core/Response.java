package com.example.no0001.Core;

public class Response {
    private String ResponseCode;
    private Object ResponseBody;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public Object getResponseBody() {
        return ResponseBody;
    }

    public void setResponseBody(Object responseBody) {
        ResponseBody = responseBody;
    }

    public Response() {
    }

    public Response(String responseCode, Object responseBody) {
        ResponseCode = responseCode;
        ResponseBody = responseBody;
    }
}
