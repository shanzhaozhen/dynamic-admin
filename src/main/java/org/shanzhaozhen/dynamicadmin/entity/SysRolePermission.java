package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1642473344166749722L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}
