<%--
  Created by IntelliJ IDEA.
  User: yun_lian
  Date: 2018/4/19
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="pageDiv">
    <button type="button" class="btn btn-default btn-sm goHome">
        <span class="glyphicon glyphicon-fast-backward"></span>&nbsp;首页
    </button>
    <button type="button" class="btn btn-default btn-sm goBack">
        <span class="glyphicon glyphicon-step-backward"></span>&nbsp;上一页
    </button>
    <button type="button" class="btn btn-default btn-sm goNext">
        <span class="glyphicon glyphicon-step-forward"></span>&nbsp;下一页
    </button>
    <button type="button" class="btn btn-default btn-sm goEnd">
        <span class="glyphicon glyphicon-fast-forward"></span>&nbsp;末页
    </button>
    <div class="input-group col-lg-6"
         style="background-color: aquamarine;width: auto;float: right;margin-left: 5px;">
        <span class="input-group-addon" style="width: 50px;">去第</span>
        <input type="text" class="form-control goNo" name="name" value="${page.pageNo}" style="width: 60px">
        <span class="input-group-btn goPageBtn">
            <button class="btn btn-default" type="button">页&emsp;Go!</button>
        </span>
    </div>
    <div class="input-group"
         style="background-color: aquamarine;width: auto;float: right;;margin-left: 5px;height: 34px;">
        <span class="input-group-addon" style="width: 50px;">${page.pageNo}/${page.totalPage}</span>
    </div>

</div>
