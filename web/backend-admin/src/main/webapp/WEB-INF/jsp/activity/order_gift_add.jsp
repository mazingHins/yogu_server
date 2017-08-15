<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.core.constant.OrderGiftTypeConstants"%>
<%@ page import="com.mazing.core.enums.pay.PayType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<title>赠礼编辑</title>
</head>
<style>
.all {
	height: 54px;
	border-bottom: 1px solid #ccc;
}

.select {
	width: 264px;
	float: left;
	margin: 0;
	padding: 0;
}

.n-sel {
	width: 114px;
	height: auto;
}

.one, .two, .three, .four, .five, .img-wrap {
	display: inline-block;
}

.sel {
	width: 130px;
	text-align: left;
}

.sel a {
	width: 74px;
	float: left;
}

.scn {
	display: inline-block;
	margin-top: 15px;
	margin-right: 30px;
}

.sub, .add {
	float: right;
	margin-right: 6px;
	margin-top: 6px;
}

/* .one {
	width: 130px;
}
 */
.s2 {
	margin-left: 62px;
}

.s3 {
	margin-left: 163px;
}

.s4 {
	margin-left: 210px;
}

.s5 {
	margin-left: 260px;
}

@media screen and (max-width: 1080px) {
	.one {
		width: 100px
	}
	.s5 {
		display: none;
	}
}
</style>
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
				<h1 id="titleMessage">
					管理数据项 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="row">
								<div class="col-sm-12">
									<p class="text-left">说明：</p>
									<p class="text-left">1. 赠礼的 有效日期 不能与已有的时间 发生重叠， 不能是过去的日期</p>
									<p class="text-left" style="color: red;">2. 赠礼类型若为优惠券，请注意了，
										该优惠券一定要设置为【不可分享】</p>
									<p class="text-left" style="color: red;">3.
										一个用户下单能得到该优惠券几次，完全由优惠券本身控制</p>
									<p class="text-left" style="color: red;">4.
										若类型是【卡包】, 请注意： 卡包规则本身限制了 一个人只能领取一次该卡包，不能领取多次</p>	
									<p class="text-left" style="color: red;">5.
										如果设置了链接可分享，则链接js中一定要包含getShareData方法，否则将获取不到分享的标题，内容，图片</p>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post"
											action="#" onsubmit="return false;">
											
											<div class="form-group">
												<label class="col-md-2 control-label">订餐类型<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<select id="payType" name="payType" class="form-control" >
														<option value="<%=PayType.ONLINE.getValue()%>">线上订餐</option>
														<option value="<%=PayType.MAZING_PAY.getValue()%>">Mazing Pay</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">数据项类型<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<select id="typeID" name="giftType" class="form-control"
														onchange="selectGiftType(this.options[this.options.selectedIndex].value)">
														<option value="<%=OrderGiftTypeConstants.coupon%>">优惠券</option>
														<option value="<%=OrderGiftTypeConstants.couponBag%>">卡包</option>
														<%-- <option value="<%=OrderGiftTypeConstants.h5URL%>">活动链接</option> --%>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">红包名称<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="descripID" class="form-control" name="descrip"
														placeholder="请输入红包名称">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">订单最低金额 <span style="color:red;">*</span></label>
												<div class="col-md-2">
													<input class="form-control" type="text" id="enoughMoney" name="enoughMoney"
														    placeholder="该活动订单最低应该满足的金额" maxlength="8">
												</div>
												<div class="col-md-8 text-red" id="enoughMoneyInfo">
													元
												</div>
											</div>

											<div class="form-group">
												<label class="col-md-2 control-label" id="effectDateLabel">有效日期
													<span style="color: red;">*</span>
												</label> <label class="col-md-2 control-label" id="obtainDateLabel"
													style="display: none;">有效领取日期段 <span
													style="color: red;">*</span></label>

												<div class="col-md-4">
													<div class="input-group">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<input type="text" class="form-control pull-right active"
															name="effectiveDateRange" id="effectiveDateRange">
													</div>
												</div>
											</div>


											<div class="form-group">
												<label class="col-md-2 control-label">生效时间<span
													style="color: red;">*</span></label>
												<div class="col-md-3">

													<div class="row" id="vote_1">
														<div class="col-xs-4">
															<input id="activeStartMinutePreviewID1" type='text'
																name="timePeriodStart" class="form-control input-sm"
																readonly="readonly" />
														</div>
														<div class="col-xs-1">~</div>
														<div class="col-xs-4">
															<input id="activeEndMinutePreviewID1" type='text'
																name="timePeriodEnd" class="form-control input-sm"
																readonly="readonly" />
														</div>
													</div>

													</br>
													<div class="small">
														<img src="/static/images/icon-02.png" alt="继续添加时间段"
															id="addTimeInput">
													</div>

												</div>
												<div class="col-md-3 text-info">请不要添加重叠的时间段</div>
											</div>






											<div class="line"></div>

											<div class="last">

												<c:if test="${giftType==1}">
													<h3>优惠券添加：</h3>
												</c:if>
												<c:if test="${giftType==2}">
													<h3>卡包添加：</h3>
												</c:if>
												<c:if test="${giftType==3}">
													<h3>活动链接添加：</h3>
												</c:if>

												<div class="mark">
													<span class="s1">名称</span> <span
														style="color: red; font-size: 12px; margin: 0; padding: 0;">*</span>
													<span class="s2">中奖概率</span><span
														style="color: red; font-size: 12px; margin: 0; padding: 0;">*</span>
													<span class="s3">上传图片</span><span
														style="color: red; font-size: 12px; margin: 0; padding: 0;">*</span>
													<span class="s4">链接路径</span>
													<span class="s5">是否可分享</span> <span
												</div>


												<%
													for (int i = 1; i <= 5; i++) {
												%>

												<div class="all all01" id="gift_<%=i%>">

													<!-- 	<div class="form-group select" id="selectedItems">
														<label class="col-md-2 control-label sel"><a
															href="javascript:void(0)" onclick="showCouponModal()"
															data-toggle="modal" data-target="#couponModal">选择优惠券</a></label>
														<div class="col-md-8 n-sel" id="selectedItemsDetail">未选择优惠券
														</div>
													</div> -->

													<div class="one">
														<select id="select_id<%=i%>" name="coupon"
															onchange="setCouponRuleId(this.options[this.options.selectedIndex].value,<%=i%>)">
															<option value="">--请选择--</option>

															<c:if test="${not empty couponList}">
																<c:forEach var="coupon" items="${couponList}"
																	varStatus="status">
																	<option value="${coupon.couponRuleId}">${coupon.couponName}</option>
																</c:forEach>
															</c:if>

															<c:if test="${not empty bagList}">
																<c:forEach var="bag" items="${bagList}"
																	varStatus="status">
																	<option value="${bag.bagId}">${bag.name}</option>
																</c:forEach>
															</c:if>


														</select> <input type="hidden" name="couponRuleId"
															id="couponRuleId_<%=i%>">

													</div>

													<div class="two">
														<div class="scn">
															<input type="text" class="num" name="percent"
																id="percentId_<%=i%>" placeholder="几率百分比，0~100的数值">
															<span>%</span>
														</div>
													</div>

													<div class="three">
														<div class="sl">
															<%-- <img id="imgPreview_<%=i%>" width="200" src="" /> --%>
															<input id="picInput<%=i%>" callback="previewImg"
																type="file" num="<%=i%>" name="showImg_<%=i%>"
																value="更换图片..."> <input type="hidden"
																id="showImg_<%=i%>">
														</div>
													</div>

													<div class="four">
														<input type="text" id="h5Url_<%=i%>" placeholder="跳转链接">

													</div>

													<div class="five" style="margin-left: 140px;">
														<input name="canShare_<%=i%>" type="radio" value="1" checked="checked">是</input>
														<input name="canShare_<%=i%>" type="radio" value="0"/>否</input>
													</div>
												</div>


												<%
													}
												%>





												<!-- 	</br>
													<div class="small">
														<img src="/static/images/icon-02.png" alt="继续添加红包选项"
															id="addGiftInput">
													</div> -->



												<!-- <button class="sub">确认</button> -->
												<button class="add" onclick="addNewGift();">添加</button>

											</div>


										</form>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- footer -->
		<jsp:include page="/include/footer.jsp" />

		<!-- control sidebar -->
		<jsp:include page="/include/control-sidebar.jsp" />



		<!-- 商家列表Modal -->
		<div class="modal fade" id="couponModal" tabindex="-1" role="dialog"
			aria-labelledby="couponModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="couponModalLabel">选择优惠券</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input type="text" id="storeKeyword" name="storeKeyword"
										maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat"
											onclick="searchCoupon()">查询</button>
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
						<div class="row" id="couponPaginator"></div>
					</div>
					<div class="row">
						<div class="col-sm-12" style="left: 25px;" id="selectedCoupon">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="clearSelectedCoupon()">清空</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- ./wrapper -->
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
	
		//时间段 最大动态5个， 优惠券最多选择5个， 这个是限定，不然太难搞
	
		var spotMax = 5;//限定添加text元素的总个数  

		var imgDomain = 'https://img.mazing.com';

		// 被选中的优惠券的ID
		// key=id, value=name
		var includeCouponIdMap = {};
		

		$(function() {
			
			var giftType = "${giftType}";
			$("#typeID").val(giftType);
			
			var payType = "${payType}";
			$("#payType").val(payType);
			
			//若是活动链接,下拉不可选
			if(giftType==<%=OrderGiftTypeConstants.h5URL%>){
				for (var i = 1; i <= spotMax; i++) {
					$("#select_id"+i).attr("disabled","disabled");
				}
				
			}
			

			var time_afterId = 0;//要追加元素的id  
			var time_newId = 0;//新生成text的id  
			$("#addTimeInput").click(function() {
				time_afterId++
				time_newId = time_afterId + 1;
				addTimeInput(this, time_afterId, time_newId);
			});

			var gift_afterId = 0;//要追加元素的id  
			var gift_newId = 0;//新生成text的id  
			$("#addGiftInput").click(function() {
				gift_afterId++
				gift_newId = gift_afterId + 1;
				addGiftInput(this, gift_afterId, gift_newId);
			});
			
			for (var i = 1; i <= spotMax; i++) {
				//上传文件 change事件
				$("#picInput"+i).change(uploadPic);
			}
			
			
			// 模版函数：输出餐厅的星级
			template.helper('renderStoreStar', function(star) {
				return star / 10;
			});

			// 模版函数：输出 escape 单引号、双引号后的结果
			template.helper('escapeCouponName', function(name) {
				var regExp = new RegExp("'", "g");
				var tmp = name.replace(regExp, "\\'");
				regExp = new RegExp("\"", "g");
				tmp = tmp.replace(regExp, "\\\"");
				return tmp;
			});

			// 选择时间范围控件
			$('#effectiveDateRange').daterangepicker({
				timePicker : true,
				timePickerIncrement : 1,
				format : 'YYYY-MM-DD',
				timePicker12Hour : false,
				locale : {
					cancelLabel : '取消',
					applyLabel : '确定',
					fromLabel : '开始日期',
					toLabel : '结束日期'
				}
			});

			bindDatePicker(1);//从第一个位置开始

		});
		
		
		//赠礼类型下拉 事件， 若是卡包 or 活动链接  类型, 带参数giftType 重新load 数据
		function selectGiftType(type){
			
				var payType = $('select[name=payType]').val();
				
				window.location.href = "/admin/activity/orderGift/add.xhtm?giftType="+type + "&payType="+ payType;
			
		}
		
		//save to DB
		function addNewGift(){
			
			var payType = $('select[name=payType]').val();
			
			var giftType = $('select[name=giftType]').val();
			
			var descrip = $.trim($('#descripID').val());
			
			var enoughMoney = $.trim($('#enoughMoney').val());
			
			var effectiveDateRange = $.trim($('#effectiveDateRange').val());
			
			var timePeriod = "";
			
			for (var i = 1; i <= spotMax; i++) {
				
				var start = $.trim($('#activeStartMinutePreviewID'+i).val());
				var end = $.trim($('#activeEndMinutePreviewID'+i).val());
				
				//alert("start="+start+", end="+end);
				if(start && end){
					var one = start + "~" + end;
					if(i==1){
						timePeriod = one;
					}else{
						timePeriod = timePeriod + ";" + one;
					}
				}
			}
			
			if(descrip==''){
				MyDialog.alert("请设置红包的名称");
				return;
			}
			
			if(effectiveDateRange==''){
				MyDialog.alert("请设置红包的有效日期范围");
				return;
			}
			
			if(timePeriod==''){
				MyDialog.alert("请设置红包的 有效时间段");
				return;
			}
			
			
			var couponRuleIds = "";
			var percents = "";
			var showImgUrls = "";
			var h5Urls = "";
			
			for (var i = 1; i <= 5; i++) {
				var couponRuleId = $.trim($('#couponRuleId_'+i).val());
				
				var percent = parseInt($('#percentId_'+i).val());
				
				//var imgName = "input[name=showImg_"+i +"]";
				
				//var showImg = $.trim($(imgName).val());
				
				var showImg = $.trim($('#showImg_'+i).val());
				
				var h5Url = $.trim($('#h5Url_'+i).val());
				
				//console.log('i='+ i +",objectId=" + couponRuleId + ",percent=" + percent + ", showImg=" + showImg + ", h5Url=" + h5Url);
				
				
				if(giftType == <%=OrderGiftTypeConstants.coupon%> || giftType == <%=OrderGiftTypeConstants.couponBag%>){
					
					if(couponRuleId){
						
						//设置了优惠券，那么其他比填项 必须要设置
						if(!$.isNumeric(percent) || percent>100){
							MyDialog.alert("几率百分比需为0-100的整数值");
							return;
						}
						
					
					 	if(showImg==''){
							MyDialog.alert("必须设置该优惠券的弹窗图");
							return;
						}
					}
				}
				
				if(giftType == <%=OrderGiftTypeConstants.h5URL%>){
					
					if(h5Url){
						//设置了优惠券，那么其他比填项 必须要设置
						if(!$.isNumeric(percent) || percent>100){
							MyDialog.alert("几率百分比需为0-100的整数值");
							return;
						}
						
						if(showImg==''){
							MyDialog.alert("必须设置该活动的弹窗图");
							return;
						}
					}
				}
				
				if(couponRuleId || h5Url){
					
					
					if(i==1){
						couponRuleIds = couponRuleId;
						percents = percent + "";
						showImgUrls = showImg;
						h5Urls = h5Url ;
					}else{
						if(couponRuleId){
							couponRuleIds = couponRuleIds + "," + couponRuleId;
						}
						percents = percents + "," + percent;
						showImgUrls = showImgUrls + ";" + showImg;
						
						if(h5Url){
							h5Urls = h5Urls + ";" + h5Url;
						}
					}
				}
			}
			
			if(percents){
				var percentArr = percents.split(",");
				var total = 0;
				for ( var per in percentArr) {
					 	total = total + parseInt(percentArr[per]);
				}
				
				if(total>100){
					MyDialog.alert("各项的中奖概率总和不能超过100%,请重新设置");
					return;
				}
			}
			
			//console.log('giftType=' + giftType+', descrip='+descrip+', effectiveDateRange='+effectiveDateRange+', timePeriod='+timePeriod);
			
			//console.log('couponRuleIds=' + couponRuleIds+', percents='+percents+', showImgUrls='+showImgUrls+', h5Urls='+h5Urls);
			
			var radios = $("input:checked");
			//获得是否可分享按钮的值，并拼接为字符串
			var canShares = '';
			$.each($("input:checked"), function(index, element){
				canShares += $(this).val() + ";";
			});
			canShares = canShares.substring(0, canShares.length - 1);
			
			enoughMoney = enoughMoney * 100;//元转分
			
			$.post('/admin/activity/orderGift/newAdd.do', {
				'payType' : payType,
				'enoughMoney' : enoughMoney,
				'objectIds' : couponRuleIds,
				'showImgUrl' : showImgUrls,
				'percent' : percents,
				'dateRange' : effectiveDateRange,
				'timeRange' : timePeriod,
				'type' : giftType,
				'descrip' : descrip,
				'url' : h5Urls,
				'canShare' : canShares
			}, function(json) {
				
				if(json.success){
					
					MyDialog.alert(json.message, function() {
						//添加成功后调到 list页面
						window.location.href = "/admin/activity/orderGift/list.xhtm";
					});
				}else{
					MyDialog.alert(json.message);
				}
			}, 'json');
			
		}
		
		//下拉选择了优惠券,设置它的id到hidden中存储
		function setCouponRuleId(couponRuleId, index){
			//alert("index:"+index);
			$("#couponRuleId_"+index).val(couponRuleId);
		}
	
		
		//暂时不用了
		function addGiftInput(obj, afterId, newId) {

			if (newId > spotMax) {
				MyDialog.alert("最多只能添加5项红包选项");
				return;
			}

			$('#gift_' + afterId)
					.after(
							'</br>'
									+ '<div class="all all01" id="gift_'+newId+'">'

									+ '<div class="one">'
									+ '<select id="select_id'
									+ newId
									+ '" name="coupon" onchange="changeCoupon('
									+ newId
									+ ');">'
									+ '<option value ="">--请选择--</option>'
									+ '</select>'
									+ '</div>'

									+ '<div class="two">'
									+ '<div class="scn">'
									+ '<input type="text" class="num" name="percent"> <span>%</span>'
									+ '</div>'
									+ '</div>'

									+ '<div class="three">'
									+ '<div class="sl">'
									+ '<input id="picInput" callback="previewImg" type="file" name="showImg" value="更换图片...">'
									+ '</div>' + '</div>'

									+ '<div class="four">'
									+ '<input type="text" name="h5Url">'
									+ '</div>' + '</div>');
		}
	
		
		
		function changeCoupon(index) {
			MyDialog.alert("当前选择项为： " + index);
		}

		//动态添加 输入时间段的框
		function addTimeInput(obj, afterId, newId) {

			if (newId > spotMax) {
				MyDialog.alert("最多只能添加5项时间段");
				return;
			}

			$('#vote_' + afterId)
					.after(
							'</br>'
									+ '<div id="vote_'+newId+'" class="row">'
									+ '<div class="col-xs-4">'
									+ '<input id="activeStartMinutePreviewID'+newId+'" type="text" name="timePeriodStart" class="form-control input-sm" readonly="readonly" />'
									+ '</div>'
									+ '<div class="col-xs-1">~</div>'
									+ '<div class="col-xs-4">'
									+ '<input id="activeEndMinutePreviewID'+newId+'" type="text" name="timePeriodEnd" class="form-control input-sm" readonly="readonly" />'
									+ '</div>' + '</div>');

			//添加日期绑定事件
			bindDatePicker(newId);

		};
		
		//相应的页面元素绑定日期控件
		function bindDatePicker(index) {

			// 选择时间
			$('#activeStartMinutePreviewID' + index).datetimepicker({
				language : 'zh-CN',
				format : 'hh:ii',
				startView : 1,
				maxView : 1,
				minuteStep : 5,
				autoclose : true,
				keyboardNavigation : false,
				startDate : '00:00'
			}).on('changeDate', function() {
				var str = $('#activeStartMinutePreviewID' + index).val();
				strToMinuteSetVal(str, 'activeStartMinuteID' + index);
			});

			$('#activeEndMinutePreviewID' + index).datetimepicker({
				language : 'zh-CN',
				format : 'hh:ii',
				startView : 1,
				maxView : 1,
				minuteStep : 5,
				autoclose : true,
				keyboardNavigation : false,
				startDate : '24:00'
			}).on('changeDate', function() {
				var str = $('#activeEndMinutePreviewID' + index).val();
				if (str == '00:00') {
					str = '24:00';
					$('#activeEndMinutePreviewID' + index).val(str);// 灰机，这个不起作用，还是显示 00:00
				}
				strToMinuteSetVal(str, 'activeEndMinuteID' + index);
			});
		}


		// str是一个 hh:mm格式的时间，将其转换成分钟数，并赋值给 #id 组件
		function strToMinuteSetVal(str, id) {
			var m = hmToMinute(str);
			$('#' + id).val(m);
		}

		// 【时:分】转换成 ‘分钟数’
		// 00:00 -> 0 __  01:00 -> 60 __ 01:30 -> 90 __ 24:00 -> 1440
		function hmToMinute(str) {
			var ar = str.split(':');//暂时不考虑校验
			var h = new Number(ar[0]);
			var m = new Number(ar[1]);
			return ((h * 60) + (m));
		}

		// ‘分钟数’ 转换成 【时:分】
		// 0 -> 00:00 __  60 -> 01:00 __ 90 -> 01:30 __ 1440 -> 24:00
		function minuteToHM(minute) {
			var h = Math.floor(minute / 60);
			var m = Math.floor(minute % 60);
			//左边补0
			if (10 > h)
				h = ('0' + h);
			if (10 > m)
				m = ('0' + m);
			return h + ':' + m;
		}

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
				url : "/admin/coupon/queryCouponRulesForBag",
				// 显示页码的位置
				paginator : 'couponPaginator',
				// 模版ID
				tpl : 'storeTableTpl',
				// 请求的参数表，
				params : {
					'keyword' : keyword
				},
				// 默认page=1, pageSize=20
				pageSize : 10,
				success : tableCallback
			});
		}

		// 渲染完table之后的回调，主要给以选中的券打勾
		function tableCallback(json) {
			$("#storeTable a[ruleId != '']")
					.each(
							function(i, d) {
								var dom = $(d);
								var id = dom.attr('ruleId');
								if (includeCouponIdMap[id]) {
									var name = dom.html();
									dom
											.html('<span style="color: green;">√<span> &nbsp; '
													+ name);
								}
							});
		}

		// 清除选择的store
		function clearSelectedCoupon() {
			includeCouponIdMap = {};
			$('#selectedCoupon').html('未选择优惠券');
			$('#selectedItemsDetail').html('未选择优惠券');
		}

		//清除其他被选中选项及样式
		function clearOthersSelectedStyle() {
			$("#storeTable a[ruleId != '']").each(function(i, d) {
				var dom = $(d);
				var id = dom.attr('ruleId');
				if (includeCouponIdMap[id]) {
					var name = dom.children("span").children("span").html();
					delete includeCouponIdMap[id];
					$("#storeTable a[ruleId='" + id + "']").html(name);
				}
			});
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
				$("#storeTable a[ruleId='" + id + "']").html(name);
			} else {

				//清除其他选项及样式
				clearOthersSelectedStyle();

				includeCouponIdMap[id] = name;
				$("#storeTable a[ruleId='" + id + "']").html(
						'<span style="color: green;">√<span> &nbsp; ' + name);
			}
			echoSelectedCoupon();
		}

		// 显示已经选中的优惠券名称
		function echoSelectedCoupon() {
			// 组装优惠券名列表
			var includeCouponNames = '', length = 0;
			for ( var id in includeCouponIdMap) {
				var name = includeCouponIdMap[id];
				name = ('(' + id + ')&nbsp;' + name);

				if (0 < length)
					includeCouponNames += '<br/>';
				includeCouponNames += name;
				length += 1;
			}
			var htmlTxt = '<p><h4>已选中 <span style="color:red;">' + length
					+ '</span> 种优惠券：</h4>' + includeCouponNames + '</p>';
			$('#selectedCoupon').html(htmlTxt);
			$('#selectedItemsDetail').html(htmlTxt);
		}

		//
		//
		//


		// 上传文件
		// 有两个组件会调用到
		function uploadPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			var num = input.attr('num');
			
			//创建FormData对象
			var data = new FormData();
			//为FormData对象添加数据
			$.each($('#' + id)[0].files, function(i, file) {
				data.append('picFile', file);
			});
			
			/*
			var img = "tmp/pop-50_885x1311.png";
			
			eval(callback + '("' + img + '", "'+num+'");');
			*/
			//$("#showImg_"+num).val("xxxx");
			
		 	$.ajax({
				data : data,
				type : 'POST',
				cache : false,
				contentType : false, //不可缺
				processData : false, //不可缺
				url : '/admin/system/uploadPic.do',
				success : function(json) {
					if (json.success) {
						var img = json.object;
						if (callback) {
							eval(callback + '("' + img + '", "'+num+'");');
						}
					} else {
						MyDialog.alert(json.message);
					}
				}
			}); 
		}
		
		//设置上传后的图片地址到 hidden中存储
		function previewImg(img, index) {
			
		//	$("#imgPreview"+index).attr("src", imgDomain + img);
			
			var imgName = "input[name=showImg_"+index +"]";
			//设置上传后的图片 地址
			$("#showImg_"+index).val(img);
		}


	</script>
</body>
</html>
