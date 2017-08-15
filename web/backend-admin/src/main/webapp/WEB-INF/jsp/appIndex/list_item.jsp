<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendItemTarget"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>App首页--项列表</title>
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
					App首页--项列表 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="col-sm-6 form-inline">
									<ol class="breadcrumb" style="background-color: #ffffff;">
										<li>${city.name}&nbsp;/&nbsp;${lang.zhName}</li>
										<li class="active"><a href="/admin/appIndex/block/list.xhtm?cityCode=${city.code}&langCode=${lang.code}"><strong>${block.title}&nbsp;&nbsp;(${blockType.name})</strong></a></li>
									</ol>
								</div>
								<div class="col-sm-6 form-inline text-right">
									<a class="btn btn-success" href="/admin/appIndex/item/edit.xhtm?bid=${block.bid}">添加数据项</a>
								</div>
								<input type="hidden" id="bid" value="${block.bid}">
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<table id="listTable" class="table table-bordered table-hover"><!-- table-striped -->
											<thead>
												<tr>
													<th>类型</th>
													<th>名称/简介</th>
													<th>状态</th>
													<th>图片</th>
													<th>logo/价格/分享</th>
													<th>H5地址/参数</th>
													<th>创建时间</th>
													<th>移动</th>
													<th>操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr class="{{if value.status == <%=BooleanConstants.FALSE%>}}bg-warning{{/if}}">
												<td>
													{{showTargetName value.target}}
												</td>
												<td>
													<p class="text-primary">{{value.name}}</p>
													<p class="text-warning">{{value.description}}</p>
												</td>
												<td>
													{{if value.status == <%=BooleanConstants.TRUE%>}}<p class="text-success font-weight">启用中</p>{{/if}}
													{{if value.status != <%=BooleanConstants.TRUE%>}}<p class="text-warning">已停用</p>{{/if}}
												</td>
												<td>
													<img src="${imgDomain}{{value.img}}" height="60" />
												</td>
												<td>
													{{if '' != value.logo}}<img src="${imgDomain}{{value.logo}}" height="60" />{{/if}}
													{{if 'dish' == value.target}}
													{{if 0 < value.originalPrice}}
													原价：{{cent2yuan value.originalPrice}}元
													<br>
													{{/if}}
													现价：{{cent2yuan value.price}}元
													{{/if}}

													{{if 'h5' == value.target && value.canShare == <%=BooleanConstants.TRUE%>}}<p class="text-success">可以分享</p>{{/if}}
													{{if 'h5' == value.target && value.canShare != <%=BooleanConstants.TRUE%>}}<p class="text-danger">不可分享</p>{{/if}}
												</td>
												<td>
													<p class="text-primary">{{value.url}}</p>
													<p class="text-warning">{{value.targetParams}}</p>
												</td>
												<td style="font-size: 12px;">{{formatDateTime value.createTime}}</td>
												<td>
													{{if i != 0}}
													<!--<a class="btn btn-default btn-sm" href="javascript:mv({{value.itemId}}, 3);">置顶</a>-->
													<a class="btn btn-default btn-sm" href="javascript:mv({{value.itemId}}, 1);">上移</a>
													{{/if}}
													<br>
													{{if i+1 < object.length}}
													<a class="btn btn-default btn-sm" href="javascript:mv({{value.itemId}}, 2);">下移</a>
													<!--<a class="btn btn-default btn-sm" href="javascript:mv({{value.itemId}}, 4);">置底</a>-->
													{{/if}}
												</td>
												<td>
													<a class="btn btn-primary btn-sm" href="/admin/appIndex/item/edit.xhtm?bid={{value.bid}}&itemId={{value.itemId}}">编辑</a>
													{{if value.status == <%=BooleanConstants.TRUE%>}}<a class="btn btn-warning btn-sm" href="javascript:enable({{value.itemId}}, 0);">停用</a>{{/if}}
													{{if value.status == <%=BooleanConstants.FALSE%>}}<a class="btn btn-success btn-sm" href="javascript:enable({{value.itemId}}, 1);">启用</a>{{/if}}
													<a class="btn btn-danger btn-sm" href="javascript:del({{value.itemId}}, '{{value.name}}');">删除</a>
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
		function search() {
			var bid = $("#bid").val();
			if (!bid) {
				MyDialog.alert("请从【块列表】中点击【查看数据项】进入本页面！");
				return;
			}

			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "/admin/appIndex/item/list",
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {
					bid : bid
				}
			});
		}

		function del(itemId, itemName) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '你确认删除【' + itemName + '】数据项么？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/appIndex/item/del', {'itemId': itemId}, function(json) {
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

		// type；1：上移、2：下移、3：置顶、4：置底
		function mv(itemId, type) {
			$.getJSON('/admin/appIndex/item/mv', {
				'itemId' : itemId,
				'type' : type
			}, function(json) {
				if (json.success) {
					search();
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		// type；1：启用、其他：禁用
		function enable(itemId, type) {
			var url = '/admin/appIndex/item/';
			url += (1 == type ? 'enable' : 'disable');
			$.getJSON(url, {
				'itemId' : itemId
			}, function(json) {
				if (json.success) {
					search();
				} else {
					MyDialog.alert(json.message);
				}
			});
		}

		// itemTarget List >>>> [{name: '', value: ''}, {..} ..]
		var targetArr = [
			<%boolean one = true;for(RecommendItemTarget target : RecommendItemTarget.values()) {%>
			<%if(!one) {%><%=", "%><%}%>{name: '<%=target.getName()%>', value: '<%=target.getValue()%>'}
			<%one = false;}%>
		];
		// 根据target类型，返回名称
		function getTargetName(target) {
			var name = target;
			$.each(targetArr, function(i, d) {
				if(target == d.value) {
					name = d.name;
				}
			});
			return name;
		}

		$(function() {
			// target类型转换成中文显示
			template.helper('showTargetName', getTargetName);
			// 搜索数据
			search();
		});
	</script>
</body>
</html>
