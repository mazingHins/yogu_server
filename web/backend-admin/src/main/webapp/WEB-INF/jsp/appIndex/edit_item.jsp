<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendItemTarget"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>App首页--项管理页面</title>
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
					管理数据项 <small></small>
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
									<li>${block.title}&nbsp;&nbsp;<strong>(${blockType.name})</strong></li>
									<li class="active"><a href="/admin/appIndex/item/list.xhtm?bid=${block.bid}"><strong>返回项列表</strong></a></li>
								</ol>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<form id="editForm" class="form-horizontal" method="post">
											<input id="bidID" name="bid" type="hidden" value="${block.bid}" />
											<input id="targetIdID" name="targetId" type="hidden" />
											<input id="shareImgID" name="shareImg" type="hidden" />
											<input id="itemIdID" name="itemId" type="hidden" />
											<input id="logoID" name="logo" type="hidden" />
											<input id="imgID" name="img" type="hidden" />

											<div class="form-group">
												<label class="col-md-2 control-label">数据项类型<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<select id="targetID" name="target" class="form-control">
														<%for(RecommendItemTarget terget : RecommendItemTarget.values()) {%>
														<option value="<%=terget.getValue()%>"><%=terget.getName()%></option>
														<%}%>
													</select>
												</div>
											</div>
											<div id="targerDiv" class="form-group hidden">
												<label class="col-md-2 control-label">数据项<span style="color: red;">*</span></label>
												<div class="col-md-2">
													<a id="showModalName" class="btn btn-default" href="javascript:showModal();"></a>
												</div>
											</div>
											<div id="nameDiv" class="form-group">
												<label class="col-md-2 control-label">项名称<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="nameID" class="form-control" name="name" placeholder="请输入项显示的名称">
												</div>
											</div>
											<div id="descriptionDiv" class="form-group hidden">
												<label class="col-md-2 control-label">项简介</label>
												<div class="col-md-3">
													<input id="descriptionID" class="form-control" name="description" placeholder="请输入项显示的简介内容">
												</div>
											</div>
											<div id="imgPreviewDiv" class="form-group">
												<label class="col-md-2 control-label">项图片<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="imgPreview" width="200" src="" />
													<input id="picInput" callback="previewImg" type="file" value="更换图片...">
												</div>
												<div class="col-md-3">
													<p class="text-red">新上传的图片将会原图保存</p>
													<p class="text-red">请您确保他们的尺寸是合适，并且一致的</p>
													<p class="text-blue">广告：建议主广告750*350px，小广告750*200px</p>
													<p class="text-blue">餐厅图片或菜品图片：建议350*350px</p>
													<p class="text-blue">一/两行4列：建议png格式，120*120px</p>
												</div>
											</div>
											<div id="logoPreviewDiv" class="form-group hidden">
												<label class="col-md-2 control-label">logo</label>
												<div class="col-md-3">
													<img id="logoPreview" width="100" src="" />
												</div>
											</div>
											<div id="originalPriceDiv" class="form-group hidden">
												<label class="col-md-2 control-label">原价</label>
												<div class="col-md-3">
													<p id="originalPricePreview" class="form-control-static"></p>
													<input id="originalPriceID" type="hidden" class="form-control" name="originalPrice" placeholder="原价" readonly="readonly">
												</div>
											</div>
											<div id="priceDiv" class="form-group hidden">
												<label class="col-md-2 control-label">现价</label>
												<div class="col-md-3">
													<p id="pricePreview" class="form-control-static"></p>
													<input id="priceID" type="hidden" class="form-control" name="price" placeholder="现价" readonly="readonly">
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
											<div id="targetParamsDiv" class="form-group hidden">
												<label class="col-md-2 control-label">附带参数</label>
												<div class="col-md-3">
													<input id="targetParamsID" class="form-control" name="targetParams">
													<button id="tagID" type="button" class="btn btn-link hidden" onclick="showTagModal();">选择餐厅TAG</button>
												</div>
											</div>
											<div id="shareTitleDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享给朋友的标题<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="shareTitleID" class="form-control" name="shareTitle" placeholder="请输入分享的标题">
												</div>
											</div>
											<div id="shareTimelineTitleDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享到朋友圈的标题<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<input id="shareTimelineTitleID" class="form-control" name="shareTimelineTitle" placeholder="请输入分享的标题">
												</div>
											</div>
											<div id="shareImgDiv" class="form-group hidden">
												<label class="col-md-2 control-label">分享的图标<span style="color: red;">*</span></label>
												<div class="col-md-3">
													<img id="shareImgPreview" width="80" src="" />
													<input id="sharePicInput" callback="previewShareImg" type="file" value="更换图片...">
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

		<!-- 美食列表Modal -->
		<div class="modal fade" id="dishModal" tabindex="-1" role="dialog" aria-labelledby="dishModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="dishModalLabel">选择美食</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="input-group input-group-sm">
									<input id="dishKeyword" name="dishKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="searchDish();">Go!</button>
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
											<tr>
												<td>${category.categoryName}</td>
												<td>
													<c:forEach items="${category.tags}" var="tag" varStatus="status">
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
	<script type="text/javascript">
		var imgDomain = '${imgDomain}';

		//
		// 搜索美食
		//

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
			$("#targetIdID").val(dishKey);
			var name = $("#dish_name_" + dishKey).val();
			$("#nameID").val(name);
			var img = $("#dish_cardImg_" + dishKey).val();
			$("#imgPreview").attr("src", imgDomain + img);
			$("#imgID").val(img);
			var special = $("#dish_specialContent_" + dishKey).val();
			$("descriptionID").val(special);
			
			var oPrice = $("#dish_originalPrice_" + dishKey).val();
			$("#originalPriceID").val(oPrice);
			$("#originalPricePreview").text(cent2yuan(oPrice)+'元');
			var price = $("#dish_price_" + dishKey).val();
			$("#priceID").val(price);
			$("#pricePreview").text(cent2yuan(price)+'元');
		}

		//
		//搜索餐厅
		//

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
			$("#targetIdID").val(storeId);
			var name = $("#store_name_" + storeId).val();
			$("#nameID").val(name);
			var content = $("#store_content_" + storeId).val();
			$("#descriptionID").val(content);
			var img = $("#store_topicImg_" + storeId).val();
			$("#imgPreview").attr("src", imgDomain + img);
			$("#imgID").val(img);
			var logo = $("#store_logoPath_" + storeId).val();
			$("#logoPreview").attr("src", imgDomain + logo);
			$("#logoID").val(logo);
		}

		//
		//
		//

		// 打开模态窗口
		// 根据target决定打开那个modal
		function showModal() {
			var target = $("#targetID").val();
			if("store" == target) {
				$("#storeModal").modal("show");
				searchStore();
			} else if("dish" == target) {
				$("#dishModal").modal("show");
				searchDish();
			}
		}

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
			$("#targetParamsID").val(tagIds);
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
						if(callback) {
							eval(callback+'("'+img+'");');
						}
					} else {
						MyDialog.alert(json.message);
					}
				}
			});
		}
		function previewImg(img) {
			$("#imgPreview").attr("src", imgDomain + img);
			$("#imgID").val(img);
		}
		function previewShareImg(img) {
			$("#shareImgPreview").attr("src", imgDomain + img);
			$("#shareImgID").val(img);
		}

		// 更换target事件
		function changeTarget() {
			var target = $("#targetID").val();
			if ("h5" == target) {
				$('#targetParamsID').attr('placeholder', '请输入需要附带的参数');
				showInOrHideOther(["urlDiv", "targetParamsDiv", "canShareDiv"]);
				cnahgeCanShare();
			} else if ("store" == target) {
				$("#showModalName").text("请选择餐厅");
				showInOrHideOther(["targerDiv", "logoPreviewDiv", "descriptionDiv", "targetIdDiv"]);
			} else if ("dish" == target) {
				$("#showModalName").text("请选择美食");
				showInOrHideOther(["targerDiv", "descriptionDiv", "priceDiv", "targetIdDiv", "originalPriceDiv"]);
			} else if ("index" == target) {
				var tobj = $('#targetParamsID').val('').removeAttr('readonly').attr('placeholder', '请选择TAG...');
				showInOrHideOther(["tagID", "targetParamsDiv"]);
			}
		}
		// 显示指定的div，其他的div隐藏
		// nameDiv、imgPreviewDiv
		var allDiv = ["tagID", "targerDiv", "descriptionDiv", "logoPreviewDiv", "originalPriceDiv", "priceDiv"
		              , "urlDiv", "canShareDiv", "targetParamsDiv", "shareImgDiv", "shareTitleDiv", "shareTimelineTitleDiv", "shareContentDiv"];
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

		// 如果url中带了 itemId 参数，则转为修改数据
		function toEdit(itemId) {
			$.getJSON('/admin/appIndex/item/get?itemId=' + itemId, {}, function(json) {
				if (json.success) {
					showEdit(json.object);
				} else {
					MyDialog.alert(json.message);
				}
			});
		}
		// 初始化修改页面
		function showEdit(item) {
			// 设置选中的类型，并设置为不可更改
			$("#targetID").val(item.target);
			$("#itemIdID").val(item.itemId);
			
			$("#bidID").val(item.bid);
			$("#imgID").val(item.img);
			$("#imgPreview").attr("src", imgDomain + item.img);// img view
			$("#targetIdID").val(item.targetId);
			
			$("#nameID").val(item.name);
			$("#descriptionID").val(item.description);
			$("#logoID").val(item.logo);
			$("#logoPreview").attr("src", imgDomain + item.logo);// logo view
			$("#urlID").val(item.url);
			$("#canShareID").val(item.canShare);
			
			$("#originalPriceID").val(item.originalPrice);
			$("#originalPricePreview").text(cent2yuan(item.originalPrice)+'元');
			$("#priceID").val(item.price);
			$("#pricePreview").text(cent2yuan(item.price)+'元');
			
			$("#shareImgID").val(item.shareImg);
			$("#shareImgPreview").attr("src", imgDomain + item.shareImg);// logo view
			$("#shareTitleID").val(item.shareTitle);
			$("#shareTimelineTitleID").val(item.shareTimelineTitle);
			$("#shareContentID").val(item.shareContent);
			
			delSelect(item.target);
			// 如果是index，还需要做一些额外的事情
			if('index' == item.target) {
				// 打开 TAG的选择div
				showOrHideDiv("tagID", true);
				// 初始化tag选中的项目
				var str = item.targetParams;
				if(str) {
					var arr = str.split(',');
					onSelect(arr);
				}
			}

			// 需要放到最后面来赋值，因为changeTarget方法中有可能会清空其内容
			$("#targetParamsID").val(item.targetParams);
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
		// 选择select组件，保留指定的选项，其他的删除（是option节点移除）
		function delSelect(retain) {
			if(!retain) return;
			$("#targetID option[value!='"+retain+"']").remove();
			changeTarget();
		}

		// 提交表单数据
		function submitUpdate() {
			$("#editForm").ajaxSubmit({
				url: '/admin/appIndex/item/edit.do', type: 'post'
				, beforeSubmit: formValidate // 表单验证 
				, success: function(json) {
					MyDialog.alert(json.message, function() {
						if(json.success) {
							var bid = $("#bidID").val();
							window.location.href = '/admin/appIndex/item/list.xhtm?bid=' + bid;
						}
					});
				}
			});
		}
		function formValidate() {
			// 必传的参数
			var target = $('#targetID').val();
			if(!target) return alertMsg('请选择目标类型！');
			var name = $('#nameID').val();
			if(!name) return alertMsg('请输入项名称！');
			if(32 < name.length) return alertMsg('项名称长度不能超过32');
			var img = $('#imgID').val();
			if(!img) return alertMsg('请上传一张图片作为项图片！');
			
			// 分开校验的参数
			if('h5' == target) {
				var url = $('#urlID').val();
				if(!url) return alertMsg('请输入HTML5的URL地址！');
				if(250 < url.length) return alertMsg('URL过长！');
				// 如果允许分享
				var canShare = $('#canShareID').val();
				if(1 == canShare) {
					var shareTitle = $('#shareTitleID').val();
					if(!shareTitle) return alertMsg('请输入分享给朋友标题！');
					if(60 < shareTitle.length) return alertMsg('分享给朋友标题长度不能超过60');
					var shareTimelineTitle = $('#shareTimelineTitleID').val();
					if(!shareTimelineTitle) return alertMsg('请输入分享到朋友圈的标题！');
					if(60 < shareTimelineTitle.length) return alertMsg('分享到朋友圈的标题长度不能超过60');
					var shareImg = $('#shareImgID').val();
					if(!shareImg) return alertMsg('请上传一张分享的标题图片！');
					var shareContent = $('#shareContentID').val();
					if(!shareContent) return alertMsg('请输入分享的内容简介！');
					if(64 < shareContent.length) return alertMsg('分享的内容简介长度不能超过64');
				}
			}
			else if('store' == target) {
				var targetId = $('#targetIdID').val();
				if(!targetId) return alertMsg('请选择一家餐厅！');
				var description = $('#descriptionID').val();
				if(64 < description.length) return alertMsg('简介长度不能超过64');
			}
			else if('dish' == target) {
				var targetId = $('#targetIdID').val();
				if(!targetId) return alertMsg('请选择一个美食！');
				var description = $('#descriptionID').val();
				if(64 < description.length) return alertMsg('简介长度不能超过64');
				//originalPriceID priceID
			}
			else if('index' == target) {
			}
			// targetParamsID
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
			showOrHideDiv('shareTimelineTitleDiv', show);
			showOrHideDiv('shareContentDiv', show);
			// h5可分享，参数为：newIndexHtml5
			var tobj  = $('#targetParamsID');
			if(show) {
				tobj.val('newIndexHtml5');
				tobj.attr('readonly', 'readonly');
			} else {
				tobj.val('');
				tobj.removeAttr('readonly');
			}
		}

		$(function() {
			$("#picInput").change(uploadPic);
			$("#sharePicInput").change(uploadPic);
			$("#targetID").change(changeTarget);
			$("#canShareID").change(cnahgeCanShare);
			// 模版函数：输出餐厅的星级
			template.helper('renderStoreStar', function(star) { return star / 10; });

			// 是否是修改数据（url中是否带有itemId）
			var itemId = $.getUrlParam("itemId");
			if(itemId) {
				// 编辑
				toEdit(itemId);
			} else {// 新增
				// 餐厅就只能选择餐厅，美食就只能选择美食
				var blockType = '${block.type}';
				if('store' == blockType || 'dish' == blockType) delSelect(blockType);
				changeTarget();
			}
		});
	</script>
</body>
</html>
