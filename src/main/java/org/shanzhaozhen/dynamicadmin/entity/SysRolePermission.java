package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1642473344166749722L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer roleId;

    private Integer permissionId;

}
