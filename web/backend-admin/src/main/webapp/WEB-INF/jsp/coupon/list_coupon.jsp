<%@ page import="com.mazing.services.backend.admin.resources.coupon.CouponListResource" %>
<%@ page import="com.mazing.core.constant.CouponBearTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponGroupTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponTypeConstants" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page import="com.mazing.core.constant.CouponUsingChannelConstants" %>
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
									<p class="text-left">（2）使失效：优惠券全部作废，用户已经领取的优惠券也不能使用</p>
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
											<th>使用黑名单</th>
											<th>有效日期是否动态</th>
											<th>使用渠道</th>
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
													{{if value.isEnable == <%=BooleanConstants.TRUE%>}}
														{{value.couponName}}
														{{if value.isStop == <%=BooleanConstants.TRUE%>}}
															<span style="color: #ff0000;" title="优惠券已经停止发放">(停止发放)</span>
														{{/if}}
													{{/if}}
													{{if value.isEnable != <%=BooleanConstants.TRUE%>}}
														<!-- 这个优惠券已经干掉了 -->
														<span style="text-decoration: line-through; color:#888;" title="优惠券已失效">{{value.couponName}}</span>
													{{/if}}
													{{if value.bagId > 0}}
														<span style="color: #ff00df;" title="已经进入卡包"><br/>(卡包：{{value.bagName}})</span>
													{{/if}}

												</td>
												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
													<!-- 现金券 -->
													现金券
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
													折扣券
													{{/if}}
												</td>
												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
														<!-- 现金券 -->
														{{cent2yuan value.regExpression}}
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
														<!-- 折扣券 -->
														{{discount2text value.regExpression}}
													{{/if}}
												</td>

												<td>
													{{if value.couponType == <%=CouponTypeConstants.CASH_COUPON%>}}
														无
													{{/if}}
													{{if value.couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>}}
														<!-- 折扣券 -->
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
												
													{{if value.hasExclude == 1}}
														<td style="color:red;">
															<a href="javascript:void(0)" >是</a>
														</td>
													{{/if}}
													
													{{if value.hasExclude != 1}}
														<td >
															<a href="javascript:void(0)" >否</a>
														</td>
													{{/if}}
																	
												
													{{if value.isDynamic == 1}}
														<td style="color:red;">
															是
														</td>
													{{/if}}
													
													{{if value.isDynamic != 1}}
														<td >
															否
														</td>
													{{/if}}
												<td style="font-size: 12px;">
													{{if value.usingChannel == <%=CouponUsingChannelConstants.NONE_LIMIT%>}}
														通用
													{{/if}}
													{{if value.usingChannel == <%=CouponUsingChannelConstants.ONLINE_ORDER%>}}
														线上订餐
													{{/if}}
													{{if value.usingChannel == <%=CouponUsingChannelConstants.MAZING_PAY%>}}
														Mazing Pay
													{{/if}}
												</td>
	
												<td style="font-size: 12px;">{{formatDateTime value.startTime}}</td>
												<td style="font-size: 12px;">{{formatDateTime value.endTime}}</td>
												<td style="font-size: 12px;">{{value.adminName}}</td>
												<td>
													<a href="javascript:void(0)" onclick="displayDetail({{value.couponRuleId}})" class="btn btn-default">查看详细</a>
													{{if value.isEnable == <%=BooleanConstants.TRUE%> && value.isStop != <%=BooleanConstants.TRUE%> && value.bagId == 0}}
													&nbsp;<a href="javascript:void(0)" onclick="getCouponQRCode({{value.couponRuleId}}, '{{value.encryptCouponRuleId}}')" class="btn btn-default" title="查看二维码">二维码</a>
													&nbsp;<a href="javascript:void(0)" onclick="stopExchange({{value.couponRuleId}})" class="btn btn-default" title="用户将不能领取这种优惠券">终止发放</a>
													{{/if}}
													{{if value.isEnable == <%=BooleanConstants.TRUE%> && value.bagId == 0}}
													&nbsp;<a href="javascript:void(0)" onclick="disableCoupon({{value.couponRuleId}})" class="btn btn-default" title="优惠券将作废，已经领取的无法使用，未领取的优惠券不能被领取">使失效</a>
													&nbsp;<a href="javascript:void(0)" onclick="getUnusedCoupon({{value.couponRuleId}})" class="btn btn-default" title="返回一些未使用过的优惠码">我要…</a>
													&nbsp;<a href="javascript:void(0)" onclick="newBatch({{value.couponRuleId}}, '{{escapeName value.couponName}}')" class="btn btn-primary" title="生成新的批次">新批次</a>
													{{/if}}
													
													{{if value.couponBearType == <%=CouponBearTypeConstants.PLATFORM%>}}
														&nbsp;<a href="javascript:void(0)" onclick="editBlack({{value.couponRuleId}})" class="btn btn-primary" title="编辑优惠券黑名单">编辑黑名单</a>
													{{/if}}
													<a href="javascript:void(0)" onclick="displayCheckoutURL('{{value.couponName}}', '{{value.encryptCouponRuleId}}')" class="btn btn-default">核销链接</a>
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

	<div class="row">
		<label class="control-label col-sm-4 text-right">有效日期是否动态：</label>
		<div class="col-sm-8">
			{{if isDynamic == <%=BooleanConstants.TRUE%>}}
				是
			{{/if}}
			{{if isDynamic == <%=BooleanConstants.FALSE%>}}
				否
			{{/if}}
		</div>
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
		<label class="control-label col-sm-4 text-right">每天限用：</label>
		<div class="col-sm-8">{{dayUseTotal}} 次</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">使用者：</label>
		<div class="col-sm-8">
			{{if verifyRecePhone == <%=BooleanConstants.TRUE%>}}
			收货人与领取人电话一致才能使用
			{{/if}}
			{{if verifyRecePhone == <%=BooleanConstants.FALSE%>}}
			不限
			{{/if}}
		</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">成本承担：</label>
		<div class="col-sm-8">
			{{if couponBearType == <%=CouponBearTypeConstants.MERCHANT%>}}
			商家
			{{/if}}
			{{if couponBearType == <%=CouponBearTypeConstants.PLATFORM%>}}
			平台
			{{/if}}
		</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">使用范围：</label>
		<div class="col-sm-8">
			{{useLimit}}
		</div>
	</div>
	<div class="row">
		<label class="control-label col-sm-4 text-right">分享/赠送：</label>
		<div class="col-sm-8">
			{{if canShare == <%=BooleanConstants.TRUE%>}}
			【可以分享】 &nbsp;
			{{/if}}
			{{if canTransfer == <%=BooleanConstants.TRUE%>}}
			【可以转让】 &nbsp;
			{{/if}}
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

	
	function editBlack(couponRuleId){
		var url = "/admin/coupon/blacklist/edit.xhtm?couponRuleId="+couponRuleId;
		window.location.href = url;
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
	function getCouponQRCode(couponRuleId, encryptCouponRuleId) {
		BootstrapDialog.show({
			title: '二维码展示',
			message: '<div style="text-align: center;width: 100%;height: 400px;">'
					+'<img src="/admin/coupon/getCouponQRCode/'+couponRuleId+'?width=350&height=350" />'
					+'<br>'
					+'https://m.mazing.com/open/share/couponShare/'+encryptCouponRuleId
					+'</div>',
			buttons: [{ label: '取消', action: function(dialog) { dialog.close(); } }]
		});
	}

	function displayCheckoutURL(couponName, encryptCouponRuleId){
		//https://static.mazing.com/static/coupon/checkout/index.html
		var url = "https://web.mazing.com/mobile/exchange/index?couponRuleId="+encryptCouponRuleId+"&couponName="+couponName+"&ad="+adminId;
		window.location.href = url;
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

	// 生成新的批次
	// 2016/1/29 by ten
	function newBatch(couponRuleId, couponRuleName) {
		BootstrapDialog.show({
			title: '生成新的批次',
			message: '优惠券「' + couponRuleName + '」，请输入要生成的数量：<br>' +
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
						$.post("/admin/coupon/newBatch.do", {
							'couponRuleId': couponRuleId,
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

	// 显示优惠券的详细内容
	function displayDetail(couponRuleId) {
		$.getJSON("/admin/coupon/getCouponRuleDetail", {'couponRuleId':couponRuleId, 'display' : true}, function(json) {
			if (json.success) {
				var detail = json.object;
				var includeStoreNameMap = detail.includeStoreNameMap;
				
				var excludeStoreNameMap = detail.excludeStoreNameMap;
				
				var includeGroupType = detail.includeGroupType;
				
				var useLimit = '';
				var inclu = 0;
				var exclu = 0;
				for (var prop in includeStoreNameMap) {
					if (useLimit != '') {
						useLimit += '，';
					}
					useLimit += prop;
					
					inclu ++;
				}
				
				for (var prop in excludeStoreNameMap) {
					if (useLimit != '') {
						useLimit += '，';
					}
					useLimit += prop;
					exclu ++;
				}
				
				
				if (useLimit == '') {
					useLimit = '【不限】';
				}else{
					if(inclu > 0){
						var desc = "";
						if(includeGroupType==<%=CouponGroupTypeConstants.STORE%>){
							desc = "   餐厅可用";
						}else if(includeGroupType==<%=CouponGroupTypeConstants.DISH%>){
							desc = "   美食可用";
						}
						
						useLimit += desc;
					}
					if(exclu > 0)
						useLimit += "   餐厅禁用";
				}
				detail['useLimit'] = useLimit;
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
