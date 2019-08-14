package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.exception.ResultErrorException;
import org.shanzhaozhen.dynamicadmin.exception.ResultException;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ResultException.class)
    public ResultObject handleSuccessException(ResultException e) {
        return ResultObject.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ResultErrorException.class)
    public void handleErrorException(HttpServletResponse httpServletResponse, ResultErrorException e) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(HttpServletResponse httpServletResponse, IllegalArgumentException e) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

}
