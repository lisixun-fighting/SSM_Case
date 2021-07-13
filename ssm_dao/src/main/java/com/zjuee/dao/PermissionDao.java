package com.zjuee.dao;

import com.zjuee.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PermissionDao")
public interface PermissionDao {

    /**
     * 查询指定角色的权限
     * @param rid
     * @return
     */
    @Select("select * from permission where id in ( select permissionId from role_permission where roleId = #{value} ) ")
    List<Permission> findByRid(String rid);

    /**
     * 查询指定角色可添加的权限
     * @param rid
     * @return
     */
    @Select("select * from permission where id not in ( select permissionId from role_permission where roleId = #{value} )")
    List<Permission> findAvailPermissions(String rid);

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 添加权限
     * @param pName
     * @param url
     */
    @Insert("insert into permission (permissionName, url) values (#{pName}, #{url})")
    void save(@Param("pName") String pName, @Param("url") String url);

    @Select("select * from permission where id = #{value}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "permissionName", property = "permissionName"),
            @Result(column = "url", property = "url"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.zjuee.dao.RoleDao.findByPid"))
    })
    Permission findById(String pid);
}
