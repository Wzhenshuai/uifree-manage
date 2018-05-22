package com.rt.cms.service.system;

import com.rt.cms.model.system.Organization;
import com.rt.cms.model.system.OrganizationExample;
import com.rt.cms.web.dto.system.TreeDto;

import java.util.List;

/**
 * @author wangzs
 */
public interface OrganizationService {

    int createOrganization(Organization organization);

    int updateOrganization(Organization organization);

    void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> find(OrganizationExample example);

    List<TreeDto> findOrgTree(Long pId);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
