package com.geek.websocket;

import com.geek.bean.Message;
import com.geek.util.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Liuqi
 * Date: 2017/3/31.
 * Socket处理器
 */
@Service
public class MyWebSocketHandler implements WebSocketHandler {
    public static final Map<Long, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<Long, WebSocketSession>();
    }

    /**
     * 建立连接以后
     *
     * @param webSocketSession
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

        Long uid = (Long) webSocketSession.getAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, webSocketSession);
        }
        System.out.println("建立连接以后");
    }

    /**
     * 消息处理
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        if (webSocketMessage.getPayloadLength() == 0) {
            return;
        }
        Message msg = (Message) JsonUtils.json2Obj(webSocketMessage.getPayload().toString(), Message.class);
        msg.setDate(new Date());
        sendMessageToUser(msg.getTo(), new TextMessage(msg.getText()));
        System.out.println("消息处理");
        return;
    }

    /**
     * 消息传输错误处理
     *
     * @param webSocketSession
     * @throws Exception
     */
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("消息传输错误处理");
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(webSocketSession.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    /**
     * 关闭连接
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket:" + webSocketSession.getId() + "已经关闭");
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 移除Socket会话
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(webSocketSession.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket会话已经移除:用户ID" + entry.getKey());
                break;
            }
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void broadcast(final TextMessage message) throws IOException {
        System.out.println("给所有的用户发送消息");
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
        // 多线程群发
        while (it.hasNext()) {
            final Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                // entry.getValue().sendMessage(message);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
    /**
     * 给某个用户发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageToUser(Long uid, TextMessage message) throws IOException {
        System.out.println("给某个用户发送消息");
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }
}
