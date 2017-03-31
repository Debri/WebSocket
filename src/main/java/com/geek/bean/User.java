package com.geek.bean;

/**
 * Created by Liuqi
 * Date: 2017/3/31.
 */

/**
 * 用户类
 */
public class User {
    private long id;
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
