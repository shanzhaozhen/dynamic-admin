package org.shanzhaozhen.dynamicadmin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private static final long serialVersionUID = -7305968647134811297L;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createdDate;

    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.UPDATE)
    private Date lastModifiedDate;

    @TableField(fill = FieldFill.UPDATE)
    private Integer lastModifiedBy;

}
