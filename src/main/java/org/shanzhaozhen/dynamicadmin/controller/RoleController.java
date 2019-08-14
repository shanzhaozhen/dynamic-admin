package org.shanzhaozhen.dynamicadmin.controller;

import org.shanzhaozhen.dynamicadmin.entity.sys.RoleDo;
import org.shanzhaozhen.dynamicadmin.form.BaseSearchForm;
import org.shanzhaozhen.dynamicadmin.param.ResultObject;
import org.shanzhaozhen.dynamicadmin.service.RoleService;
import org.shanzhaozhen.dynamicadmin.utils.ResultUtils;
import org.shanzhaozhen.dynamicadmin.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResultUtils.success(roleService.getRoleById(roleId));
    }

    @PostMapping("/role")
    public ResultObject addSysRole(@RequestBody RoleVo roleVo) {
        return ResultUtils.success("添加成功", roleService.addRole(roleVo));
    }

    @PutMapping("/role")
    public ResultObject updateSysRole(@RequestBody RoleVo roleVo) {
        return ResultUtils.success("修改成功", roleService.updateRole(roleVo));
    }

    @DeleteMapping("/role/{roleId}")
    public ResultObject updateSysRole(@PathVariable("roleId") Long roleId) {
        return roleService.deleteRole(roleId) ? ResultUtils.success("删除成功") : ResultUtils.failure("删除失败");
    }

}
