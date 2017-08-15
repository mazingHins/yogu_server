<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>白名单管理</title>
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
				白名单管理
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="javascript:void(0)" data-toggle="modal" data-target="#configModal" onclick="addConfig()"><i class="fa fa-dashboard"></i> 新增白名单</a></li>
			</ol>
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
											<th>用户ID</th>
											<th>姓名</th>
											<th>帐号</th>
											<th>最后一次登录DID</th>
											<!-- <th>创建时间</th> -->
											<th>操作</th>
										</tr>
										</thead>
										<tbody id="listTableBody" style="font-size: 14px;">

										</tbody>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.uid}}</td>
												<td>{{value.realname}}</td>
												<td>{{value.mobile}}</td>
												<td>{{value.did}}</td>
												<!-- <td>{{formatDateTime value.createTime}}</td> -->
												<td>
													<a href="javascript:void(0)" class="btn btn-default" onclick="deleteWhiteList({{value.uid}}, '{{value.mobile}}', '{{value.realname}}')">删除</a>
												</td>
											</tr>
											{{/each}}
										</script>
									</table>
								</div>
							</div>

						</div><!-- /.box-body -->
					</div><!-- /.box -->

				</div><!-- /.col -->
			</div><!-- /.row -->

			<!-- Modal -->
			<div class="modal fade" id="configModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">增加白名单</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="mobile" class="control-label">米星帐号:</label>
								<input type="text" class="form-control" id="mobile" placeholder="请输入手机号码">
							</div>
							<div class="form-group">
								<label for="realname" class="control-label">姓名:</label>
								<input type="text" class="form-control" id="realname" placeholder="真实姓名">
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

	// 删除白名单
	function deleteWhiteList(uid, mobile, realname) {
		BootstrapDialog.confirm('确认删除这个白名单吗？帐号：' + mobile + "，姓名：" + realname, function(result) {
			if (result) {
				$.post('/admin/system/deleteWhitelist.do', {
					'uid': uid
				}, function (json) {
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
		var mobile = $.trim($('#mobile').val());
		var realname = $.trim($('#realname').val());
		if (mobile.length > 0 && realname.length > 0) {
			$.post('/admin/system/addWhitelist.do', {
				'mobile': mobile,
				'realname': realname
			}, function (json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新读一次信息
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
		$.getJSON("/admin/system/whitelist", {}, function(json) {
			if (json.success) {
				var htmlTxt = template('listTableTpl', json);
				$('#listTableBody').html(htmlTxt);
			}
			else {
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
