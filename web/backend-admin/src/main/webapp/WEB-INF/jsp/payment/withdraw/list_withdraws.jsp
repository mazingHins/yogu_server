<%@ page import="com.mazing.core.enums.order.OrderIsSysConfirm" %>
<%@ page import="com.mazing.core.enums.order.OrderStatus" %>
<%@ page import="com.mazing.core.enums.pay.PayType" %>
<%@ page import="com.mazing.core.enums.pay.WithdrawStatus" %>
<%@ page import="com.mazing.core.enums.pay.PayMode" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>用户提现列表</title>
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
				用户提现列表
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/payment/withdraw/applyList.xhtm"><i class="fa fa-dashboard"></i> 提现申请</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
				
					<ul id="orderTypeTab" class="nav nav-tabs">
						<li id="withdrawsListTab" class="active">
							<a href="#withdrawsList" data-toggle="tab">
								提现记录
							</a>
						</li>
						<li id="finishedOrdersTab"><a href="#finishedOrdersList" data-toggle="tab">完成的订单</a></li>
					</ul>
					
					
					<div id="orderTypeTabContent" class="tab-content">
					
					<div class="tab-pane fade in active" style="background-color: #fff;" id="withdrawsList">
				
					<div class="box">
						<div class="box-header">
							<div class="col-sm-3">
								<div class="input-group input-group-sm">
									<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
									</span>
								</div>
							</div>
							<div class="col-sm-9">
								<a href="https://doc.open.alipay.com/doc2/detail?treeId=64&articleId=103777&docType=1" target="_blank">错误码请参考此处</a>
							</div>
						</div><!-- /.box-header -->
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>ID</th>
											<th>帐号名</th>
											<th>支付宝帐号</th>
											<th>提现金额（元）</th>
											<th>创建时间</th>
											<th>状态</th>
										</tr>
										</thead>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													{{value.withdrawId}}
												</td>
												<td><a href="javascript:void(0)" onclick="showFinishedOrders({{value.storeId}})">{{value.accountName}}</a></td>
												<td>&nbsp;</td>
												<td>{{cent2yuan value.amount}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													{{if value.withdrawStatus == <%=WithdrawStatus.FAILED.getValue()%>}}
													<span style="color:red;">失败</span>，<strong>原因：</strong>
													{{errorTxt value.resultRemark}}
													{{/if}}
													{{if value.withdrawStatus == <%=WithdrawStatus.SUCCESS.getValue()%>}}
													<span style="color:green;">成功</span>
													{{/if}}
													{{if value.withdrawStatus == <%=WithdrawStatus.IN_PROGRESS.getValue()%>}}
													<span style="color:#0000ff;">处理中</span>
													{{/if}}
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
					
					</div>
					
					
					<div class="tab-pane fade in" style="background-color: #fff;" id="finishedOrdersList">
					
					<div class="box" >
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="finishedOrdersListTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th>订单编号</th>
											<th>收货联系人</th>
											<th>联系电话</th>
											<th>订单状态</th>
											<th>应付金额(元)</th>
											<th>支付类型</th>
											<th>支付方式</th>
											<th>创建时间</th>
											<th>预计送达时间</th>
											<th>商家确认收货时间</th>
											<th>用户确认收货时间</th>
										</tr>
										</thead>
										<script id="finishedOrdersListTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>
													<a target="_blank" href="/admin/order/orderDetail.xhtm?orderNo={{value.orderNo}}">{{value.orderNo}}</a>
												</td>
												<td>{{value.contacts}}</td>
												<td>{{value.phone}}</td>
												<td>
													{{if value.status == <%=OrderStatus.NON_PAYMENT.getValue()%>}}
													未付款
													{{/if}}
													{{if value.status == <%=OrderStatus.PENDING_ACCEPT.getValue()%>}}
													{{if value.payType == <%=PayType.ONLINE.getValue()%>}}
													在线支付
													{{/if}}
													{{if value.payType == <%=PayType.CASH.getValue()%>}}
													现金支付
													{{/if}}
													{{/if}}

													{{if value.status == <%=OrderStatus.ACCEPT_ORDER.getValue()%>}}
													<span style="color:#0000ff;">商家已接单</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.FINISH_COOKING.getValue()%>}}
													<span style="color:#0000ff;">（完成制作）等待配送</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.DELIVERY.getValue()%>}}
													<span style="color:#0000ff;">配送中</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.CONFIRM_RECEIPT_USER.getValue()%>}}
													<span style="color:green;">买家确认收货</span>
													{{if value.sysConfirm == <%=OrderIsSysConfirm.YES.getValue()%>}}
													<!-- 订单是否自动签收, 1-不是, 2-是 -->
													<br/>(<span style="color: green;">系统自动签收</span>)
													{{/if}}
													{{/if}}
													{{if value.status == <%=OrderStatus.HAS_COMMENT.getValue()%>}}
													<span style="color:green;">买家已评论</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.REFUND_APPLY.getValue()%>}}
													<span style="color: #c71585;">申请退款中</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.REFUND.getValue()%>}}
													<span style="color: #c71585;">系统退款中</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.REFUND_REFUSE.getValue()%>}}
													<span style="color: #c71585;">拒绝退款</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.REFUND_SUCCESS.getValue()%>}}
													<span style="color: #c71585;">退款成功</span>
													{{/if}}
													{{if value.status == <%=OrderStatus.CANCEL.getValue()%>}}
													<span style="color:#808080;">已取消</span>
													{{/if}}
												</td>
												<td>{{cent2yuan value.totalFee}}</td>
												<td>
													{{if value.payType == <%=PayType.ONLINE.getValue()%>}}
													<span style="color:green;">线上支付</span>
													{{/if}}
													{{if value.payType == <%=PayType.MAZING_PAY.getValue()%>}}
													<span style="color:green;">米星付</span>
													{{/if}}
													{{if value.payType == <%=PayType.CASH.getValue()%>}}
													<span>货到付款</span>
													{{/if}}
												</td>
												<td>
													{{if value.payType == <%=PayType.ONLINE.getValue()%>}}
														{{if value.payMode == <%=PayMode.ALIPAY.getValue()%>}}
														<span>支付宝</span>
														{{/if}}
														{{if value.payMode == <%=PayMode.WECHAT.getValue()%>}}
														<span>微信</span>
														{{/if}}
													{{/if}}
												</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>{{formatDateTime value.deliveryTime}}</td>
												<td>{{formatDateTime value.storeConfirmTime}}</td>
												<td>{{formatDateTime value.userConfirmTime}}</td>
											</tr>
											{{/each}}
										</script>
									</table>

								</div>
							</div>
							<div class="row col-sm-12" id="finishedOrdersListPaginator">
							</div>
						</div><!-- /.box-body -->
					</div><!-- /.box -->
					
					</div>
					
						<!-- tab end -->
					</div>
					
					
					
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
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/payment/withdraw/loadWithdraws",
			// 显示页码的位置
			paginator: 'listPaginator',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {}
		});
	}
	

	function searchFinishedOrders(storeId) {
		$('#finishedOrdersListTable').artPaginate({
			// 获取数据的地址
			url: "/admin/payment/withdraw/paidOrders",
			// 显示页码的位置
			paginator: 'finishedOrdersListPaginator',
			// 模版ID
			tpl: 'finishedOrdersListTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'storeId': storeId}
		});
	}
	

	// 重新加载 table 列表
	function showFinishedOrders(storeId) {
		$("ul.nav-tabs li").removeClass("active");
		$('#finishedOrdersTab').addClass("active"); //Add "active" class to selected tab  
		$('#finishedOrdersList').addClass("active").addClass("in"); //Add "active" class to selected tab  
		

        $("#withdrawsList").hide(); //Hide all tab content  
        
        searchFinishedOrders(storeId);
        $("#finishedOrdersList").fadeIn(); //Fade in the active content 
	}

    $("#withdrawsListTab").click(function() {  
        $('#withdrawsList').fadeIn(); //Fade in the active content  
    });

    function errorTxt(val) {
    	if(val == 'UPLOAD_FILE_NOT_FOUND') { return '非常抱歉，找不到上传的文件!'; }
    	if(val == 'UPLOAD_FILE_NAME_ERROR') { return '上传文件名不能为空'; }
    	if(val == 'UPLOAD_USERID_ERROR') { return '上传用户ID不能为空'; }
    	if(val == 'UPLOAD_ISCONFIRM_ERROR') { return '复核参数错误'; }
    	if(val == 'UPLOAD_XTEND_NAME_ERROR') { return '抱歉，上传文件的格式不正确，文件扩展名必须是xls、csv'; }
    	if(val == 'UPLOAD_CN_FILE_NAME_ERROR') { return '抱歉，上传文件的文件名中不能有乱码'; }
    	if(val == 'UPLOAD_FILE_NAME_LENGTH_ERROR') { return '抱歉，上传文件的文件名长度不能超过64个字节'; }
    	if(val == 'UPLOAD_FILE_NAME_DUPLICATE') { return '抱歉，您上传文件的文件名不能和以前上传过的有重复'; }
    	if(val == 'EMAIL_ACCOUNT_LOCKED') { return '您暂时无法使用此功能，请立即补全您的认证信息'; }
    	if(val == 'BATCH_OUT_BIZ_NO_DUPLICATE') { return '业务唯一校验失败'; }
    	if(val == 'BATCH_OUT_BIZ_NO_LIMIT_ERROR') { return '抱歉，上传文件的批次号必须为11~32位的数字、字母或数字与字母的组合'; }
    	if(val == 'AMOUNT_FORMAT_ERROR') { return '抱歉，您上传的文件中，第二行第五列的金额不正确。格式必须为半角的数字，最高精确到分，金额必须大于0'; }
    	if(val == 'PAYER_FORMAT_ERROR') { return '您上传的文件中付款账户格式错误'; }
    	if(val == 'PAYER_IS_NULL') { return '抱歉，您上传的文件中付款账户不能为空'; }
    	if(val == 'FILE_CONTENT_NULL') { return '抱歉，您上传的文件内容不能为空'; }
    	if(val == 'FILE_CONTENT_LIMIT') { return '抱歉，您上传的文件付款笔数超过了最大限制'; }
    	if(val == 'PAYER_USERINFO_NOT_EXIST') { return '抱歉，您上传文件中的付款账户，与其所对应的账户信息不匹配或状态异常'; }
    	if(val == 'DAILY_QUOTA_LIMIT_EXCEED') { return '日限额超限'; }
    	if(val == 'FILE_SUMMARY_NOT_MATCH') { return '抱歉，您填入的信息与上传文件中的数据不一致'; }
    	if(val == 'ILLEGAL_CONTENT_FORMAT') { return '文件内容格式非法'; }
    	if(val == 'DETAIL_OUT_BIZ_NO_REPEATE') { return '同一批次中商户流水号重复'; }
    	if(val == 'TOTAL_COUNT_NOT_MATCH') { return '总笔数与明细汇总笔数不一致'; }
    	if(val == 'TOTAL_AMOUNT_NOT_MATCH') { return '总金额与明细汇总金额不一致'; }
    	if(val == 'PAYER_ACCOUNT_IS_RELEASED') { return '付款账户名与他人重复，无法进行收付款。为保障资金安全，建议及时修改账户名'; }
    	if(val == 'PAYEE_ACCOUNT_IS_RELEASED') { return '收款账户名与他人重复，无法进行收付款'; }
    	if(val == 'ERROR_INVALID_UPLOAD_FILE') { return '抱歉，您上传的文件无效！请确认文件是否存在，并且是有效的文件格式。'; }
    	if(val == 'FILE_SAVE_ERROR') { return '文件上传到服务器失败，请确认您是否已关闭待上传的文件'; }
    	if(val == 'ERROR_FILE_NAME_DUPLICATE') { return '上传的文件名不能重复'; }
    	if(val == 'BATCH_ID_NULL') { return '批次明细查询时批次ID为空'; }
    	if(val == 'BATCH_NO_NULL') { return '批次号为空'; }
    	if(val == 'PARSE_DATE_ERROR') { return '到账户批次查询日期格式错误'; }
    	if(val == 'USER_NOT_UPLOADER') { return '用户查询不是其上传的批次信息'; }
    	if(val == 'ERROR_ACCESS_DATA') { return '无权访问该数据'; }
    	if(val == 'ILLEGAL_FILE_NAME') { return '文件名不合法，只允许为数字、英文（半角）、中文、点以及下划线'; }
    	if(val == 'ERROR_FILE_EMPTY') { return '非常抱歉，找不到上传的文件或文件内容为空!'; }
    	if(val == 'ERROR_FILE_NAME_SURFFIX') { return '错误的文件后缀名'; }
    	if(val == 'ERROR_FILE_NAME_LENGTH') { return '过长的文件名'; }
    	if(val == 'ERROR_SEARCH_DATE') { return '付款记录的查询时间段跨度不能超过15天'; }
    	if(val == 'ERROR_BALANCE_NULL') { return '用户余额不存在'; }
    	if(val == 'ERROR_USER_INFO_NULL') { return '用户信息为空'; }
    	if(val == 'ERROR_USER_ID_NULL') { return '用户名为空'; }
    	if(val == 'ERROR_BATCH_ID_NULL') { return '批次ID为空'; }
    	if(val == 'ERROR_BATCH_NO_NULL') { return '批次号为空'; }
    	if(val == 'STATUS_NOT_VALID') { return '请等待该批次明细校验完成后再下载'; }
    	if(val == 'USER_SERIAL_NO_ERROR') { return '商户流水号的长度不正确，不能为空或必须小于等于32个字符'; }
    	if(val == 'USER_SERIAL_NO_REPEATE') { return '同一批次中商户流水号重复'; }
    	if(val == 'RECEIVE_EMAIL_ERROR') { return '收款人EMAIL的长度不正确，不能为空或必须小于等于100个字符'; }
    	if(val == 'RECEIVE_NAME_ERROR') { return '收款人姓名的长度不正确，不能为空或必须小于等于128个字符'; }
    	if(val == 'RECEIVE_REASON_ERROR') { return '付款理由的长度不正确，不能为空或必须小于等于100个字符'; }
    	if(val == 'RECEIVE_MONEY_ERROR') { return '收款金额格式必须为半角的数字，最高精确到分，金额必须大于0'; }
    	if(val == 'RECEIVE_ACCOUNT_ERROR') { return '收款账户有误或不存在'; }
    	if(val == 'RECEIVE_SINGLE_MONEY_ERROR') { return '收款金额超限'; }
    	if(val == 'LINE_LENGTH_ERROR') { return '流水列数不正确，流水必须等于5列'; }
    	if(val == 'SYSTEM_DISUSE_FILE') { return '用户逾期15天未复核，批次失败'; }
    	if(val == 'MERCHANT_DISUSE_FILE') { return '用户复核不通过，批次失败'; }
    	if(val == 'TRANSFER_AMOUNT_NOT_ENOUGH') { return '转账余额不足，批次失败'; }
    	if(val == 'RECEIVE_USER_NOT_EXIST') { return '收款用户不存在'; }
    	if(val == 'ILLEGAL_USER_STATUS') { return '用户状态不正确'; }
    	if(val == 'ACCOUN_NAME_NOT_MATCH') { return '用户姓名和收款名称不匹配'; }
    	if(val == 'ERROR_OTHER_CERTIFY_LEVEL_LIMIT') { return '收款账户实名认证信息不完整，无法收款'; }
    	if(val == 'ERROR_OTHER_NOT_REALNAMED') { return '收款账户尚未实名认证，无法收款'; }
    	if(val == '用户撤销') { return '用户撤销'; }
    	if(val == 'USER_NOT_EXIST') { return '用户不存在'; }
    	if(val == 'RECEIVE_EMAIL_NAME_NOT_MATCH') { return '收款方email账号与姓名不匹配'; }
    	if(val == 'SYSTEM_ERROR') { return '支付宝系统异常'; }
    	return val;
    }

	$(function() {
		template.helper('errorTxt', errorTxt);// 支付宝错误码转换

//		var uid = $.getUrlParam('uid');
//		if (typeof uid == 'undefined' || uid == null || uid == '') {
//			uid = 0;
//		}
		search();
	});
</script>
</body>
</html>
