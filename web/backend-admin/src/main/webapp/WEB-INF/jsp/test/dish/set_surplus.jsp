<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>菜品库存管理</title>
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
				菜品库存管理
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/account/listAccount.xhtm"><i class="fa fa-dashboard"></i> 菜品库存管理</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" 
						  action="/admin/test/dish/setDailyNum.do">
						<div class="form-group">
							<label class="col-md-2 control-label">菜品Id</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishId"
									   name="dishId" title="菜品Id" placeholder="菜品Id"
									   maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<!--  <div class="form-group">
							<label class="col-md-2 control-label">菜品名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishName" name="dishName"
									   title="菜品名称" placeholder="菜品名称" maxlength="30" data-minlength="1" readonly="true">
							</div>
						</div>-->

						<div class="form-group">
							<label class="col-md-2 control-label">每日供应</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dailyNum" name="dailyNum"
									   title="每日供应" placeholder="每日供应" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary"
									   type="submit" id="submit">提交</button>
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
	function loadForm(){
		var dishId = $.getUrlParam("dishId");
		var dailyNum = $.getUrlParam("dailyNum");
		$('#dishId').val(dishId);
		$('#dailyNum').val(dailyNum);
	}
	
	$(function(){
		loadForm();
		
		$('#editForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					MyDialog.alert(json.message);

				} catch (e) {
				}
			}
		});
	});
</script>
</body>
</html>