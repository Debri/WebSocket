package com.geek.bean;

/**
 * Created by Liuqi
 * Date: 2017/3/31.
 */

import java.util.Date;

/**
 * 消息类
 */
public class Message {
    private long from;//发送者
    private String fromName;
    private long to;//接收者
    private String toName;
    private String text;//消息内容
    private Date date;//发送时间

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", fromName='" + fromName + '\'' +
                ", to=" + to +
                ", toName='" + toName + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
