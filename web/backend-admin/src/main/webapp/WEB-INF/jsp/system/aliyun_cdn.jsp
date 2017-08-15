<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>阿里云CDN缓存刷新</title>
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
				刷新CDN缓存
				<small></small>
			</h1>
			<ol class="breadcrumb">
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row" style="padding: 15px;">
				<p class="bg-warning text-danger" style="padding: 15px;">注意：每天最多可以刷新${urlQuota}个URL和${dirQuota}个目录。刷新任务生效时间大约为5分钟。</p>
			</div>
		
			<div class="row">
				<div class="col-xs-12">

					<div class="box box-solid">
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist">
										<li role="presentation" class="active"><a href="#refreshUrl" role="tab" data-toggle="tab">URL刷新</a></li>
										<li role="presentation"><a href="#refreshDir" role="tab" data-toggle="tab">目录刷新</a></li>
										<li role="presentation"><a id="refreshListID" href="#refreshList" role="tab" data-toggle="tab">查询操作记录</a></li>
									</ul>

									<!-- Tab panes -->
									<div class="tab-content">
										<!-- URL刷新 -->
										<div role="tabpanel" class="tab-pane active" id="refreshUrl">
											<div style="padding: 15px 0 0;">
												<div><p class="text-muted">输入刷新缓存的URL路径：</p></div>
												<div><textarea id="refreshUrlText" rows="10" multi-url-rightful="" class="form-control" style="font-size: 16px;" placeholder="https://static.mazing.com/abc/image/1.png，刷新首页请输入https://static.mazing.com/" required="required"></textarea></div>
												<div><p class="text-muted">多个URL请用回车分隔，每个URL应当以http://或https://开头，一次提交不能超过10个URL</p></div>
												<div><p>剩余URL刷新数量<b class="text-warning">${urlRemain}</b>个</p></div>
												<div><button id="refreshUrlBtn" class="btn btn-primary">刷新URL</button></div>
											</div>
										</div>
										<!-- 目录刷新 -->
										<div role="tabpanel" class="tab-pane" id="refreshDir">
											<div style="padding: 15px 0 0;">
												<div><p class="text-muted">输入刷新缓存的目录路径：</p></div>
												<div><textarea id="refreshDirText" rows="10" multi-url-rightful="" class="form-control" style="font-size: 16px;" placeholder="https://static.mazing.com/abc/image/，刷新全站请输入https://static.mazing.com/" required="required"></textarea></div>
												<div><p class="text-muted">多个目录请用回车隔开，每个URL应当以http://或https://开头，一次提交不能超过10个目录</p></div>
												<div><p>剩余目录刷新数量<b class="text-warning">${dirRemain}</b>个</p></div>
												<div><button id="refreshDirBtn" class="btn btn-primary">刷新目录</button></div>
											</div>
										</div>
										<!-- 查询操作记录 -->
										<div role="tabpanel" class="tab-pane" id="refreshList">
											<div style="padding: 15px 0 0;">
												<table id="listTable" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>操作内容  <button id="refreshListBtn" class="btn btn-default">刷新</button></th>
															<th>操作时间</th>
															<th>状态</th>
															<th>进度</th>
														</tr>
													</thead>
													<tbody id="listTableBody" style="font-size: 14px;">
													</tbody>
												</table>
											</div>
										</div>
									</div>

								</div>
							</div>

						</div><!-- /.box-body -->
					</div><!-- /.box -->


				</div><!-- /.col -->
			</div><!-- /.row -->


		</section><!-- /.content -->
	</div><!-- /.content-wrapper -->

	<!-- footer -->
	<jsp:include page="/include/footer.jsp"/>

	<!-- control sidebar -->
	<jsp:include page="/include/control-sidebar.jsp"/>
</div><!-- ./wrapper -->

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>

<script id="listTableTpl" type="text/html">
{{each object as value i}}
	<tr>
		<td>{{value.objectPath}}</td>
		<td>{{formatDateTime value.creationTime}}</td>
		<td>{{showStatus value.status}}</td>
		<td>{{value.process}}</td>
	</tr>
{{/each}}
</script>

<script type="text/javascript">
	var batchSize = 10;
	var domainList = ['http://static.mazing.com/', 'https://static.mazing.com/', 'http://img.mazing.com/', 'https://img.mazing.com/'];
	function err(msg) {
		MyDialog.alert(msg);
		return false;
	}
	function startsWith(source, str) {
		return (0 == source.indexOf(str));
	}
	function endWith(source, str) {
		var pos = source.lastIndexOf(str);
		if(pos === -1) {
			return false;
		} else {
			return pos + str.length === source.length;
		}
	}
	function valiRefresh(text) {
		text = $.trim(text);
		if(!(text)) return err('请按要求输入内容！');
		// 正确操作的数据
		var data = [];
		// 分解，单个交验
		var domainErrMsg = '';
		var ts = text.split('\n');
		var subSize = 0;
		for(var i = 0; i < ts.length; i++) {
			var t = $.trim(ts[i]);
			if(!(t)) continue;
			// 基本校验
			if(-1 != t.indexOf(' ')) return err('路径中不能包含空格！');
			if(!(startsWith(t, 'http://')) && !(startsWith(t, 'https://'))) return err('必须以http://或者https://开头！');
			// 域名有效性交验
			var inOk = false;
			for(var j = 0; j < domainList.length; j++) if(startsWith(t, domainList[j])) inOk = true;
			if(!(inOk)) domainErrMsg += ('<br>'+t);
			// 数量校验
			if(inOk) {
				subSize += 1;
				if(batchSize < subSize) return err('一次提交不能超过'+batchSize+'个URL。');
			}
			// ---  OK
			data[data.length] = t;
		}
		if(domainErrMsg) return err('以下域名不能操作：'+domainErrMsg);
		return data;
	}

	function subRefreshUrl() {
		var text = $('#refreshUrlText').val();
		var data = valiRefresh(text);
		if(data) sub(data, false);
	}
	function subRefreshDir() {
		var text = $('#refreshDirText').val();
		var data = valiRefresh(text);
		if(data) sub(data, true);
	}
	function sub(dataArr, isDir) {
		// 目录必须以 / 结尾
		if(isDir)
			for(var i = 0; i < dataArr.length; i++)
				if(!(endWith(dataArr[i], '/'))) {
					MyDialog.alert('目录必须以 / 结尾');
					return;
				}
		
		var pathContent = '';
		for(var i = 0; i < dataArr.length; i++) {
			if(0 < i) pathContent += '\n';
			pathContent += dataArr[i];
		}
		if(!(pathContent)) { MyDialog.alert('没有需要刷新的内容！');return; }
		
		var dir = (true === isDir);
		// alert(dir + ' - ' + pathContent);return;
		$.post('/admin/system/aliyun/cdn_refresh.do', {pathContent: pathContent, isDir: dir}, function(json){
			if (json.success) {
				MyDialog.alert('操作成功！');
				$('#refreshListID').tab('show');
			} else MyDialog.alert(json.message);
		}, 'json');
	}

	function showRefreshList() {
		$.getJSON("/admin/system/aliyun/cdn_refreshList", {}, function(json) {
			if (json.success) {
				var htmlTxt = template('listTableTpl', json);
				$('#listTableBody').html(htmlTxt);
				alreadyShowList = true;
			} else {
				MyDialog.alert(json.message);
			}
		});
	}
	function showStatus(statusStr) {
		if('Complete' == statusStr) return '<span class="text-success">成功</span>';
		if('Refreshing' == statusStr) return '<span class="text-warning">刷新中</span>';
		if('Failed' == statusStr) return '<span class="text-danger">刷新失败</span>';
		if('Pending' == statusStr) return '等待刷新';
		return statusStr;
	}

	var alreadyShowList = false;
	$(function() {
		$('#refreshListBtn').on('click', showRefreshList);
		$('#refreshUrlBtn').on('click', subRefreshUrl);
		$('#refreshDirBtn').on('click', subRefreshDir);
		
		template.helper('showStatus', showStatus);
		
		$('#refreshListID').on('shown.bs.tab', function (e) { if(alreadyShowList)return; showRefreshList(); });
	});
</script>
</body>
</html>
