<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>米星</title>
  <link rel="stylesheet" href="/static/css/login-style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  <style type="text/css">
    p {
      font-size: 12px;
      color: #ffffff;
      padding-bottom: 10px;
    }
  </style>
</head>
<body>
<form action="index.html" class="login">
  <h1>发生错误</h1>
  <p><c:out value="${message}" escapeXml="true"/></p>
  <input type="button" onclick="history.back()" value="返回上一页" class="login-submit">
</form>
</body>
</html>
