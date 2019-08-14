package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.sys.ResourceDo;
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
    public ResultObject addResource(@RequestBody ResourceDo resourceDo) {
        return ResultUtils.success("添加成功", resourceService.addResource(resourceDo));
    }

    @PutMapping("/resource")
    public ResultObject updateResource(@RequestBody ResourceDo resourceDo) {
        return ResultUtils.success("修改成功", resourceService.updateResource(resourceDo));
    }

    @DeleteMapping("/resource/{resourceId}")
    public ResultObject updateResource(@PathVariable("resourceId") Long resourceId) {
        return resourceService.deleteResource(resourceId) ? ResultUtils.success("删除成功") : ResultUtils.failure("删除失败");
    }

}
