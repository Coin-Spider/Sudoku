package com.example.no0001.Core.Except;

import com.example.no0001.Core.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
@ResponseBody
@Controller
public class ExceptionHandle {
    @ExceptionHandler(ApiExcept.class)
    public final Response handleAllExceptions(ApiExcept ex) {
        return new Response(ex.errorCode,ex.errorMessage);
    }
}
