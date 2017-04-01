package com.geek.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * Created by Liuqi
 * Date: 2017/3/31.
 * webSocket配置处理器
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    private MyWebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/ws").addInterceptors(new HandShake());
        webSocketHandlerRegistry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
    }
}
