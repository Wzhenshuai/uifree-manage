package com.rt.cms.web.filter;

import com.rt.cms.model.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import com.rt.cms.common.Constants;
import com.rt.cms.service.system.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: Wangzs
 * <p>Date: 17-5-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUsername(username);
        request.setAttribute(Constants.CURRENT_USER, user);
        return true;
    }
}
