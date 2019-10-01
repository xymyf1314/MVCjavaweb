<%--
  Created by IntelliJ IDEA.
  User: fan
  Date: 2019/9/19 0019
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.neuedu.entity.Admin" %>
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ERROR：权限不足</title>
    <style>
        #one{
            margin: auto;
            font-size: 30px;
            color: #2E2D3C;
        }
    </style>
</head>
<body>
<div id="one">
    您的权限不足，请和您的超级管理员联系！
    <p><span style="color: red;" id="second">5</span>秒钟后跳转到会员列表</p>
</div>

<script type="text/javascript">
    var number = 5;

    function fun1() {
        if (number <= 0) number = 0;
        if (number <= 0) location.href = "userServlet?method=findAll";
        number--;
        var time = document.getElementById("second");
        time.innerHTML = number + "";
    }

    setInterval(fun1, 1000);
</script>

</body>
</html>