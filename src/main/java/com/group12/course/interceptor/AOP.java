package com.group12.course.interceptor;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.tools.Jwt;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class AOP {

    private final Logger logger = LoggerFactory.getLogger(AOP.class);
    /**
     * 拦截 controller 包下所以方法
     */
    @Pointcut("!execution(public * com.group12.course.controller.UserController.login(..))" +
            "&& !execution(public * com.group12.course.controller.UserController.forgetPassword(..))" +
            "&& !execution(public * com.group12.course.controller.SeminarProgressController.*(..))" +
            "&& execution(public * com.group12.course.controller.*.*(..))")
    public void log() {}

    /**
     * 前置通知
     *
     * @param proceedingJoinPoint
     */
    @Around("log()")//log()方法之前
    public Object checkJwt(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //接收请求，记录请求
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra!=null){
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("Authorization");
            Boolean validJwt = Jwt.checkExpire(token);
            if (validJwt){
                System.out.println("jwt is ok");
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                Object result = proceedingJoinPoint.proceed();
                stopWatch.stop();
                double elapsedTime = stopWatch.getTime() / 1000.0;
                Signature signature = proceedingJoinPoint.getSignature();
                String infoString = "[" + signature.toShortString() + "][Elapsed time: " + elapsedTime + " s]";
            if (elapsedTime > 1) {
                logger.error(infoString + "[Note that it's time consuming!]");
            } else {
                logger.info(infoString);
            }

                return result;
            }
            else {
                logger.error("jwt token is valid");
                HttpServletResponse response = sra.getResponse();
                if (response!=null){
                    Map<String,String> errorMessage = new HashMap<>(16);
                    errorMessage.put("message","jwt token is invalid");
                    String json = JSON.toJSONString(errorMessage);
                    response.setStatus(403);
                    response.getWriter().write(json);
                }
            }

        }
        return null;

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
