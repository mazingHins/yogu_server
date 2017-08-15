<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>订单列表</title>
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
				订单列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/test/store/list.xhtm"><i class="fa fa-dashboard"></i> 返回商家列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="col-sm-3">
								<div class="input-group input-group-sm">
									<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
									</span>
								</div>
							</div>
							<div class="col-sm-9" style="text-align: right;">

							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th>订单号</th>
											<th>状态</th>
											<th>电话</th>
											<th>收货联系人</th>
											<th>收货地址</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.orderId}}
												</td>
												<td>{{value.orderNo}}</td>
												<td>
													{{if value.status == 10}}
													未付款
													{{/if}}
													{{if value.status == 15}}
													已付款
													{{/if}}
													{{if value.status == 20}}
													已接单
													{{/if}}
													{{if value.status == 25}}
													已配送
													{{/if}}
													{{if value.status == 35}}
													买家确认收货
													{{/if}}
													{{if value.status == 65}}
													已取消
													{{/if}}

												</td>
												<td>{{value.phone}}</td>
												<td>{{value.contacts}}</td>
												<td>{{value.address}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													<!--
													<a href="/admin/test/order/query?orderId={{value.orderId}}">查看订单</a>
													-->
													{{if value.status == 10}}
													<!-- 未付款 OrderStatus.NON_PAYMENT -->
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'pay')">付款</a> &nbsp; &nbsp;
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'cancel')">取消订单</a>
													{{/if}}
													{{if value.status == 15}}
													<!-- 已付款 HAS_PAYMENT -->
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'accept')">接单</a>
													{{/if}}
													{{if value.status == 20}}
													<!-- 已接单 RECEIVER_ORDER -->
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'delivery')">配送</a>
													{{/if}}
													{{if value.status == 25}}
													<!-- 配送 DELIVERY -->
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'storeConfirm')">商家确认</a> &nbsp; &nbsp;
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'userConfirm')">买家确认</a>
													{{/if}}
													{{if value.status == 35}}
													<!-- 买家已确认收货 CONFIRM_RECEIPT_USER -->
													<a href="javascript:void(0)" onclick="doAction({{value.orderId}}, 'comment')">评论</a>
													{{/if}}
													{{if value.status == 65}}
													<!-- 已取消 -->
													已取消
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
	function search() {
		var storeId = $.getUrlParam("storeId");
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/test/order/query",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}

	// 重新加载列表
	function reloadPage() {
		var page = $('#listTable').artPage();
		if (page) {
			$('#listTable').artPaginateNext(page);
		}
	}

	// 执行 action
	// action: cancel/pay/accept/delivery/storeConfirm
	function doAction(orderId, action) {
		$.getJSON('/admin/test/order/perform', {'orderId':orderId, 'action': action}, function(json) {
			MyDialog.alert(json.message);
			if (json.success) {
				reloadPage();
			}
		});
	}

	$(function(){
		var storeId = $.getUrlParam("storeId");
		if (storeId) {
			search();
		}
		else {
			MyDialog.alert("请到商家界面选择一家商家查看订单", function() {
				window.location.href = "/admin/test/store/list.xhtm";
			});
		}
	});
</script>
</body>
</html>
