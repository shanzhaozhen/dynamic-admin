package org.shanzhaozhen.dynamicadmin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shanzhaozhen.dynamicadmin.common.sys.JwtErrorConst;
import org.shanzhaozhen.dynamicadmin.common.sys.ResultType;

import java.util.function.Function;
import java.util.function.Supplier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "API公共返回对象")
@Log4j2
public class ResultObject<T> {

    @ApiModelProperty(value = "业务状态码", name = "code")
    private Integer code;

    @ApiModelProperty(value = "返回的信息", name = "message")
    private String message;

    @ApiModelProperty(value = "返回的数据", name = "data")
    private T data;

    @ApiModelProperty(value = "请求完成的时间", name = "timestamp")
    private long timestamp = System.currentTimeMillis();

    public ResultObject(Integer code) {
        this.code = code;
    }

    public ResultObject(String msg) {
        this.message = msg;
    }

    public ResultObject(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResultObject(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultObject(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResultObject(JwtErrorConst jwtErrorConst) {
        this.code = jwtErrorConst.getCode();
        this.message = jwtErrorConst.getReason();
    }

    public static <T> ResultObject<T> getResultObject(Supplier<T> s) {
        ResultObject<T> result = new ResultObject<>();
        T data = s.get();
        result.setData(data);
        result.setCode(ResultType.SUCCESS);
        return result;
    }


    public static <T> ResultObject<T> getResultObject(Function<ResultObject<T>, T> s) {
        ResultObject<T> result = new ResultObject<>();
        T data = s.apply(result);
        if (result.getMessage() == null) {
            result.setData(data);
            result.setCode(ResultType.SUCCESS);
        }
        return result;
    }

}
