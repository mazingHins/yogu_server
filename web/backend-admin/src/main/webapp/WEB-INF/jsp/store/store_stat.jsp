<%@ page import="com.mazing.core.enums.Role"%>
<%@ page import="com.mazing.core.enums.merchant.StoreBizType"%>
<%@ page import="com.mazing.core.enums.merchant.StoreStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>餐厅状态统计</title>
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
					餐厅状态 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="/admin/store/list.xhtm"><i
							class="fa fa-dashboard"></i> 餐厅列表</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div id="storeTabContent" class="tab-content">
							<!-- tab start -->
							<div class="tab-pane fade in active"
								style="background-color: #fff;" id="baseInfoTab">
								<table id="statInfoTable"
									class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>状态</th>
											<th>数值</th>
										</tr>
									</thead>
									<tbody id="statInfo">

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

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<jsp:include page="/include/map-lib.jsp" />
	
	<script type="text/javascript">
		// 查询门店的信息
		function query() {
			$.getJSON('/admin/store/storeStat',{},
				function(json) {
					if (json.success) {
						var htmlTxt = template('statInfoTemplate', json.object);
						$('#statInfo').html(htmlTxt);
					} else {
						MyDialog.alert(json.message);
					}
				});
		}

		$(function() {
			// 查询门店
			query();
		});
	</script>

	<script id="statInfoTemplate" type="text/html">
		<tr>
			<td>正常营业</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.IN_BUSSINESS.getValue()%>">{{IN_BUSSINESS}}</a></td>
		</tr>
		<tr>
			<td>休业</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.IN_REST.getValue()%>">{{IN_REST}}</a></td>
		</tr>
		<tr>
			<td>结业</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.CLOSED.getValue()%>">{{CLOSED}}</a></td>
		</tr>
		<tr>
			<td>审核中</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.CHECKING.getValue()%>">{{CHECKING}}</a></td>
		</tr>
		<tr>
			<td>即将开业</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.COMING_SOON.getValue()%>">{{COMING_SOON}}</a></td>
		</tr>
		<tr>
			<td>封号</td>
			<td><a href="/admin/store/list.xhtm?status=<%=StoreStatus.FROST.getValue()%>">{{FROST}}</a></td>
		</tr>
	</script>

</body>
</html>
