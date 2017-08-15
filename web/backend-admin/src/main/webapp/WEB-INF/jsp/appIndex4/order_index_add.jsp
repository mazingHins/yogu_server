<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockAction"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.services.store.StoreConstants"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<title>添加订餐首页配置</title>
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
					添加订餐首页配置 <small><ol class="breadcrumb">
							<li style="font-weight: normal;">${city.name}&nbsp;/&nbsp;${lang.zhName}</li>
							<li class="active"><a
								href="/admin/order/index/list.xhtm?cityCode=${city.code}&langCode=${lang.code}"><strong>返回订餐首页版本列表</strong></a></li>
						</ol></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div id="listDiv" class="col-xs-12">
						<div class="box" id="block">
							<h3>广告配置</h3>
							<!-- /.box-header -->
							<div id="listAdverItem" class="box-body">
								<script id="adverItem" type="text/html">
								<table class="table table-bordered table-hover" id="adverItem_{{i}}">
									<tr>
										<th width="140">广告_{{i}}</th>
										<td></td>
									</tr>
									<tr>
										<th>内容</th>
										<td>
											<form id="editForm" class="form-horizontal" method="post">
												<div class="form-group">
													<label class="col-md-2 control-label">数据项类型<span
														style="color: red;">*</span></label>
													<div class="col-md-3">
														<select id="target_{{i}}" name="target" class="form-control">
															<option value="newsBlock">资讯</option>
															<option value="store">餐厅</option>
															<option value="h5">h5</option>
														</select>
													</div>
												</div>

											<div id="targerDiv" class="form-group">
												<label class="col-md-2 control-label">数据项</label>
												<div class="col-md-2">
													<a id="showModalName" class="btn btn-default" href="javascript:showModal({{i}});">选择餐厅/美食</a>
												</div>
											</div>

												<div class="form-group">
													<label class="col-md-2 control-label">数据项ID<span
														style="color: red;">*</span></label>
													<div class="col-md-2">
														<input id="targetId_{{i}}" value="0" class="form-control" name="targetId" value="" placeholder="数据项ID默认0">
													</div>
												</div>


												<div class="form-group">
													<label class="col-md-2 control-label">项名称</label>
													<div class="col-md-3">
														<input id="name_{{i}}" class="form-control" name="name" placeholder="请输入项显示的名称">
													</div>
													<div class="col-md-3">
														<p class="text-red">不填默认取关联的数据项ID相关内容</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">项简介</label>
													<div class="col-md-3">
														<input id="description_{{i}}" class="form-control" name="description" placeholder="请输入项显示的简介内容">
													</div>
													<div class="col-md-3">
														<p class="text-red">不填默认取关联的数据项ID相关内容</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">项url</label>
													<div class="col-md-3">
														<input id="url_{{i}}" class="form-control" name="url" placeholder="请输入项跳转的url">
													</div>
													<div class="col-md-3">
														<p class="text-red">不填默认取关联的数据项ID相关内容</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">项图片<span
														style="color: red;">*</span></label>
													<div class="col-md-3">
														<img id="imgPreview_{{i}}" width="200" src="" /> 
														<input id="picInput_{{i}}" num="{{i}}" callback="previewImg" type="file" value="更换图片..."> 
														<input id="img_{{i}}" name="img" type="hidden" />
													</div>
													<div class="col-md-3">
														<p class="text-red">新上传的图片将会原图保存</p>
														<p class="text-red">请您确保他们的尺寸是合适，并且一致的</p>
														<p class="text-blue">广告：建议主广告750*350px，小广告750*200px</p>
														<p class="text-blue">餐厅图片或菜品图片：建议350*350px</p>
														<p class="text-blue">一/两行4列：建议png格式，120*120px</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">附带参数</label>
													<div class="col-md-3">
														<input id="targetParams_{{i}}" class="form-control" name="targetParams">
														<button id="tag_{{i}}" type="button" class="btn btn-link" onclick="showTagModal({{i}});">选择餐厅TAG</button>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">是否可分享</label>
													<div class="col-md-3">
														<select id="canShare_{{i}}" name="canShare" class="form-control">
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">分享的图标</label>
													<div class="col-md-3">
														<img id="shareImgPreview_{{i}}" width="200" src="" /> 
														<input id="shareImgInput_{{i}}" num="{{i}}" callback="previewShareImg" type="file" value="更换图片..."> 
														<input id="shareImg_{{i}}" name="shareImg" type="hidden" />
													</div>
													<div class="col-md-3">
														<p class="text-red">能分享时候必填</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">分享给朋友的标题</label>
													<div class="col-md-3">
														<input id="shareTitle_{{i}}" class="form-control" name="shareTitle" placeholder="分享数据项给朋友显示的标题">
													</div>
													<div class="col-md-3">
														<p class="text-red">能分享时候必填</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">分享朋友圈的标题</label>
													<div class="col-md-3">
														<input id="shareTimelineTitle_{{i}}" class="form-control" name="shareTimelineTitle" placeholder="分享数据项到朋友圈显示的标题">
													</div>
													<div class="col-md-3">
														<p class="text-red">能分享时候必填</p>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-2 control-label">分享的内容</label>
													<div class="col-md-3">
														<input id="shareContent_{{i}}" class="form-control" name="shareContent" placeholder="分享专题显示的内容">
													</div>
													<div class="col-md-3">
														<p class="text-red">能分享时候必填</p>
													</div>
												</div>


											</form>
										</td>
									</tr>
									<tr>
										<th>操作</th>
										<td><input type="button" value="删除"
											class="btn btn-danger" onclick="delAdverItem('{{i}}')"></td>
									</tr>
								</table>
								</script>
							</div>
							<div id="listAdverItem" class="box-body">
								<input type="button" value="添加广告" class="btn btn-primary"
									onclick="addAdverItem()">
							</div>
						</div>
					</div>

					<div id="listDiv" class="col-xs-12">
						<div class="box" id="block">
							<h3>标签配置</h3>
							<!-- /.box-header -->
							<div class="box-body">
								<label class="col-md-2 control-label">标签配置</label>
								<div class="col-md-3">
									<input id="targetParams_1000" class="form-control"
										name="tagIds">
									<button id="tag_1000" type="button" class="btn btn-link"
										onclick="showTagModal(1000);">选择餐厅TAG</button>
								</div>
							</div>
						</div>
					</div>

					<div class="box-body">
						<input type="button" value="提交" class="btn btn-success"
							onclick="return addOrderIndex()">
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
		<div class="modal fade" id="storeModal" tabindex="-1" role="dialog"
			aria-labelledby="storeModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="storeModalLabel">选择餐厅</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input id="storeKeyword" name="storeKeyword" maxlength="30"
										class="form-control" placeholder="输入关键字进行搜索"> <span
										class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat"
											onclick="searchStore();">Go!</button>
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

		<!-- 美食列表Modal -->
		<div class="modal fade" id="dishModal" tabindex="-1" role="dialog"
			aria-labelledby="dishModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="dishModalLabel">选择美食</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input id="dishKeyword" name="dishKeyword" maxlength="30"
										class="form-control" placeholder="输入关键字进行搜索"> <span
										class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat"
											onclick="searchDish();">Go!</button>
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
									<script id="dishTableTpl" type="text/html">
										{{each object as value i}}
										<tr>
											<td>
												{{value.storeName}}
											</td>
											<td>
												<a href="javascript:selectDish({{value.dishKey}}, '{{value.dishName}}')">{{value.dishName}}</a>
											</td>
											<input id="dish_price_{{value.dishKey}}" type="hidden" value="{{value.price}}" />
											<input id="dish_name_{{value.dishKey}}" type="hidden" value="{{value.dishName}}" />
											<input id="dish_cardImg_{{value.dishKey}}" type="hidden" value="{{value.cardImg}}" />
											<input id="dish_originalPrice_{{value.dishKey}}" type="hidden" value="{{value.originalPrice}}" />
											<input id="dish_specialContent_{{value.dishKey}}" type="hidden" value="{{value.specialContent}}" />
										</tr>
										{{/each}}
									</script>
								</table>
							</div>
						</div>
						<div class="row" id="dishPaginator"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
					</div>
				</div>
			</div>
		</div>

		<!-- 餐厅TAG Modal -->
		<div class="modal fade" id="tagModal" tabindex="-1" role="dialog"
			aria-labelledby="tagModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
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
										<c:forEach items="${tagCategoryList}" var="category">
											<tr>
												<td>${category.categoryName}</td>
												<td><c:forEach items="${category.tags}" var="tag"
														varStatus="status">
														<div class="checkbox-inline">
															<label> <input type="checkbox" name="selectTag"
																value="${tag.tagId}">${tag.tagName}
															</label>
														</div>
													</c:forEach></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row" id="dishPaginator"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="selectTag();">确认</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ./wrapper -->

	<!-- bottom js -->
	<%@ include file="/include/bottom-js.jsp"%>
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			template.helper('renderStoreStar', function(star) { return star / 10; });
			template('adverItem', {i:1})
		});
		
		var block_i = 1;
		function addAdverItem(){
			var html = template('adverItem', {i:block_i});
			$('#listAdverItem').append(html);
			$("#picInput_"+block_i).change(uploadPic);
			$("#shareImgInput_"+block_i).change(uploadPic);
			block_i = block_i + 1;
		}
		
		function delAdverItem(bi){
			$('#adverItem_'+bi).remove();
		}
		

//------------
		var imgDomain = '${imgDomain}';
		var adverItemNo = 0;
		
		function showModal(i) {
			var target = $("#target_"+i).val();
			if("store" == target) {
				$("#storeModal").modal("show");
				searchStore();
			} else if("dish" == target) {
				$("#dishModal").modal("show");
				searchDish();
			}
			adverItemNo = i;
		}
		
		function showTagModal(i) {
			$("#tagModal").modal("show");
			adverItemNo = i;
		}
		function selectTag() {
			var tagIds = '';
			$('input[name="selectTag"]:checked').each(function() {
				var id = $(this).val();
				if(tagIds) tagIds += (','+id);
				else tagIds = id;
			});
			$("#targetParams_"+adverItemNo).val(tagIds);
		}
		
		// 搜索餐厅
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
			$("#targetId_"+adverItemNo).val(storeId);
			var name = $("#store_name_" + storeId).val();
			$("#name_"+adverItemNo).val(name);
			var content = $("#store_content_" + storeId).val();
			$("#description_"+adverItemNo).val(content);
			var img = $("#store_topicImg_" + storeId).val();
			$("#imgPreview_"+adverItemNo).attr("src", imgDomain + img);
			$("#img_"+adverItemNo).val(img);
		}
		
		// 搜索美食
		function searchDish() {
			var keyword = $('#dishKeyword').val();
			$('#dishTable').artPaginate({
				type : 'post',
				url : "/admin/dish/query",// 获取数据的地址
				paginator : 'dishPaginator',// 显示页码的位置
				tpl : 'dishTableTpl',// 模版ID
				params : { 'keyword' : keyword },// 请求的参数表
				pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
			});
		}
		// 选中了指定的美食
		function selectDish(dishKey) {
			$("#targetId_"+adverItemNo).val(dishKey);
			var name = $("#dish_name_" + dishKey).val();
			$("#name_"+adverItemNo).val(name);
			var img = $("#dish_cardImg_" + dishKey).val();
			$("#imgPreview_"+adverItemNo).attr("src", imgDomain + img);
			$("#img_"+adverItemNo).val(img);
			var special = $("#dish_specialContent_" + dishKey).val();
			$("description_"+adverItemNo).val(special);
		}

		// 上传文件
		function uploadPic(a) {
			var input = $(a.target);
			var id = input.attr('id');
			var callback = input.attr('callback');
			var num = input.attr('num');
			
			console.log('num: ', num);
			
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
						if(callback) {
							eval(callback + '("' + img + '", "'+num+'");');
						}
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}
		function previewImg(img, index) {
			$("#imgPreview_"+index).attr("src", imgDomain + img);
			$("#img_"+index).val(img);
		}
		function previewShareImg(img, index){
			$("#shareImgPreview_"+index).attr("src", imgDomain + img);
			$("#shareImg_"+index).val(img);
		}
//------------
	
		function loadIndexBlockType(){
			requestNode('/user/admin/getindexblocktype', 'get', {}, function(data){
				var html = '';
				for(var district of data){
					html += '<option value="'+district.type+'">'+district.name+'</option>';
				}
				$('#indexBlockType_newBlock').html(html);
			});
		}
		
		function loadIndexActionType(){
			requestNode('/user/admin/getindexactiontype', 'get', {}, function(data){
				var html = '';
				for(var district of data){
					html += '<option value="'+district.type+'">'+district.name+'</option>';
				}
				$('#indexBlockType_newBlock').html(html);
			});
		}
		
		function check(){
			var cityCode = '${city.code}';
			var langCode = '${lang.code}';
			
			var targetElementArray = $('select[name="target"]');
			var targetIdElementArray = $('input[name="targetId"]');
			var nameElementArray = $('input[name="name"]');
			var descriptionElementArray = $('input[name="description"]');
			var imgElementArray = $('input[name="img"]');
			var targetParamsElementArray = $('input[name="targetParams"]');
			var urlElementArray = $('input[name="url"]');
			
			var canShareElementArray = $('select[name="canShare"]');
			var shareImgElementArray = $('input[name="shareImg"]');
			var shareTitleElementArray = $('input[name="shareTitle"]');
			var shareTimelineTitleElementArray = $('input[name="shareTimelineTitle"]');
			var shareContentElementArray = $('input[name="shareContent"]');
			
			var adverBlock = {title:'广告', cityCode, langCode, type:'adver', action:''};
			adverBlock.items = [];
			
			for(var i = 0; i < targetElementArray.length; i++) {
				var target = $(targetElementArray[i]).val().trim();
				var targetId = $(targetIdElementArray[i]).val().trim();
				var name = $(nameElementArray[i]).val().trim();
				var description = $(descriptionElementArray[i]).val().trim();
				var img = $(imgElementArray[i]).val().trim();
				var targetParams = $(targetParamsElementArray[i]).val().trim();
				var url = $(urlElementArray[i]).val().trim();
				
				if((!url && target == 'h5') || (!/^((http|https):\/\/(\w+:{0,1}\w*@)?(\S+)|)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/.test(url) && target == 'h5')) {
					MyDialog.alert('h5的数据项url地址错误，请输入正确的url地址！例如：https://www.mazing.com');
					return false;
				}
				
				if(!targetId.trim() && (target=='newsBlock' || target=='store' || target=='dish')){
					MyDialog.alert('资讯/餐厅/美食的数据项ID不能为空！');
					return false;
				}
				targetId = parseInt(targetId) || 0;
				
				if(!targetParams.trim() && target=='index'){
					MyDialog.alert('瀑布流的附加参数不能为空！');
					return false;
				}
				
				var item = {target,targetId,name,description,img,targetParams,url:url};
				
				item.canShare = $(canShareElementArray[i]).val() || '0';
				if(item.canShare == '1'){
					item.shareImg = $(shareImgElementArray[i]).val();
					item.shareTitle = $(shareTitleElementArray[i]).val();
					item.shareTimelineTitle = $(shareTimelineTitleElementArray[i]).val();
					item.shareContent = $(shareContentElementArray[i]).val();
					if(!item.shareImg.trim() || !item.shareTitle.trim() || !item.shareTimelineTitle.trim() || !item.shareContent.trim() ){
						MyDialog.alert('能分享时, 分享的参数不能为空！');
						return false;
					}
				}
				
				adverBlock.items.push(item);
			}
			
			if(adverBlock.items.length==0){
				MyDialog.alert('广告设置不能为空！');
				return false;
			}
			
			var tagsBlock = {title:'tags list', cityCode, langCode, type:'tags', action:'',itemIds:[]};
			var itemIdsArray = $('input[name="tagIds"]').val().split(',');
			for(var i of itemIdsArray){
				if(!i){
					continue;
				}
				if(parseInt(i)){
					tagsBlock.itemIds.push(parseInt(i));
				}else{
					MyDialog.alert('标签配置输入格式错误！');
					return false;
				}
			}
			
			return [adverBlock,tagsBlock];
		}
		
		function addOrderIndex(){
			var blockArray = check();
			console.log('blockArray', blockArray);
			if(!blockArray || blockArray.length==0){
				return false;
			}
			
			var cityCode = '${city.code}';
			var langCode = '${lang.code}';
			requestNode('/order/admin/addindex', 'post', {cityCode, langCode, blockArray:JSON.stringify(blockArray)}, function(data){
				window.location.href = '/admin/order/index/list.xhtm?cityCode=${city.code}&langCode=${lang.code}';
			});
	        return true;
		}

		//**************************************************************

		// 更换target事件
		function changeAction() {
			var action = $("#actionID").val();
			if ("h5" == action) {
				$('#actionParamsID').attr('placeholder', '请输入需要附带的参数');
				showInOrHideOther([ "urlDiv", "actionParamsDiv", "canShareDiv" ]);
				cnahgeCanShare();
			} else if ("index" == action) {
				var tobj = $('#actionParamsID').val('').removeAttr('readonly')
						.attr('placeholder', '请选择TAG...');
				showInOrHideOther([ "tagID", "actionParamsDiv" ]);
			} else {
				showInOrHideOther([]);
			}
		}
		// 显示指定的div，其他的div隐藏
		// nameDiv、imgPreviewDiv
		var allDiv = [ "tagID", "urlDiv", "canShareDiv", "autoSortingDiv",
				"actionParamsDiv", "shareImgDiv", "shareTitleDiv",
				"shareContentDiv" ];
		function showInOrHideOther(showIdArray) {
			var allLength = allDiv.length;
			for (var i = 0; i < allLength; i++) {
				var div = allDiv[i];
				var showOrHide = false;// 默认隐藏
				var arrLength = showIdArray.length;
				for (var j = 0; j < arrLength; j++) {
					if (div == showIdArray[j]) {
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
			if (showOrHide)
				$("#" + divId).removeClass("hidden").addClass("show").show();
			else
				$("#" + divId).removeClass("show").addClass("hidden").hide();
		}

		// 如果url中带了 bid 参数，则转为修改数据
		function toEdit(bid) {
			$.getJSON('/admin/appIndex/block/get?bid=' + bid, {},
					function(json) {
						if (json.success) {
							showEdit(json.object);
						} else {
							MyDialog.alert(json.message);
						}
					});
		}
		// 选择select组件，保留指定的选项，其他的删除（是option节点移除）
		function delSelect(retain) {
			if (!retain)
				return;
			$("#typeID option[value!='" + retain + "']").remove();
			changeAction();
		}

		// tag选择器，默认选中arr中的数据，其他的不选中
		function onSelect(arr) {
			$('input[name="selectTag"]').each(function() {
				var ok = false;
				var val = $(this).val();
				for (var i = 0; i < arr.length; i++)
					if (val == arr[i]) {
						ok = true;
						break;
					}
				// 是否选中
				$(this).attr("checked", ok);
			});
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
			var tobj = $('#actionParamsID');
			if (show) {
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
			if ('store' == type)
				showOrHideDiv('autoPushDiv', true);
			else
				showOrHideDiv('autoPushDiv', false);
			// 美食：是否显示  自动排序
			if ('dish' == type)
				showOrHideDiv('autoSortingDiv', true);
			else
				showOrHideDiv('autoSortingDiv', false);
		}

		$(function() {
			$("#typeID").change(cnahgeType);
			$("#actionID").change(changeAction);
			$("#sharePicInput").change(uploadPic);
			$("#canShareID").change(cnahgeCanShare);

			// 选择时间
			$('#activeStartMinutePreviewID').datetimepicker({
				language : 'zh-CN',
				format : 'hh:ii',
				startView : 1,
				maxView : 1,
				minuteStep : 5,
				autoclose : true,
				keyboardNavigation : false,
				startDate : '00:00'
			}).on('changeDate', function() {
				var str = $('#activeStartMinutePreviewID').val();
				strToMinuteSetVal(str, 'activeStartMinuteID');
			});
			$('#activeEndMinutePreviewID').datetimepicker({
				language : 'zh-CN',
				format : 'hh:ii',
				startView : 1,
				maxView : 1,
				minuteStep : 5,
				autoclose : true,
				keyboardNavigation : false,
				startDate : '24:00'
			}).on('changeDate', function() {
				var str = $('#activeEndMinutePreviewID').val();
				if (str == '00:00') {
					str = '24:00';
					$('#activeEndMinutePreviewID').val(str);// 灰机，这个不起作用，还是显示 00:00
				}
				strToMinuteSetVal(str, 'activeEndMinuteID');
			});

			// 是否是修改数据（url中是否带有bid）
			var bid = $.getUrlParam("bid");
			if (bid) {
				toEdit(bid);
			}// 编辑
		});

		// str是一个 hh:mm格式的时间，将其转换成分钟数，并赋值给 #id 组件
		function strToMinuteSetVal(str, id) {
			var m = hmToMinute(str);
			$('#' + id).val(m);
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
			if (10 > h)
				h = ('0' + h);
			if (10 > m)
				m = ('0' + m);
			return h + ':' + m;
		}
	</script>
</body>
</html>
