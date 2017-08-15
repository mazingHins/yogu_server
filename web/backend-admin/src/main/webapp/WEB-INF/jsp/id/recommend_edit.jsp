<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>ID管理--ID推荐编辑</title>
</head>
<style>
label, label input {
	cursor: pointer
}
font {
	color: red;
	font-weight: bold;
	font-size: 12px
}
</style>
<body class="skin-blue sidebar-mini">
<div class="wrapper"> 
  
  <!-- header -->
  <jsp:include page="/include/header.jsp" />
  
  <!-- sidebar -->
  <jsp:include page="/include/sidebar.jsp" />
  <link href="/static/plugins/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header"> <a style="cursor:pointer" onclick="back();">返回推荐列表</a> </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <table class="table">
                <tr>
                  <td width="250" align="right">选择要推送给哪个城市的用户：</td>
                  <td width="200"><select class="form-control" id="cityCode">
                      <option value="all">全部</option>
                    </select></td>
                  <td></td>
                </tr>
                <tr>
                  <td width="250" align="right">选择要推送给哪种语言的用户：</td>
                  <td width="250"><select class="form-control" id="langCode">
                      <option value="all">全部</option>
                    </select></td>
                  <td></td>
                </tr>
                <tr>
                	<td width="250" align="right">状态：</td>
                	<td><font id="status" value="1"></font></td>
                	
                </tr>
                <tr>
                  <td width="250" align="right">发布人头像：</td>
                  <td width="250"><img id="avatarPreview" width="50"/><br>
                    <input id="avatar" type="hidden">
                    <input id="avatarFile" type="file" value="更换头像..." onChange="uploadAvatar();"></td>
                  <td><font color="red">(默认使用当前管理员的app头像)</font></td>
                </tr>
                <tr>
                  <td width="250" align="right">标题：</td>
                  <td width="250"><input type="text" id="title" name="title" class="form-control" placeholder="请输入标题..."/></td>
                  <td></td>
                </tr>
                <tr>
                  <td width="250" align="right">发布时间：<br>
                    <font>到点了才会推送给用户</font></td>
                  <td width="250"><div class="input-group">
                      <div class="input-group-addon"> <i class="fa fa-calendar"></i> </div>
                      <input type="text" class="form-control pull-right active" name="publishTime" id="publishTime" placeholder="请选择发布时间...">
                    </div></td>
                  <td></td>
                </tr>
              </table>
            </div>
            <div class="box-body">
              <input type="button" value="&nbsp;&nbsp;+&nbsp;&nbsp;" id="newButton" class="btn btn-success" onClick="createNewBlock()"/>
            </div>
            <div class="box-body"><font> 操作说明： <br>
              1.最多只能添加9个推荐块<br>
              2.推荐类型有5种，分别是：餐厅mini blog、餐厅c blog、美食卡片、瀑布流、h5页面<br>
              3.填写规则(必须在对应的输入框填写!!!)：<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-1.当推荐类型为“餐厅mini blog”时，必须填写餐厅id<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-2.当推荐类型为“餐厅c blog”时，必须填写店铺id<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-3.当推荐类型为“美食卡片”时，必须要填写菜品key，这时候app会在当前界面弹出美食卡片；如果同时填写餐厅id，则会跳去该餐厅的mini blog然后再弹起美食卡片<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-4.当推荐类型为“瀑布流”时，必须填写tag列表，tag列表是前往瀑布流页面中filter的tag参数，多个用英文逗号分割(例如：10001,10002)<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-5.当推荐类型为“h5页面”时，必须填写url，并指定是否可以分享<br>
              &nbsp;&nbsp;&nbsp;&nbsp;3-6.图片上传要求：<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一张图：610x190<br>
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;两张图：第一张400x190；第二张190x190<br>
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;三张图以上：190x190<br>
              </font> </div>
            <div class="box-body" id="recommendBody"> </div>
            <div class="box-body" id="addRecommendButton" style="display:none">
              <input type="button" value="保存" id="button" class="btn btn-success" onClick="return addRecommend();"/>
            </div>
          </div>
        </div>
        <!-- /.col --> 
      </div>
      <!-- /.row --> 
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
<script src="/static/plugins/datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script> 
<script type="text/javascript">
	var maxBlockNum = 9;
	var index = 0;
	var now = 0;
	var status = 1;
	
	var langCodeValue = $.getUrlParam('langCode') || 'zh';
	var cityCodeValue = $.getUrlParam('cityCode') || '020';
	
	$(function() {		
		init();
	});
	
	function init(){
		refreshNewButton();
		getAreaConfig();
		getLangConfig();
		getIdRecommend();
		
		requestNode('/system/admin/now', 'get', {}, function(data){
			now = parseInt(data) || new Date().getTime();

			$('#publishTime').datetimepicker({
				format: 'yyyy-mm-dd hh:ii:ss',
				startDate: new Date(now).format("yyyy-MM-dd hh:mm"),
				autoclose: true,
				startView: 1, 
				maxView: 1
			}).on('changeDate', function() {
				requestNode('/system/admin/now', 'get', {}, function(data){
					now = parseInt(data) || new Date().getTime();
					
					var publishTime = $('#publishTime').val().trim();
					var publishTimestamp = moment(publishTime).unix();
					$('#publishTime').val(publishTime);
				});
			});
		});
	}
	
	function getIdRecommend(){
		var rid = $.getUrlParam('rid') || 0;
		if(rid <= 0){
			$('#button').attr('onClick', 'addRecommend();').attr('value','保存');
			getAdminInfo();
			return;
		}
		
		$('#button').attr('onClick', 'editRecommend();').attr('value','修改');
		requestNode('/user/admin/getidrecommend', 'get', {rid:rid}, function(data){
			$('#cityCode').val(data.cityCode);
			$('#langCode').val(data.langCode);
			var url = IMAGE_DOMAIN + data.avatar;
			$('#avatarPreview').attr('src', url);
			$('#avatar').val(data.avatar);
			$('#title').val(data.title);
			$('#publishTime').val(moment(data.publishTime * 1000).format('YYYY-MM-DD HH:mm:ss'));
			status = data.status;
			
			if(status == 1){	
				$('#status').html('显示');
				$('#status').attr('value', 1);
			} else {
				$('#status').html('隐藏');
				$('#status').attr('value', 0);
			}

			// 显示每一个块
			for(var i = 0; i < data.itemsJson.length; i++){
				var item = data.itemsJson[i];
				createNewBlock(item);
			}
		});
	}
	
	function back(){
		var langCodeValue = $.getUrlParam('langCode') || 'zh';
		var cityCodeValue = $.getUrlParam('cityCode') || '020';
		window.location.href = '/admin/id/recommend/list.xhtm?cityCode=' + langCodeValue + '&langCode=' + cityCodeValue;
	}
	
	function getAreaConfig(){
		requestNode('/system/admin/areaconfig', 'get', {}, function(data){
			var html = '';
			for(var area of data){
				if(cityCodeValue && cityCodeValue == area.code){
					html += '<option value="'+area.code+'" selected>'+area.name+'</option>';
				} else {
					html += '<option value="'+area.code+'">'+area.name+'</option>';
				}
			}
			$('#cityCode').append(html);
		});
	}
	
	function getLangConfig(){
		requestNode('/system/admin/langconfig', 'get', {}, function(data){
			var html = '';
			for(var lang of data){
				if(langCodeValue && langCodeValue == lang.name){
					html += '<option value="'+lang.name+'" selected>'+lang.description+'</option>';
				} else {
					html += '<option value="'+lang.name+'">'+lang.description+'</option>';
				}
			}
			$('#langCode').append(html);
		});
	}
	
	function getAdminInfo(){
		requestNode('/oauth/admin/info', 'get', {}, function(data){
			var url = IMAGE_DOMAIN + data.profile.profilePic;
			$('#avatarPreview').attr('src', url);
			$('#avatar').val(data.profile.profilePic);
		});
	}
	
	function refreshNewButton(){
		$('#newButton').css('cursor','pointer');
		if(maxBlockNum <= 0){
			$('#newButton').attr('title', '你已经不可以再新增推荐块了！').attr('disabled', 'disabled').removeClass('btn-success').addClass('btn-inverse');
		} else {
			$('#newButton').attr('title', '新增一个推荐块，你还可以新增'+maxBlockNum+'个！').removeAttr('disabled').removeClass('btn-inverse').addClass('btn-success');
		}
	}
	
	function createNewBlock(item){
		maxBlockNum -= 1;
		refreshNewButton();
	
		var _index = ++index;
		var html = '<div style="width:40%;float:left;margin:10px;height:758px" id="block-'+_index+'">';
		html += '<h3>内容'+_index+'<a href="javascript:void(0)" style="position:relative;top:-10px;font-size:10px" onClick="return deleteNewBlock(\'block-'+_index+'\');" title="点我删除本块数据">X</a></h3>';
		html += '<table class="table" width="100%">';
		html += '<tr><td align="right" width="120">推荐类型<font>*</font>：</td>';
		html += '<td width="300"><select id="target-'+_index+'" class="form-control" onChange="switchTarget('+_index+', this.value);switchTargetParams('+_index+', this.value);">';
		html += '<option value="">=======</option>';
		html += '<option value="store"'+(item && item.target === 'store' ? ' selected' : '')+'>餐厅mini blog</option>';
		html += '<option value="cblog"'+(item && item.target === 'cblog' ? ' selected' : '')+'>餐厅c blog</option>';
		html += '<option value="dish"'+(item && item.target === 'dish' ? ' selected' : '')+'>美食卡片</option>';
		html += '<option value="index"'+(item && item.target === 'index' ? ' selected' : '')+'>瀑布流</option>';
		html += '<option value="h5"'+(item && item.target === 'h5' ? ' selected' : '')+'>h5页面</option>';
		html += '</select></td><td></td></tr>';		
		
		html += '<tr><td align="right" width="120" id="targetIdTitle-'+_index+'"></td><td width="150"><input type="text" class="form-control" id="targetId-'+_index+'"'+(item && item.target?' value="'+item.targetId+'"':'')+'></td><td></td></tr>';
		
		html += '<tr><td align="right" width="120" id="targetParamsTitle-'+_index+'"></td><td width="150"><input type="text" class="form-control" id="targetParams-'+_index+'"'+(item && item.targetParams?' value="'+item.targetParams+'"':'')+'></td><td></td></tr>';
		
		html += '<tr height="100"><td align="right" width="120" id="urlTitle-'+_index+'"></td><td width="150"><input type="text" class="form-control" id="url-'+_index+'"'+(item && item.url?' value="'+item.url+'"':'')+'><br><label>分享：<input type="radio" id="canShare-'+_index+'-yes" name="canShare-'+_index+'" value="1" '+(!item || item && item.canShare=='1'?' checked="checked"':'')+' onClick="selectShare('+_index+',\'yes\');"/></label><label>&nbsp;&nbsp;不分享：<input type="radio" id="canShare-'+_index+'-no" name="canShare-'+_index+'" value="0" '+(item && item.canShare == '0'?' checked="checked"':'')+' onClick="selectShare('+_index+',\'no\')"/></label></td><td></td></tr>';
	
		html += '<tr height="305"><td align="right" width="120" id="shareInfoTitle-'+_index+'"></td><td width="150" id="shareInfo-'+_index+'"></td><td></td></tr>';
		
		html += '<tr><td align="right" width="120" id="imageTitle-'+_index+'">推荐图片：</td><td width="150"><img width="50" height="50" id="imagePreview-'+_index+'"'+(item && item.image?' src="'+IMAGE_DOMAIN+item.image+'"':'')+'/><br><input type="hidden" class="form-control" id="image-'+_index+'" value="'+(item && item.image?item.image:'')+'"><br><input type="file" class="form-control" id="imageInput-'+_index+'" title="上传图片" onChange="uploadImage('+_index+')"/></td><td></td></tr>';
		
		html += '</table>';
		html += '</div>';
		$('#recommendBody').append(html);
		
		$('#urlTitle-'+_index).parent().find('label').css('display','none');
		$('#addRecommendButton').css('display','');
		
		if(item){
			if(item.target){
				switchTarget(_index, item.target);
				switchTargetParams(_index, item.target);
			}
			
			if(item.target === 'h5'){
				$('#canShare-'+_index+'-yes').parent().removeAttr('style');
				$('#canShare-'+_index+'-no').parent().removeAttr('style');
				
				var canShare = $('#canShare-'+_index+'-yes').attr("checked") ? true : false;
				if(canShare){
					$('#shareInfoTitle-'+_index).html('分享信息<font>*</font>：');
					$('#shareInfo-'+_index).html(getShareInfo(index, item));
				} else {
					$('#shareInfoTitle-'+index).html('');
					$('#shareInfo-'+index).html('');
				}
			}
		}
	}
	
	function deleteNewBlock(id){
		if(!confirm('你确定要删除本块！')){
			return false;
		}
		
		$('#'+id).remove();
		
		maxBlockNum += 1;
		refreshNewButton();
		
		--index;
		
		if(maxBlockNum >= 9){
			$('#addRecommendButton').css('display', 'none');
		} else {
			$('#addRecommendButton').css('display', '');
		}
		
		// 修正各个id
		$('#recommendBody div').each(function(i, e){
			var _index = i + 1;
			var id = 'block-'+_index;
			var origin = parseInt($(e).attr('id').replace(new RegExp(/[^0-9]+/g), "")) || 0;
			$(e).attr('id', id);
			$('#'+id+' h3').html('内容'+_index+'<a href="javascript:void(0)" style="position:relative;top:-10px;font-size:10px" onClick="return deleteNewBlock(\'block-'+_index+'\');" title="点我删除本块数据">X</a>');
			$('#'+id+' select').attr('id', 'target-'+_index).attr('onChange', 'switchTarget('+_index+', this.value);');
			
			$('#'+id+' #targetIdTitle-'+origin).attr('id', 'targetIdTitle-'+_index);
			$('#'+id+' #targetId-'+origin).attr('id', 'targetId-'+_index);
			
			$('#'+id+' #targetParamsTitle-'+origin).attr('id', 'targetParamsTitle-'+_index);
			$('#'+id+' #targetParams-'+origin).attr('id', 'targetParams-'+_index);
			
			$('#'+id+' #urlTitle-'+origin).attr('id', 'urlTitle-'+_index);
			$('#'+id+' #url-'+origin).attr('id', 'url-'+_index);
			
			$('#'+id+' #canShare-'+origin+'-yes').attr('id', 'canShare-'+_index+'-yes').attr('name', 'canShare-'+_index);
			$('#'+id+' #canShare-'+origin+'-no').attr('id', 'canShare-'+_index+'-no').attr('name', 'canShare-'+_index);
			
			var canShare = $('#canShare-'+_index+'-yes').attr("checked") ? true : false;
			if(canShare){
				$('#'+id+' #shareImgPreview-'+origin).attr('id', 'shareImgPreview-'+_index);
				$('#'+id+' #shareImgInput-'+origin).attr('id', 'shareImgInput-'+_index).attr('onChange','uploadShareImg('+_index+')');
				$('#'+id+' #shareImg-'+origin).attr('id', 'shareImg-'+_index);
				
				$('#'+id+' #shareTitle-'+origin).attr('id', 'shareTitle-'+_index);
				$('#'+id+' #shareTimelineTitle-'+origin).attr('id', 'shareTimelineTitle-'+_index);
				$('#'+id+' #shareContent-'+origin).attr('id', 'shareContent-'+_index);
				
				$('#'+id+' #showPub-'+origin+'-yes').attr('id', 'showPub-'+_index+'-yes').attr('name', 'showPub-'+_index);
				$('#'+id+' #showPub-'+origin+'-no').attr('id', 'showPub-'+_index+'-no').attr('name', 'showPub-'+_index);
			}
			
			$('#'+id+' #imageTitle-'+origin).attr('id', 'imageTitle-'+_index);
			$('#'+id+' #imagePreview-'+origin).attr('id', 'imagePreview-'+_index);
			$('#'+id+' #imageInput-'+origin).attr('id', 'imageInput-'+_index).attr('onChange','uploadImage('+_index+')');
			$('#'+id+' #image-'+origin).attr('id', 'image-'+_index);
		});
		
		return true;
	}
	
	function switchTarget(index, value){
		switch((value || '').trim()){
			case 'store':
				$('#targetIdTitle-'+index).html('餐厅id<font>*</font>：');
				$('#targetId-'+index).attr('placeholder','请输入餐厅id...');
				$('#urlTitle-'+index).html('');
				$('#urlTitle-'+index).parent().find('label').css('display','none');
				$('#url-'+index).removeAttr('placeholder').val('');
				$('#shareInfoTitle-'+index).html('');
				$('#shareInfo-'+index).html('');
				break;
			case 'dish':
				$('#targetIdTitle-'+index).html('餐厅id：');
				$('#targetId-'+index).attr('placeholder','请输入餐厅id...');
				$('#urlTitle-'+index).html('');
				$('#urlTitle-'+index).parent().find('label').css('display','none');
				$('#url-'+index).removeAttr('placeholder').val('');
				$('#shareInfoTitle-'+index).html('');
				$('#shareInfo-'+index).html('');
				break;
			case 'cblog':
				$('#targetIdTitle-'+index).html('店铺id<font>*</font>：');
				$('#targetId-'+index).attr('placeholder','请输入店铺id...');
				$('#urlTitle-'+index).html('');
				$('#urlTitle-'+index).parent().find('label').css('display','none');
				$('#url-'+index).removeAttr('placeholder').val('');
				$('#shareInfoTitle-'+index).html('');
				$('#shareInfo-'+index).html('');
				break;
			case '':
				$('#urlTitle-'+index).html('');
				$('#urlTitle-'+index).parent().find('label').css('display','none');
				$('#url-'+index).removeAttr('placeholder').val('');
				$('#shareInfoTitle-'+index).css('display','none').html('');
				$('#shareInfo-'+index).css('display','none').html('');
				break;
			default:
				$('#targetIdTitle-'+index).html('');
				$('#targetId-'+index).removeAttr('placeholder');
				$('#urlTitle-'+index).html('分享url<font>*</font>：');
				$('#urlTitle-'+index).parent().find('label').css('display','');
				$('#url-'+index).attr('placeholder','请输入分享url...');
				$('#shareInfoTitle-'+index).html('分享信息<font>*</font>：');
				$('#shareInfo-'+index).html(getShareInfo(index));
				break;
		}
	}
	
	function selectShare(index, option){
		if(option === 'yes'){
			$('#canShare-'+index+'-yes').attr('checked', 'checked');
			$('#canShare-'+index+'-no').removeAttr('checked');
			$('#shareInfoTitle-'+index).html('分享信息<font>*</font>：');
			$('#shareInfo-'+index).html(getShareInfo(index));
		} else {
			$('#canShare-'+index+'-yes').removeAttr('checked');
			$('#canShare-'+index+'-no').attr('checked', 'checked');
			$('#shareInfoTitle-'+index).html('');
			$('#shareInfo-'+index).html('');
		}
	}
	
	function selectShowPub(index, option){
		if(option === 'yes'){
			$('#showPub-'+index+'-yes').attr('checked', 'checked');
			$('#showPub-'+index+'-no').removeAttr('checked');
		} else {
			$('#showPub-'+index+'-yes').removeAttr('checked');
			$('#showPub-'+index+'-no').attr('checked', 'checked');
		}
	}
	
	function getShareInfo(index, item){
		var html = '<table style="font-size:10px;padding:10px">';
		html += '<tr><td width="80">分享时的图标<font>*</font>：</td><td><img width="50" height="50" id="shareImgPreview-'+index+'"'+(item && item.shareImg?' src="'+IMAGE_DOMAIN+item.shareImg+'"':'')+'/><br><input type="hidden" class="form-control" id="shareImg-'+index+'" value="'+(item && item.shareImg?item.shareImg:'')+'"><br><input type="file" class="form-control" id="shareImgInput-'+index+'" title="上传分享时的图标" onChange="uploadShareImg('+index+')"/></td><td></td></tr>';	
		html += '<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
		html += '<tr><td>分享给朋友显示的标题<font>*</font>：</td><td><input type="text" class="form-control" id="shareTitle-'+index+'"'+(item && item.shareTitle?' value="'+item.shareTitle+'"':'')+' placeholder="请输入分享给朋友显示的标题..."></td><td></td></tr>';
		html += '<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
		html += '<tr><td>分享到朋友圈显示的标题<font>*</font>：</td><td><input type="text" class="form-control" id="shareTimelineTitle-'+index+'"'+(item && item.shareTimelineTitle?' value="'+item.shareTimelineTitle+'"':'')+' placeholder="请输入分享到朋友圈显示的标题..."></td><td></td></tr>';
		html += '<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
		html += '<tr><td>分享专题显示的内容<font>*</font>：</td><td><input type="text" class="form-control" id="shareContent-'+index+'"'+(item && item.shareContent?' value="'+item.shareContent+'"':'')+' placeholder="请输入分享专题显示的内容..."></td><td></td></tr>';
		html += '<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>';
		html += '<tr><td>是否显示发布时间<font>*</font>：</td><td><label>显示：<input type="radio" id="showPub-'+index+'-yes" name="showPub-'+index+'" value="1" '+(!item || item && item.showPub=='1'?' checked="checked"':'')+' onClick="selectShowPub('+index+',\'yes\');"/></label><label>&nbsp;&nbsp;不显示：<input type="radio" id="showPub-'+index+'-no" name="showPub-'+index+'" value="0" '+(item && item.showPub == '0'?' checked="checked"':'')+' onClick="selectShowPub('+index+',\'no\')"/></label></td><td></td></tr>';
		html += '</table>';
		return html;
	}
	
	function switchTargetParams(index, value){
		switch(value){
			case 'index':
				$('#targetParamsTitle-'+index).html('tag列表<font>*</font>：');
				$('#targetParams-'+index).attr('placeholder','默认选中的tag列表，多个以英文逗号分隔...');
				$('#urlTitle-'+index).html('');
				$('#urlTitle-'+index).parent().find('label').css('display','none');
				$('#url-'+index).removeAttr('placeholder');
				$('#shareInfoTitle-'+index).css('display','none').html('');
				$('#shareInfo-'+index).css('display','none').html('');
				break;
			case 'dish':
				$('#targetParamsTitle-'+index).html('菜品key<font>*</font>：');
				$('#targetParams-'+index).attr('placeholder','请输入菜品key...');
				break;
			default:
				$('#targetParamsTitle-'+index).html('');
				$('#targetParams-'+index).removeAttr('placeholder');
				break;
		}
	}
	
	function uploadImage(index){
		uploadFile($('#imageInput-'+index), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#imagePreview-'+index).attr('src', url);
			$('#image-'+index).val(data);
		}, 500, 500);
	}
	
	function uploadShareImg(index){
		uploadFile($('#shareImgInput-'+index), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#shareImgPreview-'+index).attr('src', url);
			$('#shareImg-'+index).val(data);
		}, 500, 500);
	}
	
	function uploadAvatar(){
		uploadFile($('#avatarFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#avatarPreview').attr('src', url);
			$('#avatar').val(data);
		}, 500, 500);
	}
	
	function check() {
		var data = {};
		
		var cityCode = $('#cityCode').val().trim();
		if(!cityCode){
			MyDialog.alert('未选择推送城市！');
			return false;
		}
		data.cityCode = cityCode;
		
		var langCode = $('#langCode').val().trim();
		if(!langCode){
			MyDialog.alert('未选择推送语言！');
			return false;
		}
		data.langCode = langCode;
		
		var title = $('#title').val().trim();
		if(!title){
			MyDialog.alert('未填写标题！');
			return false;
		}
		if(title.length > 40){
			MyDialog.alert('标题长度不能大于40！');
			return false;
		}
		data.title = title;
		
		var avatar = $('#avatar').val().trim();
		if(!avatar){
			MyDialog.alert('未上传发布人头像！');
			return false;
		}
		data.avatar = avatar;
		
		var publishTime = $('#publishTime').val().trim();
		if(!publishTime){
			MyDialog.alert('未选择发布时间！');
			return false;
		}
		data.publishTime = publishTime;
		
		var num = parseInt($('#recommendBody div').length) || 0;
		if(num <= 0){
			MyDialog.alert('只少要有一个编辑块，才可以保存！');
			return false;
		}
		
		var list = [];
		for(var i = 1; i <= num; i++) {
			var item = {};
			
			var target = $('#target-' + i).val().trim();
			if(!target){
				MyDialog.alert('内容'+i+'未选择推荐类型！');
				return false;
			}
			item.target = target;
			
			var targetId = $('#targetId-' + i).val().trim();
			if(!targetId){
				var msg = '';
				switch(target){
					case 'store':
						msg = '餐厅id';
						MyDialog.alert('内容'+i+'未填写'+msg+'！');
						return false;
					case 'cblog':
						msg = '店铺id';
						MyDialog.alert('内容'+i+'未填写'+msg+'！');
						return false;
				}
			}
			item.targetId = targetId;
			
			var targetParams = $('#targetParams-' + i).val().trim();
			if(!targetParams){
				switch(target){
					case 'index':
						MyDialog.alert('内容'+i+'未填写tag列表！');
						return false;
					case 'dish':
						msg = '菜品key';
						MyDialog.alert('内容'+i+'未填写'+msg+'！');
						return false;
				}
			}
			item.targetParams = targetParams;
			
			var url = $('#url-' + i).val().trim();
			if(!url && target === 'h5'){
				MyDialog.alert('内容'+i+'未填写url地址！');
				return false;
			}
			if(!/^((http|https):\/\/(\w+:{0,1}\w*@)?(\S+)|)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/.test(url) && target === 'h5'){
				MyDialog.alert('内容'+i+'的url地址错误，请输入正确的url地址！例如：https://www.mazing.com');
				return false;
			}
			item.url = url;
			item.canShare = $('#canShare-'+i+'-yes').attr("checked") ? 1 : 0;
			
			if(item.canShare === 1){
				var shareImg = $('#shareImg-' + i).val().trim();
				if(!shareImg){
					MyDialog.alert('内容'+i+'未上传分享时的图标！');
					return false;
				}
				item.shareImg = shareImg;
				
				var shareTitle = $('#shareTitle-' + i).val().trim();
				if(!shareTitle){
					MyDialog.alert('内容'+i+'未填写分享给朋友显示的标题！');
					return false;
				}
				item.shareTitle = shareTitle;
				
				var shareTimelineTitle = $('#shareTimelineTitle-' + i).val().trim();
				if(!shareTimelineTitle){
					MyDialog.alert('内容'+i+'未填写分享到朋友圈显示的标题！');
					return false;
				}
				item.shareTimelineTitle = shareTimelineTitle;
				
				var shareContent = $('#shareContent-' + i).val().trim();
				if(!shareContent){
					MyDialog.alert('内容'+i+'未填写分享专题显示的内容！');
					return false;
				}
				item.shareContent = shareContent;
				
				item.showPub = $('#showPub-'+i+'-yes').attr("checked") ? 1 : 0;
			}
			
			var image = $('#image-' + i).val().trim();
			if(!image){
				MyDialog.alert('内容'+i+'未上传图片！');
				return false;
			}
			item.image = image;
			list.push(item);
		}
		
		data.status = $('#status').attr('value');
		data.itemsJson = JSON.stringify(list);
		return data;
	}
	
	function addRecommend(){
		var data = check();
		if(!data){
			return false;
		}
		
		var publishTime = moment($('#publishTime').val().trim()).toDate().getTime();
		requestNode('/system/admin/now', 'get', {}, function(now){
			if(publishTime <= now){
				MyDialog.alert('发布时间不能小于等于当前时间，设定的发布时间是：'+$('#publishTime').val().trim()+'，当前服务器时间是：'+moment(now).format('YYYY-MM-DD HH:mm:ss'));
				return false;
			}
			requestNode('/user/admin/addidrecommend', 'post', data, function(data){
				MyDialog.alert('添加成功！');
				window.location.href = '/admin/id/recommend/list.xhtm?cityCode=' + cityCodeValue + '&langCode=' + langCodeValue;
			});
		});
		return true;
	}
	
	function editRecommend(){
		var data = check();
		if(!data){
			return false;
		}
		
		var publishTime = moment($('#publishTime').val().trim()).toDate().getTime();
		requestNode('/system/admin/now', 'get', {}, function(now){
			if(publishTime <= now){
				MyDialog.alert('发布时间不能小于等于当前时间，设定的发布时间是：'+$('#publishTime').val().trim()+'，当前服务器时间是：'+moment(now).format('YYYY-MM-DD HH:mm:ss'));
				return false;
			}
			data.rid = $.getUrlParam('rid');
			requestNode('/user/admin/editidrecommend', 'post', data, function(data){
				MyDialog.alert('修改成功！');
				window.location.href = '/admin/id/recommend/list.xhtm?cityCode=' + cityCodeValue + '&langCode=' + langCodeValue;
			});	
		});
		return true;
	}
</script>
</body>
</html>