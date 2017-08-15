<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>App首页--块列表</title>
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
					App首页--块列表 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
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
								<div class="col-sm-6 form-inline blockquote-reverse">
									<a class="btn btn-success" href="javascript: pubBetaVersion();">发布为测试版</a>
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
													<!-- <th>ID</th> -->
													<!-- <th>城市</th> -->
													<th>块类型</th>
													<th>块标题</th>
													<th>生效时间</th>
													<!-- <th>最后修改时间</th> -->
													<!-- <th>创建时间</th> -->
													<th>排序</th>
													<th>操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr class="{{if value.status == <%=BooleanConstants.FALSE%>}}bg-warning{{/if}}">
												<!-- <td>{{value.bid}}</td> -->
												<!-- <td>{{value.cityCode}}</td> -->
												<td>
													{{showTypeName value.type}}
													{{if value.autoPush == <%=BooleanConstants.TRUE%>}}&nbsp;/&nbsp;<span class="text-success">自推</span>{{/if}}
													{{if value.autoSorting == <%=BooleanConstants.TRUE%>}}&nbsp;/&nbsp;<span class="text-danger">自排</span>{{/if}}
												</td>
												<td>{{value.title}}</td>
												<td>
													{{minuteToHM value.activeStartMinute}}&nbsp;~&nbsp;{{minuteToHM value.activeEndMinute}}
													{{if value.activeLoginStatus == <%=BooleanConstants.TRUE%>}}&nbsp;/&nbsp;<span class="text-primary">登录</span>{{/if}}
													{{if value.activeLoginStatus == <%=BooleanConstants.FALSE%>}}&nbsp;/&nbsp;<span class="text-muted">未登录</span>{{/if}}
												</td>
												<!-- <td style="font-size: 12px;">{{formatDateTime value.modifyTime}}</td> -->
												<!-- <td style="font-size: 12px;">{{formatDateTime value.createTime}}</td> -->
												<td>
													{{if i != 0}}
													<!-- <a class="btn btn-default btn-sm" href="javascript:mv({{value.bid}}, 3);">置顶</a> -->
													<a class="btn btn-default btn-sm" href="javascript:mv({{value.bid}}, 1);">上移</a>
													{{/if}}
													{{if i+1 < object.length}}
													<a class="btn btn-default btn-sm" href="javascript:mv({{value.bid}}, 2);">下移</a>
													<!-- <a class="btn btn-default btn-sm" href="javascript:mv({{value.bid}}, 4);">置底</a> -->
													{{/if}}
												</td>
												<td>
													<a class="btn btn-default btn-sm" href="/admin/appIndex/block/edit.xhtm?cityCode={{value.cityCode}}&langCode={{value.langCode}}&bid={{value.bid}}">编辑</a>
													<a class="btn btn-info btn-sm" href="javascript:copyShow({{value.bid}}, '{{value.title}}');">复制</a>
													{{if value.status == <%=BooleanConstants.TRUE%>}}
													<a class="btn btn-warning btn-sm" href="javascript:enable({{value.bid}}, 0);">停用</a>
													{{/if}}
													{{if value.status == <%=BooleanConstants.FALSE%>}}
													<a class="btn btn-success btn-sm" href="javascript:enable({{value.bid}}, 1);">启用</a>
													{{/if}}
													{{if value.autoPush != <%=BooleanConstants.TRUE%>}}
													<a class="btn btn-primary btn-sm" href="/admin/appIndex/item/list.xhtm?bid={{value.bid}}">管理项</a>
													{{/if}}
													<a class="btn btn-danger btn-sm" href="javascript:del({{value.bid}}, '{{value.title}}');">删除</a>
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

		<!-- Copy Modal -->
		<div class="modal fade" id="copyModal" tabindex="-1" role="dialog" aria-labelledby="copyModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="copyModalLabel">请 选择/输入 目的信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal">
							<input id="sourceBidID" type="hidden" />

							<div class="form-group">
								<label class="col-md-3 control-label">当前块信息</label>
								<div class="col-md-8">
									<p id="sourceTilteID" class="form-control-static"></p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">目标城市<span style="color: red;">*</span></label>
								<div class="col-md-8">
									<select class="form-control" id="destCity">
										<c:forEach items="${cityList}" var="city">
											<option value="${city.code}">${city.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">目标语言<span style="color: red;">*</span></label>
								<div class="col-md-8">
									<select class="form-control" id="destLang">
										<c:forEach items="${langList}" var="lang">
											<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-3 control-label">新的标题<span style="color: red;">*</span></label>
								<div class="col-md-8">
									<input id="newTitle" class="form-control" placeholder="请输入新的块标题">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" onclick="cleanCopy();">取消</button>
						<button type="button" class="btn btn-primary" onclick="applyCopy();">确认</button>
					</div>
				</div>
			</div>
		</div>

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

			// load 数据
			$('#listTable').artPaginate({
				tpl : 'listTableTpl',
				url : "/admin/appIndex/block/list",
				params : { cityCode : cityCode, langCode: langCode}
			});
		}

		function viewCityLang() {
			var c = $("#city").find("option:selected");
			$("#nowCityCode").val(c.val());
			$("#nowCityName").html(c.text());
			var l = $("#lang").find("option:selected");
			$("#nowLangCode").val(l.val());
			$("#nowLangName").html(l.text());
		}

		function pathParamOrSelect() {
			// 将URL中指定的城市，设置到 select组件中
			var cityCode = $.getUrlParam("cityCode");
			if (cityCode) {
				$("#city").find("option[value='" + cityCode + "']").attr("selected", "selected");
			}
			// 将URL中指定的语言，设置到 select组件中
			var langCode = $.getUrlParam("langCode");
			if (langCode) {
				$("#lang").find("option[value='" + langCode + "']").attr("selected", "selected");
			}
			search();
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
			window.location.href = '/admin/appIndex/block/edit.xhtm?cityCode='+cityCode+'&langCode='+langCode;
		}

		// type；1：上移、2：下移、3：置顶、4：置底
		function mv(bid, type) {
			$.getJSON('/admin/appIndex/block/mv', {
				'type' : type,
				'bid' : bid
			}, function(json) {
				if (json.success) {
					search();
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		// type；1：启用、其他：禁用
		function enable(bid, type) {
			var url = '/admin/appIndex/block/';
			url += (1 == type ? 'enable' : 'disable');
			$.getJSON(url, {
				'bid' : bid
			}, function(json) {
				if (json.success) {
					search();
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		function pubBetaVersion() {
			var cityCode = $("#nowCityCode").val();
			var cityName = $("#nowCityName").text();
			var langCode = $("#nowLangCode").val();
			var langName = $("#nowLangName").text();
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '请确认是否将【'+cityName+' / '+langName+'】的数据块，发布为测试版本？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/appIndex/block/pubBetaVersion', {
							cityCode : cityCode, langCode : langCode
						}, function(json) {
							MyDialog.alert(json.message);
						});
					}
				}
			});
		}

		var yesSub = false;
		function copyShow(bid, blockTitle) {
			yesSub = false;
			$('#sourceBidID').val(bid);
			$('#newTitle').val(blockTitle);
			$('#sourceTilteID').html(blockTitle+'&nbsp;&nbsp;(id:'+bid+')');
			$("#copyModal").modal("show");
		}
		function cleanCopy() {
			yesSub = false;
			$('#sourceBidID').val('');
			$('#newTitle').val('');
			$('#sourceTilteID').html('');
			$("#copyModal").modal("hide");
		}
		function applyCopy() {
			var sBid = $('#sourceBidID').val();
			var dCity = $('#destCity').val();
			var dLang = $('#destLang').val();
			var newTitle = $('#newTitle').val();
			
			if (!sBid) { MyDialog.alert("不知道你要复制那个块！");return; }
			if (!dCity) { MyDialog.alert("请先选择目的城市！");return; }
			if (!dLang) { MyDialog.alert("请先选择目的语言！");return; }
			if (!newTitle) { MyDialog.alert("请先输入新的块标题！");return; }
			if (yesSub) { MyDialog.alert("请耐心等待一下！");return; }
			
			var showBlock = $('#sourceTilteID').html();
			var showCity = $("#destCity").find("option:selected").text();
			var showLang = $("#destLang").find("option:selected").text();
			
			BootstrapDialog.confirm({
				title: '请确认是否操作',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				//message: '确定将<span class="text-primary">【'+showBlock+'】</span>复制到<span class="text-success">【'+showCity+' / '+showLang+'】</span>的布局中么？'
				//	+'<p class="bg-danger" style="padding: 15px;">复制数据需要时间，点击确定之后，请耐心等待。</p>',
				message: '<p class="bg-info" style="padding: 15px;">确定将【'+showBlock+'】复制到【'+showCity+' / '+showLang+'】的布局中么？<p>'
					+'<p class="bg-danger" style="padding: 15px;">复制数据需要时间，点击确定之后，请耐心等待。</p>',
				callback: function(result) {
					if(result) {
						yesSub = true;
						var url = '/admin/appIndex/block/copy?sBid='+sBid+'&dCity='+dCity+'&dLang='+dLang;
						$.ajax({url: url, data: {newTitle: newTitle}, timeout: 20000, type: 'POST', dataType: 'json', success: function (json) {
							MyDialog.alert(json.message);
							if (json.success) {
								cleanCopy();
								search();
							}
						}});
					}
				}
			});
		}

		function del(bid, blockTitle) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '你确认删除【' + blockTitle + '】块么？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/appIndex/block/del?bid='+bid, {}, function(json) {
							if (json.success) {
								search();
							} else {
								MyDialog.alert(json.message);
							}
						});
					}
				}
			});
		}

		// blockType List >> [{name: '', value: ''}, {..} ..]
		var blockTypeArr = [
   			<%boolean one = true;for(RecommendBlockType type : RecommendBlockType.values()) {%>
   			<%if(!one) {%><%=", "%><%}%>{name: '<%=type.getName()%>', value: '<%=type.getValue()%>'}
   			<%one = false;}%>
   		];
		$(function() {
			template.helper('minuteToHM', minuteToHM);// ‘分钟数’ 转换成 【时:分】
			// 块类型转换成中文显示
			template.helper('showTypeName', function(type) {
				var name = type;
				$.each(blockTypeArr, function(i, d) {
					if(type == d.value) {
						name = d.name;
					}
				});
				return name;
			});
			
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
