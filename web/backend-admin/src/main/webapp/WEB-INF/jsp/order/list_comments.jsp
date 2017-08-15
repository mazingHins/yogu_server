<%@ page import="com.mazing.core.enums.FeedbackCode" %>
<%@ page import="com.mazing.core.enums.order.OrderCommentStar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>订单评论列表</title>
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
				订单列表
				<small></small>
			</h1>
			<ol class="breadcrumb">

			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<div class="col-sm-6">
								<div class="row">
									<div class="col-xs-12" style="margin: 10px;">
										<p class="text-left">说明：</p>
										<p class="text-left">（1）必须选择查询时间</p>
										<p class="text-left">（2）可以按下单人的手机号码查询</p>
										<p class="text-left">（3）可以按订单号模糊查询</p>
									</div>
								</div>
								<form class="form-inline">
								<div class="col-xs-12" style="margin: 10px;">
									<p class="text-left"><span style="width:100px">订单编号：</span><input type="text" id="orderNo" name="orderNo" maxlength="30" class="form-control" placeholder="输入订单号进行搜索"></p>
									<p class="text-left"><span style="width:100px">手机号码：</span><input type="text" id="phone" name="phone" maxlength="30" class="form-control" placeholder="输入手机号码进行搜索"></p>
									<p class="text-left"><span style="width:100px">评论星级：</span><select class="form-control" name="star" id="star">
										<option value="0">所有</option>
										<option value="10">1星</option>
										<option value="20">2星</option>
										<option value="30">3星</option>
										<option value="40">4星</option>
										<option value="50">5星</option>
									</select></p>
									<p class="text-left"><span style="width:100px">查询时间：</span><input type="text" class="form-control active" size="30" name="effectiveTimeRange" id="effectiveTimeRange">
										<div class="input-group input-group-sm col-sm-8" style="margin: 10px;">
											<span class="input-group-btn">
												<button type="button" class="btn btn-info btn-flat" onclick="searchByKey()">Go!</button>
											</span>
										</div>
									</p>
								</div>
								</form>
							</div>
							<div class="col-sm-6" style="text-align: right;">

							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID/#</th>
											<th>订单号</th>
											<th>联系人/地址</th>
											<th>评分</th>
											<th>原因</th>
											<th>评论</th>
											<th>评论时间</th>
											<th>操作</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.commentId}}<br/>
													# {{value.serialNumber}}
												</td>
												<td>
													<a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.orderNo}}">{{value.orderNo}}</a><br/>
													<i class="fa fa-map-marker"></i>: <a href="/admin/store/storeDetail.xhtm?storeId={{value.storeId}}">{{value.storeName}}</a>
												</td>
												<td>
													<i class="fa fa-phone"></i>: {{value.phone}}<br/>
													<i class="fa fa-user"></i>: {{value.contacts}}<br/>
													<i class="fa fa-arrow-circle-right"></i>: {{value.address}}<br/>
													<i class="fa fa-jpy"></i><i class="fa fa-user"></i>: <a href="/admin/user/queryUser.xhtm?uid={{value.uid}}" target="_blank">{{value.userNickname}}</a>
												</td>
												<td>
													{{if value.star < <%=OrderCommentStar.STAR_FOUR%>}}
														 <span style="color:#ff0000;">{{value.star/10}}星</span>
													{{/if}}
													{{if value.star >= <%=OrderCommentStar.STAR_FOUR%>}}
														{{value.star/10}}星
													{{/if}}
												   
												</td>
												<td>
													{{if value.star < <%=OrderCommentStar.STAR_FOUR%>}}
														 <span style="color:#ff0000;">{{showFeedbackReason value.feedbackCode}}</span>
													{{/if}}
													{{if value.star >= <%=OrderCommentStar.STAR_FOUR%>}}
														{{showFeedbackReason value.feedbackCode}}
													{{/if}}
												   
												</td>
												<td>
													{{if value.star < <%=OrderCommentStar.STAR_FOUR%>}}
														 <span style="color:#ff0000;"> {{value.content}}</span>
													{{/if}}
													{{if value.star >= <%=OrderCommentStar.STAR_FOUR%>}}
														 {{value.content}}
													{{/if}}
												</td>
												<td style="font-size: 14px;">{{formatDateTime value.createTime}}</td>
												<td>
													<a href="javascript:void(0)" onclick="deleteComment({{value.orderNo}}, {{value.commentId}}, this)">删除评论</a>
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
</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	function search(orderNo, effectiveTimeRange, star, phone) {
		if(orderNo == ''){
			orderNo=0;
		}
		
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/order/comment/query",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'orderNo': orderNo, "effectiveTimeRange" : effectiveTimeRange, "star" : star, "phone":phone}
		});
	}

	//根据条件搜索
	function searchByKey(){
		var orderNo = $.trim($('#orderNo').val());
		var effectiveTimeRange = $.trim($('#effectiveTimeRange').val());
		var star = $.trim($('#star').val());
		var phone = $.trim($('#phone').val());
		
		if (effectiveTimeRange == '') {
			MyDialog.alert('请选择查询时间段');
			return;
		}
		
		search(orderNo, effectiveTimeRange, star, phone);
	}

	// 重新加载列表
	function reloadPage() {
		var page = $('#listTable').artPage();
		if (page) {
			$('#listTable').artPaginateNext(page);
		}
	}

	// 管理员删除评论
	var deleteCommentYes = false;
	function deleteComment(orderNo, commentId, el) {
		deleteCommentYes = false;
		BootstrapDialog.show({
			title: '删除订单评论',
			message: '确认删除?',
			buttons: [{
				label: '确定',
				action: function(dialog) { deleteCommentYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { deleteCommentYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if (deleteCommentYes) {
					if (el) {
						$(el).html('');
					}
					// 执行修改
					$.post("/admin/order/comment/deleteComment.do", {'orderNo':orderNo, 'commentId':commentId}, function(json) {
						MyDialog.alert(json.message);
						// 重新加载页面
						reloadPage();
					}, "json");
				}
			}
		});
	}
	
	// FeedbackCode List >>>> [{code: '', reason: ''}, {..} ..]
	var codeArr = [
		<%boolean one = true;for(FeedbackCode v : FeedbackCode.values()) {%>
		<%if(!one) {%><%=", "%><%}%>{code: '<%=v.getCode()%>', reason: '<%=v.getReason()%>'}
		<%one = false;}%>
	];
	// 根据code，返回名称
	function getCodeReason(code) {
		var reason = '';
		$.each(codeArr, function(i, d) {
			if(code == d.code) {
				reason = d.reason;
			}
		});
		return reason;
	}
	
	//orderNo初始为零，默认搜索所有订单
	$(function() {
		
		var date = new Date();
		var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    
	    var currentdate = date.getFullYear() + "-" + month + "-" + strDate
        	+ " " + date.getHours() + ":" + date.getMinutes();
        	
        var stardate = "2016-01-01 00:00";
		
		// 选择时间范围控件
		$('#effectiveTimeRange').daterangepicker({
			timePicker: true, timePickerIncrement: 1, format: 'YYYY-MM-DD HH:mm',
			timePicker12Hour: false,
			startDate:stardate,endDate:currentdate,
			locale: { cancelLabel: '取消', applyLabel: '确定', fromLabel:'开始日期', toLabel:'结束日期' }
		});
		
		$('#effectiveTimeRange').val(stardate + ' - ' + currentdate);
		
		// FeedbackCode转换成中文显示
		template.helper('showFeedbackReason', getCodeReason);
		
		// 点击 document 其他地方，浮层隐藏 2015/12/18
		$(document).bind("click", function(e) {
			if ($(e.target).closest('#searchStoreDiv').length == 0) {
				$("#searchStoreDiv").hide();
			}
		});
		
		// 默认查询一次
		searchByKey();
	});
</script>

<!-- 搜索出来的餐厅浮层 -->
<div class="row" id="searchStoreDiv" style="position: absolute; padding: 7px 10px; z-index: 1000; background-color: #fff; border: 1px solid #ccc;border-radius: 4px;display: none;">
	<div class="col-sm-12">
		<div class="row">
				以下是符合条件的餐厅，请选择一个
		</div>
		<div class="row" id="searchStoreBodyDiv" style="position: relative; overflow: hidden; width: auto; height: 200px;">
		</div>
	</div>
</div>
</body>
</html>
