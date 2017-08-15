<%@ page import="com.mazing.core.enums.config.FindPickType" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>发现挑选配置管理</title>
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
					发现挑选配置 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 系统配置 -->
							<div class="tab-pane fade in active" id="activityConfig">
								<div class="box box-solid">
									<div class="box-header">
										<div class="row">
											<div class="col-sm-12">
												<p class="text-left">说明：</p>
												<p class="text-left">无法新增配置栏目，只能进行栏目的修改，如果需要新增栏目，跟服务端的人员申请。</p>
												<p class="text-left">修改内容包括：中英文名称，顺序，是否展示分类，是否在app上展示该栏目，编辑该栏目下的标签</p>
												<p class="text-left">序号：栏目的排序，先后顺序是按照“序号”从小到大</p>
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
															<th width="10%">中文名称</th>
															<th width="10%">英文名称</th>
															<th width="10%">配置类型</th>
															<th width="10%">序号</th>
															<th width="10%">是否展示标签种类</th>
															<th width="10%">客户端是否可以多选</th>
															<th width="10%">有效状态</th>
															<th width="10%">更新时间</th>
															<th width="10%">操作</th>
														</tr>
													</thead>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.cnName}}
														</td>
														<td>
															{{value.enName}}
														</td>
														<td>
															{{if value.findType == <%=FindPickType.SCENE.getValue()%>}}
																场景
															{{/if}}
															{{if value.findType == <%=FindPickType.TIME_INTERVAL.getValue()%>}}
																时段
															{{/if}}
															{{if value.findType == <%=FindPickType.AREA.getValue()%>}}
																区域
															{{/if}}
															{{if value.findType == <%=FindPickType.DISH.getValue()%>}}
																菜系
															{{/if}}
														</td>
														<td>
															{{value.sequence}}
														</td>
														<td>
															{{if value.showCategory == <%=BooleanConstants.TRUE%>}}
																是
															{{/if}}
															{{if value.showCategory == <%=BooleanConstants.FALSE%>}}
																否
															{{/if}}
														</td>
														<td>
															{{if value.multiSelect == <%=BooleanConstants.TRUE%>}}
																是
															{{/if}}
															{{if value.multiSelect == <%=BooleanConstants.FALSE%>}}
																否
															{{/if}}
														</td>
														<td>
															{{if value.effectiveStatus == <%=BooleanConstants.TRUE%>}}
																是
															{{/if}}
															{{if value.effectiveStatus == <%=BooleanConstants.FALSE%>}}
																否
															{{/if}}
														</td>
														<td>{{formatDateTime value.updateTime}}</td>
														<td>
															<a href="/admin/tag/findPickTag.xhtm?findId={{value.findId}}">查看</a>
															&nbsp; <a href="javascript:void(0)" onclick="modifyPick('{{value.findId}}','{{value.cnName}}','{{value.enName}}', '{{value.findType}}', '{{value.sequence}}', '{{value.showCategory}}', '{{value.multiSelect}}', '{{value.effectiveStatus}}')" data-toggle="modal" data-target="#configModal">修改</a>
														</td>
													</tr>
													{{/each}}
												</script>
												</table>
											</div>
										</div>
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
				
				<!-- update Modal -->
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
										<label for="type" class="control-label col-md-4">栏目类型:<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<select name="select" id="findType">
												<option value="1">场景</option>
												<option value="2">时段</option>
												<option value="3">区域</option>
												<option value="4">菜系</option>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="msg" class="control-label col-md-4">序号<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<input type="text" class="form-control" id="sequence">
										</div>
									</div>

									<div class="form-group">
										<label for="position" class="control-label col-md-4">展示标签种类:<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<select name="select2" id="showCategory">
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="position" class="control-label col-md-4">是否支持多选:<span
											style="color: red;">*</span></label>
										<div class="col-md-8">
											<select id="multiSelect">
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="status" class="control-label col-md-4">是否有效:
											<span style="color: red;">*</span>
										</label>
										<div class="col-md-3">
											<select name="select4" id="effectiveStatus" class="form-control">
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
									<div>
										<input type="hidden" class="form-control" id="findId" value="">
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
				<!-- /.update Modal -->
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
				url: "/admin/tag/listFindPick",
				// 显示页码的位置
				// paginator: 'listPaginator2',
				// 模版ID
				tpl: 'listTableTpl',
				params: {},
				logPage: 'list_findPick_page'
			});
		}
		
		// 修改栏目
		function modifyPick(findId, cnName, enName, findType, sequence, showCategory, multiSelect, effectiveStatus) {

			$('#findId').val(findId);
			$('#cnName').val(cnName);
			$('#enName').val(enName);
			//设置某项为选中状态    
			$("#findType").get(0).selectedIndex = findType - 1;
			$('#sequence').val(sequence);
			$("#showCategory").get(0).selectedIndex = showCategory;
			$('#multiSelect').get(0).selectedIndex = multiSelect;
			$("#effectiveStatus").get(0).selectedIndex = effectiveStatus;
		}
		
		// 保存栏目
		function save() {

			var findId = $('#findId').val();
			var cnName = $.trim($('#cnName').val());
			var enName = $.trim($('#enName').val());
			var findType = $.trim($('#findType').val());
			var sequence = $('#sequence').val();
			var showCategory = $.trim($('#showCategory').val());
			var multiSelect = $('#multiSelect').val();
			var effectiveStatus = $.trim($('#effectiveStatus').val());
			
			if (cnName == "") {
				MyDialog.alert('中文名称不能为空');
				return;
			}
			if (cnName == "") {
				MyDialog.alert('英文名称不能为空');
				return;
			}
			if (sequence == "" || sequence <0) {
				MyDialog.alert('顺序填写错误');
				return;
			}

			$.post('/admin/tag/updateFindPick.do', {
				'findId' : findId,
				'cnName' : cnName,
				'enName' : enName,
				'findType' : findType,
				'sequence' : sequence,
				'showCategory' : showCategory,
				'multiSelect' : multiSelect,
				'effectiveStatus' : effectiveStatus
			}, function(json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新拉取
					search();
				}
			}, 'json');

			$('#findId').val('');
		}

		$(function() {
			search();
		});
		
	</script>
</body>
</html>
