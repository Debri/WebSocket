<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/3/31
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>webSocket</title>
</head>
<body>
<form action="msg/login" method="post">
    用户名:
    <select name="id">
        <option value="1">jack</option>
        <option value="2">rose</option>
    </select><br>
    密码:
    <input name="password" type="text" value="123456">
    <input type="submit" value="login">
</form>
</body>
</html>
