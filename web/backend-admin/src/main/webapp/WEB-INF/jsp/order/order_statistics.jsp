<%@ page import="static com.yogu.core.enums.merchant.OrderPickType.*" %>
<%@ page import="com.yogu.core.enums.order.OrderStatus" %>
<%@ page import="com.yogu.core.constant.CouponBearTypeConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<!-- chart.js -->
	<script src="/static/plugins/chartjs/Chart.js"></script>
	<title>订单统计</title>
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
				订单统计
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<!--  <li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 返回门店列表</a></li>-->
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<!-- <p class="text-left">（1）本界面操作和商家后台类似，搜索结果在新界面展示；</p>
					<p class="text-left">（2）选了配送类型后，自动刷新当前tab；</p>-->
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<ul id="statTab" class="nav nav-tabs">
						<li class="active"><a href="#stat" data-toggle="tab">
							订单统计 </a></li>
						<li><a href="#byMonth" data-toggle="tab">每月营业额</a></li>
						<li><a href="#byWeek" data-toggle="tab">每周营业额</a></li>
						<li><a href="#statCouponTotal" data-toggle="tab">总优惠券统计</a></li>
						<li><a href="#statCouponByMonth" data-toggle="tab">每月优惠券统计</a></li>
					</ul>

				</div>
			</div>
			
			<div class="row">

				<div class="col-xs-12">

					<!--  <ul id="userTab" class="nav nav-tabs">
						<li class="active">
							<a href="#createTotal" data-toggle="tab">
								创建订单总数
							</a>
						</li>
						<li><a href="#onlineTotal" data-toggle="tab" onclick="test()">线上支付订单数</a></li>
						<li><a href="#cashTotal" data-toggle="tab">现金支付订单数</a></li>
						<li><a href="#nonPayTotal" data-toggle="tab">未支付订单数</a></li>
						<li><a href="#cancelTotal" data-toggle="tab">成功取消订单数</a></li>
						<li><a href="#completeTotal" data-toggle="tab">完成的订单数</a></li>
						<li><a href="#completeTotalFee" data-toggle="tab">完成的订单的金额</a></li>
						<li><a href="#createTotalFee" data-toggle="tab">创建的订单的金额</a></li>
					</ul>-->
					
					<div id="userTabContent" class="tab-content">
						<!-- tab start -->
						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="stat">
							 
							 <div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）创建订单数：当天创建的线上、现金支付订单数, 不论是否支付成功</p>
									<p class="text-left">（2）线上支付订单数：当天线上支付成功后的订单数（包括退款的）</p>
									<p class="text-left">（3）现金支付订单数：当天货到付款后的订单数（包括取消的）</p>
									<p class="text-left">（4）未支付订单数：选择了线上支付，但没付款/被取消的订单数</p>
									<p class="text-left">（5）取消订单数：线上支付（退款成功），现金支付（取消完成）的订单数</p>
									<p class="text-left">（6）完成订单数：当天完成（即确认收货/评论）的订单数</p>
									<p class="text-left">（7）完成订单总金额 ：当天完成（即确认收货/评论）的订单金额</p>
									<p class="text-left">（8）创建订单总金额：不包括没付款的（即线上支付没付款，或现金支付没成功），不包括退款的订单数量。<strong>跟创建订单数统计条件不同！！！</strong></p>
								</div>
							</div>
							 
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<form class="form-inline">
										数据量：
										<select id="timeline" name="timeline" class="form-control" onchange="queryData()">
											<option value="week" selected>一周</option>
											<option value="month">一个月</option>
										</select>
									</form>
								</div>
							</div>
							<div style="float:left; margin:2px" id="createTotal" >
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">创建订单数 | 线上支付订单数 | 现金支付订单数 | 未支付订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="createTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="float:left; margin:2px" id="onlineTotal" border="1">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">取消订单数 | 完成订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="onlineTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="clear:both"></div>
							<div style="float:left; margin:2px" id="cashTotal">
								<div  class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">完成订单总金额 | 创建订单总金额</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="cashTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="float:left; margin:2px; display: none;"  id="nonPayTotal">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">未支付订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="nonPayTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="clear:both"></div>
							<div style="float:left; margin:2px; display: none;"  id="cancelTotal">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">取消订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="cancelTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="float:left; margin:2px; display: none;" id="completeTotal">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">完成订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="completeTotalChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="clear:both"></div>
							<div style="float:left; margin:2px; display: none;" id="completeTotalFee">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">完成订单总金额</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="completeTotalFeeChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="float:left; margin:2px; display: none;" id="createTotalFee">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">创建订单总金额</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%">
												<canvas id="createTotalFeeChart" width="700" height="400"></canvas>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 摘要 by ten 2016/2/25 -->
							<div style="clear:both"></div>
							<div style="float:left; margin:2px; display: none;"  id="summaryTotal">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">取消订单数</p>
										</div>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12" style="width:100%" id="summaryTotalDetail">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="clear:both"></div>
						</div>
						<!-- tab end -->
						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;"
							 id="byMonth">
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）从 2016-01-01 开始计算，只统计线上支付的、已经<strong>完成</strong>的订单。</p>
									<p class="text-left">（2）数据是<strong>实时</strong>统计的。</p>
									<p class="text-left">（3）客单价 = 营业额 ÷ 数量</p>
									<p class="text-left">（4）时间范围是按订单的“创建时间”</p>
									<p class="text-left">（5）只统计非米星付的营业额</p>
									<p class="text-left">（5）订单金额低于（包括等于）10元的，不纳入统计范围</p>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table id="monthTable"
										   class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>月份</th>
											<th>营业额（元）</th>
											<th>订单数量</th>
											<th>客单价（元）</th>
										</tr>
										</thead>
										<tbody id="monthTableInfo">

										</tbody>
										<!-- 模版 -->
										<script id="monthTableTemplate" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.month}}</td>
												<td>
													{{cent2yuan value.total}}
												</td>
												<td>
													{{ value.num}}
												</td>
												<td>
													{{cent2yuan value.avg}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
						</div>
						<!-- tab end -->
						<!-- 最近9周订单统计 -->
						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;"
							 id="byWeek">
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）统计最近9周的订单数据，按最新的一周到最后的一周排列，从 2016-01-01 开始计算，只统计线上支付的、已经<strong>完成</strong>的订单。</p>
									<p class="text-left">（2）数据是<strong>实时</strong>统计的。</p>
									<p class="text-left">（3）客单价 = 营业额 ÷ 数量</p>
									<p class="text-left">（4）时间范围是按订单的“创建时间”</p>
									<p class="text-left">（5）只统计非米星付的营业额</p>
									<p class="text-left">（5）订单金额低于（包括等于）10元的，不纳入统计范围</p>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<table id="weekTable"
										   class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>周</th>
											<th>营业额（元）</th>
											<th>订单数量</th>
											<th>客单价（元）</th>
										</tr>
										</thead>
										<tbody id="weekTableInfo">

										</tbody>
										<!-- 模版 -->
										<script id="weekTableTemplate" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.timeToWeek}}</td>
												<td>
													{{cent2yuan value.totalOfWeek}}
												</td>
												<td>
													{{value.orderNumOfWeek}}
												</td>
												<td>
													{{cent2yuan value.avg}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
						</div>
						<!-- tab end -->
						<!-- 统计总的优惠券 -->
						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;"
							 id="statCouponTotal">
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）从 2016-01-01 开始计算，统计总的优惠券数据，只统计线上支付的、已经<strong>完成</strong>的订单中的优惠券。</p>
									<p class="text-left">（2）数据是<strong>实时</strong>统计的。</p>
									<p class="text-left">（3）只统计非米星付的优惠劵</p>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<table id="totalCouponTable"
										   class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>优惠券类型</th>
											<th>订单总额（元）</th>
											<th>优惠金额（元）</th>
											<th>订单数量</th>
										</tr>
										</thead>
										<tbody id="totalCouponTableInfo">

										</tbody>
										<!-- 模版 -->
										<script id="totalCouponTableTemplate" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{if value.coupon_bear_type == 0}}
														无
													{{/if}}

													{{if value.coupon_bear_type == <%=CouponBearTypeConstants.PLATFORM%>}}
														平台型
													{{/if}}

													{{if value.coupon_bear_type == <%=CouponBearTypeConstants.MERCHANT%>}}
														商家型
													{{/if}}
													
												</td>
												<td>
													{{cent2yuan value.total}}
												</td>
												<td>
													{{cent2yuan value.discount}}
												</td>
												<td>
													{{value.num}}
												</td>
											</tr>

											{{if object.length ==  (i + 1)}}
												<tr>
													<td>总计</td>
													<td>
														{{cent2yuan value.sumOfTotal}}
													</td>
													<td>
														{{cent2yuan value.sumOfDiscount}}
													</td>
													<td>
														{{value.totalOrderNum}}
													</td>
												</tr>
											{{/if}}
											{{/each}}
										</script>
									</table>

								</div>
							</div>
						</div>
						<!-- tab end -->
						<!-- 统计每月的优惠券 -->
						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;"
							 id="statCouponByMonth">
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）从 2016-01-01 开始计算，统计每月的优惠券数据，只统计线上支付的、已经<strong>完成</strong>的订单中的优惠券。</p>
									<p class="text-left">（2）数据是<strong>实时</strong>统计的。</p>
									<p class="text-left">（3）只统计非米星付的优惠劵</p>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<table id="couponByMonthTable"
										   class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>月份</th>
											<th>优惠券类型</th>
											<th>订单总额（元）</th>
											<th>优惠金额（元）</th>
											<th>订单数量</th>
										</tr>
										</thead>
										<tbody id="couponByMonthTableInfo">

										</tbody>
										<!-- 模版 -->
										<script id="couponByMonthTableTemplate" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.month}}</td>
												<td>
													{{if value.coupon_bear_type == 0}}
														无
													{{/if}}

													{{if value.coupon_bear_type == <%=CouponBearTypeConstants.PLATFORM%>}}
														平台型
													{{/if}}

													{{if value.coupon_bear_type == <%=CouponBearTypeConstants.MERCHANT%>}}
														商家型
													{{/if}}
												</td>
												<td>
													{{cent2yuan value.total}}
												</td>
												<td>
													{{cent2yuan value.discount}}
												</td>
												<td>
													{{value.num}}
												</td>
											</tr>

											{{if object.length ==  (i + 1)}}
												<tr>
													<td>总计</td>
													<td></td>
													<td>
														{{cent2yuan value.sumOfTotal}}
													</td>
													<td>
														{{cent2yuan value.sumOfDiscount}}
													</td>
													<td>
														{{value.totalOrderNum}}
													</td>
												</tr>
											{{/if}}
											{{/each}}
										</script>
										
									</table>

								</div>
							</div>
						</div>
						<!-- tab end -->
					</div>
			</div>
		 </div>
		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->

<!-- 订单展示模版 -->
<script id="newOrdersTableTpl" type="text/html">
	
</script>
<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	
	var header = [];
	var createOrdersTotal = [];
	var createOrdersFee = [];
	var onlineOrdersTotal = [];
	var cashOrdersTotal = [];
	var nonPaymentTotal = [];
	var cancelOrdersTotal = [];
	var finishOrdersTotal = [];
	var finishOrdersFee = [];
	var onlineOrderFee = [];
	
	var chart1;
	var chart2;
	var chart3;
	

	function queryData() {
		var type;
		if (document.getElementById("timeline").value == "week") {
			type = 1;
		}else {
			type = 2;
		}
			
		$.getJSON("/admin/order/statistic/query?type=" + type, {}, function(json) {
			if (json.success) {
					var chartItem = json.object;
					header = [];
					createOrdersFee = [];
					createOrdersTotal = [];
					onlineOrdersTotal = [];
					cashOrdersTotal = [];
					nonPaymentTotal = [];
					cancelOrdersTotal = [];
					finishOrdersTotal = [];
					finishOrdersFee = [];
					onlineOrderFee = [];
					if (chartItem.length > 0) {
						for (var i = 0; i < chartItem.length; i++) {
							var item = chartItem[i];
							var date = item.dateSerial + "";
							
							header.push(date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8));
							
							createOrdersTotal.push(item.createOrdersTotal);
							
							createOrdersFee.push(item.createOrdersFee / 100);
							
							onlineOrdersTotal.push(item.onlineOrdersTotal);
							
							cashOrdersTotal.push(item.cashOrdersTotal);
							
							nonPaymentTotal.push(item.nonPaymentTotal);
							
							cancelOrdersTotal.push(item.cancelOrdersTotal);
							
							finishOrdersTotal.push(item.finishOrdersTotal);
							
							finishOrdersFee.push(item.finishOrdersFee / 100);
							
							onlineOrderFee.push(item.onlineOrderFee / 100);
						}
					}
					var createTotalData = {
							labels : header,
							datasets : [
								{
									label: "创建订单总数",
									fillColor : "rgba(0,255,0,0)",
									strokeColor : "rgba(0,255,0,1)",
									pointColor : "rgba(0,255,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(0,255,0,1)",
									data : createOrdersTotal
								},{
									label: "线上支付订单数",
									fillColor: "rgba(0,191,255,0)",
						            strokeColor: "rgba(0,191,255,1)",
						            pointColor: "rgba(0,191,255,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(0,191,255,1)",
									data : onlineOrdersTotal
								},{
									label: "现金支付订单数",
									fillColor: "rgba(186,85,211,0)",
						            strokeColor: "rgba(186,85,211,1)",
						            pointColor: "rgba(186,85,211,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(186,85,211,1)",
									data : cashOrdersTotal
								},{
									label: "未支付订单数",
									fillColor: "rgba(255,0,0,0)",
						            strokeColor: "rgba(255,0,0,1)",
						            pointColor: "rgba(255,0,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(255,0,0,1)",
									data : nonPaymentTotal
								}
							]
					}
					var ctx1 = document.getElementById("createTotalChart").getContext("2d");
					if (chart1 != null){
						chart1.destroy();
					}
					chart1 = new Chart(ctx1).Line(createTotalData, {
						scaleShowLabels : true,
						multiTooltipTemplate: "<"+"%= datasetLabel %>" + " : " + "<"+"%= value %>"
						//responsive: true
					});
					
					var onlineTotalData = {
							labels : header,
							datasets : [
								{
									label: "取消订单数",
									fillColor: "rgba(255,0,0,0)",
						            strokeColor: "rgba(255,0,0,1)",
						            pointColor: "rgba(255,0,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(255,0,0,1)",
									data : cancelOrdersTotal
								}, {
									label: "完成订单数",
									fillColor: "rgba(0,255,0,0)",
						            strokeColor: "rgba(0,255,0,1)",
						            pointColor: "rgba(0,255,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(0,255,0,1)",
									data : finishOrdersTotal
								}
							]
					}
					var ctx2 = document.getElementById("onlineTotalChart").getContext("2d");
					if (chart2 != null){
						chart2.destroy();
					}
					chart2 = new Chart(ctx2).Line(onlineTotalData, {
						multiTooltipTemplate: "<"+"%= datasetLabel %>" + " : " + "<"+"%= value %>"
						//responsive: true
					});
					
					var cashTotalData = {
							labels : header,
							datasets : [
								{
									label: "完成订单总金额",
									fillColor : "rgba(0,255,0,0)",
									strokeColor : "rgba(0,255,0,1)",
									pointColor : "rgba(0,255,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(0,255,0,1)",
									data : finishOrdersFee
								},{
									label: "创建订单总金额",
									fillColor: "rgba(0,191,255,0)",
						            strokeColor: "rgba(0,191,255,1)",
						            pointColor: "rgba(0,191,255,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(0,191,255,1)",
									data : createOrdersFee
								},{
									label: "线上支付总金额",
									fillColor: "rgba(255,255,0,0)",
						            strokeColor: "rgba(255,255,0,1)",
						            pointColor: "rgba(255,255,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(255,255,0,1)",
									data : onlineOrderFee
								}
							]
					}
					var ctx3 = document.getElementById("cashTotalChart").getContext("2d");
					if (chart3 != null){
						chart3.destroy();
					}
					chart3 = new Chart(ctx3).Line(cashTotalData, {
						scaleLabel : function (valuePayload) {
						    return Number(valuePayload.value) + "元"
						},
						multiTooltipTemplate: "<"+"%= datasetLabel %>" + " : " + "<"+"%= value %>"
					});
					/*
					var nonPayData = {
							labels : header,
							datasets : [
								{
									label: "未支付订单总数",
									fillColor : "rgba(220,220,220,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : nonPaymentTotal
								}
							]
					}
					var ctx4 = document.getElementById("nonPayTotalChart").getContext("2d");
					if (chart4 != null){
						chart4.destroy();
					}
					chart4 = new Chart(ctx4).Line(nonPayData, {
						//responsive: true
					});
					
					
					var cancelTotalData = {
							labels : header,
							datasets : [
								{
									label: "取消订单总数",
									fillColor : "rgba(220,220,220,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : cancelOrdersTotal
								}
							]
					}
					var ctx5 = document.getElementById("cancelTotalChart").getContext("2d");
					if (chart5 != null){
						chart5.destroy();
					}
					chart5 = new Chart(ctx5).Line(cancelTotalData, {
						//responsive: true
					});
					
					var completeTotalData = {
							labels : header,
							datasets : [
								{
									label: "完成订单总数",
									fillColor : "rgba(220,220,220,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : finishOrdersTotal
								}
							]
					}
					var ctx6 = document.getElementById("completeTotalChart").getContext("2d");
					if (chart6 != null){
						chart6.destroy();
					}
					chart6 = new Chart(ctx6).Line(completeTotalData, {
						//responsive: true
					});
					
					var completeTotalFeeData = {
							labels : header,
							datasets : [
								{
									label: "完成订单总金额",
									fillColor : "rgba(220,220,220,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : finishOrdersFee
								}
							]
					}
					var ctx7 = document.getElementById("completeTotalFeeChart").getContext("2d");
					if (chart7 != null){
						chart7.destroy();
					}
					chart7 = new Chart(ctx7).Line(completeTotalFeeData, {
						scaleLabel : function (valuePayload) {
						    return Number(valuePayload.value) + "元"
						}
					});
					
					var createTotalFeeData = {
							labels : header,
							datasets : [
								{
									label: "创建订单总金额",
									fillColor : "rgba(220,220,220,0.2)",
									strokeColor : "rgba(220,220,220,1)",
									pointColor : "rgba(220,220,220,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(220,220,220,1)",
									data : createOrdersFee
								}
							]
					}
					var ctx8 = document.getElementById("createTotalFeeChart").getContext("2d");
					if (chart8 != null){
						chart8.destroy();
					}
					chart8 = new Chart(ctx8).Line(createTotalFeeData, {
						scaleLabel : function (valuePayload) {
						    return Number(valuePayload.value) + "元"
						}
					});*/
				}
				else {
					MyDialog.alert(json.message);
				}
			});
	}

	function queryCoupon() {
		
	}
	
	$(function () {
		queryData();

		// 查询每月订单数据
		$.getJSON('/admin/order/statistic/byMonth', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('monthTableTemplate', json);
				$('#monthTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询最新9周的订单数据
		$.getJSON('/admin/order/statistic/byWeek', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('weekTableTemplate', json);
				$('#weekTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询总的优惠券数据
		$.getJSON('/admin/order/statistic/totalCoupon', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('totalCouponTableTemplate', json);
				$('#totalCouponTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询每月的优惠券数据
		$.getJSON('/admin/order/statistic/couponByMonth', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('couponByMonthTableTemplate', json);
				$('#couponByMonthTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
	});
	
	
</script>
</body>
</html>
