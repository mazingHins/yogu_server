<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>美食分类管理</title>
</head>
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
					交易中 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="/admin/checking/account/list.xhtm"><i
							class="fa fa-dashboard"></i> 对账管理</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<form id="editForm" class="form-horizontal" method="post"
							action="/admin/test/dish/category/modifyName.do">
							<div class="form-group">
								<label class="col-md-2 control-label">订单金额</label>
								<div class="col-md-3">
									<input class="form-control" type="text" id="orderFee"
										name="orderFee" title="订单金额" placeholder="订单金额" maxlength="30"
										data-minlength="2" readonly="true">
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">账户余额</label>
								<div class="col-md-3">
									<input class="form-control" type="text" id="dealingBalance"
										name="dealingBalance" title="账户余额" placeholder="账户余额"
										maxlength="30" data-minlength="2" readonly="true">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">流水金额</label>
								<div class="col-md-3">
									<input class="form-control" type="text" id="dealingStatement"
										name="dealingStatement" title="流水金额" placeholder="流水金额"
										maxlength="30" data-minlength="2" readonly="true">
								</div>
							</div>
						</form>
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

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		function loadForm() {
			var categoryId = $.getUrlParam("categoryId");
			var categoryName = $.getUrlParam("categoryName");
			$('#categoryId').val(categoryId);
			$('#categoryName').val(categoryName);
		}

		$(function() {
			loadForm();
		});
	</script>
</body>
</html>