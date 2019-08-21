package org.shanzhaozhen.dynamicadmin.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_resource")
@ApiModel(value="RoleResource对象", description="")
public class RoleResourceDO extends BaseEntity {

    private static final long serialVersionUID = 1642473344166749722L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    private Long resourceId;

    public RoleResourceDO(Long roleId, Long resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
