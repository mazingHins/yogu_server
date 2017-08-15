<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page import="com.mazing.core.constant.OrderGiftTypeConstants" %>
<%@ page import="com.mazing.core.enums.pay.PayType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>赠礼列表</title>
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
				赠礼列表
				<small></small>
			</h1>
			
			
			
			<ol class="breadcrumb">
				<li><a href="/admin/activity/orderGift/add.xhtm"><i class="fa fa-dashboard"></i> 新增-赠礼</a></li>
			</ol>
		</section>
		

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="row">
								<div class="col-sm-3">
								</div>
								<div class="col-sm-9" style="text-align: right;">
									<a href="javascript:void(0)" onclick="flushCache();" class="btn btn-primary" >发布</a>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<p class="text-left">说明：</p>
									<p class="text-left">1. 赠礼的有效日期 不能重叠</p>
									<p class="text-left" style="color:red;">2. 若新增了红包赠礼记录，并想让它 立即 生效 ，请点击右上角的【发布】</p>
								</div>
							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th width="5%">名称</th>
											<th  width="5%">时间轴</th>
											<th>类型</th>
											<th>满足金额</th>
											<th>活动渠道</th>
											<th>开始(包含)</th>
											<th>结束(包含)</th>
											
											<th width="8%">时间段</th>
											<th width="30%">红包信息</th>
											
											<th>创建时间</th>
											<th>创建人</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.gid}}
												</td>
												<td>
													{{value.descrip}}
												</td>
	
													
													{{if value.timeLine=="进行中" && value.isEnable == <%=BooleanConstants.TRUE%> }}
															<td style="color:green;">{{value.timeLine}}</td>
													{{/if}}

													{{if value.timeLine=="已结束" && value.isEnable == <%=BooleanConstants.TRUE%> }}
															<td style="color:red;">{{value.timeLine}}</td>
													{{/if}}

													{{if value.timeLine=="待开始" && value.isEnable == <%=BooleanConstants.TRUE%>}}
															<td style="color:blue;">{{value.timeLine}}</td>
													{{/if}}

													 {{if value.isEnable == <%=BooleanConstants.FALSE%>}}
												   			<td style="color:red;">已禁用</td>
									    			 {{/if}}
													
												<td>
													{{if value.type == <%=OrderGiftTypeConstants.coupon%>}}
															优惠券
													{{/if}}

													{{if value.type == <%=OrderGiftTypeConstants.couponBag%>}}
															卡包
													{{/if}}

													{{if value.type == <%=OrderGiftTypeConstants.h5URL%>}}
															H5活动链接
													{{/if}}

												</td>

												<td>
														{{cent2yuan value.enoughMoney}}
												</td>
													{{if value.payType == <%=PayType.ONLINE.getValue()%>}}
															<td style="color:blue;">线上订餐</td>
													{{/if}}
	
													{{if value.payType == <%=PayType.MAZING_PAY.getValue()%>}}
															<td style="color:blue;">Mazing Pay</td>
													{{/if}}
												<td>
														{{value.startStr}}
												</td>

												<td>
														{{value.endStr}}
												</td>
												
												<td>	
														{{enter2br value.validHours}}
														
												</td>
												<td >
														{{enter2br value.teamDetail}}
														
												</td>

												<td style="font-size: 12px;">{{formatDateTime value.createTime}}</td>
												<td style="font-size: 12px;">{{value.adminName}}</td>
												<td>
													{{if value.isEnable == <%=BooleanConstants.TRUE%>}}
													&nbsp;<a href="javascript:void(0)" onclick="disableGift({{value.gid}})" class="btn btn-default" title="将该记录失效">禁用</a>
													{{/if}}
													{{if value.isEnable == <%=BooleanConstants.FALSE%>}}
													&nbsp;<a href="javascript:void(0)" onclick="enableGift({{value.gid}})" class="btn btn-default" title="将该记录生效">启用</a>
													{{/if}}
													&nbsp;<a href="javascript:void(0)" onclick="deleteGift({{value.gid}})" class="btn btn-primary" title="删除记录">删除</a>
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
							<div class="row col-sm-12" id="listPaginator">
							</div>
						</div><!-- /.box-body -->
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

<!--<a href="#" rel="tooltip" data-placement="right" title="{{value.teamDetail}}">详情</a>-->
<!-- <a href="#" rel="tooltip" data-placement="right" title="{{value.validHours}}">详情</a> -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>

</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>

<script type="text/javascript">

	function search() {
		var keyword = $.trim($('#keyword').val());
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/activity/orderGift/queryOrderGifts",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'keyword': keyword}
		});
	}

	// 加载优惠券数据
	function flushCache() {
		$.post('/admin/activity/orderGift/flushCache', {}, function(json) {
			if (json.success) {
				MyDialog.alert(json.message);
			} else {
				MyDialog.alert("刷新失败,请重试");
			}
		});
	}
	
	
	
	// 使删除
	var deleteGiftYes = false;
	function deleteGift(gid) {
		deleteGiftYes = false;
		BootstrapDialog.show({
			title: '使删除',
			message: '该赠礼记录将<strong>被删除</strong>，下单将获取不到该赠礼。',
			buttons: [{
				label: '确定',
				action: function(dialog) { deleteGiftYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { deleteGiftYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (deleteGiftYes) {
					$.post('/admin/activity/orderGift/deleteGift.do', {
						'gid': gid
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							reloadPage();
						}
					});
				}
			}
		});
	}
	
	
	
	
	
	// 重新加载列表
	function reloadPage() {
		var page = $('#listTable').artPage();
		if (page) {
			$('#listTable').artPaginateNext(page);
		}
	}


	// 使生效
	var enableCouponYes = false;
	function enableGift(gid) {
		enableCouponYes = false;
		BootstrapDialog.show({
			title: '使生效',
			message: '该赠礼记录将<strong>启用</strong>，下单将能获取该赠礼。',
			buttons: [{
				label: '确定',
				action: function(dialog) { enableCouponYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { enableCouponYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (enableCouponYes) {
					$.post('/admin/activity/orderGift/enableGift.do', {
						'gid': gid
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							reloadPage();
						}
					});
				}
			}
		});
	}
	
	// 使失效
	var disableCouponYes = false;
	function disableGift(gid) {
		disableCouponYes = false;
		BootstrapDialog.show({
			title: '使失效',
			message: '该赠礼记录将<strong>作废</strong>，下单将不能获取该赠礼。',
			buttons: [{
				label: '确定',
				action: function(dialog) { disableCouponYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { disableCouponYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (disableCouponYes) {
					$.post('/admin/activity/orderGift/disableGift.do', {
						'gid': gid
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							reloadPage();
						}
					});
				}
			}
		});
	}


	$(function() {
		search();

		// 模版函数：把\n 替换成 <br/>
		template.helper('enter2br', function (content) {
			return content.replace(/\n/g, "<br/>");
		});

		// 模版函数：输出 escape 单引号、双引号后的结果
		template.helper('escapeName', function (name) {
			var regExp = new RegExp("'", "g");
			var tmp = name.replace(regExp, "\\'");
			regExp = new RegExp("\"", "g");
			tmp = tmp.replace(regExp, "\\\"");
			return tmp;
		});

		// 模版函数：用tips来输出菜品的说明，需要把双引号 (") 替换为 单引号(')
		template.helper('renderTips', function (content) {
			return content.replace(/"/g, "'");
		});
		
		// 模版函数：折扣显示
		template.helper('discount2text', function (discount) {
			var d = parseFloat(discount) / 10;
			return d + ' 折';
		});
	});
</script>
</body>
</html>
