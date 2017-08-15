<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/include/meta.html"%>
		<title>定时任务管理</title>
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
						定时任务管理 <small></small>
					</h1>
				</section>
	
				<!-- Main content -->
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
	
							<div class="box box-solid">
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="listTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>分组</th>
														<th>任务Class</th>
														<th>cron表达式</th>
														<th>备注</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody id="listTableBody" style="font-size: 14px;">
												</tbody>
												<script id="listTableTpl" type="text/html">
												{{each object as value i}}
												<tr class="{{if value.status == <%=BooleanConstants.FALSE%>}}bg-warning{{/if}} {{if value.status == 2}}bg-danger{{/if}}">
													<td>{{value.jobGroup}}</td>
													<td>{{value.jobClass}}</td>
													<td>{{value.cronExpression}}</td>
													<td id="remarks_{{value.jobId}}">{{value.remarks}}</td>
													<td>
														{{if value.status == <%=BooleanConstants.TRUE%> || value.status == <%=BooleanConstants.FALSE%>}}
															{{if value.status == <%=BooleanConstants.TRUE%>}}<a class="btn btn-warning btn-sm" href="javascript:enable({{value.jobId}}, 0);">停用</a>{{/if}}
															{{if value.status == <%=BooleanConstants.FALSE%>}}<a class="btn btn-success btn-sm" href="javascript:enable({{value.jobId}}, 1);">启用</a>{{/if}}
															<a class="btn btn-success btn-sm" href="javascript:show({{value.jobId}}, '{{value.jobGroup}}', '{{value.jobName}}', '{{value.jobClass}}', '{{value.cronExpression}}');">cron</a>
														{{/if}}
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
					<div class="modal fade" id="jobModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">修改执行时间</h4>
									<input type="hidden" id="jobId">
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label for="mobile" class="control-label">任务分组</label>
										<input type="text" class="form-control" id="jobGroup" readonly>
									</div>
									<div class="form-group">
										<label for="mobile" class="control-label">任务名称</label>
										<input type="text" class="form-control" id="jobName" readonly>
									</div>
									<div class="form-group">
										<label for="realname" class="control-label">Class</label>
										<input type="text" class="form-control" id="jobClass" readonly>
									</div>
									<div class="form-group">
										<label for="realname" class="control-label">备注</label>
										<textarea id="remarks" class="form-control" rows="2" readonly></textarea>
									</div>
									<div class="form-group">
										<label for="mobile" class="control-label">当前Cron</label>
										<input type="text" class="form-control" id="cronExpression" readonly>
									</div>

									<div class="form-group">
										<label for="realname" class="control-label">执行周期</label>
										<input id="newCronExpression" type="text" class="form-control" placeholder="请输入新的cron表达式">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="updateCron();">修改</button>
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
		<script type="text/javascript">
			// 启用、停用
			function enable(jobId, enable) {
				$.getJSON('/admin/system/jobEnable', {
					'jobId' : jobId,
					'enable' : enable
				}, function(json) {
					if (!(json.success))
						MyDialog.alert(json.message);
					query();
				});
			}
	
			function show(jobId, jobGroup, jobName, jobClass, cronExpression) {
				$('#jobId').val(jobId);
				$('#jobGroup').val(jobGroup);
				$('#jobName').val(jobName);
				$('#jobClass').val(jobClass);
				$('#cronExpression').val(cronExpression);
				var remarks = $('#remarks_' + jobId).html();
				$('#remarks').val(remarks);
	
				$('#newCronExpression').val(cronExpression);
				$("#jobModal").modal("show");
			}
	
			// 保存cron
			function updateCron() {
				var jobId = $('#jobId').val();
				var newCronExpression = $.trim($('#newCronExpression').val());
				if (0 >= newCronExpression.length) {
					MyDialog.alert('请输入cron表达式');
					return;
				}
				$.post('/admin/system/updateCron.do', {
					'jobId' : jobId,
					'newCronExpression' : newCronExpression
				}, function(json) {
					MyDialog.alert(json.message);
					if (json.success) {
						$('#jobModal').modal('hide');
						query();
					}
				}, 'json');
			}
	
			function query() {
				$.getJSON("/admin/system/jobList", {}, function(json) {
					if (json.success) {
						var htmlTxt = template('listTableTpl', json);
						$('#listTableBody').html(htmlTxt);
					} else {
						MyDialog.alert(json.message);
					}
				});
			}
	
			$(function() {
				query();
			});
		</script>
	</body>
</html>
