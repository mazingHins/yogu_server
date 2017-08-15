<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>游戏运营平台-帐号管理-修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/include/header.jsp"></jsp:include>
<link href="/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<!-- 顶部导航 -->
		<jsp:include page="/include/top_nav.jsp"></jsp:include>
		<!-- 左边导航 -->
		<jsp:include page="/include/left_nav.jsp"></jsp:include>
		<div id="page-wrapper" style="height: 100%;">
			<div>
				<div class="row">
					<div class="col-lg-12">
						<h2 class="page-header">账号管理-修改密码</h2>
					</div>
					<!-- /.col-lg-12 -->
				</div>

				<div class="row">
					<div class="col-lg-12">
						<form class="form-horizontal" method="post">
							
							<div class="form-group">
								<label class="col-lg-2 control-label">原密码</label>
								<div class="col-lg-3">
									<input type="password" id="old" required class="form-control"
										placeholder="原密码">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">新密码</label>
								<div class="col-lg-3">
									<input type="password" id="current" name="passport" required
										class="form-control" placeholder="新密码">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">再次输入新密码</label>
								<div class="col-lg-3">
									<input type="password" id="confirm" name="passport" required
										class="form-control" placeholder="再次输入新密码">
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-4 col-lg-offset-2">
									<input class="btn btn-primary submit-btn" value="修改"
										type="button" id="update">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript"
	src="/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="/js/plugins/validate/jquery-validate.bootstrap-tooltip.min.js"></script>
<script type="text/javascript" src="/js/plugins/box/bootbox.min.js"></script>
<script type="text/javascript">
	function validate() {
		var old = $.trim($('#old').val());
		var current = $.trim($('#current').val());
		var confirm = $.trim($('#confirm').val());
		var message = '';
		if (old.length < 1) {
			message = '旧密码长度不对。';
		}
		else if (current.length < 8) {
			message = '新密码长度不能小于8。';
		}
		else if (current != confirm) {
			message = '确认密码和密码不相等。';
		}
		if (message != '') {
			bootbox.alert({
				title : '提示信息',
				message : message
			});
		}
		
		return (message == '');
	}
	$('#update').click(function(e) {
		if (!validate()) {
			return;
		}
		$.post("/account/saveNewPassword.do", {
			old : $.trim($('#old').val()),
			current : $.trim($('#current').val()),
			confirm : $.trim($('#confirm').val())
		}, function(json) {
			bootbox.alert({
				title : '提示信息',
				message : json.message
			});
			if (json.success) {
				$('#old').val('');
				$('#current').val('');
				$('#confirm').val('');
			}
			
		}, 'json');
	});
</script>
</html>