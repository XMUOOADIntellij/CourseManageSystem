package com.group12.course.interceptor;

import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.tools.Jwt;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Logger;


@Aspect
@Component
public class AOP {

    /**
     * 拦截 controller 包下所以方法
     */
    @Pointcut("!execution(public * com.group12.course.controller.UserController.login(..))" +
            "&& !execution(public * com.group12.course.controller.UserController.forgetPassword(..))" +
            "&& !execution(public * com.group12.course.controller.SeminarProgressController.*(..))" +
            "&& execution(public * com.group12.course.controller.UserController.*(..))")
    public void log() {}

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Around("log()")//log()方法之前
    public void checkJwt(JoinPoint joinPoint) {
        //接收请求，记录请求
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra!=null){
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("Authorization");
            Boolean validJwt = Jwt.checkExpire(token);
            if (validJwt){
                System.out.println("jwt is ok");
            }
            else {
                System.out.println("jwt is bad");
            }


            //记录日志
            System.out.println("url" + request.getRequestURI().toString());
            System.out.println("method" + request.getMethod());
            System.out.println("ip" + request.getRemoteAddr());

            Enumeration<String> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                System.out.println("name:" + name + ",value:{" + request.getParameter(name) + "}");

            }
        }

    }

    /**
     * 后置通知
     * @param ret
//     */
//    @AfterReturning(returning = "ret", pointcut = " log() ")
//    public void doAfter(Object ret) {
//        System.out.println("response" + ret);
//    }
}
