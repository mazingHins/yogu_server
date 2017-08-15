<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎进入米星管理后台</title>
</head>

<body>
	<div>请选择一个门店进行操作：</div>
	<c:forEach items="${it}" var="var">
		<div>
			<a href="/test2/store/index?storeId=${var.storeId}">${var.storeName}</a>
		</div>
	</c:forEach>

	<div>**************************</div>
	<div>
		<a href="/test2/store/open.xhtm">新建一个门店</a>
	</div>
</body>
</html>
