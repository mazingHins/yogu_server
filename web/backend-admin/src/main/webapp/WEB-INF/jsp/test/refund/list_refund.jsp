<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>退款处理列表</title>
<script type="text/javascript">
	
</script>
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
				<h1>
					退款处理列表 <small></small>
				</h1>
				<!-- 
			<ol class="breadcrumb">
				<li><a href="/admin/test/range/edit.xhtm?storeId=${param.storeId}"><i class="fa fa-dashboard"></i>新增配送范围</a></li>
			</ol>
			 -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<form id="editForm" class="form-horizontal" method="post"
											action="alipayapi.xhtm">
											<div class="form-group">
												<label class="col-md-2 control-label">退款编号</label>
												<div class="col-md-3">
													<input class="form-control" type="text" id="refundNo"
														name="refundNo" title="退款编号" placeholder="退款编号"
														maxlength="30" data-minlength="2" readonly="true" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">退款数量</label>
												<div class="col-md-3">
													<input class="form-control" type="text" id="number"
														name="number" title="退款数量" placeholder="退款数量"
														maxlength="30" data-minlength="2" readonly="true" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">退款总金额(元)</label>
												<div class="col-md-3">
													<input class="form-control" type="text" id="refundFee"
														name="refundFee" title="退款总金额" placeholder="退款总金额"
														maxlength="30" data-minlength="2" readonly="true" />
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-offset-2">
													<button class="btn btn-primary" type="submit" id="submit">退款</button>
												</div>
											</div>
										</form>


									</div>
								</div>
								<div class="row col-sm-12" id="listPaginator"></div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
		$(function() {
			$.getJSON("/admin/test/refund/loadRefundData", {}, function(json) {
				if (json.success) {
					var value = json.object;
					$('#refundNo').val(value.no);
					$('#number').val(value.number);
					$('#refundFee').val(value.refundFee);
				} else {
					MyDialog.alert(json.message);
				}
			});
		});
	</script>
</body>
</html>
