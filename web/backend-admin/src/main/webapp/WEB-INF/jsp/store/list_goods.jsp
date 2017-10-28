<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>商品列表</title>
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
				商品列表
				<small></small>
			</h1>
			<!--
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> 新增商家</a></li>
			</ol>
			-->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<ul id="storeTab" class="nav nav-tabs">
						<li class="active"><a href="#storeListTab" data-toggle="tab">
							商品列表 </a></li>
					</ul>
					<div id="storeTabContent" class="tab-content">
						<!-- tab start -->
						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="storeListTab">
							<div class="box">
								<div class="box-header">
									<div class="col-sm-3">
										<div class="input-group input-group-sm">
											<input type="text" id="keyword" name="keyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
											<input type="hidden" id="storeStatus" name="storeStatus">
									<span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="search(0)">Go!</button>
									</span>
										</div>
									</div>
									<div class="col-sm-9" style="text-align: right;">

									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row col-sm-12" id="listPaginator">
									</div>

									<div class="row">
										<div class="col-sm-12">
											<table id="listTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>ID</th>
													<th>商品名称</th>
													<th>商品头像</th>
													<th>零售价</th>
													<th>批发价</th>
													<th>排序</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
												</thead>
												<script id="listTableTpl" type="text/html">
													{{each object as value i}}
													<tr>
														<td>
															{{value.goodsId}}
														</td>
														<td><a href="/admin/goods/goodsDetail.xhtm?goodsKey={{value.goodsKey}}">{{value.goodsName}}</a></td>
														<td>{{value.cardImg}}</td>
														<td>{{value.phone}}</td>
														<td>
															{{if value.bizType == <%=StoreBizType.NORMAL.getValue()%>}}
															常规类餐厅
															{{/if}}
															{{if value.bizType == <%=StoreBizType.ADVBOOK.getValue()%>}}
															预定类餐厅
															{{/if}}
															{{if value.bizType == 0}}
															未设置
															{{/if}}
														</td>
														<td>
															{{if value.status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
															<span style="color:green;">营业中</span>
															{{/if}}
															{{if value.status == <%=StoreStatus.IN_REST.getValue()%>}}
															<span style="color:#808080;">休业</span>
															{{/if}}
															{{if value.status == <%=StoreStatus.CLOSED.getValue()%>}}
															<span style="color: #c9302c;">结业</span>
															{{/if}}
															{{if value.status == <%=StoreStatus.CHECKING.getValue()%>}}
															<span>审核中</span>
															{{/if}}
															{{if value.status == <%=StoreStatus.COMING_SOON.getValue()%>}}
															<span style="color:blue;">即将开业</span>
															{{/if}}
															{{if value.status == <%=StoreStatus.FROST.getValue()%>}}
															<span style="color:red;">封号</span>
															{{/if}}
														</td>
														<td>{{formatDateTime value.createTime}}</td>
														<td>
															<a target="_blank" href="/admin/store/listStoreOrder.xhtm?storeId={{value.storeId}}">查看订单</a>
															&nbsp; <a href="javascript:void(0)" onclick="getQRCode({{value.storeId}})">二维码</a>
															&nbsp; <a href="javascript:void(0)" onclick="setDate({{value.storeId}})">导出订单</a>
														</td>
														<td>
															{{value.remark}}
														</td>
													</tr>
													{{/each}}
												</script>
											</table>

										</div>
									</div>
									<div class="row col-sm-12" id="listPaginator2">
									</div>
								</div><!-- /.box-body -->
							</div><!-- /.box -->
						</div> <!-- tab end -->

						<div class="tab-pane fade in active"
							 style="background-color: #fff;" id="storeMapTab">
							<div style="width: 100%;height: 600px;" id="mapContainer">

							</div>
						</div>
					</div>
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
<jsp:include page="/include/map-lib.jsp" />
<script type="text/javascript">
	function search(uid) {
		var keyword = $('#keyword').val();
		var storeStatus = $('#storeStatus').val();
		if (typeof storeStatus == 'undefined' || storeStatus == null || storeStatus == '') {
			storeStatus = 0;
		}
		$('#listTable').artPaginate({
			// 获取数据的地址
			url: "/admin/store/query",
			// 显示页码的位置
			paginator: 'listPaginator2',
			//dupPaginator: 'listPaginator2',
			// 模版ID
			tpl: 'listTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params: {'uid': uid, 'keyword': keyword, 'storeStatus': storeStatus},
			logPage: 'list_store_page'
		});
	}

	// 展示二维码
	function getQRCode(storeId) {
		BootstrapDialog.show({
			title: '二维码展示',
			message: '<div style="text-align: center;width: 100%;height: 350px;">'
			+'<img src="/admin/store/qrcode?storeId='+storeId+'" width="350" height="350"/>'
			+'</div>',
			buttons: [{ label: '取消', action: function(dialog) { dialog.close(); } }]
		});
	}
	
	
	//导出Excel
	var exportExcelYes = false;
	function setDate(storeId){
		
		BootstrapDialog.show({
			title:'设置导出时间',
			message:
				'<form class="form-horizontal" id="editForm">' + 
							'<div class="input-group">' +
								'<div class="input-group-addon">' +
									'<i class="fa fa-calendar"></i>' +
								'</div>' +
								'<input type="text" class="form-control pull-right active" name="setTime" id="setTime">'+
							'</div>' +
							'<div class="input-group">' +
								'<input type="checkbox" name="payType" value="<%=PayType.ONLINE.getValue()%>" checked="checked">在线订餐'+
								'<input type="checkbox" name="payType" value="<%=PayType.MAZING_PAY.getValue()%>">米星pay'+
							'</div>' +
			'</form>' +
			'<script type="text/javascript">bindDatePicker()\<\/script>'
				
			,
			buttons: [{
				label: '导出',
				action: function(dialog) { 
					var setTime = $.trim($('#setTime').val());
					if(setTime == ""){
						alert("请选择导出时间！");
						return;						
					}
					
					var payTypes = '';
					$.each($("input[name='payType']:checked"), function(index, element){
						payTypes += $(this).val() + ",";
					});
					if(payTypes.length == 0){
						alert("请选择支付类型!");
						return;						
					}
					
					payTypes = payTypes.substring(0, payTypes.length - 1);
					
					var startTime = $.trim(setTime.substring(0,20));
					var endTime = $.trim(setTime.substring(22));
					window.location.href = encodeURI("/admin/store/queryOrderListForExcel?storeId=" + storeId + "&startTime=" + startTime + "&endTime=" + endTime + "&payTypes="+ payTypes);
					dialog.close(); 
				}
			}, {
				label: '取消',
				action: function(dialog) { dialog.close(); }
			}
			],
			onshow: function(dialogRef) {
				
			},
			onhide: function(dialogRef){
			}
		});
	}
	//导出Excel选择时间
	function bindDatePicker() {
		$("#setTime").daterangepicker({
			timePicker: true, timePickerIncrement: 1, format: 'YYYY-MM-DD HH:mm:ss',
			timePicker12Hour: false,
			minDate:'2016-01-01',
			locale: { cancelLabel: '取消', applyLabel: '确定', fromLabel:'开始日期', toLabel:'结束日期' }
		});
	}

	$(function() {
		var uid = $.getUrlParam('uid');
		var storeStatus = $.getUrlParam('status');
		$('#storeStatus').val(storeStatus);
		if (typeof uid == 'undefined' || uid == null || uid == '') {
			uid = 0;
		}
		search(uid);
	});

	// 地图
	var _map = null;
	var _mapDataDisplayed = false;
	var _readingMapData = false;
	function displayMap() {
		if (_map == null) {
			// 初始化地图
			_map = new AMap.Map('mapContainer', {
				resizeEnable: true,
				zoom: 12
			});
			_map.plugin(["AMap.ToolBar"], function() {
				_map.addControl(new AMap.ToolBar());
			});

			if (_mapDataDisplayed == false) {
				if (_readingMapData) {
					MyDialog.alert('正在读取数据，不要着急，请不要刷新界面……');
				}
				else {
					_readingMapData = true;
					$.getJSON('/admin/store/queryAllStoreRanges', {}, function (json) {
						if (json.success) {
							renderRanges(json.object);
						}
						else {
							MyDialog.alert(json.message);
						}
					});

					// 只读取2000个餐厅
					$.getJSON('/admin/store/query', {'page': 1, 'pageSize' : 2000}, function (json) {
						if (json.success) {
							renderStores(json.object);
						}
						else {
							MyDialog.alert(json.message);
						}
					});
				} // end if
			} // end if (_mapDataDisplayed)
		} // end if (_map == null)
	}

	// 展示
	function renderRanges(list) {
		var center = null;
		var color = ['#7ec95a']; // , '#5ac99a', '#5aa1c9', '#9e5ac9', '#ce881e', '#d7db15', '#db5c15', '#5cef02'

		for (var rangeCount=0; rangeCount < list.length; rangeCount++) {
			var coordList = list[rangeCount];
			var polygonArr = new Array();
			for (var coordCount=0; coordCount < coordList.length; coordCount++) {
				var coord = coordList[coordCount];
				polygonArr.push([coord['lng'], coord['lat']]);
				if (center == null) {
					center = new AMap.LngLat(coord['lng'], coord['lat']);
				}
			}
			var polygon = new AMap.Polygon({
				path: polygonArr,//设置多边形边界路径
				strokeColor: "#FF33FF", //线颜色
				strokeOpacity: 0.2, //线透明度
				strokeWeight: 3,    //线宽
				fillColor: color[rangeCount % color.length], //填充色
				fillOpacity: 0.35//填充透明度
			});
			polygon.setMap(_map);

		} // end for
		// 设置中心点
		_map.setCenter(center);
		_mapDataDisplayed = true;
	}

	// 显示所有餐厅的标记
	function renderStores(list) {
		for (var i=0; i < list.length; i++) {
			var store = list[i];
			// 设置标记
			var marker = new AMap.Marker({
				icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
				position: [store.lng, store.lat]
			});
			marker.setMap(_map);
		}
	}
</script>
</body>
</html>
