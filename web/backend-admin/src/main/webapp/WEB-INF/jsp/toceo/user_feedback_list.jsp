<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ page import="com.mazing.services.store.RecommendBlockAction"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.services.store.StoreConstants"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<title>To CEO--用户反馈列表</title>
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
				<h1 id="titleMessage">
					用户反馈列表 
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">

						<div class="box">
							<!-- /.box-header -->
							<div id="listDiv" class="box-body">
							<script id="listDivTpl" type="text/html">
								<table class="table table-bordered table-hover">
									<tr>
										<th>序号</th>
										<th>用户ID</th>
										<th>反馈内容</th>
										<th>反馈时间</th>
										<th>联系方式</th>
									</tr>
									{{each list as ufb i}}
										<tr>
											<td>{{ufb.feedbackId}}</td>
											<td>{{ufb.uid}}</td>
											<td>{{ufb.content}}</td>
											<td>{{ufb.createTime | dateFormat}}</td>
											<td>{{ufb.passport}}</td>
										</tr>
									{{/each}}
								</table>
							<div>
								{{if pageIndex > 1}}<a href="javascript:loadUserFeedbackList({{pageIndex-1}},{{pageSize}});" class="btn btn-primary">上一页</a>{{/if}}
								{{if pageSize == list.length}}<a href="javascript:loadUserFeedbackList({{pageIndex+1}},{{pageSize}});" class="btn btn-primary">下一页</a>{{/if}}
							</div>
							</script>
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

	</div>
	<!-- ./wrapper -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			loadUserFeedbackList();
		});

		function loadUserFeedbackList(pageIndex, pageSize){
			template.helper('dateFormat', function (date) {
				return moment.unix(date).format('YYYY-MM-DD HH:mm:ss');
			});
			pageIndex = pageIndex || ${pageIndex};
			pageSize = pageSize || ${pageSize};
			url = '/user/admin/getuserfeedback';
			requestNode(url, 'get', {pageIndex, pageSize}, function(data){
				var html = template('listDivTpl', data);
				document.getElementById('listDiv').innerHTML = html;
			});
		}
	</script>
</body>
</html>
