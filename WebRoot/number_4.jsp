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

<title>My JSP 'number_4.jsp' starting page</title>

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
	<h1 style="text-align:center;font-family:楷体;color:Black">了解近年学校情况</h1>
	<br />
	<br />

	<form style="text-align:center"
		action="<c:url value='/NumberServlet?method=InfoSchool'/>"
		method="post">

		请输入您想报的学校：<input style="width:300px;height:40px" type="text" name="school" /> <input style="width:60px;height:40px" type="submit"
			value="查询" />

	</form>
	<br />
	<br />
</body>
</html>
