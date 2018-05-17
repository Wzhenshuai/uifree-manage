package tech.wetech.admin.service.system;

import tech.wetech.admin.model.system.Organization;
import tech.wetech.admin.model.system.OrganizationExample;
import tech.wetech.admin.web.dto.system.TreeDto;

import java.util.List;

/**
 * @author cjbi
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
