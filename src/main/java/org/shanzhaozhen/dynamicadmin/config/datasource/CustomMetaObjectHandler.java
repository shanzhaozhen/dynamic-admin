package org.shanzhaozhen.dynamicadmin.config.datasource;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = UserDetailsUtils.getUserId();
        this.setFieldValByName("createdDate", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("lastModifiedDate", new Date(), metaObject);
        this.setFieldValByName("lastModifiedBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = UserDetailsUtils.getUserId();
        this.setFieldValByName("lastModifiedDate", new Date(), metaObject);
        this.setFieldValByName("lastModifiedBy", userId, metaObject);
    }
}
