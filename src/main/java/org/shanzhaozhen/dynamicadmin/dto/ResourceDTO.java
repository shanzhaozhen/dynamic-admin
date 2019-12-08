package org.shanzhaozhen.dynamicadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="ResourceDTO对象", description="")
public class ResourceDTO extends BaseEntity {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源路由")
    private String path;

    @ApiModelProperty(value = "资源类型")
    private Integer type;

    @ApiModelProperty(value = "资源描述")
    private String description;

    private List<RoleDTO> roles;

    private List<ResourceDTO> children;
}
