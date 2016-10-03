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

<title>My JSP 'List_2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/manystyle.css'/>">

</head>

<body background="<c:url value='/image/show.jpg'/>">
	<h1 style="text-align:center;font-family:楷体;color:Black">恭喜您！有相关学校的专业可报！</h1>
	<h1 style="text-align:center;font-family:楷体;color:Black">查询结果</h1>
	<hr />
	<h1 style="text-align:center;font-family:楷体;color:Black">${school}</h1>
	<table align="center" width="60%" border="1">
		<tr>
			<th>专业名称</th>
			<th>投档成绩</th>
			<th>搜索链接</th>
		</tr>

		<c:forEach items="${schoollist_2 }" var="entry">
			<tr>
				<td>${entry.zymc }</td>
				<td>${entry.tdcj }</td>
				<td><a
					href="https://www.baidu.com/s?wd=${school}${entry.zymc }">搜索</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
