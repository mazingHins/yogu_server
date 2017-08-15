<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>cblog管理--品牌列表</title>
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
      <h1> cblog管理--品牌列表 <small></small> </h1>
      <ol class="breadcrumb">
        <li><a style="cursor:pointer" data-toggle="modal" data-target="#addNewBrandModal"><i class="fa fa-dashboard"></i> 新增品牌</a></li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <table class="table">
                    <tr>
                      <!--<td width="70">城市：</td>
                      <td width="120"><select class="form-control" name="orderStatus" id="city" onChange="listBrand()">
                          <c:forEach items="${cityList}" var="city">
                            <option value="${city.code}">${city.name}</option>
                          </c:forEach>
                        </select></td>
                      <td width="70">语言：</td>
                      <td width="100"><select class="form-control" id="lang" onChange="listBrand()">
                          <c:forEach items="${langList}" var="lang">
                            <option value="${lang.code}">${lang.zhName}</option>
                          </c:forEach>
                        </select></td>-->
                      <td width="100">排序方式：</td>
                      <td width="150"><select class="form-control" id="order" onChange="listBrand();">
                          <option value="brandId">按创建时间</option>
                          <option value="storeNum">按店铺数量</option>
                          <option value="brandName">按品牌名称</option>
                        </select></td>
                      <td width="150"><select class="form-control" id="by" onChange="listBrand();">
                          <option value="asc">顺序</option>
                          <option value="desc">反序</option>
                        </select></td>
                      <td width="300"><input type="text" class="form-control" id="brandName" placeholder="请输入要搜索的品牌..."/></td>
                      <td width="100"><input type="button" class="btn btn-success" id="searchBrandName" value="搜索品牌" onClick="listBrand()"/></td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  <table id="listTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th width="100">品牌ID</th>
                        <th width="200">品牌名称</th>
                        <th width="100">店铺数量</th>
                        <th width="180">创建日期</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <script id="listTableTpl" type="text/html">
						{{each object.list as value i}}
						<tr{{if value.status == 0}} class="danger"{{/if}}>
							<td>{{value.brandId}}</td>
							<td>{{value.brandName}}</td>
							<td>{{value.storeNum}}</td>
							<td>{{value.createTime}}</td>
							<td>
							<a href="/admin/cblog/brandInfo/info.xhtm?brandId={{value.brandId}}">查看详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
							{{if value.status == 1}}
								<a style="cursor:pointer;" onClick="return closeBrand({{value.brandId}});">关闭</a>
							{{/if}}
							{{if value.status == 0}}
								<a style="cursor:pointer;" onClick="return openBrand({{value.brandId}});">开启</a>
							{{/if}}
							</td>
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

<div class="modal fade" id="addNewBrandModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加品牌</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="nameCN" class="control-label">品牌名称：</label>
          <input type="text" class="form-control" id="name" placeholder="请输入品牌名称">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onClick="return addBrand();">提交</button>
      </div>
    </div>
  </div>
  </section>
</div>

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	listBrand();
	
	function addBrand(){
		var name = $('#name').val().trim();
		if(!name){
			MyDialog.alert('请输入品牌名称！');  
			return false;
		}
		if(name.length > 100){
			MyDialog.alert('品牌名称不能超过100个字！');  
			return false;
		}
		
		requestNode('/store/admin/addbrand', 'post', {name:name}, function(data){
			listBrand();
		});
		
		$('#addNewBrandModal').modal('hide');
		return true;
	}
	
	$('#addNewBrandModal').on('hidden.bs.modal', function () {
	  	$('#name').val('');
	})
	
	function listBrand(){
		$('#listTable').artPaginate({
			// 获取数据的地址
			url : "https://napi.mazing.com/store/admin/listbrand",
			// 显示页码的位置
			paginator: 'listPaginator2',
			// 模版ID
			tpl : 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params : {cityCode:$('#city').val()||'020',langCode:$('#lang').val()||'zh',brandName:$('#brandName').val().replace(/(\')?(\")?/g,''),order:$('#order').val(),by:$('#by').val()}
		});
	}
	
	function openBrand(brandId){
		var b = confirm('你确定要开启吗？');
		if(!b){
			return false;
		}
		requestNode('/store/admin/openbrand', 'post', {brandId:brandId}, function(data){
			listBrand();
		});
		return true;
	}
	
	function closeBrand(brandId){
		var b = confirm('你确定要关闭吗？');
		if(!b){
			return false;
		}
		requestNode('/store/admin/closebrand', 'post', {brandId:brandId}, function(data){
			listBrand();
		});
		return true;
	}
		
	</script>
</body>
</html>