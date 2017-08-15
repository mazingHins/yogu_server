<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>提现处理列表</title>
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
				提现待处理列表
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
											<th>提现批次号</th>
											<th>提现笔数</th>
											<th>提现总金额(元)</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.batchNo}}</td>
												<td>{{value.totalNum}}</td>
												<td>{{value.totalAmount}}</td>
												<td>
													{{if value.batchNo != ""}}
														<a class="btn btn-primary" href="javascript:void(0)" onclick="closeBatch('{{value.batchNo}}')">关闭</a>
														<div style="display: inline">
												    		<form id="detailInprogressForm" class="form-horizontal" method="post" style="display: inline"
																action="/admin/test/withdraw/inprogressListDetail.xhtm">
																<div class="form-group" style="display: none">
																	<label class="col-md-2 control-label">提现ID</label>
																		<div class="col-md-3">
																			<input class="form-control" type="text" id="withdrawIdsShowInProgress"
																				name="withdrawIds" title="提现ID" placeholder="提现ID"
																				readonly="true" value="{{value.withdrawIds}}" />
																		</div>
																</div>
																<button class="btn btn-primary" type="submit" id="submit">详细</button>
															</form>
														</div>
													{{/if}}
													{{if value.batchNo == ""}}
														<form id="editForm" class="form-horizontal" method="post" style="display: inline"
																action="/admin/test/withdraw/alipayapi.xhtm">
											
															<div class="form-group" style="display: none">
																<label class="col-md-2 control-label">提现ID</label>
																	<div class="col-md-3">
																		<input class="form-control" type="text" id="withdrawIds"
																			name="withdrawIds" title="提现ID" placeholder="提现ID"
																			readonly="true" value="{{value.withdrawIds}}" />
																	</div>
															</div>
															<button class="btn btn-primary" type="submit" id="submit">提现</button>
														</form>
														<div style="display: inline">
												    		<form id="detailForm" class="form-horizontal" method="post" style="display: inline"
																action="/admin/test/withdraw/applyListDetail.xhtm">
																<div class="form-group" style="display: none">
																	<label class="col-md-2 control-label">提现ID</label>
																		<div class="col-md-3">
																			<input class="form-control" type="text" id="withdrawIdsShow"
																				name="withdrawIds" title="提现ID" placeholder="提现ID"
																				readonly="true" value="{{value.withdrawIds}}" />
																		</div>
																</div>
																<button class="btn btn-primary" type="submit" id="submit">详细</button>
															</form>
														</div>
													{{/if}}
														
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

	function listApplyList() {
		var storeId = $.getUrlParam("storeId");
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/test/withdraw/loadWithdrawData",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}
	
	$(function(){
		listApplyList();
	});
	
	function closeBatch(batchNo){
		$.getJSON("/admin/test/withdraw/closeBatch?batchNo="+batchNo, {}, function(json) {
			MyDialog.alert(json.message);
			
			window.location.reload();
		});
	}
	
</script>
</body>
</html>
