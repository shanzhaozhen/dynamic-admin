package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.RouteConverter;
import org.shanzhaozhen.dynamicadmin.form.RouteForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.RouteService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.vo.RouteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/route/tree")
    public ResultObject<List<RouteVO>> getAllRouteTree() {
        return ResultUtils.success(RouteConverter.dtoToVO(routeService.getAllRouteTree()));
    }

    @GetMapping("/route/{routeId}")
    public ResultObject<RouteVO> getRouteByRouteId(@PathVariable("routeId") Long routeId) {
        return ResultUtils.success(RouteConverter.dtoToVO(routeService.getRouteById(routeId)));
    }

    @PostMapping("/route")
    public ResultObject<RouteVO> addRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultUtils.success(RouteConverter.dtoToVO(routeService.addRoute(RouteConverter.formToDTO(routeForm))));
    }

    @PutMapping("/route")
    public ResultObject<RouteVO> updateRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultUtils.success(RouteConverter.dtoToVO(routeService.updateRoute(RouteConverter.formToDTO(routeForm))));
    }

    @DeleteMapping("/route/{routeId}")
    public ResultObject<?> updateRoute(@PathVariable("routeId") Long routeId) {
        return ResultUtils.defaultResult(routeService.deleteRoute(routeId));
    }

}
