<%@ page import="com.mazing.core.enums.pay.WithdrawStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>提现处理列表</title>
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
				提现待处理列表
				<small></small>
			</h1>

		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<p class="text-left">提现说明：</p>
					<p class="text-left">点击下面的【提现】按钮，就会跳到支付宝打帐给用户。</p>
					<p class="text-left">基于安全的考虑，在下面点了一次【提现】按钮后，必须等到支付宝有明确的结果返回（成功或失败），才能进行下一次提现！</p>
					<p class="text-red">在上一次【提现】结果返回前，再次点击【提现】，会给用户发放两次钱，切记切记！</p>
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
											<th>提现批次号</th>
											<th>提现笔数</th>
											<th>提现总金额(元)</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.batchNo}}</td>
												<td>{{value.totalNum}}</td>
												<td>{{value.totalAmount}}</td>
												<td>
													<input type="hidden" id="withdrawDetail" value="{{value.withdrawIds}}"/>
													{{if value.batchNo != ""}}
														<a class="btn btn-primary" href="javascript:void(0)" onclick="closeBatch('{{value.batchNo}}')">关闭</a>
														<div style="display: inline">

															<button class="btn btn-primary" type="button" onclick="showInprogressDetails()">详细</button>
														</div>
													{{/if}}
													{{if value.batchNo == ""}}
														<form id="editForm" class="form-horizontal" method="post" style="display: inline"
																action="/admin/payment/withdraw/alipayapi.do" target="_blank">

															<div class="form-group" style="display: none">
																<label class="col-md-2 control-label">提现ID</label>
																	<div class="col-md-3">
																		<input class="form-control" type="text" id="withdrawIds"
																			name="withdrawIds" title="提现ID" placeholder="提现ID"
																			readonly="true" value="{{value.withdrawIds}}" />
																	</div>
															</div>
															<button class="btn btn-primary" type="submit">提现</button>
														</form>
														<div style="display: inline">
															&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
															<button class="btn btn-primary" type="button" onclick="showApplyDetails()">详细</button>
														</div>
													{{/if}}
														
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


			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="detailsTable" class="table table-bordered table-hover">
										<tr>
											<th>提现编号</th>
											<th>支付平台</th>
											<th>账户姓名</th>
											<th>账户</th>
											<th>提现金额</th>
											<th>状态</th>
											<th>备注说明</th>
											<th>操作</th>
										</tr>
										<tbody id="detailsTableBody">

										</tbody>
										<script id="detailsTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.withdrawId}}</td>
												<td>
													{{if value.accountType == 1}}
													支付宝
													{{/if}}
													{{if value.accountType == 2}}
													微信
													{{/if}}
												</td>
												<td>{{value.accountName}}</td>
												<td>{{value.accountNo}}</td>
												<td>{{cent2yuan value.amount}}</td>
												<td>
													{{if value.withdrawStatus == <%=WithdrawStatus.IN_PROGRESS.getValue()%>}}
													申请中
													{{/if}}
													{{if value.withdrawStatus == <%=WithdrawStatus.SUCCESS.getValue()%>}}
													成功
													{{/if}}
													{{if value.withdrawStatus == <%=WithdrawStatus.FAILED.getValue()%>}}
													失败
													{{/if}}
												</td>
												<td>{{value.remark}}</td>
												<td>
													{{renderOperation value.withdrawId}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
							<div class="row col-sm-12" id="detailsTablePaginator">
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

	// 是否显示操作栏
	var displayOperation = false;

	// 读取列表
	function listApplyList() {
		var storeId = $.getUrlParam("storeId");
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/payment/withdraw/loadWithdrawData",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}

	// 显示申请详细
	function showApplyDetails() {
		var withdrawIds = $('#withdrawDetail').val();
		$.post("/admin/payment/withdraw/listApplyDetail.do", {'withdrawIds':withdrawIds}, function(json){
			if (json.success) {
				displayOperation = true;  // 显示操作栏
				var htmlTxt = template('detailsTableTpl', json);
				$('#detailsTableBody').html(htmlTxt);
			}
		}, "json");
	}

	// 显示处理中的提醒申请
	function showInprogressDetails() {
		var withdrawIds = $('#withdrawDetail').val();
		$.post("/admin/payment/withdraw/listInprogressDetail.do", {'withdrawIds':withdrawIds}, function(json){
			if (json.success) {
				displayOperation = false; // 不显示操作栏
				var htmlTxt = template('detailsTableTpl', json);
				$('#detailsTableBody').html(htmlTxt);
			}
		}, "json");
	}
	
	$(function() {
		listApplyList();

		// 模版函数：输出图片的完整地址
		template.helper('renderOperation', function (withdrawId) {
			if (displayOperation) {
				return '<a href="javascript:void(0)" class="btn btn-primary" onclick="closeWithdraw(' + withdrawId + ')">拒绝</a>';
			}
			else {
				return "";
			}
		});
	});
	
	function closeBatch(batchNo){
		$.getJSON("/admin/payment/withdraw/closeBatch?batchNo="+batchNo, {}, function(json) {
			MyDialog.alert(json.message);
			
			window.location.reload();
		});
	}

	// 退回用户的退款申请
	function closeWithdraw(withdrawId){
		$.getJSON("/admin/payment/withdraw/closeWithdraw?withdrawId="+withdrawId, {}, function(json) {
			MyDialog.alert(json.message);

			window.location.reload();
		});
	}
	
</script>
</body>
</html>
