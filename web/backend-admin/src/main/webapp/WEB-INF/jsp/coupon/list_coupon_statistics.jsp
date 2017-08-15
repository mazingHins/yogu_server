<%@page import="com.mazing.core.enums.pay.PayType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>创建优惠券统计</title>
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
				创建优惠券统计
				<small></small>
			</h1>
			<ol class="breadcrumb">

			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div id="userTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="queryUser">
							<div class="box box-solid">
								<div class="box-header">
									<div class="row">
										<div class="col-sm-1">
											<select class="form-control" id="yearMonth" style="width: 130px;"></select>
										</div>
										<div class="col-sm-1">
											<select class="form-control" name="payTypes" id="payTypes">
												<option value="<%=PayType.ONLINE.getValue()%>,<%=PayType.MAZING_PAY.getValue()%>" selected>全部订单</option>
												<option value="<%=PayType.ONLINE.getValue()%>">订餐订单</option>
												<option value="<%=PayType.MAZING_PAY.getValue()%>">米星付订单</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<p class="text-left">说明：</p>
											<p class="text-left">（1）月份，与顶部选中的一致，仅作展示，方便识别。</p>
											<p class="text-left">（2）展示顺序按平台型优惠券总优惠金额较高的创建人优先，同一创建人的两条记录（平台型、商家型）连续展示。</p>
											<p class="text-left">（3）仅统计当月创建过券，且券有被成功消费的创建人。</p>
											<p class="text-left">（4）统计每天凌晨进行一次，如确认收货订单发生退款，相关金额会在下一次统计进行更新。</p>
										</div>
									</div>
								</div>
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="statisticsTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>月份</th>
													<th>创建人</th>
													<th>优惠券类型</th>
													<th>订单金额(元)</th>
													<th>优惠金额(元)</th>
													<th>订单数量</th>
												</tr>
												</thead>
										<script id="statisticsTemplate" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.month}}
												</td>
												<td>
													{{value.adminName}}
												</td>
												<td>
													{{value.couponBearTypeName}}
												</td>
												<td>
													{{value.totalFee / 100}}
												</td>
												<td>
													{{value.couponFee / 100}}
												</td>
												<td>
													{{value.orderSum}}
												</td>
											</tr>
											{{/each}}
										</script>
											</table>
										</div>
									</div>
								</div><!-- /.box-body -->
							</div><!-- /.box -->
						</div> <!-- /.tab -->
					</div> <!-- /.tabContent - -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->
<script id="regPhoneTemplate" type="text/html">
	<tr>
		<td>{{total}}</td>
		<td>{{phoneStr}}</td>
		<td></td>
	</tr>
</script>
<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">	
	$(function(){
		var date = new Date();
		var month = date.getMonth() + 1;
		var html = '';
		for(var i = 1; i <= month; i++){
			var value = date.getFullYear() + '-' + (i < 10 ? ('0' + i) : i);
			var name = date.getFullYear() + '年' + (i < 10 == 1 ? ('0' + i) : i) + '月';
			if( i == month){
				html += '<option value="' + value + '" selected>'+ name +'</option>';
			}else{
				html += '<option value="' + value + '">'+ name +'</option>';				
			}
		}
		$('#yearMonth').append(html);

		//初始化调用
		statistics();
		
		//切换月份事件
		$('#yearMonth, #payTypes').change(function(){
			statistics();
		});
	});
	
	function statistics(){
		var date = $('#yearMonth').val();
		var payTypes = $('#payTypes').val();
		$('#statisticsTable').artPaginate({
			// 获取数据的地址
			url: "/admin/coupon/queryCouponStatistics",
			// 显示页码的位置
			//paginator: 'listPaginator',
			// 模版ID
			tpl: 'statisticsTemplate',
			method: 'post',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'date': date, 'payTypes': payTypes}
		});
	}
</script>
</body>
</html>
