<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mazing.core.constant.RecommendVersionPubType"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>App首页--版本列表</title>
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
					App首页--版本列表 <small></small>
				</h1>
				<ol class="breadcrumb">
					<!-- <li><a href=""><i class="fa fa-dashboard"></i>新增</a></li> -->
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="row magin col-sm-12">
								    <p class="text-left">版本发布说明：</p>
								    <p class="text-left">（1）在版本发布之前，先确认『块』和『项』已经编辑好，并且在【<a href="/admin/appIndex/block/list.xhtm">块列表/编辑</a>】的页面里，『<span class="text-red">发布为测试版</span>』。</p>
								    <p class="text-left">（2）测试版只有在<a href="/admin/system/whitelist.xhtm"><strong>白名单</strong></a>的用户才能看到；</p>
								    <p class="text-left text-red">（3）测试版必须发布为发布版，用户才能看到；</p>
								    <p class="text-left">（4）版本回滚：出错的时候可以回滚为上一个正式版；</p>
								</div>
							</div>
							<div class="box-header">
								<div class="col-sm-6 form-inline">
									<select class="form-control" id="city">
										<c:forEach items="${cityList}" var="city">
											<option value="${city.code}">${city.name}</option>
										</c:forEach>
									</select>
									<select class="form-control" id="lang">
										<c:forEach items="${langList}" var="lang">
											<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>
									<!-- 
									<div class="input-group input-group-sm col-sm-1">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="search();">查询</button>
										</span>
									</div>
									 -->
									&nbsp;&nbsp;&nbsp;当前城市：<span id="nowCityName"></span>、语言：<span id="nowLangName"></span>
									<input id="nowCityCode" type="hidden" />
									<input id="nowLangCode" type="hidden" />
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
													<th>发布时间</th>
													<th>发布人</th>
													<th>创建时间</th>
													<th>操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.vid}}
												</td>
												<td>
													{{value.cityCode}}
												</td>
												<td>
													{{if value.status == <%=RecommendVersionPubType.BETA%>}}<p class="text-primary">测试版</p>{{/if}}
													{{if value.status == <%=RecommendVersionPubType.RELEASE%>}}<p class="text-success">正式版</p>{{/if}}
													{{if value.status == <%=RecommendVersionPubType.BETA_HISTORY%>}}<p class="text-primary">测试版历史</p>{{/if}}
													{{if value.status == <%=RecommendVersionPubType.RELEASE_HISTORY%>}}<p class="text-muted">正式版历史</p>{{/if}}
													{{if value.status == <%=RecommendVersionPubType.RELEASE_BACK%>}}<p class="text-warning">被回退的版本</p>{{/if}}
												</td>
												<td style="font-size: 12px;">{{formatDateTime value.publishTime}}</td>
												<td data-adminId="{{value.adminId}}">
													{{value.adminId}}
												</td>
												<td style="font-size: 12px;">{{formatDateTime value.createTime}}</td>
												<td>
													{{if value.status == <%=RecommendVersionPubType.RELEASE%>}}
													<a href="javascript:rollback({{value.vid}});" class="btn btn-warning">版本回滚</a>
													{{/if}}
													{{if value.status == <%=RecommendVersionPubType.BETA%>}}
													<a href="javascript:pubReleaseVersion({{value.vid}});" class="btn btn-success">发布为正式版</a>
													{{/if}}
												</td>
											</tr>
											{{/each}}
										</script>
										</table>

									</div>
								</div>
								<div class="row col-sm-12" id="listPaginator"></div>
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
		function search() {
			var cityCode = $("#city").val();
			if(!cityCode) { MyDialog.alert("请先选择一个城市！"); return; };
			var langCode = $("#lang").val();
			if(!langCode) { MyDialog.alert("请先选择语言！"); return; };
			
			viewCityLang();
			
			$('#listTable').artPaginate({
				url : "/admin/appIndex/version/list",// 获取数据的地址
				paginator : 'listPaginator',// 显示页码的位置
				tpl : 'listTableTpl',// 模版ID
				params : {cityCode: cityCode, langCode: langCode},
				pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
				, success: showUidToName
			});
		}

		function showUidToName() {
			var tds = $('#listTable td[data-adminId]');
			//得到uid集合
			var tmp = {};
			$.each(tds, function(i, v) {
				var adminId = $(v).attr('data-adminId');
				tmp[adminId] = adminId;
			});
			// set to str
			var uids = '';
			for(id in tmp) {
				if(uids) uids += ',';
				uids += id;
			}
			
			// reshow
			if(uids) {
				$.getJSON('/admin/base/findAdminByIds?uids='+uids, {}, function(json) {
					if(!(json.success)) {
						MyDialog.alert(json.message);
					} else {
						var data = json.object;
						$.each(data, function(i, user) {
							var adminId = user.uid;
							var html = adminId+' ['+user.realname+']';
							$('#listTable td[data-adminId="'+adminId+'"]').html(html);
						});
					}
				});
			}
		}

		function viewCityLang() {
			var c = $("#city").find("option:selected");
			$("#nowCityCode").val(c.val());
			$("#nowCityName").html(c.text());
			var l = $("#lang").find("option:selected");
			$("#nowLangCode").val(l.val());
			$("#nowLangName").html(l.text());
		}

		function pubReleaseVersion(vid) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '你确定将这个测试版升级为发布版？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/appIndex/version/pubReleaseVersion?vid='+vid, {}, function(json) {
							MyDialog.alert(json.message);
							search();
						});
					}
				}
			});
		}

		function rollback(vid) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '请确认是否还原该版本的数据至当前编辑块/项中么？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/appIndex/version/rollback?vid='+vid, {}, function(json) {
							MyDialog.alert(json.message);
							search();
						});
					}
				}
			});
		}

		$(function() {
			$('#city').on('change', search);
			$('#lang').on('change', search);
			search();
		});
	</script>
</body>
</html>
