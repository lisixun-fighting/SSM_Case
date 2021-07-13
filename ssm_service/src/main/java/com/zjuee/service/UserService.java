package com.zjuee.service;

import com.zjuee.domain.Role;
import com.zjuee.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserInfo findById(String id) throws Exception;

    /**
     * 根据uid查询可添加的role
     * @param id
     * @return
     */
    List<Role> findRolesByUid(String id) throws Exception;

    /**
     * 给指定用户添加roles
     * @param uid
     * @param rids
     */
    void addRoleToUser(String uid, String[] rids) throws Exception;
}
