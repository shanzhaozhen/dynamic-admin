package org.shanzhaozhen.dynamicadmin.form;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "基础分页列表查询前端传入参数")
public class BaseSearchForm {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();

    /**
     * 生成mybatis的分页实体
     * @param baseSearchForm
     * @return
     */
    public static Page getPage(BaseSearchForm baseSearchForm) {
        Page page = new Page(baseSearchForm.getCurrent(), baseSearchForm.getSize());
        page.setOrders(baseSearchForm.getOrders());
        return page;
    }

}
