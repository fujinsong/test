<%--
  Created by IntelliJ IDEA.
  User: Curry_for_3
  Date: 2019/11/30
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="yun_include/head.jsp"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="${ctx_modules}css/new_style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="header-wrap">
    <div id="header">
        <div id="logo">
        </div>
        <div id="top_quick_links">
            <a>${loginUserName}， 您好！今天是
                <script language=javascript type=text/javascript>
                    var enabled = 0;
                    today = new Date();
                    var day;
                    var date;
                    if (today.getDay() == 0) day = " 星期日"
                    if (today.getDay() == 1) day = " 星期一"
                    if (today.getDay() == 2) day = " 星期二"
                    if (today.getDay() == 3) day = " 星期三"
                    if (today.getDay() == 4) day = " 星期四"
                    if (today.getDay() == 5) day = " 星期五"
                    if (today.getDay() == 6) day = " 星期六"
                    date = (today.getFullYear()) + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日" + day + "";
                    document.write(date);
                </script>
                &nbsp;&nbsp;
            </a>
            <a href="javascript:void(0)" class="logout" style="margin-left: 20px;">
                <img src="${ctx_imgs}catalog/out.gif" alt="安全退出" width="46" height="20" border="0">
            </a>
        </div>
    </div>
</div>
</body>
</html>

<script language=JavaScript>
    $(".logout").click(function(){
        if (confirm("您确定现在退出系统吗？")){
            top.location = $(".ctx").val()+"user/exit";
        }
        return false;
    });
    // var islogin = $(".adminLoginStatus").val();
    // if(islogin==null || islogin=='' || islogin!='login'){
    //     //已经登录，直接跳转到登录页面
    //     window.top.location.href="adminLogin";
    // }
</script>