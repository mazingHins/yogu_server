<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>商家登录</title>
</head>
<link class="bootstrap library" rel="stylesheet" type="text/css"
	href="http://sandbox.runjs.cn/js/sandbox/bootstrap-2.2.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<section class="loginBox row-fluid">
			<div class="tabbable" id="tabs-634549">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-60560" data-toggle="tab">帐号登录</a></li>
					<li><a href="#panel-549981" data-toggle="tab">二维码登录</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-60560">
						<form action="/test2/login.do" method="post">
							<div>
								<input type="text" name="passport" placeholder="用户名" />
							</div>
							<div>
								<input type="password" name="password" placeholder="密码" />
							</div>
							<!-- 
						<div class="span6">
							<label><input type="checkbox" name="rememberme" />下次自动登录</label>
						</div>
						 -->
							<div class="span1">
								<input type="submit" value=" 登录 " class="btn btn-primary">
							</div>
						</form>
					</div>
					<div class="tab-pane" id="panel-549981">扫码登录。。这里显示图片。。。</div>
				</div>
			</div>
		</section>
		<!-- /loginBox -->
	</div>
	<!-- /container -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
</body>
</html>
