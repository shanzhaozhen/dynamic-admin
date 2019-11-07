package org.shanzhaozhen.dynamicadmin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shanzhaozhen.dynamicadmin.param.Meta;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AsyncRoute对象", description="用于前端菜单的存放")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsyncRoute {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "菜单路由")
    private String path;

    @ApiModelProperty(value = "菜单是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单是否总是显示")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "菜单是否总是显示")
    private Meta meta;

    @ApiModelProperty(value = "父级id")
    private Long pid;

    @ApiModelProperty(value = "排序等级")
    private Integer priority;

    @ApiModelProperty(value = "下级菜单")
    private List<AsyncRoute> children;

}
