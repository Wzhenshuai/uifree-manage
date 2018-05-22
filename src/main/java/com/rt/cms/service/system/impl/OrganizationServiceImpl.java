package com.rt.cms.service.system.impl;

import com.rt.cms.model.system.OrganizationExample;
import com.rt.cms.service.system.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.rt.cms.common.Constants;
import com.rt.cms.mapper.system.OrganizationMapper;
import com.rt.cms.model.system.Organization;
import com.rt.cms.web.dto.system.TreeDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public int createOrganization(Organization organization) {
        Organization parent = findOne(organization.getParentId());
        organization.setParentIds(parent.makeSelfAsParentIds());
        organization.setAvailable(true);
        return organizationMapper.insert(organization);
    }

    @Override
    public int updateOrganization(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationMapper.deleteByPrimaryKey(organizationId);
    }

    @Override
    public Organization findOne(Long organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    @Override
    public List<Organization> findAll() {
        return organizationMapper.selectByExample(new OrganizationExample());
    }

    @Override
    public List<Organization> find(OrganizationExample example) {
        return organizationMapper.selectByExample(example);
    }

    @Override
    public List<TreeDto> findOrgTree(Long pId) {
        if (StringUtils.isEmpty(pId)) {
            pId = Constants.ORG_ROOT_ID;
        }
        List<TreeDto> tds = new ArrayList<>();
        OrganizationExample example = new OrganizationExample();
        OrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pId);
        organizationMapper.selectByExample(example).forEach(o -> tds.add(new TreeDto(o.getId(), o.getParentId(), o.getName(), Boolean.FALSE.equals(o.getLeaf()), o)));
        return tds;
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        OrganizationExample example = new OrganizationExample();
        example.or().andIdNotEqualTo(excludeOraganization.getId()).andParentIdsNotLike(excludeOraganization.makeSelfAsParentIds() + "%");
        return organizationMapper.selectByExample(example);
    }

    @Override
    public void move(Organization source, Organization target) {
        OrganizationExample moveSourceExample = new OrganizationExample();
        moveSourceExample.or().andIdEqualTo(source.getId());
        organizationMapper.updateByExampleSelective(target, moveSourceExample);
        organizationMapper.updateSalefParentIds(source.makeSelfAsParentIds());
    }
}
