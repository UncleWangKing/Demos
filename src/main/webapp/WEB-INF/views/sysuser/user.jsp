<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="../common/bootstrap.jsp"%>
	<%@include file="../common/bootstrap-table.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/sysuser/user.js"></script>
</head>
<body>

	<div class="container">
		<table id="user_table" data-toggle="bootstrap-table">

		</table>
	</div>

</body>
</html>