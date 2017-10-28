<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>添加美食</title>
</head>
<body class="skin-blue sidebar-mini">
<div class="wrapper">

	<!-- header -->
	<jsp:include page="/include/header.jsp"/>

	<!-- sidebar -->
	<jsp:include page="/include/sidebar.jsp" />

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1 id="titleMessage">
				添加美食
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 返回餐厅列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）编辑美食的过程中，请不要离开界面；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal" method="post">
						<div class="form-group">
							<label class="col-md-2 control-label">美食名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishName"
									   name="dishName" title="美食名称" placeholder="美食名称"
									   maxlength="12">
								<input name="dishId" id="dishId" type="hidden" value="0"/>
								<input name="storeId" id="storeId" type="hidden" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">价格</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="price" name="price"
									   title="价格" placeholder="价格" maxlength="7">
							</div>
							<div class="col-md-3">
								元
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">标签</label>
								<div class="col-md-9">
									<select class="form-control" name="categoryId">
										<option value="">无</option>
									</select>
								</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">规格</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="spec" name="spec"
									   title="规格" placeholder="规格" maxlength="16">
							</div>
							<div class="col-md-3">
								比如：400克/个
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">保质期</label>
							<div class="col-md-9">
								<select class="form-control" name="expiryTime">
									<option value="">即食即用</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">特别推介</label>
							<div class="col-md-9">
								<select class="form-control" name="promoteTag">
									<option value="">无</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">原价</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="originalPrice" name="originalPrice"
									   title="原价" placeholder="原价" maxlength="16">
							</div>
							<div class="col-md-3">
								比如：400克/个
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary"
										type="button" id="submit" onclick="submitForm()">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>

		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->


	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">

	// 修改在编辑功能下的title
	function modifyTitle() {
		var newTitle = '编辑美食';
		$('#titleMessage').html(newTitle)
		document.title = newTitle;
	}

	// 返回正在编辑的 dishId
	function getDishId() {
		var dishId = $.getUrlParam("dishId");
		//var storeId = $.getUrlParam("storeId");
		if (typeof dishId == 'undefined' || dishId == null || dishId == '') {
			dishId = '0';
		}
		return dishId;
	}

	$(function() {
		var dishId = getDishId();
		//var storeId = $.getUrlParam("storeId");
		if (dishId != '0') {
			modifyTitle();
		}
		$('#dishId').val(dishId);

	});

	// 校验参数
	function validate() {
		var message = '';
		var dishName = $.trim($('#dishName').val());
		var price = $.trim($('#price').val());
		if (dishName == '') {
			message = '请输入美食名称。';
		} else if (!$.isNumeric(price) || price > 9999.99) {
			message = '请输入正确的价格（0.01~9999.99）。';
		}
		if (message != '') {
			MyDialog.alert(message);
		}
		return (message == '');
	}

	// 提交表单内容
	function submitForm() {
		if (!validate())
			return;

		var appIds = [];

		if (appIds.length <= 0 || ids.length <= 0) {
			MyDialog.alert("请选择菜单权限。");
		}
		else {
			$.post('/admin/store/editDish.do', {

			}, function (json) {

				if (json.success) {
					//MyDialog.alert(json.message, function() {
					//	window.location.href = "/admin/account/listRole.xhtm";
					//});
					// clear form
				}
				else {
					MyDialog.alert(json.message);
				}
			}, 'json');
		}
	}
</script>
</body>
</html>
