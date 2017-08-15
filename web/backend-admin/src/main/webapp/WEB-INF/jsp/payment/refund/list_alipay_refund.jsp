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
					支付宝退款处理 <small></small>
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
						<p class="text-left">退款说明：</p>
						<p class="text-left">点击下面的【退款】按钮，就会跳到支付宝退款给用户。</p>
						<p class="text-red">对同一个订单的退款可以多次进行，只会成功一次，但是建议等到上一次的结果处理完毕再进行下一次的退款处理。</p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<form id="editForm" class="form-horizontal" method="post"
											action="alipayapi.do" target="_blank">
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
								<!-- <div class="row col-sm-12" id="listPaginator"></div> -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
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
												<td><a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.internalTradeNo}}">{{value.internalTradeNo}}</a></td>
												<td>{{cent2yuan value.totalFee}}</td>
												<td>{{cent2yuan value.refundFee}}</td>
												<td>{{value.remark}}</td>
												<td>{{value.nickname}}</td>
											</tr>
											{{/each}}
										</script>
									</table>


									</div>
								</div>
								<!-- <div class="row col-sm-12" id="listPaginator"></div> -->
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
			// 2016-02-04 modify by hins 内容：调整了顺序，先加载当前批次退款数据，再根据加载的数据编号去查询列表
			$.getJSON("/admin/payment/refund/alipay/loadRefundData", {}, function(json) {
				if (json.success) {
					var value = json.object;
					$('#refundNo').val(value.no);
					$('#number').val(value.number);
					$('#refundFee').val(value.refundFeeByYuan);
					
					$('#listTable').artPaginate({
						// 获取数据的地址
						url: "/admin/payment/refund/alipay/loadRefundList",
						// 显示页码的位置
						paginator: 'listPaginator',
						// 模版ID
						tpl: 'listTableTpl',
						// 请求的参数表，默认page=1, pageSize=20
						params: {'no':value.no}
					});
					
					
				} else {
					MyDialog.alert(json.message);
				}
			});
			
		});
	</script>
</body>
</html>
