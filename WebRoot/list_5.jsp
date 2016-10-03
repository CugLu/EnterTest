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

<title>My JSP 'list_5.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/manystyle.css'/>">

</head>

<body background="<c:url value='/image/show.jpg'/>">

	<hr />

	<h1 style="text-align:center;font-family:楷体;color:Black">查询结果</h1>

	<%
		String str = null;
	%>
	<c:choose>
		<c:when
			test="${empty majorlist_5_1 && empty majorlist_5_2 && empty majorlist_5_3 }">
			<%
				str = "额，您输入的专业好像错了！ 专业名：";
			%>
		</c:when>
		<c:otherwise>
			<%
				str = "您查询的专业为：";
			%>
		</c:otherwise>
	</c:choose>

	<h2 style="text-align:center;font-family:楷体;color:Black"><%=str%>${major_5 }
	</h2>

	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black">${year_5 }年计划</h2>
	<table align="center" width="60%" border="1">
		<tr>
			<th>专业代码</th>
			<th>专业名称</th>
			<th>院校代号</th>
			<th>院校名称</th>
			<th>科类</th>
			<th>招生计划数</th>
		</tr>
		<c:forEach items="${majorlist_5_1 }" var="entry">
			<tr>
				<td>${entry.zydm }</td>
				<td>${entry.zymc }</td>
				<td>${entry.yxdh }</td>
				<td>${entry.yxmc }</td>
				<td>${entry.kl }</td>
				<td>${entry.jhs }</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black">${year_5-1 }年计划</h2>
	<table align="center" width="60%" border="1">
		<tr>
			<th>专业代码</th>
			<th>专业名称</th>
			<th>院校代号</th>
			<th>院校名称</th>
			<th>科类</th>
			<th>招生计划数</th>
		</tr>
		<c:forEach items="${majorlist_5_2 }" var="entry">
			<tr>
				<td>${entry.zydm }</td>
				<td>${entry.zymc }</td>
				<td>${entry.yxdh }</td>
				<td>${entry.yxmc }</td>
				<td>${entry.kl }</td>
				<td>${entry.jhs }</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black">${year_5-2 }年计划</h2>
	<table align="center" width="60%" border="1">
		<tr>
			<th>专业代码</th>
			<th>专业名称</th>
			<th>院校代号</th>
			<th>院校名称</th>
			<th>科类</th>
			<th>招生计划数</th>
		</tr>
		<c:forEach items="${majorlist_5_3 }" var="entry">
			<tr>
				<td>${entry.zydm }</td>
				<td>${entry.zymc }</td>
				<td>${entry.yxdh }</td>
				<td>${entry.yxmc }</td>
				<td>${entry.kl }</td>
				<td>${entry.jhs }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
