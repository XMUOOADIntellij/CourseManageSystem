package com.group12.course.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


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
    }
}
