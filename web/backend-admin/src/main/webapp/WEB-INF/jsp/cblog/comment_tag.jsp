<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>cblog管理--评价标签管理</title>
</head>
<body class="skin-blue sidebar-mini">
<div class="wrapper"> 
  
  <!-- header -->
  <jsp:include page="/include/header.jsp" />
  
  <!-- sidebar -->
  <jsp:include page="/include/sidebar.jsp" />
  <div class="modal fade" id="tagsModal" tabindex="-1" role="dialog" aria-labelledby="tagsModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
          <h4 class="modal-title" id="tagsModalLabel">选择标签</h4>
        </div>
        <!-- tag的模版 --> 
        <script id="storeTagTemplate" type="text/html">
		{{each object as value i}}
				<div class="row margin" style="border-bottom: 1px solid #ddd;">
					<div class="col-xs-3 {{if value.appShow != 1}}text-red{{/if}}">{{value.categoryName}}</div>
					<div class="col-xs-9 checkbox {{if value.appShow != 1}}text-red{{/if}}">
						{{each value.tags as tag j}}
							<label><input type="checkbox" id="tag{{tag.tagId}}" name="storeTag" categoryId="{{value.categoryId}}" value="{{tag.tagId}}"/>{{tag.tagName}}</label>
						{{/each}}
					</div>
				</div>
		{{/each}}
	</script>
        <div class="modal-body">
          <div class="row">
            <div class="col-sm-12" id="tagContainer"></div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="confirmTags()">确认</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper"> 
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1> 评价标签管理 </h1>
      <ol class="breadcrumb">
        <li><a style="cursor:pointer" data-toggle="modal"
						data-target="#tagsModal"><i
							class="fa fa-dashboard"></i>增减标签</a></li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box box-solid">
            <div class="box-body">
              <table class="table table-hover" id="tagList">
                <tr>
                  <th width="300">标签类别</th>
                  <th>标签名</th>
                </tr>
              </table>
            </div>
            <!-- /.col --> 
          </div>
        </div>
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
	var tags = {};
	var selectTags = {};
	getAllTags();
	
	function getCommentTagsConfig(){
		requestNode('/store/admin/listcommenttag', 'get', {}, function(data){
			// 列出列表，标记已选
			var html = '';
			for(var categoryId in data){
				var tagLists = data[categoryId];
				html += '<tr><td>'+tags[categoryId].name+'</td>';
				html += '<td><table class="table">';
				for(var i = 0; i < tagLists.length; i++){	
					var tag = tagLists[i];
					html += '<tr><td width="200">'+tags[categoryId].map[tag.tagId]+'('+tag.tagId+')</td>';				 
					html += '<td width="30"><a style="cursor:pointer" onClick="switchCommentTag('+tag.tagId+','+(i===0?null:tagLists[i-1].tagId)+')">↑</a></td>';				 
					html += '<td width="30"><a style="cursor:pointer" onClick="switchCommentTag('+tag.tagId+','+(i===tagLists.length-1?null:tagLists[i+1].tagId)+')">↓</a></td>';			 
					html += '<td><a style="cursor:pointer" onClick="return deleteCommentTag('+tag.tagId+');">删除</a></td></tr>';	
					$('#tag'+tag.tagId).attr('checked', 'checked');
					selectTags[tag.tagId] = categoryId;
				}
				html += '</table></td></tr>';
			}
			$('#tagList').html('<tr><th width="300">标签类别</th><th>标签名</th></tr>');
			if(!html){
				html += '<tr><td colspan="2">尚未配置标签！</td></tr>';
			}
			$('#tagList').append(html);
		});
	}
	
	function getAllTags(){	
		requestNode('/store/admin/commenttagconfig', 'get', {}, function(config){
			config = Object.values(config);
			
			tags = {};
			selectTags = {};
			$.ajax({
				url: '/admin/store/loadStoreTag?storeId=1',
				method: 'get',
				error:function(){
					MyDialog.alert('请求java服务器出错！');
				},
				success:function(json){
					if (!json.success) {
						MyDialog.alert(json.message);
						return;
					}

					var newObject = [];				
					for(var category of json.object){
						var data = {};
						data.name = category.categoryName;
						data.map = {};
						for(var tag of category.tags){
							data.map[tag.tagId] = tag.tagName;
						}
						tags[category.categoryId] = data;
						
						if(config.indexOf(category.categoryId) > -1){
							newObject.push(category);
						}
					}
					
					json.object = newObject;
					var htmlTxt = template('storeTagTemplate', json);
					$('#tagContainer').html(htmlTxt);
					
					getCommentTagsConfig();
				}
			});
		});
	}
	
	function confirmTags(){
		var data = {};
		var num = parseInt($('#tagContainer input[type="checkbox"]').length);
		for(var i = 0; i < num; i++){
			var obj = $('#tagContainer input[type="checkbox"]:eq('+i+')');
			if(obj.is(":checked")){
				var tagId = obj.attr('value');
				var categoryId = obj.attr('categoryId');
				data[tagId] = categoryId;
			}
		}
		if(Object.keys(data).length <= 0){
			return;
		}
		requestNode('/store/admin/addcommenttag', 'post', data, function(data){
			getAllTags();
		});
	}
	
	function deleteCommentTag(tagId){
		if(!confirm('删除标签，有可能会影响到app端评价界面的布局！！！你确定要删除吗？')){
			return false;
		}
		requestNode('/store/admin/deletecommenttag', 'post', {tagId:tagId}, function(data){
			getAllTags();
		});
		return true;
	}
	
	function switchCommentTag(tagId1, tagId2){
		if(!tagId1 || !tagId2){
			MyDialog.alert('此项目处于最低端或最顶端，不可以再移动！');
			return;
		}
		requestNode('/store/admin/switchcommenttag', 'post', {tagId1:tagId1,tagId2:tagId2}, function(data){
			getAllTags();
		});
	}
</script>
</body>
</html>