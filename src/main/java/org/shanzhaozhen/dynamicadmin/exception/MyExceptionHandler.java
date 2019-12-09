package org.shanzhaozhen.dynamicadmin.exception;

import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ResultException.class)
    public ResultObject handleSuccessException(ResultException e) {
        return ResultUtils.defaultResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ResultErrorException.class)
    public void handleErrorException(HttpServletResponse httpServletResponse, ResultErrorException e) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    /**
     * 断言事件监听
     * @param httpServletResponse
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(HttpServletResponse httpServletResponse, IllegalArgumentException e) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    /**
     * 监听表单验证错误信息
     * @param e 数据校验事件
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultObject handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResultUtils.failure(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

}
