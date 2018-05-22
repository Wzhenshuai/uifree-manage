package com.rt.cms.web.controller.system;

import com.rt.cms.common.base.Result;
import com.rt.cms.model.system.OrganizationExample;
import com.rt.cms.service.system.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rt.cms.annotation.SystemLog;
import com.rt.cms.model.system.Organization;
import com.rt.cms.web.controller.base.BaseController;
import com.rt.cms.web.dto.system.TreeDto;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController{

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("organization:view")
    public String toPage(Model model) {
        OrganizationExample example = new OrganizationExample();
        example.setOrderByClause("priority");
        model.addAttribute("organizationList", organizationService.find(example));
        return "system/organization";
    }

    @ResponseBody
    @RequestMapping("/tree")
    public List<TreeDto> findOrgTree(Long pId) {
        return organizationService.findOrgTree(pId);
    }

    @ResponseBody
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "{id}/load", method = RequestMethod.POST)
    public Result load(@PathVariable Long id) {
        Organization organization = organizationService.findOne(id);
        return Result.Success(organization);
    }

    @ResponseBody
    @RequiresPermissions("organization:create")
    @SystemLog("组织管理创建组织")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result create(@Valid Organization organization) {
        organizationService.createOrganization(organization);
        return Result.Success();
    }

    @ResponseBody
    @RequiresPermissions("organization:update")
    @SystemLog("组织管理更新组织")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@Valid Organization organization) {
        organizationService.updateOrganization(organization);
        return Result.Success();
    }

    @ResponseBody
    @RequiresPermissions("organization:delete")
    @SystemLog("组织管理删除组织")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        organizationService.deleteOrganization(id);
        return Result.Success();
    }

}
