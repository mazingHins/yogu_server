<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>弹窗广告配置管理</title>
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
					弹窗广告配置 <small></small>
				</h1>
				<!-- --> <ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#configModal" onclick="reset()"><i
							class="fa fa-dashboard"></i> 新增配置</a></li>
				</ol> 
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="cacheTab" class="nav nav-tabs">
							<li class="active"><a href="#getLatestAdverActivity"
								data-toggle="tab"> 弹窗广告配置 </a></li>
							<!-- -->
						</ul>

						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 系统配置 -->
							<div class="tab-pane fade in active" id="activityConfig">
								<div class="box box-solid">
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12">
												<div style="word-wrap: break-word; color: red;">注意：
													目前只展示最近配置的一条数据,配置新的数据至正式环境时请先确保在测试环境已测试验收通过</div>
												</br> </br>
												
								<div class="col-sm-6 form-inline">
									<select class="form-control" name="orderStatus" id="city">
										<c:forEach items="${cityList}" var="city">
											<option value="${city.code}">${city.name}</option>
										</c:forEach>
									</select>	
									<select class="form-control" id="lang">
										<c:forEach items="${langList}" var="lang">
											<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>
									
									<div class="input-group input-group-sm col-sm-1">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="search();">查询</button>
										</span>
									</div>
									&nbsp;&nbsp;&nbsp;当前城市：<span id="nowCityName"></span>&nbsp;&nbsp; 语言：<span id="nowLangName" style="color:red;"></span>
									<input id="nowCityCode" type="hidden" />
									<input id="nowLangCode" type="hidden" />
								</div>
								
												<table id="activityTable"
													class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="5%">ID</th>
															<th width="8%">广告图</th>
															<th width="8%">广告跳转链接</th>
															<th width="8%">是否可分享</th>
															<th width="10%">展示类型</th>
															<th width="22%">活动开始时间</th>
															<th width="10%">活动结束时间</th>
															<th width="8%">操作</th>	
														</tr>
													</thead>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.aid}}
														</td>
		
														<td>
															<img src="${imgDomain}{{value.img}}" width="200px" height="300px">
															
														</td>
														<td style="word-wrap:break-word;">{{value.url}}</td>
														<td style="word-wrap:break-word;" >
															{{if value.shareFlag == 1}}
																是
															{{/if}}
															
															{{if value.shareFlag == 0}}
																否
															{{/if}}
														</td>
														<td style="word-wrap:break-word;">
															{{if value.type == 1}}
																活动期内展示一次
															{{/if}}
															
															{{if value.type == 2}}
																活动期内每天展示一次
															{{/if}}
														</td>
														
													<td style="word-wrap:break-word;">{{formatDateTime value.beginTime}}</td>
														
													<td style="word-wrap:break-word;">{{formatDateTime value.endTime}}</td>	

													<td>
													<a href="javascript:deleteAdverActivity({{value.aid}});">删除</a>
													&nbsp; <a href="javascript:void(0)" onclick="modifyActivity('{{value.img}}', '{{formatDateTime value.beginTime}}', '{{formatDateTime value.endTime}}', '{{value.url}}', '{{value.shareFlag}}', '{{value.type}}',  '{{value.aid}}')" data-toggle="modal" data-target="#configModal">修改</a>
														</td>
													</tr>
													{{/each}}
												</script>
												</table>
											</div>
										</div>
							
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->

							</div>
							<!-- /.tab -->
							<!-- tab end 系统配置 -->

							<!-- tab start 其他配置 -->
							<!-- tab end 其他配置 -->


						</div>
						<!-- /.tabContent - -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<!-- Modal -->
				<div class="modal fade" id="configModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<!-- -->
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="closeEdit()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">编辑配置</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal">
										<div class="form-group">
										<label for="type" class="control-label col-md-4" style="color: red;">上传的图片将会原样保存,请尽量按要求的尺寸590x874预先处理好需要上传的图片</label>
										</div>
										<div id="imgPreviewDiv" class="form-group">
												<label class="control-label col-md-4">广告图:<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="imgPreview" width="200" src="" />
													<input id="picInput" callback="previewImg" type="file" value="更换图片...">
													<input id="img" type="hidden">
												</div>
										</div>
									
								
									<div class="form-group">
										<label for="type" class="control-label col-md-4">展示类型:</label>
										<div class="col-md-8">
											<select name="select" id="type">
												<option value="1" selected="selected">活动期间展示一次</option>
												<option value="2">活动期间每天展示一次</option>
											</select>
										</div>
									</div>
									
								<%-- 	<div class="form-group">
										<label for="type" class="control-label col-md-4">语言版本:</label>
										<div class="col-md-8">
											<select class="form-control" id="langCode" name="langCode">
												<c:forEach items="${langList}" var="lang">
													<option value="${lang.code}">${lang.zhName}</option>
												</c:forEach>
											</select>
										</div>
									</div> --%>
								

									<div class="form-group">
										<label class="control-label col-md-4">生效时间 <span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" class="form-control pull-right active"
													name="effectiveTimeRange" id="effectiveTimeRange">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="url" class="control-label col-md-4">活动跳转链接:
										</label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="url"
												placeholder="http地址">
										</div>
									</div>
									<div class="form-group">
										<label for="shareFlag" class="control-label col-md-4">是否可分享:
											<span style="color: red;">*</span>
										</label>
										<!--  <input type="text" class="form-control" id="isOpen"> -->
										<div class="col-md-3">
											<select name="select2" id="shareFlag" class="form-control">
												<option value="0" selected="selected col-md-4">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
									<div>
										<input type="hidden" class="form-control" id="aid" value="">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="closeEdit()">取消</button>
								<button type="button" class="btn btn-primary" onclick="save()">保存</button>
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

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script type="text/javascript">
		// 配置表
		var configMap = {};

		//var imgDomain = "http://img.mazing.com/";
		var imgDomain = '${imgDomain}';
		
		
		function search() {
			var cityCode = $("#city").val();
			
			if(!cityCode) { MyDialog.alert("请先选择一个城市！"); return; };
			var langCode = $("#lang").val();
			if(!langCode) { MyDialog.alert("请先选择语言！"); return; };
			
			
			//设置选项可视值
			viewCityLang();
			
			
			// load 数据
			$('#activityTable').artPaginate({
				// 获取数据的地址
				url : "/admin/system/getLatestAdverActivity",
				// 显示页码的位置
				paginator: 'listPaginator2',
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {
					cityCode : cityCode,
					langCode : langCode
				}
			});
			
			$('#aid').val('');
		}
		
		function pathParamOrSelect() {
			var cityCode = $.getUrlParam("cityCode");
			// 将URL中指定的城市，设置到 select组件中
			if (cityCode) {
				$("#city").find("option[value='" + cityCode + "']").attr(
						"selected", "selected");
			}
			
			
			// 将URL中指定的语言，设置到 select组件中
			var langCode = $.getUrlParam("langCode");
			if (langCode) {
				$("#lang").find("option[value='" + langCode + "']").attr("selected", "selected");
			}
			search();
		}
		
		
		function viewCityLang() {
			var c = $("#city").find("option:selected");
			$("#nowCityCode").val(c.val());
			$("#nowCityName").html(c.text());
			var l = $("#lang").find("option:selected");
			$("#nowLangCode").val(l.val());
			$("#nowLangName").html(l.text());
		}
		
		
		function previewImg(img) {
			$("#imgPreview").attr("src", imgDomain + img);
			$("#img").val(img);
		}
		
		function uploadPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			//创建FormData对象
			var data = new FormData();
			
			//$("#img").val("xxx");
			//为FormData对象添加数据
			$.each($('#'+id)[0].files, function(i, file) { data.append('picFile', file); });
			$.ajax({
				data: data,
				type: 'POST',
				cache: false,
				contentType: false, //不可缺
				processData: false, //不可缺
				url: '/admin/system/uploadPic.do',
				success: function(json){
					if(json.success){
						var img = json.object;
						if(callback) {
							eval(callback+'("'+img+'");');
						}
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}
		
		
		// 新增
		function reset() {
			$('#aid').val('');
			$('#img').val('');
			$('#picInput').val('');
			$("#imgPreview").attr("src", '');
			$('#effectiveTimeRange').val('');
			$('#type').val('1');
			$('#url').val('');
			$('#shareFlag').val('0');
			
			// 将列表中选择的语言code 设置入 编辑页面
			var langCode = $("#nowLangCode").val();
			
			
			/* if (langCode) {
				$("#langCode").find("option[value='" + langCode + "']").attr("selected", true);
			} */
		}
		
		function closeEdit(){
			$('#aid').val('');
			$('#img').val('');
			$('#picInput').val('');
			$("#imgPreview").attr("src", '');
			$('#effectiveTimeRange').val('');
			$('#type').val('1');
			$('#url').val('');
			$('#shareFlag').val('0');
			
			/* for (var i = 0; i < 3; i++) {
				$("#langCode").find("option:selected").attr("selected", false);
				
			} */
			
		}
		
		function pre(el) {
			el.style.color = "black";
		}

		function strDateTime2(str) {
			//格式2003-12-05 13:04:06
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			var r = str.match(reg);
			if (r == null)
				return false;
			var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
			return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
					&& d.getDate() == r[4] && d.getHours() == r[5]
					&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
		}

		// 修改弹窗广告
		function modifyActivity(img, beginTime, endTime, url, shareFlag, type,
				aid) {

			$('#aid').val(aid);
			//$('#startTime').val(startTime);
			//$('#endTime').val(endTime);
			var effectiveTimeRange = "" + beginTime + " - " + endTime;
			$('#effectiveTimeRange').val(effectiveTimeRange);

			//设置某项为选中状态    
			$("#type").get(0).selectedIndex = type - 1;
			$('#url').val(url);
			$('#img').val(img);
			$("#imgPreview").attr("src", imgDomain + img);
			$("#shareFlag").get(0).selectedIndex = shareFlag;
			
			// 将列表中选择的语言code 设置入 编辑页面
			/* var langCode = $("#nowLangCode").val();
			if (langCode) {
				$("#langCode").find("option[value='" + langCode + "']").attr("selected", true);
			} */
		}

		String.prototype.replaceAll = function(s1, s2) {
			return this.replace(new RegExp(s1, "g"), s2);
		}

		// 删除
		var executeDelete;
		function deleteAdverActivity(aid) {
			executeDelete = false;

			BootstrapDialog.show({
				title : '删除最近一条弹窗广告',
				message : '确认要删除这条记录吗?',
				buttons : [ {
					label : '确定',
					action : function(dialog) {
						executeDelete = true;
						dialog.close();
					}
				}, {
					label : '取消',
					action : function(dialog) {
						dialog.close();
					}
				} ],
				onhide : function(dialogRef) {
					if (executeDelete == true) {
						$.post('/admin/system/deleteAdverActivity.do', {
							'aid' : aid
						}, function(json) {
							MyDialog.alert(json.message);
							if (json.success) {
								// 重新拉取
								//getLatestAdverActivity();
								//$('#aid').val();
								search();
								
								executeDelete = false;
							}
						}, 'json');
					}
				}
			});

		}

		// 保存
		function save() {

			var aid = $('#aid').val();
			var img = $.trim($('#img').val());
			
			var langCode = $.trim($('#nowLangCode').val());
			var cityCode = $("#city").val();
			
			var type = $.trim($('#type').val());
			var url = $.trim($('#url').val());
			var shareFlag = $.trim($('#shareFlag').val());

			var effectiveTimeRange = $('#effectiveTimeRange').val();
			
			if (img == "") {
				MyDialog.alert('活动图片不能为空，请准确填写');
				return;
			}
			
			
			if (effectiveTimeRange == "") {
				MyDialog.alert('活动时间范围不能为空，请准确填写');
				return;
			}
			
			var timeArr = effectiveTimeRange.split(" - ");

			var beginTime = timeArr[0];
			var endTime = timeArr[1];

			
		//	if (url == "") {
		//		MyDialog.alert('活动跳转链接不能为空，请准确填写');
		//		return;
		//	}
			
			

			$.post('/admin/system/addAdverActivity.do', {
				'img' : img,
				'type' : type,
				'langCode' : langCode,
				'cityCode' : cityCode,
				'url' : url,
				'shareFlag' : shareFlag,
				'beginTime' : beginTime,
				'endTime' : endTime
			}, function(json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新拉取
					
					search();
					//getLatestAdverActivity();
				}
			}, 'json');

			//$('#aid').val('');
		}

		// 查询最近一条弹窗广告
		function getLatestAdverActivity() {

			$.getJSON("/admin/system/getLatestAdverActivity", {},
					function(json) {
						if (json.success) {
							var htmlTxt = template('listTableTpl', json);
							$('#listTableBody').html(htmlTxt);
							var list = json.object;
							for (var i = 0; i < list.length; i++) {
								var row = list[i];
								var key = row.groupCode + '_' + row.configKey;
								configMap[key] = row;
							}
						} else {
							MyDialog.alert(json.message);
						}
					});

			$('#aid').val('');
		}

		$(function() {
			template.helper('renderConfigValue', function(value) {
				if (value.length <= 50) {
					return value;
				}
				return value.substr(0, 47) + "……";
			});
			template.helper('renderConfigKey', function(value) {
				if (value.length <= 30) {
					return value;
				}
				return value.substr(0, 27) + "……";
			});

			// 选择时间范围控件
			$('#effectiveTimeRange').daterangepicker({
				timePicker : true,
				timePickerIncrement : 1,
				format : 'YYYY-MM-DD HH:mm:ss',
				timePicker12Hour : false,
				locale : {
					cancelLabel : '取消',
					applyLabel : '确定',
					fromLabel : '开始日期',
					toLabel : '结束日期'
				}
			});
			
			$("#picInput").change(uploadPic);
			
			
			$('#city').on('change', search);
			$('#lang').on('change', search);
			
			pathParamOrSelect();

			//getLatestAdverActivity();

		});
	</script>
</body>
</html>
