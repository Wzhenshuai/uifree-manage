package com.rt.cms.service.system.impl;

import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.mapper.system.LogMapper;
import com.rt.cms.model.system.LogExample;
import com.rt.cms.service.system.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.rt.cms.model.system.LogWithBLOBs;

import java.util.List;

/**
 * 日志服务
 * Created by cjbi on 2017/12/14.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Override
    public int createLogWithBLOBs(LogWithBLOBs logWithBLOBs) {
        return logMapper.insertSelective(logWithBLOBs);
    }

    @Override
    public PageResultSet<LogWithBLOBs> findByPage(Page page) {
        LogExample example = new LogExample();
        example.setOffset(page.getOffset());
        example.setLimit(page.getLimit());
        example.setOrderByClause("create_time desc");//时间倒序
        if(!StringUtils.isEmpty(page.getSearch())) {
            String value = "%" + page.getSearch() + "%";
            example.or().andUsernameLike(value);//用户名
            example.or().andIpLike(value);//ip地址
            example.or().andReqMethodLike(value);//请求方法
            example.or().andExecMethodLike(value);//执行方法
            example.or().andExecDescLike(value);//执行描述
            example.or().andStatusLike(value);//状态
        }
        PageResultSet<LogWithBLOBs> resultSet = new PageResultSet<>();
        long count = logMapper.countByExample(example);
        List<LogWithBLOBs> list = logMapper.selectByExampleWithBLOBs(example);
        resultSet.setRows(list);
        resultSet.setTotal(count);
        return resultSet;
    }
}
