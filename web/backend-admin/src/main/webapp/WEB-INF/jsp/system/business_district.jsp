<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>商圈配置管理</title>
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
    <h1> 商圈配置管理 <small></small> </h1>
    <ol class="breadcrumb">
      <li><a style="cursor:pointer" data-toggle="modal" data-target="#addProvinceModal"><i class="fa fa-dashboard"></i>修改省份</a></li>
      <li><a style="cursor:pointer" data-toggle="modal" data-target="#addCityModal" onClick="loadProvinceConfig('AddCityModal')"><i class="fa fa-dashboard"></i>修改城市</a></li>
      <li><a style="cursor:pointer" data-toggle="modal" data-target="#addDistrictModal" onClick="loadProvinceConfig('AddDistrictModal')"><i class="fa fa-dashboard"></i>修改行政区</a></li>
      <li><a style="cursor:pointer" data-toggle="modal" data-target="#addBusinessDistrictModal" onClick="loadProvinceConfig()"><i class="fa fa-dashboard"></i>增加商圈</a></li>
    </ol>
  </section>
  
  <!-- Main content -->
  <section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-solid">
        <div class="box-body">
          <div class="col-sm-3 form-inline">
            <label for="province" class="control-label">省份：</label>
            <select class="form-control" id="province" onChange="baseProvinceId = this.value; loadCityConfig()">
            </select>
          </div>
          <div class="col-sm-3 form-inline">
            <label for="city" class="control-label">城市：</label>
            <select class="form-control" id="city" onChange="baseCityId = this.value; loadDistrictConfig()">
            </select>
          </div>
          <div class="col-sm-3 form-inline">
            <label for="district" class="control-label">区：</label>
            <select class="form-control" id="district" onChange="baseDistrictCode = this.value; getBusinessDistrict();">
            </select>
          </div>
          <br>
          <br>
          <table class="table table-hover" id="businessDistrict">
          </table>
        </div>
        <!-- /.box-body --> 
      </div>
      <!-- /.box --> 
      
    </div>
    <!-- /.col --> 
  </div>
  <!-- /.row -->
  
  <div class="modal fade" id="addProvinceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">修改省份(点击该省份即可修改)：</h4>
        </div>
        <div class="modal-body">
          <div class="form-group col-sm-12">
            <label for="nameCN" class="control-label">已有省份</label>
            <br>
            <ul style="margin-left:-40px;width:100%">
            </ul>
            <br>
          </div>
          <input type="hidden" class="form-control" id="provinceCode">
          <div class="form-group col-sm-12">
            <label for="nameCN" class="control-label">省份中文名称：</label>
            <input type="text" class="form-control" id="provinceNameCN" placeholder="请输入省份的中文名称">
          </div>
          <div class="form-group col-sm-12">
            <label for="nameEN" class="control-label">省份英文名称：</label>
            <input type="text" class="form-control" id="provinceNameEN" placeholder="请输入省份的英文名称">
          </div>
        </div>
        <div class="modal-footer" id="updateProvince"></div>
      </div>
    </div>
    </section>
  </div>
  <div class="modal fade" id="addCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">修改城市(点击该城市即可修改)：</h4>
        </div>
        <input type="hidden" class="form-control" id="citypCode">
        <input type="hidden" class="form-control" id="cityCode">
        <div class="modal-body">
          <div class="form-group col-sm-12">
            <label for="provinceModal" class="control-label">选择省份：</label>
            <select class="form-control" id="provinceAddCityModal" onChange="baseProvinceId = this.value; loadCityConfig('AddCityModal')">
            </select>
          </div>
          <div class="form-group col-sm-12">
            <label for="nameCN" class="control-label">已有城市</label>
            <br>
            <ul style="margin-left:-40px;width:100%">
            </ul>
            <br>
          </div>
          <div class="form-group col-sm-12">
            <label for="nameCN" class="control-label">中文名称：</label>
            <input type="text" class="form-control" id="cityNameCN" placeholder="请输入城市的中文名称">
          </div>
          <div class="form-group col-sm-12">
            <label for="nameEN" class="control-label">英文名称：</label>
            <input type="text" class="form-control" id="cityNameEN" placeholder="请输入城市的英文名称">
          </div>
        </div>
        <div class="modal-footer" id="updateCity"></div>
      </div>
    </div>
    </section>
  </div>
  <div class="modal fade" id="addDistrictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">修改行政区(点击该城市即可修改)：</h4>
        </div>
        <input type="hidden" class="form-control" id="districtpCode">
        <input type="hidden" class="form-control" id="districtCode">
        <div class="modal-body">
          <div class="form-group col-xs-6">
            <label for="provinceModal" class="control-label">所属省份：</label>
            <select class="form-control" id="provinceAddDistrictModal" onChange="baseProvinceId = this.value; loadCityConfig()">
            </select>
          </div>
          <div class="form-group col-xs-6">
            <label for="cityModal" class="control-label">所属城市：</label>
            <select class="form-control" id="cityAddDistrictModal" onChange="baseCityId = this.value; loadDistrictConfig()">
            </select>
          </div>
          <div class="form-group">
            <label for="nameCN" class="control-label">已有区</label>
            <br>
            <ul style="margin-left:-40px;width:100%">
            </ul>
            <br>
          </div>
          <div class="form-group">
            <label for="nameCN" class="control-label">中文名称：</label>
            <input type="text" class="form-control" id="districtNameCN" placeholder="请输入区的中文名称">
          </div>
          <div class="form-group">
            <label for="nameEN" class="control-label">英文名称：</label>
            <input type="text" class="form-control" id="districtNameEN" placeholder="请输入区的英文名称">
          </div>
        </div>
        <div class="modal-footer" id="updateDistrict"></div>
      </div>
    </div>
    </section>
  </div>
  
  <!-- Modal -->
  <div class="modal fade" id="addBusinessDistrictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onClick="clear();">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">增加商圈</h4>
        </div>
        <div class="modal-body">
          <div class="form-group col-xs-4">
            <label for="provinceModal" class="control-label">所属省份：</label>
            <select class="form-control" id="provinceAddBusinessDistrictModal" onChange="baseProvinceId = this.value; loadProvinceConfig(); loadCityConfig();">
            </select>
          </div>
          <div class="form-group col-xs-4">
            <label for="cityModal" class="control-label">所属城市：</label>
            <select class="form-control" id="cityAddBusinessDistrictModal" onChange="baseCityId = this.value; loadCityConfig(); loadDistrictConfig();">
            </select>
          </div>
          <div class="form-group col-xs-4">
            <label for="districtModal" class="control-label">所属区：</label>
            <select class="form-control" id="districtAddBusinessDistrictModal" onChange="baseDistrictCode = this.value; loadDistrictConfig(); getBusinessDistrict();">
            </select>
          </div>
          <div class="form-group">
            <label for="nameCN" class="control-label">中文名称：</label>
            <input type="text" class="form-control" id="businessDistrictNameCN" placeholder="请输入商圈的中文名称">
          </div>
          <div class="form-group">
            <label for="nameEN" class="control-label">英文名称：</label>
            <input type="text" class="form-control" id="businessDistrictNameEN" placeholder="请输入商圈的英文名称">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" onClick="return addBusinessDistrict();">提交</button>
        </div>
      </div>
    </div>
    </section>
  </div>
  <!-- /.content-wrapper --> 
  
  <!-- footer -->
  <jsp:include page="/include/footer.jsp"/>
  
  <!-- control sidebar -->
  <jsp:include page="/include/control-sidebar.jsp"/>
</div>
<!-- ./wrapper --> 

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	var baseProvinceId = '110000';// 默认北京
	var baseCityId = '010';// 
	var baseDistrictCode = '110101';// 默认东成区
	
	loadProvinceConfig();
	
	$('#addProvinceModal').on('hidden.bs.modal', function () {
		baseProvinceId = '110000';
		$('#provinceNameCN').val('');
		$('#provinceCode').val('');
		$('#provinceNameEN').val('');
	  	$('#updateProvince').html('');
		
		$('#addProvinceModal ul li').each(function(i,e){
			var code = $(e).attr('id');
			$('#'+code).attr('style','list-style:none;float:left;margin-right:10px');
			$('#'+code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
		});
	});
	
	$('#addCityModal').on('hidden.bs.modal', function () {
		clearCityModal();
	});
	
	$('#addDistrictModal').on('hidden.bs.modal', function () {
		clearDistrictModalModal();
	});
	
	function clearCityModal(){
		baseProvinceId = '110000';
		baseCityId = '010';
		$('#cityCode').val('')
		$('#citypCode').val('')
		$('#cityNameCN').val('');
		$('#cityNameEN').val('');
		$('#updateCity').html('');
		
		$('#addCityModal ul li').each(function(i,e){
			var code = $(e).attr('id');
			$('#'+code).attr('style','list-style:none;float:left;margin-right:10px');
			$('#'+code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
		});
	}
	
	function clearDistrictModalModal(){
		baseProvinceId = '110000';
		baseCityId = '010';
		baseDistrictCode = '110101';
		$('#districtCode').val('')
		$('#districtpCode').val('')
		$('#districtNameCN').val('');
		$('#districtNameEN').val('');
		$('#updateDistrict').html('');
		
		$('#addDistrictModal ul li').each(function(i,e){
			var code = $(e).attr('id');
			$('#'+code).attr('style','list-style:none;float:left;margin-right:10px');
			$('#'+code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
		});
	}
	
	function loadProvinceConfig(){
		requestNode('/store/admin/areaconfig', 'get', {level:2,pcode:86}, function(data){
			var html = '', addProvinceModalHtml = '';
			for(var province of data){
				if(baseProvinceId == province.code){
					html += '<option value="'+province.code+'" selected="selected">'+province.nameCn+'('+province.code+')</option>';
				} else {
					html += '<option value="'+province.code+'">'+province.nameCn+'('+province.code+')</option>';
				}
				addProvinceModalHtml += '<li id="'+province.code+'" style="list-style:none;float:left;margin-right:10px" onClick="fillProvince('+province.code+');"><a style="cursor:pointer;" title="' + province.nameCn + '">' + province.nameCn + '</a></li>';
			}
			$('#province').html(html);
			$('#provinceAddCityModal').html(html);
			$('#provinceAddDistrictModal').html(html);
			$('#provinceAddBusinessDistrictModal').html(html);
			$('#addProvinceModal ul').html(addProvinceModalHtml);
			loadCityConfig();
		});
	}
	
	function fillProvince(code){
		var obj = $('#'+code);
		obj.attr('style','list-style:none;float:left;margin-right:10px;background:#3c8dbc;');
		$('#'+code+' a').attr('style','cursor:pointer;color:#fff;');
		requestNode('/store/admin/areaconfig', 'get', {level:2,pcode:86}, function(data){
			for(var c of data){
				if(parseInt(c.code) !== parseInt(obj.attr('id'))) {
					 $('#'+c.code).attr('style','list-style:none;float:left;margin-right:10px');
					 $('#'+c.code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
					continue;
				}
				$('#provinceCode').val(c.code)
				$('#provinceNameCN').val(c.nameCn);
				$('#provinceNameEN').val(c.nameEn);
				$('#updateProvince').html('<button type="button" class="btn btn-success" onClick="return updateProvince();">提交修改</button>');
			}
		});
	}
	
	function updateProvince(){
		var provinceNameCN = $('#provinceNameCN').val().trim();
		if(!provinceNameCN){
			MyDialog.alert('省份中文名称不能为空！');  
			return false;
		}
		
		if(provinceNameCN.length > 64){
			MyDialog.alert('省份的中文名称长度不可以超过64个字符！');  
			return false;
		}
		
		var provinceNameEN = $('#provinceNameEN').val().trim();
		if(!provinceNameEN){
			MyDialog.alert('省份英文名称不能为空！');  
			return false;
		}
		
		if(provinceNameEN.length > 64){
			MyDialog.alert('省份的英文名称长度不可以超过64个字符！');  
			return false;
		}
		
		requestNode('/store/admin/updateprovinceconfig', 'post', {code:$('#provinceCode').val(),nameCn:provinceNameCN,nameEn:provinceNameEN}, function(data){
			MyDialog.alert('省份修改成功！'); 
			$('#addProvinceModal').modal('hide');
		});
		return true;
	}
	
	function loadCityConfig(){
		requestNode('/store/admin/areaconfig', 'get', {level:3,pcode:baseProvinceId}, function(data){
			var html = '', addCityModalHtml = '';
			for(var city of data){
				if(baseCityId == city.code){
					html += '<option value="'+city.code+'" selected="selected">'+city.nameCn+'('+city.code+')</option>';
				} else {
					html += '<option value="'+city.code+'">'+city.nameCn+'('+city.code+')</option>';
				}
				addCityModalHtml += '<li id="'+city.code+'" p="'+city.pcode+'" style="list-style:none;float:left;margin-right:10px"><a style="cursor:pointer;" onclick="fillCity(\''+city.code+'\');" title="' + city.nameCn + '">' + city.nameCn + '</a></li>';
			}
			if(!html) {
				html += '<option value="0">===暂无城市===</option>';
			}
			
			clearCityModal();
			$('#city').html(html);
			$('#cityAddDistrictModal').html(html);
			$('#cityAddBusinessDistrictModal').html(html);
			$('#addCityModal ul').html(addCityModalHtml);
			baseCityId = $('#city').val();
			loadDistrictConfig();
		});
	}
	
	function fillCity(code){
		var obj = $('#'+code);
		obj.attr('style','list-style:none;float:left;margin-right:10px;background:#3c8dbc;');
		$('#'+code+' a').attr('style','cursor:pointer;color:#fff;');
		requestNode('/store/admin/areaconfig', 'get', {level:3,pcode:obj.attr('p')}, function(data){
			for(var c of data){
				if(parseInt(c.code) !== parseInt(obj.attr('id'))) {
					 $('#'+c.code).attr('style','list-style:none;float:left;margin-right:10px');
					 $('#'+c.code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
					continue;
				}
				$('#citypCode').val(c.pcode);
				$('#cityCode').val(c.code);
				$('#cityNameCN').val(c.nameCn);
				$('#cityNameEN').val(c.nameEn);
				$('#updateCity').html('<button type="button" class="btn btn-success" onClick="return updateCity();">提交修改</button>');
			}
		});
	}
	
	function updateCity(){
		var cityNameCN = $('#cityNameCN').val().trim();
		if(!cityNameCN){
			MyDialog.alert('城市中文名称不能为空！');  
			return false;
		}
		
		if(cityNameCN.length > 64){
			MyDialog.alert('城市的中文名称长度不可以超过64个字符！');  
			return false;
		}
		
		var cityNameEN = $('#cityNameEN').val().trim();
		if(!cityNameEN){
			MyDialog.alert('城市英文名称不能为空！');  
			return false;
		}
		
		if(cityNameEN.length > 64){
			MyDialog.alert('城市的英文名称长度不可以超过64个字符！');  
			return false;
		}
		
		requestNode('/store/admin/updatecityconfig', 'post', {pcode:$('#citypCode').val(),code:$('#cityCode').val(),nameCn:cityNameCN,nameEn:cityNameEN}, function(data){
			MyDialog.alert('城市修改成功！'); 
			$('#addCityModal').modal('hide');
		});
		return true;
	}
	
	function loadDistrictConfig(){
		requestNode('/store/admin/areaconfig', 'get', {level:4,pcode:baseCityId}, function(data){
			var html = '', addDistrictModalHtml = '';
			for(var district of data){
				if(baseDistrictCode == district.code){
					html += '<option value="'+district.code+'" selected="selected">'+district.nameCn+'('+district.code+')</option>';
				} else {
					html += '<option value="'+district.code+'">'+district.nameCn+'('+district.code+')</option>';
				}
				addDistrictModalHtml += '<li id="'+district.code+'" p="'+district.pcode+'" style="list-style:none;float:left;margin-right:10px" onclick="fillDistrict(\''+district.code+'\');"><a style="cursor:pointer;" title="' + district.nameCn + '">' + district.nameCn + '</a></li>';
			}
			clear();
			if(!html) {
				html += '<option value="0">===暂无区===</option>';
			}
			clearDistrictModalModal();
			$('#district').html(html);
			$('#districtAddBusinessDistrictModal').html(html);
			$('#addDistrictModal ul').html(addDistrictModalHtml);
			baseDistrictCode = $('#district').val();
			getBusinessDistrict();
		});
	}
	
	function fillDistrict(code){
		var obj = $('#'+code);
		obj.attr('style','list-style:none;float:left;margin-right:10px;background:#3c8dbc;');
		$('#'+code+' a').attr('style','cursor:pointer;color:#fff;');
		requestNode('/store/admin/areaconfig', 'get', {level:4,pcode:obj.attr('p')}, function(data){
			for(var c of data){
				if(parseInt(c.code) !== parseInt(obj.attr('id'))) {
					 $('#'+c.code).attr('style','list-style:none;float:left;margin-right:10px');
					 $('#'+c.code+' a').attr('style','cursor:pointer;color:#3c8dbc;');
					continue;
				}
				$('#districtpCode').val(c.pcode);
				$('#districtCode').val(c.code);
				$('#districtNameCN').val(c.nameCn);
				$('#districtNameEN').val(c.nameEn);
				$('#updateDistrict').html('<button type="button" class="btn btn-success" onClick="return updateDistrict();">提交修改</button>');
			}
		});
	}
	
	function updateDistrict(){
		var districtNameCN = $('#districtNameCN').val().trim();
		if(!districtNameCN){
			MyDialog.alert('行政区中文名称不能为空！');  
			return false;
		}
		
		if(districtNameCN.length > 64){
			MyDialog.alert('行政区的中文名称长度不可以超过64个字符！');  
			return false;
		}
		
		var districtNameEN = $('#districtNameEN').val().trim();
		if(!districtNameEN){
			MyDialog.alert('行政区英文名称不能为空！');  
			return false;
		}
		
		if(districtNameEN.length > 64){
			MyDialog.alert('行政区的英文名称长度不可以超过64个字符！');  
			return false;
		}
		
		requestNode('/store/admin/updatedistrictconfig', 'post', {pcode:$('#districtpCode').val(),code:$('#districtCode').val(),nameCn:districtNameCN,nameEn:districtNameEN}, function(data){
			MyDialog.alert('行政区修改成功！'); 
			$('#addDistrictModal').modal('hide');
		});
		return true;
	}
	
	function getBusinessDistrict(){
		requestNode('/store/admin/getbusinessdistrictconfig', 'get', {districtCode:baseDistrictCode}, function(data){
			if(data.length <= 0){
				$('#businessDistrict').html('<tr><td>此地区暂无商圈</td></tr>');
				return;
			}
			var html = '<tr><th width="200">商圈code</th><th width="200">商圈中文名</th><th width="200">商圈英文名</th><th>操作</th></tr>';
			for(var i = 0; i < data.length; i++){
				var _data = data[i];
				var prev = data[i-1];
				var next = data[i+1];
				html += '<tr><td id="b'+_data.code+'" districtCode="'+_data.pcode+'">'+_data.code+'</td><td id="nameCn'+_data.code+'">'+_data.nameCn+'</td><td id="nameEn'+_data.code+'">'+_data.nameEn+'</td><td><a style="cursor:pointer" onClick="return updateBusinessDistrict(\'up\','+_data.code+','+(prev?prev.code:null)+')">↑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onClick="return updateBusinessDistrict(\'down\','+_data.code+','+(next?next.code:null)+')">↓</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id="edit'+_data.code+'" style="cursor:pointer" onClick="return updateBusinessDistrict(\'edit\','+_data.code+')">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer" onClick="return updateBusinessDistrict(\'delete\','+_data.code+')">删除</a></td></tr>';
			}
			$('#businessDistrict').html(html);
		});
	}
	
	function addBusinessDistrict(){
		var districtCode = $('#districtAddBusinessDistrictModal').val().trim();
		if(!districtCode){
			MyDialog.alert('请选择区！');  
			return false;
		}
		var businessDistrictNameCN = $('#businessDistrictNameCN').val().trim();
		if(!businessDistrictNameCN){
			MyDialog.alert('请输入商圈的中文名称！');  
			return false;
		}
		if(businessDistrictNameCN.length > 64){
			MyDialog.alert('商圈的中文名称长度不可以超过64个字符！');  
			return false;
		}
		var businessDistrictNameEN = $('#businessDistrictNameEN').val().trim();
		if(!businessDistrictNameEN){
			MyDialog.alert('请输入商圈的英文名称！');  
			return false;
		}
		if(businessDistrictNameEN.length > 64){
			MyDialog.alert('商圈的英文名称长度不可以超过64个字符！');  
			return false;
		}
		requestNode('/store/admin/addbusinessdistrictconfig', 'post', {districtCode:districtCode,nameCn:businessDistrictNameCN,nameEn:businessDistrictNameEN}, function(data){
			clear();
			$('#addBusinessDistrictModal').modal('hide');
			getBusinessDistrict();
		});
		return true;
	}
	
	function addCity(){
		var provinceCode = $('#provinceAddCityModal').val();
		if(!provinceCode){
			MyDialog.alert('请选择省份！');  
			return false;
		}
		var cityCode = $('#cityCode').val();
		if(!cityCode){
			MyDialog.alert('请输入城市代号！');  
			return false;
		}
		if(!confirm('请确认清楚城市是否正确，否则造成数据有误后果自负！')){
			return false;
		}
		var cityNameCN = $('#cityNameCN').val();
		if(!cityNameCN){
			MyDialog.alert('请输入城市的中文名称！');  
			return false;
		}
		var cityNameEN = $('#cityNameEN').val();
		if(!cityNameEN){
			MyDialog.alert('请输入城市的英文名称！');  
			return false;
		}
		requestNode('/store/admin/addcityconfig', 'post', {code:cityCode,pcode:provinceCode,nameCn:cityNameCN,nameEn:cityNameEN}, function(data){
			loadProvinceConfig();
			$('#cityCode').val('');
			$('#cityNameCN').val('');
			$('#cityNameEN').val('');
			$('#addCityModal').modal('hide');
		});
		return true;
	}
	
	function addProvince(){
		var provinceCode = $('#provinceCode').val();
		if(!provinceCode){
			MyDialog.alert('请输入省份代号！');  
			return false;
		}
		if(!confirm('请确认清楚省份代号是否正确，否则造成数据有误后果自负！')){
			return false;
		}
		var provinceNameCN = $('#provinceNameCN').val();
		if(!provinceNameCN){
			MyDialog.alert('请输入省份的中文名称！');  
			return false;
		}
		var provinceNameEN = $('#provinceNameEN').val();
		if(!provinceNameEN){
			MyDialog.alert('请输入省份的英文名称！');  
			return false;
		}
		requestNode('/store/admin/addprovinceconfig', 'post', {pcode:86,code:provinceCode,nameCn:provinceNameCN,nameEn:provinceNameEN}, function(data){
			loadProvinceConfig();
			$('#provinceCode').val('');
			$('#provinceNameCN').val('');
			$('#provinceNameEN').val('');
			$('#addProvinceModal').modal('hide');
		});
		return true;
	}
			
	function addDistrict(){
		var cityCode = $('#cityAddDistrictModal').val();
		if(!cityCode){
			MyDialog.alert('请选择城市！');  
			return false;
		}
		var districtCode = $('#districtCode').val();
		if(!districtCode){
			MyDialog.alert('请输入区代号！');  
			return false;
		}
		if(!confirm('请确认清楚区代号是否正确，否则造成数据有误后果自负！')){
			return false;
		}
		var districtNameCN = $('#districtNameCN').val().trim();
		if(!districtNameCN){
			MyDialog.alert('请输入区的中文名称！');  
			return false;
		}
		var districtNameEN = $('#districtNameEN').val().trim();
		if(!districtNameEN){
			MyDialog.alert('请输入区的英文名称！');  
			return false;
		}
		requestNode('/store/admin/adddistrictconfig', 'post', {code:districtCode,pcode:cityCode,nameCn:districtNameCN,nameEn:districtNameEN}, function(data){
			loadProvinceConfig();
			$('#districtCode').val('');
			$('#districtNameCN').val('');
			$('#districtNameEN').val('');
			$('#addDistrictModal').modal('hide');
		});
		return true;
	}
	
	function clear(){
		$('#businessDistrictNameCN').val('');
		$('#businessDistrictNameEN').val('');
	}
		
	function updateBusinessDistrict(action, code, param){
		switch(action){
			case 'up':
				if(!param){
					MyDialog.alert('我已经在第一啦，不可以再上升！');  
					return false;
				}
				
				requestNode('/store/admin/switchbusinessdistrictconfig', 'post', {code1:code,code2:param}, function(data){
					getBusinessDistrict();
		});
				return true;
			case 'down':
				if(!param){
					MyDialog.alert('我已经垫底啦，不可以再下降啦！');  
					return false;
				}
				requestNode('/store/admin/switchbusinessdistrictconfig', 'post', {code1:code,code2:param}, function(data){
					getBusinessDistrict();
		});
				return true;
			case 'edit':
				$('#nameCn'+code).html('<input id="inputCn'+code+'" type="text" class="form-control col-sm-2" value="'+$('#nameCn'+code).html()+'"/>');
				$('#nameEn'+code).html('<input id="inputEn'+code+'" type="text" class="form-control col-sm-2" value="'+$('#nameEn'+code).html()+'"/>');
				$('#edit'+code).html('确定').attr('onClick', 'return update('+code+')');
				return true;
			case 'delete':
				var b = confirm('删除了商圈，关联了这个商圈的店铺全部都会变成无商圈，会导致发现功能没有数据！！！你确定要删除吗？');
				if(!b){
					return false;
				}
				requestNode('/store/admin/deletebusinessdistrict', 'post', {code:code}, function(data){
					getBusinessDistrict();
				});
				return true;
		}
	}
	
	function update(code){
		var nameCN = $('#inputCn'+code).val().trim();
		if(!nameCN){
			MyDialog.alert('请输入商圈的中文名称！');  
			return false;
		}
		if(nameCN.length > 64){
			MyDialog.alert('商圈的中文名称长度不可以超过64个字符！');  
			return false;
		}
		
		var nameEN = $('#inputEn'+code).val().trim();
		if(!nameEN){
			MyDialog.alert('请输入商圈的英文名称！');  
			return false;
		}
		if(nameEN.length > 64){
			MyDialog.alert('商圈的英文名称长度不可以超过64个字符！');  
			return false;
		}
		
		var districtCode = $('#b'+code).attr('districtCode');
		requestNode('/store/admin/updatebusinessdistrict', 'post', {districtCode:districtCode,code:code,nameCn:nameCN,nameEn:nameEN}, function(data){
				getBusinessDistrict();
		});
		return true;
}
</script>
</body>
</html>
