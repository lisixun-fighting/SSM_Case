package com.zjuee;

import com.zjuee.domain.SysLog;
import com.zjuee.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component("LogAop")
@Aspect
public class LogAop {

    private Date visitTime; // 开始时间
    private Class<?> clazz; // 访问的类
    private Method method; // 访问的方法

    @Autowired
    private HttpServletRequest request; // 通过监听器获取

    @Autowired
    private SysLogService sysLogService;

    /**
     * 前置通知
     * 获取开始时间，执行类，执行方法
     * @param jp
     */
    @Before("execution(* com.zjuee.controller.*.*(..))")
    public void doBefore(JoinPoint jp) {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        // 获取访问的方法名
        String methodName = jp.getSignature().getName();
        // 获取访问的方法的参数
        Object[] args = jp.getArgs();
        // 获取具体执行的方法对象
        if(clazz != null) {
            try {
                if(args == null || args.length == 0) {
                    method = clazz.getMethod(methodName);
                }else {
                    Class<?>[] classArgs = new Class[args.length];
                    for (int i = 0; i < args.length; i++) {
                        classArgs[i] = args[i].getClass();
                    }
                    method = clazz.getMethod(methodName, classArgs);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 后置通知
     * @param jp
     */
    @After("execution(* com.zjuee.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        // 获取访问时长
        long executionTime = new Date().getTime() - visitTime.getTime();

        // 获取url
        String url = "";
        // 通过反射注解信息来获取url
        if(clazz != null && method != null) {
            // 获取类上的@RequestMapping("/order")
            RequestMapping classAnnotation = clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null) {
                url = classAnnotation.value()[0];
                // 获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null) {
                    // 获取到访问路径的url
                    url = url + methodAnnotation.value()[0];
                }
            }
        }

        // 获取访问的ip
        // 通过request对象获取
        String ip = request.getRemoteAddr();

        // 获取当前操作的用户
        // 首先从SpringSecurity中获取上下文对象SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User)context.getAuthentication().getPrincipal();
        // 通过上下文对象获取用户对象UserDetails
        String username = user.getUsername();

        // 将日志相关信息封装到SysLog对象
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        if(clazz != null && method != null) {
            sysLog.setMethod("[类名]" + clazz.getName() + " [方法名]" + method.getName());
        }

        // 调用Service完成日志保存操作
        sysLogService.save(sysLog);
    }
}
