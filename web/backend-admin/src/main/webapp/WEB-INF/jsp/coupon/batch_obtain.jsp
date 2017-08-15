<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.core.constant.OrderGiftTypeConstants"%>
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
									<p class="text-left" style="color: red;">1. 领取人的手机号码如果超过100个，请分批领取，一个批次最多100个，避免造成服务器压力过大</p>
									<p class="text-left" style="color: red;">2. 请确保领取的券 or 卡包 余量充足</p>
									<p class="text-left">3. 领取若有失败， 此处只提示最后一个领取失败的原因</p>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post"
											action="#" onsubmit="return false;">

											<div class="form-group">
												<label class="col-md-2 control-label">领取红包类型<span
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
												<label class="col-md-2 control-label">优惠券/卡包 名<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
														<select id="select_id" name="coupon"
															onchange="setCouponRuleId(this.options[this.options.selectedIndex].value)">
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


														 </select><input type="hidden" name="couponRuleId"
															id="couponRuleId">
												</div>
										</div>
										
										
								
										
											<div class="form-group">
												<label class="col-md-2 control-label">领取人手机号码<span
													style="color: red;">*</span></label>
												<div class="col-md-3">
													<textarea name="mobiles" id="mobiles" style="margin: 0px; width: 390px; height: 82px;"></textarea>
												</div>
												
												<div class="col-md-3 text-info">多个手机号码请使用英文逗号 ',' 分隔;  一次领取手机号个数最好不要超过100个,请分批领取,不要偷懒!!!</div>
											</div>
											
											<div class="form-group">
												<div class="col-md-offset-2">
													<button class="btn btn-primary"  onclick="startBatchObtain()">立即领取</button>
												</div>
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


	</div>
	<!-- ./wrapper -->
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
	

		var imgDomain = 'https://img.mazing.com';

		// 被选中的优惠券的ID
		// key=id, value=name
		var includeCouponIdMap = {};
		

		$(function() {
			
			var giftType = "${giftType}";
			$("#typeID").val(giftType);
			
			
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



		});
		
		
		//赠礼类型下拉 事件， 若是卡包 or 活动链接  类型, 带参数giftType 重新load 数据
		function selectGiftType(type){
				window.location.href = "/admin/coupon/batchObtain.xhtm?giftType="+type;
		}
		
		//下拉选择了优惠券,设置它的id到hidden中存储
		function setCouponRuleId(couponRuleId, index){
			$("#couponRuleId").val(couponRuleId);
		}
		
		//批量领取
		function startBatchObtain(){
			
			
			var type = $('select[name=giftType]').val();
			var couponRuleId = $("#couponRuleId").val();
			var mobiles = $.trim($("#mobiles").val());
			
			//alert("type="+type+", couponRuleId="+couponRuleId+", mobiles="+mobiles);
			
			if(mobiles==""){
				MyDialog.alert("请输入要领取的手机号码");
				return;
			}
				
			
			$.post('/admin/coupon/batchObtain.do', {
				'type' : type,
				'objectId' : couponRuleId,
				'mobiles' : mobiles
			}, function(json) {
				
				if(json.success){
					
					MyDialog.alert("全部领取成功");
				}else{
					MyDialog.alert(json.message);
				}
			}, 'json');
			
			
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
