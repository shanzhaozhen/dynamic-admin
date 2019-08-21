package org.shanzhaozhen.dynamicadmin.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

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

    @ApiModelProperty(value = "关联的资源")
    @TableField(exist = false)
    private List<ResourceDO> resourceDOList;

    @ApiModelProperty(value = "关联的资源id")
    @TableField(exist = false)
    private List<Long> resourceIds;

    public List<Long> getResourceIds() {
        List<Long> resourceIds = new ArrayList<>();
        if (this.resourceDOList == null) {
            return resourceIds;
        }
        for (ResourceDO resourceDO : resourceDOList) {
            resourceIds.add(resourceDO.getId());
        }
        return resourceIds;
    }

}
