<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/manystyle.css'/>">
  </head>
  
  <body background="<c:url value='/image/index.jpg'/>">
  <div>
  	<br/>
  	<h1 style="text-align:center;font-family:楷体;color:Black">欢迎来到高考志愿填报辅助系统</h1>
    
    <br/>
    <div class="diva">
    
    <dl style="text-align:center">
 		<dt><a href="<c:url value='/number_1.jsp'/>"><b>通过分数选学校专业</b></a></dt>
 		<dt><br/></dt>
 		<dt><a href="<c:url value='/number_3.jsp'/>"><b>通过专业选学校</b></a></dt>
 		<dt><br/></dt>
 		<dt><a href="<c:url value='/number_4.jsp'/>"><b>查看院校相关信息</b></a></dt>
 		<dt><br/></dt>
 		<dt><a href="<c:url value='/number_5.jsp'/>"><b>查看专业相关信息</b></a></dt>
 		<dt><br/></dt>
	</dl>
	
	</div>
  </div>
  
  <!-- 
  <div class="divb">
  <p style="text-align:center">中国地质大学191131班</p>
  </div>
   -->
 
  </body>
</html>
