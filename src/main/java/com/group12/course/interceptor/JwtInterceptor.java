package com.group12.course.interceptor;

import com.group12.course.entity.Jwt;
import com.group12.course.entity.Teacher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt 过滤器
 * @author Xu Gang
 * @date 2018年12月11日
 * */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        //只有返回true才会继续向下执行，返回false取消当前请求
        System.out.println("myinterc prehandler");
        String token = request.getHeader("Authorization");
        //token不存在
        if(null != token) {
            Teacher login = Jwt.unsign(token, Teacher.class);
            String loginId = request.getParameter("loginId");
            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
            System.out.println("jwt id :"+login.getAccount());
            System.out.println("user id :"+loginId);
            if(null != loginId && null != login) {
                if(loginId == login.getAccount()) {
                    return true;
                }
                else{
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            System.out.println("token not exist");
            System.out.println(request);
            return false;
        }
    }
}
