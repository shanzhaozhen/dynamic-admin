package org.shanzhaozhen.dynamicadmin.utils;

import com.alibaba.fastjson.JSONObject;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletResponseUtils {

    public static void resultJson(HttpServletResponse httpServletResponse, Integer status, String content) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        if (status != null) {
            httpServletResponse.setStatus(status);
        }
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(content);
    }


    public static void resultJson(HttpServletResponse httpServletResponse, Integer status, ResultObject<?> resultObject) throws IOException {
        resultJson(httpServletResponse, status, JSONObject.toJSONString(resultObject));
    }

}
