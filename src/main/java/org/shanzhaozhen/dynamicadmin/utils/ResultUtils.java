package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.param.ResultParam;

public class ResultUtils {

    public static ResultParam success() {
        return ResultParam.builder()
                .code(0)
                .message("success")
                .build();
    }

    public static ResultParam success(Object data) {
        return ResultParam.builder()
                .code(0)
                .message("success")
                .data(data)
                .build();
    }

    public static ResultParam success(String message) {
        return ResultParam.builder()
                .code(0)
                .message(message)
                .build();
    }

    public static ResultParam success(int code, String message) {
        return ResultParam.builder()
                .code(code)
                .message(message)
                .build();
    }

    public static ResultParam success(int code, String message, Object data) {
        return ResultParam.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
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
