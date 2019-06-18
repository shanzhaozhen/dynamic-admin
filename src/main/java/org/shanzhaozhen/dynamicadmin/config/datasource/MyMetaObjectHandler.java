package org.shanzhaozhen.dynamicadmin.config.datasource;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.shanzhaozhen.dynamicadmin.utils.UserDetailsUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Integer userId = UserDetailsUtils.getSysUserId();
        this.setInsertFieldValByName("createdDate", new Date(), metaObject);
        this.setInsertFieldValByName("createBy", userId, metaObject);
        this.setInsertFieldValByName("lastModifiedDate", new Date(), metaObject);
        this.setInsertFieldValByName("lastModifiedBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Integer userId = UserDetailsUtils.getSysUserId();
        this.setUpdateFieldValByName("lastModifiedDate", new Date(), metaObject);
        this.setUpdateFieldValByName("lastModifiedBy", userId, metaObject);
    }
}
