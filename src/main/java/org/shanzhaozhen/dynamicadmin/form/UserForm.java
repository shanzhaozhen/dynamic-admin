package org.shanzhaozhen.dynamicadmin.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户Form实体")
public class UserForm {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 20, message = "用户名长度为4-20位")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 25, message = "密码长度为6-25位")
    private String password;

}
