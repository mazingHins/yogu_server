<%@ page import="com.yogu.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>商品列表</title>
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
				商品列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/goods/editGoods.xhtm"><i class="fa fa-dashboard"></i> 新增商品</a></li>
			</ol>
			<!--
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> 新增商家</a></li>
			</ol>
			-->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<ul id="storeTab" class="nav nav-tabs">
						<li class="active"><a href="#storeListTab" data-toggle="tab">
							商品列表 </a></li>
					</ul>
					<div id="storeTabContent" class="tab-content">
						<!-- tab start -->
						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="storeListTab">
							<div class="box">
								<div class="box-header">
									<div class="col-sm-3">
										<div class="input-group input-group-sm">
											<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
									</span>
										</div>
									</div>
									<div class="col-sm-9" style="text-align: right;">

									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row col-sm-12" id="listPaginator">
									</div>

									<div class="row">
										<div class="col-sm-12">
											<table id="listTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>ID</th>
													<th>商品名称</th>
													<th>商品头像</th>
													<th>零售价</th>
													<th>批发价</th>
													<th>排序</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
												</thead>
												<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.goodsId}}
														</td>
														<td><a href="/admin/goods/goodsDetail.xhtm?goodsKey={{value.goodsKey}}">{{value.goodsName}}</a></td>
														<td><img width='80' src='{{value.cardImg}}' /></td>
														<td>{{cent2yuan value.retailPrice}}</td>
														<td>{{cent2yuan value.tradePrice}}</td>
														<td>{{value.sequence}}</td>
														<td>
															{{if value.status == <%=BooleanConstants.TRUE%>}}
															上架
															{{/if}}
															{{if value.status == <%=BooleanConstants.FALSE%>}}
															下架
															{{/if}}
														</td>
														<td>
															<a target="_blank" href="/admin/goods/detail.xhtm?goodsKey={{value.goodsKey}}">查看详情</a>
															<a target="_blank" href="/admin/goods/editGoods.xhtm?goodsKey={{value.goodsKey}}">编辑</a>
														</td>
													</tr>
													{{/each}}
												</script>
											</table>

										</div>
									</div>
									<div class="row col-sm-12" id="listPaginator2">
									</div>
								</div><!-- /.box-body -->
							</div><!-- /.box -->
						</div> <!-- tab end -->

						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="storeMapTab">
							<div style="width: 100%;height: 600px;" id="mapContainer">

							</div>
						</div>
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

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<jsp:include page="/include/bottom-js.jsp" />
<jsp:include page="/include/map-lib.jsp" />
<script type="text/javascript">
	function search() {
		var keyword = $('#keyword').val();
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/goods/query",
			// 显示页码的位置
			paginator: 'listPaginator2',
			//dupPaginator: 'listPaginator2',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'keyword': keyword},
			logPage: 'list_store_page'
		});
	}

	$(function() {
		search();
	});
	
</script>
</body>
</html>
