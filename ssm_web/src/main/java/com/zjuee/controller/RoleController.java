package com.zjuee.controller;

import com.zjuee.domain.Permission;
import com.zjuee.domain.Role;
import com.zjuee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 根据id查询角色详情
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true)String id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.setViewName("role-show");
        mv.addObject("role",role);
        return mv;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll";
    }

    /**
     * 查询指定角色的可添加权限
     * @param rid
     * @return
     */
    @RequestMapping("/findPermissionsByRid")
    public ModelAndView findPermissionsByRid(@RequestParam(name = "id", required = true)String rid) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(rid);
        List<Permission> permissions = roleService.findPermissionsByRid(rid);
        mv.setViewName("role-permission-add");
        mv.addObject("role",role);
        mv.addObject("permissionList", permissions);
        return mv;
    }

    /**
     * 向指定的角色添加权限
     * @param rid
     * @param pids
     * @return
     */
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId")String rid, @RequestParam(name = "ids")String[] pids) {
        roleService.addPermissionToRole(rid, pids);
        return "redirect:findAll";
    }
}
