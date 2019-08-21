package org.shanzhaozhen.dynamicadmin.form;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RoleForm {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotEmpty(message = "标识名称不能为空")
    @ApiModelProperty(value = "标识名称")
    private String identification;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "关联的资源id")
    private List<Long> resourceIds;

}
