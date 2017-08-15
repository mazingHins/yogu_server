<%@ page import="com.mazing.core.constant.CouponBearTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponTypeConstants" %>
<%@ page import="com.mazing.core.constant.CouponGroupTypeConstants" %>
<%@ page import="com.mazing.core.enums.merchant.StoreStatus" %>
<%@ page import="com.mazing.core.web.ResultCode" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page import="com.mazing.core.constant.CouponUsingChannelConstants" %>
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
							<label class="col-md-2 control-label">使用渠道<span style="color:red;">*</span></label>
							<div class="col-md-3">
								<label>
									<input type="radio" name="usingChannel" value="<%=CouponUsingChannelConstants.NONE_LIMIT%>" onclick="showSelectDish()"/>
									通用
								</label>
								
								<label>
									<input type="radio" name="usingChannel"  onclick="showSelectDish()" value="<%=CouponUsingChannelConstants.ONLINE_ORDER%>"  checked />
									线上订餐
								</label>
								
								<label>
									<input type="radio" name="usingChannel" value="<%=CouponUsingChannelConstants.MAZING_PAY%>"  onclick="hideSelectDish();"/>
									Mazing Pay
								</label>
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
							<label class="col-md-2 control-label">我的列表是否显示券名</label>
							<div class="col-md-4">
								<label>
									<input type="radio" name="showName" value="<%=BooleanConstants.TRUE%>" onclick="showNameUrl()"/>
									是
								</label>
								<label>
									<input type="radio" name="showName" value="<%=BooleanConstants.FALSE%>"  checked onclick="hideNameUrl()"/>
									否
								</label>
							</div>
							
							<div class="col-md-3 text-red">
								是: 我的优惠券列表将会显示优惠券的名字，可能会占用之前其它的内容位置</br>
								否： 我的优惠券列表不显示优惠券名
							</div>
						</div>
						
						<div class="form-group"  style="display:none;" id="urlDiv">
							<label class="col-md-2 control-label">优惠券名超级链接 <span style="color:red;">*</span></label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="url"
									   name="url" title="优惠券名点击后的跳转链接" placeholder="券名超链接"
									   maxlength="256">
							</div>
							
							<div class="col-md-3 text-red">
								若配置了该链接，我的优惠券列表的 优惠券名字 将可点击并跳转至该链接地址
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
							<label class="col-md-2 control-label">有效时间动态</label>
							<div class="col-md-4">
								<label>
									<input type="radio" name="isDynamic" value="<%=BooleanConstants.TRUE%>" onclick="showEffectDays()"/>
									是
								</label>
								<label>
									<input type="radio" name="isDynamic" value="<%=BooleanConstants.FALSE%>"  checked onclick="hideEffectDays()"/>
									否
								</label>
							</div>
							
							<div class="col-md-3 text-red">
								是: 优惠券的有效时间将从领取的当天开始计时，N天后失效（N为设置的有效天数）</br>
								否： 优惠券的有效日期为固定日期
							</div>
						</div>
						
						
						<div class="form-group" style="display:none;" id="effectDaysDiv">
							<label class="col-md-2 control-label">优惠券有效天数 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<input class="form-control" type="text" id="effectDays" name="effectDays"
									   title="有效天数" placeholder="有效天数" maxlength="7" value="0">
							</div>
							<div class="col-md-3">
								天
							</div>
							<div class="col-md-3 text-red">
								‘有效时间动态’ 选择‘是’ 时 必填， 目前最大可设置为100天
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
							<label class="col-md-2 control-label">每天限用 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<select class="form-control" name="dayUseTotal">
									<option value="1" selected>1个</option>
									<option value="2">2个</option>
									<option value="3">3个</option>
									<option value="5">5个</option>
									<option value="7">7个</option>
									<option value="10">10个</option>
									<option value="50">50个</option>
								</select>
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">使用者</label>
							<div class="col-md-4">
								<label>
									<input type="checkbox" name="verifyRecePhone" value="1" onclick="checkTransfer()"/>
									收货人电话与领券人电话一致才可使用
								</label>
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
						<div class="form-group">
							<label class="col-md-2 control-label">可以转让</label>
							<div class="col-md-4">
								<label>
									<input type="radio" name="canTransfer" value="<%=BooleanConstants.TRUE%>" />
									是
								</label>
								<label>
									<input type="radio" name="canTransfer" value="<%=BooleanConstants.FALSE%>" checked/>
									否
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">成本承担方 <span style="color:red;">*</span></label>
							<div class="col-md-2 radio">
								<label>
									<input type="radio" name="couponBearType" onclick="choosePlatform()" value="<%=CouponBearTypeConstants.PLATFORM%>" checked/>
									平台
								</label>
								<label>
									<input type="radio" name="couponBearType"  value="<%=CouponBearTypeConstants.MERCHANT%>" />
									商家
								</label>
							</div>
							<div class="col-md-6 text-red">
								平台：平台承担优惠券成本；商家：商家承担优惠券成本！
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-2 control-label">限制级别 <span style="color:red;">*</span></label>
							<div class="col-md-2">
								<a class="btn btn-default" href="javascript:void(0)" data-toggle="modal" data-target="#storeModal" onclick="showStoreModal()">选择商家</a>
								<a class="btn btn-default" id="selectDish" href="javascript:void(0)" data-toggle="modal" data-target="#dishModal" onclick="showDishModal()">选择美食</a>
							</div>
							<div class="col-md-6 text-red">
								商家型优惠券必须选择限制级别中的一种，默认选中商家级别
							</div>
						</div>
						
						
					<!-- 	<div class="form-group">
							<label class="col-md-2 control-label">选择商家</label>
							<div class="col-md-2">
								<a class="btn btn-default" href="javascript:void(0)" data-toggle="modal" data-target="#storeModal" onclick="showStoreModal()">选择商家</a>
							</div>
							<div class="col-md-6 text-red">
								商家型优惠券必须选择商家
							</div>
						</div> -->
						
						<div class="form-group" id="selectedStores" style="display: none;">
							<label class="col-md-2 control-label">&nbsp;</label>
							<div class="col-md-8" id="selectedStoresDetail">未选择餐厅
							</div>
						</div>
						
						
						
					<!-- 	<div class="form-group">
							<label class="col-md-2 control-label">选择美食</label>
							<div class="col-md-2">
								<a class="btn btn-default" href="javascript:void(0)" data-toggle="modal" data-target="#dishModal" onclick="showDishModal()">选择美食</a>
							</div>
						</div> -->
						
						
						<div class="form-group" id="selectedDishs" style="display: none;">
							<label class="col-md-2 control-label">&nbsp;</label>
							<div class="col-md-8" id="selectedDishsDetail">未选择美食
							</div>
						</div>
						
						<div class="form-group">
								<label class="col-md-2 control-label">添加黑名单</label>
								<div class="col-md-4">
									<label> <input type="radio"  name="addBlack"
										value="<%=BooleanConstants.TRUE%>" onclick="showAddBlack()" />
										是
									</label> <label> <input type="radio"  name="addBlack"
										value="<%=BooleanConstants.FALSE%>" checked
										onclick="hideAddBlack()" /> 否
									</label>
								</div>
								<div class="col-md-6 text-red">
									商家型的优惠券目前不可添加黑名单
								</div>
						</div>
					
						
							<input type="text" name="excludeMembersStr" id="excludeMembersStr"
								style="display: none;" />

							<div class="box-body" id="couponBlacklistDiv">
								<div class="row">
									<div class="col-sm-12" id="couponBlacklist"></div>
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

			<!-- 商家列表Modal -->
			<div class="modal fade" id="storeModal" tabindex="-1" role="dialog" aria-labelledby="storeModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="storeModalLabel">选择餐厅</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-md-12">
									<div class="input-group input-group-sm">
										<input type="text" id="storeKeyword" name="storeKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="searchStore()">Go!</button>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<table id="storeTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th><a href="javascript:void(0)" onclick="selectCurrentPageStore()">√ 本页全选</a></th>
											<th>餐厅名称</th>
											<th>餐厅评分</th>
											<th>状态</th>
										</tr>
										</thead>
										<script id="storeTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.storeId}}
													<input type="hidden" name="modalStoreId" value="{{value.storeId}}"/>
													<input type="hidden" name="modalStoreName" value="{{escapeStoreName value.storeName}}"/>
												</td>
												<td>
													<a href="javascript:void(0)" onclick="selectStore({{value.storeId}}, '{{escapeStoreName value.storeName}}')" id="storeRow_{{value.storeId}}">{{renderStoreName value.storeName}}</a>
												</td>

												<td>
													{{renderStoreStar value.star}}
												</td>
												<td>
													{{if value.status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
													<span style="color:green;">营业中</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.IN_REST.getValue()%>}}
													<span style="color:#808080;">休业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.CLOSED.getValue()%>}}
													<span style="color: #c9302c;">结业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.CHECKING.getValue()%>}}
													<span>审核中</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.COMING_SOON.getValue()%>}}
													<span style="color:blue;">即将开业</span>
													{{/if}}
													{{if value.status == <%=StoreStatus.FROST.getValue()%>}}
													<span style="color:red;">封号</span>
													{{/if}}
												</td>
											</tr>
											{{/each}}
										</script>
									</table>
								</div>
							</div>
							<div class="row" id="storePaginator">
							</div>
							<div class="row col-sm-12" id="selectedStore">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearSelectedStore()">清空</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
						</div>
					</div>
				</div>
			</div>
			
			
					<!-- 美食列表Modal -->
		<div class="modal fade" id="dishModal" tabindex="-1" role="dialog" aria-labelledby="dishModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="dishModalLabel">选择美食</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input id="dishKeyword" name="dishKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="searchDish();">Go!</button>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<table id="dishTable" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>餐厅名称</th>
											<th>美食名称</th>
										</tr>
									</thead>
									<script id="dishTableTpl" type="text/html">
										{{each object as value i}}
										<tr>
											<td>
												{{value.storeName}}
												<input type="hidden" name="modalDishId" value="{{value.dishKey}}"/>
												<input type="hidden" name="modalDishName" value="{{value.dishName}}"/>
											</td>
											<td>
					<a href="javascript:void(0)" onclick="selectDish({{value.dishKey}}, '{{value.dishName}}')" id="dishRow_{{value.dishKey}}">{{value.dishName}}</a>
											</td>
											<input id="dish_price_{{value.dishKey}}" type="hidden" value="{{value.price}}" />
											<input id="dish_name_{{value.dishKey}}" type="hidden" value="{{value.dishName}}" />
											<input id="dish_cardImg_{{value.dishKey}}" type="hidden" value="{{value.cardImg}}" />
											<input id="dish_originalPrice_{{value.dishKey}}" type="hidden" value="{{value.originalPrice}}" />
											<input id="dish_specialContent_{{value.dishKey}}" type="hidden" value="{{value.specialContent}}" />
										</tr>
										{{/each}}
									</script>
								</table>
							</div>
						</div>
						<div class="row" id="dishPaginator"></div>
						<div class="row col-sm-12" id="selectedDish">
							</div>
					</div>
					<div class="modal-footer">
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
	<!-- 黑名单的模版 -->
<script id="couponBlacklistTemplate" type="text/html">
		<div class="row margin" style="border-bottom: 1px solid #ddd;">
				<div class="col-xs-9 checkbox text-red ">
							{{each object as value i}}
								<label><input type="checkbox" name="blackRecord" value="{{value.storeId}}" checked>{{value.storeName}}</label> &nbsp; &nbsp; &nbsp; &nbsp;
							{{/each}}
				</div>
		</div>
</script>
<script type="text/javascript">

	// 当优惠券为餐厅类型，选择餐厅的时候，被选中的餐厅的ID
	// key=id, value=name
	var includeStoreIdMap = {};
	// key=name, value="1"
	var includeStoreNameMap = {};
	
	//当优惠券 包含群组为菜品时,被选中的菜品dishKey 容器
	var includeDishIdMap = {};
	var includeDishNameMap = {};
	
	
	//显示黑名单复选框
	function showAddBlack(){
		loadCouponBlacklist();
		$('#couponBlacklistDiv').show();
		
	}
	// 隐藏黑名单复选框
	function hideAddBlack(){
		$('#couponBlacklistDiv').hide();
		
	}
	// 查询所有黑名单列表
	function loadCouponBlacklist() {
		// 注意：这里可以使用一个参数 groupCode
		var groupCode = '';
		$.getJSON("/admin/coupon/queryCouponBlacklist", {}, function(json) {
			if (json.success) {
				var htmlTxt = template('couponBlacklistTemplate', json);
				$('#couponBlacklist').html(htmlTxt);
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}
	//显示券名跳转url 输入框
	function showNameUrl(){
		$("#urlDiv").show();
	}
	//隐藏券名跳转url 输入框
	function hideNameUrl(){
		$("#urlDiv").hide();
	}
	
	//隐藏限制级别 中的 选择美食
	function hideSelectDish(){
		$("#selectDish").hide();
	}
	//显示限制级别 中的 选择美食
	function showSelectDish(){
		$("#selectDish").show();
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

	// 展示餐厅modal
	function showStoreModal() {
		searchStore();
		$('#selectedStores').show();
	}
	
	// 展示餐厅modal
	function showDishModal() {
		searchDish();
		$('#selectedDishs').show();
	}

	// 选择了平台型
	function choosePlatform() {
		$('#selectedStores').hide();
	}

	// 如果选择了[收货人电话与领券人电话一致才可使用]，那么优惠券不可以转让
	function checkTransfer() {
		var verifyRecePhone = $('input[name=verifyRecePhone]').prop('checked');
		console.log('verifyRecePhone=' + verifyRecePhone);
		var canTransfer = $('input[name=canTransfer]:checked').val();
		if (verifyRecePhone && parseInt(canTransfer) == 1) {
			MyDialog.alert('如果选择了【收货人电话与领券人电话一致才可使用】，那么优惠券不可以转让')
			return false;
		}
		return true;
	}

	//
	// 餐厅搜索
	//
	function searchStore() {
		var keyword = $('#storeKeyword').val();
		$('#storeTable').artPaginate({
			// 获取数据的地址
			url: "/admin/store/query",
			// 显示页码的位置
			paginator: 'storePaginator',
			// 模版ID
			tpl: 'storeTableTpl',
			// 请求的参数表，
			params: {'keyword': keyword, 'excludeFrost':'true'},
			// 默认page=1, pageSize=20
			pageSize : 10
		});
	}
	
	//
	//美食搜索
	//
	function searchDish() {
		var keyword = $('#dishKeyword').val();
		$('#dishTable').artPaginate({
			type : 'post',
			url : "/admin/dish/query",// 获取数据的地址
			paginator : 'dishPaginator',// 显示页码的位置
			tpl : 'dishTableTpl',// 模版ID
			params : { 'keyword' : keyword },// 请求的参数表
			pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
		});
	}

	// 清除选择的store
	function clearSelectedStore() {
		includeStoreIdMap = {};
		includeStoreNameMap = {};
		$('#selectedStore').html('未选择餐厅');
		$('#selectedStoresDetail').html('未选择餐厅');
	}
	
	// 清除选择的dish
	function clearSelectedDish() {
		includeDishIdMap = {};
		includeDishNameMap = {};
		$('#selectedDish').html('未选择美食');
		$('#selectedDishsDetail').html('未选择美食');
	}

	// 选择一家餐厅
	function selectStore(id, name) {
		var rowId = '#storeRow_' + id;
		if (includeStoreIdMap[id]) {
			// 反选
			delete includeStoreIdMap[id];
			delete includeStoreNameMap[name];
			$(rowId).html(name);
		}
		else {
			includeStoreIdMap[id] = name;
			includeStoreNameMap[name] = "1";
			$(rowId).html('<span style="color: green;">√<span> &nbsp; ' + name);
		}
		echoSelectedStore();
		
		
		if(Object.keys(includeStoreIdMap).length > 0){//如果选择了限制餐厅， 那么限制菜品 就要清除
			clearSelectedDish();
		}
	}
	
	// 选择一个美食
	function selectDish(id, name) {
		var rowId = '#dishRow_' + id;
		if (includeDishIdMap[id]) {
			// 反选
			delete includeDishIdMap[id];
			delete includeDishNameMap[name];
			$(rowId).html(name);
		}
		else {
			includeDishIdMap[id] = name;
			includeDishNameMap[name] = "1";
			$(rowId).html('<span style="color: green;">√<span> &nbsp; ' + name);
		}
		echoSelectedDish();
		
		if(Object.keys(includeDishIdMap).length > 0){//如果选择了限制菜品， 那么限制餐厅 就要清除
			clearSelectedStore();
		}
	}

	// 选择当前页的store
	function selectCurrentPageStore() {
		var modalStoreId = $('input[name=modalStoreId]');
		var modalStoreName = $('input[name=modalStoreName]');
		if (modalStoreId.length > 0) {
			var pageChecked = isCurrentPageAllStoreChecked();
			for (var i=0; i < modalStoreId.length; i++) {
				var id = $(modalStoreId[i]).val();
				if (pageChecked) {
					// 反选
					selectStore(id, $(modalStoreName[i]).val());
				}
				else if (!includeStoreIdMap[id]) {
					// 选中整页
					selectStore(id, $(modalStoreName[i]).val());
				}
			}
		}
	}
	
	// 选择当前页的dish
	function selectCurrentPageDish() {
		var modalDishId = $('input[name=modalDishId]');
		var modalDishName = $('input[name=modalDishName]');
		if (modalDishId.length > 0) {
			var pageChecked = isCurrentPageAllDishChecked();
			for (var i=0; i < modalDishId.length; i++) {
				var id = $(modalDishId[i]).val();
				if (pageChecked) {
					// 反选
					selectDish(id, $(modalDishName[i]).val());
				}
				else if (!includeDishIdMap[id]) {
					// 选中整页
					selectDish(id, $(modalDishName[i]).val());
				}
			}
		}
	}

	// 当前页所有的餐厅是否已经选择
	function isCurrentPageAllStoreChecked() {
		var modalStoreId = $('input[name=modalStoreId]');
		if (modalStoreId.length > 0) {
			for (var i = 0; i < modalStoreId.length; i++) {
				var id = $(modalStoreId[i]).val();
				if (!includeStoreIdMap[id]) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 当前页所有的美食是否已经选择
	function isCurrentPageAllDishChecked() {
		var modalDishId = $('input[name=modalDishId]');
		if (modalDishId.length > 0) {
			for (var i = 0; i < modalDishId.length; i++) {
				var id = $(modalDishId[i]).val();
				if (!includeDishIdMap[id]) {
					return false;
				}
			}
		}
		return true;
	}

	// 显示已经选中的餐厅名称
	function echoSelectedStore() {
		// 组装餐厅名列表
		var includeStoreNames = '';
		for (var name in includeStoreNameMap) {
			if (includeStoreNames == '') {
				includeStoreNames = name;
			}
			else {
				includeStoreNames += '，' + name;
			}
		}
		var htmlTxt = '已选中 <span style="color:red;">' + Object.keys(includeStoreNameMap).length + '</span> 家餐厅：' + includeStoreNames;
		$('#selectedStore').html(htmlTxt);
		$('#selectedStoresDetail').html(htmlTxt);
	}
	
	// 显示已经选中的餐厅名称
	function echoSelectedDish() {
		// 组装餐厅名列表
		var includeDishNames = '';
		for (var name in includeDishNameMap) {
			if (includeDishNames == '') {
				includeDishNames = name;
			}
			else {
				includeDishNames += '，' + name;
			}
		}
		var htmlTxt = '已选中 <span style="color:red;">' + Object.keys(includeDishNameMap).length + '</span> 个美食：' + includeDishNames;
		$('#selectedDish').html(htmlTxt);
		$('#selectedDishsDetail').html(htmlTxt);
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
		

		// 模版函数：输出餐厅名，如果餐厅已经选中，要打勾
		template.helper('renderStoreName', function (name) {
			if (typeof includeStoreNameMap[name] == 'string') {
				return '<span style="color: green;">√<span> &nbsp; ' + name;
			}
			return name;
		});

		// 模版函数：输出 escape 单引号、双引号后的结果
		template.helper('escapeStoreName', function (name) {
			var regExp = new RegExp("'", "g");
			var tmp = name.replace(regExp, "\\'");
			regExp = new RegExp("\"", "g");
			tmp = tmp.replace(regExp, "\\\"");
			return tmp;
		});

		// 模版函数：输出餐厅的星级
		template.helper('renderStoreStar', function (star) {
			var tmp = star / 10;
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
		var couponBearType = parseInt($('input[name=couponBearType]:checked').val());
		var addBlack = parseInt($('input[name=addBlack]:checked').val());
		
		//动态优惠券时间
		var isDynamic = parseInt($('input[name=isDynamic]:checked').val());
		
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
		//else if (couponType == <%=CouponTypeConstants.CASH_COUPON%> && parseFloat(enoughMoneyStr) < parseFloat(regExpression)) {
		//	message = '可用订单金额不能小于面值。';
		//}
		else if (effectiveTimeRange == '') {
			message = '请选择优惠券的生效时间'
		}
		else if (couponBearType == <%=CouponBearTypeConstants.MERCHANT%> && (Object.keys(includeStoreIdMap).length <= 0 && Object.keys(includeDishIdMap).length <= 0)) {
			message = '商家型优惠券必须选择至少一个餐厅';
		}
		else if(isDynamic == <%=BooleanConstants.TRUE%> ){
			if(!$.isNumeric(effectDays) || effectDays < 1 || effectDays > 100)
				message = '请输入正确的有效天数(1~100的整数)';
			
			if(canTransfer ==  <%=BooleanConstants.TRUE%>)
				message = '动态时间的优惠券不可转让, 请重新设置';
		}
		else if(couponBearType == <%=CouponBearTypeConstants.MERCHANT%> && addBlack==1){
			message = '商家型的优惠券不需要选择黑名单';
			
		}else if(couponType == <%=CouponTypeConstants.DISCOUNT_COUPON%> && (!$.isNumeric(mostOffer) || mostOffer < 1 || mostOffer > 150000)){
			
			message = '折扣券必须设置最高优惠金额值， 且为大于1~150000的整数';
		}
		
		if (message != '') {
			MyDialog.alert(message);
		}
		else {
			//包含群组， 成员id串
			var includeMembersStr = "";
			
			if(Object.keys(includeStoreIdMap).length > 0){
				includeMembersStr = Object.keys(includeStoreIdMap).join(',');
				$('#includeGroupType').val(<%=CouponGroupTypeConstants.STORE%>);//包含群组成员类型
			}else if(Object.keys(includeDishIdMap).length > 0){
				includeMembersStr = Object.keys(includeDishIdMap).join(',');
				$('#includeGroupType').val(<%=CouponGroupTypeConstants.DISH%>);
			}
			
			
			$('#includeMembersStr').val(includeMembersStr);
			
			if(couponBearType== <%=CouponBearTypeConstants.PLATFORM%>){
				//黑名单商家ids
				var values = new Array();
				var blackList = $('input[name=blackRecord]:checked').each(function(i, item) {
					values.push(item.value);
				});
				if (values.length > 0) {
					var blackIds = values.join(',');
					console.log('blackIds: ' + blackIds)
					$('#excludeMembersStr').val(blackIds);
				}
			}
			
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
