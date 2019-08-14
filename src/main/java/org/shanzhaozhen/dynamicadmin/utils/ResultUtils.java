package org.shanzhaozhen.dynamicadmin.utils;

import org.shanzhaozhen.dynamicadmin.param.ResultObject;

public class ResultUtils {

    public static ResultObject success() {
        return new ResultObject(0, "success");
    }

    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<>(0, "success", data);
    }

    public static ResultObject success(String message) {
        return new ResultObject(0, message);
    }

    public static <T> ResultObject<T> success(String message, T data) {
        return new ResultObject<>(0, message, data);
    }

    public static ResultObject success(Integer code, String message) {
        return new ResultObject(code, message);
    }

    public static <T> ResultObject<T> success(Integer code, String message, T data) {
        return new ResultObject<>(code, message, data);
    }

    public static ResultObject failure() {
        return new ResultObject(-1, "failure");
    }

    public static ResultObject failure(String message) {
        return new ResultObject(-1, message);
    }

    public static ResultObject failure(Integer code, String message) {
        return new ResultObject(code, message);
    }

    public static ResultObject resultCode(Integer code) {
        return new ResultObject(code);
    }


}
