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
    API("API" ,1),
    BUTTON("按钮" ,2);

    private String name;

    private Integer value;

    public static List<EnumParam> toList() {
        List<EnumParam> list = new ArrayList<>();
        for (ResourceType routeType : ResourceType.values()) {
            EnumParam enumParam = new EnumParam(routeType.getName(), routeType.getValue());
            list.add(enumParam);
        }
        return list;
    }
}
