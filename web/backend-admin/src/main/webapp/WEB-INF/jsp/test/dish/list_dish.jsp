<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>商家列表</title>
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
				菜品列表
				<small></small>
			</h1>
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
											<th>菜品ID</th>
											<th>菜品Key</th>
											<th>菜品名称</th>
											<th>门店名称</th>
											<th>每日供应</th>
											<th>上架/下架</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.dishId}}
												</td>
												<td>{{value.dishKey}}</td>
												<td>{{value.dishName}}</td>
												<td>{{value.storeName}}</td>
												<td>{{value.dailyNum}}</td>
												<td>
													{{if value.status == 1}}
													已上架
													{{/if}}
													{{if value.status == 2}}
													已下架
													{{/if}}
												</td>	
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													{{if value.status == 1}}
													<a href="javascript:void(0)" onclick="setStatusOff({{value.dishId}})">下架</a>
													{{/if}}
													{{if value.status == 2}}
													<a href="javascript:void(0)" onclick="setStatusOn({{value.dishId}})">上架</a>
													{{/if}}
													&nbsp;
													<a href="/admin/test/dish/setSurplus.xhtm?dishId={{value.dishId}}&dailyNum={{value.dailyNum}}">库存修改</a>
													&nbsp;
													<a href="/admin/test/dish/editDish.xhtm?dishKey={{value.dishKey}}">修改菜品</a>
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
			url: "/admin/test/dish/loadDishes",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}
	
	function setStatusOff(dishId){
		$.getJSON("/admin/test/dish/down?dishId="+dishId, {}, function(json) {
			MyDialog.alert(json.message);
			location.replace(document.referrer);
		});
		
	}
	
	function setStatusOn(dishId){
		$.getJSON("/admin/test/dish/up?dishId="+dishId, {}, function(json) {
			MyDialog.alert(json.message);
			//location.replace(document.referrer);
			window.location.reload();
		});
		
	}
	
	$(function(){
		listDishes();
	});
</script>
</body>
</html>
