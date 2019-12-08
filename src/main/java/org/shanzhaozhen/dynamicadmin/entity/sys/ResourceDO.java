package org.shanzhaozhen.dynamicadmin.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_resource")
@ApiModel(value="Resource对象", description="")
public class ResourceDO extends BaseEntity {

    private static final long serialVersionUID = 4485640590947953262L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源路由")
    private String path;

    @ApiModelProperty(value = "资源类型")
    private Integer type;

    @ApiModelProperty(value = "资源描述")
    private String description;



}
