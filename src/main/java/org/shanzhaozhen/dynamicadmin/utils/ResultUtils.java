package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.param.ResultParam;

public class ResultUtils {

    public static ResultParam success() {
        return new ResultParam(0, "success");
    }

    public static ResultParam success(Object data) {
        return new ResultParam(0, "success", data);
    }

    public static ResultParam success(String message) {
        return new ResultParam(0, message);
    }

    public static ResultParam success(String message, Object data) {
        return new ResultParam(0, message, data);
    }

    public static ResultParam success(int code, String message) {
        return new ResultParam(code, message);
    }

    public static ResultParam success(int code, String message, Object data) {
        return new ResultParam(code, message, data);
    }

    public static ResultParam failure() {
        return ResultParam.builder()
                .code(-1)
                .message("failure")
                .build();
    }

    public static ResultParam failure(String message) {
        return ResultParam.builder()
                .code(-1)
                .message(message)
                .build();
    }

    public static ResultParam failure(int code, String message) {
        return ResultParam.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ResultParam resultCode(int code) {
        return ResultParam.builder()
                .code(code)
                .build();
    }


}
