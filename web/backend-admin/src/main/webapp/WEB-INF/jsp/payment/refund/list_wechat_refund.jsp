<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>微信退款处理列表</title>
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
					微信退款处理 <small></small>
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
						<ul id="cacheTab" class="nav nav-tabs">
							<li class="active"><a href="#refundList" data-toggle="tab">
									退款列表 </a></li>
							<li><a href="#queryRefund" data-toggle="tab">退款查询</a></li>
						</ul>


						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 退款列表 -->
							<div class="tab-pane fade in active" id="refundList">
								<div class="row">
									<div class="col-xs-12">
										<p class="text-left">退款说明：</p>
										<p class="text-left">点击下面的【退款】按钮，就表示直接同意退款给用户。</p>
										<p class="text-red">对同一个订单的退款可以多次进行，只会成功一次，但是建议等到上一次的结果处理完毕再进行下一次的退款处理。</p>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12">
										<div class="box">
											<div class="box-body">
												<div class="row">
													<div class="col-sm-12">
														<form id="editForm" class="form-horizontal" method="post">
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
																	<button class="btn btn-primary" type="button"
																		id="submit" onclick="submitForm()">退款</button>
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
														<table id="listTable"
															class="table table-bordered table-hover">
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


							</div>
							<!-- tab end 退款列表 -->

							<!-- tab start 退款查询 -->
							<div class="tab-pane fade in" id="queryRefund">
								<div class="box box-solid">
									<form role="form">
										<div class="box-body">
											<div class="row">
												<div class="form-horizontal col-sm-12">
													<div class="form-group">
														<label class="col-sm-2 control-label"> 支付编号 </label>
														<div class="col-sm-8">
															<input type="text" name="payNo" id="payNo" class="form-control" placeholder="请输入一个支付编号" maxlength="100" />
														</div>
													</div>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-2">
															<button type="button" class="btn btn-primary" onclick="queryWechat()">查询</button>
														</div>
													</div>
													<div id="queryWechatResult"></div>
												</div>
											</div>
										</div>
										<!-- /.box-body -->
									</form>
								</div>
								<!-- /.box -->
							</div>
							<!-- tab end 退款查询 -->

						</div>
					</div>
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

	<script id="queryTemplate" type="text/html">

		<div class="form-group">
			<label class="col-sm-2 control-label">业务结果 </label>
			<div class="col-sm-8">
				{{if resultCode == 1}}退款申请接收成功{{/if}}
				{{if resultCode == 0}}退款申请接收失败{{/if}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">退款状态 </label>
			<div class="col-sm-8">
				{{refundStatusDes}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平台退款编号 </label>
			<div class="col-sm-8">
				{{refundNo}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">微信退款编号 </label>
			<div class="col-sm-8">
				{{wechatRefundNo}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">错误代码 </label>
			<div class="col-sm-8">
				{{errCode}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">错误代码描述 </label>
			<div class="col-sm-8">
				{{errCodeDes}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">订单总金额 </label>
			<div class="col-sm-8">
				{{cent2yuan totalFee}} 元
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">退款总金额 </label>
			<div class="col-sm-8">
				{{cent2yuan refundFee}} 元
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">退款入账账户 </label>
			<div class="col-sm-8">
				{{refundRecvAccout}}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">接口日志 </label>
			<div class="col-sm-8">
				{{apiLog}}
			</div>
		</div>
	</script>

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		$(function() {
			var no;	// 当前批次退款数据编号，此编号是临时数据，有时效
			// 先加载当前批次退款数据，再加载列表
			$.getJSON("/admin/payment/refund/wechat/loadRefundData", {}, function(json) {
				if (json.success) {
					var value = json.object;
					$('#refundNo').val(value.no);
					$('#number').val(value.number);
					$('#refundFee').val(value.refundFeeByYuan);
					
					$('#listTable').artPaginate({
						// 获取数据的地址
						url: "/admin/payment/refund/wechat/loadRefundList",
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
		
		// 提交表单内容
		function submitForm() {
				$.post('/admin/payment/refund/wechat/wechatapi.do', {
					'refundNo':$('#refundNo').val()
				}, function (json) {
					MyDialog.alert(json.message);
				}, 'json');
		}
		
		// 查询微信退款结果
		function queryWechat() {
			var payNo = $.trim($('#payNo').val());
			if (payNo) {
				$.getJSON('/admin/payment/refund/wechat/queryWechatByApi', {'payNo': payNo}, function(json) {
					if (json.success) {
						// 使用模版显示收入信息
						var htmlTxt = template('queryTemplate', json.object);
						$('#queryWechatResult').html(htmlTxt);
					}
					else {
						MyDialog.alert(json.message);
					}
				});
			}
		}
	</script>
</body>
</html>
