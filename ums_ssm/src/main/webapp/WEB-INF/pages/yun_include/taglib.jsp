<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--动态获取项目部署名，并取一个变量存放--%>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>
<c:set var="ctx_modules" value="${ctx}static/modules/"/>
<c:set var="ctx_sources" value="${ctx}static/sources/"/>
<%--图片素材地址--%>
<c:set var="ctx_imgs" value="${ctx_modules}images/"/>
<%--图片服务器--%>
<c:set var="imgServer" value="http://localhost:80/imageServer" />