<%@page import="com.yogu.core.enums.pay.PayMode"%>
<%@ page import="com.yogu.core.enums.pay.PayType" %>
<%@ page import="com.yogu.core.enums.order.OrderIsSysConfirm" %>
<%@ page import="com.yogu.core.enums.order.OrderStatus" %>
<%@ page import="com.yogu.core.enums.BooleanConstants" %>
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

			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="col-sm-6">
								<form class="form-inline">
								<select class="form-control" name="orderStatus" id="orderStatus">
									<option value="0">所有</option>
									<!--
									<option value="1">进行中的订单</option>
									<option value="2">非进行中订单</option>
									-->
								</select>
								<div class="input-group input-group-sm col-sm-8">
									<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入订单号、手机号码、餐厅名字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="searchByKey()">Go!</button>
									</span>
								</div>
								</form>
							</div>
							<div class="col-sm-6" style="text-align: right;">

							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<p class="text-left">订单状态有：未付款、在线支付、现金支付、已接单、已配送、买家确认收货、买家已评论、申请退款中、系统退款中、退款成功、拒绝退款、已取消</p>
									<p class="text-left">下单渠道有：iphone、android、mobile(H5)</p>
									<p class="text-left">支付类型有：线上支付、货到付款、没选择（下单后定时任务取消）</p>
									<p class="text-left">米星付的订单不会在此展示</p>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID/#</th>
											<th>订单号</th>
											<th>价格</th>
											<th>状态</th>
											<th>支付方式</th>
											<th>联系人/地址</th>
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
												<td>
													<a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.orderNoStr}}">{{value.orderNoStr}}</a><
												</td>
												<td>
													{{if value.totalFee >= 1000000}}
														<!-- 5位数 -->
														<span style="color:#ff0000;">{{cent2yuan value.totalFee}}</span>
													{{/if}}
													{{if value.totalFee >= 100000 && value.totalFee < 1000000}}
														<!-- 4位数 -->
														<span style="color:#0000ff;">{{cent2yuan value.totalFee}}</span>
													{{/if}}
													{{if value.totalFee < 100000}}
														{{cent2yuan value.totalFee}}
													{{/if}}
												</td>
												<td>
													{{if value.status == <%=OrderStatus.NON_PAYMENT.getValue()%>}}
													未付款
													{{/if}}
													{{if value.status == <%=OrderStatus.PENDING_ACCEPT.getValue()%>}}
														<span style="color:#0000ff;">已支付</span><br/>
													{{/if}}
													{{if value.status == <%=OrderStatus.ACCEPT_ORDER.getValue()%>}}
													<span style="color:#0000ff;">商家已接单</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.FINISH_COOKING.getValue()%>}}
													<span style="color:#0000ff;">等待配送</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.DELIVERY.getValue()%>}}
													<span style="color:#0000ff;">配送中</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.CONFIRM_RECEIPT_USER.getValue()%>}}
													<span style="color:green;">买家确认收货</span>
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
												<td>
													{{if value.payMode == <%=PayMode.ALIPAY.getValue()%>}}
													<span style="color:#808080;">支付宝</span>
													{{/if}}
													{{if value.payMode == <%=PayMode.WECHAT.getValue()%>}}
													<span style="color:#808080;">微信</span>
													{{/if}}
												</td>
												<td>
													<i class="fa fa-phone"></i>: {{value.phone}}<br/>
													<i class="fa fa-user"></i>: {{value.contacts}}<br/>
													<i class="fa fa-arrow-circle-right"></i>: {{value.address}}<br/>
													<i class="fa fa-jpy"></i><i class="fa fa-user"></i>: {{value.userNickname}}
												</td>
												<td style="font-size: 14px;">{{formatDateTime value.createTime}}</td>
												<td>
													<a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.orderNoStr}}">查看详情</a><
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
	function search(keyword) {
		if (keyword.length > 0 && isNaN(keyword)) {
			$.getJSON('/admin/order/searchStore', {'keyword':keyword}, function(json) {
				if (json.success) {
					var storeList = json.object;
					if (storeList.length == 1) {
						$('#listTable').artPaginate({
							// 获取数据的地址
							url: "/admin/order/queryByStoreId",
							// 显示页码的位置
							paginator: 'listPaginator',
							// 模版ID
							tpl: 'listTableTpl',
							// 请求的参数表，默认page=1, pageSize=20
							params: {'storeId': storeList[0].storeId}
						});
					}
					else if (storeList.length > 1) {
						renderStoreList(storeList);
					}
					else {
						MyDialog.alert('没有找到符合条件的数据');
					}
				}
				else {
					MyDialog.alert(json.message);
				}
			});
		}
		else {
			$('#listTable').artPaginate({
				// 获取数据的地址
				url: "/admin/order/query",
				// 显示页码的位置
				paginator: 'listPaginator',
				// 模版ID
				tpl: 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params: {'keyword': keyword}
			});
		}
	}

	// 展示餐厅的列表供选择
	// ten 2015/12/18
	function renderStoreList(list) {
		var top = $('#keyword').offset().top;
		var left = $('#keyword').offset().left;
		var height = $('#keyword').outerHeight();
		var htmlText = '';
		for (var i=0; i < list.length; i++) {
			var store = list[i];
			var regExp = new RegExp("'", "g");
			var name = store.storeName.replace(regExp, "\\'");
			regExp = new RegExp("\"", "g");
			name = name.replace(regExp, "\\\"");
			htmlText += '<div style="width:100%;border-top: 1px solid #f4f4f4;"><a style="padding: 8px;display: block;" href="javascript:searchStore(' + store.storeId + ',\'' + name + '\')">' + store.storeName + '</a></div>';
		}
		$('#searchStoreBodyDiv').html(htmlText);
		$('#searchStoreDiv').css('left', left);
		$('#searchStoreDiv').css('top', top + height);
		$('#searchStoreDiv').show();

	}
	
	//根据条件搜索
	function searchByKey(){
		var keyword = $.trim($('#keyword').val());
		search(keyword);
	}

	// 根据 storeId 查询结果 2015/12/18
	function searchStore(storeId, storeName) {
		$('#searchStoreDiv').hide();
		$('#keyword').val(storeName);
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/order/queryByStoreId",
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

	// 管理员取消订单
	var cancelOrderYes = false;
	function cancel(orderNo, el) {
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
					if (el) {
						$(el).html('');
					}
					// 执行修改
					$.post("/admin/store/cancelOrder.do", {'orderNo':orderNo, 'remark':reason}, function(json) {
						MyDialog.alert(json.message);
						// 重新加载页面
						reloadPage();
					}, "json");
				}
			}
		});
	}
	
	// 管理员取消顺丰配送
	var cancelSfExpressYes = false;
	function cancelSfExpress(orderNo, el) {
		cancelSfExpressYes = false;
		BootstrapDialog.show({
			title: '取消订单',
			message: '取消订单原因：<br>' +
			'<form role="form" class="form-horizontal"><div class="form-group"><div class="col-xs-12"><input type="text" name="reason" class="form-control"></div></div>' +
			'</form>',
			buttons: [{
				label: '确定',
				action: function(dialog) { cancelSfExpressYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { cancelSfExpressYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (cancelSfExpressYes) {
					var reason = $.trim(dialogRef.getModalBody().find('input[name=reason]').val());
					if(0 >= reason.length) {
						MyDialog.alert('请输入取消订单的原因。');
						return false;
					}
					if (el) {
						$(el).html('');
					}
					// 执行修改
					$.post("/admin/store/cancelExpress.do", {'orderNo':orderNo, 'remark':reason}, function(json) {
						MyDialog.alert(json.message);
						// 重新加载页面
						reloadPage();
					}, "json");
				}
			}
		});
	}
	
	//orderNo初始为零，默认搜索所有订单
	$(function() {
		search('');

		// 点击 document 其他地方，浮层隐藏 2015/12/18
		$(document).bind("click", function(e) {
			if ($(e.target).closest('#searchStoreDiv').length == 0) {
				$("#searchStoreDiv").hide();
			}
		});
	});
</script>

<!-- 搜索出来的餐厅浮层 -->
<div class="row" id="searchStoreDiv" style="position: absolute; padding: 7px 10px; z-index: 1000; background-color: #fff; border: 1px solid #ccc;border-radius: 4px;display: none;">
	<div class="col-sm-12">
		<div class="row">
				以下是符合条件的餐厅，请选择一个
		</div>
		<div class="row" id="searchStoreBodyDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;">
		</div>
	</div>
</div>
</body>
</html>
