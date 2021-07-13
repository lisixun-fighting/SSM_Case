package com.zjuee.dao;

import com.zjuee.domain.Role;
import com.zjuee.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 根据用户名称查询用户信息 用于登录
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{value}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.zjuee.dao.RoleDao.findByUid"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Insert("insert into users (email, username, password, phoneNum, status) " +
            "values (#{email}, #{username}, #{password}, #{phoneNum}, #{status})")
    void save(UserInfo userInfo) throws Exception;


    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from users where id = #{value}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.zjuee.dao.RoleDao.findByUid"))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 给指定用户添加指定角色
     * @param uid
     * @param rid
     * @throws Exception
     */
    @Insert("insert into users_role values (#{uid}, #{rid})")
    void allRoleToUser(@Param("uid") String uid, @Param("rid") String rid) throws Exception;


    List<UserInfo> findByRid(String rid);
}
