<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>角色列表</title>
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
				角色列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/account/editRole.xhtm"><i class="fa fa-dashboard"></i> 新增角色</a></li>
			</ol>
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
											<th>Role ID</th>
											<th>Name</th>
											<th>Remarks</th>
											<th>Create Time</th>
											<th>Operation</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.roleId}}
												</td>
												<td>{{value.roleName}}</td>
												<td>{{value.remarks}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													<a href="javascript:deleteRole({{value.roleId}});">删除</a>
													&nbsp; <a href="/admin/account/editRole.xhtm?roleId={{value.roleId}}">修改</a>
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
	function search() {
		var keyword = $('#keyword').val();
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/account/queryRole",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {keyword: keyword}
		});
	}

	// 删除角色
	function deleteRole(roleId) {

	}

	$(function(){
		search();
	});
</script>
</body>
</html>
