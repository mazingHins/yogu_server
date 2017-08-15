<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.CommonConstants" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>消息推送</title>
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
					消息推送 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="pushTab" class="nav nav-tabs">
							<li class="active"><a href="#pushToAll" data-toggle="tab">消息推送</a></li>
							<li><a href="#pushSmsToAll" data-toggle="tab">短信营销</a></li>
						</ul>
						<div id="pushTabContent" class="tab-content">
							<!-- tab start 消息推送 -->
							<div class="tab-pane fade in active" id="pushToAll">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">此功能主要用于，推送消息给所有用户（B & C），请谨慎使用！</p>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">
												<div class="form-group">
													<label class="col-sm-2 control-label"> 选择推送给的系统: </label>
													<div class="col-sm-3">
														<select class="form-control" name="select" id="sysType">
															<option value="0" selected>所有系统</option>
															<option value="1" >IOS系统</option>
															<option value="2">Android系统</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label"> 用户语言: </label>
													<div class="col-sm-3">
														<select class="form-control" name="select" id="lang">
															<option value="zh" selected>中文(默认)</option>
															<option value="en" >英文</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label"> 城市: </label>
													<div class="col-sm-3">
														<select class="form-control" name="select" id="city">
															<c:forEach items="${cityList}" var="city">
																<option value="${city.code}" <c:if test="${city.code == '020'}">selected</c:if>>${city.name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label"> 标题: </label>
													<div class="col-sm-3">
														<input type="text" id="pushTitle" class="form-control" placeholder="请输入标题" maxlength="24" value="米星"/>
													</div>
													<div class="col-sm-3">
														<p class="text-left">一般没必要请不要修改标题</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label"> 内容: </label>
													<div class="col-sm-3">
														<textarea id="pushContent" class="form-control" placeholder="请输入需要推送的内容" maxlength="72" ></textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-2 control-label"> 动作: </label>
													<div class="col-md-3">
														<select id="actionID" name="action" class="form-control">
															<option value="">无动作</option>
															<option value="h5">打开HTML5页面</option>
															<option value="waterfall">打开瀑布流</option>
															<option value="miniBlog">打开餐厅MiniBlog</option>
															<option value="indexDishCard">在首页打开美食卡片</option>
															<option value="blogDishCard">在餐厅Blog打开美食卡片</option>
														</select>
													</div>
												</div>
												<div id="actionParam1Div" class="form-group hidden">
													<label id="param1Title" class="col-md-2 control-label">附带参数</label>
													<div class="col-md-3">
														<input id="actionParam1ID" class="form-control" readonly="readonly">
													</div>
												</div>
												<div id="actionParam2Div" class="form-group hidden">
													<label id="param2Title" class="col-md-2 control-label">附带参数</label>
													<div class="col-md-3">
														<input id="actionParam2ID" class="form-control" readonly="readonly">
														<button id="actionSelectBtn" type="button" class="btn btn-link" onclick="actionParamSelect();">选择...</button>
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
							<!-- tab end 消息推送 -->

							<!-- tab start 短信营销 -->
							<div class="tab-pane fade in" id="pushSmsToAll">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">此功能主要用于，发送短信给<span class="text-red">所有用户</span>，请谨慎使用！</p>
										</div>
									</div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="form-horizontal col-sm-12">

												<div class="form-group">

													<label class="col-sm-4 control-label">
														模版ID（请在系统配置里配置）
													</label>
													<div class="col-sm-8">
														<input type="text" id="templateId" class="form-control" maxlength="50"/>
													</div>
												</div>
												<div class="form-group">

													<label class="col-sm-4 control-label">
														手机号码（英文逗号隔开）<br/>
														<span class="text-red">如果不填写，群发所有用户</span><br/>
														请注意短信费用！
													</label>
													<div class="col-sm-8">
														<textarea class="form-control" id="mobilePhones"></textarea>
													</div>

												</div>
												<div class="form-group">

													<label class="col-sm-4 control-label">
														发送参数，英文逗号隔开
													</label>
													<div class="col-sm-8">
														<textarea class="form-control" id="sendParams"></textarea>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-8">
														<button type="button" class="btn btn-primary" onclick="sendSms()">发送</button>
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-12">
														<pre>
	首先要在云片网登记这个营销模板，注意是营销模板。
	然后在“系统配置管理”定义这个模板，比如：
        group_code=smsTemplate2
        config_key =yx_coupon_1
        config_value={"1" : "", "2" : "", "3":"", "text" : "{0}在呼唤你～您的{1}将在{2}过期，赶紧订个爽下吧！ 米星-APP下载 http://t.cn/R4GbpSK 券也可以转送朋友哦！回T退订"}
    text替换成具体模板内容。
    然后页面填写：
        模板ID：yx_coupon_1
		手机号码：1862007xxxx,1812249yyyy
        发送参数：香炆新西兰纯天然羔羊,50元现金券（羊鼎天）,周日2/7/23:58
	营销模板的ID用 yx_ 开头！
														</pre>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- tab end 短信营销 -->
						</div>

					</div>
					<!-- /.tabContent - -->
				</div>
				<!-- /.col -->
		</div>
		<!-- /.row -->

		</section>
		<!-- /.content -->



		<!-- 通用 Modal -->
		<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDivLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="modalDivLabel">标题内容</h4>
					</div>
					<div id="modalBodyDiv" class="modal-body">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="modalOk();">确认</button>
					</div>
				</div>
			</div>
		</div>

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
			var lang = $.trim($('#lang').val());
			var city = $.trim($('#city').val())
			var pushTitle = $.trim($('#pushTitle').val());
			var pushContent = $.trim($('#pushContent').val());
			var sysType = $.trim($('#sysType').val());

			if (pushTitle == "") {
				MyDialog.alert('标题不能为空，请准确填写');
				return;
			}
			if (pushContent == "") {
				MyDialog.alert('内容不能为空，请准确填写');
				return;
			}
			var maxLen = <%=CommonConstants.MAX_PUSH_MSG_LENGTH%>;
			if (pushContent.length > maxLen) {
				MyDialog.alert('内容不能超过' + maxLen + '个字符，当前 ' + pushContent.length + ' 个字符。建议不超过屏幕显示的两行。');
				return;
			}
			// 附带参数校验
			var action = $.trim($('#actionID').val());
			var data = {'title' : pushTitle, 'message' : pushContent, 'sysType' : sysType, 'action': action, 'lang': lang, 'city': city};

			var param1 = $('#actionParam1ID').val();
			var param2 = $('#actionParam2ID').val();
			if(action) {
				// 打开mini blog
				if('miniBlog' == action) {
					if (param2 == '') { MyDialog.alert('请选择餐厅。'); return; }
					data.storeId = param2;
				}
				// 在首页打开美食卡片
				else if('indexDishCard' == action) {
					if (param2 == '') { MyDialog.alert('请选择美食。'); return; }
					data.dishKey = param2;
				}
				// 在MiniBlog打开美食卡片
				else if('blogDishCard' == action) {
					if (param1 == '') { MyDialog.alert('请选择美食。'); return; }
					data.storeId = param1;
					data.dishKey = param2;
				}
				// 前往瀑布流
				else if('waterfall' == action) {
					if (param2 == '') { MyDialog.alert('请至少选择一个TAG。'); return; }
					data.tags = param2;
				}
				// 打开html5页面
				else if('h5' == action) {
					if (param1 == '') { MyDialog.alert('请输入网址。'); return; }
					data.url = param1;
				}
			}

			// 增加确认弹窗 by ten 2016/2/1
			BootstrapDialog.confirm('确认要推送给<span class="text-red">所有用户</span>吗？', function (result) {
				if (result) {
					$.post('/admin/system/pushToAll.do', data, function(json) {
						MyDialog.alert(json.message);
						if (json.success) {
							//$('#pushTitle').val("");
							//$('#pushContent').val("");
						}
					}, 'json');
				}
			});
		}

		// 发送短信
		function sendSms() {
			var templateId = $.trim($('#templateId').val());
			var mobilePhones = $.trim($('#mobilePhones').val());
			var sendParams = $.trim($('#sendParams').val());
			if (templateId == '') {
				MyDialog.alert('请输入模板ID');
			}
			else {
				var phones = (mobilePhones == '' ? '<span class="text-red">全平台</span>' : mobilePhones);
				BootstrapDialog.show({
					title: '确定要发短信吗？',
					message: '<p>模版ID：' + templateId + '</p><p>手机号码：' + mobilePhones + '</p><p>短信参数：' + sendParams + '</p>',
					buttons: [{
						label: '发送',
						action: function(dialog) {
							dialog.close();

							$.post('/admin/system/sendSmsToAll.do', {'templateId' : templateId,
								'mobilePhones' : mobilePhones,
								'sendParams' : sendParams
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

		$(function() {
			$('#actionID').change(actionChange);
			template.helper('renderStoreStar', function(star) { return star / 10; });
		});

		// ####
		
		// action变更事件
		function actionChange() {
			var action = $('#actionID').val();
			// 打开mini blog
			if('miniBlog' == action) {
				showParamInput({param2show: true, param2title: '餐厅ID', param2name: 'storeId', selBtnText: '选择餐厅...', selBtnFunc: 'selectStore'});
			}
			// 在首页打开美食卡片
			else if('indexDishCard' == action) {
				showParamInput({param2show: true, param2title: '美食KEY', param2name: 'dishKey', selBtnText: '选择美食...', selBtnFunc: 'selectDish'});
			}
			// 在MiniBlog打开美食卡片
			else if('blogDishCard' == action) {
				showParamInput({param1show: true, param1title: '餐厅ID', param1name: 'storeId', param2show: true, param2title: '美食KEY', param2name: 'dishKey', selBtnText: '选择美食...', selBtnFunc: 'selectDish'});
			}
			// 前往瀑布流
			else if('waterfall' == action) {
				showParamInput({param2show: true, param2title: 'TAGs', param2name: 'tags', selBtnText: '选择TAG...', selBtnFunc: 'selectTag'});
			}
			// 打开html5页面
			else if('h5' == action) {
				showParamInput({param1show: true, param1title: '请输入网址', param1name: 'url', param1input: true});
			}
			// 其他：没有参数
			else {
				showParamInput({});
			}
		}
		
		// 是否显示 附带参数（1 & 2），以及 【选择...】按钮
		// param1show：是否显示参数1输入框、param2show：是否显示参数2输入框
		// param1input：参数1输入框是否可编辑、param2input：参数2输入框是否可编辑
		// selBtnText：选择按钮的文本、selBtnFunc：点击选择按钮时触发的方法string
		function showParamInput(option) {
			// param1
			if(option.param1show) $('#actionParam1Div').removeClass('hidden').addClass('show').show();
			else $('#actionParam1Div').removeClass('show').addClass('hidden').hide();
			$('#param1Title').text(option.param1title ? option.param1title : '参数');
			$('#param2Title').text(option.param2title ? option.param2title : '参数');
			// param2
			if(option.param2show) $('#actionParam2Div').removeClass('hidden').addClass('show').show();
			else $('#actionParam2Div').removeClass('show').addClass('hidden').hide();
			
			var input1 = $('#actionParam1ID');
			input1.val('');
			var input2 = $('#actionParam2ID');
			input2.val('');
			// readonly 设置
			if(option.param1input) input1.removeAttr('readonly');
			else input1.attr('readonly', 'readonly');
			if(option.param2input) input2.removeAttr('readonly');
			else input2.attr('readonly', 'readonly');
			// name 设置
			if(option.param1name) input1.attr('name', option.param1name);
			else input1.removeAttr('name');
			if(option.param2name) input2.attr('name', option.param2name);
			else input2.removeAttr('name');
			
			var btn = $('#actionSelectBtn');
			// 按钮显示的文字
			if(option.selBtnText) btn.text(option.selBtnText);
			else btn.text('选择...');
			// 按钮事件
			if(option.selBtnFunc) btn.attr('func', option.selBtnFunc);
			else btn.removeAttr('func');
		}
		
		// 选择... 按钮的事件
		function actionParamSelect() {
			var func = $('#actionSelectBtn').attr('func');
			if(func) eval(func+'()');
		}
		
		
		// ####
		// ## 对modal的处理

		// 选择餐厅
		function selectStore(page) {
			var pageIndex = (page || 1);
			var keyword = $('#storeKeyword').val();
			var params = { 'keyword' : keyword, page: pageIndex, pageSize: 10 };
			
			$.ajax({url: '/admin/store/query', type: 'POST', data: params, dataType: 'json', success: function(json) {
					if(!json.success) {
						MyDialog.alert(json.message);
						return;
					}
					// success
					showModalDiv('请选择餐厅', 'storeTemplate', json);
					// 生成页码
					var opt = {table: 'storeTemplate', paginator: 'storePaginator', flipPageFunc: 'selectStore'};
					var ap = new ArtPaginator(opt);
					ap.createPageHtml(opt.paginator);
					ap.redrawPageBar(pageIndex, json);
				}
			});
		}
		function cStore(storeId) {
			$('#actionParam2ID').val(storeId);
		}
		
		// 选择美食
		function selectDish(page) {
			var pageIndex = (page || 1);
			var keyword = $('#dishKeyword').val();
			var params = { 'keyword' : keyword, page: pageIndex, pageSize: 10 };
			
			$.ajax({url: '/admin/dish/query', type: 'POST', data: params, dataType: 'json', success: function(json) {
					if(!json.success) {
						MyDialog.alert(json.message);
						return;
					}
					// success
					showModalDiv('请选择美食', 'dishTemplate', json);
					// 生成页码
					var opt = {table: 'dishTemplate', paginator: 'dishPaginator', flipPageFunc: 'selectDish'};
					var ap = new ArtPaginator(opt);
					ap.createPageHtml(opt.paginator);
					ap.redrawPageBar(pageIndex, json);
				}
			});
		}
		function cDish(dishKey, storeId) {
			$('#actionParam1ID').val(storeId);
			$('#actionParam2ID').val(dishKey);
		}
		
		// 选择TAG
		function selectTag() {
			showModalDiv('请选择餐厅TAG', 'tagsTemplate');
		}
		function cTag(tags) {
			var tagIds = '';
			$('input[name="selectTag"]:checked').each(function() {
				var id = $(this).val();
				if(tagIds) tagIds += (','+id);
				else tagIds = id;
			});
			$('#actionParam2ID').val(tagIds);
		}
		
		// 打开modal并展示内容
		function showModalDiv(titleName, templateId, templateObject) {
			$('#modalDivLabel').text(titleName);
			if(templateId) {
				var obj = (templateObject || {});
				var htmlTxt = template(templateId, obj);
				$('#modalBodyDiv').html(htmlTxt);
			}
			// 清空选项，并显示窗口
			
			$("#modalDiv").modal("show");
		}
		// 模态窗口点击确定按钮
		function modalOk() {
		}
	</script>
	
	<!-- TAG模态窗口（固定数据） -->
	<script id="tagsTemplate" type="text/html">
<div class="row">
	<div class="col-md-12">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="30%">分类</th>
					<th width="70%">TAG<span class="text-red"></span></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tagCategoryList}" var="category" >
					<tr  class="<c:if test="${category.appShow != 1}">text-red</c:if>">
						<td>${category.categoryName}</td>
						<td>
							<c:forEach items="${category.tagList}" var="tag" varStatus="status">
								<div class="checkbox-inline">
									<label>
										<input type="checkbox" name="selectTag" onclick="javascript:cTag(${tag.tagId});" value="${tag.tagId}">${tag.tagName}
									</label>
								</div>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	</script>

	<!-- 餐厅选择模态窗口 -->
	<script id="storeTemplate" type="text/html">
<div class="row">
	<div class="col-md-12">
		<div class="input-group input-group-sm">
			<input id="storeKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
			<span class="input-group-btn">
				<button type="button" class="btn btn-info btn-flat" onclick="selectStore();">Go!</button>
			</span>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<table id="storeTable" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>餐厅名称</th>
					<th>餐厅评分</th>
				</tr>
			</thead>
			{{each object as value i}}
			<tr>
				<td>
					<a href="javascript:cStore({{value.storeId}})">{{value.storeName}}</a>
				</td>
				<td>
					{{renderStoreStar value.star}}
				</td>
			</tr>
			{{/each}}
		</table>
	</div>
</div>
<div id="storePaginator" class="row"></div>
	</script>

	<!-- 美食选择模态窗口 -->
	<script id="dishTemplate" type="text/html">
<div class="row">
	<div class="col-md-12">
		<div class="input-group input-group-sm">
			<input id="dishKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
			<span class="input-group-btn">
				<button type="button" class="btn btn-info btn-flat" onclick="selectDish();">Go!</button>
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
			{{each object as value i}}
			<tr>
				<td>
					{{value.storeName}}
				</td>
				<td>
					<a href="javascript:cDish({{value.dishKey}}, {{value.storeId}})">{{value.dishName}}</a>
				</td>
			</tr>
			{{/each}}
		</table>
	</div>
</div>
<div id="dishPaginator" class="row"></div>
	</script>
</body>
</html>
