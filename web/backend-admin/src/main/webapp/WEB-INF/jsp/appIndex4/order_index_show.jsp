<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ page import="com.mazing.services.store.RecommendBlockAction"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.services.store.StoreConstants"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<title>显示订餐首页配置</title>
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
					显示订餐首页配置 <small><ol class="breadcrumb">
							<li style="font-weight: normal;">${city.name}&nbsp;/&nbsp;${lang.zhName}</li>
							<li class="active"><a href="/admin/order/index/list.xhtm?cityCode=${city.code}&langCode=${lang.code}"><strong>返回订餐首页版本列表</strong></a></li>
						</ol></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<!-- /.box-header -->
							<div class="box-body">
								<table id="listTable" class="table table-bordered table-hover" cellpadding="5" cellspacing="5">
									<tr>
										<th>数据项类型</th>
										<th>数据项ID</th>
										<th>项名称</th>
										<th>项图片</th>
										<th>项内容</th>
										<th>分享内容</th>
									</tr>
						<script id="listTrTpl" type="text/html">
									{{each list as block i}}
										<tr height="30"><th colspan="6">{{block.title}}</th></tr>
											{{if block.type == 'adver'}}
												{{each block.items as item j}}
													<tr>
														<td>
															广告
															-
															{{if item.target == 'newsBlock'}}资讯{{/if}}
															{{if item.target == 'store'}}餐厅{{/if}}
															{{if item.target == 'h5'}}h5{{/if}}
															{{if item.target == 'dish'}}美食{{/if}}
															{{if item.target == 'index'}}瀑布流{{/if}}
															{{if item.target == 'tags'}}标签{{/if}}
														</td>
														<td>{{item.targetId}}</td>
														<td>{{item.name}}</td>
														<td>
															{{if item.img && item.img != ''}}<img height='100' src='https://img.mazing.com/{{item.img}}'/>{{/if}}
															{{if item.shareImg && item.shareImg != ''}}<img height='100' src='https://img.mazing.com/{{item.img}}'/>{{/if}}
														</td>
														<td>
															{{if item.description && item.description != ''}}简介：{{item.description}}{{/if}}
															{{if item.url && item.url != ''}}<br/>链接：{{item.url}}{{/if}}
															{{if item.targetParams && item.targetParams != ''}}<br/>附加参数：{{item.targetParams}}{{/if}}
														</td>
														<td>
															{{if item.shareTitle && item.shareTitle != ''}}标题：{{item.shareTitle}}{{/if}}
															{{if item.shareTimelineTitle && item.shareTimelineTitle != ''}}<br/>朋友圈标题：{{item.shareTimelineTitle}}{{/if}}
															{{if item.shareContent && item.shareContent != ''}}<br/>内容：{{item.shareContent}}{{/if}}
														</td>
													</tr>
												{{/each}}
											{{/if}}
											
											{{if block.type == 'tags'}}
												<tr height="30">
													<td colspan="6">
														{{each block.itemIds as item j}}
															{{item}}&nbsp;&nbsp;&nbsp;&nbsp;
														{{/each}}
													</td>
												</tr>
											{{/if}}
										{{/each}}
					</script>
									<tr>
								</table>
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
	<script
		src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			loadIndexBlock();
		});
		
		function loadIndexBlock(){
			template.helper('dateFormat', function (date) {
				return moment.unix(date).format('YYYY-MM-DD HH:mm:ss');
			});
			
			template.helper('stringArrayJoin', function (date) {
				return date.join(',');
			});
			
			requestNode('/order/admin/getindex?vid=${vid}', 'get', {}, function(data){
				var html = template('listTrTpl', {list:data});
				$('#listTable').append(html);
			});
		}
		
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
			var data = [];

			var cityCode = '${city.code}';
			var langCode = '${lang.code}';
			
			var titleElementArray = $('input[name="title"]');
			var typeElementArray = $('select[name="type"]');
			var actionElementArray = $('select[name="action"]');
			var itemIdsElementArray = $('input[name="itemIds"]');
			
			titleElementArray.each(function(i,e){
				var title = $(e).val();
				if(!title.trim()){
					MyDialog.alert('标题输入错误！');
					return false;
				}
				var type = $(typeElementArray[i]).val();
				var action = $(actionElementArray[i]).val();
				var itemIdsArray = $(itemIdsElementArray[i]).val().split(',');
				var itemIds = [];
				for(var i of itemIdsArray){
					if(parseInt(i)){
						itemIds.push(parseInt(i));
					}else{
						MyDialog.alert('ID输入格式错误！');
						return false;
					}
				}
				data.push({title:title.trim(), cityCode, langCode, type:type, action:action, itemIds:itemIds});
			})
			
			return data;
		}
		
		function addIdIndex(){
			var blockArray = check();
			console.log('blockArray', blockArray);
			if(!blockArray || blockArray.length==0){
				return false;
			}
			var cityCode = '${city.code}';
			var langCode = '${lang.code}';
			requestNode('/user/admin/addindex', 'post', {cityCode, langCode, blockArray:JSON.stringify(blockArray)}, function(data){
				window.location.href = '/admin/id/index/list.xhtm?cityCode=${city.code}&langCode=${lang.code}';
			});
	        return true;
		}
		
		//搜索餐厅
		function searchStore() {
			var keyword = $('#storeKeyword').val();
			$('#storeTable').artPaginate({
				type : 'post',
				url : "/admin/store/query",// 获取数据的地址
				paginator : 'storePaginator',// 显示页码的位置
				tpl : 'storeTableTpl',// 模版ID
				params : {
					'keyword' : keyword
				},// 请求的参数表
				pageSize : 10
			// 每页多少条数据（默认：page=1,pageSize=20）
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

		// 打开模态窗口
		// 根据target决定打开那个modal
		function showModal() {
			var type = $("#type").val();
			if ("store" == type) {
				$("#storeModal").modal("show");
				searchStore();
			} else if ("newBlock" == type) {
			}
		}

		//**************************************************************
		var imgDomain = '${imgDomain}';

		// 打开模态窗口
		function showTagModal() {
			$("#tagModal").modal("show");
		}
		function selectTag() {
			var tagIds = '';
			$('input[name="selectTag"]:checked').each(function() {
				var id = $(this).val();
				if (tagIds)
					tagIds += (',' + id);
				else
					tagIds = id;
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
			$.each($('#' + id)[0].files, function(i, file) {
				data.append('picFile', file);
			});
			$.ajax({
				data : data,
				type : 'POST',
				cache : false,
				contentType : false, //不可缺
				processData : false, //不可缺
				url : '/admin/system/uploadPic.do',
				success : function(json) {
					if (json.success) {
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
		// 初始化修改页面
		function showEdit(block) {
			var nowCity = '${city.code}';
			var nowLang = '${lang.code}';
			if (block.cityCode != nowCity) {
				MyDialog.alert('当前城市与块数据中的城市不一致，请从新选择需要编辑的块数据！');
				return;
			}
			if (block.langCode != nowLang) {
				MyDialog.alert('当前语言与块数据中的语言不一致，请从新选择需要编辑的块数据！');
				return;
			}

			// 设置选中的类型，并设置为不可更改
			$("#bidID").val(block.bid);
			$("#typeID").val(block.type);
			$("#actionID").val(block.action);
			$("#titleID").val(block.title);
			$("#urlID").val(block.url);
			if (
	<%=BooleanConstants.TRUE%>
		== block.autoPush)
				$("#autoPushID").attr('checked', true);
			if (
	<%=BooleanConstants.TRUE%>
		== block.autoSorting)
				$("#autoSortingID").attr('checked', true);

			$('#activeLoginStatusID').val(block.activeLoginStatus);
			$("#activeStartMinuteID").val(block.activeStartMinute);
			$("#activeStartMinutePreviewID").val(
					minuteToHM(block.activeStartMinute));
			$("#activeEndMinuteID").val(block.activeEndMinute);
			$("#activeEndMinutePreviewID").val(
					minuteToHM(block.activeEndMinute));

			$("#canShareID").val(block.canShare);
			$("#shareImgID").val(block.shareImg);
			$("#shareImgPreview").attr("src", imgDomain + block.shareImg);// logo view
			$("#shareTitleID").val(block.shareTitle);
			$("#shareContentID").val(block.shareContent);

			delSelect(block.type);
			// 如果是index，还需要设置选中tag
			if ('index' == block.action) {
				showOrHideDiv("tagID", true);// 打开 TAG的选择div
				// 初始化tag选中的项目
				var str = block.actionParams;
				if (str) {
					var arr = str.split(',');
					onSelect(arr);
				}
			}

			$("#actionParamsID").val(block.actionParams);
			cnahgeType();
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

		// 提交表单数据
		function submitUpdate() {
			$("#editForm")
					.ajaxSubmit(
							{
								url : '/admin/appIndex/block/edit.do',
								type : 'post',
								beforeSubmit : formValidate // 表单验证
								,
								success : function(json) {
									MyDialog
											.alert(
													json.message,
													function() {
														if (json.success) {
															var cityCode = $(
																	"#cityCodeID")
																	.val();
															var langCode = $(
																	"#langCodeID")
																	.val();
															window.location.href = '/admin/appIndex/block/list.xhtm?cityCode='
																	+ cityCode
																	+ '&langCode='
																	+ langCode;
														}
													});
								}
							});
		}
		function formValidate() {
			// 必传的参数
			var cityCode = $('#cityCodeID').val();
			if (!cityCode)
				return alertMsg('参数错误，未知的城市！');
			var langCode = $('#langCodeID').val();
			if (!langCode)
				return alertMsg('参数错误，未知的语言！');
			var type = $('#typeID').val();
			if (!type)
				return alertMsg('请选择块类型！');
			var title = $('#titleID').val();
			if (!title)
				return alertMsg('请输入块标题！');
			if (32 < title.length)
				return alertMsg('块标题长度不能超过32');

			var startMinute = $('#activeStartMinuteID').val();
			if (!startMinute)
				return alertMsg('请设置开始生效时间！');
			var endMinute = $('#activeEndMinuteID').val();
			if (!endMinute)
				return alertMsg('请设置截止生效时间！');
			if (!valiAutoPushMinute())
				return false;// vali方法内有弹窗，所以这个不在弹了

			var action = $('#actionID').val();
			// 分开校验的参数
			if ('h5' == action) {
				var url = $('#urlID').val();
				if (!url)
					return alertMsg('请输入HTML5的URL地址！');
				if (250 < url.length)
					return alertMsg('URL过长！');
				// 如果允许分享
				var canShare = $('#canShareID').val();
				if (1 == canShare) {
					var shareTitle = $('#shareTitleID').val();
					if (!shareTitle)
						return alertMsg('请输入分享的标题！');
					if (60 < shareTitle.length)
						return alertMsg('分享的标题长度不能超过60');
					var shareImg = $('#shareImgID').val();
					if (!shareImg)
						return alertMsg('请上传一张分享的标题图片！');
					var shareContent = $('#shareContentID').val();
					if (!shareContent)
						return alertMsg('请输入分享的内容简介！');
					if (64 < shareContent.length)
						return alertMsg('分享的内容简介长度不能超过64');
				}
			} else if ('index' == action) {
				// 接受不选任何tag
			}
			// actionParamsID
		}
		function valiAutoPushMinute() {// 自动推送数据，校验开始结束时间是否重叠多次
			var type = $('#typeID').val();
			if ('store' != type)
				return true;// 餐厅列表才校验
			var use = $("#autoPushID").is(':checked');//.attr('checked');
			if (!use)
				return true;

			var startMinute = $('#activeStartMinuteID').val();
			var endMinute = $('#activeEndMinuteID').val();
			var cityCode = $('#cityCodeID').val();
			var langCode = $('#langCodeID').val();
			var bid = $("#bidID").val();

			// vali（同步的形式）
			var url = '/admin/appIndex/block/valiStartEndMinute?cityCode='
					+ cityCode + '&langCode=' + langCode + '&startMinute='
					+ startMinute + '&endMinute=' + endMinute;
			if (bid)
				url += ('&bid=' + bid);//如果是修改，需要带上bid
			var result = false;
			$.ajax({
				url : url,
				type : 'get',
				async : false,
				fail : function(data) {
					return false;
				},
				success : function(json) {
					if (!json.success)
						MyDialog.alert(json.message);
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
