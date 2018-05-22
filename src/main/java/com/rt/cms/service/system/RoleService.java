package com.rt.cms.service.system;

import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.model.system.Role;
import com.rt.cms.web.dto.system.RoleDto;

import java.util.List;
import java.util.Set;

public interface RoleService {

    PageResultSet<RoleDto> findByPage(Page page);

    int createRole(Role role);

    int updateRole(Role role);

    void deleteRole(Long roleId);

    Role findOne(Long roleId);

    List<Role> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
