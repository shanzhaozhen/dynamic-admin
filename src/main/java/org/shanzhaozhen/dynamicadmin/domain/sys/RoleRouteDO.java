package org.shanzhaozhen.dynamicadmin.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.shanzhaozhen.dynamicadmin.domain.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_route")
@ApiModel(description = "角色-路由关系DO实体")
public class RoleRouteDO extends BaseEntity {

    private static final long serialVersionUID = 1642473344166749722L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "路由ID")
    private Long routeId;

}
