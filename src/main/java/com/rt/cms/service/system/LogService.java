package com.rt.cms.service.system;

import com.rt.cms.common.base.Page;
import com.rt.cms.common.base.PageResultSet;
import com.rt.cms.model.system.LogWithBLOBs;

public interface LogService {

    /**
     * 创建日志
     * @param logWithBLOBs
     * @return
     */
    int createLogWithBLOBs(LogWithBLOBs logWithBLOBs);

    PageResultSet<LogWithBLOBs> findByPage(Page page);

}
