package org.shanzhaozhen.dynamicadmin.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shanzhaozhen.dynamicadmin.common.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "API公共返回对象")
public class ResultObject<T> {

    private static final Logger logger = LoggerFactory.getLogger(ResultObject.class);


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

    public ResultObject(Integer code, String msg, T data) {
        this.message = msg;
        this.data = data;
    }

    public static <T> ResultObject<T> getResultObject(Supplier<T> s) {
        ResultObject<T> result = new ResultObject<>();
        try {
            T t = s.get();
            result.setData(t);
            result.setCode(ResultType.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setCode(ResultType.FAILURE);
        }

        return result;
    }


    public static <T> ResultObject<T> getResultObject(Function<ResultObject<T>, T> s) {
        ResultObject<T> result = new ResultObject<>();
        try {
            T r = s.apply(result);
            if (result.getMessage() == null) {
                result.setData(r);
                result.setCode(ResultType.SUCCESS);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setCode(ResultType.FAILURE);
        }

        return result;
    }

}
