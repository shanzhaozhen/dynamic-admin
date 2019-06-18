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
public class SysDictionary extends BaseEntity {

    private static final long serialVersionUID = -4727379501712632270L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer pid;

}
