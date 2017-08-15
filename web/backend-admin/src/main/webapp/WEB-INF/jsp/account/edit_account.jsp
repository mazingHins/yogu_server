<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>新增管理员</title>
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
			<h1 id="titleMessage">
				新增管理员
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/account/listAccount.xhtm"><i class="fa fa-dashboard"></i> 管理员列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）先增加角色才能增加帐号；</p>
					<p class="text-left">（2）每个帐号必须分配一个角色；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" modelattribute="account"
						  onsubmit="return validateForm(this)" action="/admin/account/updateAccount.do">
						<div class="form-group">
							<label class="col-md-2 control-label">手机帐号</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="mobileNo"
									   name="mobileNo" title="手机号码" placeholder="手机号码"
									   maxlength="30" data-minlength="2" required="true" onblur="loadUid()">
								<input type="hidden" name="uid" id="uid" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">真实姓名</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="realname" name="realname"
									   title="真实姓名" placeholder="真实姓名" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">角色</label>
							<div class="col-md-3">
								<input type="hidden" name="roleId" id="roleId"
									   style="width: 100%;" required="true"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary"
									   type="submit" id="submit">提交</button>
							</div>
						</div>
					</form>
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

	// 修改在编辑功能下的title
	function modifyTitle() {
		var newTitle = '修改管理员';
		$('#titleMessage').html(newTitle)
		document.title = newTitle;
	}

	$(function () {
		$.getJSON("/admin/account/allRoles", {}, function(json) {
			if (json.success) {
				var roles = json.object;
				var name = $('#roleId');
				name.empty();
				var data = [];

				if (roles.length > 0) {
					for (var i = 0; i < roles.length; i++) {
						var item = roles[i];
						data.push({
							id: item.roleId,
							text: item.roleName + (item.remarks == '' ? '' : ' (' + item.remarks + ')')
						});
					}
				}
				name.select2({
					'allowClear' : true,
					'data': data,
					'placeholder': '请选择一个角色'
				});

				// 如果是编辑帐号，加载帐号信息
				loadAccount();
			}
			else {
				MyDialog.alert(json.message);
			}
		});

		$('#editForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					MyDialog.alert(json.message);
					var uid = $.getUrlParam("uid");
					if (uid) {
						// ignore
					}
					else {
						// 新增之后清空手机号码和 uid，否则不会更新
						$('#uid').val('0');
						$('#mobileNo').val('')
					}

				} catch (e) {
				}
			}
		});
	});

	// 读取当前要编辑的管理员信息
	function loadAccount() {
		var uid = $.getUrlParam("uid");
		if (uid) {
			modifyTitle();
			$.getJSON("/admin/account/getAdmin?uid=" + uid, {}, function(json) {
				if (json.success) {
					var account = json.object;
					$('#roleId').select2('val', '' + account.roleId);
					$('#uid').val(uid);
					$('#mobileNo').val(account.mobileNo).attr("readonly", "readonly");
					$('#realname').val(account.realname).attr("readonly", "readonly");
					$('.btn-primary').html('更新');
				}
				else {
					MyDialog.alert(json.message);
				}
			});
		}
	}

	// 校验参数
	function validateForm(form) {
		var roleId = $('#roleId').val();
		var mobileNo = $.trim($('#mobileNo').val());
		var realname = $('realname').val();
		var uid = $('#uid').val();
		var message = '';

		if (mobileNo == '') {
			message = '请输入手机帐号.';
		}
		else if (roleId == '' || roleId == '0') {
			message = '请为用户选择一个角色.'
		}

		if (message != '') {
			MyDialog.alert(message);
		}
		return (message == '');
	}

	// 根据手机号码加载用户信息
	function loadUid() {
		var uid = $('#uid').val();
		if (uid == '' || uid == '0') {
			var mobileNo = $.trim($('#mobileNo').val());
			if (mobileNo != '') {
				$.getJSON("/admin/account/queryByPhone", {
					'countryCode': '86', // 暂时用86，界面上不用选择国家
					'phone': mobileNo
				}, function (json) {
					if (json.success) {
						var account = json.object;
						$('#uid').val(account.uid);
						var realname = $('realname').val();
						if (realname == '' && account.nickname) {
							$('#realname').val(account.nickname);
						}

					}
					else {
						MyDialog.alert(json.message);
					}
				});
			}
		}
	}
</script>
</body>
</html>
