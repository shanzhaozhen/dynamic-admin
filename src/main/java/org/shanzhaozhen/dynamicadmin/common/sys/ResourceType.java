package org.shanzhaozhen.dynamicadmin.common.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.shanzhaozhen.dynamicadmin.param.EnumParam;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ResourceType {

    PATH("路径" ,0),
    MENU("菜单" ,1),
    API("API" ,2),
    BUTTON("按钮" ,3);

    private String name;

    private Integer value;

    public static List<EnumParam> toList() {
        List<EnumParam> list = new ArrayList<>();
        for (ResourceType menuType : ResourceType.values()) {
            EnumParam enumParam = new EnumParam(menuType.getName(), menuType.getValue());
            list.add(enumParam);
        }
        return list;
    }
}
