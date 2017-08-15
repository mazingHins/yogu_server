<%@ page import="static com.mazing.core.enums.merchant.OrderPickType.*" %>
<%@ page import="com.mazing.core.enums.order.OrderIsSysConfirm" %>
<%@ page import="com.mazing.core.enums.order.OrderStatus" %>
<%@ page import="com.mazing.core.enums.pay.PayType" %>
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
				<li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 返回餐厅列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<p class="text-left">（1）本界面操作和商家后台类似，搜索结果在新界面展示；</p>
					<p class="text-left">（2）选了配送类型后，自动刷新当前tab；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<form class="form-inline">
						配送类型：
						<select id="pickType" name="pickType" class="form-control" onchange="refresh(true)">
							<option value="<%=MERCHANT_DELIVERY.getValue()%>" selected>商家配送</option>
							<option value="<%=SELF_PICK.getValue()%>">自提</option>
							<option value="<%=EXPRESS_DELIVERY.getValue()%>">快递</option>
						</select>
						<!--
						<input type="text" class="form-control" id="orderNo" name="orderNo" placeholder="订单号"/>
						<input type="button" value="搜索"/>
						-->

					</form>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<ul id="orderTypeTab" class="nav nav-tabs">
						<li class="active">
							<a href="#inProgressOrders" data-toggle="tab">
								进行中的订单
							</a>
						</li>
						<li><a href="#finishedOrders" data-toggle="tab">完成的订单</a></li>
					</ul>

					<div id="orderTypeTabContent" class="tab-content">
						<!-- tab start -->
						<div class="tab-pane fade in active" style="background-color: #fff;" id="inProgressOrders">
							<div class="row">
								<div class="col-xs-12">
									&nbsp;
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<!-- 进行中的订单 -->
									<ul id="inProgressOrderTab" class="nav nav-tabs">
										<li class="active">
											<a href="#newOrders" data-toggle="tab">
												新单
											</a>
										</li>
										<li><a href="#onCookingOrders" data-toggle="tab" >制作</a></li>
										<li><a href="#finishCookingOrders" data-toggle="tab">待发货</a></li>
										<li><a href="#onDeliveryOrders" data-toggle="tab">待收货</a></li>
										<li><a href="#refundOrders" data-toggle="tab">退款</a></li>
									</ul>

									<div id="inProgressOrderTabContent" class="tab-content">
										<!-- tab start 新单 -->
										<div class="tab-pane fade in active" style="background-color: #fff;" id="newOrders">
											<table id="newOrdersTable" class="table table-bordered table-hover">
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

											</table>
											<div class="row col-sm-12" id="newOrdersPaginator">
											</div>
										</div>
										<!-- tab end -->

										<!-- tab start 制作中的订单 -->
										<div class="tab-pane fade in" style="background-color: #fff;" id="onCookingOrders">
											<table id="onCookingOrdersTable" class="table table-bordered table-hover">
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

											</table>
											<div class="row col-sm-12" id="onCookingOrdersPaginator">
											</div>
										</div>
										<!-- tab end -->

										<!-- tab start 待发货 -->
										<div class="tab-pane fade in" style="background-color: #fff;" id="finishCookingOrders">
											<table id="finishCookingOrdersTable" class="table table-bordered table-hover">
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

											</table>
											<div class="row col-sm-12" id="finishCookingOrdersPaginator">
											</div>
										</div>
										<!-- tab end -->

										<!-- tab start 待收货 -->
										<div class="tab-pane fade in" style="background-color: #fff;" id="onDeliveryOrders">
											<table id="onDeliveryOrdersTable" class="table table-bordered table-hover">
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

											</table>
											<div class="row col-sm-12" id="onDeliveryOrdersPaginator">
											</div>
										</div>
										<!-- tab end -->
										<!-- tab start 退款 -->
										<div class="tab-pane fade in" style="background-color: #fff;" id="refundOrders">
											<table id="refundOrdersTable" class="table table-bordered table-hover">
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

											</table>
											<div class="row col-sm-12" id="refundOrdersPaginator">
											</div>
										</div>
										<!-- tab end -->

									</div> <!-- tab content end -->
								</div> <!-- col end -->
							</div> <!-- row end -->

							<!-- 一个空行 -->
							<div class="row">
								<div class="col-xs-12">
									&nbsp;
								</div>
							</div>
						</div>
						<!-- tab end -->

						<!-- tab start 所有订单 -->
						<div class="tab-pane fade in" style="background-color: #fff;" id="finishedOrders">
							<table id="finishedOrdersTable" class="table table-bordered table-hover">
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

							</table>
							<div class="row col-sm-12" id="finishedOrdersPaginator">
							</div>
						</div>
						<!-- tab end -->
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->

		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->

<!-- 订单展示模版 -->
<script id="newOrdersTableTpl" type="text/html">
	{{each object as value i}}
	<tr>
		<td>
			{{value.serialNumber}}
		</td>
		<td><a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.orderNo}}">{{value.orderNo}}</a></td>
		<td>
			{{if value.status == <%=OrderStatus.NON_PAYMENT.getValue()%>}}
			未付款
			{{/if}}
			{{if value.status == <%=OrderStatus.PENDING_ACCEPT.getValue()%>}}
			{{if value.payType == <%=PayType.ONLINE.getValue()%>}}
			在线支付
			{{/if}}
			{{if value.payType == <%=PayType.CASH.getValue()%>}}
			现金支付
			{{/if}}
			{{/if}}

			{{if value.status == <%=OrderStatus.ACCEPT_ORDER.getValue()%>}}
			<span style="color:#0000ff;">商家已接单</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.FINISH_COOKING.getValue()%>}}
			<span style="color:#0000ff;">（完成制作）等待配送</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.DELIVERY.getValue()%>}}
			<span style="color:#0000ff;">配送中</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.CONFIRM_RECEIPT_USER.getValue()%>}}
			<span style="color:green;">买家确认收货</span>
			{{if value.sysConfirm == <%=OrderIsSysConfirm.YES.getValue()%>}}
			<!-- 订单是否自动签收, 1-不是, 2-是 -->
			<br/>(<span style="color: green;">系统自动签收</span>)
			{{/if}}
			{{/if}}
			{{if value.status == <%=OrderStatus.HAS_COMMENT.getValue()%>}}
			<span style="color:green;">买家已评论</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.REFUND_APPLY.getValue()%>}}
			<span style="color: #c71585;">申请退款中</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.REFUND.getValue()%>}}
			<span style="color: #c71585;">系统退款中</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.REFUND_REFUSE.getValue()%>}}
			<span style="color: #c71585;">拒绝退款</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.REFUND_SUCCESS.getValue()%>}}
			<span style="color: #c71585;">退款成功</span>
			{{/if}}
			{{if value.status == <%=OrderStatus.CANCEL.getValue()%>}}
			<span style="color:#808080;">已取消</span>
			{{/if}}

		</td>
		<td>{{value.phone}}</td>
		<td>{{value.contacts}}</td>
		<td>{{value.address}}</td>
		<td>{{formatDateTime value.createTime}}</td>
		<td>
			{{if value.status == <%=OrderStatus.NON_PAYMENT.getValue()%>
			|| value.status == <%=OrderStatus.PENDING_ACCEPT.getValue()%>
			|| value.status == <%=OrderStatus.ACCEPT_ORDER.getValue()%>
			|| value.status == <%=OrderStatus.FINISH_COOKING.getValue()%>
			|| value.status == <%=OrderStatus.DELIVERY.getValue()%>}}
			<a href="javascript:void(0)" onclick="cancel({{value.orderNo}})">取消订单</a>
			{{/if}}

		</td>
	</tr>
	{{/each}}
</script>
<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	// [进行中的订单] 当前正在显示的 tab，以 tab id 命名
	var _tab = 'newOrders';
	// 标识当前是什么类型的订单，以 tab id 命名
	var _orderTypeTab = 'inProgressOrders';
	// [进行中的订单] 每个 tab 的配置信息：url、模板id、table id、分页id
	// key = tab id, value = {配置}
	var _tabSetting = {
		'newOrders' : {'url' : '/admin/store/newOrders', table: 'newOrdersTable', 'paginator' : 'newOrdersPaginator', 'tpl' : 'newOrdersTableTpl'},
		'onCookingOrders' : {'url' : '/admin/store/onCookingOrders', table: 'onCookingOrdersTable', 'paginator' : 'onCookingOrdersPaginator', 'tpl' : 'newOrdersTableTpl'},
		'finishCookingOrders' : {'url' : '/admin/store/finishCookingOrders', table: 'finishCookingOrdersTable', 'paginator' : 'finishCookingOrdersPaginator', 'tpl' : 'newOrdersTableTpl'},
		'onDeliveryOrders' : {'url' : '/admin/store/onDeliveryOrders', table: 'onDeliveryOrdersTable', 'paginator' : 'onDeliveryOrdersPaginator', 'tpl' : 'newOrdersTableTpl'},
		'refundOrders' : {'url' : '/admin/store/refundOrders', table: 'refundOrdersTable', 'paginator' : 'refundOrdersPaginator', 'tpl' : 'newOrdersTableTpl'}
	};
	// 标识 [进行中的订单] 每个 tab 是否加载过，key = tab id
	var _tabLoaded = {};
	// 标识 [完成的订单] 是否加载
	var finishedOrdersLoaded = false;

	// [进行中的订单] 切换 tab
	function changeInProgressTab(theTab) {
		_tab = theTab;
		refresh();
	}

	// [进行中的订单] 刷新当前 tab
	// pickTypeChange=true 表示用户改变配送类型
	function refresh(pickTypeChange) {
		var storeId = $.getUrlParam("storeId");
		var pickType = $('#pickType').val();
		var loaded = _tabLoaded[_tab];
		if (typeof loaded == 'undefined') {
			loaded = false;
		}
		if (typeof pickTypeChange == 'undefined') {
			pickTypeChange = false;
		}

		if (_orderTypeTab == 'inProgressOrders') {
			// 进行中的订单
			var setting = _tabSetting[_tab];
			if (!pickTypeChange && loaded) {
				// 已经加载过，重新刷新当页
//				console.log('reload: ' + setting.table );
				reloadPage(setting.table);
			}
			else {
				// 第一次加载 tab
				$('#' + setting.table).artPaginate({
					// 获取数据的地址
					url: setting.url,
					// 显示页码的位置
					paginator: setting.paginator,
					// 模版ID
					tpl: setting.tpl,
					// 请求的参数表，默认page=1, pageSize=20
					params: {'storeId': storeId, 'pickType': pickType}
				});
				_tabLoaded[_tab] = true;
			}
		}
		else {
			// 完成的订单
			if (!pickTypeChange && finishedOrdersLoaded) {
				reloadPage('finishedOrders');
			}
			else {
				// 第一次加载 tab
				$('#finishedOrdersTable').artPaginate({
					// 获取数据的地址
					url: '/admin/store/finishedOrders',
					// 显示页码的位置
					paginator: 'finishedOrdersPaginator',
					// 模版ID
					tpl: 'newOrdersTableTpl',
					// 请求的参数表，默认page=1, pageSize=20
					params: {'storeId': storeId, 'pickType': pickType}
				});
				finishedOrdersLoaded = true;
			}
		}
	}

	// 管理员取消订单
	var cancelOrderYes = false;
	function cancel(orderNo) {
		cancelOrderYes = false;
		BootstrapDialog.show({
			title: '取消订单',
			message: '取消订单原因：<br>' +
			'<form role="form" class="form-horizontal"><div class="form-group"><div class="col-xs-12"><input type="text" name="reason" class="form-control"></div></div>' +
			'</form>',
			buttons: [{
				label: '确定',
				action: function(dialog) { cancelOrderYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { cancelOrderYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (cancelOrderYes) {
					var reason = $.trim(dialogRef.getModalBody().find('input[name=reason]').val());
					if(0 >= reason.length) {
						MyDialog.alert('请输入取消订单的原因。');
						return false;
					}
					// 执行修改
					$.post("/admin/store/cancelOrder.do", {'orderNo':orderNo, 'remark':reason}, function(json) {
						MyDialog.alert(json.message);
					}, "json");
				}
			}
		});
	}

	// 重新加载 table 列表
	function reloadPage(table) {
		var page = $('#' + table).artPage();
		if (page) {
//			console.log('page = ' + page);
			$('#' + table).artPaginateNext(page);
		}
	}

	$(function() {
		// [进行中的订单] 初始化切换 tab 的事件
		$('#inProgressOrderTab a').click(function (e) {
			e.preventDefault();//阻止a链接的跳转行为
			$(this).tab('show');//显示当前选中的链接及关联的content
			var href = $(this).attr('href');
			if (href) {
				changeInProgressTab(href.substr(1));
			}
		});

		// [完成的订单] 切换 tab
		$('#orderTypeTab a').click(function (e) {
			e.preventDefault();//阻止a链接的跳转行为
			$(this).tab('show');//显示当前选中的链接及关联的content
			var href = $(this).attr('href');
			if (href) {
				_orderTypeTab = href.substr(1);
				refresh(false);
			}
		});
		var storeId = $.getUrlParam("storeId");
		if (storeId) {
			refresh(false);
		}
		else {
			MyDialog.alert("请到门店界面选择一家门店查看订单", function() {
				window.location.href = "/admin/store/list.xhtm";
			});
		}
	});
</script>
</body>
</html>
