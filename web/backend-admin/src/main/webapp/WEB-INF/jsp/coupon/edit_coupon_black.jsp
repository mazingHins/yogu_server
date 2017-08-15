<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<%@ page import="com.mazing.coupon.remote.vo.AdminBlackVO"%>
<title>优惠券黑名单管理</title>
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
					优惠券 --${couponName} <small></small>
			</section>

			<div>
				&nbsp; &nbsp; &nbsp; &nbsp;
				</br>
			</div>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-3 text-red">黑名单管理</div>
					<div class="col-xs-9 checkbox text-red">
						<c:forEach items="${voList}" var="vo">

							<c:choose>
								<c:when test="${vo.checked eq true}">
									<label> <input type="checkbox" name="blackRecord"
										value="${vo.storeId}" checked>${vo.storeName}</label>&nbsp; &nbsp; &nbsp; &nbsp;
    						</c:when>

								<c:otherwise>
									<label> <input type="checkbox" name="blackRecord"
										value="${vo.storeId}">${vo.storeName}</label>&nbsp; &nbsp; &nbsp; &nbsp;
   							 </c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>

				<input type="text" id="hasExclude" value="${hasExclude}"
					style="display: none;" />

				<div class="box-footer with-border">
					<div class="box-tools pull-right">
						<button class="btn btn-primary" onclick="saveCouponBlack()">提交</button>
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

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>

	<script type="text/javascript">
	
	
		function saveCouponBlack() {

			var couponRuleId = $.getUrlParam("couponRuleId");
			//旧的标识，是否有排除群组
			var hasExclude = $('#hasExclude').val();

			//黑名单商家ids
			var values = new Array();
			var blackList = $('input[name=blackRecord]:checked').each(
					function(i, item) {
						values.push(item.value);
					});

			var blackIds = "";
			if (values.length > 0) {
				blackIds = values.join(',');
			}
			console.log('blackIds: ' + blackIds)	
				
				$.post('/admin/coupon/saveCouponBlack.do', {
					'couponRuleId' : couponRuleId,
					'blackIds' : blackIds,
					'hasExclude' : hasExclude
				}, function(json) {
					
					if(json.success){
						
						MyDialog.alert(json.message);
						
						self.setInterval("reload()",1000);
					}
				}, 'json');

		}
		
		
		function reload(){
			
			window.location.reload();
		}
		
		
	</script>
</body>
</html>
