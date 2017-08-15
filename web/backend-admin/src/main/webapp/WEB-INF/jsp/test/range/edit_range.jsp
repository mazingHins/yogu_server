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
				${it.title}配送范围
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/test/range/list.xhtm?storeId=${param.storeId}"><i class="fa fa-dashboard"></i>返回列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" action="/admin/test/range/edit.do">
						<div hidden="true" class="form-group">
							<label class="col-md-2 control-label">storeId</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="storeId" name="storeId" title="门店Id" placeholder="门店Id" maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div hidden="true" class="form-group">
							<label class="col-md-2 control-label">rangeId</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="rangeId" name="rangeId" title="Id" placeholder="Id" maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">配送服务名</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="name" name="name" title="配送服务名" placeholder="配送服务名" maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">设定类型</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="cutType" name="cutType" title="设定类型" placeholder="设定类型" maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">设定值</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="cutValue" name="cutValue" title="设定值" placeholder="设定值" maxlength="30" data-minlength="2" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">起送金额（分）</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="minimumMoney" name="minimumMoney" title="起送金额（分）" placeholder="起送金额（分）" maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">配送费用（分）</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="money" name="money" title="配送费用（分）" placeholder="配送费用（分）" maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">免运费金额（分）</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="fullFreeFreight" name="fullFreeFreight" title="免运费金额（分）" placeholder="免运费金额（分）" maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">配送时间（分钟）</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="minute" name="minute" title="配送时间（分钟）" placeholder="配送时间（分钟）" maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">配送服务备注</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="remark" name="remark" title="配送服务备注" placeholder="配送服务备注" maxlength="30" data-minlength="2">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary" type="submit" id="submit">${it.title}</button>
								<a class="btn btn-primary" href="/admin/test/range/list.xhtm?storeId=${param.storeId}">返回</a>
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
		$('#storeId').val('${it.range.storeId}');
		$('#rangeId').val('${it.range.rangeId}');
		$('#name').val('${it.range.name}');
		$('#remark').val('${it.range.remark}');
		$('#cutType').val('${it.range.cutType}');
		$('#cutValue').val('${it.range.cutValue}');
		$('#minimumMoney').val('${it.range.minimumMoney}');
		$('#money').val('${it.range.money}');
		$('#fullFreeFreight').val('${it.range.fullFreeFreight}');
		$('#minute').val('${it.range.minute}');
	}

	function loadFormData(range){
		$('#storeId').val(range.storeId);
		$('#rangeId').val(range.rangeId);
		$('#name').val(range.name);
		$('#remark').val(range.remark);
		$('#cutType').val(range.cutType);
		$('#cutValue').val(range.cutValue);
		$('#minimumMoney').val(range.minimumMoney);
		$('#money').val(range.money);
		$('#fullFreeFreight').val(range.fullFreeFreight);
		$('#minute').val(range.minute);
	}

	$(function(){
		loadForm();
		
		$('#editForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					if(1 != json.code) {
						MyDialog.alert(json.message);
					} else {
						MyDialog.alert('修改成功!');
						loadFormData(json.object);
					}
				} catch (e) {
				}
			}
		});
	});
	
	
</script>
</body>
</html>