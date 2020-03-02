<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/12/4
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <link href="${ctx_modules}css/form-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/user_form.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}user/list">用户列表</a></li>
    <li class="active"><a href="${ctx}user/form?id=${user.id}">用户${not empty user.id?'修改':'添加'}</a></li>
</ul>
<br/>
<form class="inputForm" action="${ctx}user/save" method="post">
<%--    <h4>用户${not empty user.id?'修改':'添加'}</h4>--%>
    <input type="hidden" name="id" value="${user.id}" class="id">
    <div class="input-group">
        <span class="input-group-addon">账号:</span>
        <input type="text" class="form-control userName" name="userName" value="${user.userName}">
        <span class="err-class"></span>
    </div>
    <div class="input-group">
        <span class="input-group-addon">真实姓名:</span>
        <input type="text" class="form-control" name="realName" value="${user.realName}">
        <span class="err-class"></span>
    </div>
    <div class="input-group">
        <span class="input-group-addon">性别:</span>
        <select class="form-control" name="sex">
            <option value="0" ${user.sex==0?"selected":""}>男</option>
            <option value="1" ${user.sex==1?"selected":""}>女</option>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">角色:</span>
        <select class="form-control" name="role.id">
            <c:forEach items="${roleList}" var="role">
                <option value="${role.id}" ${user.role.id==role.id?"selected":""}>${role.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">手机号:</span>
        <input type="text" class="form-control mobile" name="mobile" value="${user.mobile}">
        <span class="err-class"></span>
    </div>
    <div class="form-actions">
        <input class="btn btn-primary" type="submit" value="保&emsp;存"/>&nbsp;
        <input class="btn btn-default cancel_btn" type="button" value="返&emsp;回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>
