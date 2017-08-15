<%@ page import="com.mazing.core.constant.CouponBearTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponTypeConstants" %>
<%@ page import="com.mazing.core.enums.merchant.StoreStatus" %>
<%@ page import="com.mazing.core.web.ResultCode" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>新增/编辑优惠券</title>
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
			<h1 id="titleMessage">
				新增卡包
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/coupon/listCouponBag.xhtm"><i class="fa fa-dashboard"></i> 返回卡包列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）编辑卡包的过程中，请不要离开界面；</p>
					<p class="text-red">（2）一种优惠券只能进入一个卡包，进入卡包后优惠券将被强制不能被分享，可以转让（如果设置了允许转让）。</p>
					<p class="text-left">（3）优惠券一旦进入卡包，永远不能被释放。停止发放卡包后，优惠券就只能作废了。</p>
					<p class="text-left">（4）卡包一旦生成，不能被修改。</p>
					<p class="text-left">（5）每个人只能领取一个卡包。</p>
					<p class="text-red">（6）进入卡包的优惠券，数量、有效期必须一致。</p>
					<p class="text-red">（7）卡包中的券，只能是【代金券】类型。</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" action="/admin/coupon/saveCouponBag.do" onsubmit="return validate()">

						<div class="form-group">
							<label class="col-md-2 control-label">卡包名称 <span style="color:red;">*</span></label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="bagName"
									   name="bagName" title="卡包名称" placeholder="最长32个字符"
									   maxlength="24">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">可以领取的时间范围 <span style="color:red;">*</span></label>
							<div class="col-md-4">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control pull-right active" name="effectiveTimeRange" id="effectiveTimeRange">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">可以分享</label>
							<div class="col-md-4">
								<label>
									<input type="radio" name="canShare" value="<%=BooleanConstants.TRUE%>" checked/>
									是
								</label>
								<label>
									<input type="radio" name="canShare" value="<%=BooleanConstants.FALSE%>" />
									否
								</label>
							</div>
						</div>
						<div class="form-group" id="selectedItems">
							<label class="col-md-2 control-label"><a href="javascript:void(0)" onclick="showCouponModal()" data-toggle="modal" data-target="#couponModal">选择优惠券</a></label>
							<div class="col-md-8" id="selectedItemsDetail">未选择优惠券
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<input type="hidden" name="couponRuleIdStr" id="couponRuleIdStr" value=""/>
								<button class="btn btn-primary"
										type="submit" id="submit" >提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- 商家列表Modal -->
			<div class="modal fade" id="couponModal" tabindex="-1" role="dialog" aria-labelledby="couponModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="couponModalLabel">选择优惠券</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="input-group input-group-sm">
										<input type="text" id="storeKeyword" name="storeKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="searchCoupon()">查询</button>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<table id="storeTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID/类型</th>
											<th>优惠券名称</th>
											<th>创建时间</th>
										</tr>
										</thead>
										<script id="storeTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.couponRuleId}}&nbsp;/&nbsp;{{value.couponType}}
													<input type="hidden" name="modalCouponRuleId" value="{{value.couponRuleId}}"/>
													<input type="hidden" name="modalCouponName" value="{{escapeCouponName value.couponName}}"/>
												</td>
												<td>
													<a href="javascript: selectCoupon({{value.couponRuleId}}, {{value.couponType}}, '{{escapeCouponName value.couponName}}');" ruleId="{{value.couponRuleId}}">{{value.couponName}}</a>
												</td>

												<td>
													{{formatDateTime value.createTime}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>
								</div>
							</div>
							<div class="row" id="couponPaginator">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12" style="left: 25px;" id="selectedCoupon">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearSelectedCoupon()">清空</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
						</div>
					</div>
				</div>
			</div>
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

	// 被选中的优惠券的ID
	// key=id, value=name
	var includeCouponIdMap = {};

	// 展示优惠券modal
	function showCouponModal() {
		searchCoupon();
	}

	//
	// 优惠券搜索
	//
	function searchCoupon() {
		var keyword = $('#storeKeyword').val();
		$('#storeTable').artPaginate({
			// 获取数据的地址
			url: "/admin/coupon/queryCouponRulesForBag",
			// 显示页码的位置
			paginator: 'couponPaginator',
			// 模版ID
			tpl: 'storeTableTpl',
			// 请求的参数表，
			params: {'keyword': keyword},
			// 默认page=1, pageSize=20
			pageSize : 10,
			success : tableCallback
		});
	}

	// 渲染完table之后的回调，主要给以选中的券打勾
	function tableCallback(json) {
		$("#storeTable a[ruleId != '']").each(function(i, d) {
			var dom = $(d);
			var id = dom.attr('ruleId');
			if(includeCouponIdMap[id]){
				var name = dom.html();
				dom.html('<span style="color: green;">√<span> &nbsp; ' + name);
			}
		});
	}

	// 清除选择的store
	function clearSelectedCoupon() {
		includeCouponIdMap = {};
		$('#selectedCoupon').html('未选择优惠券');
		$('#selectedItemsDetail').html('未选择优惠券');
	}

	// 选择一种优惠券
	function selectCoupon(id, type, name) {
		//if(1 != type) {
		//	alert('【'+name+'】不是代金券，券包只能添加“代金券”');
		//	return;
		//}
		if (includeCouponIdMap[id]) {
			// 反选
			delete includeCouponIdMap[id];
			$("#storeTable a[ruleId='"+id+"']").html(name);
		}
		else {
			includeCouponIdMap[id] = name;
			$("#storeTable a[ruleId='"+id+"']").html('<span style="color: green;">√<span> &nbsp; ' + name);
		}
		echoSelectedCoupon();
	}

	// 显示已经选中的优惠券名称
	function echoSelectedCoupon() {
		// 组装优惠券名列表
		var includeCouponNames = '', length = 0;
		for (var id in includeCouponIdMap) {
			var name = includeCouponIdMap[id];
			name = ('('+id+')&nbsp;'+name);
			
			if (0 < length) includeCouponNames += '<br/>';
			includeCouponNames += name;
			length += 1;
		}
		var htmlTxt = '<p><h4>已选中 <span style="color:red;">' + length + '</span> 种优惠券：</h4>' + includeCouponNames + '</p>';
		$('#selectedCoupon').html(htmlTxt);
		$('#selectedItemsDetail').html(htmlTxt);
	}

	// 是否在提交数据，不允许重复提交
	var submittingData = false;

	$(function() {
		// 模版函数：输出 escape 单引号、双引号后的结果
		template.helper('escapeCouponName', function (name) {
			var regExp = new RegExp("'", "g");
			var tmp = name.replace(regExp, "\\'");
			regExp = new RegExp("\"", "g");
			tmp = tmp.replace(regExp, "\\\"");
			return tmp;
		});

		// 选择时间范围控件
		$('#effectiveTimeRange').daterangepicker({
			timePicker: true, timePickerIncrement: 1, format: 'YYYY-MM-DD HH:mm',
			timePicker12Hour: false,
			locale: { cancelLabel: '取消', applyLabel: '确定', fromLabel:'开始日期', toLabel:'结束日期' }
		});

		$('#editForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					submittingData = false;
					if (json.success) {
						MyDialog.alert(json.message, function() {
							window.location.href = "/admin/coupon/listCouponBag.xhtm";
						});
					}
					else {
						MyDialog.alert(json.message);
					}

				} catch (e) {
				}
			}
		});
	});

	// 校验参数
	function validate() {
		if (submittingData) {
			MyDialog.alert('正在提交数据，请勿重复提交。');
			return;
		}
		var message = '';
		var bagName = $.trim($('#bagName').val());
		var effectiveTimeRange = $.trim($('#effectiveTimeRange').val());
		var couponBearType = parseInt($('input[name=couponBearType]:checked').val());

		if (bagName == '') {
			message = '请输入卡包名称。';
		}
		else if (effectiveTimeRange == '') {
			message = '请选择卡包的可以领取时间范围'
		}
		else if (Object.keys(includeCouponIdMap).length <= 0) {
			message = '卡包至少要选择一种优惠券'
		}

		if (message != '') {
			MyDialog.alert(message);
		}
		else {
			var couponRuleIdStr = Object.keys(includeCouponIdMap).join(',');
			$('#couponRuleIdStr').val(couponRuleIdStr);
		}
		submittingData = (message == '');
		if (submittingData) {
			window.setTimeout(clearSubmittingData, 10000);
		}
		return submittingData;
	}

	function clearSubmittingData() {
		submittingData = false;
	}

</script>
</body>
</html>
