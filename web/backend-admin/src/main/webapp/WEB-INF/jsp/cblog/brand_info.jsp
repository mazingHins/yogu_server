<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>cblog管理--品牌详情</title>
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
    <section class="content-header"> <a style="cursor:pointer" onclick="window.location.href='/admin/cblog/brandList/list.xhtm'"> 返回品牌列表</a>
      <ol class="breadcrumb">
        <li><a href="/admin/cblog/brandStoreInfo/edit.xhtm?brandId=${brandId}&sinfoId=0"><i class="fa fa-dashboard"></i> 新增店铺</a></li>
      </ol>
    </section>
    
    <!-- 二维码 modal-->
    <div class="modal fade" id="qrimageModal" tabindex="-1" role="dialog" aria-labelledby="qrimageModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
            <h4 class="modal-title" id="qrimageModalLabel">查看二维码</h4>
          </div>
          <div class="modal-body">
            <div class="row" style="text-align:center"> </div>
          </div>
          <div class="modal-footer"> </div>
        </div>
      </div>
    </div>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header"> </div>
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <h2>店铺详情</h2>
                  <table class="table table-bordered table-hover">
                    <tr>
                      <td width="200">品牌ID：</td>
                      <td id="brandId"></td>
                    </tr>
                    <tr>
                      <td>品牌名称：</td>
                      <td id="brandName"></td>
                    </tr>
                    <tr>
                      <td>店铺数量：</td>
                      <td id="storeNum"></td>
                    </tr>
                    <tr>
                      <td>创建日期：</td>
                      <td id="createTime"></td>
                    </tr>
                    <tr>
                      <td>品牌状态：</td>
                      <td id="status"></td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="row">
                <div class="col-sm-12">
                  <table class="table">
                    <tr>
                      <td width="300"><input type="text" class="form-control" id="storeName" placeholder="请输入要搜索的店铺..."/></td>
                      <td width="100"><input type="button" class="btn btn-success" id="searchStoreName" value="搜索店铺" onClick="listStoreInfo()"/></td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  <h2>店铺列表：</h2>
                  <table id="listTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th width="200">店铺ID</th>
                        <th width="300">店铺名称</th>
                        <th width="300">关联的mini-blog餐厅</th>
                        <th width="400">地址</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <script id="listTableTpl" type="text/html">
						{{each object as value i}}
						<tr{{if value.status == 0}} class="danger"{{/if}}>
							<td>{{value.sinfoId}}</td>
							<td>{{value.storeName}}</td>
							<td>
							{{if value.storeId > 0}}<a href="/admin/store/storeDetail.xhtm?storeId={{value.storeId}}" title="点我可以跳到餐厅详情页面！" target="_blank">{{value.miniblogStoreName}}</a>{{/if}}
							{{if value.storeId == 0}}暂无{{/if}}
							</td>
							<td>{{value.addressDetail}}</td>
							<td><a href="/admin/cblog/brandStoreInfo/edit.xhtm?brandId={{value.brandId}}&sinfoId={{value.sinfoId}}" title="点击查看详情">查看详情</a>
						&nbsp;&nbsp;<a href="javascript:void(0)" onClick="qrimage({{value.sinfoId}})">查看二维码</a>
						&nbsp;&nbsp;<a href="javascript:void(0)" onClick="return deleteCblog({{value.sinfoId}},'{{value.storeName}}')">删除店铺</a>
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

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	getBrandInfo();
	
	function qrimage(sinfoId){
		var url = 'https://m.mazing.com/open/share/storeinfo/' + sinfoId;
		$('#qrimageModal .row').html('<img src="https://napi.mazing.com/system/qrimage/get/'+encodeURIComponent(url)+'" width="300" height="300"/><br/>H5地址：' + url);
		$('#qrimageModal').modal();
	}
	
	function getBrandInfo(){
		requestNode('/store/admin/getbrand', 'get', {brandId:${brandId}}, function(data){
			$('title').html('cblog管理--品牌详情--'+data.brandName);
			$('.content-header h1').html(data.brandName+'的品牌详情');
			$('#brandId').html(data.brandId);
			$('#brandName').html(data.brandName);
			$('#storeNum').html(data.storeNum);
			$('#createTime').html(moment(data.createTime * 1000).format('YYYY-MM-DD HH:mm:ss'));
			var status = data.status === 1 ? '开启' : '关闭';
			status += '&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">(如果品牌的状态为“关闭”，则app不会显示属于该品牌所有的cblog资讯！)</font>';
			$('#status').html(status);
			listStoreInfo();
		});
	}
	
	function listStoreInfo(){
		$('#listTable').artPaginate({
			// 获取数据的地址
			url : "https://napi.mazing.com/store/admin/liststoreinfo",
			// 显示页码的位置
			paginator: 'listPaginator2',
			// 模版ID
			tpl : 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params : {brandId:${brandId},storeName:$('#storeName').val()}
		});
	}
	
	function deleteCblog(sinfoId, storeName){
		if(!confirm('你确定要删除 “'+storeName+'” 吗？')){
			return false;
		}
		
		requestNode('/store/admin/deletecblog', 'post', {sinfoId:sinfoId}, function(data){
			window.location.reload();
		});
		return true;
	}
	</script>
</body>
</html>