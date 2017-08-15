<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>push通知商家</title>
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
					push通知商家 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="cacheTab" class="nav nav-tabs">
							<li><a href="#pushMerchant" data-toggle="tab">push通知</a></li>
						</ul>

						<!-- tab start 创建帐号 -->
						<div class="tab-pane fade in" id="pushMerchant">
							<div class="box box-solid">
								<div class="box-header">
									<div class="col-sm-8">
										<p class="text-left">此功能主要用于，有版本升级时，push通知给商家。</p>
									</div>
								</div>
								<!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="form-horizontal col-sm-12">

											<div class="form-group">
												<label class="col-sm-2 control-label"> 推送对象: </label>
												<div class="col-sm-3">
													<select class="form-control" name="select" id="systype">
														<option value="1" selected>IOS ORG版本B端</option>
														<option value="2">Android B端</option>
													</select>
												</div>

											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label"> 通知标题： </label>
												<div class="col-sm-3">
													<input type="text"  id="pushTitle" class="form-control"
														placeholder="请输入通知的标题" maxlength="24" value="米星"/>
												</div>
												<div class="col-sm-3">
													<p class="text-left">一般没必要请不要修改标题</p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label"> 推送内容： </label>
												<div class="col-sm-3">
													<textarea  id="pushContent" class="form-control"
														placeholder="请输入需要通知的内容" maxlength="72" > </textarea>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-2">
													<button type="button" class="btn btn-primary" onclick="send()">立即发送</button>
												</div>
											</div>

										</div>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box -->
						</div>
						<!-- tab end 创建帐号 -->


					</div>
					<!-- /.tabContent - -->
				</div>
				<!-- /.col -->
		</div>
		<!-- /.row -->

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
		// 配置表
		var configMap = {};


		String.prototype.replaceAll = function(s1, s2) {
			return this.replace(new RegExp(s1, "g"), s2);
		}

		// 保存
		function send() {

			var systype = $('#systype').val();
			var pushTitle = $.trim($('#pushTitle').val());
			var pushContent = $.trim($('#pushContent').val());


			if (pushTitle == "") {
				MyDialog.alert('通知标题不能为空，请准确填写');
				return;
			}
			if (pushContent == "") {
				MyDialog.alert('通知内容不能为空，请准确填写');
				return;
			}
			if (pushContent.length > 70) {
				MyDialog.alert('通知内容不能超过70个字符，当前 ' + pushContent.length + ' 个字符。建议不超过屏幕显示的两行。');
				return;
			}

			// 增加确认弹窗 by ten 2016/2/1
			BootstrapDialog.confirm('确认要进行推送吗？', function (result) {
				if (result) {
					$.post('/admin/system/push2Merchant.do', {
						'systype' : systype,
						'pushTitle' : pushTitle,
						'pushContent' : pushContent
					}, function(json) {
						MyDialog.alert(json.message);
						if (json.success) {
							//$('#pushTitle').val("");
							//$('#pushContent').val("");
						}
					}, 'json');
				}
			});
		}

		$(function() {

		});
	</script>
</body>
</html>
