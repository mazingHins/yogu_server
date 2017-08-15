<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>菜品库存管理</title>
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
				菜品库存管理
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/account/listAccount.xhtm"><i class="fa fa-dashboard"></i> 菜品库存管理</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" 
						  action="/admin/test/dish/update.do">
						<div class="form-group">
							<label class="col-md-2 control-label">菜品Id</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishId"
									   name="dishId" title="菜品Id" placeholder="菜品Id"
									   maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div hidden="true" class="form-group">
							<label class="col-md-2 control-label">门店Id</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="storeId"
									   name="storeId" title="门店Id" placeholder="门店Id"
									   maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">菜品Key</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishKey"
									   name="dishKey" title="菜品Key" placeholder="菜品Key"
									   maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">主图</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="topicImg"
									   name="topicImg" title="主图" placeholder="主图"
									   maxlength="30" data-minlength="2">
							</div>
						</div>
						<div hidden="true" class="form-group">
							<label class="col-md-2 control-label">收藏图</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="favImg"
									   name="favImg" title="收藏图" placeholder="收藏图"
									   maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">菜品名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dishName"
									   name="dishName" title="菜品名称" placeholder="菜品名称"
									   maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">售价(分)</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="price"
									   name="price" title="售价" placeholder="售价"
									   maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">品类</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="categoryId"
									   name="categoryId" title="品类" placeholder="品类"
									   maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">每日供应</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="dailyNum" name="dailyNum"
									   title="每日供应" placeholder="每日供应" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">规格</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="spec" name="spec"
									   title="规格" placeholder="规格" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">保质期</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="expiryTime" name="expiryTime"
									   title="保质期" placeholder="保质期" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">简短卖点</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="content" name="content"
									   title="简短卖点" placeholder="简短卖点" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">特别推介</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="promoteTag" name="promoteTag"
									   title="特别推介" placeholder="特别推介" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">原价(分)</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="originalPrice" name="originalPrice"
									   title="原价" placeholder="原价" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">卖点</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="specialContent" name="specialContent"
									   title="卖点" placeholder="卖点" maxlength="30" data-minlength="1" required="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">图片Id路径列表</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="idPathEntry" name="idPathEntry"
									   title="图片Id路径列表" placeholder="图片Id路径列表" maxlength="30" data-minlength="1">
							</div>
						</div>
						<div hidden="true" class="form-group">
							<label class="col-md-2 control-label">删除图片Id列表</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="delPicIds" name="delPicIds"
									   title="删除图片Id列表" placeholder="删除图片Id列表,多个用英文逗号分开" maxlength="30" data-minlength="1">
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
	function loadForm(){
		var dishKey = $.getUrlParam("dishKey");

		$.getJSON("/admin/test/dish/detail", {'dishKey': dishKey}, function(json) {
			if (json.success) {
				var dish = json.object;
				$('#dishId').val(dish.dishId);
				$('#dishKey').val(dish.dishKey);
				$('#topicImg').val(dish.topicImg);
				$('#dishName').val(dish.dishName);
				$('#price').val(dish.price);
				$('#spec').val(dish.spec);
				$('#categoryId').select2('val', '' + dish.categoryId);
				$('#dailyNum').val(dish.dailyNum);
				$('#expiryTime').val(dish.expiryTime);
				$('#content').val(dish.content);
				$('#promoteTag').val(dish.promoteTag);
				$('#originalPrice').val(dish.originalPrice);
				$('#specialContent').val(dish.specialContent);
				$('#idPathEntry').val(dish.idPathEntry);
				$('#delPicIds').val(dish.delPicIds);
				$('#storeId').val(dish.storeId);
				$('#favImg').val(dish.favImg);
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}
	$(function () {
		$.getJSON("/admin/test/dish/category/allCategories", {}, function(json) {
			if (json.success) {
				var categories = json.object;
				var dropdown = $('#categoryId');
				dropdown.empty();
				var data = [];
				if (categories.length > 0) {
					for (var i = 0; i < categories.length; i++) {
						var item = categories[i];
						data.push({
							id: item.categoryId,
							text: item.categoryName
						});
					}
				}
				
				dropdown.select2({
					'allowClear' : true,
					'data': data,
					'placeholder': '请选择一个类别'
				});
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	});
	
	$(function(){
		loadForm();
		
		$('#editForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					MyDialog.alert(json.message);

				} catch (e) {
				}
			}
		});
	});
	
	
</script>
</body>
</html>