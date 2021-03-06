package com.rt.cms.service.system;

import com.rt.cms.model.system.ResourceExample;
import com.rt.cms.model.system.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService {

    int createResource(Resource resource);

    int updateResource(Resource resource);

    void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);

    List<Resource> find(ResourceExample example);

    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

}
