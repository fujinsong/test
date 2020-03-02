<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/12/5
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <link href="${ctx_modules}css/form-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/role_form.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}role/list">角色列表</a></li>
    <li class="active"><a href="${ctx}role/form?id=${role.id}">角色${not empty role.id?'修改':'添加'}</a></li>
</ul>
<br/>
<form class="inputForm" action="${ctx}role/save" method="post">
    <input type="hidden" name="id" value="${role.id}" class="id">
    <div class="input-group">
        <span class="input-group-addon">角色名:</span>
        <input type="text" class="form-control name" name="name" value="${role.name}">
        <span class="err-class"></span>
    </div>
<%--    <div class="input-group">--%>
<%--        <span class="input-group-addon">状态:</span>--%>
<%--        <select class="form-control" name="status">--%>
<%--            <option value="0" ${role.status==0?"selected":""}>正常</option>--%>
<%--            <option value="1" ${role.status==1?"selected":""}>删除</option>--%>
<%--        </select>--%>
<%--    </div>--%>
    <div class="form-actions">
        <input class="btn btn-primary" type="submit" value="保&emsp;存"/>&nbsp;
        <input class="btn btn-default cancel_btn" type="button" value="返&emsp;回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>