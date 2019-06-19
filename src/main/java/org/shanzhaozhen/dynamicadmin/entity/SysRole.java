package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 6203528166202612882L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Set<SysUser> sysUsers;

    private Set<SysPermission> sysPermissions;

    private List<Integer> permissionIds;

}
