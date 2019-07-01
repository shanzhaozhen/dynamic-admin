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

    private Integer code;

    private boolean success;

    private String msg;

    private Object data;

    public ResultParam(String msg) {
        this.msg = msg;
    }

    public ResultParam(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResultParam(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
}
