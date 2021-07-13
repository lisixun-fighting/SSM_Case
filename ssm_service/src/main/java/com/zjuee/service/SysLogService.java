package com.zjuee.service;

import com.zjuee.domain.SysLog;

import java.util.List;

public interface SysLogService {

    /**
     * 保存日志
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    List<SysLog> findAll();
}
