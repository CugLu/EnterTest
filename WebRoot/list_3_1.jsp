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

<title>My JSP 'list_3_1.jsp' starting page</title>

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
		<c:when test="${empty schoollist_3_1 }">
			<%
				str = "额，您输入的专业好像错了！ 专业：";
			%>
			<h2 style="text-align:center;font-family:楷体;color:Black"><%=str%>${major
				}
			</h2>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>

	<table align="center" width="33%" border="2">
		<c:forEach items="${schoollist_3_1 }" var="entry">
			<tr style="text-align:center">
				<td><a
					href="<c:url value='/NumberServlet?method=MajToSch&major=${entry }'/>">${entry
						}</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
