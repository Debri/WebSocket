package com.geek.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Liuqi
 * Date: 2017/3/31.
 */

/**
 * 处理webSocket的连接和断开
 */
public class HandShake implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Websocket:用户[ID:"+ ((ServletServerHttpRequest) serverHttpRequest).getServletRequest()
                .getSession(false).getAttribute("uid") + "]已经建立连接");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            // 标记用户
            Long uid = (Long) session.getAttribute("uid");
            if (uid != null) {
                map.put("uid", uid);
            } else {
                return false;
            }
        }
        return true;
    }
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
