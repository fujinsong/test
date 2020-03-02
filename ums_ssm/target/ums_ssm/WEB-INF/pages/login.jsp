<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/11/30
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp"%>
<html>
<head>
    <title>登录页面</title>
    <%--  引入自己的资源  --%>
    <script type="text/javascript" src="${ctx_modules}js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_modules}css/login.css">
</head>
<body>
<form class="loginForm" action="${ctx}user/login">
    <div class="page-header">
        <h2>Sign In 后台登录</h2>
    </div>
    <div class="input-group">
        <span class="input-group-addon">手机号：</span>
        <input type="text" name="mobile" class="form-control mobile" placeholder="手机号不能为空"
               aria-describedby="sizing-addon2">
        <span class="err-class"></span>
    </div>
    <br>
    <div class="input-group">
        <span class="input-group-addon">验证码：</span>
        <input type="password" name="verification" class="form-control verification" placeholder="验证码不能为空"
               aria-describedby="sizing-addon2">
        <button type="button" class="btn btn-default btn-sm sendSms">
            <span class=""></span>发送短信验证码
        </button>
        <span class="err-class"></span>
    </div>
    <br>
    <div class="btns">
        <button type="submit" class="btn btn-default btn-sm myLoginBtn" style="margin-right: 22px;">
            <span class=""></span>登&emsp;&emsp;&emsp;录
        </button>
    </div>
</form>
</body>
</html>

