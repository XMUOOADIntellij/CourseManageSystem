package com.group12.course.tools;

import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;


/**
 * 配置webSocket
 * @author Y Jiang
 * @date 2018/12/23
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {



    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/seminarSocket");
        config.setApplicationDestinationPrefixes("/app");
    }


    /**
     * registers the "/gs-guide-websocket" endpoint
     * enabling SockJS fallback options so that alternate transports
     * may be usedif WebSocket is not available.
     * The SockJS client willattempt to connect to "/gs-guide-websocket"
     * and use the best transport available (websocket, xhr-streaming, xhr-polling, etc)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP端点 ，订阅或发布消息到目的地路径前，要连接该端点
        registry.addEndpoint("/Socket").setAllowedOrigins("*").withSockJS();
//        registry.addEndpoint("/Socket").addInterceptors(new HandshakeInterceptor() {
//            /**
//             * websocket握手
//             */
//            @Override
//            public boolean beforeHandshake(ServerHttpRequest request,
//                                           ServerHttpResponse response,
//                                           WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//                ServletServerHttpRequest req = (ServletServerHttpRequest) request;
//                //获取token认证
//                String token = req.getServletRequest().getParameter("token");
//                //解析token获取用户信息
//                Principal user = parseToken(token);
//                //如果token认证失败user为null，返回false拒绝握手
//                if(user == null){
//                    System.out.println("user is null");
//                    return false;
//                }
//                //保存认证用户
//                attributes.put("user", user);
//                System.out.println(user.getName());
//                return true;
//            }
//
//            @Override
//            public void afterHandshake(ServerHttpRequest request,
//                                       ServerHttpResponse response,
//                                       WebSocketHandler wsHandler, Exception exception) {
//            }
//        }).setHandshakeHandler(new DefaultHandshakeHandler(){
//            @Override
//            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//                //设置认证用户
//                return (Principal)attributes.get("user");
//            }
//        }).setAllowedOrigins("*").withSockJS();
    }

    /**
     * 根据token认证授权
     * @param token token值
     */
//    private Principal parseToken(String token){
//        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
//        Student jwtStudent = Jwt.unSign(token,Student.class);
//        Principal principal = new Principal() {
//            @Override
//            public String getName() {
//                if (jwtStudent!=null){
//                    return jwtStudent.getId().toString();
//                }
//                else if (jwtTeacher!=null){
//                    return jwtTeacher.getId().toString();
//                }
//                else {
//                    return null;
//                }
//            }
//        };
//        return principal;
//    }
}
