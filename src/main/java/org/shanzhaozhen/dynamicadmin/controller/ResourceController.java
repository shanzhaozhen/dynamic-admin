package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.common.sys.ResourceType;
import org.shanzhaozhen.dynamicadmin.converter.ResourceConverter;
import org.shanzhaozhen.dynamicadmin.form.ResourceForm;
import org.shanzhaozhen.dynamicadmin.service.ResourceService;
import org.shanzhaozhen.dynamicadmin.vo.ResourceVO;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    private final String GET_ALL_RESOURCE_TREE = "/resource/tree";
    private final String GET_ALL_RESOURCE_ROOT_TREE = "/resource/root-tree";
    private final String GET_RESOURCE_BY_ID = "/resource/{resourceId}";
    private final String ADD_RESOURCE = "/resource";
    private final String UPDATE_RESOURCE = "/resource";
    private final String DELETE_RESOURCE = "/resource/{resourceId}";

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping(GET_ALL_RESOURCE_TREE)
    public ResultObject<List<ResourceVO>> getAllResourceTree() {
        return ResultObject.getResultObject(result -> ResourceConverter.toVO(resourceService.getAllResourceTreeByType(null)));
    }

    @GetMapping(GET_ALL_RESOURCE_ROOT_TREE)
    public ResultObject<List<ResourceVO>> getAllResourceRootTree() {
        return ResultObject.getResultObject(result -> ResourceConverter.toVO(resourceService.getAllResourceTreeByType(ResourceType.KID.getValue())));
    }


    @GetMapping(GET_RESOURCE_BY_ID)
    public ResultObject<ResourceVO> getResourceByResourceId(@PathVariable("resourceId") Long resourceId) {
        return ResultObject.getResultObject(result -> ResourceConverter.toVO(resourceService.getResourceById(resourceId)));
    }

    @PostMapping(ADD_RESOURCE)
    public ResultObject<ResourceVO> addResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultObject.getResultObject(result -> ResourceConverter.toVO(resourceService.addResource(ResourceConverter.toDTO(resourceForm))));
    }

    @PutMapping(UPDATE_RESOURCE)
    public ResultObject<ResourceVO> updateResource(@RequestBody @Validated ResourceForm resourceForm) {
        return ResultObject.getResultObject(result -> ResourceConverter.toVO(resourceService.updateResource(ResourceConverter.toDTO(resourceForm))));
    }

    @DeleteMapping(DELETE_RESOURCE)
    public ResultObject<Boolean> deleteResource(@PathVariable("resourceId") Long resourceId) {
        return ResultObject.getResultObject(result -> resourceService.deleteResource(resourceId));
    }

}
