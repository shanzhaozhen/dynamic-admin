package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 4485640590947953262L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String permissionName;

    private String description;

    private Integer type;

    private String url;

    private Integer pid;

    private String icon;

    private Integer priority;

    private Set<SysRole> sysRoles;

    private List<SysPermission> children;

}
