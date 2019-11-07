package org.shanzhaozhen.dynamicadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Department对象", description="")
public class DepartmentDTO {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

}
