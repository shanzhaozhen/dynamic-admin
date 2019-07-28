package org.shanzhaozhen.dynamicadmin.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultParam {

    private int code;

    private String message;

    private Object data;

    public ResultParam(String msg) {
        this.message = msg;
    }

    public ResultParam(boolean success, String msg) {
        this.message = msg;
    }

    public ResultParam(boolean success, String msg, Object data) {
        this.message = msg;
        this.data = data;
    }
}
