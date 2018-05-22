package com.rt.cms.mapper.system;

import java.util.List;

import com.rt.cms.model.system.LogExample;
import org.apache.ibatis.annotations.Param;
import com.rt.cms.model.system.Log;
import com.rt.cms.model.system.LogWithBLOBs;

public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LogWithBLOBs record);

    int insertSelective(LogWithBLOBs record);

    List<LogWithBLOBs> selectByExampleWithBLOBs(LogExample example);

    List<Log> selectByExample(LogExample example);

    LogWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LogWithBLOBs record, @Param("example") LogExample example);

    int updateByExampleWithBLOBs(@Param("record") LogWithBLOBs record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(LogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LogWithBLOBs record);

    int updateByPrimaryKey(Log record);
}