package com.rt.cms.web.controller.system;

import com.rt.cms.common.base.ResultCodeEnum;
import com.rt.cms.model.system.User;
import com.rt.cms.service.system.OrganizationService;
import com.rt.cms.service.system.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.rt.cms.common.Constants;
import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.common.base.Result;
import com.rt.cms.service.system.UserService;
import com.rt.cms.annotation.SystemLog;
import com.rt.cms.web.controller.base.BaseController;
import com.rt.cms.web.dto.system.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("user:view")
    public String toPage(Model model) {
        setCommonData(model);
        return "system/user";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("user:view")
    public PageResultSet<UserDto> list(Page page) {
        return userService.findByPage(page);
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @RequiresPermissions("user:create")
    @SystemLog("用户管理创建用户")
    public Result create(@Valid User user) {
        userService.createUser(user);
        return Result.Success();
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("user:update")
    @SystemLog("用户管理更新用户")
    public Result update(User user) {
        userService.updateUser(user);
        return Result.Success();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("user:delete")
    @SystemLog("用户管理删除用户")
    public Result delete(@RequestParam("id") Long[] ids, HttpServletRequest request) {
        // 当前用户
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        boolean isSelf = Arrays.stream(ids).anyMatch(id -> id.equals(user.getId()));
        if (isSelf) {
            return Result.Failure(ResultCodeEnum.FailedDelOwn);
        }
        Arrays.asList(ids).forEach(id -> userService.deleteUser(id));
        return Result.Success();
    }

    @ResponseBody
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/change/password", method = RequestMethod.POST)
    @SystemLog("用户管理更改用户密码")
    public Result changePassword(@PathVariable("id") Long id, String newPassword) {
        userService.changePassword(id, newPassword);
        return Result.Success();
    }

    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }

}
