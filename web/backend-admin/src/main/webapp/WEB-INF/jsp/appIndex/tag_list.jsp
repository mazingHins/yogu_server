<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>餐厅标签数据管理</title>
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
					餐厅标签数据管理 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#categoryModal" onclick="clearInput()"><i
							class="fa fa-dashboard"></i> 新增标签类别</a></li>
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#tagModal" onclick="clearInput()"><i
							class="fa fa-dashboard"></i> 新增标签</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="cacheTab" class="nav nav-tabs">
							<li class="active"><a href="#storeTags" data-toggle="tab">
									餐厅标签 </a></li>
							<!--  <li><a href="#otherConfig" data-toggle="tab">其他配置</a></li>-->
						</ul>

						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 餐厅标签 -->
							<div class="tab-pane fade in active" id="storeTags">
								<div class="box box-solid">
									<div class="box-header with-border">
										<p class="text-red">重要说明：</p>
										<p class="text-left">（1）如果你删除了一个 tag，这个tag在所有餐厅消失。</p>
										<p class="text-left">（2）如果你删除了一个 tag类别，这个类别下所有的tag在所有餐厅消失。</p>
										<p class="text-left">（3）tag拥有ID和名称，编辑一个tag，只是改了它的名称。比如某餐厅只有一个tag叫“海鲜”，管理员把“海鲜”改为了“鲍鱼”，部分客户端更新没那么及时，依然显示“海鲜”，但提交搜索后，还是能搜到这个餐厅，因为tag的ID没变。</p>
										<p class="text-left text-red">（4）TAG不建议设置过多，会对服务器性能有一定的影响；如果某些TAG不再使用了，可以考虑改名，重复使用。</p>
										
										<p class="text-left text-red">注意： 修改标签的名称，将会发生两种情况-> 1. 将标签名改成其它已存在的标签名, 该标签的ID 也会发生变化, 变成已存在的那个标签的ID值 ;
																		2. 将该标签名改成还不存在的标签名, 其它分类中该相同标签名的标签 的名字也会一起变更
																		3. 如果要修改的标签已在多个组存在, 如果是情况1, 那么将只改变 现在该组的标签,不会变更其它组 相同名的标签</p>
									</div><!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12">
												<table id="listTable"
													class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="30%">标签类别<span class="text-red">（红色表示不在APP上显示）</span></th>
															<th width="60%">标签名<span class="text-red">（红色表示不在APP上显示）</span></th>
														</tr>
													</thead>
													<tbody id="listTableBody" style="font-size: 14px;">

													</tbody>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td class="{{if value.appShow != 1}}text-red{{/if}}">
															<span style="font-size: 16px;">{{value.categoryName}}  {{value.enName}}</span><br/>
															<br/>
															<a href="javascript:void(0)" class="btn btn-sm" onclick="updateCategoryName({{value.categoryId}},'{{value.categoryName}}','{{value.enName}}',{{value.appShow}})">编辑</a> &nbsp;
															<a href="javascript:void(0)" class="btn btn-sm" onclick="deleteCategory({{value.categoryId}},'{{value.categoryName}}')">删除</a> &nbsp;
															<a href="javascript:void(0)" class="btn btn-sm" onclick="moveCategory({{value.categoryId}},-1)"> ↑ </a> &nbsp;
															<a href="javascript:void(0)" class="btn btn-sm" onclick="moveCategory({{value.categoryId}},1)"> ↓ </a> &nbsp;
														</td>
														<td>
															<table class="table table-bordered table-hover">
															{{each value.tags as tag j}}
															<tr class="{{if value.appShow != 1}}text-red{{/if}}">
																<td>
																	{{tag.tagName}}
																</td>
																<td>
																	{{tag.enName}}
																</td>
																<td>
																	{{tag.tagId}}
																</td>
																<td>
																	{{if tag.tagId == 8888}}
																		 该tag&nbsp;
																		 不能&nbsp;
																		 编辑&nbsp;
																		 &nbsp;
																	{{/if}}
																	{{if tag.tagId != 8888}}
																		<a href="javascript:void(0)" class="btn btn-xs" onclick="updateTagName({{tag.tagId}},{{tag.categoryId}},'{{tag.tagName}}','{{tag.enName}}')">编辑</a> &nbsp;
																		<a href="javascript:void(0)" class="btn btn-xs" onclick="deleteTag({{tag.tagId}},{{tag.categoryId}},'{{tag.tagName}}')">删除</a> &nbsp;
																		<a href="javascript:void(0)" class="btn btn-xs" onclick="moveTag({{tag.tagId}},{{tag.categoryId}},-1)"> ↑ </a> &nbsp;
																		<a href="javascript:void(0)" class="btn btn-xs" onclick="moveTag({{tag.tagId}},{{tag.categoryId}},1)"> ↓ </a> &nbsp;
																	{{/if}}	
																					
																	
																</td>
															</tr>
															{{/each}}
															</table>
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
						</div>
						<!-- /.tabContent - -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<!-- Modal -->
				<div class="modal fade" id="categoryModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
							    <button type="button" class="close" data-dismiss="modal"
										aria-label="Close" onclick="clearInput()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">新增/修改标签分类</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal">
								<div class="form-group">
									<label for="categoryName" class="control-label col-md-4">标签类别中文名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="categoryName">
									</div>
								</div>
								<div class="form-group">
									<label for="categoryEnName" class="control-label col-md-4">标签类别英文名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="categoryEnName">
									</div>
								</div>
								<div class="form-group">
									<label for="appShow" class="control-label col-md-4">在APP上显示</label>
									<div class="col-md-8">
										<label><input type="checkbox" id="appShow" value="1" checked="checked">显示（不选表示：不显示）</label>
									</div>
								</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="clearInput()">取消</button>
								<button type="button" class="btn btn-primary" onclick="saveCategory()">保存</button>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="modal fade" id="tagModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel2">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
							    <button type="button" class="close" data-dismiss="modal"
										aria-label="Close" onclick="clearInput()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel2">新增/修改标签</h4>
							</div>
							<div class="modal-body">
								<form id="editForm2" class="form-horizontal">
								<div class="form-group">
									<label for="tagCategory" class="control-label col-md-4">标签类别</label>
									<div class="col-md-8">
										<select name="tagCategory" id="tagCategory" class="form-control">
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label for="tagName" class="control-label col-md-4">标签中文名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="tagName">
									</div>
									<label for="tagEnName" class="control-label col-md-4">标签英文名</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="tagEnName">
									</div>
								</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="clearInput()">取消</button>
								<button type="button" class="btn btn-primary" onclick="saveTag()">保存</button>
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
		function search() {
			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "/admin/appIndex/storeTag/list",
				// 显示页码的位置
				paginator : 'listPaginator',
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {},
				page: 1,
				pageSize: 500
			});
		}
		
		function reloadCategory() {
			$.getJSON('/admin/appIndex/storeTag/listCategroy', function(json) {
				if (json.success) {
					// 重新拉取
					var categories = json.object;
					if (categories.length > 0) {
						$('#tagCategory').html('');
						for (var i = 0; i < categories.length; i++) {
							jQuery("<option></option>").val(categories[i].categoryId).text(categories[i].categoryName).appendTo("#tagCategory");
						}
					}
				}
			});
		}
		
		function clearInput() {
			$('#categoryName').val('');
			$('#tagCategory').val('');
			$('#tagName').val('');
			$('#tagEnName').val('');
		}
		
		function saveCategory() {
			var categoryName = $.trim($('#categoryName').val());
			var categoryEnName = $.trim($('#categoryEnName').val());
			var appShow = $('#appShow').is(':checked');
			var appShowVal = (appShow ? 1 : 0);
			if (categoryName == "") {
				MyDialog.alert('标签分类中文名不能为空,请准确填写');
				return;
			}
			if (categoryEnName == "") {
				MyDialog.alert('标签分类英文名不能为空,请准确填写');
				return;
			}
			$.post('/admin/appIndex/storeTag/saveCategory.do', {
				'categoryName' : categoryName,
				'categoryEnName' : categoryEnName,
				'appShow' : appShowVal
			}, function(json) {
				$('#categoryModal').modal('hide');
				// 检查是否unique key重复
				if (json.message.indexOf("MySQLIntegrityConstraintViolationException") > 0) {
					MyDialog.alert("名称不能重复");
				} else {
					MyDialog.alert(json.message);
				}
				if (json.success) {
					// 重新拉取
					search();
					reloadCategory();
				}
			}, 'json');
		}
		
		function saveTag() {
			var tagCategory = $('#tagCategory').val();
			var tagName = $.trim($("#tagName").val());
			var tagEnName = $.trim($("#tagEnName").val());
			
			if (tagCategory == "" || tagCategory == null) {
				MyDialog.alert('标签类别不能为空,请准确选择');
				return;
			}
			
			if (tagName == "") {
				MyDialog.alert('标签中文名不能为空,请准确填写');
				return;
			}
			if(tagName.length > 32){
				MyDialog.alert('标签中文名长度不能超过32,请准确填写');
				return;
			}
			
			if (tagEnName == "") {
				MyDialog.alert('标签英文名不能为空,请准确填写');
				return;
			}
			if(tagEnName.length > 32){
				MyDialog.alert('标签英文名长度不能超过32,请准确填写');
				return;
			}
			
			$.post('/admin/appIndex/storeTag/saveTag.do', {
				'categoryId' : tagCategory,
				'tagName' : tagName, 
				'tagEnName' : tagEnName 
			}, function(json) {
				$('#tagModal').modal('hide');
				// 检查是否unique key重复
				if (json.message.indexOf("MySQLIntegrityConstraintViolationException") > 0) {
					MyDialog.alert("名称不能重复");
				} else {
					MyDialog.alert(json.message);
				}
				if (json.success) {
					// 重新拉取
					search();
				}
			}, 'json');
		}

		// 删除标签
		function deleteTag(tagId, categoryId, tagName) {
			BootstrapDialog.confirm("删除标签，可能会影响到cblog的发现，store，app首页等模块的功能。确认删除标签 \"" + tagName + "\" ？", function(result) {
				if (result) {
					$.post('/admin/appIndex/storeTag/deleteTag.do', {
						'tagId' : tagId,
						'categoryId' : categoryId
					}, function(json) {
						MyDialog.alert(json.message);
						if (json.success) {
							// 重新拉取
							search();
							reloadCategory();
						}
					}, 'json');
				}
			});
		}
		
		// 删除标签组
		function deleteCategory(categoryId, categoryName) {
			BootstrapDialog.confirm("删除标签组，可能会影响到cblog的发现，store，app首页等模块的功能。确认删除标签组 \"" + categoryName + "\" ？", function(result) {
				if (result) {
					$.post('/admin/appIndex/storeTag/deleteCategory.do', {
						'categoryId' : categoryId
					}, function(json) {
						MyDialog.alert(json.message);
						if (json.success) {
							// 重新拉取
							search();
						}
					}, 'json');
				}
			});
		}

		// 修改tag的排序
		// direction: 1: 向下（排序往后），-1: 向上（排序往前）
		function moveTag(tagId, categoryId, direction) {
			$.post('/admin/appIndex/storeTag/moveTag.do', {
				'tagId' : tagId,
				'categoryId' : categoryId,
				'direction' : direction
			}, function(json) {
				if (json.success) {
					// 重新拉取
					search();
					reloadCategory();
				} else {
					MyDialog.alert(json.message);
				}
			}, 'json');
		}
		
		function moveCategory(categoryId, direction) {
			$.post('/admin/appIndex/storeTag/moveCategory.do', {
				'categoryId' : categoryId,
				'direction' : direction
			}, function(json) {
				if (json.success) {
					// 重新拉取
					search();
					reloadCategory();
				} else {
					MyDialog.alert(json.message);
				}
			}, 'json');
		}

		$(function() {
			search();
			reloadCategory();
		});
		
		
		// 修改标签组名称
		var updateCategoryNameYes;
		function updateCategoryName(categoryId, oldCategoryName, oldEnName, appShow) {
			updateCategoryNameYes = false;
			var checked = (1 == appShow);
			BootstrapDialog.show({
				title: '修改标签类别名称',
				message: '你确认要修改标签类别名称吗？'
					+ '<form role="form" class="form-horizontal">'
					+ '<div class="form-group"><label class="col-xs-3 control-label">标签类别中文名：</label><div class="col-xs-9"><input type="text" name="categoryName" value="'+ oldCategoryName +'" class="form-control"></div></div>'
					+ '<div class="form-group"><label class="col-xs-3 control-label">标签类别英文名：</label><div class="col-xs-9"><input type="text" name="categoryEnName" value="'+ oldEnName +'" class="form-control"></div></div>'
					+ '<div class="form-group"><label class="col-xs-3 control-label">在APP上显示：</label><div class="col-xs-9"><label><input type="checkbox" name="appShow" value="1" '+(checked?'checked':'')+'>显示（不选表示：不显示）</label></div></div>'
					+ '</form>',
				buttons: [{
						label: '确定',
						action: function(dialog) { updateCategoryNameYes=true; dialog.close(); }
					}, {
						label: '取消',
						action: function(dialog) { updateCategoryNameYes=false; dialog.close(); }
					}
				],
				onhide: function(dialogRef){
					if(updateCategoryNameYes) {
						var categoryName = dialogRef.getModalBody().find('input[name=categoryName]').val();
						var categoryEnName = dialogRef.getModalBody().find('input[name=categoryEnName]').val();
						var appShow = dialogRef.getModalBody().find('input[name=appShow]').is(':checked');
						categoryName = $.trim(categoryName);
						// 校验
						if (0 >= categoryName.length) {
							MyDialog.alert('标签类别中文名不能为空。');
							return false;
						}
						if (categoryName.length > 32) {
							MyDialog.alert('标签类别中文长度不能大于32。');
							return false;
						}						
						
						if (0 >= categoryEnName.length) {
							MyDialog.alert('标签类别英文名不能为空。');
							return false;
						}
						if (categoryEnName.length > 32) {
							MyDialog.alert('标签类别英文长度不能大于32。');
							return false;
						}
						
						// 执行修改
						var appShowVal = (appShow ? 1 : 0);
						$.post("/admin/appIndex/storeTag/updateCategoryName.do", {'categoryId':categoryId, 'categoryName':categoryName, 'categoryEnName':categoryEnName, 'appShow':appShowVal}, function(json){
							// 检查是否unique key重复
							if (json.message.indexOf("MySQLIntegrityConstraintViolationException") > 0) {
								MyDialog.alert("名称不能重复");
							} else {
								MyDialog.alert(json.message);
							}
							// reload
							search();
							reloadCategory();
						}, "json");
					}
				}
			});
		}
		
		// 修改标签组名称
		var updateTagNameYes;
		function updateTagName(tagId, categoryId, oldTagName, oldEnName) {
			updateTagNameYes = false;
			BootstrapDialog.show({
				title: '修改标签名称',
				message: '你确认要修改标签名称吗？ 注意： 修改标签的名称，将会发生两种情况-> 1. 将标签名改成其它已存在的标签名, 该标签的ID 也会发生变化, 变成已存在的那个标签的ID值 ;'
									+ '2. 将该标签名改成还不存在的标签名, 其它分类中该相同标签名的标签 的名字也会一起变更'
									+ '3. 如果要修改的标签已在多个组存在, 如果是情况1, 那么将只改变 现在该组的标签,不会变更其它组 相同名的标签'
					+ '<form role="form" class="form-horizontal">'
					+ '<div class="form-group"><label class="col-xs-3 control-label">标签中文名：</label><div class="col-xs-9"><input type="text" name="tagName" value="'+ oldTagName +'" class="form-control"></div></div>'
					+ '<div class="form-group"><label class="col-xs-3 control-label">标签英文名：</label><div class="col-xs-9"><input type="text" name="tagEnName" value="'+ oldEnName +'" class="form-control"></div></div>'
					+ '</form>',
				buttons: [{
						label: '确定',
						action: function(dialog) { updateTagNameYes=true; dialog.close(); }
					}, {
						label: '取消',
						action: function(dialog) { updateTagNameYes=false; dialog.close(); }
					}
				],
				onhide: function(dialogRef){
					if(updateTagNameYes) {
						var tagName = dialogRef.getModalBody().find('input[name=tagName]').val();
						var tagEnName = dialogRef.getModalBody().find('input[name=tagEnName]').val();
						tagName = $.trim(tagName);
						// 校验
						if (0 >= tagName.length) {
							MyDialog.alert('标签中文名不能为空。');
							return false;
						}
						if (tagName.length > 32) {
							MyDialog.alert('标签中文名长度不能超过32。');
							return false;
						}
						
						if (0 >= tagEnName.length) {
							MyDialog.alert('标签英文名不能为空。');
							return false;
						}
						if (tagEnName.length > 32) {
							MyDialog.alert('标签英文名长度不能超过32。');
							return false;
						}
						
						// 执行修改
						$.post("/admin/appIndex/storeTag/updateTagName.do", {'tagId':tagId, 'categoryId':categoryId, 'tagName':tagName,'tagEnName':tagEnName}, function(json){
							// 检查是否unique key重复
							if (json.message.indexOf("MySQLIntegrityConstraintViolationException") > 0) {
								MyDialog.alert("名称不能重复");
							} else {
								MyDialog.alert(json.message);
							}
							// reload
							search();
						}, "json");
					}
				}
			});
		}
		
	</script>
</body>
</html>
