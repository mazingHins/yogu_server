<%@ page import="com.mazing.core.enums.merchant.StoreStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>转让餐厅</title>
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
				转让餐厅
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 餐厅列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="box box-solid">
				<div class="box-header">
					<div class="col-sm-12">
						<p class="text-left">请选择要转让的内容</p>
					</div>
				</div><!-- /.box-header -->

					<div class="box-body">
						<div class="row">
							<form role="form" class="form-horizontal">
							<div class="form-group">
								<label class="col-xs-3 control-label">转让内容</label>
								<div class="col-xs-4">
									<label> &nbsp; 1. 转让基础内容：餐厅、所有美食、场地照片、健康证照片、员工</label>
									<input type="hidden" id="storeId" name="storeId" value="0"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">转让给经营者帐号</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" name="toPassport" id="toPassport" placeholder="手机号码" value="" maxlength="20"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">转让给经营者姓名</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" name="toRealName" id="toRealName" value="" maxlength="64"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">转让给经营者证件号</label>
								<div class="col-xs-4">
									<input type="text" class="form-control" name="toIdentity" id="toIdentity" placeholder="身份证或护照" value="" maxlength="20"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">餐厅新地址（可不填）</label>
								<div class="col-xs-3">
									<input type="text" class="form-control" name="newAddress" id="newAddress" value="" maxlength="100"/>
								</div>
								<div class="col-xs-2">
									<input type="text" class="form-control" name="newLng" id="newLng" value="" placeholder="经度(longitude)" maxlength="25"/>
								</div>
								<div class="col-xs-2">
									<input type="text" class="form-control" name="newLat" id="newLat" value="" placeholder="纬度(latitude)" maxlength="25"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-3">&nbsp;</div>
								<div class="col-xs-9">
									参考：<a href="http://lbs.amap.com/console/show/picker" target="_blank">高德坐标拾取器</a>
								</div>
							</div>
						</form>
						</div><!-- /.row -->
						<!-- 去掉该选项 <div class="row">
                            <div class="col-xs-2"></div>
                            <div class="col-xs-10">
                                <label><input type="checkbox" id="transferContent2" name="transferContent2" value="2"/>2. 转让个人认证信息：个人证件照片、手持证件照片（<span style="color: #ff0000;font-weight: bold;">请慎重选择</span>）</label>
                            </div>
                        </div> -->

						<!-- /.row -->
						<div class="row">
							<div class="col-sm-12">
								<table id="userTable" class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>属性</th>
										<th>数值</th>
										<th>LOGO</th>
									</tr>
									</thead>
									<tbody id="storeInfo">

									</tbody>
								</table>

							</div>
						</div>

						<div class="row">
							<div class="col-xs-12">
								<button type="button" onclick="transfer()">转让</button>
							</div>
						</div>

					</div><!-- /.box-body -->

			</div><!-- /.box -->

		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script id="storeTemplate" type="text/html">
	<tr>
		<td>餐厅名称</td>
		<td>{{storeName}}</td>
		<td rowspan="4">
			{{if logoPath != null && logoPath != ''}}
			<img width="200px" src="{{renderImageUrl logoPath}}"/> <br/>
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>地址</td>
		<td>{{address}}</td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td>{{formatDateTime createTime}}</td>
	</tr>
	<tr>
		<td>拥有者昵称</td>
		<td>{{nickname}}</td>
	</tr>
	<tr>
		<td>拥有者帐号</td>
		<td>
			{{passport}}
		</td>
	</tr>
	<tr>
		<td>状态</td>
		<td>
			{{if status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
			正常营业
			{{/if}}
			{{if status == <%=StoreStatus.IN_REST.getValue()%>}}
			休业
			{{/if}}
			{{if status == <%=StoreStatus.CLOSED.getValue()%>}}
			<span style="color:red;">结业</span>
			{{/if}}
			{{if status == <%=StoreStatus.FROST.getValue()%>}}
			<span style="color:red;">封号</span>
			{{/if}}
		</td>
	</tr>

</script>
<script type="text/javascript">
	// 图片域名
	var imageDomain = '';
	// 查询门店的信息
	function query() {
		var storeId = $.getUrlParam("storeId");

		if (storeId == null || storeId == '' || storeId == '0') {
			MyDialog.alert('请在门店列表界面选择一家门店进行操作');
			return false;
		}
		else {
			$('#storeId').val(storeId);
			$.getJSON('/admin/store/transferDetail', {'storeId': storeId}, function (json) {
				if (json.success) {
					render(json.object);
				}
				else {
					MyDialog.alert(json.message);
				}
			});
		}
		return true;
	}

	// 用模板显示用户的信息
	function render(value) {
		// 图片域名
		imageDomain = value.imgDomain;
		if (imageDomain.substring(imageDomain.length - 1) != '/') {
			imageDomain = imageDomain + '/';
		}

		var htmlTxt = template('storeTemplate', value);
		$('#storeInfo').html(htmlTxt);
	}

	// 检查地址是否有效，全填或者全不填
	function checkAddress() {
		var newAddress = $.trim($('#newAddress').val());
		var newLng = $.trim($('#newLng').val());
		var newLat = $.trim($('#newLat').val());
		if (newAddress && newLat && newLng) {
			if (newLat > 90) {
				MyDialog.alert('纬度不能超过90度');
				return false;
			}
			if (newLng > 180) {
				MyDialog.alert('经度不能超过180度');
				return false;
			}
			return true;
		}
		if (newAddress || newLat || newLng) {
			MyDialog.alert('请同时输入地址和经纬度');
			return false;
		}
		return true;
	}

	// 执行转让
	function transfer() {
		var storeId = $.getUrlParam("storeId");
		if (storeId == null || storeId == '' || storeId == '0') {
			MyDialog.alert('请在门店列表界面选择一家门店进行操作');
			return false;
		}

		var toPassport = $.trim($('#toPassport').val());
		if (toPassport == '') {
			MyDialog.alert('请输入你要转给哪个帐号');
			return;
		}


		var toRealName = $.trim($('#toRealName').val());
		if (toRealName == '') {
			MyDialog.alert('请输入你要转给者的姓名');
			return;
		}

		var toIdentity = $.trim($('#toIdentity').val());
		if (toIdentity == '') {
			MyDialog.alert('请输入你要转给者的证件号');
			return;
		}

		checkAddress();

		BootstrapDialog.show({
			title: '转让门店',
			message: '你确认要转让这家门店给帐号 [' + toPassport + '] ？',
			buttons: [{
				label: '转让',
				action: function (dialog) {
					dialog.close();
					$.post("/admin/store/storeTransfer.do", {
						'storeId': storeId,
						'countryCode': '86',
						'passport': toPassport,
						'realName': toRealName,
						'identity': toIdentity,
						'transferAudit': true,
						'newAddress' : $.trim($('#newAddress').val()),
						'newLng' : $.trim($('#newLng').val()),
						'newLat' :  $.trim($('#newLat').val())
					}, function (json) {
						MyDialog.alert(json.message);
						query();
					}, 'json');
				}
			}, {
				label: '取消',
				action: function (dialog) {
					dialog.close();
				}
			}]
		});
	}


	$(function(){
		// 模版函数：输出图片的完整地址
		template.helper('renderImageUrl', function (uri) {
			return imageDomain + uri;
		});

		query();
	});
</script>
</body>
</html>
