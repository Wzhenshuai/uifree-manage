package com.rt.cms.web.controller.system;

import com.rt.cms.service.system.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.model.system.LogWithBLOBs;
import com.rt.cms.web.controller.base.BaseController;

/**
 * 日志控制器 Created by wangzs on 2017/12/16.
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{

    @Autowired
    private LogService logService;

    @RequiresPermissions("log:view")
    @RequestMapping(method = RequestMethod.GET)
    public String toPage(Model model) {
        return "system/log";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("log:view")
    public PageResultSet<LogWithBLOBs> list(Page page) {
        logService.findByPage(page);
        return logService.findByPage(page);
    }

}
