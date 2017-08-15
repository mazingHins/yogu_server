<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>优惠券黑名单管理</title>
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
					优惠券黑名单管理 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#configModal" onclick="addConfig()"><i
							class="fa fa-dashboard"></i> 新增黑名单</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-sm-12">
						<p class="text-left">说明：</p>
						<p class="text-left">（1）新增的黑名单商家， 并不会自动应用于 在线的优惠券，
							若想应用于优惠券，请对优惠券单独进行编辑</p>
						<p class="text-left">（2）删除的黑名单商家， 将会从在线的优惠券黑名单中剔除， 请谨慎操作</p>
					</div>

					<div class="col-xs-12">

						<div class="box box-solid">
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<table id="listTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th width="10%">记录ID</th>
													<th width="20%">餐厅ID</th>
													<th width="40%">餐厅名</th>
													<th width="20%">创建时间</th>
													<th width="10%">操作</th>
												</tr>
											</thead>
											<tbody id="listTableBody" style="font-size: 14px;">

											</tbody>
											<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.id}}
												</td>
												<td>{{value.storeId}}</td>
												<td>{{value.storeName}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													<a href="javascript:void(0)" class="btn btn-default" onclick="deleteWhiteList({{value.id}}, '{{value.storeId}}', '{{value.storeName}}')">删除</a>
												</td>
											</tr>
											{{/each}}
										</script>
										</table>
									</div>
								</div>

							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<!-- Modal -->
				<div class="modal fade" id="configModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">增加黑名单</h4>
							</div>
							<div class="modal-body">
								<div class="form-group" align="right">
										<a id="showModalName" class="btn btn-default" href="javascript:showModal();">请选择餐厅</a>
								</div>
								
								<div class="form-group">
									<label for="storeId" class="control-label">餐厅ID:</label> <input
										type="text" class="form-control" id="storeId"
										placeholder="请输入餐厅id">
								</div>
								<div class="form-group">
									<label for="storeName" class="control-label">餐厅名称:</label> <input
										type="text" class="form-control" id="storeName"
										placeholder="餐厅名称">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary" onclick="save()">保存</button>
							</div>
						</div>
					</div>
				</div>


			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- footer -->
		<jsp:include page="/include/footer.jsp" />

		<!-- control sidebar -->
		<jsp:include page="/include/control-sidebar.jsp" />
		
		
				<!-- 餐厅列表Modal -->
				<div class="modal fade" id="storeModal" tabindex="-1" role="dialog"
					aria-labelledby="storeModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="storeModalLabel">选择餐厅</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-md-12">
										<div class="input-group input-group-sm">
											<input id="storeKeyword" name="storeKeyword" maxlength="30"
												class="form-control" placeholder="输入关键字进行搜索"> <span
												class="input-group-btn">
												<button type="button" class="btn btn-info btn-flat"
													onclick="searchStore();">Go!</button>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table id="storeTable"
											class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>餐厅名称</th>
													<th>餐厅评分</th>
												</tr>
											</thead>
										<script id="storeTableTpl" type="text/html">
										{{each object as value i}}
											<tr>
												<td>
													<a href="javascript:selectStore({{value.storeId}},'{{value.storeName}}')">{{value.storeName}}</a>
												</td>
												<td>
													{{renderStoreStar value.star}}
												</td>
											</tr>
										{{/each}}
									</script>
										</table>
									</div>
								</div>
								<div class="row" id="storePaginator"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">确认</button>
							</div>
						</div>
					</div>
				</div>
				
	</div>
	<!-- ./wrapper -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		// 打开模态窗口
		// 根据target决定打开那个modal
		function showModal() {
			$("#storeModal").modal("show");
			searchStore();
		}

		
		function searchStore() {
			var keyword = $('#storeKeyword').val();
			$('#storeTable').artPaginate({
				type : 'post',
				url : "/admin/store/query",// 获取数据的地址
				paginator : 'storePaginator',// 显示页码的位置
				tpl : 'storeTableTpl',// 模版ID
				params : { 'keyword' : keyword },// 请求的参数表
				pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
			});
		}
		
		function selectStore(storeId, storeName){
			$("#storeId").val(storeId);
			$("#storeName").val(storeName);
		}
		
		
		function addConfig() {

			$("#storeId").val("");
			$("#storeName").val("");
		}
		// 删除白名单
		function deleteWhiteList(id, storeId, storeName) {
			BootstrapDialog.confirm('确认删除这个黑名单吗？餐厅ID：' + storeId + "，餐厅名："
					+ storeName, function(result) {
				if (result) {
					$.post('/admin/coupon/deleteBlackRecord.do', {
						'bid' : id
					}, function(json) {
						MyDialog.alert(json.message);
						if (json.success) {
							// 重新读一次信息
							query();
						}
					}, 'json');
				}
			});
		}

		// 保存
		function save() {
			var storeId = $.trim($('#storeId').val());
			var storeName = $.trim($('#storeName').val());
			if (storeId.length > 0 && storeName.length > 0) {
				$.post('/admin/coupon/addBlackRecord.do', {
					'storeId' : storeId,
					'storeName' : storeName
				}, function(json) {
					$('#configModal').modal('hide');
					MyDialog.alert(json.message);
					if (json.success) {
						// 重新读一次信息
						query();
					}
				}, 'json');
			} else {
				MyDialog.alert('每一项内容都不能为空，请准确填写');
			}
		}

		// 查询所有配置
		function query() {
			// 注意：这里可以使用一个参数 groupCode
			var groupCode = '';
			$.getJSON("/admin/coupon/queryCouponBlacklist", {}, function(json) {
				if (json.success) {
					var htmlTxt = template('listTableTpl', json);
					$('#listTableBody').html(htmlTxt);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		$(function() {
			
			template.helper('renderStoreStar', function(star) { return star / 10; });
			
			query();
		});
	</script>
</body>
</html>
