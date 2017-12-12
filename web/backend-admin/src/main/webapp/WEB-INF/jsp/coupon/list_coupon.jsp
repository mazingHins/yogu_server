<%@ page import="com.yogu.services.backend.admin.resources.coupon.CouponListResource" %>
<%@ page import="com.yogu.core.constant.CouponTypeConstants" %>
<%@ page import="com.yogu.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>优惠券列表</title>
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
				优惠券列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/coupon/editCoupon.xhtm"><i class="fa fa-dashboard"></i> 新增优惠券</a></li>
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
									<div class="input-group input-group-sm">
										<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
										</span>
									</div>
								</div>
								<div class="col-sm-9" style="text-align: right;">

								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）终止发放：用户将不能领取这种优惠券，已经领取的优惠券仍然可以使用</p>
									<p class="text-left"  style="color:red;">（3）有效日期动态的优惠券(列【有效日期是否动态】 为‘是’ 的 )， 【生效时间】【过期时间】指的是有效的领取时间段，该种券的有效时间以实际领取情况为准</p>
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
											<th>名称</th>
											<th>类型</th>
											<th>面值</th>
											<th>最高优惠</th>
											<th>发放总量</th>
											<th>领取总量</th>
											<th>使用人次</th>
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
													{{value.couponRuleId}}
												</td>
												<td>
													{{value.couponName}}
												</td>
												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
													现金券
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
													折扣券
													{{/if}}
												</td>
												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
														{{cent2yuan value.regExpression}}
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
														{{discount2text value.regExpression}}
													{{/if}}
												</td>

												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
														无
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
														{{cent2yuan value.mostOffer}}
													{{/if}}
												</td>
												
												<td>
													{{value.createTotal}}
												</td>
												<td>
													{{value.gainTotal}}
												</td>
												<td>
													{{value.useTotal}}
												</td>
												<td style="font-size: 12px;">{{formatDateTime value.startTime}}</td>
												<td style="font-size: 12px;">{{formatDateTime value.endTime}}</td>
												<td style="font-size: 12px;">{{value.adminName}}</td>
												<td>
													<a href="javascript:void(0)" onclick="displayDetail({{value.couponRuleId}})" class="btn btn-default">查看详细</a>
													&nbsp;<a href="javascript:void(0)" onclick="stopExchange({{value.couponRuleId}})" class="btn btn-default" title="用户将不能领取这种优惠券">终止发放</a>
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

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>

	<!-- Modal -->
	<div class="modal fade" id="couponRuleModal" tabindex="-1" role="dialog" aria-labelledby="couponRuleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="couponRuleModalLabel">查看优惠券</h4>
				</div>
				<div class="modal-body" id="couponRuleModalBody">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script id="couponRuleDetailTpl" type="text/html">
	<div class="row">
		<label class="control-label col-sm-4 text-right">类型：</label>
		<div class="col-sm-8">
			{{if couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
			代金券
			{{/if}}
			{{if couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
			折扣券
			{{/if}}
		</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">名称：</label>
		<div class="col-sm-8">{{couponName}}</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">发放总量：</label>
		<div class="col-sm-8">{{createTotal}}</div>
	</div>
	<div class="row">
		{{if couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
		<label class="control-label col-sm-4 text-right">面值：</label>
		<div class="col-sm-8">￥{{cent2yuan regExpression}} 元</div>
		{{/if}}
		{{if couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
		<label class="control-label col-sm-4 text-right">折扣：</label>
		<div class="col-sm-8">￥{{discount2text regExpression}}</div>
		{{/if}}

	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">订单金额：</label>
		<div class="col-sm-8">￥{{cent2yuan enoughMoney}} 元</div>
	</div>

	{{if isDynamic == <%=BooleanConstants.TRUE%>}}
		<div class="row">
			<label class="control-label col-sm-4 text-right">有效天数：</label>
			<div class="col-sm-8">{{effectDays}}</div>
		</div>	
	{{/if}}

	{{if isDynamic == <%=BooleanConstants.TRUE%>}}
		<div class="row">
			<label class="control-label col-sm-4 text-right">有效领取时间段：</label>
			<div class="col-sm-8">{{formatDateTime startTime}} - {{formatDateTime endTime}}</div>
		</div>
	{{/if}}
	
	{{if isDynamic == <%=BooleanConstants.FALSE%>}}
		<div class="row">
			<label class="control-label col-sm-4 text-right">生效时间：</label>
			<div class="col-sm-8">{{formatDateTime startTime}} - {{formatDateTime endTime}}</div>
		</div>

	{{/if}}

	<div class="row">
		<label class="control-label col-sm-4 text-right">每人限领：</label>
		<div class="col-sm-8">{{gainTotal}} 张</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">使用范围：</label>
		<div class="col-sm-8">
			{{useLimit}}
		</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">使用说明：</label>
		<div class="col-sm-8">
			{{enter2br description}}
		</div>
	</div>
</script>
<script type="text/javascript">
	
	var adminId =  "${adminId}";
	
	function search() {
		var keyword = $.trim($('#keyword').val());
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/coupon/queryCouponRules",
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

	// 停止发放
	var stopExchangeYes = false;
	function stopExchange(couponRuleId) {
		stopExchangeYes = false;
		BootstrapDialog.show({
			title: '终止发放',
			message: '用户将不能领取这种优惠券，已经领取的优惠券仍然可以使用',
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
					$.post('/admin/coupon/stopCouponExchange.do', {
						'couponRuleId': couponRuleId
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
	function disableCoupon(couponRuleId) {
		disableCouponYes = false;
		BootstrapDialog.show({
			title: '使失效',
			message: '优惠券全部<strong>作废</strong>，用户已经领取的优惠券也不能使用。',
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
					$.post('/admin/coupon/disableCouponRule.do', {
						'couponRuleId': couponRuleId
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

	// 显示优惠券的详细内容
	function displayDetail(couponRuleId) {
		$.getJSON("/admin/coupon/getCouponRuleDetail", {'couponRuleId':couponRuleId, 'display' : true}, function(json) {
			if (json.success) {
				var detail = json.object;
				var htmlTxt = template('couponRuleDetailTpl', detail);
				$('#couponRuleModalBody').html(htmlTxt);
				$('#couponRuleModal').modal('show');
			}
			else {
				MyDialog.alert(json.message);
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

		// 模版函数：折扣显示
		template.helper('discount2text', function (discount) {
			var d = parseFloat(discount) / 10;
			return d + ' 折';
		});
	});
</script>
</body>
</html>
