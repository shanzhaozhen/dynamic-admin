package org.shanzhaozhen.dynamicadmin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="用户信息", description="")
public class UserInfo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "个人简介")
    private String introduction;

    @ApiModelProperty(value = "角色")
    private List<String> roles;

    @ApiModelProperty(value = "菜单")
    private List<AsyncRoute> menus;

}
