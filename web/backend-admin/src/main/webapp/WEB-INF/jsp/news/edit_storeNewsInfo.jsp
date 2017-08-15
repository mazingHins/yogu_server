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
									<li class="active"><a href="/admin/news/block/storeInfo/list.xhtm"><strong>返回餐厅资讯列表</strong></a></li>
								</ol>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post">
											<input id="thumbImgID" name="thumbImg" type="hidden" />
											<input id="showImgsID" name="showImgs" type="hidden" />
											<input id="storeIdID" name="storeId" type="hidden" />
											<input id="mapImgID" name="mapImg" type="hidden" />
											<input id="sinfoIdID" name="sinfoId" type="hidden" />
											
											<input id="showMazingPayID" name="showMazingPay" type="hidden" />
											

											<!-- <div id="imgPreviewDiv" class="form-group">
												<label class="col-md-2 control-label">专题图片<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="imgPreview" width="200" src="" />
													<input id="picInput" callback="previewImg" type="file" value="更换图片...">
												</div>
												<div class="col-md-3">
													<p class="text-red">新上传的图片将会原图保存</p>
													<p class="text-red">请您确保他们的尺寸是合适，并且一致的</p>
												</div>
											</div> -->
											<div id="storeSelectDiv" class="form-group">
												<label class="col-md-2 control-label">米星餐厅<span style="color: red;"></span></label>
												<div class="col-md-3">
													<a id="showModalName" class="btn btn-default" href="javascript:showModal();">请选择餐厅</a>
													<a id="cancelBind" class="btn btn-danger" href="javascript:cancelBindStore();">取消关联餐厅</a>
												</div>
												<div class="col-md-4 text-info">
													<p>选择米星餐厅后, 表示该资讯的餐厅已入驻米星</p>
													<p class="text-red">并会自动填：<b>餐厅名称、餐厅地址、经纬度</b></p>
												</div>
											</div>
											
										<div class="form-group">
												<label class="col-md-2 control-label">是否显示线上餐厅</label>
													<div class="col-md-4">
														<label>
															<input type="radio" id="onlineShow" name="showOnlineStore" value="<%=BooleanConstants.TRUE%>" />
																是
														</label>
														<label>
															<input type="radio" id="onlineHide" name="showOnlineStore" value="<%=BooleanConstants.FALSE%>"  checked />
																否
															</label>
													</div>
							
											<div class="col-md-3 text-red">
												是: 绑定了线上的资讯餐厅 将显示 在线订餐 (米星餐厅)
												否： 不显示在线订餐
											</div>
										</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">餐厅名<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="storeNameID" class="form-control" name="storeName" placeholder="请输入餐厅名">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">轮播头图<span style="color: red;">*</span></label>
												<div id="showImgsDiv" class="col-md-3">
												</div>
												<div class="col-md-3">
													<p class="text-red">资讯首页大图的尺寸为750*564</p>
													<p class="text-red">上传的图片将会原图保存,请按要求严格上传图片</p>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label"></label>
												<div class="col-md-3">
													<a id="showImgsLinkID" class="btn btn-default" href="javascript:addShowImgs();">添加图片</a>
													<!-- <a id="showImgsLinkID" class="btn btn-danger" href="javascript:clearShowImgs();">清空图片</a> -->
												</div>
											</div>
											
											<div id="thumbImgDiv" class="form-group">
												<label class="col-md-2 control-label">餐厅小图<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="thumbImgPreview" width="200" src="" />
													<input id="thumbImgInput" preview="thumbImgPreview" cache="thumbImgID" type="file" value="更换图片...">
												</div>
												<div class="col-md-3">
													<p class="text-red">新上传的图片将会原图保存</p>
													<p class="text-red">请您确保他们的尺寸是合适，并且一致的</p>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">推荐菜品<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="recDishsID" class="form-control" name="recDishs" placeholder="请输入推荐菜品,多个菜品名以英文逗号分隔">
												</div>
												<div class="col-md-3">
													<p class="text-red">注意：多个菜品之间用英文逗号分隔,用其他符号将会被当做只有一个菜品</p>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">口味评分<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="tasteID" class="form-control" name="taste" type="number" placeholder="请输入口味评分">
												</div>
												<div class="col-md-3 text-info">
													请输入30, 代表3分
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">环境评分<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="envID" class="form-control" name="env" type="number" placeholder="请输入环境评分">
												</div>
												<div class="col-md-3 text-info">
													请输入30, 代表3分
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">性价比评分<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="costEffectiveID" class="form-control" name="costEffective" type="number" placeholder="请输入性价比评分">
												</div>
												<div class="col-md-3 text-info">
													请输入30, 代表3分
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">点评<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<textarea id="reviewsID" class="form-control" name="reviews" placeholder="请输入点评" ></textarea>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">点评人<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="reviewerID" class="form-control" name="reviewer" placeholder="请输入点评人">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">想吃人数<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="wantID" class="form-control" name="want" type="number" placeholder="请输入想吃人数">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">吃过人数<span style="color:red;">*</span></label>
												<div class="col-md-3">
													<input id="eatenID" class="form-control" name="eaten" type="number" placeholder="请输入吃过人数">
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">联系电话<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="phoneID" class="form-control" name="phone" placeholder="请输入联系电话">
												</div>
												<div class="col-md-4 text-info">
													一个号码即可
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">人均消费<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="perConsumeID" class="form-control" name="perConsume" placeholder="请输入人均消费，并带上单位如：123元">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">营业时间<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="openHoursID" class="form-control" name="openHours" placeholder="请输入营业时间">
												</div>
												<div class="col-md-4 text-info">
													无论餐厅是否入驻米星, 都请重新填写
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">餐厅地址<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="addressID" class="form-control" name="address" placeholder="请输入餐厅地址">
												</div>
											</div>
											
											<div id="mapImgDiv" class="form-group">
												<label class="col-md-2 control-label">地址缩略图<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="mapImgPreview" width="200" src="" />
													<input id="mapImgIuput" preview="mapImgPreview" cache="mapImgID" type="file" value="更换图片...">
												</div>
												<div class="col-md-3">
													<p class="text-red">新上传的图片将会原图保存</p>
													<p class="text-red">请您确保他们的尺寸是合适，并且一致的</p>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-md-2 control-label">经度<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="lngID" class="form-control" name="lng" placeholder="请输入经度Longitude">
												</div>
												<div class="col-md-3 text-info">
													<a href="http://lbs.amap.com/console/show/picker" target="_blank">高德坐标拾取器</a>
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-2 control-label">纬度<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="latID" class="form-control" name="lat" placeholder="请输入纬度Latitude">
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
		var imgDomain = 'https://img.mazing.com/';//${imgDomain}

		// 上传文件
		// 有两个组件会调用到
		function uploadThumbPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var preview = input.attr('preview');
			var cache = input.attr('cache');
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
						$("#" + preview).attr("src", imgDomain + img);
						$("#" + cache).val(img);
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}
		
		// 上传文件
		// 有两个组件会调用到
		var uploadShowImgResult = false;
		function uploadShowImg() {
			//创建FormData对象
			uploadShowImgResult = false;
			
			var data = new FormData();
			if ($("#upfile").get(0).files.length < 1) {
				alert('请选择图片再上传');
				return
			}
			data.append("picFile", $("#upfile").get(0).files[0]);
			//为FormData对象添加数据
			//$.each($('#'+id)[0].files, function(i, file) { data.append('picFile', file); });
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
						$("#tmpImg").attr("src", imgDomain + img);
						$("#tmpImg").attr("cus", img);
						uploadShowImgResult = true;
					} else {
						uploadShowImgResult = false;
						MyDialog.alert(json.message);
					}
				}
			});
		}

		// 如果url中带了 bid 参数，则转为修改数据
		function toEdit(sinfoId) {
			$.getJSON('/admin/news/block/storeInfo/get?sinfoId=' + sinfoId, {}, function(json) {
				if (json.success) {
					showEdit(json.object);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}
		// 初始化修改页面
		function showEdit(storeInfo) {
			// 设置选中的类型，并设置为不可更改
			$("#thumbImgID").val(storeInfo.thumbImg);
			$("#mapImgID").val(storeInfo.mapImg);
			$("#sinfoIdID").val(storeInfo.sinfoId);
			
			$("#thumbImgPreview").attr('src',imgDomain + storeInfo.thumbImg);
			$("#mapImgPreview").attr('src',imgDomain + storeInfo.mapImg);
			
			initTopicImg(storeInfo.showImgs);
			
			$("#showMazingPayID").val(storeInfo.showMazingPay);
			
			if(storeInfo.showOnlineStore ==1){
				$("#onlineShow").attr("checked", true);
			}else{
				$("#onlineHide").attr("checked", true);
			}
			
			$("#tasteID").val(storeInfo.taste);
			$("#envID").val(storeInfo.env);
			$("#costEffectiveID").val(storeInfo.costEffective);
			$("#reviewsID").val(storeInfo.reviews);
			$("#reviewerID").val(storeInfo.reviewer);
			
			$("#recDishsID").val(storeInfo.recDishs);
			
			$("#wantID").val(storeInfo.want);
			$("#eatenID").val(storeInfo.eaten);
			$("#phoneID").val(storeInfo.phone);// logo view
			$("#perConsumeID").val(storeInfo.perConsume);
			
			$("#openHoursID").val(storeInfo.openHours);
			$("#addressID").val(storeInfo.address);
			
			$("#lngID").val(storeInfo.lng);
			$("#latID").val(storeInfo.lat);

			$("#storeIdID").val(storeInfo.storeId);
			$("#storeNameID").val(storeInfo.storeName);
			if(0 < storeInfo.storeId)
				$('#showModalName').html(storeInfo.storeName);
		}
		
		// 打开模态窗口
		// 根据target决定打开那个modal
		function showModal() {
			$("#storeModal").modal("show");
			searchStore();
		}
		
		function cancelBindStore() {
			$("#storeIdID").val(0);
			$('#showModalName').html('请选择餐厅');
			MyDialog.alert("取消关联成功");
		}
		
		function searchStore() {
			var keyword = $('#storeKeyword').val();
			$('#storeTable').artPaginate({
				type : 'post',
				url : "/admin/store/query",// 获取数据的地址
				paginator : 'storePaginator',// 显示页码的位置
				tpl : 'storeTableTpl',// 模版ID
				params : { 'keyword' : keyword },// 请求的参数表
				pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
			});
		}
		// 选中了指定的餐厅
		function selectStore(storeId) {
			$("#storeIdID").val(storeId);
			
			$.getJSON('/admin/store/storeDetail?storeId=' + storeId, {}, function(json) {
				if (json.success) {
					$('#showModalName').html(json.object.store.storeName);
					$("#storeNameID").val(json.object.store.storeName);
					$("#phoneID").val(json.object.store.phone);
					$("#addressID").val(json.object.store.address);
					$("#latID").val(json.object.store.lat);
					$("#lngID").val(json.object.store.lng);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}


		// 提交表单数据
		function submitUpdate() {
			$("#editForm").ajaxSubmit({
				url: '/admin/news/block/storeInfo/edit.do', type: 'post'
				, beforeSubmit: formValidate // 表单验证 
				, success: function(json) {
					MyDialog.alert(json.message, function() {
						if(json.success) {
							window.location.href = '/admin/news/block/storeInfo/list.xhtm';
						}
					});
				}
			});
		}
		function formValidate() {
			// 必传的参数
			var storeName = $('#storeNameID').val();
			if(!storeName) return alertMsg('餐厅名称不能为空');
			var showImgs = $('#showImgsID').val();
			if(!showImgs) return alertMsg('请上传轮播的图片');
			var thumbImg = $('#thumbImgID').val();
			if(!thumbImg) return alertMsg('请上传餐厅小图！');
			
			var recDishs = $("#recDishsID").val();
			if(!recDishs) return alertMsg('推荐菜品不能为空！');
			
			var taste = $("#tasteID").val();
			if(!taste) return alertMsg('口味评分不能为空！');
			if(taste<10){
				 return alertMsg('请注意评分规则, 10分只代表1星');
			}
			
			var env = $("#envID").val();
			if(!env) return alertMsg('环境评分不能为空！');
			if(env<10){
				 return alertMsg('请注意评分规则, 10分只代表1星');
			}
			
			var costEffective = $("#costEffectiveID").val();
			if(!costEffective) return alertMsg('性价比评分不能为空！');
			if(costEffective<10){
				 return alertMsg('请注意评分规则, 10分只代表1星');
			}
			
			var reviews = $("#reviewsID").val();
			if(!reviews) return alertMsg('点评不能为空！');
			
			var reviewer = $("#reviewerID").val();
			if(!reviewer) return alertMsg('点评人不能为空！');
			
			var want = $("#wantID").val();
			if(!want) return alertMsg('想吃的人数不能为空！');
			
			var eaten = $("#eatenID").val();
			if(!eaten) return alertMsg('吃过的人数不能为空！');
			
			var phone = $("#phoneID").val();
			if(!phone) return alertMsg('联系电话不能为空！');
			
			var perConsume = $("#perConsumeID").val();
			if(!perConsume) return alertMsg('人均消费不能为空！');
			
			var openHours = $("#openHoursID").val();
			if(!openHours) return alertMsg('营业时间不能为空！');
			
			var address = $("#addressID").val();
			if(!address) return alertMsg('餐厅地址不能为空！');
			
			var lng = $("#lngID").val();
			if(!lng) return alertMsg('经度不能为空！');
			
			var lat = $("#latID").val();
			if(!lat) return alertMsg('纬度不能为空！');
			
			var mapImg = $("#mapImgID").val();
			if(!mapImg) return alertMsg('请上传地址缩略图！');
		}
		
		function alertMsg(msg) {// 显示错误，同时返回 false
			MyDialog.alert(msg);
			return false;
		}

		// 显示或者隐藏指定的dom节点
		/* function showOrHideDiv(divId, showOrHide) {
			if(showOrHide)
				$("#" + divId).removeClass("hidden").addClass("show").show();
			else
				$("#" + divId).removeClass("show").addClass("hidden").hide();
		} */
		

		$(function() {
			// 模版函数：输出餐厅的星级
			template.helper('renderStoreStar', function(star) { return star / 10; });
			
			$("#thumbImgInput").change(uploadThumbPic);
			$("#mapImgIuput").change(uploadThumbPic);

			
			// 是否是修改数据（url中是否带有bid）
			var sinfoId = $.getUrlParam("sinfoId");
			if(sinfoId) { toEdit(sinfoId); }// 编辑
		});
		
		var addShowImgsYes = false;
		function addShowImgs() {
			updateAddressYes = false;
			uploadShowImgResult = false;
			
			BootstrapDialog.show({
				title: '添加餐厅轮播头图',
				message: '你确认要添加餐厅轮播头图吗？'
					+ '<p><img id="tmpImg" /></p>'
					+ '<p><input type="file" id="upfile"></p>'
					+ '<p><input type="button" id="uploadFile" value="上传" onclick="uploadShowImg()"></p>',
				buttons: [{
						label: '确定',
						action: function(dialog) { addShowImgsYes=true; dialog.close(); }
					}, {
						label: '取消',
						action: function(dialog) { addShowImgsYes=false; dialog.close(); }
					}
				],
				onhide: function(dialogRef){
					if (addShowImgsYes && uploadShowImgResult) {
						var img = $("#tmpImg").attr("cus");
						addTopicImg(img);
					}
				}
			});
			
		}
		
		function giveTopicImg(index, src) {
			var html = '<div id="topic_'+index+'">';
			html += '<img width="200" src="'+ src +'" />';
			html += '<a ac="remove" class="btn btn-sm btn-danger" href="javascript: removeTopic('+index+');">移除</a>';
			if(1 < index)// 第一个图片不显示上移
				html += '<a ac="up" class="btn btn-sm btn-info" href="javascript: mvUp('+index+');">上移</a>';
			html += '<a ac="down" class="btn btn-sm btn-warning" href="javascript: mvDown('+index+');">下移</a>';
			html += '</div>';
			return html;
		}
		var topicImgList = [];// 所有的头图图片列表
		var topinImgIndex = 1;
		function initTopicImg(imgs) {
			if(imgs && 0 < imgs.length) {
				topicImgList = imgs.split(',');
				renderTopicImg();
			}
		}
		function addTopicImg(img) {
			topicImgList.push(img);
			renderTopicImg();
		}
		function renderTopicImg() {
			var html = '';
			topinImgIndex = 1;
			$.each(topicImgList, function(i, img) { html += giveTopicImg(topinImgIndex++, imgDomain+img); });
			$("#showImgsDiv").html(html);
			// set 提交的值
			var val = topicImgList.join(',');
			$('#showImgsID').val(val);
			// 将最后一个 下移 隐藏
			$('#showImgsDiv div').last().find('a[ac="down"]').hide();
		}
		function removeTopic(index) {
			// 删除topicImgList中指定下标的元素
			var newArr = [];
			var arrIndex = (index -1);
			for(var i = 0;i<topicImgList.length;i++)
				if(i != arrIndex)
					newArr.push(topicImgList[i]);
			topicImgList = newArr;
			// 重新渲染头图列表
			renderTopicImg();
		}
		function clearShowImgs() {
			topicImgList = [];
			topinImgIndex = 1;
			renderTopicImg();
		}
		function mvUp(index) {
			if(1 >= index) { alert('已经是第一张图片了！');return; }
			var mvImg = topicImgList[index-1];//需要移动的图片
			var tmpImg = topicImgList[index-2];//移动至的图片
			topicImgList[index-1] = tmpImg;
			topicImgList[index-2] = mvImg;
			renderTopicImg();
		}
		function mvDown(index) {
			if(topinImgIndex <= index) { alert('已经是最后一张图片了！');return; }
			var mvImg = topicImgList[index-1];//需要移动的图片
			var tmpImg = topicImgList[index];//移动至的图片
			topicImgList[index-1] = tmpImg;
			topicImgList[index] = mvImg;
			renderTopicImg();
		}
	</script>
</body>
</html>
