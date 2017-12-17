<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table,tr,th,td
		{
			border:1px solid black;
			border-coolapse:collapse;
			margin-top:auto;
		}
	</style>
  </head>
  
  <body>
  	<form action="/user/save" method="post">
  		<input type="hidden" name="id" value="${user.id }"/><br />
  		用户名<input type="text" name="username" value="${user.username }"/><br />
  		密码<input type="text" name="password" value="${user.password }"/><br />
  		<input type="submit" value="提交"/>
  	</form>
  </body>
</html>
