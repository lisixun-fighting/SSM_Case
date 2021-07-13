package com.zjuee.controller;

import com.zjuee.domain.SysLog;
import com.zjuee.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping("/findAll")
    ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<SysLog> logs = sysLogService.findAll();
        mv.addObject("sysLogs",logs);
        mv.setViewName("syslog-list");
        return mv;
    }
}
