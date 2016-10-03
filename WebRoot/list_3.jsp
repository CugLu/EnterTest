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

<title>My JSP 'list_3.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/manystyle.css'/>">

</head>

<body background="<c:url value='/image/show.jpg'/>">
	<h1 style="text-align:center;font-family:楷体;color:Black">查询结果</h1>

	<%
		String str = null;
	%>
	<c:choose>
		<c:when test="${empty schoollist_3_3 && empty schoollist_3_4}">
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

	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black"><%=str%>${major }
	</h2>

	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black">年份：${year1 }</h2>
	<table align="center" width="60%" border="1">
		<tr>
			<th>院校名称</th>
			<th>科类</th>
			<th>该专业录取平均分</th>
			<th>该校投档成绩(理工)</th>
			<th>该校投档成绩(文史)</th>
		</tr>
		<%
			String STR = "";
		%>
		<c:forEach items="${schoollist_3_3 }" var="entry">
			<tr>
				<td>${entry.YXMC }</td>
				<td>${entry.KL }</td>
				<c:if test="${empty entry.AVE_GRADE }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.AVE_GRADE }</td>
				<%
					STR = "";
				%>
				<c:if test="${empty entry.PRE_GRADE_OF_LiGong }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.PRE_GRADE_OF_LiGong }</td>
				<%
					STR = "";
				%>
				<c:if test="${empty entry.PRE_GRADE_OF_WenShi }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.PRE_GRADE_OF_WenShi }</td>
				<%
					STR = "";
				%>
			</tr>
		</c:forEach>

	</table>

	<br />
	<hr />

	<h2 style="text-align:center;font-family:楷体;color:Black">年份：${year2 }</h2>
	<table align="center" width="60%" border="1">
		<tr>
			<th>院校名称</th>
			<th>科类</th>
			<th>该专业录取平均分</th>
			<th>该校投档成绩(理工)</th>
			<th>该校投档成绩(文史)</th>
		</tr>

		<c:forEach items="${schoollist_3_4 }" var="entry">
			<tr>
				<td>${entry.YXMC }</td>
				<td>${entry.KL }</td>
				<c:if test="${empty entry.AVE_GRADE }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.AVE_GRADE }</td>
				<%
					STR = "";
				%>
				<c:if test="${empty entry.PRE_GRADE_OF_LiGong }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.PRE_GRADE_OF_LiGong }</td>
				<%
					STR = "";
				%>
				<c:if test="${empty entry.PRE_GRADE_OF_WenShi }">
					<%
						STR = "----";
					%>
				</c:if>
				<td><%=STR%>${entry.PRE_GRADE_OF_WenShi }</td>
				<%
					STR = "";
				%>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
