<%@ page import="com.yogu.core.constant.CouponTypeConstants" %>
<%@ page import="com.yogu.core.web.ResultCode" %>
<%@ page import="com.yogu.core.enums.BooleanConstants" %>
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
				新增优惠券
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/coupon/list.xhtm"><i class="fa fa-dashboard"></i> 返回优惠券列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）编辑优惠券的过程中，请不要离开界面；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form id="editForm" class="form-horizontal" method="post" action="/admin/coupon/saveCoupon.do" onsubmit="return validate()">
						<div class="form-group">
							<label class="col-md-2 control-label">优惠券类型 <span style="color:red;">*</span></label>
							<div class="col-md-3">
								<select name="couponType" class="form-control" onchange="changeCouponTypeInfo()">
									<option value="<%=CouponTypeConstants.CASH_COUPON%>" selected>代金券</option>
									<option value="<%=CouponTypeConstants.DISCOUNT_COUPON%>">折扣券</option>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">优惠券名称 <span style="color:red;">*</span></label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="couponName"
									   name="couponName" title="优惠券名称" placeholder="12个中文或24个英文"
									   maxlength="24">
								<input name="couponRuleId" id="couponRuleId" type="hidden" value="0"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">发放总量 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<input class="form-control" type="text" id="createTotal" name="createTotal"
									   title="发放总量" placeholder="发放总量" maxlength="7">
							</div>
							<div class="col-md-3">
								个
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">单张面值 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<input class="form-control" type="text" id="regExpression" name="regExpression"
									   title="单张面值" placeholder="优惠券面值，必须大于0" maxlength="8">
							</div>
							<div class="col-md-8 text-red" id="faceValueInfo">
								元
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">可用订单金额 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<input class="form-control" type="text" id="enoughMoneyStr" name="enoughMoneyStr"
									   title="可用订单金额" placeholder="达到指定金额的订单才能使用，必须大于0" maxlength="8">
							</div>
							<div class="col-md-3 text-red">
								元
							</div>
						</div>
						
						<div class="form-group" style="display:none;" id="mostOfferDiv" >
							<label class="col-md-2 control-label">最高优惠金额 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<input class="form-control" type="text" id="mostOfferStr" name="mostOffer"
									   title="减免金额上限" placeholder="折扣券最大减免金额，必须大于0" maxlength="8" value="0">
							</div>
							<div class="col-md-3 text-red">
								元
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label" id="effectTimeLabel">生效时间 <span style="color:red;">*</span></label>
							<label class="col-md-2 control-label" id="obtainTimeLabel" style="display:none;">有效领取时间段 <span style="color:red;">*</span></label>
							
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
							<label class="col-md-2 control-label">每人限领 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<select class="form-control" name="gainTotal">
									<option value="1" selected>1个</option>
									<option value="2">2个</option>
									<option value="3">3个</option>
									<option value="5">5个</option>
									<option value="7">7个</option>
									<option value="10">10个</option>
									<option value="50">50个</option>
									<option value="100">100个</option>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">使用说明</label>
							<div class="col-md-6">
								<textarea class="form-control" name="description" placeholder="每行一条规则说明" rows="4"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-2">
								<input type="hidden" name="includeMembersStr" id="includeMembersStr" value=""/>
								<input type="hidden" name="includeGroupType" id="includeGroupType" value="0"/>
								<button class="btn btn-primary"
										type="submit" id="submit" >提交</button>
							</div>
						</div>
					</form>
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

	
	//显示券名跳转url 输入框
	function showNameUrl(){
		$("#urlDiv").show();
	}
	//隐藏券名跳转url 输入框
	function hideNameUrl(){
		$("#urlDiv").hide();
	}
	
	//显示优惠券有效天数  输入框
	function showEffectDays(){
		$("#effectDaysDiv").show();
		$("#obtainTimeLabel").show();
		$("#effectTimeLabel").hide();
	}
	//隐藏 优惠券有效天数 输入框
	function hideEffectDays(){
		$("#effectTimeLabel").show();
		$("#obtainTimeLabel").hide();
		$("#effectDaysDiv").hide();
	}
	
	// 修改在编辑功能下的title
	function modifyTitle() {
		var newTitle = '编辑优惠券';
		$('#titleMessage').html(newTitle)
		document.title = newTitle;
	}

	// 返回正在编辑的 couponRuleId
	function getCouponRuleId() {
		var couponRuleId = $.getUrlParam("couponRuleId");
		if (typeof couponRuleId == 'undefined' || couponRuleId == null || couponRuleId == '') {
			couponRuleId = '0';
		}
		return couponRuleId;
	}

	// 填充值
	function fillForm(coupon) {

	}

	// 加载优惠券数据
	function loadCoupon(couponRuleId) {
		$.getJSON('/admin/coupon/getCouponRuleDetail', {
			'couponRuleId' : couponRuleId
		}, function(json) {
			if (json.success) {
				fillForm(json.object);
			} else {
				BootstrapDialog.show({
					title: '错误',
					message: json.message,
					buttons: [{
						label: '确定',
						action: function (dialog) {
							dialog.close();
							if (json.code == <%=ResultCode.REJECTED_OPERATION%>) {
								window.location.href = '/admin/coupon/list.xhtm';
							}
						}
					}]
				});
			}
		});
	}

	// 是否在提交数据，不允许重复提交
	var submittingData = false;

	$(function() {
		/*
		// 不支持编辑优惠券
		var couponRuleId = getCouponRuleId();
		if (couponRuleId != '0') {
			modifyTitle();
			loadCoupon(couponRuleId);
		}
		$('#couponRuleId').val(couponRuleId);
		*/
		changeCouponTypeInfo();
		
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
							window.location.href = "/admin/coupon/list.xhtm";
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

	// 根据优惠券说明修改说明文字
	function changeCouponTypeInfo() {
		var couponType = $('select[name=couponType]').val();
		if (couponType == <%=CouponTypeConstants.CASH_COUPON%>) {
			$('#faceValueInfo').html('单位：元，0.01~99999');
			
			$('#mostOfferDiv').hide();
		}
		else if (couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>) {
			$('#faceValueInfo').html('折扣范围：10~99，表示1折到99折，75表示七五折，假设商品价格为1000元时，1折=100元即可购买');
			
			//减免上限
			$('#mostOfferDiv').show();
		}
		
		
	}

	// 判断是否为正确的面值
	function isValidFaceValue(couponType, regExpression) {
		if (couponType == <%=CouponTypeConstants.CASH_COUPON%>) {
			if (!$.isNumeric(regExpression) || regExpression < 0.01 || regExpression > 99999) {
				return {'success' : false, 'message': '请输入正确的面值，单位：元（0.01~99999）。'};
			}
		}
		else if (couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%>) {
			if (!$.isNumeric(regExpression) || regExpression < 10 || regExpression > 99) {
				return {'success' : false, 'message': '请输入正确的折扣（10~99）。'};
			}
		}
		return {'success' : true, 'message' : ''};
	}

	// 校验参数
	function validate() {
		if (submittingData) {
			MyDialog.alert('正在提交数据，请勿重复提交。');
			return false;
		}
		if (!checkTransfer()) {
			return false;
		}
		var message = '';
		var couponType = $('select[name=couponType]').val();
		console.log('couponType=' + couponType);
		var couponName = $.trim($('#couponName').val());
		var regExpression = $.trim($('#regExpression').val());
		var createTotal = $.trim($('#createTotal').val());
		var enoughMoneyStr = $.trim($('#enoughMoneyStr').val());
		var effectiveTimeRange = $.trim($('#effectiveTimeRange').val());
		
		var effectDays = $.trim($('#effectDays').val());
		
		if(!effectDays)
			$('#effectDays').val("0");
		
		//减免上限金额
		var mostOffer = $.trim($('#mostOfferStr').val());
		
		var canTransfer = parseInt($('input[name=canTransfer]:checked').val());
		
		
		var validFaceValue = isValidFaceValue(couponType, regExpression);

		if (couponName == '') {
			message = '请输入优惠券名称。';
		} else if (!validFaceValue.success) {
			message = validFaceValue.message;
		}
		else if (!$.isNumeric(createTotal) || createTotal < 1 || createTotal > 1000000) {
			message = '请输入正确的发放总量（1~1000000的整数）';
		}
		else if (!$.isNumeric(enoughMoneyStr) || enoughMoneyStr < 0.01 || enoughMoneyStr > 999999) {
			message = '请输入正确的可用订单金额，单位：元（0.01~999999）';
		}
		else if (effectiveTimeRange == '') {
			message = '请选择优惠券的生效时间'
		}
		
		if (message != '') {
			MyDialog.alert(message);
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
