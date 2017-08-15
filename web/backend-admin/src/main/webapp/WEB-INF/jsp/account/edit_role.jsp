<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>新增角色</title>
	<link href="/static/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
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
				新增角色
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/account/listRole.xhtm"><i class="fa fa-dashboard"></i> 角色列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）先增加角色才能增加帐号；</p>
					<p class="text-left">（2）要为每个角色分配权限；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal" method="post">
						<div class="form-group">
							<label class="col-md-2 control-label">角色名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="rolename"
									   name="rolename" title="角色名称" placeholder="角色名称"
									   maxlength="30">
								<input name="roleId" id="roleId" type="hidden" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">备注</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="remarks" name="remarks"
									   title="备注" placeholder="备注" maxlength="100">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">权限</label>
								<div class="col-md-9">
									<div class="nav-tabs-custom">
										<ul class="nav nav-tabs" id="tabHolder">
										</ul>
										<div class="tab-content" id="tableContentHolder"></div>
									</div>
								</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary"
										type="button" id="submit" onclick="submitForm()">提交</button>
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
<script src="/static/js/ztree/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="/static/js/ztree/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var treeCount = 0;
	// 修改在编辑功能下的title
	function modifyTitle() {
		var newTitle = '修改角色';
		$('#titleMessage').html(newTitle)
		document.title = newTitle;
	}
	$(function() {
		var roleId = $.getUrlParam("roleId");
		if (typeof roleId == 'undefined' || roleId == null || roleId == '') {
			roleId = '0';
		}
		else {
			modifyTitle();
		}
		$('#roleId').val(roleId);

		$.getJSON('/admin/account/allAppMenus',
				{'roleId' : roleId},
				function(json) {
					if (json.success) {
						var treeDataList = json.appMenus;
						if (treeDataList.length > 0) {
							var setting = {
								check: {
									enable: true
								}
							};

							// 构造table的标题
							var appList = json.appList;
							var tabTitleHtml = '';
							for (var i = 0; i < appList.length; i++) {
								var app = appList[i];
								tabTitleHtml += '<li '
								+ (i == 0 ? 'class="active"' : '')
								+ '><a href="#appTab' + i + '" data-toggle="tab">'
								+ app.name + '</a></li>';
							}
							$('#tabHolder').html(tabTitleHtml);

							// 构造tab的内容
							var contentHtml = '';
							for (var i = 0; i < appList.length; i++) {
//								var app = appList[i];
								contentHtml += '<div class="tab-pane '
								+ (i == 0 ? 'active' : '')
								+ ' in" id="appTab'
								+ i
								+ '" title="appId_'
								+ appList[i].appId
								+ '"><div class="row">';
								contentHtml += '<ul id="appTree' + i + '" class="ztree"></ul></div></div>';
							}
							$('#tableContentHolder').html(contentHtml);

							var menuIdMap = {};
							var resIdMap = {};
							if (json.menuIds) {
								var menuIds = json.menuIds;
								for (var i = 0; i < menuIds.length; i++) {
									menuIdMap['id_' + menuIds[i].menuId] = '1';
								}
							}
							if (json.resIds) {
								var resIds = json.resIds;
								for (var i=0; i < resIds.length; i++) {
									resIdMap['id_' + resIds[i].resId] = '1';
								}
							}

							// 根据用户权限，设置菜单为checked状态
							function checkMenu(node) {
								var id = node.id;
								var children = node.children;
								if (children) {
									// 菜单
									var value = menuIdMap['id_' + id];
									if (value) {
										// ztree的node.checked标识这个节点是不是checked状态
										node['checked'] = true;
									}
								}
								else {
									// 叶子
									var value = resIdMap['id_' + id];
									if (value) {
										// ztree的node.checked标识这个节点是不是checked状态
										node['checked'] = true;
									}
								}

								if (children && children.length) {
									for (var i=0; i < children.length; i++) {
										checkMenu(children[i]);
									}
								}
							}

							// 构造tree
							for (var i = 0; i < appList.length; i++) {
								var data = treeDataList[i];
								checkMenu(data);
								$.fn.zTree.init($("#appTree" + i),
										setting, data);
								var zTree = $.fn.zTree
										.getZTreeObj("appTree" + i);
								zTree.expandAll(true);

								type = {
									"Y": "ps",
									"N": "ps"
								};
								zTree.setting.check.chkboxType = type;
							}

							// 显示role内容
							var role = json['role'];
							if (role) {
								$('#roleId').val(role.roleId);
								$('#rolename').val(role.roleName);
								$('#remarks').val(role.remarks);
							}

							treeCount = appList.length;

						}

					} else {
						MyDialog.alert(json.message);

					}
				});
	});

	// 校验参数
	function validate() {
		var message = '';
		var rolename = $.trim($('#rolename').val());
		var remarks = $.trim($('#remarks').val());
		if (rolename == '') {
			message = '请输入角色名称。';
		} else if (remarks == '') {
			message = '请输入备注';
		}
		if (message != '') {
			MyDialog.alert(message);
		}
		return (message == '');
	}

	// 提交表单内容
	function submitForm() {
		if (!validate())
			return;

		var appIds = [];
		var menuIds = [];

		// 读取选择到的menu id
		function getSelectedMenu() {
			appIds = [];
			menuIds = [];
			var ids = [];
			for (var j = 0; j < treeCount; j++) {
				var nodes = $.fn.zTree.getZTreeObj("appTree" + j)
						.getCheckedNodes(true), len = nodes.length;
				if (len > 0) {
					var appId = $("#appTab" + j).attr("title");
					appId = appId.substr(6);
					appIds.push(appId);
					// appIds.push(j + 1); //加入应用系统编号
					for (var i = 0; i < len; i++) {
						if (nodes[i].id > 0) {
							if (nodes[i].children) {
								// 是菜单
								menuIds.push(nodes[i].id);
							}
							else {
								// 叶子，是url resource
								ids.push(nodes[i].id);
							}
						}
					}
				}
			}
			return ids;
		}

		var ids = getSelectedMenu();

		if (appIds.length <= 0 || ids.length <= 0) {
			MyDialog.alert("请选择菜单权限。");
		}
		else {
			$.post('/admin/account/updateRole.do', {
				role: JSON.stringify({
					'roleId': $('#roleId').val(),
					'rolename': $.trim($('#rolename').val()),
					'remarks': $.trim($('#remarks').val())
				}),
				menuIds: JSON.stringify(menuIds),
				resIds: JSON.stringify(ids),
				appIds: JSON.stringify(appIds)
			}, function (json) {

				if (json.success) {
					MyDialog.alert(json.message, function() {
						window.location.href = "/admin/account/listRole.xhtm";
					});
				}
				else {
					MyDialog.alert(json.message);
				}
			}, 'json');
		}
	}
</script>
</body>
</html>
