<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>美食分类</title>
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
				美食分类
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
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>菜品分类ID</th>
											<th>菜品分类名称</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.categoryId}}
												</td>
												<td>{{value.categoryName}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													<a href="/admin/test/dish/category/editCategory.xhtm?categoryId={{value.categoryId}}&categoryName={{value.categoryName}}">修改</a>
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

	function listCategory() {
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/test/dish/category/allCategories",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {}
		});
	}
	
	$(function(){
		listCategory();
	});
</script>
</body>
</html>
