package org.shanzhaozhen.dynamicadmin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Meta对象", description="用于菜单的标题、图标、全局权限的存放")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {

    private String title;

    private String icon;

    @ApiModelProperty(value = "是否需要缓存")
    private Boolean noCache;

    @ApiModelProperty(value = "固钉")
    private Boolean affix;

    @ApiModelProperty(value = "是否打开面包屑")
    private Boolean breadcrumb;

    private List<String> roles;

}
