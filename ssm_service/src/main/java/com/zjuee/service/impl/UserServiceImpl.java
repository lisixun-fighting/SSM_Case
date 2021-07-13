package com.zjuee.service.impl;

import com.zjuee.dao.RoleDao;
import com.zjuee.dao.UserDao;
import com.zjuee.domain.Role;
import com.zjuee.domain.UserInfo;
import com.zjuee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        // 对密码进行加密处理
        String password = passwordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(password);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    /**
     * 查询没有与user关联的可用role的List集合
     * @param id
     * @return 与User关联的角色列表
     * @throws Exception
     */
    @Override
    public List<Role> findRolesByUid(String id) throws Exception {
        return roleDao.findAvailRole(id);
    }

    @Override
    public void addRoleToUser(String uid, String[] rids) throws Exception {
        for (String rid : rids) {
            userDao.allRoleToUser(uid, rid);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
            // 将自己的用户对象UserInfo封装进UserDetails
            return new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 1, true, true, true, getAuthorities(userInfo.getRoles()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取权限集合
     * @return
     */
    public List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        for(Role role : roles)
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        System.out.println(list);
        return list;
    }
}
