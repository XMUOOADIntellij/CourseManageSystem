package com.group12.course.security;

import com.alibaba.fastjson.JSON;
import com.group12.course.tools.Jwt;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
class JwtVerificationAop {

    private final Logger logger = LoggerFactory.getLogger(com.group12.course.security.JwtVerificationAop.class);
    /**
     * 拦截 controller 包下所以方法
     */
    @Pointcut("!execution(public * com.group12.course.controller.UserController.login(..))" +
            "&& !execution(public * com.group12.course.controller.UserController.forgetPassword(..))" +
            "&& !execution(public * com.group12.course.controller.SeminarProgressController.*(..))" +
            "&& execution(public * com.group12.course.controller.*.*(..))")
    public void log() {}

    /**
     * 环绕通知
     * 当 jwt 不合法时
     * 不执行 controller 层的方法
     *
     * @param proceedingJoinPoint 代表 controller 层的切点方法
     */
    @Around("log()")
    public Object checkJwt(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra!=null){
            HttpServletRequest request = sra.getRequest();
            String token = request.getHeader("Authorization");
            Boolean validJwt = Jwt.checkExpire(token);
            if (validJwt){
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                Object result = proceedingJoinPoint.proceed();
                stopWatch.stop();
                HttpServletResponse response = sra.getResponse();
                if (response!=null){
                    Map<String,String> errorMessage = new HashMap<>(16);
                    errorMessage.put("message","jwt token is expired");
                    String json = JSON.toJSONString(errorMessage);
                    response.setStatus(40);
                    response.getWriter().write(json);
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
}


