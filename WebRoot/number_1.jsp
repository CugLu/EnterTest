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

<title>My JSP 'number_1.jsp' starting page</title>

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
	<h1 style="text-align:center;font-family:楷体;color:Black">通过分数选学校</h1>
	<br />
	<br />

	<form style="text-align:center"
		action="<c:url value='/NumberServlet?method=GraToSch'/>" method="post">
		请选择科类： <select style="width:150px;height:30px" name="major">
			<option value="ligong">理工</option>
			<option value="wenshi">文史</option>
		</select> <br />
		<br /> 请输入您的分数：<input style="width:300px;height:40px" type="text"
			name="grade" /> <input style="width:60px;height:40px" type="submit"
			value="查询" />

	</form>
	<br />
	<br />

</body>
</html>
