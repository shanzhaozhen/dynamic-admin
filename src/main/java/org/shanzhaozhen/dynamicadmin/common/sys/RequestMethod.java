package org.shanzhaozhen.dynamicadmin.common.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestMethod {

    GET("查看", "GET"),
    POST("新增", "POST"),
    PUT("修改", "PUT"),
    PATCH("更新", "PATCH"),
    DELETE("删除", "DELETE");

    private String name;
    private String value;

}

