package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_resource")
@ApiModel(value="SysResource对象", description="")
public class SysResource extends BaseEntity {

    private static final long serialVersionUID = 4485640590947953262L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "权限类型")
    private Integer type;

    @ApiModelProperty(value = "权限路由")
    private String path;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序等级")
    private Integer priority;

    @ApiModelProperty(value = "菜单是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单是否总是显示")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "是否需要缓存")
    private Boolean noCache;

    @ApiModelProperty(value = "固钉")
    private Boolean affix;

    @ApiModelProperty(value = "面包屑")
    private Boolean breadcrumb;

    @TableField(exist = false)
    private List<SysRole> roles;

    @TableField(exist = false)
    private List<SysResource> children;

//
//    @TableField(exist = false)
//    private List<String> roles;

}
