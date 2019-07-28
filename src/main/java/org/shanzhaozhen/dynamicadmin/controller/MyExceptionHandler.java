package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.exception.ResultException;
import org.shanzhaozhen.dynamicadmin.param.ResultParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ResultException.class)
    public ResultParam handleSuccessException(ResultException e) {
        return ResultParam.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

}
