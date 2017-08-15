<%@ page import="com.mazing.core.enums.pay.PayMode"%>
<%@ page import="com.mazing.core.enums.pay.PayType" %>
<%@ page import="com.mazing.core.enums.order.OrderStatus" %>
<%@ page import="com.mazing.core.enums.order.OrderIsSysConfirm" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>排行榜</title>
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
					排行榜 <small></small>
				</h1>
				<ol class="breadcrumb">
					<%--<li><a href="/admin/order/allOrders.xhtm"><i--%>
							<%--class="fa fa-dashboard"></i> 订单列表</a></li>--%>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<p class="text-left">说明：</p>
						<p class="text-left">（1）餐厅订单排行榜、美食销售排行榜从 2016-01-01 开始计算。</p>
						<p class="text-left">（2）数据是<strong>实时</strong>统计的。</p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<ul id="storeTab" class="nav nav-tabs">
							<li class="active"><a href="#storeFavRank" data-toggle="tab">
									餐厅收藏排行 </a></li>
							<li><a href="#dishFavRank" data-toggle="tab">美食收藏排行</a></li>
							<li><a href="#storeOrderRank" data-toggle="tab">餐厅订单排行</a></li>
							<li><a href="#dishRank" data-toggle="tab">美食销售排行</a></li>
						</ul>
						<div id="storeTabContent" class="tab-content">
							<!-- tab start -->
							<div class="tab-pane fade in active"
								style="background-color: #fff;" id="storeFavRank">
								<table id="storeFavRankTable"
									class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>名次</th>
											<th>餐厅</th>
											<th>收藏数量</th>
										</tr>
									</thead>
									<tbody id="storeFavInfo">

									</tbody>
								</table>

							</div>
							<!-- tab end -->

							<!-- tab start -->
							<div class="tab-pane fade" style="background-color: #fff;"
								id="dishFavRank">
								<table id="dishFavTable"
									class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>名次</th>
											<th>美食名称</th>
											<th>收藏数量</th>
										</tr>
									</thead>
									<tbody id="dishFavInfo">

									</tbody>
								</table>
							</div>
							<!-- tab end -->

							<!-- tab start -->
							<div class="tab-pane fade" style="background-color: #fff;"
								 id="storeOrderRank">
								<div>

								</div>
								<table id="storeOrderRankTable"
									   class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>名次</th>
										<th>餐厅</th>
										<th>销售额（元）</th>
										<th>订单数</th>
									</tr>
									</thead>
									<tbody id="storeOrderRankInfo">

									</tbody>
								</table>
							</div>
							<!-- tab end -->

							<!-- tab start -->
							<div class="tab-pane fade" style="background-color: #fff;"
								 id="dishRank">
								<div>

								</div>
								<table id="dishRankTable"
									   class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>名次</th>
										<th>美食</th>
										<th>所属餐厅</th>
										<th>销售额（元）</th>
										<th>订单数</th>
									</tr>
									</thead>
									<tbody id="dishRankInfo">

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
	<script id="storeFavTemplate" type="text/html">
		{{each storeFavRank as value i}}
		<tr>
			<td>{{i + 1}}</td>
			<td>
				{{value.storeName}}
			</td>
			<td>
				{{value.num}}
			</td>
		</tr>
		{{/each}}
	</script>
	<!-- 菜的信息模板 -->
	<script id="dishFavTemplate" type="text/html">
		{{each dishFavRank as value i}}
		<tr>
			<td>{{i + 1}}</td>
			<td>{{value.dishName}}</td>
			<td>{{value.num}}</td>
		</tr>
		{{/each}}
	</script>
	<!-- 餐厅销售排行榜模板 -->
	<script id="storeOrderRankTemplate" type="text/html">
		{{each storeOrderRank as value i}}
		<tr>
			<td>{{i + 1}}</td>
			<td>{{value.storeName}}</td>
			<td>{{value.totalFee}}</td>
			<td>{{value.num}}</td>
		</tr>
		{{/each}}
	</script>

	<!-- 美食销售排行榜模板 -->
	<script id="dishRankTemplate" type="text/html">
		{{each dishRank as value i}}
		<tr>
			<td>{{i + 1}}</td>
			<td>{{value.dishName}}</td>
			<td>{{value.storeName}}</td>
			<td>{{value.totalFee}}</td>
			<td>{{value.num}}</td>
		</tr>
		{{/each}}
	</script>
	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">

		// 查询收藏排行榜的信息
		// ten 2016/2/29
		function query() {
			$.getJSON('/admin/order/rank/fav', {
			}, function (json) {
				if (json.success) {
					render(json.object);
				} else {
					MyDialog.alert(json.message);
				}
			});
			// 暂时不允许选择时间
			$.getJSON('/admin/order/rank/store', {
				'startTime' : '2016-01-01 00:00:00'
			}, function (json) {
				if (json.success) {
					var htmlTxt = template('storeOrderRankTemplate', json.object);
					$('#storeOrderRankInfo').html(htmlTxt);
					htmlTxt = template('dishRankTemplate', json.object);
					$('#dishRankInfo').html(htmlTxt);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}


		// 用模板显示排行榜的信息
		function render(value) {
			// 使用模版显示信息
			var htmlTxt = template('storeFavTemplate', value);
			$('#storeFavInfo').html(htmlTxt);
			htmlTxt = template('dishFavTemplate', value);
			$('#dishFavInfo').html(htmlTxt);
		}



		$(function() {
			// 初始化模版函数

			// 查询排行榜
			query();
		});
	</script>
</body>
</html>
