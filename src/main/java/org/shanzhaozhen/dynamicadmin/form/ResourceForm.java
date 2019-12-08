package org.shanzhaozhen.dynamicadmin.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ResourceForm对象", description="")
public class ResourceForm {

    @ApiModelProperty(value = "主键ID")
    @NotEmpty(groups = {Update.class}, message = "资源id不能为空")
    private Long id;

    @ApiModelProperty(value = "权限名称")
    @NotEmpty(groups = {Insert.class, Update.class}, message = "资源名称不能为空")
    private String name;

    @ApiModelProperty(value = "资源路由")
    private String path;

    @ApiModelProperty(value = "资源类型")
    private Integer type;

    @ApiModelProperty(value = "资源描述")
    private String description;

}
