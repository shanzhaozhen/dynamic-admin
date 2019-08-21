package org.shanzhaozhen.dynamicadmin.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.shanzhaozhen.dynamicadmin.entity.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dictionary")
@ApiModel(value="Dictionary对象", description="")
public class DictionaryDO extends BaseEntity {

    private static final long serialVersionUID = -4727379501712632270L;

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long pid;

}
