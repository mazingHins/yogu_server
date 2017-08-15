<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>资讯首页--块列表</title>
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
					资讯首页--块列表 <small></small>
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
									</select>	
									<select class="form-control" id="lang">
										<c:forEach items="${langList}" var="lang">
											<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>
									
									<div class="input-group input-group-sm col-sm-1">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="search();">查询</button>
										</span>
									</div>
									&nbsp;&nbsp;&nbsp;当前城市：<span id="nowCityName"></span>&nbsp;&nbsp; 语言：<span id="nowLangName" style="color:red;"></span>
									<input id="nowCityCode" type="hidden" />
									<input id="nowLangCode" type="hidden" />
								</div>
								<div class="col-sm-6 form-inline blockquote-reverse">
									<a class="btn btn-success" href="javascript: pubOfficalVersion();">发布正式版</a>
									&nbsp;&nbsp;&nbsp; <a href="javascript: toAddPage();"><i class="fa fa-dashboard"></i>新增一个块</a>
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
													<!-- <th>城市</th> -->
													<th>专题图片</th>
													<th>标题</th>
													<th>作者</th>
													<th>阅读数</th>
													<th>发布时间</th>
													<!-- <th>最后修改时间</th> -->
													<th>创建时间</th>
													<th>操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
												{{each object as value i}}
												<tr>
													<td>{{value.bid}}</td>
													<td><img src="${imgDomain}{{value.img}}" height="60" /></td>
													<td>{{value.title}}</td>
													<td>{{value.author}}</td>
													<td>{{value.readCount}}</td>
													<td style="font-size: 12px;">{{formatDateTime value.pubTime}}</td>
													<td style="font-size: 12px;">{{formatDateTime value.createTime}}</td>
													<td>
														<a class="btn btn-default btn-sm" href="/admin/news/block/edit.xhtm?cityCode={{value.cityCode}}&langCode={{value.langCode}}&bid={{value.bid}}">编辑</a>
														{{if value.status == <%=BooleanConstants.TRUE%>}}
															<a class="btn btn-warning btn-sm" href="javascript:enable({{value.bid}}, 0);">停用</a>
														{{/if}}
														{{if value.status == <%=BooleanConstants.FALSE%>}}
															<a class="btn btn-success btn-sm" href="javascript:enable({{value.bid}}, 1);">启用</a>
														{{/if}}
														<a class="btn btn-danger btn-sm" href="javascript:del({{value.bid}}, '{{value.title}}');">删除</a>
													</td>
												</tr>
												{{/each}}
										</script>
										</table>
									</div>
								</div>
								<div class="row col-sm-12" id="listPaginator2">
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
		function search() {
			var cityCode = $("#city").val();
			if(!cityCode) { MyDialog.alert("请先选择一个城市！"); return; };
			var langCode = $("#lang").val();
			if(!langCode) { MyDialog.alert("请先选择语言！"); return; };
			
			
			//设置选项可视值
			viewCityLang();
			
			
			// load 数据
			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "/admin/news/block/list",
				// 显示页码的位置
				paginator: 'listPaginator2',
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
				$("#lang").find("option[value='" + langCode + "']").attr("selected", "selected");
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
		

		function toAddPage() {
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
			
			window.location.href = "/admin/news/block/edit.xhtm?cityCode=" + cityCode+'&langCode='+langCode;
		}

		// type；1：上移、2：下移、3：置顶、4：置底
		/* function mv(bid, type) {
			$.getJSON('/admin/appIndex/block/mv', {
				'type' : type,
				'bid' : bid
			}, function(json) {
				if (json.success) {
					pathParamOrSelect();
				} else {
					MyDialog.alert(json.message);
				}
			});
		} */

		// type；1：启用、其他：禁用
		function enable(bid, type) {
			var url = '/admin/news/block/';
			url += (1 == type ? 'enable' : 'disable');
			$.getJSON(url, {
				'bid' : bid
			}, function(json) {
				if (json.success) {
					pathParamOrSelect();
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		function pubOfficalVersion() {
			var cityCode = $("#nowCityCode").val();
			var cityName = $("#nowCityName").text();
			var langCode = $("#nowLangCode").val();
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '请确认是否将新增的资讯发布至生产环境',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/news/block/publish', {
							'cityCode' : cityCode,
							'langCode' : langCode
						}, function(json) {
							MyDialog.alert(json.message);
						});
					}
				}
			});
		}

		function changeCity() {
			var s = $("#city").find("option:selected");
			$("#nowCityCode").val(s.val());
			$("#nowCityName").html(s.text());
			$("#modalCityName").html(s.text());
			search();
		}

		function del(bid, blockTitle) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '你确认删除【' + blockTitle + '】块么？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/news/block/del?bid='+bid, {}, function(json) {
							if (json.success) {
								search();
								deleteNewsStoreinfoBlock(bid);
							} else {
								MyDialog.alert(json.message);
							}
						});
					}
				}
			});
		}
		
		function deleteNewsStoreinfoBlock(bid){
			requestNode('/store/admin/deletenewsstoreinfoblock', 'post', {bid:bid}, function(data){
			});
		}

		// blockType List >> [{name: '', value: ''}, {..} ..]
		var blockTypeArr = [
   			<%boolean one = true;for(RecommendBlockType type : RecommendBlockType.values()) {%>
   			<%if(!one) {%><%=", "%><%}%>{name: '<%=type.getName()%>', value: '<%=type.getValue()%>'}
   			<%one = false;}%>
   		];
		
		
		$(function() {
			// template.helper('minuteToHM', minuteToHM);// ‘分钟数’ 转换成 【时:分】
			// 块类型转换成中文显示
			/* template.helper('showTypeName', function(type) {
				var name = type;
				$.each(blockTypeArr, function(i, d) {
					if(type == d.value) {
						name = d.name;
					}
				});
				return name;
			}); */
			$('#city').on('change', search);
			$('#lang').on('change', search);
			
			pathParamOrSelect();
		});
		
		// ‘分钟数’ 转换成 【时:分】
		// 0 -> 00:00 __  60 -> 01:00 __ 90 -> 01:30 __ 1440 -> 24:00
		function minuteToHM(minute) {
			var h = Math.floor(minute / 60);
			var m = Math.floor(minute % 60);
			//左边补0
			if(10 > h) h = ('0'+h);
			if(10 > m) m = ('0'+m);
			return h+':'+m;
		}
		
	</script>
</body>
</html>