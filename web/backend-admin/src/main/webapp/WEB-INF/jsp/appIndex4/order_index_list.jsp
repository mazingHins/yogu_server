<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>订餐首页版本列表</title>
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
					订餐首页版本列表<small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">

								<div class="col-sm-6 form-inline">
									<select class="form-control" name="orderStatus" id="city">
										<c:forEach items="${cityList}" var="city">
											<option value="${city.code}">${city.name}</option>
										</c:forEach>
									</select> <select class="form-control" id="lang">
										<c:forEach items="${langList}" var="lang">
											<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>

									<div class="input-group input-group-sm col-sm-1">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat"
												onclick="search();">查询</button>
										</span>
									</div>
									&nbsp;&nbsp;&nbsp; 当前城市：<span id="nowCityName"></span>
									&nbsp;&nbsp; 语言：<span id="nowLangName" style="color: red;"></span>
									<input id="nowCityCode" type="hidden" /> <input
										id="nowLangCode" type="hidden" />
								</div>

								<div class="col-sm-6 form-inline blockquote-reverse">
									<a class="btn btn-success" href="javascript: add();">添加新版首页</a>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<table id="listTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>ID</th>
													<th>城市</th>
													<th>状态</th>
													<th>发布人</th>
													<th>发布时间</th>
													<th>修改时间</th>
													<th>创建时间</th>
													<th>操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{value.vid}}</td>
												<td>{{value.cityCode}}</td>
												<td>
													{{if value.status == 1}}<p class="text-primary">测试版</p>{{/if}}
													{{if value.status == 2}}<p class="text-success">正式版</p>{{/if}}
												</td>
												<td data-adminId="{{value.adminId}}">
													{{value.adminId}}
												</td>
												<td style="font-size: 12px;">{{if value.publishTime != null}}{{formatDateTime value.publishTime*1000}}{{/if}}</td>
												<td style="font-size: 12px;">{{if value.updateTime != null}}{{formatDateTime value.updateTime*1000}}{{/if}}</td>
												<td style="font-size: 12px;">{{formatDateTime value.createTime*1000}}</td>
												<td>
													<a href="javascript:show({{value.vid}});" class="btn btn-primary">查看</a>
													<a href="javascript:edit({{value.vid}});" class="btn btn-primary">编辑</a>
													{{if value.status == 0}}
													<a href="javascript:publishTest({{value.vid}});" class="btn btn-warning">发布为测试版</a>
													<a href="javascript:del({{value.vid}});" class="btn btn-danger">删除</a>
													{{/if}}
													{{if value.status == 1}}
													<a href="javascript:publishFormal({{value.vid}});" class="btn btn-success">发布为正式版</a>
													{{/if}}
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
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
		function publishTest(vid) {
			var cityCode = $("#city").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#lang").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}
			BootstrapDialog.confirm({
				title : '操作确认',
				btnOKLabel : '确认',
				btnCancelLabel : '取消',
				message : '确认把  ID=' + vid + ' 发布为测试版',
				callback : function(result) {
					if (result) {
						requestNode('/order/admin/publishindex', 'post', {
							status : 1,
							vid : vid,
							cityCode : cityCode,
							langCode : langCode
						}, function(data) {
							search();
						})
					}
				}
			});
		}

		function publishFormal(vid) {
			var cityCode = $("#city").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#lang").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}
			BootstrapDialog.confirm({
				title : '操作确认',
				btnOKLabel : '确认',
				btnCancelLabel : '取消',
				message : '确认把  ID=' + vid + ' 发布为正式版',
				callback : function(result) {
					if (result) {
						requestNode('/order/admin/publishindex', 'post', {
							status : 2,
							vid : vid,
							cityCode : cityCode,
							langCode : langCode
						}, function(data) {
							search();
						})
					}
				}
			});
		}

		function add() {
			var cityCode = $("#nowCityCode").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#nowLangCode").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}
			window.location.href = '/admin/order/index/add.xhtm?cityCode='+cityCode+'&langCode='+langCode;
		}

		function edit(vid) {
			var cityCode = $("#nowCityCode").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#nowLangCode").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}
			window.location.href = '/admin/order/index/edit.xhtm?vid='+vid+'&cityCode='+cityCode+'&langCode='+langCode;
		}

		function show(vid) {
			var cityCode = $("#nowCityCode").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#nowLangCode").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}
			window.location.href = '/admin/order/index/show.xhtm?vid='+vid+'&cityCode='+cityCode+'&langCode='+langCode;
		}

		function del(vid) {
			BootstrapDialog.confirm({
				title : '操作确认',
				btnOKLabel : '确认',
				btnCancelLabel : '取消',
				message : '确定删除版本  ID=' + vid + ' ?',
				callback : function(result) {
					if (result) {
						requestNode('/order/admin/deleteindex', 'post', {
							vid : vid
						}, function(data) {
							search();
						})
					}
				}
			});
		}

		function search() {
			var cityCode = $("#city").val();
			if (!cityCode) {
				MyDialog.alert("请先选择一个城市！");
				return;
			}
			var langCode = $("#lang").val();
			if (!langCode) {
				MyDialog.alert("请先选择语言！");
				return;
			}

			//设置选项可视值
			viewCityLang();

			// load 数据
			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "https://napi.mazing.com/order/admin/getindexlist",
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {
					cityCode : cityCode,
					langCode : langCode
				}
			});
		}

		function pathParamOrSelect() {
			var cityCode = $.getUrlParam("cityCode");
			// 将URL中指定的城市，设置到 select组件中
			if (cityCode) {
				$("#city").find("option[value='" + cityCode + "']").attr(
						"selected", "selected");
			}
			// 将URL中指定的语言，设置到 select组件中
			var langCode = $.getUrlParam("langCode");
			if (langCode) {
				$("#lang").find("option[value='" + langCode + "']").attr(
						"selected", "selected");
			}
			search();
		}

		function viewCityLang() {
			var c = $("#city").find("option:selected");
			$("#nowCityCode").val(c.val());
			$("#nowCityName").html(c.text());
			var l = $("#lang").find("option:selected");
			$("#nowLangCode").val(l.val());
			$("#nowLangName").html(l.text());
		}

		function changeCity() {
			var s = $("#city").find("option:selected");
			$("#nowCityCode").val(s.val());
			$("#nowCityName").html(s.text());
			$("#modalCityName").html(s.text());
			search();
		}

		$(function() {
			$('#city').on('change', search);
			$('#lang').on('change', search);

			pathParamOrSelect();
		});
	</script>
</body>
</html>