package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDictionary extends BaseEntity {

    private static final long serialVersionUID = -4727379501712632270L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer pid;

}
