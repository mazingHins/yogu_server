<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>提现处理列表</title>
	<script type="text/javascript">
				
	</script>
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
				提现处理列表
				<small></small>
			</h1>
			<!-- 
			<ol class="breadcrumb">
				<li><a href="/admin/test/range/edit.xhtm?storeId=${param.storeId}"><i class="fa fa-dashboard"></i>新增配送范围</a></li>
			</ol>
			 -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<tr>
											<th>提现编号</th>
											<th>支付平台</th>
											<th>账户姓名</th>
											<th>账户</th>
											<th>提现金额</th>
											<th>状态</th>
											<th>备注说明</th>
											<th>操作</th>
										</tr>
										<!--  <script id="listTableTpl" type="text/html">
										</script>-->
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

	function listApplyList() {
		var list = ${it.list};
		var $tab1 = $("#listTable");
		$.each(list,function(index,item){
		    var $tr = $('<tr>');
		    var $td1 = $('<td>').html(item.withdrawId);
		    
		    var accountType = "支付宝";
		    if (item.accountType == 2){
		    	accountType = "微信";
		    }
		    var $td2 = $('<td>').html(accountType);
		    var $td3 = $('<td>').html(item.accountName);
		    var $td4 = $('<td>').html(item.accountNo);
		    var $td5 = $('<td>').html(item.amount / 100);
		    
		    var withdrawStatus = "申请中";
		    if (item.withdrawStatus == 0){
		    	withdrawStatus = "失败";
		    }
		    if (item.withdrawStatus == 2){
		    	withdrawStatus = "成功";
		    }
		    var $td6 = $('<td>').html(withdrawStatus);
		    var $td7 = $('<td>').html(item.remark);
		    
		    var $a = $('<a>')
		    $a.attr('href','javascript:void(0)');    
    		$a.attr('class','btn btn-primary');
    		$a.attr('onclick','closeWithdraw('+item.withdrawId+')');
    		$a.html('关闭');
		    
		    $tr.append($td1);
		    $tr.append($td2);
		    $tr.append($td3);
		    $tr.append($td4);
		    $tr.append($td5);
		    $tr.append($td6);
		    $tr.append($td7);
		    $tr.append($a);
		    $tab1.append($tr);
		});
	}
	
	$(function(){
		listApplyList();
	});
	
	function closeWithdraw(withdrawId){
		$.getJSON("/admin/test/withdraw/closeWithdraw?withdrawId="+withdrawId, {}, function(json) {
			MyDialog.alert(json.message);
			
			window.location.reload();
		});
	}
</script>
</body>
</html>
