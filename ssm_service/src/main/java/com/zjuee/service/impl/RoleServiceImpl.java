package com.zjuee.service.impl;

import com.zjuee.dao.PermissionDao;
import com.zjuee.dao.RoleDao;
import com.zjuee.domain.Permission;
import com.zjuee.domain.Role;
import com.zjuee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RoleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findPermissionsByRid(String rid) {
        return permissionDao.findAvailPermissions(rid);
    }

    @Override
    public void addPermissionToRole(String rid, String[] ids) {
        for (String pid : ids) {
            roleDao.addPermission(rid, pid);
        }
    }
}
