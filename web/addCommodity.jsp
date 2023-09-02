<%--
  Created by IntelliJ IDEA.
  User: 早春的火烧云
  Date: 2023/8/9
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>补充商品</title>
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
<form action="tijiao" method="post">
    <div class="form-container">
        <div class="form-group">
            <label>商品名</label>
            <input type="text" name="name" class="form-control" placeholder="商品名">
        </div>
        <div class="form-group">
            <label>价格</label>
            <input type="number" name="price" class="form-control" placeholder="价格">
        </div>
        <div class="form-group">
            <label>数量</label>
            <input type="number" name="number" class="form-control" placeholder="商品数">
        </div>
        <div class="form-group">
            <input type="submit" name="sss" value="提交" class="btn btn-secondary btn-block">
        </div>
    </div>
</form>
</body>
</html>
