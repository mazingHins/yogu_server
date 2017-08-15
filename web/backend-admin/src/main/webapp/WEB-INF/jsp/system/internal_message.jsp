<%@ page import="com.mazing.CommonConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<title>站内信</title>
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
				<h1>
					米星站内信 <small></small>
				</h1>
			</section>
			
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="pushTab" class="nav nav-tabs">
							<li class="active"><a href="#sendMsgToUser" data-toggle="tab">指定用户站内信</a></li>
							<li><a href="#sendMsgToAll" data-toggle="tab">广播站内信</a></li>
						</ul>
						
						<div id="messageTabContent" class="tab-content">
						
						<!-- tab start 用户站内信 -->
							<div class="tab-pane fade in active" id="sendMsgToUser">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">此功能主要用于，发送米星站内信给指定用户</p>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

											<div class="form-group">
												<label class="col-sm-2 control-label">选择发送给的用户类型<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<select class="form-control" name="select" id="userMsgType">
														<option value="1" selected>C端用户</option>
														<option value="2" >B端用户</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">手机号码(英文逗号隔开)<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<textarea class="form-control" id="phones"></textarea>
												</div>
												<div class="col-md-3 text-info">
													至少填写一个
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">标题<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<input type="text"  id="userTitle" class="form-control"
															   placeholder="请输入标题" maxlength="64" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">链接</label>
												<div class="col-sm-3">
													<textarea id="userTarget" class="form-control"
															   placeholder="请输入链接" maxlength="255" ></textarea>
												</div>
												<div class="col-md-3 text-info">
													必须加上http://前缀
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">内容<span style="color:red;">*</span></label>
												<div class="col-sm-3">
												<textarea  id="userMessageContent" class="form-control"
														placeholder="请输入需要发送的内容" maxlength="255" > </textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">是否可分享<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<select class="form-control" id="userCanShare">
														<option value="1" selected="selected">是</option>
														<option value="0">否</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-2">
													<button type="button" class="btn btn-primary" onclick="sendUserMsg()">立即发送</button>
												</div>
											</div>

											</div>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- tab end 用户站内信 -->
							
							<!-- tab start 系统站内信 -->
							<div class="tab-pane fade in" id="sendMsgToAll">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">此功能主要用于，发送米星站内信所有B/C端用户</p>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

											<div class="form-group">
												<label class="col-sm-2 control-label">选择发送给的用户类型<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<select class="form-control" name="select" id="sysMsgType">
														<option value="1" selected>C端用户</option>
														<option value="2" >B端用户</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">标题<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<input type="text"  id="sysTitle" class="form-control"
															   placeholder="请输入标题" maxlength="64" />
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">链接</label>
												<div class="col-sm-3">
													<textarea id="sysTarget" class="form-control"
															   placeholder="请输入链接" maxlength="255" ></textarea>
												</div>
												<div class="col-md-3 text-info">
													必须加上http://前缀
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">内容<span style="color:red;">*</span></label>
												<div class="col-sm-3">
												<textarea  id="sysMessageContent" class="form-control"
														placeholder="请输入需要发送的内容" maxlength="255" > </textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">过期时间<span style="color:red;">*</span></label>
												<div class="col-md-4">
													<div class="input-group">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<input type="text" class="form-control pull-right active" name="datePicker" id="datePicker">
													</div>
												</div>
												<div class="col-md-3 text-info">
													超过该日期后未获取消息的用户将不会收到该消息,已获取的用户不受影响
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-2 control-label">是否可分享<span style="color:red;">*</span></label>
												<div class="col-sm-3">
													<select class="form-control" id="sysCanShare">
														<option value="1" selected="selected">是</option>
														<option value="0">否</option>
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-2">
													<button type="button" class="btn btn-primary" onclick="sendSysMsg()">立即发送</button>
												</div>
											</div>

											</div>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- tab end 用户站内信 -->
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
	<script src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		// 对Date的扩展，将 Date 转化为指定格式的String
		// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
		// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
		// 例子： 
		// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
		// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
		Date.prototype.Format = function (fmt) { //author: meizz 
	    	var o = {
	        	"M+": this.getMonth() + 1, //月份 
	        	"d+": this.getDate(), //日 
	        	"h+": this.getHours(), //小时 
	        	"m+": this.getMinutes(), //分 
	        	"s+": this.getSeconds(), //秒 
	        	"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        	"S": this.getMilliseconds() //毫秒 
	    	};
	    	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    	for (var k in o)
	    		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    	return fmt;
		}
		
		// reference: http://www.bootcss.com/p/bootstrap-datetimepicker/index.htm
		$('#datePicker').datetimepicker({
			format:'yyyy-mm-dd hh:ii',
			startDate:new Date().Format("yyyy-MM-dd hh:mm"),
			autoclose: true,
			startView: 1, maxView: 1
		});
		
		// 发送
		function sendUserMsg() {
			var type = $.trim($('#userMsgType').val());
			var phones = $.trim($('#phones').val());
			var title = $.trim($('#userTitle').val());
			var target = $.trim($('#userTarget').val());
			var content = $.trim($('#userMessageContent').val());
			var canShare = $.trim($('#userCanShare').val());
			
			if (phones == "") {
				MyDialog.alert('发送用户的号码不能为空');
				return;
			}
			
			if (title == "") {
				MyDialog.alert('消息标题不能为空');
				return;
			}
			
			if (content == "") {
				MyDialog.alert('消息内容不能为空');
				return;
			}
			
			BootstrapDialog.confirm('确认要发送吗？', function (result) {
				if (result) {
					$.post('/admin/system/sendMsgToMultiUser.do', {
						'type' : type,
						'phones' : phones,
						'title' : title,
						'target' : target,
						'content' : content,
						'canShare': canShare
					}, function(json) {
						MyDialog.alert(json.message);
						
						// 请求node，保存到消息列表中
						if(json.success){
							requestNode('/user/admin/addusersitemessage', 'post', {phones:phones});
						}
					}, 'json');
				}
			});
		}
		
		function sendSysMsg() {
			var type = $.trim($('#sysMsgType').val());
			var expireTime = $.trim($('#datePicker').val());
			var title = $.trim($('#sysTitle').val());
			var target = $.trim($('#sysTarget').val());
			var content = $.trim($('#sysMessageContent').val());
			var canShare = $.trim($('#sysCanShare').val());
			
			if (expireTime == "") {
				MyDialog.alert('过期时间不能为空');
				return;
			}
			
			if (title == "") {
				MyDialog.alert('消息标题不能为空');
				return;
			}
			
			if (content == "") {
				MyDialog.alert('消息内容不能为空');
				return;
			}
			
			BootstrapDialog.confirm('确认要发送吗？', function (result) {
				if (result) {
					$.post('/admin/system/sendSysMsg.do', {
						'type' : type,
						'expireTime' : expireTime,
						'title' : title,
						'target' : target,
						'content' : content,
						'canShare' : canShare
						
					}, function(json) {
						MyDialog.alert(json.message);
					}, 'json');
				}
			});
		}
		
	</script>
</body>
</html>