<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.StoreConstants"%>
<%@ page import="com.mazing.services.store.RecommendBlockAction"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
<title>App资讯--块管理页面</title>
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
					管理资讯数据 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<ol class="breadcrumb">
									<li>${city.name}</li>
									<li class="active"><a href="/admin/news/block/list.xhtm?cityCode=${city.code}"><strong>返回资讯块列表</strong></a></li>
								</ol>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post">
											<input id="cityCodeID" name="cityCode" type="hidden" value="${city.code}" />
											<input id="langCodeID" name="langCode" type="hidden" value="${lang.code}" />
											
											<input id="shareImgID" name="shareImg" type="hidden" />
											<input id="blockImgID" name="blockImg" type="hidden" />
											<input id="pubTimeID" name="pubTime" type="hidden"/>
											<input id="bidID" name="bid" type="hidden" />
											<input id="typeID" name="type" type="hidden" />
											<input id="actionID" name="action" type="hidden" />
											<input id="sequenceID" name="sequence" type="hidden" />

											<div class="form-group">
												<label class="col-md-2 control-label">当前城市/语言</label>
												<div class="col-md-3">
													<p class="form-control-static">${city.name}&nbsp;/&nbsp;${lang.zhName}</p>
												</div>
											</div>
											<div id="imgPreviewDiv" class="form-group">
												<label class="col-md-2 control-label">专题图片<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="imgPreview" width="200" src="" />
													<input id="picInput" callback="previewImg" type="file" value="更换图片...">
												</div>
												<div class="col-md-3">
													<p class="text-red">资讯首页大图的尺寸为750*320</p>
													<p class="text-red">上传的图片将会原图保存,请按要求严格上传图片</p>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">标题<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="titleID" class="form-control" name="title" placeholder="请输入资讯的标题">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">作者<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="author" class="form-control" name="author" placeholder="请输入资讯的作者">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">阅读数<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input type="number" id="readCount" class="form-control" name="readCount" placeholder="请输入阅读数">
												</div>
												<div class="col-md-3 text-info">
													修改该数据时, 不能小于原有数据值
												</div>
											</div>
											
											<div id="urlDiv" class="form-group">
												<label class="col-md-2 control-label">URL<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="urlID" class="form-control" name="url" placeholder="请输入资讯跳转地址">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">发布时间<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<div class="input-group">
														<div class="input-group-addon">
															<i class="fa fa-calendar"></i>
														</div>
														<input type="text" class="form-control pull-right active" name="datePicker" id="datePicker">
													</div>
												</div>
												<div class="col-md-3 text-info">
													未到该时间, 用户将不会看到该资讯信息
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">是否显示发布时间</label>
												<div class="col-md-3">
													<select id="showPubID" name="showPub" class="form-control">
														<option value="<%=BooleanConstants.FALSE%>">不显示</option>
														<option value="<%=BooleanConstants.TRUE%>" selected = "selected">显示</option>
													</select>
												</div>
											</div>
											<div id="canShareDiv" class="form-group">
												<label class="col-md-2 control-label">可以分享</label>
												<div class="col-md-3">
													<select id="canShareID" name="canShare" class="form-control">
														<option value="<%=BooleanConstants.FALSE%>">不可分享</option>
														<option value="<%=BooleanConstants.TRUE%>" selected = "selected">可分享</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">作者头像<span style="color: red;"></span></label>
												<div class="col-md-3">
													<img id="authorPicPreview" width="80" src="" />
													<input id="authorPicFile" type="file" value="更换头像..." onChange="uploadAuthorPic()">
													<input id="authorPicInput" type="hidden" name="authorPic">
												</div>
												<div class="col-md-3 text-info">
												<p class="text-red">默认是当前管理员米星app的头像
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">c-blog餐厅关联</label>
												<div class="col-md-3">
													<input id="sinfoId" class="form-control" name="sinfoId" placeholder="请输入cblog店铺id，多个用英文逗号分割...">
												</div>
												<div class="col-md-3 text-info">
													如不填写，则当作是普通资讯；如填写，则关联到该c-blog店铺的星选列表中；多个用英文逗号分割
												</div>
											</div>
											<!-- <div id="actionParamsDiv" class="form-group hidden">
												<label class="col-md-2 control-label">附带参数</label>
												<div class="col-md-3">
													<input id="actionParamsID" class="form-control" name="actionParams">
													<button id="tagID" type="button" class="btn btn-link hidden" onclick="showTagModal();">选择餐厅TAG</button>
												</div>
											</div> -->
											<div id="shareTitleDiv" class="form-group">
												<label class="col-md-2 control-label">分享给朋友的标题<span style="color: red;"></span></label>
												<div class="col-md-3">
													<input id="shareTitleID" class="form-control"  name="shareTitle" placeholder="请输入分享给朋友的标题">
												</div>
												<div class="col-md-3 text-info">
													最多只能输入32个字符
												</div>
											</div>
											<div id="shareTimelineTitleDiv" class="form-group">
												<label class="col-md-2 control-label">分享给朋友圈的标题<span style="color: red;"></span></label>
												<div class="col-md-3">
													<input id="shareTimelineTitleID" class="form-control"  name="shareTimelineTitle" placeholder="请输入分享给朋友圈的标题">
												</div>
												<div class="col-md-3 text-info">
													最多只能输入32个字符
												</div>
											</div>
											<div id="shareImgDiv" class="form-group">
												<label class="col-md-2 control-label">分享的图标<span style="color: red;"></span></label>
												<div class="col-md-3">
													<img id="shareImgPreview" width="80" src="" />
													<input id="sharePicInput" type="file" value="更换图片...">
												</div>
												<div class="col-md-3 text-info">
												<p class="text-red">注意：该图同时用于指南列表/指南搜索列表的图片显示</p>
													分享的图标会原图保存，不做裁剪<br>
													请保证它的尺寸为200x200，文件不超过20k<br>
													否则会使用公众号默认图片（米图）
												</div>
											</div>
											<div id="shareContentDiv" class="form-group">
												<label class="col-md-2 control-label">分享的内容简介<span style="color: red;"></span></label>
												<div class="col-md-3">
													<input id="shareContentID" class="form-control"  name="shareContent" placeholder="请输入分享的简介内容">
												</div>
												<div class="col-md-3 text-info">
													最多只能输入64个字符
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
		
		<!-- 餐厅列表Modal -->
		<div class="modal fade" id="storeModal" tabindex="-1" role="dialog" aria-labelledby="storeModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="storeModalLabel">选择餐厅</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input id="storeKeyword" name="storeKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="searchStore();">Go!</button>
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
									<script id="storeTableTpl" type="text/html">
										{{each object as value i}}
										<tr>
											<td>
												<a href="javascript:selectStore({{value.storeId}})">{{value.storeName}}</a>
											</td>
											<td>
												{{renderStoreStar value.star}}
											</td>
											<input id="store_name_{{value.storeId}}" type="hidden" value="{{value.storeName}}" />
											<input id="store_content_{{value.storeId}}" type="hidden" value="{{value.content}}" />
											<input id="store_logoPath_{{value.storeId}}" type="hidden" value="{{value.logoPath}}" />
											<input id="store_topicImg_{{value.storeId}}" type="hidden" value="{{value.topicImg}}" />
										</tr>
										{{/each}}
									</script>
								</table>
							</div>
						</div>
						<div class="row" id="storePaginator"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
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
	
		var imgDomain = '${imgDomain}';


		// 上传文件
		// 有两个组件会调用到
		function uploadSharePic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			//创建FormData对象
			var data = new FormData();
			//$("#shareImgID").val("xx");
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
		
		// 上传文件
		// 有两个组件会调用到
		function uploadBlockPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			//创建FormData对象
			var data = new FormData();
			//$("#blockImgID").val("yy");
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
						$("#imgPreview").attr("src", imgDomain + img);
						$("#blockImgID").val(img);
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}

		// 如果url中带了 bid 参数，则转为修改数据
		function toEdit(bid) {
			$.getJSON('/admin/news/block/get?bid=' + bid, {}, function(json) {
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
			if(block.cityCode != nowCity) {
				MyDialog.alert('当前城市与块数据中的城市不一致，请从新选择需要编辑的块数据！');
				return;
			}
			// 设置选中的类型，并设置为不可更改
			$("#bidID").val(block.bid);
			$("#imgPreview").attr("src", imgDomain + block.img);
			$("#blockImgID").val(block.img);
			$("#titleID").val(block.title);
			$("#urlID").val(block.url);
			$("#author").val(block.author);
			$("#readCount").val(block.readCount);
			var date = new Date();
			date.setTime(block.pubTime);
			$("#datePicker").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			$("#pubTimeID").val(date.Format("yyyy-MM-dd hh:mm:ss"));
			
			$('#authorPicPreview').attr('src', IMAGE_DOMAIN + block.authorPic);
			if(!block.authorPic){
				getAdminInfo();
			}
			
			$('#authorPicInput').val(block.authorPic);
			
			$("#canShareID").val(block.canShare);
			$("#shareImgID").val(block.shareImg);
			$("#shareImgPreview").attr("src", imgDomain + block.shareImg);// logo view
			$("#shareTitleID").val(block.shareTitle);
			
			$("#shareTimelineTitleID").val(block.shareTimelineTitle);
			$("#shareContentID").val(block.shareContent);
			
			$("#typeID").val(block.type);
			$("#actionID").val(block.action);
			$("#sequenceID").val(block.sequence);
		}


		// 提交表单数据
		function submitUpdate() {
			var sinfoId = $("#sinfoId").val() || '';
			if(sinfoId && !/^[0-9,]+$/.test(sinfoId)){
				return alertMsg('c-blog关联id填写错误！');
			}
			
			// 保证所有cblog-id都是对的
			var sinfoIdArray = sinfoId ? sinfoId.split(',') : [];
			requestNode('/store/admin/getstoreinfos', 'get', {sinfoId:sinfoId,langCode:'zh'}, function(data){
				if(sinfoIdArray.length > 0 && data.length > 0){
					for(var i = 0; i < sinfoIdArray.length; i++){
						var storeinfo = data[i];
						if(!storeinfo){
							return alertMsg('找不到sinfoId='+sinfoIdArray[i]+'对应的cblog店铺，请检查清楚！');
						}
					}
				}
			
				$("#editForm").ajaxSubmit({
					url: '/admin/news/block/edit.do', type: 'post'
					, beforeSubmit: formValidate // 表单验证 
					, success: function(json) {
						MyDialog.alert(json.message, function() {
							if(json.success) {
								var bid = $.getUrlParam("bid");
								if(bid){
									updateNewsStoreinfoBlock(sinfoId, bid, $("#pubTimeID").val());
								} else {
									addNewsStoreinfoBlock(sinfoId, json.object, $("#pubTimeID").val());
								}
								var cityCode = $("#cityCodeID").val();
								var langCode = $("#langCodeID").val();
								window.location.href = '/admin/news/block/list.xhtm?cityCode=' + cityCode+ '&langCode=' + langCode;
							}
						});
					}
				});
			});
		}
		function formValidate() {
			// 必传的参数
			var cityCode = $('#cityCodeID').val();
			if(!cityCode) return alertMsg('参数错误，未知的城市！');
			var langCode = $('#langCodeID').val();
			if(!langCode) return alertMsg('参数错误，未知的语言！');
			var img = $('#blockImgID').val();
			if(!img) return alertMsg('请上传资讯专题图片！');
			var title = $('#titleID').val();
			if(!title) return alertMsg('请输入资讯标题！');
			var url = $("#urlID").val();
			if(!url) return alertMsg('请输入资讯跳转的地址！');
			var author = $("#author").val();
			if(!author) return alertMsg('请输入资讯的作者！');
			var authorPic = $("#authorPicInput").val();
			if(!authorPic) return alertMsg('请上传资讯作者头像！');
			var readCount = $("#readCount").val();
			if(!readCount) return alertMsg('请输入资讯的阅读数！');
			var pubTime = $("#pubTimeID").val();
			if(!pubTime) return alertMsg('请输入资讯的发布时间！');
			var sinfoId = $("#sinfoId").val();
			if(sinfoId && !/^[0-9,]+$/.test(sinfoId)){
				return alertMsg('c-blog关联id填写错误！');
			}
			
			var canShare = $('#canShareID').val();
			if(1 == canShare) {
				var shareTitle = $('#shareTitleID').val();
				if(!shareTitle) return alertMsg('请输入分享给朋友的标题！');
				
				if(shareTitle.length>32) return alertMsg('分享给朋友的标题 字符数不能超过32个');
				
				var shareTimelineTitle = $('#shareTimelineTitleID').val();
				if(!shareTimelineTitle) return alertMsg('请输入分享给朋友圈的标题！');
				
				if(shareTimelineTitle.length>32) return alertMsg('分享给朋友圈的标题 字符数不能超过32个');
				
				var shareImg = $('#shareImgID').val();
				if(!shareImg) return alertMsg('请上传一张分享的标题图片！');
				var shareContent = $('#shareContentID').val();
				if(!shareContent) return alertMsg('请输入分享的内容简介！');
				
				if(shareContent.length>64) return alertMsg('分享的内容简介 字符数不能超过64个');
			}
		}
		
		function alertMsg(msg) {// 显示错误，同时返回 false
			MyDialog.alert(msg);
			return false;
		}

		// 显示或者隐藏指定的dom节点
		function showOrHideDiv(divId, showOrHide) {
			if(showOrHide)
				$("#" + divId).removeClass("hidden").addClass("show").show();
			else
				$("#" + divId).removeClass("show").addClass("hidden").hide();
		}
		
		function cnahgeCanShare() {
			var share = $('#canShareID').val();
			var show = (1 == share);
			showOrHideDiv('shareImgDiv', show);
			showOrHideDiv('shareTitleDiv', show);
			showOrHideDiv('shareContentDiv', show);
			showOrHideDiv('shareTimelineTitleDiv', show);
		}
	
		/* function previewImg(img) {
			$("#imgPreview").attr("src", imgDomain + img);
			$("#imgID").val(img);
		} */

		$(function() {
			$("#sharePicInput").change(uploadSharePic);
			$("#canShareID").change(cnahgeCanShare);
			$("#picInput").change(uploadBlockPic);

			// 选择时间
			// reference: http://www.bootcss.com/p/bootstrap-datetimepicker/index.htm
			$('#datePicker').datetimepicker({
				format:'yyyy-mm-dd hh:ii:ss',
				startDate:new Date().Format("yyyy-MM-dd hh:mm:ss"),
				autoclose: true,
				startView: 1, maxView: 1
			}).on('changeDate', function() {
				var str = $('#datePicker').val();
				$('#pubTimeID').val(str);
			});
			
			// 是否是修改数据（url中是否带有bid）
			var bid = $.getUrlParam("bid");
			if(bid) { 
				toEdit(bid); // 编辑
				getNewsStoreinfoBlock(bid);
			} else {
				getAdminInfo();
			}
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
		
		function getAdminInfo(){
			requestNode('/oauth/admin/info', 'get', {}, function(data){
				var url = IMAGE_DOMAIN + data.profile.profilePic;
				$('#authorPicPreview').attr('src', url);
				$('#authorPicInput').val(data.profile.profilePic);
			});
		}
		
		function uploadAuthorPic(){
			uploadFile($('#authorPicFile'), function(data){
				var url = IMAGE_DOMAIN + data;
				$('#authorPicPreview').attr('src', url);
				$('#authorPicInput').val(data);
			}, 200, 200);
		}
		
		function addNewsStoreinfoBlock(sinfoId, bid, pubTime){
			if(!sinfoId){
				return;
			}
			if(!/^[0-9,]+$/.test(sinfoId)){
				MyDialog.alert('c-blog关联id填写错误！');
				return;
			}
			requestNode('/store/admin/addnewsstoreinfoblock', 'post', {sinfoId:sinfoId,bid:bid,pubTime:pubTime}, function(data){
			});
		}
		
		function getNewsStoreinfoBlock(bid){
			if(!bid){
				return;
			}
			requestNode('/store/admin/getnewsstoreinfoblock', 'get', {bid:bid}, function(data){
				let sinfoId = [];
				for(var cblog of data){
					sinfoId.push(cblog.sinfoId);
				}
				$("#sinfoId").val(sinfoId.join(','));
			});
		}
		
		function updateNewsStoreinfoBlock(sinfoId, bid, pubTime){
			if(sinfoId && !/^[0-9,]+$/.test(sinfoId)){
				MyDialog.alert('c-blog关联id填写错误！');
				return;
			}
			requestNode('/store/admin/updatenewsstoreinfoblock', 'post', {sinfoId:sinfoId,bid:bid,pubTime:pubTime}, function(data){
			});
		}
	</script>
</body>
</html>
