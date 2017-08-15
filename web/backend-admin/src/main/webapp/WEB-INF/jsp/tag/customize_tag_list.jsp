<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>标签定制配置管理</title>
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
				<h1>
					标签定制配置 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#configModal" onclick="reset()"><i
							class="fa fa-dashboard"></i> 新增配置</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
					
						<!-- 查询条件 -->
						<div class="box-header">
							<div class="col-sm-6 form-inline">
								<span id="nowCityName">城市：</span>
								<select class="form-control" id="city">
									<c:forEach items="${cityList}" var="city">
										<option value="${city.code}">${city.name}</option>
									</c:forEach>
								</select>
								
								&nbsp;&nbsp;&nbsp;
								<span id="nowCityName">语言：</span>
								<select class="form-control" id="lang">
									<option value="">所有</option>
									<option value="zh">中文</option>
									<option value="en">英文</option>
								</select>	
								
								&nbsp;&nbsp;&nbsp;
								<div class="input-group input-group-sm col-sm-1">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="search();">查询</button>
									</span>
								</div>
							</div>
							<div class="col-sm-6 form-inline blockquote-reverse">
								<a href="javascript:void(0);" data-toggle="modal" data-target="#configModal" onclick="reset()"><i class="fa fa-dashboard"></i>新增筛选标签</a>
							</div>
						</div>
					
						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 系统配置 -->
							<div class="tab-pane fade in active" id="activityConfig">
								<div class="box box-solid">
									<div class="box-header">
										<div class="row">
											<div class="col-sm-12">
												<p class="text-left">说明：</p>
											</div>
										</div>
									</div>
									<!-- /.box-header -->

									<div class="box-body">
										<div class="row">
											<div class="col-sm-12">

												<table id="poolTable" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="10%">城市</th>
															<th width="10%">中文名称</th>
															<th width="10%">英文名称</th>
															<th width="10%">序号</th>
															<th width="10%">被用户选中的数量</th>
															<th width="10%">状态</th>
															<th width="10%">显示中文版</th>
															<th width="10%">显示英文版</th>
															<th width="10%">创建时间</th>
															<th width="10%">操作</th>
														</tr>
													</thead>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{if value.cityCode == '020'}}
																广州市
															{{/if}}
															
															{{if value.cityCode == '0755'}}
																深圳市
															{{/if}}
														</td>
														<td>
															{{value.cnName}}
														</td>
														<td>
															{{value.enName}}
														</td>
														<td>
															{{value.sequence}}
														</td>
														<td>
															{{value.useNumber}}
														</td>
														<td>
															{{if value.status == <%=BooleanConstants.TRUE%>}}
																有效
															{{/if}}
															{{if value.status == <%=BooleanConstants.FALSE%>}}
																无效
															{{/if}}
														</td>
														<td>
															{{if value.showZh == <%=BooleanConstants.TRUE%>}}
																<font color="red">是</font>
															{{/if}}
															{{if value.showZh == <%=BooleanConstants.FALSE%>}}
																<font color="red">否</font>
															{{/if}}
														</td>
														<td>
															{{if value.showEn == <%=BooleanConstants.TRUE%>}}
																<font color="red">是</font>
															{{/if}}
														{{if value.showEn == <%=BooleanConstants.FALSE%>}}
																<font color="red">否</font>
															{{/if}}
														</td>
														<td>
															{{formatDateTime value.createTime}}
														</td>

													<td>
													<a href="javascript:void(0);" class="btn btn-default" onClick="viewDetail('{{value.customizeId}}', '{{value.cnName}}');">查看</a>
													<a href="" class="btn btn-default" id="shelf" name="shelf"  data-toggle="modal" data-target="#configModal" onclick="modifyCustomize('{{value.customizeId}}', '{{value.cityCode}}', '{{value.cnName}}', '{{value.enName}}', '{{value.sequence}}', '{{value.status}}', '{{value.showZh}}', '{{value.showEn}}')">修改</a>
													</td>
													</tr>
													{{/each}}
												</script>
												</table>
											</div>
										</div>

										<div class="row col-sm-12" id="listPaginator"></div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->

							</div>
							<!-- /.tab -->
							<!-- tab end 系统配置 -->

						</div>
						<!-- /.tabContent - -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
				
				
				<!-- Modal -->
				<div class="modal fade" id="configModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<!-- -->
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="reset()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">编辑配置</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal">
									<div class="form-group" id="cityCodeView">
										<label for="msg" class="control-label col-md-4">城市编码<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="hidden" class="form-control" id="customizeId">
											<input type="text" class="form-control" id="cityCode">
										</div>
									</div>
									<div class="form-group">
										<label for="msg" class="control-label col-md-4">中文名称<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="cnName">
										</div>
									</div>
									<div class="form-group">
										<label for="msg" class="control-label col-md-4">英文名称<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="enName">
										</div>
									</div>

									<div class="form-group">
										<label for="msg" class="control-label col-md-4">排序号<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="sequence">
										</div>
									</div>
									
									<div class="form-group">
										<label for="status" class="control-label col-md-4">是否有效:
											<span style="color: red;">*</span>
										</label>
										<!--  <input type="text" class="form-control" id="isOpen"> -->
										<div class="col-md-3">
											<select name="select4" id="status" class="form-control">
												<option value="0">否</option>
												<option value="1" selected="selected col-md-4">是</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="status" class="control-label col-md-4">展示给中文用户:
											<span style="color: red;">*</span>
										</label>
										<!--  <input type="text" class="form-control" id="isOpen"> -->
										<div class="col-md-3">
											<select name="select4" id="showZh" class="form-control">
												<option value="1" selected="selected col-md-4">是</option>
												<option value="0">否</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="status" class="control-label col-md-4">展示给英文用户:
											<span style="color: red;">*</span>
										</label>
										<div class="col-md-3">
											<select name="select4" id="showEn" class="form-control">
												<option value="1" selected="selected col-md-4">是</option>
												<option value="0">否</option>
											</select>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="reset()">取消</button>
								<button type="button" class="btn btn-primary" onclick="save()">保存</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.Modal -->
				
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
		// 查询标签定制列表
		function search() {
			$('#poolTable').artPaginate({
				// 获取数据的地址
				url: "/admin/tag/listCustomize",
				// 显示页码的位置
				paginator: 'listPaginator',
				// 模版ID
				tpl: 'listTableTpl',
				// 请求的参数表，默认page=1, pageSize=20
				params: {'city' : $('#city').val(), 'lang': $('#lang').val()},
				logPage: 'list_coutomize_page'
			});
		}

		$(function() {
			search();
		});
		
		// 新增配置
		function reset(){
			$('#cityCodeView').show();
			$('#customizeId').val('');
			$('#cityCode').val('');
			$('#cnName').val('');
			$('#enName').val('');
			//设置某项为选中状态    
			$('#status').val('1');
			$('#showZh').val('1');
			$('#showEn').val('1');
		}
		
		// 修改配置
		function modifyCustomize(customizeId, cityCode, cnName, enName, sequence, status, showZh, showEn) {
			$('#cityCodeView').hide();
			$('#customizeId').val(customizeId);
			$('#cityCode').val(cityCode);
			$('#cnName').val(cnName);
			$('#enName').val(enName);
			$('#sequence').val(sequence);
			//设置某项为选中状态    
			$("#status").get(0).selectedIndex = status;
			$("#showZh").val(showZh);
			$("#showEn").val(showEn);
		}
		
		// 保存
		function save() {
			
			var customizeId = $('#customizeId').val();
			var cityCode = $.trim($('#cityCode').val());
			var cnName = $.trim($('#cnName').val());
			var enName = $.trim($('#enName').val());
			var status = $.trim($('#status').val());
			var sequence = $.trim($('#sequence').val());
			var showZh = $('#showZh').val();
			var showEn = $('#showEn').val();
			if (customizeId == null || customizeId == '') {
				customizeId = 0;
			}

			$.post('/admin/tag/saveCustomize.do', {
				'cityCode' : cityCode,
				'customizeId' : customizeId,
				'cnName' : cnName,
				'enName' : enName,
				'status' : status,
				'sequence' : sequence,
				'showZh' : showZh,
				'showEn' : showEn
			}, function(json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新拉取
					search();
				}
			}, 'json');
			
		}
		
		function viewDetail(id, name){
			window.location.href = '/admin/tag/customizeDetail.xhtm?customizeId='+id+'&name=' + encodeURI(encodeURI(name));
		}
	</script>
</body>
</html>
