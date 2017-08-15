<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>退款申请列表</title>

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
			<h1>
				用户退款申请列表（待审批）
				<small></small>
			</h1>
			<!-- 
			<ol class="breadcrumb">
			</ol>
			 -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<p class="text-left">退款审批说明：</p>
					<p class="text-left">系统自动审批下面的退款申请，为了加快速度，可以人审批。</p>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>退款编号</th>
											<th>支付平台</th>
											<th>订单编号</th>
											<th>订单金额（元）</th>
											<th>退款金额（元）</th>
											<th>备注说明</th>
											<th>购买用户昵称</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.refundNo}}</td>
												<td>
													{{if value.payMode == 1}}支付宝{{/if}}
													{{if value.payMode == 2}}微信{{/if}}
												</td>
												<td>{{value.internalTradeNo}}</td>
												<td>{{cent2yuan value.totalFee}}</td>
												<td>{{cent2yuan value.refundFee}}</td>
												<td>{{value.remark}}</td>
												<td>{{value.nickname}}</td>
												<td>
													<a href="javascript:void(0)" onclick="agreeApply({{value.refundNo}})">同意退款</a>
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
							<div class="row col-sm-12" id="listPaginator">
							</div>
						</div><!-- /.box-body -->
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row -->
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

	// 加载数据
	function loadApplyData() {
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/payment/refund/loadApplyData",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {}
		});
	}

	// 同意退款
	function agreeApply(refundNo) {
		$.post("/admin/payment/refund/agreeApply.do", {'refundNo':refundNo}, function(json){
			MyDialog.alert(json.message);
			if (json.success) {
				loadApplyData(); // 重新加载数据
			}
		}, "json");
	}

	
	$(function(){
		loadApplyData();
	});
</script>
</body>
</html>
