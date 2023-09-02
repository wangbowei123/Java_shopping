<%--
  Created by IntelliJ IDEA.
  User: 早春的火烧云
  Date: 2023/8/1
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户信息注册</title>
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 使用视口高度作为容器高度，以使内容垂直居中 */
            margin: 0; /* 去除默认边距 */
        }
        .container {
            text-align: center;
            padding: 20px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="Signup" method="post">
        <div class="form-group">
            <label>用户名</label>
            <input type="text" name="id" class="form-control" placeholder="account"><br>
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" name="pwd1" class="form-control" placeholder="password"><br>
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" name="pwd2" class="form-control" placeholder="请再次输入密码"><br>
        </div>
        <button type="submit">注册</button>
    </form>
</div>
</body>
</html>
