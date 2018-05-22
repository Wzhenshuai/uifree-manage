package com.rt.cms.mapper.system;

import java.util.List;

import com.rt.cms.model.system.ResourceExample;
import org.apache.ibatis.annotations.Param;
import com.rt.cms.model.system.Resource;

public interface ResourceMapper {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}