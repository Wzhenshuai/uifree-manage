package tech.wetech.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tech.wetech.admin.common.base.Page;
import tech.wetech.admin.common.base.PageResultSet;
import tech.wetech.admin.mapper.system.RoleMapper;
import tech.wetech.admin.model.system.Resource;
import tech.wetech.admin.model.system.Role;
import tech.wetech.admin.model.system.RoleExample;
import tech.wetech.admin.service.system.ResourceService;
import tech.wetech.admin.service.system.RoleService;
import tech.wetech.admin.web.dto.system.RoleDto;
import tech.wetech.admin.web.dto.system.UserDto;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public PageResultSet<RoleDto> findByPage(Page page) {
        RoleExample example = new RoleExample();
        example.setOffset(page.getOffset());
        example.setLimit(page.getLimit());
        if (!StringUtils.isEmpty(page.getSearch())) {
            example.or().andRoleLike("%" + page.getSearch() + "%");
            example.or().andDescriptionLike("%" + page.getSearch() + "%");
        }
        long count = roleMapper.countByExample(example);
        PageResultSet<RoleDto> resultSet = new PageResultSet<>();
        List<RoleDto> dtoList = new ArrayList<>();
        roleMapper.selectByExample(example).forEach(r->{
            RoleDto dto = new RoleDto(r);
            dto.setResourceNames(getResourceNames(r.getResourceIdList()));
            dtoList.add(dto);
        });
        resultSet.setRows(dtoList);
        resultSet.setTotal(count);
        return resultSet;
    }

    private String getResourceNames(Collection<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for (Long resourceId : resourceIds) {
            Resource resource = resourceService.findOne(resourceId);
            if (resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }
        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    @Override
    public int createRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                resourceIds.addAll(role.getResourceIdList());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
