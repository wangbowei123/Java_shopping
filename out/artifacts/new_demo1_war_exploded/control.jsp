<%--
  Created by IntelliJ IDEA.
  User: 早春的火烧云
  Date: 2023/8/8
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员系统管理</title>
    <style>
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
    </style>
</head>
<body>
<div class="form-container">
    <form action="boss" method="post">
        <div class="form-group">
            <label>用户名</label>
            <input type="text" name="id" class="form-control" placeholder="account">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" name="pwd" class="form-control" placeholder="password">
        </div>
        <div class="form-group">
            <input type="submit" name="login" value="登录" class="btn btn-secondary btn-block">
        </div>
    </form>
</div>
</body>
</html>
