<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'number_3.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body background="<c:url value='/image/cin.jpg'/>">

	<hr />
	<h1 style="text-align:center;font-family:楷体;color:Black">通过专业选学校</h1>
	<br />
	<br />

	<form style="text-align:center"
		action="<c:url value='/NumberServlet?method=GetMaj'/>" method="post">
		请输入您想查的专业：<input style="width:300px;height:40px" type="text" name="major" /> <input style="width:60px;height:40px" type="submit"
			value="查询" />

	</form>
	<br />
	<br />
</body>
</html>
