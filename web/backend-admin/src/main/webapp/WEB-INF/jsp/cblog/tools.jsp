<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>cblog管理--维护工具</title>
</head>
<style>
input {
	margin: 5px
}
ul, ul li {
	list-style: none;
	margin: 0
}
label {
	cursor: pointer
}
</style>
<body class="skin-blue sidebar-mini">
<div class="wrapper"> 
  
  <!-- header -->
  <jsp:include page="/include/header.jsp" />
  
  <!-- sidebar -->
  <jsp:include page="/include/sidebar.jsp" />
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper"> 
    
    <!-- 导入品牌 modal-->
    <div class="modal fade" id="importBrandModal" tabindex="-1" role="dialog" aria-labelledby="importBrandModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
            <h4 class="modal-title" id="importBrandModalLabel">导入品牌</h4>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-xs-12">
                <input type="file" class="form-control" id="importBrandFile" onChange="return importBrand();"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 导入商圈 modal-->
    <div class="modal fade" id="importBusinessDistrictModal" tabindex="-1" role="dialog" aria-labelledby="importBusinessDistrictModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
            <h4 class="modal-title" id="importBusinessDistrictModalLabel">导入商圈</h4>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-xs-12">
                <input type="file" class="form-control" id="importBusinessDistrictFile" onChange="return importBusinessDistrict();"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 导入cblog modal-->
    <div class="modal fade" id="importCblogModal" tabindex="-1" role="dialog" aria-labelledby="importCblogModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
            <h4 class="modal-title" id="importCblogModalLabel">导入cblog</h4>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-xs-12">
                <input type="file" class="form-control" id="importCblogFile" onChange="return importCblog();"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h2>工具下载 <small></small> </h2>
            </div>
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <input type="button" class="btn btn-primary" value="下载数据导入模板" onClick="window.location.href='https://mz-download.oss-cn-hangzhou.aliyuncs.com/cblog/cblog%E6%95%B0%E6%8D%AE%E6%A8%A1%E6%9D%BF.zip'"/>
                  <input type="button" class="btn btn-primary" value="下载OSS MAC版客户端" onClick="window.location.href='https://mz-download.oss-cn-hangzhou.aliyuncs.com/cblog/ossclient_v1.1.6.dmg'"/>
                  <input type="button" class="btn btn-primary" value="下载OSS windows客户端" onClick="window.location.href='https://mz-download.oss-cn-hangzhou.aliyuncs.com/cblog/oss-widows%E7%89%88.zip'"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h2>维护工具 <small></small> </h2>
            </div>
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <input type="button" class="btn btn-primary" value="刷新cblog opensearch" onClick="return refreshOpensearch();"/>
                  <input type="button" class="btn btn-primary" value="刷新cblog缓存" onClick="return refreshCblog();"/>
                  <input type="button" class="btn btn-primary" value="导入品牌数据" onClick="$('#importBrandModal').modal()"/>
                  <input type="button" class="btn btn-primary" value="导入商圈数据" onClick="$('#importBusinessDistrictModal').modal()"/>
                  <input type="button" class="btn btn-primary" value="导入cblog数据" onClick="$('#importCblogModal').modal();"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h2>注意事项 <small></small> </h2>
            </div>
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12" style="color:red;font-weight:bold"> 1.请根据模板的例子填写数据<br>
                  2.每次导入成功或者失败，都会发送邮件；导入失败的时候，会列出错误原因<br>
                  3.上传图片，请自行使用oss客户端上传，填写的时候填写全路径；什么叫全路径？以下图为例，标蓝的文件是全路径是：cblog0223/1/id_recommend_img/kfnanfeng.jpeg<br>
                  &nbsp;&nbsp;&nbsp;&nbsp;<img src="https://mz-download.oss-cn-hangzhou.aliyuncs.com/cblog/example.png" width="650" height="200"/><br>
                  4.所有“标签”和“营业时间”，都有各自的工具生成，请使用工具生成数据然后复制到excel文件中，以降低出错的概率<br>
                  5.操作上有任何疑问请联系june </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row" style="display:none" id="supportingConfigDIV">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h2>选择工具 <small></small> </h2>
            </div>
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <ul id="cacheTab" class="nav nav-tabs">
                    <li class="active" id="openHoursTabLi"><a href="#openHoursDIV" data-toggle="tab" onClick="resetAll();"><strong>营业时间</strong></a></li>
                    <li id="supportingConfigTabLi"><a href="#supportingConfig" data-toggle="tab" onClick="resetAll();"><strong>配套设施</strong></a></li>
                    <li id="cuisineConfigTabLi"><a href="#cuisine" data-toggle="tab" onClick="resetAll();"><strong>菜系</strong></a></li>
                    <li id="tagsConfigTabLi"><a href="#tags" data-toggle="tab" onClick="resetAll();"><strong>标签</strong></a></li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane fade in active" id="openHoursDIV">
                      <table class="table">
                        <tr>
                          <td width="100">营业时间：</td>
                          <td id="openHours"></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td><a href="javascript:void(0)" onClick="openHours();">增加营业时间</a></td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                          <td><input type="button" value="生成" onClick="$('#result').html('');create();"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" value="重置" onClick="resetAll()"/></td>
                        </tr>
                      </table>
                      <table class="table">
                        <tr>
                          <td id="result"></td>
                        </tr>
                      </table>
                    </div>
                    <div class="tab-pane fade in" id="supportingConfig">
                      <div class="row" style="display:none" id="supportingConfigDIV">
                        <div class="col-xs-12">
                          <div class="box">
                            <div class="box-body">
                              <div class="row">
                                <div class="col-sm-12" id="supportingConfig"> </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="tab-pane fade in" id="cuisine">
                      <div class="row" style="display:none" id="cuisineConfigDIV">
                        <div class="col-xs-12">
                          <div class="box">
                            <div class="box-body">
                              <div class="row">
                                <div class="col-sm-12" id="cuisineConfig"> </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="tab-pane fade in" id="tags">
                      <div class="row" style="display:none" id="tagsConfigDIV">
                        <div class="col-xs-12">
                          <div class="box">
                            <div class="box-body">
                              <div class="row">
                                <div class="col-sm-12" id="tagsConfig"> </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <jsp:include page="/include/footer.jsp" />
  <jsp:include page="/include/control-sidebar.jsp" />
</div>
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	
	
	var openHoursNum = 1;
	
	$(function(){
		openHours();
		getSupportingConfig();
		getCuisineConfig();
	});
	
	function openHours(data){
		var html = '', openHoursIndex = openHoursNum++;
        html += '<div class="col-xs-12" id="openHours'+openHoursIndex+'">';
		html += '<label>营业日：</label>';

		isBsdaysAll = data && data.days === 'all';
		var isBs24hours = data && parseInt(data.hours) === 24;

		if(isBsdaysAll){
			html += '<label>整周<input class="bsdays" type="checkbox" name="bsdaysAll" value="all" onClick="bsdaysAll($(this).parent().parent());" checked="checked"/></label>';
		} else {
        	html += '<label>整周<input class="bsdays" type="checkbox" name="bsdaysAll" value="all" onClick="bsdaysAll($(this).parent().parent());"/></label>';
		}
        html += '<label>&nbsp;|&nbsp;</label>';
		if(isBsdaysAll){
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="mon" disabled="disabled"/>周一</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="tue" disabled="disabled"/>周二</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="wed" disabled="disabled"/>周三</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="thu" disabled="disabled"/>周四</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="fri" disabled="disabled"/>周五</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sat" disabled="disabled"/>周六</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sun" disabled="disabled"/>周日</label>';
		} else if(data && Array.isArray(data.days)) {
			var weekArray = ['mon','tue','wed','thu','fri','sat','sun'];
			var weekStrArray = ['周一','周二','周三','周四','周五','周六','周日'];
			for(var i = 0; i < 7; i++){
				var week = weekArray[i];
				var weekStr = weekStrArray[i];
				if(data.days.indexOf(week) > -1){
					html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="'+week+'" checked="checked" onClick="weekdaysOnClick(this);"/>'+weekStr+'</label>';
				} else {
					html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="'+week+'" onClick="weekdaysOnClick(this);"/>'+weekStr+'</label>';
				}
			}
		} else {
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="mon" onClick="weekdaysOnClick(this);"/>周一</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="tue" onClick="weekdaysOnClick(this);"/>周二</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="wed" onClick="weekdaysOnClick(this);"/>周三</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="thu" onClick="weekdaysOnClick(this);"/>周四</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="fri" onClick="weekdaysOnClick(this);"/>周五</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sat" onClick="weekdaysOnClick(this);"/>周六</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sun" onClick="weekdaysOnClick(this);"/>周日</label>';
		}
		html += '<br>';
		html += '<label>营业时间：</label>';

		if(isBs24hours){
			html += '<label>24小时营业<input class="bs24hours" name="bs24hours" type="checkbox" value="24" onClick="bs24hours($(this).parent().parent());"  checked="checked"/></label>';
		} else {
			html += '<label>24小时营业<input class="bs24hours" name="bs24hours" type="checkbox" value="24" onClick="bs24hours($(this).parent().parent());"/></label>';
		}
        html += '<label>&nbsp;|&nbsp;</label><label>';

		if(isBs24hours){
			html += '<select class="form-control st" disabled="disabled">';
			html += '<option value="">========</option>';
			html += showTimePeriod();
			html += '</select>';
			html += '</label>';
			html += '&nbsp;&nbsp;至&nbsp;&nbsp;';
			html += '<label>';
			html += '<select class="form-control et" disabled="disabled">';
			html += '<option value="">========</option>';
			html += showTimePeriod();
			html += '</select>';
			html += '</label>';
		} else {
			var hours = data ? data.hours : '';
			var st = data ? hours.substring(0, hours.indexOf('-')) : '';
			var et = data ? hours.substring(hours.indexOf('-') + 1) : '';
			html += '<select class="form-control st">';
			html += '<option value="">========</option>';
			html += showTimePeriod(st);
			html += '</select>';
			html += '</label>';
			html += '&nbsp;&nbsp;至&nbsp;&nbsp;';
			html += '<label>';
			html += '<select class="form-control et">';
			html += '<option value="">========</option>';
			html += showTimePeriod(et);
			html += '</select>';
			html += '</label>';
		}
        html += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		if(openHoursNum > 2){
        	html += '<a href="javascript:void(0)" onClick="removeOpenHours();">删除营业时间</a>';
		}
        html += '</div>';
		$('#openHours').append(html);
	}

	function removeOpenHours(){
		openHoursNum--;
		$('#openHours'+openHoursNum).remove();
	}

	function showTimePeriod(time){
		var html = '';
		for(var i = 1451577600; i < 1451664000; i += 1800){
			var t = moment.unix(i).format('HH:mm');
			if(t === time){
				html += '<option value="' + t + '" selected="selected">' + t + '</option>';
			} else {
				html += '<option value="' + t + '">' + t + '</option>';
			}
		}

		// 次日
		for(var i = 1451664000; i <= 1451707200; i += 1800){
			var t = '1:' + moment.unix(i).format('HH:mm');
			if(t === time){
				html += '<option value="' + t + '" selected="selected">' + t.substring(2) + '(次日)</option>';
			} else {
				html += '<option value="' + t + '">' + t.substring(2) + '(次日)</option>';
			}
		}
		return html;
	}

	function bsdaysAll(obj){
		var id = obj.attr('id');
		var checked = $('#'+id+' .bsdays').attr('checked');
		if(checked){
			$('#'+id+' .weekdays').removeAttr('disabled');
			$('#'+id+' .bsdays').removeAttr('checked');
		} else {
			$('#'+id+' .weekdays').attr('disabled', 'disabled');
			$('#'+id+' .weekdays').removeAttr('checked');
			$('#'+id+' .bsdays').attr('checked', 'checked');
		}
	}
	
	function weekdaysOnClick(obj){
		obj = $(obj);
		var checked = obj.attr('checked');
		if(checked){
			obj.removeAttr('checked');
		} else {
			obj.attr('checked', 'checked');
		}
	}

	function bs24hours(obj){
		var id = obj.attr('id');
		obj = $('#'+id+' .form-control');
		var disabled = obj.attr('disabled');
		if(disabled){
			obj.removeAttr('disabled');
		} else {
			obj.attr('disabled', 'disabled');
		}
	}
	
	function create(){
		// 验证每一个营业时间段是否正确
		// 同一日期，同一时间段不可以重叠
		var openHours = [];
		let size = $('#openHours div').length;
		for(var i = 0; i < size; i++){
			var _openHours = {};

			var isBsdaysChecked = $('.bsdays:eq('+i+')').is(":checked");
			var isWeekdaysChecked1 = $('.weekdays:eq('+(0+i*7)+')').is(":checked");
			var isWeekdaysChecked2 = $('.weekdays:eq('+(1+i*7)+')').is(":checked");
			var isWeekdaysChecked3 = $('.weekdays:eq('+(2+i*7)+')').is(":checked");
			var isWeekdaysChecked4 = $('.weekdays:eq('+(3+i*7)+')').is(":checked");
			var isWeekdaysChecked5 = $('.weekdays:eq('+(4+i*7)+')').is(":checked");
			var isWeekdaysChecked6 = $('.weekdays:eq('+(5+i*7)+')').is(":checked");
			var isWeekdaysChecked7 = $('.weekdays:eq('+(6+i*7)+')').is(":checked");
			var isWeekdaysChecked = isWeekdaysChecked1 || isWeekdaysChecked2 || isWeekdaysChecked3 || isWeekdaysChecked4 || isWeekdaysChecked5 || isWeekdaysChecked6 || isWeekdaysChecked7;

			if(!isBsdaysChecked && !isWeekdaysChecked){
				alert('第'+(i+1)+'块营业时间配置未选择营业日！');
				return false;
			}

			if(isBsdaysChecked){
				_openHours.days = 'all';
				isBsdaysAll = true;
			} else {
				isBsdaysAll = isBsdaysAll ? isBsdaysAll : false;// 如果已经是true，则无需理会
				_openHours.days = [];

				if(isWeekdaysChecked1){
					_openHours.days.push('mon');
				}
				if(isWeekdaysChecked2){
					_openHours.days.push('tue');
				}
				if(isWeekdaysChecked3){
					_openHours.days.push('wed');
				}
				if(isWeekdaysChecked4){
					_openHours.days.push('thu');
				}
				if(isWeekdaysChecked5){
					_openHours.days.push('fri');
				}
				if(isWeekdaysChecked6){
					_openHours.days.push('sat');
				}
				if(isWeekdaysChecked6){
					_openHours.days.push('sun');
				}
			}

			var st = parseInt($('.st:eq('+i+')').val().replace(new RegExp(/:/g),'')) || 0;
			var et = parseInt($('.et:eq('+i+')').val().replace(new RegExp(/:/g),'')) || 0;

			if(st >= 10000){
				alert('营业开始时间不可以选次日！');
				return false;
			}

			var isBs24hoursChecked = $('.bs24hours:eq('+i+')').is(":checked");
			if(!isBs24hoursChecked && st >= et){
				alert('第'+(i+1)+'块营业时间配置营业时间配置有误，必须选择“24小时营业”或者起始时间不可小于等于结束时间！');
            	return false;
			}

			if(isBs24hoursChecked){
				_openHours.hours = 24;
			} else {
				_openHours.hours = $('.st:eq('+i+')').val() + '-' + $('.et:eq('+i+')').val();
			}
			
			openHours.push(_openHours);
		}
		
		
		// 判断一下，如果同一日期的时间段有重复，就报错
		var cpOpenHours = JSON.parse(JSON.stringify(openHours));
		for(var i = 0; i < openHours.length; i++){
			var op = openHours[i];
			for(var j = 0; j < cpOpenHours.length; j++){
				var _openHours = cpOpenHours[j];
				
				if(i === j){
					continue;
				}
				
				if(op.days.toString() !== _openHours.days.toString()){
					continue;
				}

				var opArr = op.hours.toString().split('-');
				var _opst = parseInt(opArr[0].toString().replace(new RegExp(/:/g),'')) || 0;
				var _opet = opArr.length > 1 ? parseInt(opArr[1].toString().replace(new RegExp(/:/g),'')) || 0 : 24;

				var _openHoursArr = _openHours.hours.toString().split('-');
				var _openHoursst = parseInt(_openHoursArr[0].toString().replace(new RegExp(/:/g),'')) || 0;
				var _openHourset = _openHoursArr.length > 1 ? parseInt(_openHoursArr[1].toString().replace(new RegExp(/:/g),'')) || 0 : 24;
				
				// 只要有一个24小时营业，就要报错
				if((_opst === 24 && _opet === 24) || (_openHoursst === 24 && _openHourset === 24)){
					alert('第'+(i+1)+'块营业时间配置有误，同一天内已设置24小时营业，请勿设置重叠的营业时间！');
            		return false;
				}
				
				// 判断时间段有没有重叠
				// 假设 a b、c d，如果b >= c，则证明两个时间段重叠
				if(_opst <= _openHoursst && _opet >= _openHourset){
					alert('第'+(i+1)+'块营业时间配置有误，同一天内的营业时间有重叠，请勿设置重叠的营业时间！');
            		return false;
				}
			}	
		}
		
		$('#result').html(JSON.stringify(openHours));
	}

	
	
	$('#importBrandModal').on('hidden.bs.modal', function () {
	  	clear($('#importBrandFile'));
	});
	
	$('#importBusinessDistrictModal').on('hidden.bs.modal', function () {
	  	clear($('#importBusinessDistrictFile'));
	});
	
	$('#importCblogModal').on('hidden.bs.modal', function () {
	  	clear($('#importCblogFile'));
	});
	
	function getSupportingConfig(){
		requestNode('/store/admin/supportingconfig', 'get', {}, function(data){
			var html = '';
			html += '<ul>';
			for(var config of data){
				html += '<li style="width:500px;float:left"><label><input class="supportingConfigItem" type="checkbox" value="'+config.id+'">&nbsp;&nbsp;'+config.name+'('+config.nameEn+')</label></li>';
			}
			html +='</ul>';
			html +='<br/><br/>';
			html +='<div class="col-sm-12"><input type="button" value="生成" onClick="createSupportingConfig()"/>&nbsp;&nbsp;<input type="button" value="重置" onClick="resetAll()"/></div>';
			html += '<div class="col-sm-12" id="supportingConfigResult"></div>';
			
			$('#supportingConfig').html(html);
			$('#supportingConfigDIV').css('display','block');
		});
	}
	
	function getCuisineConfig(){		
		$.ajax({
			url: '/admin/appIndex/storeTag/list?page=1&pageSize=500',
			method: 'get',
			error:function(){
				MyDialog.alert('请求java服务器出错！');
			},
			success:function(res){
				var tagsHtml = '<ul>';
				var cuisineHtml = '<ul>';
				
				for(var _data of res.object){				
					if(_data.categoryName === '菜系'){
						for(var cuisine of _data.tags){
							cuisineHtml += '<li style="width:500px;float:left"><label><input class="cuisineConfigItem" type="checkbox" value="'+cuisine.tagId+'">&nbsp;&nbsp;'+cuisine.tagName+'('+cuisine.enName+')</label></li>';
						}
					}
					
					for(var config of _data.tags){
						tagsHtml += '<li style="width:500px;float:left"><label><input class="tagsConfigItem" type="checkbox" value="'+config.tagId+'">&nbsp;&nbsp;'+config.tagName+'('+config.enName+')</label></li>';
					}
				}
				
				tagsHtml +='</ul>';
				tagsHtml +='<br/><br/>';
				tagsHtml +='<div class="col-sm-12"><input type="button" value="生成" onClick="createTagsConfig()"/>&nbsp;&nbsp;<input type="button" value="重置" onClick="resetAll()"/></div>';
				tagsHtml += '<div class="col-sm-12" id="tagsConfigResult"></div>';

				cuisineHtml +='</ul>';
				cuisineHtml +='<br/><br/>';
				cuisineHtml +='<div class="col-sm-12"><input type="button" value="生成" onClick="createCuisineConfig()"/>&nbsp;&nbsp;<input type="button" value="重置" onClick="resetAll()"/></div>';
				cuisineHtml += '<div class="col-sm-12" id="cuisineConfigResult"></div>';
				
				$('#tagsConfig').html(tagsHtml);
				$('#cuisineConfig').html(cuisineHtml);
				$('#tagsConfigDIV').css('display','block');
				$('#cuisineConfigDIV').css('display','block');
			}
		});
	}
	
	function createSupportingConfig(){
		var arr = $('#supportingConfigDIV .supportingConfigItem');
		var selectSupportingConfig = [];
		for(var i = 0; i < arr.length; i++){
			var obj = $(arr[i]);
			if(!obj.is(":checked")){
				continue;
			}
			selectSupportingConfig.push(obj.attr('value'));
		}
		if(selectSupportingConfig.length <= 0){
			MyDialog.alert('您没有选中任何配套设施！');
			return false;
		}
		$('#supportingConfigResult').html(selectSupportingConfig.join(','));
		return true;
	}
	
	function createTagsConfig(){
		var arr = $('#tagsConfigDIV .tagsConfigItem');
		var selectTagsConfig = [];
		for(var i = 0; i < arr.length; i++){
			var obj = $(arr[i]);
			if(!obj.is(":checked")){
				continue;
			}
			selectTagsConfig.push(obj.attr('value'));
		}
		if(selectTagsConfig.length <= 0){
			MyDialog.alert('您没有选中任何标签！');
			return false;
		}
		$('#tagsConfigResult').html(selectTagsConfig.join(','));
		return true;
	}
	
	function createCuisineConfig(){
		var arr = $('#cuisineConfigDIV .cuisineConfigItem');
		var selectCuisineConfig = [];
		for(var i = 0; i < arr.length; i++){
			var obj = $(arr[i]);
			if(!obj.is(":checked")){
				continue;
			}
			selectCuisineConfig.push(obj.attr('value'));
		}
		if(selectCuisineConfig.length <= 0){
			MyDialog.alert('您没有选中任何菜系！');
			return false;
		}
		$('#cuisineConfigResult').html(selectCuisineConfig.join(','));
		return true;
	}
	
	function selectSupportingConfigAll(){
		var arr = $('#supportingConfigDIV .supportingConfigItem');
		for(var i = 0; i < arr.length; i++){
			var obj = $(arr[i]);
			obj.attr('checked', 'checked');
		}
	}
	
	function deSelectSupportingConfigAll(){
		var arr = $('#supportingConfigDIV .supportingConfigItem');
		for(var i = 0; i < arr.length; i++){
			var obj = $(arr[i]);
			obj.removeAttr('checked');
		}
	}
	
	function alertMessage(){
		MyDialog.alert('请求成功！请查看服务器log关注执行进程并留意邮件！');
	}
	
	function clear(obj){
		var file = obj;
		file.after(file.clone().val(""));      
		file.remove();  
	}
	
	function resetAll(){
		// 重置营业时间
		$('#openHours').html('');
		openHoursNum = 1;
		openHours();
		$('#result').html('');
		
		// 重置配套设施
		var supportingConfigArr = $('#supportingConfigDIV .supportingConfigItem');
		for(var i = 0; i < supportingConfigArr.length; i++){
			$(supportingConfigArr[i]).removeAttr('checked');
		}
		$('#supportingConfigResult').html('');
		
		// 重置菜系
		var cuisineConfigArr = $('#cuisineConfigDIV .cuisineConfigItem');
		for(var i = 0; i < cuisineConfigArr.length; i++){
			$(cuisineConfigArr[i]).removeAttr('checked');
		}
		$('#cuisineConfigResult').html('');
		
		// 重置标签
		var tagsConfigArr = $('#tagsConfigDIV .tagsConfigItem');
		for(var i = 0; i < tagsConfigArr.length; i++){
			$(tagsConfigArr[i]).removeAttr('checked');
		}
		$('#tagsConfigResult').html('');
		return true;
	}
	
	function uploadExcel(obj, url, callback){
		var file = new FormData();
		file.append("file", obj[0].files[0]);
	
		if('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' !== obj[0].files[0].type){
			clear(obj);
			MyDialog.alert('请上传excel文件！');
			return;
		}
		
		$.ajax({
			data: file,
			type: 'POST',
			cache: false,
			contentType: false, //不可缺
			processData: false, //不可缺
			url: 'https://napi.mazing.com' + url,
			headers:{
				  token:$('#token').attr('value').trim()
			 },
			success: function(json){
				if(json.success){
					callback(json.object);
				}
			}
		});
	}
	
	function refreshOpensearch(){
		if(!confirm('你确定要刷新一次opensearch吗？')){
			return false;
		}
		requestNode('/store/admin/refreshopensearch', 'post', {}, function(data){
			alertMessage();
		});
		return true;
	}
	
	function refreshCblog(){
		if(!confirm('你确定要刷新一次cblog缓存吗？')){
			return false;
		}
		requestNode('/store/admin/refreshcblogredis', 'post', {}, function(data){
			alertMessage();
		});
		return true;
	}
	
	function importBrand(){
		if(!confirm('请再三确认要导入的文件是基于“品牌模板”修改的，如因上传失误造成生产环境数据错乱则很难恢复！')){
			clear($('#importBrandFile'));
			return false;
		}
		
		uploadExcel($('#importBrandFile'), '/store/admin/importbrand', function(data){
			$('#importBrandModal').modal('hide');
			alertMessage();
		});
		return true;
	}
	
	function importBusinessDistrict(){
		if(!confirm('请再三确认要导入的文件是基于“商圈模板”修改的，如因上传失误造成生产环境数据错乱则很难恢复！')){
			clear($('#importBusinessDistrictFile'));
			return false;
		}
		
		uploadExcel($('#importBusinessDistrictFile'), '/store/admin/importbusinessdistrict', function(data){
			$('#importBusinessDistrictModal').modal('hide');
			alertMessage();
		});
		return true;
	}
	
	function importCblog(){
		if(!confirm('请再三确认要导入的文件是基于“cblog模板”修改的，如因上传失误造成生产环境数据错乱则很难恢复！')){
			clear($('#importCblogFile'));
			return false;
		}
		
		uploadExcel($('#importCblogFile'), '/store/admin/importcblog', function(data){
			$('#importCblogModal').modal('hide');
			alertMessage();
		});
		return true;
	}		
	</script>
</body>
</html>