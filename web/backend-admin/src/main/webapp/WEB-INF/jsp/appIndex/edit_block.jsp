<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockAction"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.services.store.StoreConstants"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<title>App首页--块管理页面</title>
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
				<h1 id="titleMessage">
					管理数据块 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<ol class="breadcrumb">
									<li>${city.name}&nbsp;/&nbsp;${lang.zhName}</li>
									<li class="active"><a href="/admin/appIndex/block/list.xhtm?cityCode=${city.code}&langCode=${lang.code}"><strong>返回块列表</strong></a></li>
								</ol>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post">
											<input id="cityCodeID" name="cityCode" type="hidden" value="${city.code}" />
											<input id="langCodeID" name="langCode" type="hidden" value="${lang.code}" />
											<input id="activeStartMinuteID" name="activeStartMinute" type="hidden" />
											<input id="activeEndMinuteID" name="activeEndMinute" type="hidden" />
											<input id="shareImgID" name="shareImg" type="hidden" />
											<input id="bidID" name="bid" type="hidden" />

											<div class="form-group">
												<label class="col-md-2 control-label">当前城市/语言</label>
												<div class="col-md-3">
													<p class="form-control-static">${city.name}&nbsp;/&nbsp;${lang.zhName}</p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">类型<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<select id="typeID" name="type" class="form-control">
														<%for(RecommendBlockType type : RecommendBlockType.values()) {%>
														<option value="<%=type.getValue()%>"><%=type.getName()%></option>
														<%}%>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">标题<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="titleID" class="form-control" name="title" placeholder="请输入块的标题">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">生效时间<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-xs-4">
															<input id="activeStartMinutePreviewID" type='text' class="form-control input-sm" readonly="readonly" />
														</div>
														<div class="col-xs-1">
														~
														</div>
														<div class="col-xs-4">
															<input id="activeEndMinutePreviewID" type='text' class="form-control input-sm" readonly="readonly" />
														</div>
													</div>
												</div>
												<div class="col-md-3 text-info">
													截止时间00:00就是24:00
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">显示条件（登录状态）<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<select id="activeLoginStatusID" name="activeLoginStatus" class="form-control">
														<option value="-1">不限制</option>
														<option value="<%=BooleanConstants.TRUE%>">登录了才显示</option>
														<option value="<%=BooleanConstants.FALSE%>">未登录才显示</option>
													</select>
												</div>
											</div>
											<div id="autoSortingDiv" class="form-group hidden">
												<label class="col-md-2 control-label">排序方式</label>
												<div class="col-md-3">
													<label><input id="autoSortingID" type="checkbox" name="autoSorting" value="1">自动排序</label>
												</div>
												<div class="col-md-3">
													<p class="text-blue">不勾选，排列方式即为你设定的顺序</p>
													<p class="text-red">自动排列会根据用户的位置进行排序（具体规则@产品|开发）</p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">标题点击动作</label>
												<div class="col-md-3">
													<select id="actionID" name="action" class="form-control">
														<option value="">无动作</option>
														<%for(RecommendBlockAction action : RecommendBlockAction.values()) {%>
														<option value="<%=action.getValue()%>"><%=action.getName()%></option>
														<%}%>
													</select>
												</div>
											</div>
											<div id="urlDiv" class="form-group hidden">
												<label class="col-md-2 control-label">URL<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="urlID" class="form-control" name="url" placeholder="请输入页面地址">
												</div>
											</div>
											<div id="canShareDiv" class="form-group hidden">
												<label class="col-md-2 control-label">可以分享</label>
												<div class="col-md-3">
													<select id="canShareID" name="canShare" class="form-control">
														<option value="<%=BooleanConstants.FALSE%>">不可分享</option>
														<option value="<%=BooleanConstants.TRUE%>">可分享</option>
													</select>
												</div>
											</div>
											<div id="autoPushDiv" class="form-group hidden">
												<label class="col-md-2 control-label">内容推送方式</label>
												<div class="col-md-3">
													<label><input id="autoPushID" type="checkbox" name="autoPush" value="1">自动推送</label>
												</div>
												<div class="col-md-3">
													<p class="text-blue">不勾选即为人工推送（需要配置数据项）</p>
													<p class="text-red">同一时间生效的块不能超过<%=StoreConstants.BLOCK_EFFECTIVE_NUM%>个</p>
												</div>
											</div>
											<div id="actionParamsDiv" class="form-group hidden">
												<label class="col-md-2 control-label">附带参数</label>
												<div class="col-md-3">
													<input id="actionParamsID" class="form-control" name="actionParams">
													<button id="tagID" type="button" class="btn btn-link hidden" onclick="showTagModal();">选择餐厅TAG</button>
												</div>
											</div>
											<div id="shareTitleDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享的标题<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="shareTitleID" class="form-control" name="shareTitle" placeholder="请输入分享的标题">
												</div>
											</div>
											<div id="shareImgDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享的图标<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="shareImgPreview" width="80" src="" />
													<input id="sharePicInput" type="file" value="更换图片...">
												</div>
												<div class="col-md-3 text-info">
													分享的图标会原图保存，不做裁剪<br>
													请保证它的尺寸为200x200，文件不超过20k<br>
													否则会使用公众号默认图片（米图）
												</div>
											</div>
											<div id="shareContentDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享的内容简介<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="shareContentID" class="form-control" name="shareContent" placeholder="请输入分享的简介内容">
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-offset-2">
													<button class="btn btn-primary" type="button" onclick="submitUpdate();">提交</button>
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

		<!-- 餐厅TAG Modal -->
		<div class="modal fade" id="tagModal" tabindex="-1" role="dialog" aria-labelledby="tagModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="tagModalLabel">选择餐厅TAG</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th width="30%">分类</th>
											<th width="70%">TAG<span class="text-red">（建议不要超过9个）</span></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${tagCategoryList}" var="category" >
											<tr class="<c:if test="${category.appShow != 1}">text-red</c:if>">
												<td>${category.categoryName}</td>
												<td>
													<c:forEach items="${category.tagList}" var="tag" varStatus="status">
														<div class="checkbox-inline">
															<label>
																<input type="checkbox" name="selectTag" value="${tag.tagId}">${tag.tagName}
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
						<div class="row" id="dishPaginator"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="selectTag();">确认</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- ./wrapper -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		var imgDomain = '${imgDomain}';

		// 打开模态窗口
		function showTagModal() {
			$("#tagModal").modal("show");
		}
		function selectTag() {
			var tagIds = '';
			$('input[name="selectTag"]:checked').each(function() {
				var id = $(this).val();
				if(tagIds) tagIds += (','+id);
				else tagIds = id;
			});
			$("#actionParamsID").val(tagIds);
		}

		// 上传文件
		// 有两个组件会调用到
		function uploadPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			//创建FormData对象
			var data = new FormData();
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
						$("#shareImgPreview").attr("src", imgDomain + img);
						$("#shareImgID").val(img);
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}

		// 更换target事件
		function changeAction() {
			var action = $("#actionID").val();
			if ("h5" == action) {
				$('#actionParamsID').attr('placeholder', '请输入需要附带的参数');
				showInOrHideOther(["urlDiv", "actionParamsDiv", "canShareDiv"]);
				cnahgeCanShare();
			} else if ("index" == action) {
				var tobj = $('#actionParamsID').val('').removeAttr('readonly').attr('placeholder', '请选择TAG...');
				showInOrHideOther(["tagID", "actionParamsDiv"]);
			} else {
				showInOrHideOther([]);
			}
		}
		// 显示指定的div，其他的div隐藏
		// nameDiv、imgPreviewDiv
		var allDiv = ["tagID", "urlDiv", "canShareDiv", "autoSortingDiv", "actionParamsDiv", "shareImgDiv", "shareTitleDiv", "shareContentDiv"];
		function showInOrHideOther(showIdArray) {
			var allLength = allDiv.length;
			for(var i = 0; i < allLength; i++) {
				var div = allDiv[i];
				var showOrHide = false;// 默认隐藏
				var arrLength = showIdArray.length;
				for(var j = 0; j < arrLength; j++) {
					if(div == showIdArray[j]) {
						showOrHide = true;
						break;
					}
				}
				// to dom
				showOrHideDiv(div, showOrHide);
			}
		}
		// 显示或者隐藏指定的dom节点
		function showOrHideDiv(divId, showOrHide) {
			if(showOrHide)
				$("#" + divId).removeClass("hidden").addClass("show").show();
			else
				$("#" + divId).removeClass("show").addClass("hidden").hide();
		}

		// 如果url中带了 bid 参数，则转为修改数据
		function toEdit(bid) {
			$.getJSON('/admin/appIndex/block/get?bid=' + bid, {}, function(json) {
				if (json.success) {
					showEdit(json.object);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}
		// 初始化修改页面
		function showEdit(block) {
			var nowCity = '${city.code}';
			var nowLang = '${lang.code}';
			if(block.cityCode != nowCity) {
				MyDialog.alert('当前城市与块数据中的城市不一致，请从新选择需要编辑的块数据！');
				return;
			}
			if(block.langCode != nowLang) {
				MyDialog.alert('当前语言与块数据中的语言不一致，请从新选择需要编辑的块数据！');
				return;
			}

			// 设置选中的类型，并设置为不可更改
			$("#bidID").val(block.bid);
			$("#typeID").val(block.type);
			$("#actionID").val(block.action);
			$("#titleID").val(block.title);
			$("#urlID").val(block.url);
			if(<%=BooleanConstants.TRUE%> == block.autoPush) $("#autoPushID").attr('checked', true);
			if(<%=BooleanConstants.TRUE%> == block.autoSorting) $("#autoSortingID").attr('checked', true);
			
			$('#activeLoginStatusID').val(block.activeLoginStatus);
			$("#activeStartMinuteID").val(block.activeStartMinute);
			$("#activeStartMinutePreviewID").val(minuteToHM(block.activeStartMinute));
			$("#activeEndMinuteID").val(block.activeEndMinute);
			$("#activeEndMinutePreviewID").val(minuteToHM(block.activeEndMinute));
			
			$("#canShareID").val(block.canShare);
			$("#shareImgID").val(block.shareImg);
			$("#shareImgPreview").attr("src", imgDomain + block.shareImg);// logo view
			$("#shareTitleID").val(block.shareTitle);
			$("#shareContentID").val(block.shareContent);
			
			delSelect(block.type);
			// 如果是index，还需要设置选中tag
			if('index' == block.action) {
				showOrHideDiv("tagID", true);// 打开 TAG的选择div
				// 初始化tag选中的项目
				var str = block.actionParams;
				if(str) {
					var arr = str.split(',');
					onSelect(arr);
				}
			}
			
			$("#actionParamsID").val(block.actionParams);
			cnahgeType();
		}
		// 选择select组件，保留指定的选项，其他的删除（是option节点移除）
		function delSelect(retain) {
			if(!retain) return;
			$("#typeID option[value!='"+retain+"']").remove();
			changeAction();
		}

		// tag选择器，默认选中arr中的数据，其他的不选中
		function onSelect(arr) {
			$('input[name="selectTag"]').each(function() {
				var ok = false;
				var val = $(this).val();
				for(var i = 0; i < arr.length; i++)
					if(val == arr[i]) {
						ok = true;
						break;
					}
				// 是否选中
				$(this).attr("checked", ok);
			});
		}

		// 提交表单数据
		function submitUpdate() {
			$("#editForm").ajaxSubmit({
				url: '/admin/appIndex/block/edit.do', type: 'post'
				, beforeSubmit: formValidate // 表单验证 
				, success: function(json) {
					MyDialog.alert(json.message, function() {
						if(json.success) {
							var cityCode = $("#cityCodeID").val();
							var langCode = $("#langCodeID").val();
							window.location.href = '/admin/appIndex/block/list.xhtm?cityCode=' + cityCode + '&langCode=' + langCode;
						}
					});
				}
			});
		}
		function formValidate() {
			// 必传的参数
			var cityCode = $('#cityCodeID').val();
			if(!cityCode) return alertMsg('参数错误，未知的城市！');
			var langCode = $('#langCodeID').val();
			if(!langCode) return alertMsg('参数错误，未知的语言！');
			var type = $('#typeID').val();
			if(!type) return alertMsg('请选择块类型！');
			var title = $('#titleID').val();
			if(!title) return alertMsg('请输入块标题！');
			if(32 < title.length) return alertMsg('块标题长度不能超过32');
			
			var startMinute = $('#activeStartMinuteID').val();
			if(!startMinute) return alertMsg('请设置开始生效时间！');
			var endMinute = $('#activeEndMinuteID').val();
			if(!endMinute) return alertMsg('请设置截止生效时间！');
			if(!valiAutoPushMinute()) return false;// vali方法内有弹窗，所以这个不在弹了
			
			var action = $('#actionID').val();
			// 分开校验的参数
			if('h5' == action) {
				var url = $('#urlID').val();
				if(!url) return alertMsg('请输入HTML5的URL地址！');
				if(250 < url.length) return alertMsg('URL过长！');
				// 如果允许分享
				var canShare = $('#canShareID').val();
				if(1 == canShare) {
					var shareTitle = $('#shareTitleID').val();
					if(!shareTitle) return alertMsg('请输入分享的标题！');
					if(60 < shareTitle.length) return alertMsg('分享的标题长度不能超过60');
					var shareImg = $('#shareImgID').val();
					if(!shareImg) return alertMsg('请上传一张分享的标题图片！');
					var shareContent = $('#shareContentID').val();
					if(!shareContent) return alertMsg('请输入分享的内容简介！');
					if(64 < shareContent.length) return alertMsg('分享的内容简介长度不能超过64');
				}
			}
			else if('index' == action) {
				 // 接受不选任何tag
			}
			// actionParamsID
		}
		function valiAutoPushMinute() {// 自动推送数据，校验开始结束时间是否重叠多次
			var type = $('#typeID').val();
			if('store' != type) return true;// 餐厅列表才校验
			var use = $("#autoPushID").is(':checked');//.attr('checked');
			if(!use) return true;
			
			var startMinute = $('#activeStartMinuteID').val();
			var endMinute = $('#activeEndMinuteID').val();
			var cityCode = $('#cityCodeID').val();
			var langCode = $('#langCodeID').val();
			var bid = $("#bidID").val();

			// vali（同步的形式）
			var url = '/admin/appIndex/block/valiStartEndMinute?cityCode='+cityCode+'&langCode='+langCode+'&startMinute='+startMinute+'&endMinute='+endMinute;
			if(bid) url += ('&bid='+bid);//如果是修改，需要带上bid
			var result = false;
			$.ajax({
				url: url , type: 'get' , async: false , fail: function(data){ return false; }
				, success: function(json){
					if(!json.success) MyDialog.alert(json.message);
					result = json.success;
				}
			});
			return result;
		}
		function alertMsg(msg) {// 显示错误，同时返回 false
			MyDialog.alert(msg);
			return false;
		}

		function cnahgeCanShare() {
			var share = $('#canShareID').val();
			var show = (1 == share);
			showOrHideDiv('shareImgDiv', show);
			showOrHideDiv('shareTitleDiv', show);
			showOrHideDiv('shareContentDiv', show);
			// h5可分享，参数为：newIndexBlockHtml5
			var tobj  = $('#actionParamsID');
			if(show) {
				tobj.val('newIndexBlockHtml5');
				tobj.attr('readonly', 'readonly');
			} else {
				tobj.val('');
				tobj.removeAttr('readonly');
			}
		}

		function cnahgeType() {
			var type = $("#typeID").val();
			// 餐厅：是否显示 自动推荐
			if('store' ==  type) showOrHideDiv('autoPushDiv', true);
			else showOrHideDiv('autoPushDiv', false);
			// 美食：是否显示  自动排序
			if('dish' ==  type) showOrHideDiv('autoSortingDiv', true);
			else showOrHideDiv('autoSortingDiv', false);
		}

		$(function() {
			$("#typeID").change(cnahgeType);
			$("#actionID").change(changeAction);
			$("#sharePicInput").change(uploadPic);
			$("#canShareID").change(cnahgeCanShare);

			// 选择时间
			$('#activeStartMinutePreviewID').datetimepicker({ language: 'zh-CN', format: 'hh:ii', startView: 1, maxView: 1, minuteStep: 5, autoclose: true, keyboardNavigation: false, startDate: '00:00' })
			.on('changeDate', function() {
				var str = $('#activeStartMinutePreviewID').val();
				strToMinuteSetVal(str, 'activeStartMinuteID');
			});
			$('#activeEndMinutePreviewID').datetimepicker({ language: 'zh-CN', format: 'hh:ii', startView: 1, maxView: 1, minuteStep: 5, autoclose: true, keyboardNavigation: false, startDate: '24:00' })
			.on('changeDate', function() {
				var str = $('#activeEndMinutePreviewID').val();
				if(str == '00:00') {
					str = '24:00';
					$('#activeEndMinutePreviewID').val(str);// 灰机，这个不起作用，还是显示 00:00
				}
				strToMinuteSetVal(str, 'activeEndMinuteID');
			});
			
			// 是否是修改数据（url中是否带有bid）
			var bid = $.getUrlParam("bid");
			if(bid) { toEdit(bid); }// 编辑
		});
		
		// str是一个 hh:mm格式的时间，将其转换成分钟数，并赋值给 #id 组件
		function strToMinuteSetVal(str, id) {
			var m = hmToMinute(str);
			$('#'+id).val(m);
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
			if(10 > h) h = ('0'+h);
			if(10 > m) m = ('0'+m);
			return h+':'+m;
		}
	</script>
</body>
</html>
