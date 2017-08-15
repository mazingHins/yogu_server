<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.core.enums.HotSearchWordType" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/include/meta.html"%>
	<title>热门搜索词管理</title>
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
			<h1>
				热门搜索词管理
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="javascript: showAdd();"><i class="fa fa-dashboard"></i> 新增热门搜索词</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">

					<div class="box box-solid">
						<div class="box-body">
							<div class="row">
								<div class="col-xs-12" style="margin:10px;">
									<p class="text-left">说明：</p>
									<p class="text-left">（1）新增加的热搜词默认排序在最前面</p>
									<p class="text-left">（2）现在热门搜索词类型有三种，1：餐厅热搜词，2：美食热搜词，3：指南热搜词</p>
									<p class="text-left">（3）热搜词分中文版和英文版，中文热搜词长度最长4个中文字符，英文热搜词长度最长12个英文字符</p>
									<p class="text-left">（4）每种类型的热搜词可以设置超过12个，建议最好不超过15个，但最终只会返回每种热搜词排序值最大的12个给客户端</p>
								</div>
							</div>
							
							<div class="box-header">
								<div class="col-sm-12 form-inline">
									<select class="form-control" id="cityCode">
										<c:forEach items="${cityList}" var="city">
										<option value="${city.code}">${city.name}</option>
										</c:forEach>
									</select>
									<select class="form-control" id="langCode">
										<c:forEach items="${langList}" var="lang">
										<option value="${lang.code}">${lang.zhName}</option>
										</c:forEach>
									</select>
									<select class="form-control" id="wordType">
										<c:forEach items="${typeList}" var="type">
										<option value="${type.value}">${type.name}</option>
										</c:forEach>
									</select>
									<div class="input-group input-group-sm col-sm-1">
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-flat" onclick="query()">查询</button>
										</span>
									</div>
									<div class="input-group input-group-sm col-sm-6">
										<strong>当前城市：<span id="showCity" class="text-danger"></span>、语言：<span id="showLang" class="text-danger"></span>、类型：<span id="showType" class="text-danger"></span></strong>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12">
									<table id="listTable" class="table table-bordered table-hover">
										<thead>
										<tr>
											<th width="5%">城市</th>
											<th width="5%">语言</th>
											<th width="15%">热搜词类型</th>
											<th width="15%">热搜词</th>
											<th width="15%">创建时间</th>
											<th width="15%">排序</th>
											<th width="25%">操作</th>
										</tr>
										</thead>
										<tbody id="listTableBody" style="font-size: 14px;">

										</tbody>
										<script id="listTableTpl" type="text/html">
											{{each object as value i}}
											<tr>
												<td>{{cityName value.cityCode}}</td>
												<td>{{langName value.langCode}}</td>
												<td>{{showTypeName value.wordType}}</td>
												<td>{{value.name}}</td>
												<td>{{formatDateTime value.createTime}}</td>
												<td>
													{{if i != 0}}
														<a class="btn btn-default btn-sm" href="javascript:mv({{value.wordId}}, '{{value.cityCode}}', '{{value.langCode}}', {{value.wordType}}, 1);">上移</a>
													{{/if}}
													{{if i+1 < object.length}}
														<a class="btn btn-default btn-sm" href="javascript:mv({{value.wordId}}, '{{value.cityCode}}', '{{value.langCode}}', {{value.wordType}}, -1);">下移</a>
													{{/if}}
												</td>
												<td>
													<a href="javascript:void(0)" class="btn btn-default" onclick="deleteHotSearch({{value.wordId}}, '{{value.cityCode}}', '{{value.langCode}}', {{value.wordType}}, '{{value.name}}')">删除</a>
													<a href="javascript:void(0)" class="btn btn-default" onclick="updateHotSearch({{value.wordId}}, '{{value.cityCode}}', '{{value.langCode}}', {{value.wordType}}, '{{value.name}}')">编辑</a>
												</td>
											</tr>
											{{/each}}
										</script>
									</table>
								</div>
							</div>

						</div><!-- /.box-body -->
					</div><!-- /.box -->

				</div><!-- /.col -->
			</div><!-- /.row -->

			<!-- Modal -->
			<div class="modal fade" id="hotSearchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">增加热搜词</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="newCityCode" class="control-label">城市:</label>
								<select class="form-control" id="newCityCode">
									<c:forEach items="${cityList}" var="city">
									<option value="${city.code}">${city.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="newLangCode" class="control-label">语言:</label>
								<select class="form-control" id="newLangCode">
									<c:forEach items="${langList}" var="lang">
									<option value="${lang.code}">${lang.zhName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="newWordType" class="control-label">热搜词类型:</label>
								<select class="form-control" id="newWordType">
									<c:forEach items="${typeList}" var="type">
									<option value="${type.value}">${type.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="hotWord" class="control-label">热搜词内容:</label>
								<input type="text" class="form-control" id="hotWord">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" onclick="save()">保存</button>
						</div>
					</div>
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
	function showAdd() {
		var cityCode = $('#cityCode').val();
		setSelValue('newCityCode', cityCode);
		var langCode = $('#langCode').val();
		setSelValue('newLangCode', langCode);
		var wordType = $('#wordType').val();
		setSelValue('newWordType', wordType);
		
		$('#hotSearchModal').modal('show');
	}
	
	function setSelValue(id, value) {
		var sel = $('#'+id);
		sel.find('option').attr("selected", false);
		sel.find("option[value='" + value + "']").attr("selected", true);
	}
	
	// 添加热搜词
	function save() {
		var cityCode = $.trim($('#newCityCode').val());
		var langCode = $.trim($('#newLangCode').val());
		var wordType = $.trim($('#newWordType').val());
		var name = $.trim($('#hotWord').val());
		if(!(name)) { MyDialog.alert("请填写内容！");return; }
		
		$.post('/admin/system/addHotSearch.do', {
			'cityCode': cityCode,
			'langCode': langCode,
			'wordType': wordType,
			'name': name,
		}, function (json) {
			$('#hotSearchModal').modal('hide');
			if (json.success) {
				MyDialog.alert("添加热搜词成功");
				$('#hotWord').val('');// 添加热搜词成功会清空添加中英文热搜词的表单内容
				query(cityCode, langCode, wordType);// 重新读一次信息
			}else {
				MyDialog.alert(json.message);
			}
		}, 'json');
	}
	
	 // 删除热搜词
	 function deleteHotSearch(wordId, cityCode, langCode, wordType, name) {
		 BootstrapDialog.confirm('确认要删除这个热搜词吗？热搜词：' + name, function(result) {
			if (result) {
				$.getJSON('/admin/system/deleteHotSearch?wordId='+wordId, {}, function (json) {
					MyDialog.alert(json.message);
					if (json.success) {
						query(cityCode, langCode, wordType);// 重新读一次信息
					}
				});
			}
		});
	 }
	 
	 // 编辑热搜词
	 var updateHotSearchYes;
	 function updateHotSearch(wordId, cityCode, langCode, wordType, name) {
		 updateHotSearchYes = false;
		 BootstrapDialog.show({
				title: '编辑热搜词',
				message: 
				'<form role="form" class="form-horizontal">' +
					'<div class="modal-body">' +
						'<div class="form-group">' +
							'<label for="cnHotWord" class="control-label">热搜词:</label>' +
							'<input type="text" class="form-control" id="newHotWord" value="'+name+'">' +
						'</div>' +
						//'<div class="form-group">' +
						//	'<label for="enHotWord" class="control-label">英文热搜词:</label>' +
						//	'<input type="text" class="form-control" id="newEnHotWord" value="'+enName+'">' +
						//'</div>' +
					'</div>' +
				'</form>',
				buttons: [{
					label: '确定',
					action: function(dialog) { updateHotSearchYes=true; dialog.close(); }
				}, {
					label: '取消',
					action: function(dialog) { updateHotSearchYes=false; dialog.close(); }
				}
				],
				onshown: function(dialogRef){},		
				onhide: function(dialogRef){
					if(updateHotSearchYes) {
						var newName = $("#newHotWord").val();
						if(!(newName)) { MyDialog.alert("请填写内容！");return; }
						
						$.post('/admin/system/updateHotSearch.do', {
							'wordId': wordId,
							'newName': newName,
						}, function (json) {
							MyDialog.alert(json.message);
							$('#hotSearchModal').modal('hide');
							if (json.success) {
								query(cityCode, langCode, wordType);// 重新读一次信息
							}
						}, 'json');
					}
				}
			});
	 }
	 
	//热搜词上移下移操作， type；1：上移、-1：下移
	function mv(wordId, cityCode, langCode, wordType, type) {
		$.getJSON('/admin/system/hotSearch/mv?wordId='+wordId+'&type='+type, {}, function(json) {
			if (json.success) {
				query(cityCode, langCode, wordType);
			} else {
				MyDialog.alert(json.message);
			}
		});
	}

	// 查询热搜词
	function query(city, lang, type){
		/*
		var cityCode = $('#cityCode').val();
		if(city) {
			setSelValue('cityCode', city);
			cityCode = city;
		}
		var langCode = $('#langCode').val();
		if(lang) {
			setSelValue('langCode', lang);
			langCode = lang;
		}
		var wordType = $('#wordType').val();
		if(type) {
			setSelValue('wordType', type);
			wordType = type;
		}
		*/
		var cityCode = city || $('#cityCode').val();
		var langCode = lang || $('#langCode').val();
		var wordType = type || $('#wordType').val();
		
		$.getJSON("/admin/system/listByCityAndType", {
			'cityCode': cityCode,
			'langCode': langCode,
			'wordType': wordType
		}, function(json) {
			if (json.success) {
				var htmlTxt = template('listTableTpl', json);
				$('#listTableBody').html(htmlTxt);
				showCityLangType(cityCode, langCode, wordType);
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}
	function showCityLangType(cityCode, langCode, wordType) {
		$('#showCity').html(cityName(cityCode));
		$('#showLang').html(langName(langCode));
		$('#showType').html(showTypeName(wordType));
	}
	
	// type List >> [{name: '', value: ''}, {..} ..]
	var typeArr = [
		<%boolean one = true;for(HotSearchWordType type : HotSearchWordType.values()) {%>
		<%if(!one) {%><%=", "%><%}%>{name: '<%=type.getName()%>', value: '<%=type.getValue()%>'}
		<%one = false;}%>
	];
	function showTypeName(value) {
		var name = value;
		$.each(typeArr, function(i, d) { if(value == d.value) name = d.name; });
		return name;
	}
	
	$(function() {
		// 块类型转换成中文显示
		template.helper('showTypeName', showTypeName);
		
		//$('#cityCode').on('change', function(){ query(); });
		//$('#langCode').on('change', function(){ query(); });
		//$('#wordType').on('change', function(){ query(); });
		
		query();
	});
	
</script>
</body>
</html>