package com.zjuee.dao;

import com.zjuee.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository("RoleDao")
public interface RoleDao {

    @Select("select * " +
            "from role t1, users_role t2 " +
            "where t1.id = t2.roleId and t2.userId = #{value}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.zjuee.dao.PermissionDao.findByRid"))
    })
    List<Role> findByUid(String uid) throws Exception;

    @Select("select * from role")
    List<Role> findAll();

    @Select("select * from role where id not in (select roleId from users_role where userid = #{value})")
    List<Role> findAvailRole(String uid);

    @Select("select * from role where id = #{value}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.zjuee.dao.PermissionDao.findByRid"))
    })
    Role findById(String id);

    @Insert("insert into role (roleName, roleDesc) values (#{roleName}, #{roleDesc})")
    void save(Role role);

    @Insert("insert into role_permission values (#{pid}, #{rid})")
    void addPermission(@Param("rid")String rid, @Param("pid") String pid);

    @Select("select * from role t1, role_permission t2 where t1.id = t2.roleId and t2.permissionId = #{value}")
    List<Role> findByPid(String pid);
}
