package com.zjuee.dao;

import com.zjuee.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SysLogDao")
public interface SysLogDao {

    /**
     * 保存日志
     * @param sysLog
     */
    @Insert("insert into sysLog (visitTime,username,ip,url,executionTime,method) " +
            "values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll();
}
