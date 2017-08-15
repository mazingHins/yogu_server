<%@ page import="com.mazing.core.enums.config.FindPickType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>发现挑选配置管理</title>
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
					发现挑选配置 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
			
			<div class="row">
				<div class="col-xs-12">
				
					<ul id="storeTab" class="nav nav-tabs">
						<li class="active">
							<a href="tagTab" data-toggle="tab">
								标签列表
							</a>
						</li>
					</ul>
					<div id="tabContent" class="tab-content">
					
						<!-- tab start -->
						<div class="tab-pane fade in active" style="background-color: #fff;" id="tagTab">
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">Tag列表<span>（数据是获取餐厅所有的标签，尽量选择在用户使用app的低峰期保存！！！）</span></h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12" id="tagContainer"></div>
									</div>

								</div><!-- /.box-body -->
								<div class="box-footer with-border">
									<div class="box-tools pull-right">
										<button class="btn btn-primary" onclick="save()">提交</button>
									</div>
								</div><!-- /.box-header -->
							</div><!-- /.box -->
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

	<!-- tag的模版 -->
	<script id="tagTemplate" type="text/html">
	{{each object as value i}}
	<div class="row margin" style="border-bottom: 1px solid #ddd;">
		<div class="col-xs-3">{{value.categoryName}}</div>
		<div class="col-xs-9 checkbox">
			{{each value.tags as tag j}}
				<label><input type="checkbox" name="storeTag" value="{{tag.tagId}}" {{if tag.checked}}checked{{/if}} >{{tag.tagName}}</label> &nbsp; &nbsp; &nbsp; &nbsp;
			{{/each}}
		</div>
	</div>
	{{/each}}
</script>

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		
		// 加载标签 tag 数据
		function loadStoreTag() {
			var findId = $.getUrlParam("findId");
			if (findId == null || findId == '' || findId == '0') {
				MyDialog.alert('请选择一个配置进行操作');
				return;
			}
			$.getJSON("/admin/tag/listFindPickTag", {'findId':findId}, function(json) {
				if (!json.success) {
					MyDialog.alert(json.message);
				}
				else {
					var htmlTxt = template('tagTemplate', json);
					$('#tagContainer').html(htmlTxt);
				}
			});
		}
		
		// 保存数据
		
		function save() {
			var findId = $.getUrlParam("findId");
			if (findId == null || findId == '' || findId == '0') {
				MyDialog.alert('请选择一个配置进行操作');
				return;
			} else {
				var values = new Array();
				var storeTag = $('input[name=storeTag]:checked').each(
						function(i, item) {
							values.push(item.value);
						});
				if (values.length > 0) {
					var tagIds = values.join(',');
					console.log('tagIds: ' + tagIds)
					$.post('/admin/tag/updateFindPickTag.do', {
						'findId' : findId,
						'tagIds' : tagIds
					}, function(json) {
						MyDialog.alert(json.message);
					}, 'json');
				} else {
					MyDialog.alert('请至少选择一个标签');
				}
			}
		}

		$(function() {
			loadStoreTag();
		});
	</script>
</body>
</html>
