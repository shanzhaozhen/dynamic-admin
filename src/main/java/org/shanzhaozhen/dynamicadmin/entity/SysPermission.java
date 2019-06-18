package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 4485640590947953262L;

    @TableId(type = IdType.AUTO)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

}
