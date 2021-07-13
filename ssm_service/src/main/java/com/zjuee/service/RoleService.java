package com.zjuee.service;

import com.zjuee.domain.Permission;
import com.zjuee.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleService")
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 根据id查询角色详情
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 保存用户
     * @param role
     */
    void save(Role role);

    /**
     * 查询指定角色可添加权限
     * @param rid
     * @return
     */
    List<Permission> findPermissionsByRid(String rid);

    /**
     * 给角色添加权限
     * @param rid
     * @param pids
     */
    void addPermissionToRole(String rid, String[] pids);
}
