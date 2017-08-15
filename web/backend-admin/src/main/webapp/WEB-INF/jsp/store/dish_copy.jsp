<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.core.constant.OrderGiftTypeConstants"%>
<%@ page import="com.mazing.core.enums.pay.PayType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<title>复制菜品</title>
</head>
<style>

</style>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- header -->
		<jsp:include page="/include/header.jsp" />

		<!-- sidebar -->
		<jsp:include page="/include/sidebar.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1 id="titleMessage">
					管理数据项 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="row">
								<div class="col-sm-12">
									<p class="text-left">说明：</p>
									<p class="text-left">1. 一次只能复制一个店</p>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post"
											action="#" onsubmit="return false;">

											<div class="form-group">
												<label class="col-md-2 control-label">主店账号<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<input class="form-control" id="mainAccount"
														placeholder="请输入主店账号">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">主店密码<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<input class="form-control" id="mainPass"
														placeholder="请输入主店密码">
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-2 control-label">分店账号<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<input class="form-control" id="toAccount"
														placeholder="请输入分店账号">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">分店密码<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<input class="form-control" id="toPass"
														placeholder="请输入分店密码">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">操作类型<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<select id="model" name="model" class="form-control">
														<option value="1" selected>查看上述店的美食数据</option>
														<option value="2">Copy主店在售美食数据</option>
														<option value="3">Copy主店所有美食数据</option>
													</select>
												</div>
												<div class="col-md-3">
													<p class="text-red">最好先查看数据，确保数据没有问题再执行Copy操作</p>
												</div>
											</div>
											</br></br>
											<div class="col-md-3">
													<input class="btn btn-success" value="开始"  style="margin-left:260px;" onclick="copyDish()" >
												</div>
												</br></br>
										</form>
						
										<span id="showText"></span>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- footer -->
		<jsp:include page="/include/footer.jsp" />

		<!-- control sidebar -->
		<jsp:include page="/include/control-sidebar.jsp" />



	</div>
	<!-- ./wrapper -->
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		//开始复制
		function copyDish() {

			var mainAccount = $.trim($('#mainAccount').val());
			var mainPass = $.trim($('#mainPass').val());
			var toAccount = $.trim($('#toAccount').val());
			var toPass = $.trim($('#toPass').val());

			var model = $('select[name=model]').val();

			if (mainAccount == '') {
				MyDialog.alert("请输入主店账号");
				return;
			}
			if (mainPass == '') {
				MyDialog.alert("请输入主店密码");
				return;
			}
			if (toAccount == '') {
				MyDialog.alert("请输入分店账号");
				return;
			}
			if (toPass == '') {
				MyDialog.alert("请输入分店密码");
				return;
			}

			var mainStore = mainAccount + "|" + mainPass;
			var toStore = toAccount + "|" + toPass;

			//alert("mainStore: "+ mainStore + ", toStore: "+ toStore);
			if(model==1){
				
				copy(model, mainStore, toStore);
				
			}else{
				BootstrapDialog.show({
					title : '开始复制菜品',
					message : '确认要开始复制吗？ 信息一定要正确哦',
					buttons : [ {
						label : '确认',
						action : function(dialog) {
							dialog.close();

							copy(model, mainStore, toStore);

						}
					}, {
						label : '取消',
						action : function(dialog) {
							dialog.close();
						}
					} ]
				});
			}
			
		
		}
		
		
		
		function copy(model, mainStore, toStore){
			
			$.post('/admin/store/copyDish.do', {
				'model' : model,
				'mainStore' : mainStore,
				'toStore' : toStore
			}, function(json) {

				if (json.success) {

					//展示返回的数据
					if(model==1){
						//查询
					}else{
						
						MyDialog.alert("复制完成, 请查看复制的详细结果");
						
						$('#toPass').val("");//置空密码，以免误操作出错
						
					}
					//render
					$('#showText').html(json.message);

				} else {

					MyDialog.alert(json.message);
				}
			}, 'json');
		}
	</script>
</body>
</html>
