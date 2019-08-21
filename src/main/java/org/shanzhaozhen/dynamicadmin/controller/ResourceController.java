package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDO;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resource/tree")
    public ResultObject getAllResourceTree() {
        return ResultUtils.success(resourceService.getAllResourceTree());
    }

    @GetMapping("/resource/{resourceId}")
    public ResultObject getResourceByResourceId(@PathVariable("resourceId") Long resourceId) {
        return ResultUtils.success(resourceService.getResourceById(resourceId));
    }

    @PostMapping("/resource")
    public ResultObject addResource(@RequestBody ResourceDO resourceDO) {
        return ResultUtils.success("添加成功", resourceService.addResource(resourceDO));
    }

    @PutMapping("/resource")
    public ResultObject updateResource(@RequestBody ResourceDO resourceDO) {
        return ResultUtils.success("修改成功", resourceService.updateResource(resourceDO));
    }

    @DeleteMapping("/resource/{resourceId}")
    public ResultObject updateResource(@PathVariable("resourceId") Long resourceId) {
        return resourceService.deleteResource(resourceId) ? ResultUtils.success("删除成功") : ResultUtils.failure("删除失败");
    }

}
