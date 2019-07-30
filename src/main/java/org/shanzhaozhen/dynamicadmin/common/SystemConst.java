package org.shanzhaozhen.dynamicadmin.common;

import org.shanzhaozhen.dynamicadmin.param.EnumParam;

import java.util.ArrayList;
import java.util.List;

public class SystemConst {


    public enum RequestMethod {

        GET("查看", "GET"),
        POST("新增", "POST"),
        PUT("修改", "PUT"),
        PATCH("更新", "PATCH"),
        DELETE("删除", "DELETE");

        private String name;
        private String value;

        RequestMethod(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum MenuType {

        PATH("路径" ,0),
        MENU("菜单" ,1),
        API("API" ,2),
        BUTTON("按钮" ,3);

        private String name;

        private Integer value;

        MenuType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public static List<EnumParam> toList() {
            List<EnumParam> list = new ArrayList<>();
            for (MenuType menuType : MenuType.values()) {
                EnumParam enumParam = new EnumParam(menuType.getName(), menuType.getValue());
                list.add(enumParam);
            }
            return list;
        }
    }

    public enum DictionaryType {

        KID("类型" ,0),
        VALUE("值" ,1);

        private String name;

        private Integer value;

        DictionaryType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public static List<EnumParam> toList() {
            List<EnumParam> list = new ArrayList<>();
            for (DictionaryType dictionaryType : DictionaryType.values()) {
                EnumParam enumParam = new EnumParam(dictionaryType.getName(), dictionaryType.getValue());
                list.add(enumParam);
            }
            return list;
        }
    }

    public enum TimeSlot {

        ONEWEEK("近一周"),
        ONEMOON("近一个月"),
        TREEMOON("近三个月"),
        ONEYEAR("近一年"),
        TREEYEAR("近三年"),
        ALL("全部");

        private String name;

        TimeSlot(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
