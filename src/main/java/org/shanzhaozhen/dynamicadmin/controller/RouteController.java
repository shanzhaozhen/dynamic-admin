package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.RouteConverter;
import org.shanzhaozhen.dynamicadmin.form.RouteForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.RouteService;
import org.shanzhaozhen.dynamicadmin.vo.RouteVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {

    private final String GET_ALL_ROUTE_TREE = "/route/tree";
    private final String GET_ROUTE_BY_ID = "/route/{routeId}";
    private final String ADD_ROUTE = "/route";
    private final String UPDATE_ROUTE = "/route";
    private final String DELETE_ROUTE = "/route/{routeId}";

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(GET_ALL_ROUTE_TREE)
    public ResultObject<List<RouteVO>> getAllRouteTree() {
        return ResultObject.getResultObject(result -> RouteConverter.toVO(routeService.getAllRouteTree()));
    }

    @GetMapping(GET_ROUTE_BY_ID)
    public ResultObject<RouteVO> getRouteByRouteId(@PathVariable("routeId") Long routeId) {
        return ResultObject.getResultObject(result -> RouteConverter.toVO(routeService.getRouteById(routeId)));
    }

    @PostMapping(ADD_ROUTE)
    public ResultObject<RouteVO> addRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultObject.getResultObject(result -> RouteConverter.toVO(routeService.addRoute(RouteConverter.toDTO(routeForm))));
    }

    @PutMapping(UPDATE_ROUTE)
    public ResultObject<RouteVO> updateRoute(@RequestBody @Validated RouteForm routeForm) {
        return ResultObject.getResultObject(result -> RouteConverter.toVO(routeService.updateRoute(RouteConverter.toDTO(routeForm))));
    }

    @DeleteMapping(DELETE_ROUTE)
    public ResultObject<Boolean> deleteRoute(@PathVariable("routeId") Long routeId) {
        return ResultObject.getResultObject(result -> routeService.deleteRoute(routeId));
    }

}
