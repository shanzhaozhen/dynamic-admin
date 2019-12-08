package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.converter.MenuConverter;
import org.shanzhaozhen.dynamicadmin.form.MenuForm;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.MenuService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu/tree")
    public ResultObject getAllMenuTree() {
        return ResultUtils.success(menuService.getAllMenuTree());
    }

    @GetMapping("/menu/{menuId}")
    public ResultObject getMenuByMenuId(@PathVariable("menuId") Long menuId) {
        return ResultUtils.success(menuService.getMenuById(menuId));
    }

    @PostMapping("/menu")
    public ResultObject addMenu(@RequestBody @Validated MenuForm menuForm) {
        return ResultUtils.success(menuService.addMenu(MenuConverter.formToDTO(menuForm)));
    }

    @PutMapping("/menu")
    public ResultObject updateMenu(@RequestBody @Validated MenuForm menuForm) {
        return ResultUtils.success(menuService.updateMenu(MenuConverter.formToDTO(menuForm)));
    }

    @DeleteMapping("/menu/{menuId}")
    public ResultObject updateMenu(@PathVariable("menuId") Long menuId) {
        return ResultUtils.defaultResult(menuService.deleteMenu(menuId));
    }

}
