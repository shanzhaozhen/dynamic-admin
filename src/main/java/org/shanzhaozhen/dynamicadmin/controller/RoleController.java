package org.shanzhaozhen.dynamicadmin.controller;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.shanzhaozhen.dynamicadmin.converter.RoleConverter;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.form.RoleForm;
import org.shanzhaozhen.dynamicadmin.service.RoleService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.vo.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role/page")
    public ResultObject getSysRolePage(@RequestBody BaseSearchForm baseSearchForm) {
        return ResultUtils.success(roleService.getRolePage(baseSearchForm));
    }

    @GetMapping("/role/{roleId}")
    public ResultObject getSysRoleByRoleId(@PathVariable("roleId") Long roleId) {
        return ResultUtils.success(RoleConverter.dtoToVO(roleService.getRoleById(roleId)));
    }

    @PostMapping("/role")
    public ResultObject addSysRole(@RequestBody @Validated({Insert.class}) RoleForm roleForm) {
        return ResultUtils.success(roleService.addRole(RoleConverter.formToDTO(roleForm)));
    }

    @PutMapping("/role")
    public ResultObject updateSysRole(@RequestBody @Validated({Update.class}) RoleForm roleForm) {
        return ResultUtils.success(roleService.updateRole(RoleConverter.formToDTO(roleForm)));
    }

    @DeleteMapping("/role/{roleId}")
    public ResultObject updateSysRole(@PathVariable("roleId") Long roleId) {
        return ResultUtils.defaultResult(roleService.deleteRole(roleId));
    }

}
