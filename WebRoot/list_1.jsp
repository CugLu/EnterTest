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

<title>My JSP 'list_1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/manystyle.css'/>">

</head>

<body background="<c:url value='/image/show.jpg'/>">
	<%
		String str = null;
	%>
	<c:choose>
		<c:when test="${grade>150 }">
			<%
				str = "恭喜您！有学校可报！";
			%>
		</c:when>
		<c:otherwise>
			<%
				str = "额，没有学校！";
			%>
		</c:otherwise>
	</c:choose>

	<hr />

	<h1 style="text-align:center;font-family:楷体;color:Black"><%=str%></h1>
	<h1 style="text-align:center;font-family:楷体;color:Black">查询结果</h1>
	<br />

	<hr />

	<h3 style="text-align:center;font-family:楷体;color:Black">您的分数：${grade
		}</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">今年您的排名：${location
		}</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">${year1
		}年您的排名：${lastlocation_1 }</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">对应${year1
		}年的分数：${lastgrade_1 }</h3>
	<table align="center" width="60%" border="1">
		<tr>
			<th width="20%">学校名称</th>
			<th width="20%">${year1 }年最低投档成绩</th>
			<th width="10%">备注</th>
			<th width="10%">搜索链接</th>
		</tr>

		<c:forEach items="${schoollist_1_1 }" var="entry">
			<tr>
				<td>${entry.yxmc } <a
					href="<c:url value='/NumberServlet?method=GraToMaj&major=${major }&grade=${grade }&school=${entry.yxmc }&year=${year1 }'/>">___看看专业</a>
				</td>
				<td>${entry.tdcj }</td>
				<c:choose>
					<c:when test="${entry.tdcj <= 150}">
						<td><b>特长：${entry.zymc }</b></td>
					</c:when>
					<c:otherwise>
						<td>无</td>
					</c:otherwise>
				</c:choose>
				<td><a href="https://www.baidu.com/s?wd=${entry.yxmc }">去看看呗</a>
				</td>
			</tr>
		</c:forEach>

	</table>

	<br />
	<hr />

	<h3 style="text-align:center;font-family:楷体;color:Black">您的分数：${grade
		}</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">今年您的排名：${location
		}</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">${year2
		}年您的排名：${lastlocation_2 }</h3>
	<h3 style="text-align:center;font-family:楷体;color:Black">对应${year2
		}年的分数：${lastgrade_2 }</h3>
	<table align="center" width="60%" border="1">
		<tr>
			<th width="20%">学校名称</th>
			<th width="20%">${year2 }年最低投档成绩</th>
			<th width="10%">备注</th>
			<th width="10%">搜索链接</th>
		</tr>

		<c:forEach items="${schoollist_1_2 }" var="entry">
			<tr>
				<td>${entry.yxmc } <a
					href="<c:url value='/NumberServlet?method=GraToMaj&major=${major }&grade=${grade }&school=${entry.yxmc }&year=${year2 }'/>">___看看专业</a>
				</td>
				<td>${entry.tdcj }</td>
				<c:choose>
					<c:when test="${entry.tdcj <= 150}">
						<td><b>特长：${entry.zymc }</b></td>
					</c:when>
					<c:otherwise>
						<td>无</td>
					</c:otherwise>
				</c:choose>
				<td><a href="https://www.baidu.com/s?wd=${entry.yxmc }">去看看呗</a>
				</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
