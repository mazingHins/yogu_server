<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>商品</title>
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
				商品详情
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/goods/list.xhtm"><i class="fa fa-dashboard"></i> 返回商品列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<form id="editGoods" class="form-horizontal" action="/admin/goods/saveGoods.do" onsubmit="return validate()" method="post">
						<div class="form-group">
							<label class="col-md-2 control-label">美食名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="goodsName"
									   name="goodsName" title="商品名称" placeholder="商品名称"
									   maxlength="12">
								<input name="goodsId" id="goodsId" type="hidden" value="0"/>
								<input name="goodsKey" id="goodsKey" type="hidden" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">零售价</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="retailPrice" name="retailPrice"
									   title="零售价" placeholder="零售价" maxlength="7">
							</div>
							<div class="col-md-3">
								分
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">批发价</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="tradePrice" name="tradePrice"
									   title="批发价" placeholder="批发价" maxlength="7">
							</div>
							<div class="col-md-3">
								分
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">商品主图</label>
							<div class="col-md-3">
								<img id="cardImg" width="80" src="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">商品介绍</label>
							<div class="col-md-3">
							</div>
							<div class="col-md-3" id="content">
								
							</div>
						</div>
						 <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
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

	$(function() {
		
		var goodsKey = $.getUrlParam("goodsKey");
		loadGoods(goodsKey)// 编辑
	});
	
	// 加载优惠券数据
	function loadGoods(goodsKey) {
		if(goodsKey == null || goodsKey == ''){
			return;
		}
		
		$.getJSON('/admin/goods/detail', {
			'goodsKey' : goodsKey
		}, function(json) {
			if (json.success) {
				fillForm(json.object);
			} else {
				BootstrapDialog.show({
					title: '错误',
					message: json.message,
					buttons: [{
						label: '确定',
						action: function (dialog) {
							dialog.close();
							window.location.href = '/admin/goods/list.xhtm';
						}
					}]
				});
			}
		});
	}
	
	function fillForm(goods){
		$("#goodsId").val(goods.goodsId);
		$("#goodsKey").val(goods.goodsKey);
		$("#goodsName").val(goods.goodsName);
		$("#retailPrice").val(goods.retailPrice);
		$("#tradePrice").val(goods.tradePrice);
		$("#cardImg").attr("src", goods.cardImg);
		var arr = goods.contentImgs;
		var content;
		for(var i=0;i<arr.length;i++){
 			content = content+"<img width='80' src='"+arr[i]+"' /><br/>"
		}
		$("#content").html(content);
	}
	
</script>
</body>
</html>
