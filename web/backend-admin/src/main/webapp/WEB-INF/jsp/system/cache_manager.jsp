<%@ page import="com.mazing.core.sms.SmsConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>缓存管理</title>
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
				缓存管理
				<small></small>
			</h1>
			<ol class="breadcrumb">

			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<ul id="cacheTab" class="nav nav-tabs">
						<li class="active">
							<a href="#clearCache" data-toggle="tab">
								常规缓存清理
							</a>
						</li>
						<li><a href="#queryCache" data-toggle="tab">缓存查询</a></li>
						<li><a href="#getSmsCode" data-toggle="tab">查询手机验证码</a></li>
						<li><a href="#changeSmsSP" data-toggle="tab">切换短信通道</a></li>
					</ul>

					<div id="cacheTabContent" class="tab-content">
						<!-- tab start 清除所有餐厅、美食的缓存 -->
						<div class="tab-pane fade in active" id="clearCache">
							<div class="box box-solid">
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<button type="button" onclick="clearAllStoreCache()">清除所有餐厅、美食的缓存</button>
										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->

						</div> <!-- /.tab -->
						<!-- tab end 清除所有餐厅、美食的缓存 -->

						<!-- tab start 缓存查询 -->
						<div class="tab-pane fade in" id="queryCache">
							<div class="box box-solid">
								<div class="box-header">
									<div class="col-sm-8">
										<p class="text-left">查询任意缓存的值。</p>
									</div>
								</div><!-- /.box-header -->
								<form role="form" id="createUserForm" name="createUserForm" action="/admin/user/createUser.do" method="post">
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

												<div class="form-group">
													<label class="col-sm-2 control-label">
														缓存实例：
													</label>
													<div class="col-sm-3">
														<select class="form-control" id="cacheInstance" name="cacheInstance">
															<option value="redis">redis</option>
															<option value="redisMsg">redisMsg 消息广播</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">
														缓存key：
													</label>
													<div class="col-sm-8">
														<input type="text" id="cacheKey" name="cacheKey" class="form-control" placeholder="请输入一个key" maxlength="100"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-2">
														<button type="button" class="btn btn-primary" onclick="readCache()">读取</button>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-10" id="displayCacheResult">
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-10">
														<p class="text-left">一些cache key前缀：</p>
														<p class="text-left">餐厅：Store:</p>
														<p class="text-left">美食：Dish:</p>
														<p class="text-left">餐厅详情：StoreDetails:</p>
													</div>
												</div>
											</div>
										</div>
									</div><!-- /.box-body -->
								</form>
							</div><!-- /.box -->
						</div>
						<!-- tab end 缓存查询 -->

						<!-- tab start 查询手机验证码 -->
						<div class="tab-pane fade in" id="getSmsCode">
							<div class="box box-solid">
								<div class="box-header">
									<div class="col-sm-8">
										<p class="text-left">查询手机验证码。</p>
									</div>
								</div><!-- /.box-header -->
								<form role="form">
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

												<div class="form-group">
													<label class="col-sm-2 control-label">
														手机号码
													</label>
													<div class="col-sm-4">
														<input type="text" name="smsCodeMobile" id="smsCodeMobile" class="form-control" placeholder="请输入一个手机号码" maxlength="100"/>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-2">
														<button type="button" class="btn btn-primary" onclick="getSmsCode()">读取</button>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-8" id="getSmsCodeResult">
													</div>
												</div>
											</div>
										</div>
									</div><!-- /.box-body -->
								</form>
							</div><!-- /.box -->
						</div>
						<!-- tab end 查询手机验证码 -->

						<!-- tab start 查询手机验证码 -->
						<div class="tab-pane fade in" id="changeSmsSP">
							<div class="box box-solid">
								<div class="box-header">
									<div class="col-sm-8">
										<p class="text-left" id="currentSP">当前的短信通道是<span style="color:red;"><%=SmsConfig.getCurrentSpName()%></span></p>
									</div>
								</div><!-- /.box-header -->
								<form role="form">
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

												<div class="form-group">
													<label class="col-sm-2 control-label">
														云片网
													</label>
													<div class="col-sm-4">
														<button type="button" class="btn btn-primary" onclick="changSmsSP(<%=SmsConfig.SMS_INSTANCE_YUN_PIAN%>, '<%=SmsConfig.getSpName(SmsConfig.SMS_INSTANCE_YUN_PIAN)%>')">切换到云片网</button>
													</div>
													<div class="col-sm-4">
														云片网提供短信通道
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">
														网易云信
													</label>
													<div class="col-sm-4">
														<button type="button" class="btn btn-primary" onclick="changSmsSP(<%=SmsConfig.SMS_INSTANCE_NETEASE%>, '<%=SmsConfig.getSpName(SmsConfig.SMS_INSTANCE_NETEASE)%>')">切换到网易云信</button>
													</div>
													<div class="col-sm-4">
														网易云信提供短信通道
													</div>
												</div>
												<%--<div>--%>
													<%--<div class="col-sm-12"><hr/></div>--%>
												<%--</div>--%>
												<%--<div class="form-group">--%>

													<%--<label class="col-sm-4">--%>
														<%--<button class="btn btn-danger">营销短信</button>--%>
														<%--群发（仅支持【云片网营销渠道】，和上面的配置无关）--%>
													<%--</label>--%>

												<%--</div>--%>
												<%--<div class="form-group">--%>

													<%--<label class="col-sm-4 control-label">--%>
														<%--模版ID（请在系统配置里配置）--%>
													<%--</label>--%>
													<%--<div class="col-sm-8">--%>
														<%--<input type="text" id="templateId" class="form-control" maxlength="50"/>--%>
													<%--</div>--%>
												<%--</div>--%>
												<%--<div class="form-group">--%>

													<%--<label class="col-sm-4 control-label">--%>
														<%--手机号码（英文逗号隔开）--%>
													<%--</label>--%>
													<%--<div class="col-sm-8">--%>
														<%--<textarea class="form-control" id="mobilePhones"></textarea>--%>
													<%--</div>--%>

												<%--</div>--%>
												<%--<div class="form-group">--%>

													<%--<label class="col-sm-4 control-label">--%>
														<%--发送参数，英文逗号隔开--%>
													<%--</label>--%>
													<%--<div class="col-sm-8">--%>
														<%--<textarea class="form-control" id="sendText"></textarea>--%>
													<%--</div>--%>
												<%--</div>--%>
												<%--<div class="form-group">--%>
													<%--<div class="col-sm-offset-4 col-sm-8">--%>
														<%--<button type="button" class="btn btn-primary" onclick="sendSms()">发送</button>--%>
													<%--</div>--%>
												<%--</div>--%>
												<%--<div class="form-group">--%>
													<%--<div class="col-sm-12">--%>
														<%--<pre>--%>
	<%--在“系统配置管理”定义这个模板，比如：--%>
        <%--group_code=smsTemplate2--%>
        <%--config_key =yx_coupon_1--%>
        <%--config_value={"1" : "", "2" : "", "3":"", "text" : "{0}在呼唤你～您的{1}将在{2}过期，赶紧订个爽下吧！ 米星-APP下载 http://t.cn/R4GbpSK 券也可以转送朋友哦！回T退订"}--%>
    <%--text替换成具体模板内容。--%>
    <%--然后页面填写：--%>
        <%--模板ID：yx_coupon_1--%>
		<%--手机号码：1862007xxxx,1812249yyyy--%>
        <%--发送参数：香炆新西兰纯天然羔羊,50元现金券（羊鼎天）,周日2/7/23:58--%>
	<%--营销模板的ID用 yx_ 开头！--%>
														<%--</pre>--%>
													<%--</div>--%>
												<%--</div>--%>
											</div>
										</div>
									</div><!-- /.box-body -->
								</form>
							</div><!-- /.box -->
						</div>
						<!-- tab end 查询手机验证码 -->

					</div> <!-- /.tabContent - -->
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

	// 清除所有餐厅的缓存
	function clearAllStoreCache() {
		BootstrapDialog.show({
			title: '清除餐厅缓存',
			message: '你确认要清除【所有】餐厅、美食的缓存吗？清除的同时会重新加载数据。',
			buttons: [{
				label: '清除',
				action: function(dialog) {
					dialog.close();

					// $.post('/admin/system/clearAllStoreCache.do', {}, function (json) { MyDialog.alert(json.message); }, 'json');
					$.ajax({
						type: 'POST', url: '/admin/system/clearAllStoreCache.do', data: {}, timeout: 30000, dataType: 'json'
						, success: function (json) { MyDialog.alert(json.message); }
					});
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});
	}

	// 读取 cache
	// ten 2016/2/24
	function readCache() {
		var cacheInstance = $('#cacheInstance').val();
		var cacheKey = $.trim($('#cacheKey').val());
		if (cacheKey == '') {
			MyDialog.alert('请输入key');
		}
		else {
			$.getJSON('/admin/system/getCacheValue', {'cacheInstance':cacheInstance, 'cacheKey': cacheKey}, function(json) {
				if (json.success) {
					var map = json.object;
					var message = '实例：' + map['host'] + '<br/>值：' + map['value'];
					$('#displayCacheResult').html(message);
				}
				else {
					MyDialog.alert(json.message);
				}
			});
		}
	}

	// 读取验证码
	function getSmsCode() {
		var smsCodeMobile = $.trim($('#smsCodeMobile').val());
		if (smsCodeMobile) {
			$.getJSON('/admin/system/getSmsCode', {'countryCode':'86', 'mobile': smsCodeMobile}, function(json) {
				var code = json.object;
				if (code) {
					$('#getSmsCodeResult').html("验证码是：" + code);
				}
				else {
					$('#getSmsCodeResult').html('<span style="color: red;">没有找到验证码或已经过期</span>');
				}
			});
		}
	}

	// 发送短信
	function sendSms() {
		var templateId = $.trim($('#templateId').val());
		var mobilePhones = $.trim($('#mobilePhones').val());
		var sendText = $.trim($('#sendText').val());
		if (templateId == '') {
			MyDialog.alert('请输入模板ID');
		}
		else if (mobilePhones == '') {
			MyDialog.alert('请输入要发送的手机号码，多个号码以英文逗号隔开');
		}
		else if (sendText == '') {
			MyDialog.alert('发送内容不能为空。');
		}
		else if (sendText.length >= 200) {
			MyDialog.alert('发送内容超过了200个字符，当前长度：' + sendText.length);
		}
		else {
			BootstrapDialog.show({
				title: '确定要发短信吗？',
				message: '<p>模版ID：' + templateId + '</p><p>手机号码：' + mobilePhones + '</p><p>发送内容：' + sendText + '</p><p>内容长度：' + sendText.length + ' （1中文-1字符）</p>',
				buttons: [{
					label: '发送',
					action: function(dialog) {
						dialog.close();

						$.post('/admin/system/sendSms.do', {'templateId' : templateId,
							'mobilePhones' : mobilePhones,
							'sendText' : sendText
						}, function (json) {
							MyDialog.alert(json.message);
						}, 'json');
					}
				}, {
					label: '取消',
					action: function(dialog) {
						dialog.close();
					}
				}]
			});
		}
	}

	// 修改短信 SP
	function changSmsSP(sp, name) {
		var current = $('#currentSP').html();
		BootstrapDialog.show({
			title: '切换短信提供商',
			message: current + '，你确认要切换短信通道为 [' + name + ']？生效要15秒的时间',
			buttons: [{
				label: '切换',
				action: function(dialog) {
					dialog.close();

					$.post('/admin/system/changeSmsSP.do', {'sp' : sp}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							$('#currentSP').html('当前的短信通道是<span style="color:red;">' + json.object + '</span>');
						}
					}, 'json');
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});

	}

	$(function() {

	});
</script>
</body>
</html>
