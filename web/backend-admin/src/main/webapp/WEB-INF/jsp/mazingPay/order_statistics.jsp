<%@ page import="static com.mazing.core.enums.merchant.OrderPickType.*" %>
<%@ page import="com.mazing.core.enums.order.OrderStatus" %>
<%@ page import="com.mazing.core.constant.CouponBearTypeConstants" %>
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

					<div id="userTabContent" class="tab-content">
					<!-- tab start -->
						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="stat">
							 
							 <div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）创建订单数：当天创建的米星付订单数, 不论是否支付成功</p>
									<p class="text-left">（2）支付完成数：当天米星付完成（即支付成功，不包括退款）的订单数</p>
									<p class="text-left">（3）取消数：（定时任务或手动取消、退款）的米星付订单数</p>
									<p class="text-left">（4）未支付订单数：创建了米星付订单（还没支付），（定时任务或手动取消）的米星付订单数量</p>
									<p class="text-left">（5）完成订单总金额：米星付成功的订单金额（支付后退款不统计）</p>
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
											<p class="text-left">创建订单数 | 支付完成数 | 取消数 | 未支付订单数</p>
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
							
							<div style="clear:both"></div>
							<div style="float:left; margin:2px" id="cashTotal">
								<div  class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">完成订单总金额</p>
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
									<p class="text-left">（5）只统计米星付的营业额</p>
									<p class="text-left">（6）只统计最近12个月内的数据</p>
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
									<p class="text-left">（5）只统计米星付的营业额</p>
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
									<p class="text-left">（3）只统计米星付的优惠劵</p>
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
									<p class="text-left">（3）只统计米星付的优惠劵</p>
									<p class="text-left">（4）只统计最近12个月内的数据</p>
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
	var nonPaymentTotal = [];
	var cancelOrdersTotal = [];
	var finishOrdersTotal = [];
	var finishOrdersFee = [];

	var chart1;
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
					createOrdersTotal = [];
					nonPaymentTotal = [];
					cancelOrdersTotal = [];
					finishOrdersTotal = [];
					finishOrdersFee = [];
					if (chartItem.length > 0) {
						for (var i = 0; i < chartItem.length; i++) {
							var item = chartItem[i];
							var date = item.dateSerial + "";
							
							header.push(date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8));
							
							createOrdersTotal.push(item.mazingPayCreateOrdersTotal);
							
							nonPaymentTotal.push(item.mazingPayNonPaymentTotal);
							
							cancelOrdersTotal.push(item.mazingPayCancelOrdersTotal);
							
							finishOrdersTotal.push(item.mazingPayFinishOrdersTotal);
							
							finishOrdersFee.push(item.mazingPayFinishOrdersFee / 100);
							
						}
					}
					var createTotalData = {
							labels : header,
							datasets : [
								{
									label: "创建订单数",
									fillColor : "rgba(0,255,0,0)",
									strokeColor : "rgba(0,255,0,1)",
									pointColor : "rgba(0,255,0,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke : "rgba(0,255,0,1)",
									data : createOrdersTotal
								},{
									label: "支付完成数",
									fillColor: "rgba(0,191,255,0)",
						            strokeColor: "rgba(0,191,255,1)",
						            pointColor: "rgba(0,191,255,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(0,191,255,1)",
									data : finishOrdersTotal
								},{
									label: "取消数",
									fillColor: "rgba(186,85,211,0)",
						            strokeColor: "rgba(186,85,211,1)",
						            pointColor: "rgba(186,85,211,1)",
									pointStrokeColor : "#fff",
									pointHighlightFill : "#fff",
									pointHighlightStroke: "rgba(186,85,211,1)",
									data : cancelOrdersTotal
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
				}
				else {
					MyDialog.alert(json.message);
				}
			});
	}
	
	
	$(function () {
		queryData();
		
		// 查询每月订单数据
		$.getJSON('/admin/order/mazingPay/statistic/byMonth', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('monthTableTemplate', json);
				$('#monthTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询最新9周的订单数据
		$.getJSON('/admin/order/mazingPay/statistic/byWeek', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('weekTableTemplate', json);
				$('#weekTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询总的优惠券数据
		$.getJSON('/admin/order/mazingPay/statistic/totalCoupon', {
		}, function (json) {
			if (json.success) {
				var htmlTxt = template('totalCouponTableTemplate', json);
				$('#totalCouponTableInfo').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
		
		//查询每月的优惠券数据
		$.getJSON('/admin/order/mazingPay/statistic/couponByMonth', {
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
