<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/12/1
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <link href="${ctx_modules}css/list-common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${ctx_modules}js/list-common.js"></script>
    <script type="text/javascript" src="${ctx_modules}js/user_list.js"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}user/list">用户列表</a></li>
    <li><a href="${ctx}user/form">用户添加</a></li>

</ul>
<form id="searchForm" action="${ctx}user/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" class="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" class="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input id="totalPage" class="totalPage" name="totalPage" type="hidden" value="${page.totalPage}"/>
    <div class="input-group">
        <span class="input-group-addon">账号:</span>
        <input type="text" class="form-control" name="userName" value="${user.userName}">
    </div>
    <div class="input-group">
        <span class="input-group-addon">真实姓名:</span>
        <input type="text" class="form-control" name="realName" value="${user.realName}">
    </div>
    <div class="input-group">
        <span class="input-group-addon">性别:</span>
        <select class="form-control" name="sex">
            <option value="-1">全部</option>
            <option value="0" ${user.sex==0?"selected":""}>男</option>
            <option value="1" ${user.sex==1?"selected":""}>女</option>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">登录时间:</span>
        <input type="text" class="form-control Wdate" name="startLoginTime"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${user.startLoginTime}">
        <span class="input-group-addon search_date_to" style="width: 10px">到</span>
        <input type="text" class="form-control Wdate" name="endLoginTime"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd- HH:mm:ss'})" value="${user.endLoginTime}">
    </div>
    <div class="input-group">
        <span class="input-group-addon">注册时间:</span>
        <input type="text" class="form-control Wdate" name="startRegisterTime"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${user.startRegisterTime}">
        <span class="input-group-addon search_date_to" style="width: 10px">到</span>
        <input type="text" class="form-control Wdate" name="endRegisterTime"
               onclick="WdatePicker({dateFmt:'yyyy-MM-dd- HH:mm:ss'})" value="${user.endRegisterTime}">
    </div>
    <div class="input-group">
        <span class="input-group-addon">状态:</span>
        <select class="form-control" name="status">
            <option value="-1">全部</option>
            <option value="0" ${user.status==0?"selected":""}>正常</option>
            <option value="1" ${user.status==1?"selected":""}>删除</option>
            <option value="2" ${user.status==2?"selected":""}>冻结</option>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">角色:</span>
        <select class="form-control" name="role.id">
            <option value="-1">全部</option>
            <c:forEach items="${roleList}" var="role">
                <option value="${role.id}" ${user.role.id==role.id?"selected":""}>${role.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="input-group">
        <span class="input-group-addon">手机号:</span>
        <input type="text" class="form-control" name="mobile" value="${user.mobile}">
    </div>
    <div class="input-group">
        <input class="btn btn-primary search-btn" type="button" value="查&emsp;询"/>
    </div>

</form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>账号</th>
        <th>真实姓名</th>
        <th>性别</th>
        <th>登录时间</th>
        <th>注册时间</th>
        <th>状态</th>
        <th>角色</th>
        <th>手机号</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="unit">
        <tr>
            <td><a href="${ctx}user/form?id=${unit.id}">${unit.userName}</a></td>
            <td>${unit.realName}</td>
            <td>
                <c:choose>
                    <c:when test="${unit.sex==0}">男</c:when>
                    <c:when test="${unit.sex==1}">女</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </td>
            <td>
                <fmt:formatDate value="${unit.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
            <td>
                <fmt:formatDate value="${unit.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
            <td>
                <c:choose>
                    <c:when test="${unit.status==0}">正常</c:when>
                    <c:when test="${unit.status==1}">删除</c:when>
                    <c:when test="${unit.status==2}">冻结</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </td>
            <td>${unit.role.name}</td>
            <td>${unit.mobile}</td>
            <td>
                <a href="${ctx}user/form?id=${unit.id}">修改</a>
                <a href="javascript:void(0)" about="${unit.id}" class="freeze" accesskey="yes">冻结</a>
                <a href="javascript:void(0)" about="${unit.id}" class="freeze" accesskey="no">解冻</a>
                <a href="javascript:void(0)" about="${unit.id}" class="update_role">修改角色</a>
                <a class="delete_data" name="user" title="${unit.id}" href="javascript:void(0)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="yun_include/page.jsp" %>
</body>
</html>
