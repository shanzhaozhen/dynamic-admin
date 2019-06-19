package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = -8389872342618587940L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer userId;

    private Integer roleId;

}
