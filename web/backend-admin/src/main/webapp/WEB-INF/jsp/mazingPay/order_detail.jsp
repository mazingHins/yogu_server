<%@ page import="com.mazing.core.enums.pay.PayMode"%>
<%@ page import="com.mazing.core.enums.pay.PayType" %>
<%@ page import="com.mazing.core.enums.order.OrderStatus" %>
<%@ page import="com.mazing.core.enums.order.OrderIsSysConfirm" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>订单详情</title>
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
					订单详情 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="/admin/order/mazingPay/allOrders.xhtm"><i
							class="fa fa-dashboard"></i> 订单列表</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="storeTab" class="nav nav-tabs">
							<li class="active"><a href="#baseInfoTab" data-toggle="tab">
									基础信息 </a></li>
							<li><a href="#orderTrack" data-toggle="tab">订单轨迹</a></li>
						</ul>
						<div id="storeTabContent" class="tab-content">
							<!-- tab start -->
							<div class="tab-pane fade in active"
								style="background-color: #fff;" id="baseInfoTab">
								<table id="baseInfoTable"
									class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>属性</th>
											<th>数值</th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody id="baseInfo">

									</tbody>
								</table>
							</div>
							<!-- tab end -->

							<!-- tab start -->
							<div class="tab-pane fade" style="background-color: #fff;"
								id="orderTrack">
								<table id="orderTrackTable"
									class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>轨迹ID</th>
											<th>操作时间</th>
											<th>操作内容</th>
											<th>操作人员</th>
										</tr>
									</thead>
									<tbody id="orderTrackInfo">

									</tbody>
								</table>
							</div>
							<!-- tab end -->


						</div>

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

	<!-- 基础信息的模版 -->
	<script id="baseInfoTemplate" type="text/html">
	<tr>
		<td>ID</td>
		<td>
			{{orderId}}  
		</td>
	</tr>
	<tr>
		<td>订单编号</td>
		<td>{{orderNoStr}} </td>
	</tr>
	<tr>
		<td>订单状态</td>
		<td>
			{{if status == <%=OrderStatus.NON_PAYMENT.getValue()%>}}
			未付款
			{{/if}}
			{{if status == <%=OrderStatus.PENDING_ACCEPT.getValue()%>}}
			{{if payType == <%=PayType.ONLINE.getValue()%>}}
			在线支付
			（内部支付流水号：{{payNo}}）
			{{/if}}
			{{if payType == <%=PayType.CASH.getValue()%>}}
			现金支付
			{{/if}}
			&nbsp; &nbsp; <span style="color: #ff0000;">（商家未接单）</span>
			{{/if}}
			{{if status == <%=OrderStatus.ACCEPT_ORDER.getValue()%>}}
			<span style="color:#0000ff;">商家已接单</span>
			{{/if}}
			{{if status == <%=OrderStatus.FINISH_COOKING.getValue()%>}}
			<span style="color:#0000ff;">（完成制作）等待配送</span>
			{{/if}}
			{{if status == <%=OrderStatus.DELIVERY.getValue()%>}}
			<span style="color:#0000ff;">配送中</span> &nbsp; &nbsp; （配送员：{{deliverName}}）注：如果配送员已经被解雇，这里不会展示名字，请查看订单轨迹
			{{/if}}
			{{if status == <%=OrderStatus.CONFIRM_RECEIPT_USER.getValue()%>}}
			<span style="color:green;">买家确认收货</span>
				{{if sysConfirm == <%=OrderIsSysConfirm.YES.getValue()%>}}
				<!-- 订单是否自动签收, 1-不是, 2-是 -->
				（<span style="color: green;">系统自动签收</span>）
				{{/if}}
			{{/if}}
			{{if status == <%=OrderStatus.HAS_COMMENT.getValue()%>}}
			<span style="color:green;">买家已评论</span>
			{{/if}}
			{{if status == <%=OrderStatus.REFUND_APPLY.getValue()%>}}
			<span style="color: #c71585;">申请退款中</span>
			{{/if}}
			{{if status == <%=OrderStatus.REFUND.getValue()%>}}
			<span style="color: #c71585;">系统退款中</span>
			{{/if}}
			{{if status == <%=OrderStatus.REFUND_REFUSE.getValue()%>}}
			<span style="color: #c71585;">拒绝退款</span>
			{{/if}}
			{{if status == <%=OrderStatus.REFUND_SUCCESS.getValue()%>}}
			<span style="color: #c71585;">退款成功</span>
			{{/if}}
			{{if status == <%=OrderStatus.CANCEL.getValue()%>}}
			<span style="color:#808080;">已取消</span>
			{{/if}}

		</td>
	</tr>

	<tr>
		<td>支付系统流水号</td>
		<td>
			{{if payNo > 0 }}
			{{payNo}}
			{{/if}}
			{{if payNo <= 0}}
			未支付或使用现金支付
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>支付类型</td>
		<td>
			{{if payType == <%=PayType.ONLINE.getValue()%>}}线上支付{{/if}}
			{{if payType == <%=PayType.CASH.getValue()%>}}货到付款{{/if}}
			{{if payType == <%=PayType.MAZING_PAY.getValue()%>}}
				{{if objectId == null}}
					<span style="color:#808080;">米星付</span>
				{{/if}}
				{{if objectId != null}}
						<span style="color:#808080;">秒付</span>
				{{/if}}
			{{/if}}
			{{if payType == <%=PayType.NONE.getValue()%>}}没选择{{/if}}
		</td>
	</tr>
	<tr>
		<td>支付方式</td>
		<td>
			{{if payType == <%=PayType.ONLINE.getValue()%>}}
				{{if payMode == <%=PayMode.ALIPAY.getValue()%>}}
				支付宝
				{{/if}}
				{{if payMode == <%=PayMode.WECHAT.getValue()%>}}
				微信
				{{/if}}
			{{/if}}
			{{if payType == <%=PayType.MAZING_PAY.getValue()%>}}
				{{if payMode == <%=PayMode.ALIPAY.getValue()%>}}
				支付宝
				{{/if}}
				{{if payMode == <%=PayMode.WECHAT.getValue()%>}}
				微信
				{{/if}}
			{{/if}}
			{{if payType == <%=PayType.CASH.getValue()%>}}
				现金支付
			{{/if}}
			{{if payType == <%=PayType.NONE.getValue()%>}}
			<strong>未支付</strong>
			{{/if}}
			{{if payType != <%=PayType.NONE.getValue()%>}}
			（付钱的人：{{userNickname}}）
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>下单渠道</td>
		<td>{{orderChannel}}</td>
	</tr>
	<tr>
		<td>餐厅</td>
		<td><a href="/admin/store/storeDetail.xhtm?storeId={{storeId}}" target="_blank">{{storeName}}</a></td>
	</tr>
	<tr>
		<td>收货人</td>
		<td>{{contacts}}&nbsp; &nbsp; 电话：{{phone}}</td>
	</tr>
	<tr>
		<td>送货地址</td>
		<td>{{address}}   &nbsp; &nbsp; 坐标：({{lat}},{{lng}}) &nbsp; &nbsp; 所属配送区域：{{serviceRangeName}}</td>
	</tr>
	<tr>
		<td>消费金额</td>
		<td>
			商品总价：{{cent2yuan goodsFee}} 元<br/>
			配送费：　{{cent2yuan deliveryFee}} 元 <br/>
			应付金额：{{cent2yuan totalFee}} 元 <br/>
			<span style="color:blue;">优惠总价：{{cent2yuan discountFee}} 元</span>
			{{if couponName != ''}}
			&nbsp; （优惠券：{{couponName}}）
			{{/if}}
			<br/>
			<span style="color:green;">实付金额：{{cent2yuan actualFee}}</span> 元
		</td>
	</tr>
	<tr>
		<td>备注</td>
		<td>备注：{{remark}} &nbsp; &nbsp; ，运费说明：{{deliveryRemark}}</td>
	</tr>
	<tr>
		<td>取消订单/商家拒单原因</td>
		<td><span style="color:red;">{{rejectRemark}}</span></td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td>{{formatDateTime createTime}}</td>
	</tr>
	<tr>
		<td>订单开始时间</td>
		<td>
			{{if orderBeginTime > 0}}
				{{formatDateTime orderBeginTime}}
			{{/if}}
			{{if orderBeginTime <= 0}}
				{{formatDateTime createTime}}
			{{/if}}
		</td>
	</tr>

	<tr>
		<td>预计送达时间</td>
		<td>
			{{formatDateTime deliveryTime}}
		</td>
	</tr>
	<tr>
		<td>用户确认收货时间</td>
		<td>{{if userConfirmTime > 0}}
				{{formatDateTime userConfirmTime}}
			{{/if}}
			{{if userConfirmTime <= 0}}
			    未确认收货
			{{/if}}
			{{if sysConfirm == <%=OrderIsSysConfirm.YES.getValue()%>}}
				<!-- 订单是否自动签收, 1-不是, 2-是 -->
				（<span style="color: green;">系统自动签收</span>）
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>商家确认收货时间</td>
		<td>
			{{if storeConfirmTime > 0}}
				{{formatDateTime storeConfirmTime}}
			{{/if}}
			{{if storeConfirmTime <= 0}}
				未确认送达
			{{/if}}
		</td>
	</tr>
</script>
	

<!-- 订单轨迹的模版 -->
<script id="orderTrackTemplate" type="text/html">
											{{each orderTrackList as value i}}
											<tr>
												<td>{{value.trackId}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>{{value.content}}</td>
												<td>{{value.oper}}</td>
											</tr>
											{{/each}}
</script>



	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">

		// 查询门店的信息
		function query() {
			var orderNo = $.getUrlParam("orderNo");

			if (orderNo == null || orderNo == '' || orderNo == '0') {
				MyDialog.alert('请在订单列表界面选择一条订单进行操作');
			} else {
				$.getJSON('/admin/order/orderDetail', {
					'orderNo' : orderNo
				}, function(json) {
					if (json.success) {
						render(json.object);
					} else {
						MyDialog.alert(json.message);
					}
				});
			}
		}


		// 用模板显示用户的信息
		function render(value) {
			var order = value.order;
			// 2016/2/26 增加优惠券名称的显示 by ten
			var coupon = value.coupon;
			if (coupon) {
				order['couponName'] = coupon['couponName'];
			}
			else {
				order['couponName'] = '';
			}
			// end

			// 使用模版显示门店信息
			var htmlTxt = template('baseInfoTemplate', order);
			$('#baseInfo').html(htmlTxt);
			htmlTxt = template('orderTrackTemplate', value);
			$('#orderTrackInfo').html(htmlTxt);
		}



		$(function() {
			// 初始化模版函数

			// 查询门店
			query();
		});
	</script>
</body>
</html>
