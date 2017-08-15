<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>商家配送范围列表</title>
	<script type="text/javascript">
				
	</script>
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
				配送范围列表
				<small></small>
			</h1>
			<!-- 
			<ol class="breadcrumb">
				<li><a href="/admin/test/range/edit.xhtm?storeId=${param.storeId}"><i class="fa fa-dashboard"></i>新增配送范围</a></li>
			</ol>
			 -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th>名称</th>
											<th>起送金额（分）</th>
											<th>配送费用（分）</th>
											<th>免运费金额（分）</th>
											<th>配送时间（分钟）</th>
											<th>设定类型</th>
											<th>Value</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.rangeId}}</td>
												<td>{{value.name}}</td>
												<td>{{value.minimumMoney}}</td>
												<td>{{value.money}}</td>
												<td>{{value.fullFreeFreight}}</td>
												<td>{{value.minute}}</td>
												<td>
													{{if value.cutType == 1}}半径{{/if}}
													{{if value.cutType == 2}}多边形{{/if}}
												</td>
												<td>{{value.cutValue}}</td>
												<td>{{value.remark}}</td>
												<td>
													<a href="/admin/test/range/edit.xhtm?rangeId={{value.rangeId}}&storeId={{value.storeId}}">修改</a>
													<!-- 
													&nbsp;<a href="/admin/test/range/delete.xhtm?rangeId={{value.rangeId}}&storeId={{value.storeId}}">删除</a>
													 -->
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

	function listDishes() {
		var storeId = $.getUrlParam("storeId");
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/test/range/loadData",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}
	
	$(function(){
		listDishes();
	});
</script>
</body>
</html>
