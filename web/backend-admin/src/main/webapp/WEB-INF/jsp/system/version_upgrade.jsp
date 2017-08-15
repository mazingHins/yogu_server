<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>版本升级配置管理</title>
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
					版本升级配置管理 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)" data-toggle="modal"
						data-target="#configModal" onclick="addConfig()"><i
							class="fa fa-dashboard"></i> 新增配置</a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<ul id="cacheTab" class="nav nav-tabs">
							<li class="active"><a href="#systemConfig" data-toggle="tab">
									系统配置 </a></li>
							<!--  <li><a href="#otherConfig" data-toggle="tab">其他配置</a></li>-->
						</ul>

						<div id="cacheTabContent" class="tab-content">
							<!-- tab start 系统配置 -->
							<div class="tab-pane fade in active" id="systemConfig">
								<div class="box box-solid">
									<div class="box-body">
										<div class="row">
											<div class="col-sm-12">
												<div style="word-wrap:break-word;color:red;">注意： 同类型的版本升级最多只能有一条状态为开启的,开启之后将会在app内提示升级, 请谨慎处理！</div>
												</br>
												</br>
												<table id="listTable"
													class="table table-bordered table-hover">
													<thead>
														<tr>
															<th width="8%">版本号</th>
															<th width="8%">发布时间</th>
															<th width="8%">系统类型</th>
															<th width="10%">标题</th>
															<th width="22%">提示</th>
															<th width="10%">升级地址</th>
															<th width="5%">md5</th>
															<th width="5%">包大小</th>
															<th width="5%">开启</th>
															<th width="5%">强制</th>
														</tr>
													</thead>
													<tbody id="listTableBody" style="font-size: 14px;">

													</tbody>
													<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.version}}
														</td>
														<td style="word-wrap:break-word;">{{formatDateTime value.releaseTime}}</td>
														<td style="word-wrap:break-word;">
															{{if value.systype == 1}}
																IOS 正常版本
															{{/if}}
															
															{{if value.systype == 2}}
																Android 正常版本
															{{/if}}
															
															{{if value.systype == 10}}
																IOS ORG版本
															{{/if}}
														</td>
														<td style="word-wrap:break-word;">{{value.title}}</td>
														<td style="word-wrap:break-word;">{{value.msg}}</td>
 														<td style="word-wrap:break-word;">{{value.url}}</td>
														<td style="word-wrap:break-word;">{{value.md5}}</td>
														<td style="word-wrap:break-word;">{{value.size}}</td>
														
														
														
															{{if value.isOpen == 0}}
																<td style="word-wrap:break-word;">
																	否
															{{/if}}
															
															{{if value.isOpen == 1}}
																<td style="word-wrap:break-word;color:red;">
																	是
															{{/if}}
														
																</td>
														
															{{if value.isForce == 0}}
																<td style="word-wrap:break-word;">
																	否
															{{/if}}
															
															{{if value.isForce == 1}}
																<td style="word-wrap:break-word;color:red;">
																	是
															{{/if}}
																</td>
																
														<td>
														
													<a href="javascript:deleteUpgrade({{value.vid}});">删除</a>
													&nbsp; <a href="javascript:void(0)" onclick="modify('{{value.version}}', '{{formatDateTime value.releaseTime}}', '{{value.systype}}', '{{value.title}}', '{{value.msg}}', '{{value.url}}', '{{value.md5}}', '{{value.size}}', '{{value.isOpen}}', '{{value.isForce}}', '{{value.vid}}')" data-toggle="modal" data-target="#configModal">修改</a>
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

							<!-- tab start 其他配置 -->
							<div class="tab-pane fade in" id="otherConfig">
								<div class="box box-solid">
									<div class="box-header">
										<div class="col-sm-8">
											<p class="text-left">其他配置。</p>
										</div>
									</div>
									<!-- /.box-header -->
									<form role="form" id="createUserForm" name="createUserForm"
										action="/admin/user/createUser.do" method="post">
										<div class="box-body">
											<div class="row">
												<div class="form-horizontal col-sm-12">
													<div class="form-group">
														<label class="col-sm-2 control-label"> 配置 </label>
														<div class="col-sm-8">
															<input type="text" name="cacheKey" class="form-control"
																placeholder="请输入一个key" maxlength="100" />
														</div>
													</div>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-2">
															<button type="button" class="btn btn-primary">读取</button>
														</div>
													</div>

												</div>
											</div>
										</div>
										<!-- /.box-body -->
									</form>
								</div>
								<!-- /.box -->
							</div>
							<!-- tab end 其他配置 -->


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
							<!-- -->	<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" onclick="addConfig()">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">编辑配置</h4>
							</div>
							<div class="modal-body">
								<form id="editForm" class="form-horizontal">
								<div class="form-group">
									<label for="version" class="control-label col-md-4">版本号:</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="version">
									</div>
								</div>
								<div class="form-group">
									<label for="releaseTime" class="control-label col-md-4">发布时间:</label>
									<div class="col-md-8">
										<input
												type="text" class="form-control" id="releaseTime"
												placeholder="格式为2015-12-02 10:56:23" style="color: gray;"
												onfocus="pre(this)" onkeypress="pre(this)" />
									</div>
								</div>
								<div class="form-group">
									<label for="systype" class="control-label col-md-4">系统类型:</label>
									<!--<textarea class="form-control" id="systype"></textarea>-->
									<div class="col-md-8">
										<select name="select" id="systype">
											<option value="1" selected="selected">IOS 正常版本</option>
											<option value="2">Android 正常版本</option>
											<option value="10">IOS ORG版本</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="title" class="control-label col-md-4">提示标题: <span style="color:red;">*</span></label>
									<div class="col-md-8">
										<input
												type="text" class="form-control" id="title" placeholder="1~64个字符">
									</div>
								</div>
								<div class="form-group">
									<label for="msg" class="control-label col-md-4">更新提示: <span style="color:red;">*</span></label>
									<div class="col-md-8">
										<textarea class="form-control" id="msg" placeholder="1~2000个字符"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label for="url" class="control-label col-md-4">升级地址: <span style="color:red;">*</span></label>
									<div class="col-md-8">
										<input
												type="text" class="form-control" id="url" placeholder="http地址">
									</div>
								</div>
								<div class="form-group">
									<label for="md5" class="control-label col-md-4">安卓文件md5:</label>
									<div class="col-md-8">
										<input
												type="text" class="form-control" id="md5" placeholder="为空或32个字符">
									</div>
								</div>
								<div class="form-group">
									<label for="size" class="control-label col-md-4">包大小:</label>
									<div class="col-md-3">
										<input
												type="text" class="form-control" id="size" value="0">
									</div>
								</div>
								<div class="form-group">
									<label for="isOpen" class="control-label col-md-4">是否开启: <span style="color:red;">*</span></label>
									<!--  <input type="text" class="form-control" id="isOpen"> -->
									<div class="col-md-3">
										<select name="select2" id="isOpen" class="form-control">
											<option value="0" selected="selected col-md-4">否</option>
											<option value="1">是</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="isForce" class="control-label col-md-4">是否强制: <span style="color:red;">*</span></label>

									<!-- <input type="text" class="form-control" id="isForce"> -->
									<div class="col-md-8">
										<select name="select3" id="isForce" class="form-control">
											<option value="0" selected="selected">否</option>
											<option value="1">是</option>
										</select>
									</div>
								</div>

								<!-- -->
								<div>
									<input type="hidden" class="form-control" id="vid" value="">
								</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="addConfig()">取消</button>
								<button type="button" class="btn btn-primary" onclick="save()">保存</button>
							</div>
						</div>
					</div>
				</div>
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
		// 配置表
		var configMap = {};

		// 新增
		function addConfig() {
			$('#vid').val('');
			$('#version').val('');
//			$('#releaseTime').val('格式为2015-12-02 10:56:23');
			$('#releaseTime').val(new Date().format('yyyy-MM-dd HH:mm:ss'));
			$('#systype').val('1');
			$('#title').val('');
			$('#msg').val('');
			$('#url').val('');
			$('#md5').val('');
			$('#size').val('0');
			$('#isOpen').val('0');
			$('#isForce').val('0');
		}

		function pre(el) {
			el.style.color = "black";
		}

		function strDateTime2(str) {
			//格式2003-12-05 13:04:06
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			var r = str.match(reg);
			if (r == null)
				return false;
			var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
			return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
					&& d.getDate() == r[4] && d.getHours() == r[5]
					&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
		}

		// 修改
		function modify(version, releaseTime, systype, title, msg, url, md5, size, isOpen, isForce, vid) {

			$('#vid').val(vid);
			$('#version').val(version);
			$('#releaseTime').val(releaseTime);

			var index = 0;
			if (systype == 10)
				index = 2;
			else
				index = systype - 1;
			//设置某项为选中状态    
			$("#systype").get(0).selectedIndex = index;

			$('#title').val(title);
			$('#msg').val(msg);
			$('#url').val(url);
			$('#md5').val(md5);
			$('#size').val(size);

			$("#isOpen").get(0).selectedIndex = isOpen;
			$("#isForce").get(0).selectedIndex = isForce;

		}
		
		 String.prototype.replaceAll  = function(s1,s2){    
			    return this.replace(new RegExp(s1,"g"),s2);    
			  } 

		// 保存
		function save() {

			var vid = $('#vid').val();
			var version = $.trim($('#version').val());
			var releaseTime = $.trim($('#releaseTime').val());
			var systype = $.trim($('#systype').val());
			var title = $.trim($('#title').val());
			var msg = $.trim($('#msg').val());
			var url = $.trim($('#url').val());
			var md5 = $.trim($('#md5').val());
			var size = $.trim($('#size').val());
			var isOpen = $('#isOpen').val();
			var isForce = $('#isForce').val();

			
			if (version == "") {
				MyDialog.alert('版本不能为空，请准确填写');
				return;
			}
			
			var tmp = version.replaceAll("\\.","");
			//var reg = new RegExp("\\d+");
			//alert(tmp);
			var reg = /^\d+$/;
			if(!reg.test(tmp)){
				MyDialog.alert('版本号格式不正确，请准确填写');
				return;
		     }
			
			
			if (releaseTime == "") {
				MyDialog.alert('发布时间不能为空，请准确填写');
				return;
			}
			
			if(strDateTime2(releaseTime)==false){
				MyDialog.alert('发布时间格式不正确，格式形如：2015-12-02 10:56:23');
				return;
			}
			
			
			if (title == "") {
				MyDialog.alert('标题不能为空，请准确填写');
				return;
			}
			if (msg == "") {
				MyDialog.alert('提示内容不能为空，请准确填写');
				return;
			}
			if (url == "") {
				MyDialog.alert('更新下载地址不能为空，请准确填写');
				return;
			}
			if (size == "") {
				MyDialog.alert('包大小不能为空，请准确填写');
				return;
			}

			$.post('/admin/system/saveVersionUpgrade.do', {
				'vid' : vid,
				'version' : version,
				'releaseTime' : releaseTime,
				'systype' : systype,
				'title' : title,
				'msg' : msg,
				'url' : url,
				'md5' : md5,
				'size' : size,
				'isOpen' : isOpen,
				'isForce' : isForce
			}, function(json) {
				$('#configModal').modal('hide');
				MyDialog.alert(json.message);
				if (json.success) {
					// 重新拉取
					query();
				}
			}, 'json');
			
			$('#vid').val('');
		}
		// 删除版本升级信息
		var executeDelete;
		function deleteUpgrade(vid) {
			executeDelete = false;

			BootstrapDialog.show({
				title : '删除版本升级记录',
				message : '确认要删除这条记录吗?',
				buttons : [ {
					label : '确定',
					action : function(dialog) {
						executeDelete = true;
						dialog.close();
					}
				}, {
					label : '取消',
					action : function(dialog) {
						dialog.close();
					}
				} ],
				onhide : function(dialogRef) {
					if (executeDelete == true) {
						$.post('/admin/system/deleteVersionUpgrade.do', {
							'vid' : vid
						}, function(json) {
							MyDialog.alert(json.message);
							if (json.success) {
								// 重新拉取
								query();
								$('#vid').val();
								executeDelete = false;
							}
						}, 'json');
					}
				}
			});

		}

		// 查询所有配置
		function query() {
			// 注意：这里可以使用一个参数 groupCode
			var systype = '';
			$.getJSON("/admin/system/listAllVersionUpgrades", {
				'systype' : systype
			}, function(json) {
				if (json.success) {
					var htmlTxt = template('listTableTpl', json);
					$('#listTableBody').html(htmlTxt);
					var list = json.object;
					for (var i = 0; i < list.length; i++) {
						var row = list[i];
						var key = row.groupCode + '_' + row.configKey;
						configMap[key] = row;
					}
				} else {
					MyDialog.alert(json.message);
				}
			});
			$('#vid').val('');
		}

		$(function() {
			template.helper('renderConfigValue', function(value) {
				if (value.length <= 50) {
					return value;
				}
				return value.substr(0, 47) + "……";
			});
			template.helper('renderConfigKey', function(value) {
				if (value.length <= 30) {
					return value;
				}
				return value.substr(0, 27) + "……";
			});

			query();

		});
	</script>
</body>
</html>
