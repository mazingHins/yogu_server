<%@ page import="com.mazing.core.constant.CouponBearTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponTypeConstants" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>优惠券卡包列表</title>
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
				优惠券卡包列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/coupon/editCouponBag.xhtm"><i class="fa fa-dashboard"></i> 新增卡包</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header">
							<div class="row">
								<div class="col-md-3">
									<div class="input-group input-group-sm">
										<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
										</span>
									</div>
								</div>
								<div class="col-md-9" style="text-align: right;">

								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）终止发放：用户将不能领取这种卡包，已经领取的卡包里的优惠券仍然可以使用。<span class="text-red">终止卡包的发放后，优惠券只能作废</span></p>
									<p class="text-red">（2）卡包一旦生成，不能被修改。</p>
									<p class="text-red">（3）卡包作废：用户将不能领取这种卡包，并且所有已经被用户领取的优惠券一起作废。</p>
								</div>
							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-md-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th>名称</th>
											<th>总面值</th>
											<th>领取总量</th>
											<th>相关优惠券</th>
											<th>生效时间</th>
											<th>过期时间</th>
											<th>创建人</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.bagId}}
												</td>
												<td>
													{{value.name}}
													{{if value.isStop == <%=BooleanConstants.TRUE%>}}
														<span style="color: #ff0000;" title="卡包已经停止发放">(停止发放)</span>
													{{/if}}

												</td>
												<td>
													{{cent2yuan value.faceValue}}
												</td>
												
												<td>
													{{value.gainTotal}}
												</td>
												<td>
													{{each value.couponRules as rule j}}
														<div><a href="/admin/coupon/list.xhtm?couponRuleId={{rule.couponRuleId}}" target="_blank">{{rule.couponName}}</a></div>
													{{/each}}
												</td>
												<td style="font-size: 12px;">{{formatDateTime value.startTime}}</td>
												<td style="font-size: 12px;">{{formatDateTime value.endTime}}</td>
												<td style="font-size: 12px;">{{value.adminName}}</td>
												<td>
													{{if value.isStop == <%=BooleanConstants.FALSE%>}}
													&nbsp;<a href="javascript:void(0)" onclick="getCouponBagQRCode({{value.bagId}}, '{{value.encryptBagId}}')" class="btn btn-default" title="查看二维码">二维码</a>
													&nbsp;<a href="javascript:void(0)" onclick="stopExchange({{value.bagId}})" class="btn btn-default" title="用户将不能领取这个卡包">终止发放</a>
													&nbsp;<a href="javascript:void(0)" onclick="disableCouponBag({{value.bagId}})" class="btn btn-default" title="终止发放并且优惠券作废">作废</a>
													&nbsp;<a href="javascript:void(0)" onclick="newBatch({{value.bagId}}, '{{escapeName value.name}}')" class="btn btn-primary" title="生成新的批次">新批次</a>
													{{/if}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
							<div class="row col-md-12" id="listPaginator">
							</div>
						</div><!-- /.box-body -->
					</div><!-- /.box -->
				</div><!-- /.col -->
			</div><!-- /.row -->
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
	function search() {
		var keyword = $.trim($('#keyword').val());
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/coupon/queryCouponBags",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'keyword': keyword}
		});
	}

	// 重新加载列表
	function reloadPage() {
		var page = $('#listTable').artPage();
		if (page) {
			$('#listTable').artPaginateNext(page);
		}
	}

	// 返回一些未使用过的优惠码
	function getUnusedCoupon(couponRuleId) {
		$.getJSON("/admin/coupon/getUnusedCoupon", {'couponRuleId':couponRuleId}, function(json) {
			if (json.success) {
				MyDialog.alert('可用的优惠码：' + json.object);
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}

	// 展示二维码
	function getCouponBagQRCode(bagId, encryptBagId) {
		BootstrapDialog.show({
			title: '二维码展示',
			message: '<div style="text-align: center;width: 100%;height: 400px;">'
					+'<img src="/admin/coupon/getCouponBagQRCode/'+bagId+'?width=350&height=350" />'
					+'<br>'
					+'https://m.mazing.com/open/share/couponBagShare/'+encryptBagId
					+'</div>',
			buttons: [{ label: '取消', action: function(dialog) { dialog.close(); } }]
		});
	}

	// 停止发放
	var stopExchangeYes = false;
	function stopExchange(bagId) {
		stopExchangeYes = false;
		BootstrapDialog.show({
			title: '终止发放',
			message: '用户将不能领取这种卡包，已经领取的卡包里的优惠券仍然可以使用',
			buttons: [{
				label: '确定',
				action: function(dialog) { stopExchangeYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { stopExchangeYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (stopExchangeYes) {
					$.post('/admin/coupon/stopCouponBagExchange.do', {
						'bagId': bagId
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

	// 令卡包作废
	function disableCouponBag(bagId) {
		BootstrapDialog.show({
			title: '卡包作废',
			message: '用户将不能领取这种卡包，并且所有已经被用户领取的优惠券一起作废',
			buttons: [{
				label: '确定',
				action: function (dialog) {
					dialog.close();
					$.post('/admin/coupon/disableCouponBag.do', {
						'bagId': bagId
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							reloadPage();
						}
					});
				}
			}, {
				label: '取消',
				action: function (dialog) {
					dialog.close();
				}
			}
			]
		});
	}

	// 生成新的批次
	// 2016/2/19 by ten
	function newBatch(bagId, bagName) {
		BootstrapDialog.show({
			title: '生成新的批次',
			message: '卡包「' + bagName + '」，请输入要生成的数量：<br>' +
			'<input type="text" name="createTotal" class="form-control" style="width:50%;" maxlength="5">',
			buttons: [{
				label: '确定',
				action: function(dialog) {
					dialog.close();
					var createTotal = $.trim(dialog.getModalBody().find('input[name=createTotal]').val());
					var num = parseInt(createTotal);
					if (createTotal == '' || isNaN(num)) {
						MyDialog.alert('请输入要生成的优惠券数量。');
					}
					else if (num <= 0) {
						MyDialog.alert('优惠券数量不能小于1。');
					}
					else if (num > 10000) {
						MyDialog.alert('优惠券数量不能大于10000');
					}
					else {
						// 再次确认  。。。暂无
						// 执行修改
						$.post("/admin/coupon/newBagBatch.do", {
							'bagId': bagId,
							'createTotal': createTotal
						}, function (json) {
							MyDialog.alert(json.message);
							// reload
							if (json.success) {
								search();
							}
						}, "json");
					}
				}
			}, {
				label: '取消',
				action: function(dialog) { dialog.close(); }
			}
			],
			onhide: function(dialogRef){

			}
		});
	}

	$(function() {
		search();

		// 模版函数：把\n 替换成 <br/>
		template.helper('enter2br', function (content) {
			return content.replace(/\n/g, "<br/>");
		});

		// 模版函数：折扣显示
		template.helper('discount2text', function (discount) {
			var d = parseFloat(discount) / 10;
			return d + ' 折';
		});

		// 模版函数：输出 escape 单引号、双引号后的结果
		template.helper('escapeName', function (name) {
			var regExp = new RegExp("'", "g");
			var tmp = name.replace(regExp, "\\'");
			regExp = new RegExp("\"", "g");
			tmp = tmp.replace(regExp, "\\\"");
			return tmp;
		});
	});
</script>
</body>
</html>
