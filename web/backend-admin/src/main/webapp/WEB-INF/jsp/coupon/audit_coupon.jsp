<%@ page import="com.mazing.core.enums.merchant.StoreBizType" %>
<%@ page import="com.mazing.core.enums.merchant.StoreStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>优惠券审核</title>
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
				优惠券审核
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<!--
				<li><a href="#"><i class="fa fa-dashboard"></i> 新增优惠券</a></li>
				-->
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="col-sm-12" >
								<p class="text-info">请审核以下优惠券的发放申请</p>
							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th>名称</th>
											<th>类型</th>
											<th>面值</th>
											<th>发放总量</th>
											<th>使用人次</th>
											<th>生效时间</th>
											<th>过期时间</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.storeId}}
												</td>
												<td><a href="/admin/store/storeDetail.xhtm?storeId={{value.storeId}}">{{value.storeName}}</a></td>
												<td>{{value.address}}</td>
												<td>{{value.phone}}</td>
												<td>
													{{if value.bizType == <%=StoreBizType.NORMAL.getValue()%>}}
													常规类餐厅
													{{/if}}
													{{if value.bizType == <%=StoreBizType.ADVBOOK.getValue()%>}}
													预定类餐厅
													{{/if}}
													{{if value.bizType == 0}}
													未设置
													{{/if}}
												</td>
												<td>
													{{if value.status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
													<span style="color:green;">营业中</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.IN_REST.getValue()%>}}
													<span style="color:#808080;">休业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.CLOSED.getValue()%>}}
													<span style="color: #c9302c;">结业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.CHECKING.getValue()%>}}
													<span>审核中</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.COMING_SOON.getValue()%>}}
													<span style="color:blue;">即将开业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.FROST.getValue()%>}}
													<span style="color:red;">封号</span>
													{{/if}}
												</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													<a target="_blank" href="/admin/store/listStoreOrder.xhtm?storeId={{value.storeId}}">查看订单</a>
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
	function search(uid) {
		var keyword = $('#keyword').val();
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/store/query",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'uid': uid, 'keyword': keyword}
		});
	}

	$(function() {

	});
</script>
</body>
</html>
