package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.RouteConverter;
import org.shanzhaozhen.dynamicadmin.form.RouteForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.RouteService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/route/tree")
    public ResultObject getAllRouteTree() {
        return ResultUtils.success(routeService.getAllRouteTree());
    }

    @GetMapping("/route/{routeId}")
    public ResultObject getRouteByRouteId(@PathVariable("routeId") Long routeId) {
        return ResultUtils.success(routeService.getRouteById(routeId));
    }

    @PostMapping("/route")
    public ResultObject addRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultUtils.success(routeService.addRoute(RouteConverter.formToDTO(routeForm)));
    }

    @PutMapping("/route")
    public ResultObject updateRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultUtils.success(routeService.updateRoute(RouteConverter.formToDTO(routeForm)));
    }

    @DeleteMapping("/route/{routeId}")
    public ResultObject updateRoute(@PathVariable("routeId") Long routeId) {
        return ResultUtils.defaultResult(routeService.deleteRoute(routeId));
    }

}
