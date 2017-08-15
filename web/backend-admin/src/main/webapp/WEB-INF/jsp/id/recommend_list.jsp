<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>ID管理--ID推荐列表</title>
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
      <h1> ID推荐列表 <small></small> </h1>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <div class="col-sm-6 form-inline"> 当前城市：
                <select class="form-control" id="city" onChange="listIdRecommend();">
                </select>
                &nbsp;&nbsp;当前语言：
                <select class="form-control" id="lang" onChange="listIdRecommend();">
                </select>
              </div>
              <div class="col-sm-6 form-inline blockquote-reverse"> <a href="javascript:void(0)" onClick="return addIdRecommend();"><i class="fa fa-dashboard"></i>新增一个推荐</a> </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <table id="listTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th width="100">ID</th>
                        <th width="300">标题</th>
                        <th width="100">作者头像</th>
                        <th width="170">添加时间</th>
                        <th width="170">发布时间</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <script id="listTableTpl" type="text/html">
												{{each object as value i}}
												<tr{{if value.status == 0}} class="danger"{{/if}}>
													<td>{{value.rid}}</td>
													<td>{{value.title}}</td>
													<td><img src="{{'https://img.mazing.com/'+value.avatar}}" width="50" height="50"/></td>
													<td>{{value.createTime}}</td>
													<td>{{value.publishTime}}</td>
													<td><a href="javascript:void(0)" onClick="editIdRecommend({{value.rid}})">编辑</a>&nbsp;&nbsp;
													{{if value.status == 1}}
														<a style="cursor:pointer;" onClick="return deleteIdRecommend({{value.rid}});">删除</a>
													{{/if}}
													{{if value.status == 0}}
														<a style="cursor:pointer;" onClick="return recoveryIdRecommend({{value.rid}});">恢复</a>
													{{/if}}
												</tr>
												{{/each}}
										</script>
                  </table>
                </div>
              </div>
              <div class="row col-sm-12" id="listPaginator2"> </div>
            </div>
            <!-- /.box-body --> 
          </div>
          <!-- /.box --> 
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
<script type="text/javascript">
	
		var langCodeValue = $.getUrlParam('langCode');
		var cityCodeValue = $.getUrlParam('cityCode');
		
		$(function() {		
			getAreaConfig();
		});
	
		function editIdRecommend(rid){
			window.location.href = '/admin/id/recommend/edit.xhtm?rid='+rid+'&cityCode=' + $('#city').val() + '&langCode=' + $('#lang').val();
		}
	
		function deleteIdRecommend(rid){
			if(!confirm('你确定要删除吗？')){
				return false;
			}
			requestNode('/user/admin/deleteidrecommend', 'post', {rid:rid}, function(data){
				window.location.reload();
			});
			return true;
		}
	
		function recoveryIdRecommend(rid){
			if(!confirm('你确定要恢复吗？')){
				return false;
			}
			requestNode('/user/admin/recoveryidrecommend', 'post', {rid:rid}, function(data){
				window.location.reload();
			});
			return true;
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
				$('#city').append(html);
				getLangConfig();
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
				$('#lang').append(html);
				listIdRecommend();
			});
		}
		
		function listIdRecommend(){
			$('#listTable').artPaginate({
				// 获取数据的地址
				url : "https://napi.mazing.com/user/admin/listidrecommend",
				// 显示页码的位置
				paginator: 'listPaginator2',
				// 模版ID
				tpl : 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params : {cityCode:$('#city').val(), langCode:$('#lang').val()}
			});
		}
	
	function addIdRecommend() {
		window.location.href = '/admin/id/recommend/edit.xhtm?cityCode=' + $('#city').val() + '&langCode=' + $('#lang').val();
	}
	</script>
</body>
</html>