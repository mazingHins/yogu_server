<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>系统配置管理</title>
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
				系统配置管理
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:void(0)" data-toggle="modal" data-target="#configModal" onclick="addConfig()"><i class="fa fa-dashboard"></i> 新增配置</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<ul id="cacheTab" class="nav nav-tabs">
						<li class="active">
							<a href="#systemConfig" data-toggle="tab">
								系统配置
							</a>
						</li>
						<li><a href="#otherConfig" data-toggle="tab">其他配置</a></li>
					</ul>

					<div id="cacheTabContent" class="tab-content">
						<!-- tab start 系统配置 -->
						<div class="tab-pane fade in active" id="systemConfig">
							<div class="box box-solid">
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="listTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th width="10%">分组</th>
													<th width="20%">Key</th>
													<th width="35%">Value</th>
													<th width="20%">备注</th>
													<th width="10%">创建时间</th>
													<th width="5%">操作</th>
												</tr>
												</thead>
												<tbody id="listTableBody" style="font-size: 14px;">

												</tbody>
												<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.groupCode}}
														</td>
														<td style="word-wrap:break-word;">{{renderConfigKey value.configKey}}</td>
														<td style="word-wrap:break-word;">{{renderConfigValue value.configValue}}</td>
														<td style="word-wrap:break-word;">{{value.remarks}}</td>
														<td>{{formatDateTime value.createTime}}</td>
														<td>
															<a href="javascript:void(0)" onclick="modify('{{value.groupCode}}', '{{value.configKey}}')" data-toggle="modal" data-target="#configModal">修改</a>
														</td>
													</tr>
													{{/each}}
												</script>
											</table>
										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->

						</div> <!-- /.tab -->
						<!-- tab end 系统配置 -->

						<!-- tab start 其他配置 -->
						<div class="tab-pane fade in" id="otherConfig">
							<div class="box box-solid">
								<div class="box-header">
									<div class="col-sm-8">
										<p class="text-left">其他配置。</p>
									</div>
								</div><!-- /.box-header -->
								<form role="form" id="createUserForm" name="createUserForm" action="/admin/user/createUser.do" method="post">
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">
												<div class="form-group">
													<label class="col-sm-2 control-label">
														配置
													</label>
													<div class="col-sm-8">
														<input type="text" name="cacheKey" class="form-control" placeholder="请输入一个key" maxlength="100"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-2">
														<button type="button" class="btn btn-primary">读取</button>
													</div>
												</div>

											</div>
										</div>
									</div><!-- /.box-body -->
								</form>
							</div><!-- /.box -->
						</div>
						<!-- tab end 其他配置 -->


					</div> <!-- /.tabContent - -->
				</div><!-- /.col -->
			</div><!-- /.row -->

			<!-- Modal -->
			<div class="modal fade" id="configModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">编辑配置</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="groupCode" class="control-label">分组:</label>
								<input type="text" class="form-control" id="groupCode">
							</div>
							<div class="form-group">
								<label for="configKey" class="control-label">Key:</label>
								<input type="text" class="form-control" id="configKey">
							</div>
							<div class="form-group">
								<label for="configValue" class="control-label">Value:</label>
								<textarea class="form-control" id="configValue"></textarea>
							</div>
							<div class="form-group">
								<label for="remark" class="control-label">备注:</label>
								<input type="text" class="form-control" id="remark">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" onclick="save()">保存</button>
						</div>
					</div>
				</div>
			</div>
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

	// 配置表
	var configMap = {};

	// 新增
	function addConfig() {
		$('#groupCode').val('');
		$('#configKey').val('');
		$('#configValue').val('');
		$('#remark').val('');
	}

	// 修改
	function modify(groupCode, configKey) {
		var key = groupCode + '_' + configKey;
		var row = configMap[key];
		if (row) {
			$('#groupCode').val(groupCode);
			$('#configKey').val(configKey);
			$('#configValue').val(row['configValue']);
			$('#remark').val(row['remarks']);
		}
		else {
			MyDialog.alert('找不到 ' + groupCode + " : " + configKey);
		}
	}

	// 保存
	function save() {
		var groupCode = $.trim($('#groupCode').val());
		var configKey = $.trim($('#configKey').val());
		var configValue = $.trim($('#configValue').val());
		var remark = $.trim($('#remark').val());
		if (groupCode && configKey && configValue && remark) {
			$.post('/admin/system/saveConfig.do', {
				'groupCode': groupCode,
				'configKey': configKey,
				'configValue': configValue,
				'remark': remark
			}, function (json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新读一次门店信息
					query();
				}
			}, 'json');
		}
		else {
			MyDialog.alert('每一项内容都不能为空，请准确填写');
		}
	}

	// 查询所有配置
	function query() {
		// 注意：这里可以使用一个参数 groupCode
		var groupCode = '';
		$.getJSON("/admin/system/listConfigs", {'groupCode' : groupCode}, function(json) {
			if (json.success) {
				var htmlTxt = template('listTableTpl', json);
				$('#listTableBody').html(htmlTxt);
				var list = json.object;
				for (var i=0; i < list.length; i++) {
					var row = list[i];
					var key = row.groupCode + '_' + row.configKey;
					configMap[key] = row;
				}
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}

	$(function() {
		template.helper('renderConfigValue', function (value) {
			if (value.length <= 50) {
				return value;
			}
			return value.substr(0, 47) + "……";
		});
		template.helper('renderConfigKey', function (value) {
			if (value.length <= 30) {
				return value;
			}
			return value.substr(0, 27) + "……";
		});

		query();
	});
</script>
</body>
</html>
