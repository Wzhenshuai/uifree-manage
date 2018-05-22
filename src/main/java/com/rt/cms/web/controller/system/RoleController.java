package com.rt.cms.web.controller.system;

import com.rt.cms.service.system.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rt.cms.annotation.SystemLog;
import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.common.base.Result;
import com.rt.cms.model.system.Role;
import com.rt.cms.service.system.ResourceService;
import com.rt.cms.web.controller.base.BaseController;
import com.rt.cms.web.dto.system.RoleDto;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("role:view")
    public String toPage(Model model) {
        setCommonData(model);
        return "system/role";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("role:view")
    public PageResultSet<RoleDto> list(Page page) {
        return roleService.findByPage(page);
    }

    @ResponseBody
    @RequiresPermissions("role:create")
    @SystemLog("角色管理创建角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(@Valid Role role) {
        roleService.createRole(role);
        return Result.Success();
    }

    @ResponseBody
    @RequiresPermissions("role:update")
    @SystemLog("角色管理更新角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@Valid Role role) {
        roleService.updateRole(role);
        return Result.Success();
    }

    @ResponseBody
    @RequiresPermissions("role:delete")
    @SystemLog("角色管理删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestParam("id") Long[] ids) {
        Arrays.asList(ids).forEach(id-> roleService.deleteRole(id));
        return Result.Success();
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

}
