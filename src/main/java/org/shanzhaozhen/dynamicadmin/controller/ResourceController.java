package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.ResourceConverter;
import org.shanzhaozhen.dynamicadmin.form.ResourceForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public ResultObject addResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultUtils.success(resourceService.addResource(ResourceConverter.formToDTO(resourceForm)));
    }

    @PutMapping("/resource")
    public ResultObject updateResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultUtils.success(resourceService.updateResource(ResourceConverter.formToDTO(resourceForm)));
    }

    @DeleteMapping("/resource/{resourceId}")
    public ResultObject updateResource(@PathVariable("resourceId") Long resourceId) {
        return ResultUtils.defaultResult(resourceService.deleteResource(resourceId));
    }

}
