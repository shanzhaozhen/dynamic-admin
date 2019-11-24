package org.shanzhaozhen.dynamicadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Role对象", description="")
public class RoleDTO {

    private static final long serialVersionUID = 1274983152798091196L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标识名称")
    private String identification;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "关联的资源")
    private List<ResourceDTO> resourceDTOList;

    @ApiModelProperty(value = "关联的资源id")
    private List<Long> resourceIds;

}