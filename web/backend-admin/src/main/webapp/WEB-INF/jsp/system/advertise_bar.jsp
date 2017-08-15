<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>栏位广告配置管理</title>
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
					栏位广告配置 <small></small>
				</h1>
				<!--  -->
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#configModal" onclick="reset()"><i
							class="fa fa-dashboard"></i> 新增配置</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="cacheTab" class="nav nav-tabs">
							<li class="active"><a href="#getLatestAdverBar"
								data-toggle="tab"> 栏位广告配置 </a></li>
							<!-- -->
						</ul>

						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 系统配置 -->
							<div class="tab-pane fade in active" id="barConfig">
								<div class="box box-solid">
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12">
												<div style="word-wrap: break-word; color: red;">注意：
													目前只展示最近配置的一条数据,配置新的数据至正式环境时请先确保在测试环境已测试验收通过</div>
												</br> </br>
												<table id="barTable"
													class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="8%">栏位提示语</th>
															<th width="8%">栏位位置</th>
															<th width="8%">展示类型</th>
															<th width="8%">栏位icon</th>
															<th width="8%">icon位置</th>
															<th width="10%">跳转链接</th>
															<th width="5%">是否可分享</th>
															<th width="8%">背景颜色代码</th>
															<th width="10%">活动开始时间</th>
															<th width="10%">活动结束时间</th>

														</tr>
													</thead>
													<tbody id="listTableBody" style="font-size: 14px;">

													</tbody>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.msg}}
														</td>
														<td style="word-wrap:break-word;">
															{{if value.position == 1}}
																置底
															{{/if}}
															
															{{if value.position == 2}}
																置顶
															{{/if}}
														</td>
														<td style="word-wrap:break-word;">
															{{if value.type == 1}}
																一直展示
															{{/if}}
															
															{{if value.type == 2}}
																点击1次之后消失
															{{/if}}
														</td>
	
													<td style="word-wrap:break-word;">{{value.icon}}</td>

													<td style="word-wrap:break-word;">
															{{if value.iconPosition == 1}}
																左边
															{{/if}}
															
															{{if value.iconPosition == 2}}
																右边
															{{/if}}
													</td>

													<td style="word-wrap:break-word;">{{value.url}}</td>

													<td style="word-wrap:break-word;">
															{{if value.shareFlag == 1}}
																是
															{{/if}}
															
															{{if value.shareFlag == 0}}
																否
															{{/if}}
													</td>

													<td style="word-wrap:break-word;">{{value.bgcolor}}</td>

													<td style="word-wrap:break-word;">{{formatDateTime value.beginTime}}</td>
														
													<td style="word-wrap:break-word;">{{formatDateTime value.endTime}}</td>	

													<td>
													<a href="javascript:deleteAdverBar({{value.bid}});">删除</a>
													&nbsp; <a href="javascript:void(0)" onclick="modifyBar('{{value.msg}}','{{value.position}}','{{formatDateTime value.beginTime}}', '{{formatDateTime value.endTime}}', '{{value.url}}', '{{value.shareFlag}}', '{{value.type}}','{{value.icon}}','{{value.iconPosition}}', '{{value.bgcolor}}', '{{value.bid}}', '{{value.status}}')" data-toggle="modal" data-target="#configModal">修改</a>
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
							<!-- /.tab -->
							<!-- tab end 系统配置 -->

							<!-- tab start 其他配置 -->
							<!-- tab end 其他配置 -->


						</div>
						<!-- /.tabContent - -->
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
								<!-- -->
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="reset()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">编辑配置</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal">
									<div class="form-group">
										<label for="msg" class="control-label col-md-4">栏位提示语<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="msg">
										</div>
									</div>
									<div class="form-group">
										<label for="type" class="control-label col-md-4">展示类型:<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<select name="select" id="type">
												<option value="1" selected="selected  col-md-4">一直展示</option>
												<option value="2">点击1次之后消失</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label for="position" class="control-label col-md-4">展示位置:<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<select name="select2" id="position">
												<option value="1" selected="selected  col-md-4">置底</option>
												<option value="2">置顶</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="icon" class="control-label col-md-4">icon图标</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="icon">
										</div>
									</div>

									<div class="form-group">
										<label for="iconPosition" class="control-label col-md-4">icon图标位置:</label>
										<div class="col-md-8">
											<select name="select3" id="iconPosition">
												<option value="1"  selected="selected col-md-4">左边</option>
												<option value="2">右边</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label for="url" class="control-label col-md-4">跳转链接:
											<span style="color: red;">*</span>
										</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="url"
												placeholder="http地址">
										</div>
									</div>

									<div class="form-group">
										<label for="bgcolor" class="control-label col-md-4">背景色代码</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="bgcolor">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-4">生效时间 <span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" class="form-control pull-right active"
													name="effectiveTimeRange" id="effectiveTimeRange">
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="shareFlag" class="control-label col-md-4">是否可分享:
											<span style="color: red;">*</span>
										</label>
										<!--  <input type="text" class="form-control" id="isOpen"> -->
										<div class="col-md-3">
											<select name="select4" id="shareFlag" class="form-control">
												<option value="0" selected="selected col-md-4">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="status" class="control-label col-md-4">是否有效:
											<span style="color: red;">*</span>
										</label>
										<!--  <input type="text" class="form-control" id="isOpen"> -->
										<div class="col-md-3">
											<select name="select4" id="status" class="form-control">
												<option value="1" selected="selected col-md-4">是</option>
												<option value="2">否</option>
											</select>
										</div>
									</div>
									<div>
										<input type="hidden" class="form-control" id="bid" value="">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="reset()">取消</button>
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
	</div>
	<!-- ./wrapper -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		// 配置表
		var configMap = {};

		// 新增
		function reset() {
			$('#bid').val('');
			$('#msg').val('');
			$('#effectiveTimeRange').val('');
			$('#position').val('1');
			$('#url').val('');
			$('#shareFlag').val('0');
			$('#type').val('1');
			$('#icon').val('');
			$('#iconPosition').val('1');
			$('#bgcolor').val('');
			$('#status').val('1');
		}

		function pre(el) {
			el.style.color = "black";
		}

		function strDateTime2(str) {
			//格式2003-12-05 13:04:06
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			var r = str.match(reg);
			if (r == null)
				return false;
			var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
			return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
					&& d.getDate() == r[4] && d.getHours() == r[5]
					&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
		}

		// 修改弹窗广告
		function modifyBar(msg, position, eginTime, endTime, url, shareFlag,
				type, icon, iconPosition, bgcolor, bid, status) {

			$('#bid').val(bid);
			$('#msg').val(msg);
			//设置某项为选中状态    
			$("#position").get(0).selectedIndex = position - 1;
			
			$("#status").get(0).selectedIndex = status - 1;
			
			var effectiveTimeRange = "" + beginTime + " - " + endTime;
			$('#effectiveTimeRange').val(effectiveTimeRange);

			$('#url').val(url);
			$("#shareFlag").get(0).selectedIndex = shareFlag;

			$("#type").get(0).selectedIndex = type - 1;
			$('#icon').val(icon);
			$("#iconPosition").get(0).selectedIndex = iconPosition - 1;
			$('#bgcolor').val(bgcolor);

		}

		String.prototype.replaceAll = function(s1, s2) {
			return this.replace(new RegExp(s1, "g"), s2);
		}

		// 删除
		var executeDelete;
		function deleteAdverBar(bid) {
			executeDelete = false;

			BootstrapDialog.show({
				title : '删除最近一条栏位广告',
				message : '确认要删除这条记录吗?',
				buttons : [ {
					label : '确定',
					action : function(dialog) {
						executeDelete = true;
						dialog.close();
					}
				}, {
					label : '取消',
					action : function(dialog) {
						dialog.close();
					}
				} ],
				onhide : function(dialogRef) {
					if (executeDelete == true) {
						$.post('/admin/system/deleteAdverBar.do', {
							'bid' : bid
						}, function(json) {
							MyDialog.alert(json.message);
							if (json.success) {
								// 重新拉取
								getLatestAdverBar();
								$('#aid').val();
								executeDelete = false;
							}
						}, 'json');
					}
				}
			});

		}

		// 保存
		function save() {

			var bid = $('#bid').val();
			var msg = $.trim($('#msg').val());
			var position = $.trim($('#position').val());
			var effectiveTimeRange = $('#effectiveTimeRange').val();

			if (effectiveTimeRange == "") {
				MyDialog.alert('活动时间范围不能为空，请准确填写');
				return;
			}
			var timeArr = effectiveTimeRange.split(" - ");

			var beginTime = timeArr[0];
			var endTime = timeArr[1];

			var url = $.trim($('#url').val());
			var shareFlag = $.trim($('#shareFlag').val());
			var type = $.trim($('#type').val());
			var icon = $.trim($('#icon').val());
			var iconPosition = $.trim($('#iconPosition').val());
			var bgcolor = $.trim($('#bgcolor').val());
			var status = $.trim($('#status').val());

			if (msg == "") {
				MyDialog.alert('栏位提示语不能为空，请准确填写');
				return;
			}
			if(bid == ""){
				bid = 0;
			}

			$.post('/admin/system/addAdverBar.do', {
				'bid' : bid,
				'msg' : msg,
				'position' : position,
				'url' : url,
				'shareFlag' : shareFlag,
				'type' : type,
				'icon' : icon,
				'iconPosition' : iconPosition,
				'bgcolor' : bgcolor,
				'beginTime' : beginTime,
				'endTime' : endTime,
				'status' : status
			}, function(json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新拉取
					getLatestAdverBar();
				}
			}, 'json');

			$('#bid').val('');
		}

		// 查询最近一条栏位广告
		function getLatestAdverBar() {

			$.getJSON("/admin/system/getLatestAdverBar", {}, function(json) {
				if (json.success) {
					var htmlTxt = template('listTableTpl', json);
					$('#listTableBody').html(htmlTxt);
					var list = json.object;
					for (var i = 0; i < list.length; i++) {
						var row = list[i];
						var key = row.groupCode + '_' + row.configKey;
						configMap[key] = row;
					}
				} else {
					MyDialog.alert(json.message);
				}
			});

			$('#aid').val('');
		}

		$(function() {
			template.helper('renderConfigValue', function(value) {
				if (value.length <= 50) {
					return value;
				}
				return value.substr(0, 47) + "……";
			});
			template.helper('renderConfigKey', function(value) {
				if (value.length <= 30) {
					return value;
				}
				return value.substr(0, 27) + "……";
			});

			// 选择时间范围控件
			$('#effectiveTimeRange').daterangepicker({
				timePicker : true,
				timePickerIncrement : 1,
				format : 'YYYY-MM-DD HH:mm:ss',
				timePicker12Hour : false,
				locale : {
					cancelLabel : '取消',
					applyLabel : '确定',
					fromLabel : '开始日期',
					toLabel : '结束日期'
				}
			});

			getLatestAdverBar();

		});
	</script>
</body>
</html>
