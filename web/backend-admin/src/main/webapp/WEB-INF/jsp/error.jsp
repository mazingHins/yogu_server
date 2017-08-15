<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>错误页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css">
        body {
            background: #f3f3e1;
        }

        .wrap {
            margin: 0 auto;
            width: 1000px;
        }

        .logo {
            margin-top: 50px;
        }

        .logo h1 {
            font-size: 130px;
            color: #8F8E8C;
            text-align: center;
            margin-bottom: 1px;
            text-shadow: 1px 1px 6px #fff;
        }

        .logo p {
            color: rgb(228, 146, 162);
            font-size: 30px;
            margin-top: 1px;
            text-align: center;
        }

        .logo p span {
            color: lightgreen;
        }

        .sub a {
            color: white;
            background: #8F8E8C;
            text-decoration: none;
            padding: 7px 120px;
            font-size: 13px;
            font-family: arial, serif;
            font-weight: bold;
            -webkit-border-radius: 3em;
            -moz-border-radius: .1em;
            -border-radius: .1em;
        }

        .footer a {
            color: rgb(228, 146, 162);
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="logo">
        <h1>出错啦!</h1>
        <p>错误详情：
            <!-- it.message 是 jersey 的风格 -->
            <c:out value="${message}" escapeXml="true"/>
        </p>
        <div class="sub">
            <p>
                <a href="#" onClick="javascript:window.history.back(-1);">返回</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>