package org.shanzhaozhen.dynamicadmin.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_department")
@ApiModel(value="Department对象", description="")
public class DepartmentDO extends BaseEntity {

    private static final long serialVersionUID = -4727379501712632270L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

}
