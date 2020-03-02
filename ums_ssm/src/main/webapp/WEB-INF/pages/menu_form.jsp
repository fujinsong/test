<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>权限管理</title>
    <link href="${ctx_modules}css/form-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/menu_form.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}menu/list">权限列表</a></li>
    <li class="active"><a href="${ctx}menu/form?id=${menu.id}">权限${not empty menu.id?'修改':'添加'}</a></li>
</ul>
<br/>
<form class="inputForm" action="${ctx}menu/save" method="post">
    <input type="hidden" name="id" value="${menu.id}" class="id">
    <div class="input-group">
        <span class="input-group-addon">权限名:</span>
        <input type="text" class="form-control name" name="name" value="${menu.name}">
        <span class="err-class"></span>
    </div>
    <div class="input-group">
        <span class="input-group-addon">状态:</span>
        <select class="form-control" name="status">
            <option value="0" ${menu.status==0?"selected":""}>正常</option>
            <option value="1" ${menu.status==1?"selected":""}>删除</option>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">url:</span>
        <input type="text" class="form-control url" name="url" value="${menu.url}">
        <span class="err-class"></span>
    </div>
    <div class="form-actions">
        <input class="btn btn-primary" type="submit" value="保&emsp;存"/>&nbsp;
        <input class="btn btn-default cancel_btn" type="button" value="返&emsp;回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>
