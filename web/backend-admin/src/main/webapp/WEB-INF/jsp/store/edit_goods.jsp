<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>添加美食</title>
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
			<h1 id="titleMessage">
				添加美食
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 返回餐厅列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<p class="text-left">说明：</p>
					<p class="text-left">（1）编辑美食的过程中，请不要离开界面；</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form id="editGoods" class="form-horizontal" action="/admin/goods/saveGoods.do" onsubmit="return validate()" method="post">
						<div class="form-group">
							<label class="col-md-2 control-label">美食名称</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="goodsName"
									   name="goodsName" title="商品名称" placeholder="商品名称"
									   maxlength="12">
								<input name="goodsId" id="goodsId" type="hidden" value="0"/>
								<input name="goodsKey" id="goodsKey" type="hidden" value="0"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">零售价</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="retailPrice" name="retailPrice"
									   title="零售价" placeholder="零售价" maxlength="7">
							</div>
							<div class="col-md-3">
								分
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">批发价</label>
							<div class="col-md-3">
								<input class="form-control" type="text" id="tradePrice" name="tradePrice"
									   title="批发价" placeholder="批发价" maxlength="7">
							</div>
							<div class="col-md-3">
								分
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">商品主图</label>
							<div class="col-md-3">
							<input id="cardImg" name="cardImg" type="hidden" />
							<input type="file" id="upfile">
							<input type="button" id="upJQuery" value="上传" onclick="uploadCardImg()">
							</div>
							<div class="col-md-3">
								<img id="cardImgPreview" width="80" src="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">商品介绍</label>
							<div class="col-md-3">
							<input id="content" name="content" type="hidden" />
							<input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
							</div>
							<div class="col-md-3" id="contentImg">
								
							</div>
						</div>
						 <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
						<div class="form-group">
							<div class="col-md-offset-2">
								<button class="btn btn-primary"
										type="submit" id="submit" >提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>

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

	$(function() {
		
		var goodsKey = $.getUrlParam("goodsKey");
		loadGoods(goodsKey)// 编辑
		
		$(function () {
		    //0.初始化fileinput
		    var oFileInput = new FileInput();
		    oFileInput.Init("txt_file", "/admin/system/uploadMultPic.do");
		});
		
		$('#editGoods').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					if (json.success) {
						MyDialog.alert(json.message, function() {
							window.location.href =  "/admin/goods/list.xhtm";
						});
					}
					else {
						MyDialog.alert(json.message);
					}

				} catch (e) {
				}
			}
		});

	});
	
	//初始化fileinput
		var FileInput = function () {
	    var oFile = new Object();

	    //初始化fileinput控件（第一次初始化）
	    oFile.Init = function(ctrlName, uploadUrl) {
	    var control = $('#' + ctrlName);

	    //初始化上传控件的样式
	    control.fileinput({
	        language: 'zh', //设置语言
	        uploadUrl: uploadUrl, //上传的地址
	        allowedFileExtensions: ['jpg', 'png'],//接收的文件后缀
	        showUpload: true, //是否显示上传按钮
	        showCaption: false,//是否显示标题
	        browseClass: "btn btn-primary", //按钮样式     
	        maxFileCount: 10, //表示允许同时上传的最大文件个数
	        enctype: 'multipart/form-data',
	        validateInitialCount:true,
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	    });

	    //导入文件上传完成之后的事件
	    $("#txt_file").on("fileuploaded", function (event, data, previewId, index) {
	        var obj = data.response.object;
	        console.log(obj);
	        var content = $("#content").val();
	        if(content == null || content = ''){
	        	content = obj;
	        }else{
	        	content = content + "," + obj;
	        }
	        $("#content").val(content);
	    });
	}
	    return oFile;
	};

	// 校验参数
	function validate() {
		var message = '';
		var goodsName = $.trim($('#goodsName').val());
		var retailPrice = $.trim($('#retailPrice').val());
		var tradePrice = $.trim($('#tradePrice').val());
		var cardImg = $.trim($('#cardImg').val());
		if (goodsName == '') {
			message = '请输入商品名称。';
		} else if (!$.isNumeric(retailPrice) || retailPrice > 999999 || retailPrice<1) {
			message = '请输入零售价（1-999999）。';
		}else if (!$.isNumeric(tradePrice) || tradePrice > 999999 || tradePrice<1) {
			message = '请输入批发价（1-999999）。';
		}else if (cardImg == '') {
			message = '请上传商品主图';
		}
		if (message != '') {
			MyDialog.alert(message);
		}
		return (message == '');
	}

	
	function uploadCardImg(storeId) {
		var fd = new FormData();
		fd.append("upload", 1);
		fd.append("upfile", $("#upfile").get(0).files[0]);
		$.ajax({
			url: "/admin/goods/modifyCardImage.do",
			type: "POST",
			processData: false,
			contentType: false,
			data: fd,
			success: function(d) {
				$("#cardImgPreview").attr("src", d.object);
				$("#cardImg").val(d.object);
			}
		});
	}
	
	// 加载优惠券数据
	function loadGoods(goodsKey) {
		if(goodsKey == null || goodsKey == ''){
			return;
		}
		
		
		$.getJSON('/admin/goods/detail', {
			'goodsKey' : goodsKey
		}, function(json) {
			if (json.success) {
				fillForm(json.object);
			} else {
				BootstrapDialog.show({
					title: '错误',
					message: json.message,
					buttons: [{
						label: '确定',
						action: function (dialog) {
							dialog.close();
							window.location.href = '/admin/goods/list.xhtm';
						}
					}]
				});
			}
		});
	}
	
	function fillForm(goods){
		$("#goodsId").val(goods.goodsId);
		$("#goodsKey").val(goods.goodsKey);
		$("#goodsName").val(goods.goodsName);
		$("#retailPrice").val(goods.retailPrice);
		$("#tradePrice").val(goods.tradePrice);
		$("#cardImg").val(goods.cardImg);
		$("#cardImgPreview").attr("src", goods.cardImg);
	}
	
</script>
</body>
</html>
