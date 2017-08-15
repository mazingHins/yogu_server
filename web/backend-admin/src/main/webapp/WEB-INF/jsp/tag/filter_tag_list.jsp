<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<%@ include file="/include/meta.html"%>
	<title>筛选标签管理</title>
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
				筛选标签管理
				<small></small>
			</h1>
			<!-- <ol class="breadcrumb">
				<li><a href="" data-toggle="modal" data-target="#addModal"><i class="fa fa-dashboard"></i> 新增筛选标签</a></li>
			</ol> -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					
					<!-- 查询条件 -->
					<div class="box-header">
						<div class="col-sm-6 form-inline">
							<select class="form-control" name="cityCode" id="city">
							</select>	
							<div class="input-group input-group-sm col-sm-1">
								<span class="input-group-btn">
									<button type="button" class="btn btn-info btn-flat" onclick="query();">查询</button>
								</span>
							</div>
							&nbsp;&nbsp;&nbsp;当前城市：<span id="nowCityName"></span>
						</div>
						<div class="col-sm-6 form-inline blockquote-reverse">
							<a href="" data-toggle="modal" data-target="#addModal"><i class="fa fa-dashboard"></i>新增筛选标签</a>
						</div>
					</div>
							
					<div class="box box-solid">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>标签ID</th>
											<th>标签名称</th>
											<th>分类ID</th>
											<th>分类名称</th>
											<th>创建时间</th>
											<th>序号</th>
											<th>操作</th>
										</tr>
										</thead>
										<tbody id="listTableBody" style="font-size: 14px;">

										</tbody>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.tagId}}</td>
												<td>{{value.tagName}}</td>
												<td>{{value.categoryId}}</td>
												<td>{{value.categoryName}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>{{value.sequence}}</td>
												<td>
													{{if i != 0}}
														<a href="javascript:void(0)" class="btn btn-default" name="moveUp" lang="{{value.tagId}}" categoryId="{{value.categoryId}}">上移</a>
													{{/if}}
													{{if i+1 < object.length}}
														<a href="javascript:void(0)" class="btn btn-default" id="moveDown" name="moveDown" lang="{{value.tagId}}" categoryId="{{value.categoryId}}">下移</a>
													{{/if}}
													<a href="javascript:void(0)" class="btn btn-default" name="delBtn" lang="{{value.tagId}}"  categoryId="{{value.categoryId}}" >删除</a>
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
			<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">广州市-新增筛选标签</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="mobile" class="control-label">标签分类:</label>
								<select class="form-control" name="categoryId" id="categoryId"></select>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">标签:</label>
								<select class="form-control" name="tagId" id="tagId"></select>
							</div>
							<div class="form-group">
								<input type="hidden" id="cityCode" name="cityCode" value="020">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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

	//初始化
	$(function() {
		//初始化类别
		initCategory();
		
		//初始化城市
		initCityCode();
		
		//新增click事件
		$('#saveBtn').click(save);
		
		//类别改变事件
		$('#categoryId').change(categoryChange);
		
		//city事件
		$('#city').change(cityChange);
		
		//初始化查询
		query();
	});
	
	// 查询所有数据
	function query() {
		// 注意：这里可以使用一个参数 groupCode
		$.getJSON("/admin/system/filterTag/listAll", {'cityCode': $('#city').val()}, function(json) {
			if (json.success) {
				var htmlText = template('listTableTpl', json);
				$('#listTableBody').html(htmlText);
				
				//上移标签
				$('a[name="moveUp"]').click(function(){
					move($(this).attr('lang'), $(this).attr('categoryId'), $('#city').val(), -1);
				});
				
				//下移标签
				$('a[name="moveDown"]').click(function(){
					move($(this).attr('lang'), $(this).attr('categoryId'), $('#city').val(), 1);
				});
				
				//删除标签
				$('a[name="delBtn"]').click(function(){
					deleteFilterTag($(this).attr('lang'), $(this).attr('categoryId'), $('#city').val());
				});
			
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}

	//初始化类别
	function initCategory(){
		//只初始化一次
		if($('#categoryId').find('option').length == 0){
			$.get('/admin/system/filterTag/listCategroy', function(json){
				if(json.success){
					$('#categoryId').append('<option value="0">请选择分类</option>');
					$.each(json.object, function(index ,element){
						$('#categoryId').append('<option value="'+ element.categoryId +'">'+ element.categoryName+'</option>');
					});
				}
			});
		}
	}
	
	var categoryChange = function(){
		if($(this).val() != 0){
			$.get('/admin/system/filterTag/listTagsByCategoryId?categoryId=' + $(this).val(), function(json){
				if(json.success){
					var html = '';
					html += '<option value="0">请选择标签</option>';
					$.each(json.object, function(index ,element){
						html += '<option value="'+ element.tagId +'">'+ element.tagName+'</option>';
					});
					$('#tagId').html(html);
				}
			});
		}
	} 
	
	//初始化城市编码
	function initCityCode(){
		//只初始化一次
		if($('#city').find('option').length == 0){
			$.get('/admin/system/filterTag/listCityCode', function(json){
				if(json.success){
					$.each(json.object, function(index ,element){
						$('#city').append('<option value="'+ element.code +'">'+ element.name+'</option>');
						if(index == 0)
							$('#nowCityName').text(element.name);
					});
				}
			});
		}
	}
	
	//cityChange事件
	function cityChange(){
		var cityCode = $(this).val();
		$('#cityCode').val(cityCode);
		var cityName = $(this).find('option:selected').text();
		$('#myModalLabel').text(cityName + "-新增筛选标签");
		$('#nowCityName').text(cityName);
	}
	
	// 保存
	function save() {
		var categoryId = $.trim($('#categoryId').val());
		if(categoryId == 0){
			MyDialog.alert('标签分类不能为空，请选择');
			return;
		}
		
		var tagId = $.trim($('#tagId').val());
		if(tagId == 0){
			MyDialog.alert('标签不能为空，请选择');
			return;
		}
		
		var cityCode = $.trim($('#cityCode').val());
		if(!cityCode){
			MyDialog.alert('城市不能为空，请选择');
			return;
		}
		
		$.post('/admin/system/filterTag/add.do', {
			'tagId': tagId,
			'categoryId': categoryId,
			'cityCode': cityCode
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				$('#addModal').modal('hide');
				// 重新读一次信息
				query();
			}
		});
	}
	
	// 删除白名单
	var deleteFilterTag = function (tagId, categoryId, cityCode) {
		BootstrapDialog.confirm('确认删除这个标签吗？', function(result) {
			if (result) {
				$.post('/admin/system/filterTag/deleteTag.do', {
					'tagId': tagId,
					'categoryId': categoryId,
					'cityCode': cityCode
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
	
	//移动标签
	function move(tagId, categoryId, cityCode, direction){
		$.post('/admin/system/filterTag/moveFilterTag.do', {
			'tagId': tagId,
			'categoryId': categoryId,
			'cityCode': cityCode,
			'direction' : direction
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				// 重新读一次信息
				query();
			}
		}, 'json');
	}
	
</script>
</body>
</html>
