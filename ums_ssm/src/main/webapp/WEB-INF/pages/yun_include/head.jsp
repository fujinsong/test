<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=EmulateIE7"/>
<%--引用的资源--%>
<script src="${ctx_sources}jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${ctx_sources}jquery/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx_sources}jquery/additional-methods.min.js" type="text/javascript"></script>

<script src="${ctx_sources}My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx_sources}bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx_sources}bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="${ctx_sources}bootstrap-treeview-1.2.0/dist/bootstrap-treeview.min.js" ></script>
<script src="${ctx_sources}layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
<script src="${ctx_sources}layui/layui.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctx_sources}layui/css/layui.css">
<%--公用资源--%>
<link href="${ctx_modules}css/common_jsp.css" type="text/css" rel="stylesheet"/>
<input type="hidden" class="ctx" value="${ctx}">
<input type="hidden" class="loginStatus" value="${loginStatus}">
<input type="hidden" class="loginUserName" value="${loginUserName}">