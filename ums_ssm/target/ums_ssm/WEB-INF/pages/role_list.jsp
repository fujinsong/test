<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/12/2
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>角色管理</title>
    <link href="${ctx_modules}css/list-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/list-common.js"></script>
    <script type="text/javascript" src="${ctx_modules}js/role_list.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}role/list">角色列表</a></li>
    <li><a href="${ctx}role/form">角色添加</a></li>

</ul>
<form id="searchForm" action="${ctx}role/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" class="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" class="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="totalPage" class="totalPage" name="totalPage" type="hidden" value="${page.totalPage}"/>
    <div class="input-group">
        <span class="input-group-addon">名称:</span>
        <input type="text" class="form-control" name="name" value="${role.name}">
    </div>
    <div class="input-group">
        <span class="input-group-addon">状态:</span>
        <select class="form-control" name="status">
            <option value="-1">全部</option>
            <option value="0" ${role.status==0?"selected":""}>正常</option>
            <option value="1" ${role.status==1?"selected":""}>删除</option>
        </select>
    </div>
    <div class="input-group">
        <input class="btn btn-primary search-btn" type="button" value="查&emsp;询"/>
    </div>

</form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>角色名称</th>
        <th>创建时间</th>
        <th>修改时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="unit">
        <tr>
            <td><a href="${ctx}role/form?id=${unit.id}">${unit.name}</a></td>
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
            <td>
                <a href="${ctx}role/form?id=${unit.id}">修改</a>
                <a href="javascript:void(0)" about="${unit.id}" class="update_menu">授权</a>
                <a class="delete_data" name="role" title="${unit.id}" href="javascript:void(0)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="yun_include/page.jsp" %>
</body>
</html>


