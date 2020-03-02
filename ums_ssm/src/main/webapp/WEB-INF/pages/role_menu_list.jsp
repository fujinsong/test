<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/12/5
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>权限管理</title>
    <link href="${ctx_modules}css/list-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/list-common.js"></script>
    <script type="text/javascript" src="${ctx_modules}js/role_menu_list.js"></script>
</head>
<body>
<input type="hidden" class="roleId" value="${roleId}">
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}menu/list">权限列表</a></li>
    <%--    <li><a href="${ctx}menu/form">角色添加</a></li>--%>

</ul>
<form id="searchForm" action="${ctx}menu/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" class="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" class="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="totalPage" class="totalPage" name="totalPage" type="hidden" value="${page.totalPage}"/>
    <div class="input-group">
        <span class="input-group-addon">名称:</span>
        <input type="text" class="form-control" name="name" value="${menu.name}">
    </div>
    <div class="input-group">
        <input class="btn btn-primary search-btn" type="button" value="查&emsp;询"/>
    </div>

</form>
<div class="input-group" style="margin-left: 2%">
    <input class="btn btn-primary save_menu" type="button" value="保&emsp;存"/>
</div>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>选项</th>
        <th>权限名称</th>
        <th>权限地址</th>
        <th>创建时间</th>
        <th>修改时间</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="unit">
        <tr>
            <td>
                <input type="checkbox" name="id" value="${unit.id}">
            </td>
            <td>${unit.name}</td>
            <td>${unit.url}</td>
            <td>
                <fmt:formatDate value="${unit.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
            <td>
                <fmt:formatDate value="${unit.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
            <td>
                <c:choose>
                    <c:when test="${unit.status==0}">正常</c:when>
                    <c:when test="${unit.status==1}">删除</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="yun_include/page.jsp" %>
</body>
</html>
