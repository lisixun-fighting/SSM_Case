package com.zjuee.controller;

import com.zjuee.domain.Permission;
import com.zjuee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList", permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(@RequestParam(name = "permissionName", required = true)String pName, @RequestParam(name = "url")String url) {
        permissionService.save(pName,url);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true)String pid) {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(pid);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }
}
