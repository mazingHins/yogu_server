<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>消息推送统计</title>
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
					消息推送统计 <small></small>
				</h1>
			</section>

			<section class="content">
					<div class="row">
						<div class="col-xs-12">
	
							<div class="box box-solid">
								<div class="box-body">
									
									<div class="row">
										<div class="col-xs-12" style="margin:10px;">
											<p class="text-left">说明：</p>
											<p class="text-left">（1）统计数据最快在发送完push后的<strong>2分钟</strong>内产生，当成功发送的<strong>总数不等于0</strong>时表示统计完成</p>
											<p class="text-left">（2）加载此页面时默认查询今天的消息推送统计数据</p>
										</div>
									</div>
									
									<div class="row">
										<div class="col-xs-12">
											<div class="input-group">
												请选择查询的日期范围：
												<input type="text" name="setTime" id="setTime" onfocus="showDatePicker()" style="width: 300px;">&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="button" class="btn btn-primary" id="query">查询</button>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-sm-12">
											<table id="listTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th>推送时间</th>
														<th>推送内容</th>
														<th>发送的系统类型</th>
														<th>发送者名称</th>
														<th>发送成功总数</th>
														<th>推送总数</th>
														<th>推送成功率</th>
													</tr>
												</thead>
												<tbody id="listTableBody" style="font-size: 14px;">
												</tbody>
												<script id="listTableTpl" type="text/html">
												{{each object as value i}}
													<tr>
														<td>
															{{value.create_time}}
														</td>
														<td>
															{{value.msg}}
														</td>
														<td>
															{{if value.type == 1}}
																IOS系统
															{{/if}}
															{{if value.type == 2}}
																Android系统
															{{/if}}
														</td>
														<td>
															{{value.admin_name}}
														</td>
														<td>
															{{value.total_success}}
														</td>
														<td>
															{{value.total}}
														</td>
														<td>
															{{if value.total_success != 0}}
																{{ (value.total_success / value.total) * 100 }}%
															{{/if}}
															{{if value.total_success == 0}}
																0%
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
	
	$("#query").click(function() {
		
		var setTime = $.trim($('#setTime').val());
		
		if(setTime == ""){
			MyDialog.alert("请选择查询时间段！");
		}else{
			var startTime = $.trim(setTime.substring(0,17));
			var endTime = $.trim(setTime.substring(18));
			query(startTime, endTime);
		}
	});
	
	//查询消息推送统计
	function query(startTime, endTime) {
		$.getJSON("/admin/system/messageList", {
			'startTime':startTime,
			'endTime':endTime
		}, function(json) {
			if (json.success) {
				var htmlTxt = template('listTableTpl', json);
				$('#listTableBody').html(htmlTxt);
			} else {
				MyDialog.alert(json.message);
			}
		});
	}
	// 显示日期插件
	function showDatePicker() {
		$("#setTime").daterangepicker({
			timePicker: true, timePickerIncrement: 1, format: 'YYYY-MM-DD HH:mm',
			timePicker12Hour: false,
			locale: { cancelLabel: '取消', applyLabel: '确定', fromLabel:'开始日期', toLabel:'结束日期' }
		});
	}
	// 加载页面时默认查询今天的消息推送统计
	$(function() {
		var startTime = "";
		var endTime = "";
		query(startTime, endTime);
	});
	
	</script>
</body>
</html>
