package com.zjuee.service;

import com.zjuee.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 保存权限
     * @param pName
     * @param url
     */
    void save(String pName, String url);

    /**
     * 查询指定权限详情
     * @param pid
     * @return
     */
    Permission findById(String pid);
}
