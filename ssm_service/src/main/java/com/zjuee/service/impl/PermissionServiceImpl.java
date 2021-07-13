package com.zjuee.service.impl;

import com.zjuee.dao.PermissionDao;
import com.zjuee.domain.Permission;
import com.zjuee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PermissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(String pName, String url) {
        permissionDao.save(pName, url);
    }

    @Override
    public Permission findById(String pid) {
        return permissionDao.findById(pid);
    }
}
