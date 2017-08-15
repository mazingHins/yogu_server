<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>餐厅资讯</title>
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
					餐厅资讯 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="col-sm-12 form-inline blockquote-reverse">
									&nbsp;&nbsp;&nbsp; <a href="javascript: toAddPage();"><i class="fa fa-dashboard"></i>新增餐厅资讯</a>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<table id="listTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>餐厅资讯ID</th>
													<th>餐厅名</th>
													<th>口味评分</th>
													<th>环境评分</th>
													<th>性价比评分</th>
													<th>点评</th>
													<th>点评人</th>
													<th>想吃人数</th>
													<th>吃过人数</th>
													<th>是否入驻</th>
													<th>mazing pay</th>
													<th>线上餐厅</th>
													<th>地图位置</th>
													<th>地址</th>
													<th>电话</th>
													<th>人均消费</th>
													<th>营业时间</th>
													<th width="13%">操作</th>
												</tr>
											</thead>
											<script id="listTableTpl" type="text/html">
												{{each object as value i}}
												<tr>
													<td>{{value.sinfoId}}</td>
													<td>{{value.storeName}}</td>
													<td>{{value.taste / 10}}分</td>
													<td>{{value.env / 10}}分</td>
													<td>{{value.costEffective / 10}}分</td>
													<td>{{value.reviews}}</td>
													<td>{{value.reviewer}}</td>
													<td>{{value.want}}</td>
													<td>{{value.eaten}}</td>
													<td>
														{{if value.inMazing == <%=BooleanConstants.TRUE%>}}
															是
														{{/if}}
														{{if value.inMazing != <%=BooleanConstants.TRUE%>}}
															否
														{{/if}}
													</td>

													<td>
														{{if value.showMazingPay == <%=BooleanConstants.TRUE%>}}
															开启
														{{/if}}
														{{if value.showMazingPay != <%=BooleanConstants.TRUE%>}}
															未开启
														{{/if}}
													</td>

													<td>
														{{if value.showOnlineStore == <%=BooleanConstants.TRUE%>}}
															展示
														{{/if}}
														{{if value.showOnlineStore != <%=BooleanConstants.TRUE%>}}
															不展示
														{{/if}}
													</td>
	
													<td><img src="${imgDomain}{{value.mapImg}}" height="60" /></td>
													<td>{{value.address}}</td>
													<td>{{value.phone}}</td>
													<td>{{value.perConsume}}</td>
													<td>{{value.openHours}}</td>
													<td>
														<a class="btn btn-default btn-sm" href="/admin/news/block/storeInfo/edit.xhtm?sinfoId={{value.sinfoId}}">编辑</a>
														&nbsp;<a class="btn btn-danger btn-sm" href="javascript:del({{value.sinfoId}}, '{{value.storeName}}');">删除</a>
	&nbsp;<a class="btn btn-default btn-sm" href="javascript:void(0)" onclick="getQRCode({{value.sinfoId}})" title="查看二维码">二维码</a>
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
			// load 数据
			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "/admin/news/block/storeInfo/list",
				// 显示页码的位置
				paginator: 'listPaginator2',
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {
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
			changeCity();
		}

		function toAddPage() {
			window.location.href = "/admin/news/block/storeInfo/edit.xhtm";
		}

		// type；1：启用、其他：禁用
		/* function enable(bid, type) {
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
		} */
		
		// 展示二维码
		function getQRCode(sinfoId) {
			BootstrapDialog.show({
				title: '二维码展示',
				message: '<div style="text-align: center;width: 100%;height: 400px;">'
						+'<img src="/admin/news/block/storeInfo/getStoreinfoQRCode/'+sinfoId+'?width=350&height=350" />'
						+'<br>'
						+'https://m.mazing.com/open/share/storeinfo/'+sinfoId
						+'</div>',
				buttons: [{ label: '取消', action: function(dialog) { dialog.close(); } }]
			});
		}


		function del(sinfoId, storeName) {
			BootstrapDialog.confirm({
				title: '操作确认',
				btnOKLabel: '确认',
				btnCancelLabel: '取消',
				message: '你确认删除【' + storeName + '】的资讯信息么？',
				callback: function(result) {
					if(result) {
						$.getJSON('/admin/news/block/storeInfo/del?sinfoId='+sinfoId, {}, function(json) {
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
			search();
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