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
@TableName("sys_role")
@ApiModel(value="Role对象", description="")
public class RoleDO extends BaseEntity {

    private static final long serialVersionUID = 6203528166202612882L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标识名称")
    private String identification;

    @ApiModelProperty(value = "描述")
    private String description;

}
