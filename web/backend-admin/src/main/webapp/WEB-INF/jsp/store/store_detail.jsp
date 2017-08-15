<%@ page import="com.mazing.core.enums.Role" %>
<%@ page import="com.mazing.core.enums.merchant.StoreBizType" %>
<%@ page import="com.mazing.core.enums.merchant.StoreStatus" %>
<%@ page import="com.mazing.core.enums.BooleanConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/include/meta.html"%>
	<title>餐厅详情</title>
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
				餐厅详情
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/admin/store/list.xhtm"><i class="fa fa-dashboard"></i> 餐厅列表</a></li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<ul id="storeTab" class="nav nav-tabs">
						<li class="active">
							<a href="#baseInfoTab" data-toggle="tab">
								基础信息
							</a>
						</li>
						<li><a href="#payAccountTab" data-toggle="tab">直联</a></li>
						<li><a href="#tagTab" data-toggle="tab">标签（Tag）</a></li>
						<li><a href="#rangeTab" data-toggle="tab">配送信息</a></li>
						<li><a href="#dishTab" data-toggle="tab">菜品信息</a></li>
						<li><a href="#staffTab" data-toggle="tab">员工信息</a></li>
						<li><a href="#auditTab" data-toggle="tab">认证信息</a></li>
						<li><a href="#incomeTab" data-toggle="tab">收入数据</a></li>
					</ul>
					<div id="storeTabContent" class="tab-content">
						<!-- tab start -->
						<div class="tab-pane fade in active" style="background-color: #fff;" id="baseInfoTab">
							<table id="baseInfoTable" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>属性</th>
									<th>数值</th>
									<th>&nbsp;</th>
								</tr>
								</thead>
								<tbody id="baseInfo">

								</tbody>
							</table>
							<div class="row">
								<div class="col-xs-12">
									<p class="text-left">餐厅状态说明：</p>
									<p class="text-left"><strong>休业：</strong>休息中，店主可以自己设为营业，并且可以被用户搜索到，能查看美食但不能购买</p>
									<p class="text-left"><strong>结业：</strong>餐厅倒闭，用户看不到这家餐厅，也不能被搜索到，店主不能设为营业，但可以编辑餐厅的内容</p>
									<p class="text-left"><strong>即将开业：</strong>餐厅可以出现在首页瀑布流，用户能查看美食但不能购买</p>
									<p class="text-left"><strong>封号：</strong>封了这家餐厅，令它人间蒸发，店主可以进入B端查看数据但不能编辑餐厅内容</p>
								</div>
							</div>

						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="payAccountTab">
						<ol class="breadcrumb">
							<li><a href="javascript:void(0);" data-toggle="modal" data-target="#addModal"><i class="fa fa-dashboard"></i>新增直联信息</a></li>
						</ol>
							<table class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>支付类型</th>
									<th>appId</th>
									<th>商户号</th>
									<th>是否开通直联</th>
									<th>创建时间</th>
									<th>修改时间</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody id="payAccountInfo">

								</tbody>
							</table>
						</div>
						<!-- tab end -->
						
						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="tagTab">
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">Tag列表<span class="text-red">（红色表示不在APP上显示）</span></h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12" id="tagContainer"></div>
									</div>

								</div><!-- /.box-body -->
								<div class="box-footer with-border">
									<div class="box-tools pull-right">
										<button class="btn btn-primary" onclick="saveStoreTag()">提交</button>
									</div>
								</div><!-- /.box-header -->
							</div><!-- /.box -->
						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="rangeTab">
							<div id="mapContainer" class="col-xs-12" style="height: 550px;">

							</div>
							<table id="rangeTable" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>ID</th>
									<th>区域</th>
									<th>配送费</th>
									<th>起送金额</th>
									<th>免运费金额</th>
									<th>配送时间</th>
								</tr>
								</thead>
								<tbody id="rangeInfo">

								</tbody>
							</table>
						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="dishTab">
							<table id="dishTable" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>ID/key</th>
									<th>名称</th>
									<th>默认规格</th>
									<th>价格</th>
									<th>每日供应量</th>
									<th>推广标签</th>
									<th>内容</th>
									<th>规格详细</th>
									<th>提前下单时间</th>
									<th>收藏数量</th>
									<th>图片</th>
								</tr>
								</thead>
								<tbody id="dishInfo">

								</tbody>
							</table>
						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="staffTab">
							<table id="staffTable" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>ID</th>
									<th>名字</th>
									<th>昵称</th>
									<th>电话</th>
									<th>角色</th>
									<th>IM ID</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody id="staffInfo">

								</tbody>
							</table>
						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="auditTab">
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">个人认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="storeAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>实名认证</th>
													<th>场所设施认证</th>
													<th>健康认证</th>
													<th>食品安全承诺</th>
													<th>营业执照</th>
													<th>资质证明</th>
												</tr>
												</thead>
												<tbody id="storeAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->

							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">个人认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="userAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>属性</th>
													<th>内容</th>
												</tr>
												</thead>
												<tbody id="userAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->
							
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">场所认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="facilityAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>属性</th>
													<th>内容</th>
												</tr>
												</thead>
												<tbody id="facilityAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->
							
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">健康认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="healthAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>员工姓名</th>
													<th>健康证</th>
													<th>操作</th>
												</tr>
												</thead>
												<tbody id="healthAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->
							
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">营业执照认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="businessLicenseAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>属性</th>
													<th>内容</th>
												</tr>
												</thead>
												<tbody id="businessLicenseAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->
							
							<div class="box box-default box-solid">
								<div class="box-header with-border">
									<h3 class="box-title">资质证明认证信息</h3>
									<div class="box-tools pull-right">
										<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>
								</div><!-- /.box-header -->
								<div class="box-body">
									<div class="row">
										<div class="col-sm-12">
											<table id="cateringCertificationAuditTable" class="table table-bordered table-hover">
												<thead>
												<tr>
													<th>属性</th>
													<th>内容</th>
												</tr>
												</thead>
												<tbody id="cateringCertificationAuditInfo">

												</tbody>
											</table>

										</div>
									</div>

								</div><!-- /.box-body -->
							</div><!-- /.box -->
							
						</div>
						<!-- tab end -->

						<!-- tab start -->
						<div class="tab-pane fade" style="background-color: #fff;" id="incomeTab">
							<table id="incomeTable" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>属性</th>
									<th>数值</th>
									<th>说明</th>
								</tr>
								</thead>
								<tbody id="incomeInfo">

								</tbody>
							</table>
						</div>
						<!-- tab end -->
					</div>

				</div><!-- /.col -->
			</div><!-- /.row -->
			
			<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">新增直联信息</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="mobile" class="control-label">支付类型:</label>
								<select class="form-control" name="payMode" id="payMode">
									<option value="1">支付宝</option>
									<option value="2" selected="selected">微信</option>
								</select>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">appId:</label>
								<input type="text" class="form-control" name="appId" id="appId"></input>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">商户号:</label>
								<input type="text" class="form-control" name="mchId" id="mchId"></input>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">直联开关:</label>
								<select class="form-control" name="isOpen" id="isOpen">
									<option value="0" selected="selected">不开通</option>
									<option value="1">开通</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" onclick="addPayAccount();">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>

			<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="myModalLabel">编辑直联信息</h4>
						</div>
						<div class="modal-body">
							<div class="form-group" style="display: none;">
								<label for="mobile" class="control-label">支付类型:</label>
								<select class="form-control" name="payMode" id="payMode2">
									<option value="1">支付宝</option>
									<option value="2" selected="selected">微信</option>
								</select>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">appId:</label>
								<input type="text" class="form-control" name="appId" id="appId2"></input>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">商户号:</label>
								<input type="text" class="form-control" name="mchId" id="mchId2"></input>
							</div>
							<div class="form-group">
								<label for="mobile" class="control-label">直联开关:</label>
								<select class="form-control" name="isOpen" id="isOpen2">
									<option value="0" selected="selected">不开通</option>
									<option value="1">开通</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" onclick="updatePayAccount();">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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

<!-- 基础信息的模版 -->
<script id="baseInfoTemplate" type="text/html">
	<tr>
		<td>ID</td>
		<td>
			{{storeId}}  &nbsp; | &nbsp;
			{{if status != <%=StoreStatus.FROST.getValue()%>}}
			<button onclick="ban({{storeId}})">封号</button> &nbsp;
			{{/if}}
			{{if status == <%=StoreStatus.FROST.getValue()%>}}
			<button onclick="unban({{storeId}})">解封</button> &nbsp;
			{{/if}}
			&nbsp; &nbsp;
			<button onclick="cleanCache({{storeId}})">清除缓存</button>
			&nbsp; &nbsp;
			<button onclick="updateCSM({{storeId}})">修改客户经理</button>
			&nbsp; &nbsp;
			<button onclick="updateName({{storeId}})">修改餐厅名称</button><br/>
			<div style="padding-top: 10px;">
			<a href="/admin/store/storeTransfer.xhtm?storeId={{storeId}}" title="转让门店给其他人">转让餐厅并修改数据</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a href="javascript:void(0)" onclick="updateAddress({{storeId}})">修改餐厅地址</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a target="_blank" href="/admin/store/listStoreOrder.xhtm?storeId={{storeId}}">查看餐厅订单</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a href="javascript:void(0)" onclick="displayStoreAccount({{storeId}})">提现帐号</a>
			&nbsp; &nbsp; &nbsp; &nbsp;
			<a href="javascript:void(0)" onclick="saveStaff({{storeId}})">添加员工</a>
			</div>
		</td>
		<td rowspan="5" align="center">
			{{if logoPath != null && logoPath != ''}}
				<img src="{{renderImageUrl logoPath}}"/><br/>
			{{/if}}

		</td>
	</tr>
	<tr>
		<td>名称</td>
		<td>{{storeName}} &nbsp; | &nbsp; <span id="starHolder">{{star / 10}}星</span> &nbsp; | &nbsp; 收藏数量：{{favCount}}</td>
	</tr>
	<tr>
		<td>店主</td>
		<td>uid：{{uid}} &nbsp; | &nbsp; 昵称：{{nickname}}</td>
	</tr>
	<tr>
		<td>地址</td>
		<td>{{address}} ({{zip}})  &nbsp; &nbsp; 坐标：({{lng}},{{lat}}) &nbsp; &nbsp; 电话：{{phone}}</td>
	</tr>
	<tr>
		<td>客户经理</td>
		<td>名称：{{csmName}} &nbsp; | &nbsp; 电话：{{csmPhone}}</td>
	</tr>
	<tr>
		<td>餐厅类型</td>
		<td>
			{{if bizType == <%=StoreBizType.NORMAL.getValue()%>}}
			常规类餐厅
			{{/if}}
			{{if bizType == <%=StoreBizType.ADVBOOK.getValue()%>}}
			预定类餐厅
			{{/if}}
			{{if bizType == 0}}
			未设置
			{{/if}}

			&nbsp; &nbsp; &nbsp;
			现金支付：
			{{if supportCash == 1}}
			<span style="color:green;">支持</span>
			{{/if}}
			{{if supportCash != 1}}
			<span style="color:#808080;">不支持</span>
			{{/if}}
		</td>
		<td>
			<p>修改瀑布流图片</p>
			<p><input type="file" id="upfile"></p>
			<p><input type="button" id="upJQuery" value="上传" onclick="uploadTopicImg({{storeId}})"></p>
		</td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td>{{formatDateTime createTime}}</td>
		<td rowspan="6" align="center">
			{{if topicImg != null && topicImg != ''}}
			<img id="topicImg" src="{{renderImageUrl topicImg}}"/><br/>
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>简介</td>
		<td>
			{{content}}
		</td>
	</tr>
	<tr>
		<td>订单特征值</td>
		<td>
			{{orderFeatureContent}}
		</td>
	</tr>
	<tr>
		<td>状态</td>
		<td>
			<!-- 见StoreStatus -->
			{{if status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
			<span style="color:green;">营业中</span>
			{{/if}}
			{{if status == <%=StoreStatus.IN_REST.getValue()%>}}
			<span style="color:#808080;">休业</span>
			{{/if}}
			{{if status == <%=StoreStatus.CLOSED.getValue()%>}}
			<span style="color: #c9302c;">结业</span>
			{{/if}}
			{{if status == <%=StoreStatus.CHECKING.getValue()%>}}
			<span>审核中</span>
			{{/if}}
			{{if status == <%=StoreStatus.COMING_SOON.getValue()%>}}
			<span style="color:blue;">即将开业</span>
			{{/if}}
			{{if status == <%=StoreStatus.FROST.getValue()%>}}
			<span style="color:red;">封号</span>
			{{/if}}
			&nbsp;
			{{if status == <%=StoreStatus.IN_BUSSINESS.getValue()%>}}
			<button onclick="openWin({{storeId}}, <%=StoreStatus.IN_REST.getValue()%>, '休业原因')">休业</button> &nbsp;
			<button onclick="openWin({{storeId}}, <%=StoreStatus.CLOSED.getValue()%>, '结业原因')">结业</button> &nbsp;
			{{/if}}
			{{if status == <%=StoreStatus.IN_REST.getValue()%> || status == <%=StoreStatus.CLOSED.getValue()%> || status==<%=StoreStatus.CHECKING.getValue()%>}}
			<button onclick="modifyStoreStatus({{storeId}}, <%=StoreStatus.IN_BUSSINESS.getValue()%>, '')">恢复营业</button> &nbsp;
			<button onclick="modifyStoreStatus({{storeId}}, <%=StoreStatus.COMING_SOON.getValue()%>, '')">即将开业</button> &nbsp;
			{{/if}}
			{{if status == <%=StoreStatus.COMING_SOON.getValue()%>}}
			<button onclick="modifyStoreStatus({{storeId}}, <%=StoreStatus.IN_BUSSINESS.getValue()%>, '')">恢复营业</button> &nbsp;
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>备注</td>
		<td>
			{{remark}}
		</td>
	</tr>
	
	<tr>
		<td style="color:red;">Mazing Pay 状态</td>
		<td>
			{{if supportMazingPay == <%=BooleanConstants.TRUE %>}}
				<span style="color:green;">已开通</span>
					&nbsp;&nbsp;	
				<button onclick="changeMazingPayStatus({{storeId}}, <%=BooleanConstants.FALSE%>)">关闭Mazing Pay</button> &nbsp;
			{{/if}}
			
			{{if supportMazingPay == <%=BooleanConstants.FALSE %>}}
				<span style="color:red;">未开通</span>
					&nbsp;&nbsp;
				<button onclick="changeMazingPayStatus({{storeId}}, <%=BooleanConstants.TRUE%>)">开启Mazing Pay</button> &nbsp;
			{{/if}}
		</td>
	</tr>

	<tr>
		<td>营业时间</td>
		<td>
			{{openHours}}
		<br/>{{openHoursFormat}}
			{{if acceptOrderDeadlineText != null}}
			<br/>截单时间：{{acceptOrderDeadlineText}}
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>配送时间</td>
		<td>
			{{serviceHours}}
			<br/>{{serviceHoursFormat}}
		</td>
	</tr>


	<tr>
		<td style="color:red;">顺丰专送</td>
		<td>
			{{if isSupportSf == <%=BooleanConstants.TRUE %>}}
				<span style="color:green;">已开通</span>
					&nbsp;&nbsp;	
				<button onclick="changeSupportSfStatus({{storeId}}, <%=BooleanConstants.FALSE%>)">关闭顺丰专送</button> &nbsp;
			{{/if}}
			
			{{if isSupportSf == <%=BooleanConstants.FALSE %>}}
				<span style="color:red;">未开通</span>
					&nbsp;&nbsp;
				<button onclick="changeSupportSfStatus({{storeId}}, <%=BooleanConstants.TRUE%>)">开启顺丰专送</button> &nbsp;
			{{/if}}

			<input type="hidden" id="isSupportSfHidden" value="{{isSupportSf}}" />
		</td>

		<td style="color:green;">
		餐厅类型: <select id="cuisineType" name="cuisineType" class="form-control" onchange="selectCuisineType({{storeId}}, this.options[this.options.selectedIndex].value)" >
		
		{{each sfFeeConfigList as feeConfig i}}
			
			<option value="{{feeConfig.cuisineType}}">{{feeConfig.descrip}}</option>
			
		{{/each}}
			
				</select>
		配送费：<span id="deliveryFee"></span>元		
		
		<input id="dbCuisineType" value="{{cuisineType}}" type="hidden" />
		<input id="dbSfDeliveryFee"  type="hidden" />
		<input id="cuisineTypeHidden"  type="hidden" />

		</td>

		<td style="width:100px">
			米星补助(元)： <input id="mazingBear" name="mzBear" value="{{cent2yuan storeSf.mzBear}}" />
			商家承担(元)： <input id="merchantBear" name="merchantBear" value="{{cent2yuan storeSf.merchantBear}}" />
			用户承担(元)： <input id="userBear" name="userBear" value="{{cent2yuan storeSf.userBear}}" />
		</td>
		
		<td style="color:blue;">
			<input type="button" id="commitSfDataButton" value="提交" onclick="commitSfData({{storeId}})" />
		</td>

	</tr>

	<tr>
		<td>温馨提示</td>

		<td >
		<!--	中文提示： <input id="warmTipZh" name="warmTipZh" value="{{warmTipZh}}" maxlength=128  style="margin: 0px; width: 390px; height: 82px;"/> -->
			</br>
		<!--	英文提示： <input id="warmTipEn" name="warmTipEn" value="{{warmTipEn}}"  maxlength=128  style="margin: 0px; width: 390px; height: 82px;"/>-->

			中文提示： <textarea id="warmTipZh" name="warmTipZh"  maxlength=888  style="margin: 0px; width: 390px; height: 82px;" >{{warmTipZh}} </textarea>
			</br>
			英文提示： <textarea id="warmTipEn" name="warmTipEn" maxlength=1000  style="margin: 0px; width: 390px; height: 82px;" >{{warmTipEn}}</textarea>
			
	
			<textarea  style="display:none;"  id="warmTipZhHidden"  value="{{warmTipZh}}" />
			<textarea   style="display:none;" id="warmTipEnHidden"  value="{{warmTipEn}}" />
		</td>
		<td style="color:blue;">
			<input type="button" id="commitWarmTip" value="提交" onclick="commitWarmTipData({{storeId}})" />
		</td>
	</tr>

<tr>
			<td>
						<p>推荐首页展示餐厅主图配置</p>
			</td>
			<td>
						<img id="imgPreview" width="200" src="" />
						<input id="picInput" callback="previewImg" type="file" value="选择图片..." />
						</br>
						<input  class="btn btn-success" name="upShow" value="保存更改" onclick="upIndexShowImg({{storeId}})" />
						<input  class="btn btn-success" name="delShow" value="清除" onclick="delIndexShowImg({{storeId}})" />
						<input id="imgID" name="img" type="hidden" />

						<p class="text-blue">新上传的图片将会原图保存</p>
						<p class="text-red">建议logo图为 346*346px， 正方形，请严格按要求上传</p>
			</td>
</tr>
</script>

<!-- 直联的模版 -->
<script id="payAccountTemplate" type="text/html">
	{{each object as value i}}
	<tr>
		<td>
			{{if value.payMode == 1}}
				支付宝
			{{/if}}
			{{if value.payMode == 2}}
				微信
			{{/if}}
		</td>
		<td>
			{{value.appId}}
		</td>
		<td>{{value.mchId}}</td>
		<td>
			{{if value.isOpen == 1}}
				已开通
			{{/if}}
			{{if value.isOpen == 0}}
				未开通
			{{/if}}
		</td>
		<td>{{formatDateTime value.createTime}}</td>
		<td>{{formatDateTime value.updateTime}}</td>
		<td>
			<a href="javascript:void(0);" onClick="openWin2({{value.payMode}}, '{{value.appId}}', '{{value.mchId}}', {{value.isOpen}});">修改</a>
		</td>
	</tr>
	{{/each}}
</script>

<!-- 收入数据模版 ten 2015/12/17 -->
<script id="incomeTemplate" type="text/html">
	<tr style="font-weight: bold;">
		<td>餐厅总赚钱</td>
		<td>{{cent2yuan earnedAmount}} 元</td>
		<td>总赚钱=下面的 A + B + C + D</td>
	</tr>
	<tr>
		<td>（A）已经提现成功的金额</td>
		<td>{{cent2yuan historyWithdraw}} 元</td>
		<td>已经成功打到商家的支付宝帐号</td>
	</tr>
	<tr>
		<td>（B）正在申请提现的金额</td>
		<td>{{cent2yuan inProgressWithdraw}} 元</td>
		<td>商家正在申请提现的金额</td>
	</tr>
	<tr>
		<td>（C）可提现金额</td>
		<td>{{cent2yuan withdrawAmount}} 元</td>
		<td>已经入账的，可以提现但还没申请提现的金额</td>
	</tr>
	<tr>
		<td>（D）待入账金额</td>
		<td>{{cent2yuan settledAmount}} 元</td>
		<td>用户已经确认收货，还没进入可提现的金额</td>
	</tr>
	<tr>
		<td>（E）交易中金额</td>
		<td>{{cent2yuan dealingAmount}} 元</td>
		<td>正在进行中的订单，用户还没确认收货的金额</td>
	</tr>
</script>

<!-- 配送范围的模版 -->
<script id="rangeTemplate" type="text/html">
	{{each rangeList as value i}}
	<tr>
		<td>{{value.rangeId}}</td>
		<td style="background-color: {{renderRangeColor i}};">{{value.name}}</td>
		<td>{{cent2yuan value.money}}</td>
		<td>{{cent2yuan value.minimumMoney}}</td>
		<td>
			{{if value.fullFreeFreight <= 0}}
			无
			{{/if}}
			{{if value.fullFreeFreight > 0}}
			{{cent2yuan value.fullFreeFreight}}
			{{/if}}
		</td>
		<td>{{value.minute}} 分钟</td>

	</tr>
	{{/each}}
</script>

<!-- 菜品的模版 -->
<script id="dishTemplate" type="text/html">
	{{each dishList as value i}}
	<tr>
		<td>{{value.dishId}} / {{value.dishKey}}</td>
		<td>
			{{if value.status == 1}}
			<span alt="已上架" style="color:green">{{value.dishName}}</span>
			{{/if}}
			{{if value.status != 1}}
			<span alt="已下架" style="color:#808080">{{value.dishName}}</span>
			{{/if}}
		</td>
		<td>{{value.spec}}</td>
		<td>{{cent2yuan value.price}} 元</td>
		<td>{{value.dailyNum}}</td>
		<td>{{value.promoteTag}}</td>
		<td>
			<a href="#" rel="tooltip" data-placement="right" title="{{renderTips value.content}}">TIPS</a>
		</td>
		
		<td>
			<a href="#" rel="tooltip" data-placement="right" title="{{renderTips value.specLabel}}">详情</a>
		</td>
			
		<td>{{value.advanceMinute}} 分钟</td>
		<td>{{value.favCount}}</td>
		
		<td><a target="_blank" href="{{renderImageUrl value.cardImg}}"><img width="50" src="{{renderImageUrl value.cardImg}}"/></a></td>
	</tr>
	{{/each}}
</script>

<!-- 员工列表的模版 
		目前去除以下角色：
			Role.ADMINISTRATOR 管理员
			Role.CUSTOMER_SERVICE 客服
			Role.DISH_PREPARATOR 配菜员
			Role.KITCHEN_ADMIN 厨房管理员
			Role.PACKER 打包员
			Role.COOK 厨师

-->
<script id="staffTemplate" type="text/html">
	{{each staffList as value i}}
	<tr>
		<td>{{value.uid}}</td>
		<td>{{value.name}}</td>
		<td>{{value.nickname}}</td>
		<td>{{value.phone}}</td>
		<td>
			{{if value.role == <%=Role.BOSS.getValue()%>}}店主{{/if}}
			{{if value.role == <%=Role.SHOPKEEPER.getValue()%>}}店长{{/if}}
			{{if value.role == <%=Role.ORDER_ACCEPTER.getValue()%>}}接单员{{/if}}
			{{if value.role == <%=Role.DELIVER.getValue()%>}}配送员{{/if}}
			{{if value.role == <%=Role.CUSTOMER_SERVICE.getValue()%>}}客服{{/if}}
		</td>

		<td>{{value.imId}}</td>
		<td>{{formatDateTime value.createTime}}</td>
		<td><a href="javascript:void(0)" onclick="updateStaff('{{value.name}}', {{value.uid}}, {{value.role}}, {{value.storeId}}, {{value.imId}})">修改</a></td>
	</tr>
	{{/each}}
</script>

<!-- 餐厅认证的模版 -->
<script id="userAuditTemplate" type="text/html">
	<tr>
		<td>
			基本内容
		</td>
		<td>
			姓名：{{realname}}，电话：{{phone}}，证件号：{{identity}}
		</td>
	</tr>
	<tr>
		<td>证件照</td>
		<td>
			{{if iFace != null && iFace != ''}}
			<img src="{{renderImageUrl iFace}}" width="200px" height="200px"/>
			{{/if}}
			{{if iBack != null && iBack != ''}}
			<img src="{{renderImageUrl iBack}}" width="200px" height="200px"/>
			{{/if}}
			{{if iHold != null && iHold != ''}}
			<img src="{{renderImageUrl iHold}}" width="200px" height="200px"/>
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>
			操作
		</td>
		<td>
			{{if status == 1}}
			{{modifyAuditButton 'user' -1}}
			{{/if}}
			{{if status == 0 }}
				{{if iFace != '' && iBack != '' && iHold != ''}}
					<!-- 资料齐全 -->
					{{modifyAuditButton 'user' -1}}
				{{/if}}
				{{if iFace == '' || iBack == '' || iHold == ''}}
					&nbsp;<span style="color:#ff0000;">未提交</span>
				{{/if}}
			{{/if}}
			{{if status == 2 }}
			&nbsp;<span style="color:green;">已通过</span>
			{{/if}}
			{{if status == 3 }}
			&nbsp;<span style="color:#ff0000;">未通过，等待用户再次提交</span>
			{{/if}}
		</td>
	</tr>
</script>


<!-- 场所认证的模版 -->
<script id="facilityAuditTemplate" type="text/html">
	<tr>
		<td>
			餐厅地址
		</td>
		<td>
			{{kitchenAddress}}
		</td>
	</tr>
	<tr>
		<td>
			餐厅类型
		</td>
		<td>
			{{kitchenType}}
		</td>
	</tr>
	<tr>
		<td>
			餐厅大小
		</td>
		<td>
			{{scale}}
		</td>
	</tr>
	<tr>
		<td>场所图片</td>
		<td>
			{{if pics != null && pics != ''}}
			{{sliptImageUrl pics}}
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>
			操作
		</td>
		<td>
			{{if status == 1 }}
			{{modifyAuditButton 'facility' -1}}
			{{/if}}
			{{if status == 0 }}
				{{if pics != null && pics != ''}}
					{{modifyAuditButton 'facility' -1}}
				{{/if}}
				{{if pics == null || pics == ''}}
				&nbsp;<span style="color:#ff0000;">未提交</span>
				{{/if}}
			{{/if}}
			{{if status == 2 }}
			&nbsp;<span style="color:green;">已通过</span>
			{{/if}}
			{{if status == 3 }}
			&nbsp;<span style="color:#ff0000;">未通过，等待用户再次提交</span>
			{{/if}}
		</td>
	</tr>
</script>


<!-- 健康认证的模版 -->
<script id="healthAuditTemplate" type="text/
tml">
	{{each healthAudit as value i}}
	<tr>
		<td>
			{{value.realname}}
		</td>
		<td><img src="{{renderImageUrl value.hPic1}}" width="200px" height="200px"/></td>
		<td>
			{{if value.status == 1 }}
			{{modifyAuditButton 'health' value.hid}}
			{{/if}}
			{{if value.status == 0 }}
			&nbsp;<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if value.status == 2 }}
			&nbsp;<span style="color:green;">已通过</span>
			{{/if}}
			{{if value.status == 3 }}
			&nbsp;<span style="color:#ff0000;">未通过，等待用户再次提交</span>
			{{/if}}
		</td>
	</tr>
	{{/each}}
</script>

<!-- 营业执照认证的模版 -->
<script id="businessLicenseAuditTemplate" type="text/html">
	<tr>
		<td>营业执照图片</td>
		<td>
			{{if businessLicense != null && businessLicense != ''}}
			{{sliptImageUrl businessLicense}}
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>
			操作
		</td>
		<td>
			{{if status == 1 }}
			{{modifyAuditButton 'businessLicense' -1}}
			{{/if}}
			{{if status == 0 }}
			&nbsp;<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if status == 2 }}
			&nbsp;<span style="color:green;">已通过</span>
			{{/if}}
			{{if status == 3 }}
			&nbsp;<span style="color:#ff0000;">未通过，等待用户再次提交</span>
			{{/if}}
			<!-- 如果原件字段为空，则显示加水印-->
			{{if businessLicense != '' && (businessLicenseSource == '' || businessLicenseSource == null) }}
				<button onclick="toImageMark('{{storeId}}', 'businessLicense')">添加水印</button>
			{{/if}}
		</td>
	</tr>
</script>

<!-- 资质证明认证的模版 -->
<script id="cateringCertificationAuditTemplate" type="text/html">
	<tr>
		<td>资质证明图片</td>
		<td>
			{{if cateringCertification != null && cateringCertification != ''}}
			{{sliptImageUrl cateringCertification}}
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>
			操作
		</td>
		<td>
			{{if status == 1 }}
			{{modifyAuditButton 'cateringCertification' -1}}
			{{/if}}
			{{if status == 0 }}
			&nbsp;<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if status == 2 }}
			&nbsp;<span style="color:green;">已通过</span>
			{{/if}}
			{{if status == 3 }}
			&nbsp;<span style="color:#ff0000;">未通过，等待用户再次提交</span>
			{{/if}}
			<!-- 如果原件字段为空，则显示加水印-->
			{{if cateringCertification != '' && (cateringCertificationSource == '' || cateringCertificationSource == null) }}
				<button onclick="toImageMark('{{storeId}}', 'cateringCertification')">添加水印</button>
			{{/if}}
		</td>
	</tr>
</script>

<!-- 个人认证的模版 -->
<script id="storeAuditTemplate" type="text/html">
	<tr>
		<td>
			{{if userAudit == null}}
			<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if userAudit != null}}
				{{if userAudit.status == 0}}
				<span style="color:#ff0000;">未提交</span>
				{{/if}}
				{{if userAudit.status == 1}}
				<span style="color:#ff0000;">审核中</span>
				{{/if}}
				{{if userAudit.status == 2}}
				<span style="color:green;">已通过</span>
				{{/if}}
				{{if userAudit.status == 3}}
				<span style="color:#ff0000;">未通过</span>
				{{/if}}
			{{/if}}
		</td>
		<td>
			{{if facilityAudit == null}}
			<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if facilityAudit != null}}
				{{if facilityAudit.status == 0}}
				<span style="color:#ff0000;">未提交</span>
				{{/if}}
				{{if facilityAudit.status == 1}}
				<span style="color:#ff0000;">审核中</span>
				{{/if}}
				{{if facilityAudit.status == 2}}
				<span style="color:green;">已通过</span>
				{{/if}}
				{{if facilityAudit.status == 3}}
				<span style="color:#ff0000;">未通过</span>
				{{/if}}
			{{/if}}
		</td>
		<td>
			{{getHealthStatus healthAudit}}
		</td>
		<td>
			<span style="color:green;">已通过</span>
		</td>

		<td>
			{{if businessLicenseAudit == null}}
			<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if businessLicenseAudit != null}}
				{{if businessLicenseAudit.status == 0}}
				<span style="color:#ff0000;">未提交</span>
				{{/if}}
				{{if businessLicenseAudit.status == 1}}
				<span style="color:#ff0000;">审核中</span>
				{{/if}}
				{{if businessLicenseAudit.status == 2}}
				<span style="color:green;">已通过</span>
				{{/if}}
				{{if businessLicenseAudit.status == 3}}
				<span style="color:#ff0000;">未通过</span>
				{{/if}}
			{{/if}}
		</td>

		<td>
			{{if cateringCertificationAudit == null}}
			<span style="color:#ff0000;">未提交</span>
			{{/if}}
			{{if cateringCertificationAudit != null}}
				{{if cateringCertificationAudit.status == 0}}
				<span style="color:#ff0000;">未提交</span>
				{{/if}}
				{{if cateringCertificationAudit.status == 1}}
				<span style="color:#ff0000;">审核中</span>
				{{/if}}
				{{if cateringCertificationAudit.status == 2}}
				<span style="color:green;">已通过</span>
				{{/if}}
				{{if cateringCertificationAudit.status == 3}}
				<span style="color:#ff0000;">未通过</span>
				{{/if}}
			{{/if}}
		</td>
	</tr>
</script>

<!-- tag的模版 -->
<script id="storeTagTemplate" type="text/html">
	{{each object as value i}}
	<div class="row margin" style="border-bottom: 1px solid #ddd;">
		<div class="col-xs-3 {{if value.appShow != 1}}text-red{{/if}}">{{value.categoryName}}</div>
		<div class="col-xs-9 checkbox {{if value.appShow != 1}}text-red{{/if}}">
			{{each value.tags as tag j}}
				<label  {{if tag.tagId==8888}} style="display:none;"{{/if}} ><input type="checkbox" name="storeTag" value="{{tag.tagId}}" {{if tag.checked}}checked{{/if}} >{{tag.tagName}}</label> &nbsp; &nbsp; &nbsp; &nbsp;
			{{/each}}
		</div>
	</div>
	{{/each}}
</script>
<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<jsp:include page="/include/map-lib.jsp" />
<script type="text/javascript">
	// 图片地址
	var imageDomain = '';

	var uid ; 
	// 查询门店的信息
	function query() {
		var storeId = $.getUrlParam("storeId");

		if (storeId == null || storeId == '' || storeId == '0') {
			MyDialog.alert('请在门店列表界面选择一家门店进行操作');
		}
		else {
			$.getJSON('/admin/store/storeDetail', {'storeId': storeId}, function (json) {
				if (json.success) {
					
					uid = json.object.store.uid;
					// 模版函数：根据不同的审核模块，为审核操作赋予不同的参数
					template.helper('modifyAuditButton', function (type,hid) {
						var html = "&nbsp;<button onclick=\"updateAuditStatus("+storeId+","+uid+","+hid+",'"+type+"',2)\">通过</button> "+
						"&nbsp;&nbsp;<button onclick=\"updateAuditStatus("+storeId+","+uid+","+hid+",'"+type+"',3)\">拒绝</button>";
						return html;
					});
					
					render(json.object);
					//设置顺丰配送是否可编辑
					setSfModifyStatus();
					
					//读取直联列表
					loadPayAccount(uid);
				}
				else {
					MyDialog.alert(json.message);
				}
			});

			// 查询收入数据
			queryIncome(storeId);

			// 查询餐厅tag
			loadStoreTag(storeId);
			
			
		}
	}

	// 查询收入信息 2015/12/17 ten
	function queryIncome(storeId) {
		$.getJSON('/admin/store/storeIncome', {'storeId': storeId}, function (json) {
			if (json.success) {
				// 使用模版显示收入信息
				var htmlTxt = template('incomeTemplate', json.object);
				$('#incomeInfo').html(htmlTxt);
			}
			else {
				MyDialog.alert(json.message);
			}
		});
	}
	
	//修改餐厅温馨提示信息
	function commitWarmTipData(storeId){
		
		var warmTipZh = $.trim($("#warmTipZh").val());
		var warmTipEn = $.trim($("#warmTipEn").val());
		
		
		var warmTipZhHidden = $.trim($("#warmTipZhHidden").val());
		var warmTipEnHidden = $.trim($("#warmTipEnHidden").val());
		
		//之前为空，现在提交还是空，不予提交
		//if(warmTipZh=='' && warmTipEn=='' && warmTipZhHidden=='' && warmTipEnHidden==''){
			//MyDialog.alert("请输入温馨提示的内容");
		//	return;
		//}
		
		$.post('/admin/store/modifyWarmTip.do', {
			'storeId': storeId,
			'warmTipZh' : warmTipZh,
			'warmTipEn': warmTipEn
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				// 重新读一次门店信息
				query();
			}
		}, 'json');
		
	}

	// 修改门店状态
	function modifyStoreStatus(storeId, status, remark) {
		$.post('/admin/store/modifyStatus.do', {
			'storeId': storeId,
			'status': status,
			'remark': remark
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				// 重新读一次门店信息
				query();
			}
		}, 'json');
	}
	//设置顺丰配送信息的可编辑状态
	function setSfModifyStatus(){
		//
		var isSupportSf = $("#isSupportSfHidden").val();
		
		//alert("isSupportSf="+isSupportSf);
		if(isSupportSf ==  <%=BooleanConstants.TRUE%>){
			$("#cuisineType").attr("disabled", false);
			$("#mazingBear").attr("disabled", false);
			$("#merchantBear").attr("disabled", false);
			$("#userBear").attr("disabled", false);
			$("#commitSfDataButton").attr("disabled", false);
		}
		else{
			$("#cuisineType").attr("disabled", true);
			$("#mazingBear").attr("disabled", true);
			$("#merchantBear").attr("disabled", true);
			$("#userBear").attr("disabled", true);
			$("#commitSfDataButton").attr("disabled", true);
		}
	}
	//相乘
	function multiply(from, multi)  
	{   
	    var m=0,s1=from.toString(),s2=multi.toString();   
	    try{m+=s1.split(".")[1].length}catch(e){}   
	    try{m+=s2.split(".")[1].length}catch(e){}   
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
	} 
	
	//提交 顺丰费用分担信息
	function commitSfData(storeId){
		
		var mzBear = $.trim($("#mazingBear").val());
		var merchantBear = $.trim($("#merchantBear").val());
		var userBear = $.trim($("#userBear").val());
		
		if(!$.isNumeric(mzBear)){
			MyDialog.alert("输入的米星补助费用金额不正确，请重新输入");
			return;
		}
		if(!$.isNumeric(merchantBear)){
			MyDialog.alert("输入的商家承担费用金额不正确，请重新输入");
			return;
		}
		if(!$.isNumeric(userBear)){
			MyDialog.alert("输入的用户承担费用金额不正确，请重新输入");
			return;
		}
		
		//alert("mzBear="+mzBear+", merchantBear="+merchantBear+",userBear="+userBear);
		
		mzBear = multiply(mzBear,100);
		merchantBear = multiply(merchantBear,100);
		userBear = multiply(userBear,100);
		
		if(mzBear!=0){
			var mzBearStr = mzBear.toString();
			if(mzBearStr.indexOf(".")!=-1){
				MyDialog.alert("输入的米星补助费用金额精度不正确，最多只能有两位小数，请重新输入");
				return;				
			}
			
		}
		if(merchantBear!=0){
			var merchantBearStr = merchantBear.toString();
			if(merchantBearStr.indexOf(".")!=-1){
				MyDialog.alert("输入的商家承担费用金额精度不正确，最多只能有两位小数，请重新输入");
				return;				
			}
		}
		if(userBear!=0){
			var userBearStr = userBear.toString();
			if(userBearStr.indexOf(".")!=-1){
				MyDialog.alert("输入的用户承担费用金额精度不正确，最多只能有两位小数，请重新输入");
				return;				
			}
		}
		
		
		
		var total = mzBear+ merchantBear + userBear;
		
		//alert("total="+total);
		var cuisineType = $("#cuisineTypeHidden").val();
		
		var dbSfDeliveryFee = $("#dbSfDeliveryFee").val();
		
		//alert("mzBear="+mzBear+", merchantBear="+merchantBear+",userBear="+userBear+", total="+total+", dbSfDeliveryFee="+dbSfDeliveryFee+",cuisineTypeHidden="+cuisineType);
		
		if(total != dbSfDeliveryFee){
			MyDialog.alert("输入金额总和不正确，请重新输入");
			return;
		}
		
		
		BootstrapDialog.show({
			title: '修改米星配送费用',
			message: '你确认要修改米星配送的费用配置吗？',
			buttons: [{
				label: '确认',
				action: function(dialog) {
					dialog.close();
					$.post('/admin/store/modifySfConfigData.do', {
						'storeId': storeId,
						'userBear': userBear,
						'mzBear': mzBear,
						'merchantBear': merchantBear,
						'cuisineType': cuisineType
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							//change button name
						}
					}, 'json');/**/
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});
		
		
		
		
	}
	
	
	//选择顺丰配送的餐点类型
	function selectCuisineType(storeId, type){
		
		//显示对应的配送费用
		showDeliveryFee(type);
		
		/*
		$.post('/admin/store/modifyStoreSfCuisineType.do', {
			'storeId': storeId,
			'cuisineType': type
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				//change button name
			}
		}, 'json');*/
		
	}
	
	//在选择餐点类型时,设置需要显示的 对应的配送费用
	function showDeliveryFee(cuisineType){
		//数据库单位是分,该处显示时是元
		var dbSfDeliveryFee = 0;
		
		if(cuisineType>0){
			
			$.getJSON('/admin/store/getSfFeeConfigById', {'cuisineType': cuisineType}, function (json) {
				if (json.success) {
					
					dbSfDeliveryFee = json.object.fee;
					
					$("#deliveryFee").html(cent2yuan(dbSfDeliveryFee));//设置需要显示的费用值
					$("#dbSfDeliveryFee").val(dbSfDeliveryFee);//存储费用值
				
					//alert("cuisineType="+cuisineType+", dbSfDeliveryFee="+dbSfDeliveryFee);
				}
				else {
					MyDialog.alert(json.message);
				}
			});
			
		}else{
			
			$("#deliveryFee").html(cent2yuan(dbSfDeliveryFee));//设置需要显示的费用值
			$("#dbSfDeliveryFee").val(dbSfDeliveryFee);//存储费用值
			
		}
		
		$("#cuisineTypeHidden").val(cuisineType);//存储餐点类型
	
		
		/*
		if(dbCuisineType>=1 && dbCuisineType<=3){
			dbSfDeliveryFee = 11;
		}else if(dbCuisineType>=4 && dbCuisineType<=7){
			dbSfDeliveryFee = 7;
		}else if(dbCuisineType>=8 && dbCuisineType<=9){
			dbSfDeliveryFee = 25;
		}else if(dbCuisineType==10){
			dbSfDeliveryFee = 18;
		}*/
		
		
	}
	
	//  开启，关闭顺丰配送
	function changeSupportSfStatus(storeId, status){
		$.post('/admin/store/modifySupportSfStatus.do', {
			'storeId': storeId,
			'status': status
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				//设置是否开启顺丰配送 状态值
				$("#isSupportSfHidden").val(status);
				// 重新读一次门店信息
				query();
			}
		}, 'json');
	}
	
	function changeMazingPayStatus(storeId, status){
		$.post('/admin/store/modifyMazingPayStatus.do', {
			'storeId': storeId,
			'status': status
		}, function (json) {
			MyDialog.alert(json.message);
			if (json.success) {
				// 重新读一次门店信息
				query();
			}
		}, 'json');
	}

	// 用模板显示用户的信息
	function render(value) {
		var store = value.store;
		// 图片域名
		imageDomain = value.imgDomain;
		if (imageDomain.substring(imageDomain.length - 1) != '/') {
			imageDomain = imageDomain + '/';
		}

		// 使用模版显示门店信息
		var htmlTxt = template('baseInfoTemplate', store);
		$('#baseInfo').html(htmlTxt);
		
		//顺丰配送，米星餐厅的餐点类型
		var dbCuisineType = $("#dbCuisineType").val();
		var oObj = $("#cuisineType").get(0).selectedIndex=dbCuisineType-1;
		//设置需要显示的配送费
		showDeliveryFee(dbCuisineType);
		
		//设置推荐首页配置图
		var indexShowImg = store.indexShowImg;
		if(indexShowImg)
			$("#imgPreview").attr("src", imageDomain + indexShowImg);
		
		htmlTxt = template('rangeTemplate', value);
		$('#rangeInfo').html(htmlTxt);
		htmlTxt = template('dishTemplate', value);
		$('#dishInfo').html(htmlTxt);
		htmlTxt = template('staffTemplate', value);
		$('#staffInfo').html(htmlTxt);

		// 认证信息，不一定存在
		var storeAudit = value.storeAudit;
		if (storeAudit) {
			htmlTxt = template('storeAuditTemplate', value);
			$('#storeAuditInfo').html(htmlTxt);
		}
		var userAudit = value.userAudit;
		if (userAudit!=null) {
			htmlTxt = template('userAuditTemplate', userAudit);
			$('#userAuditInfo').html(htmlTxt);
		}

		var facilityAudit = value.facilityAudit;
		if (facilityAudit!=null) {
			htmlTxt = template('facilityAuditTemplate', facilityAudit);
			$('#facilityAuditInfo').html(htmlTxt);
		}
		
		var healthAudit = value.healthAudit;
		if (healthAudit!=null) {
			htmlTxt = template('healthAuditTemplate', value);
			$('#healthAuditInfo').html(htmlTxt);
		}
		
		var businessLicenseAudit = value.businessLicenseAudit;
		if (businessLicenseAudit!=null) {
			htmlTxt = template('businessLicenseAuditTemplate', businessLicenseAudit);
			$('#businessLicenseAuditInfo').html(htmlTxt);
		}
		
		var cateringCertificationAudit = value.cateringCertificationAudit;
		if (cateringCertificationAudit!=null) {
			htmlTxt = template('cateringCertificationAuditTemplate', cateringCertificationAudit);
			$('#cateringCertificationAuditInfo').html(htmlTxt);
		}

		// 菜品的介绍用 tooltip 的形式展示
		$('#dishInfo').tooltip({
			selector: "a[rel=tooltip]"
		});

		
		$("#picInput").change(uploadPic);
		
		// 显示地图
		displayRange(value.rangeList);
	}

	// 修改门店状态：封号
	function ban(storeId) {
		BootstrapDialog.show({
			title: '门店封号',
			message: $('<input type="text" name="banReason" class="form-control" placeholder="请输入封号原因"/>'),
			buttons: [{
				label: '封号',
				cssClass: 'btn-primary',
				hotkey: 13, // Enter.
				action: function(dialogRef) {
					var reason = $.trim($('input[name=banReason]').val());
					if (reason == '') {
						MyDialog.alert('请输入封号原因！');
					}
					else {
						dialogRef.close();
						$.getJSON("/admin/store/ban", {'storeId':storeId, 'reason': reason}, function(json) {
							MyDialog.alert(json.message);
							// reload 门店信息
							query();
						});
					}
				}
			}, {
				label: '取消',
				action: function(dialogRef) {
					dialogRef.close();
				}
			}]
		});

	}

	// 修改门店状态：解封
	function unban(storeId) {
		BootstrapDialog.show({
			title: '解封门店',
			message: '你确认要解封这家门店吗？',
			buttons: [{
				label: '解封',
				action: function(dialog) {
					dialog.close();
					$.getJSON("/admin/store/unban", {'storeId':storeId}, function(json) {
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					});
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});

	}

    /**
     * 
     * 通过/拒绝审核信息
     *
     * @author ben
     * @date 2015年11月30日 下午4:33:14 
     * @param storeId 门店id
     * @param uid 用户id（只有在更改用户实名认证的时候才会有，其他情况为－1）
     * @param type 要更新的认证类型：实名认证 － user ； 场所认证 －facility ； 营业执照认证 － businessLicense ； 资质证明认证 － cateringCertification
     * @param status 要更新至的状态，只有两种：  2 － 审核通过 ； 3 － 审核拒绝
     */
	function updateAuditStatus(storeId,uid,hid,type,status) {
		BootstrapDialog.show({
			title: '通过/拒绝审核信息',
			message: '你确认要通过/拒绝这条审核信息吗？',
			buttons: [{
				label: '确认',
				action: function(dialog) {
					dialog.close();
					$.post("/admin/store/updateAuditStatus.do", {'storeId':storeId, 'uid':uid,'hid':hid,'type':type,'status':status}, function(json) {
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					});
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});

	}

	// 清除缓存
	function cleanCache(storeId) {
		BootstrapDialog.show({
			title: '清除缓存',
			message: '你确认要清除这家门店的缓存吗？',
			buttons: [{
				label: '确定',
				action: function(dialog) {
					dialog.close();
					$.getJSON("/admin/store/cleanCache", {'storeId':storeId}, function(json) {
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					});
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});
	}

	// 修改门店名称
	var updateNameYes;
	function updateName(storeId) {
		updateNameYes = false;
		BootstrapDialog.show({
			title: '修改餐厅名称',
			message: '你确认要修改这家餐厅的名称吗？'
					+ '<form role="form" class="form-horizontal">'
					+ '<div class="form-group"><label class="col-xs-3 control-label">新的餐厅名称：</label><div class="col-xs-9"><input type="text" name="storeName" class="form-control"></div></div>'
					+ '</form>',
			buttons: [{
					label: '确定',
					action: function(dialog) { updateNameYes=true; dialog.close(); }
				}, {
					label: '取消',
					action: function(dialog) { updateNameYes=false; dialog.close(); }
				}
			],
			onhide: function(dialogRef){
				if(updateNameYes) {
					var newName = dialogRef.getModalBody().find('input[name=storeName]').val();
					newName = $.trim(newName);
					if(0 >= newName.length) {
						MyDialog.alert('请输入新的餐厅名称!');
						return false;
					}
					// 再次确认  。。。暂无
					// 执行修改
					$.post("/admin/store/updateName.do", {'storeId':storeId, 'newName':newName}, function(json){
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					}, "json");
				}
			}
		});
	}

	// 修改门店客户经理信息
	var updateCSMYes;
	function updateCSM(storeId) {
		updateCSMYes = false;
		BootstrapDialog.show({
			title: '修改餐厅客户经理信息',
			message: '你确认要修改这家餐厅的客户经理信息吗？'
				+ '<form role="form" class="form-horizontal">'
				+ '<div class="form-group"><label class="col-xs-3 control-label">客户经理名称：</label><div class="col-xs-9"><input type="text" name="csmName" class="form-control"></div></div>'
				+ '<div class="form-group"><label class="col-xs-3 control-label">客户经理电话：</label><div class="col-xs-9"><input type="text" name="csmPhone" class="form-control" placeholder="手机号码"></div></div>'
				+ '</form>',
			buttons: [{
					label: '确定',
					action: function(dialog) { updateCSMYes=true; dialog.close(); }
				}, {
					label: '取消',
					action: function(dialog) { updateCSMYes=false; dialog.close(); }
				}
			],
			onhide: function(dialogRef){
				if(updateCSMYes) {
					var csmName = dialogRef.getModalBody().find('input[name=csmName]').val();
					var csmPhone = dialogRef.getModalBody().find('input[name=csmPhone]').val();
					csmName = $.trim(csmName);
					csmPhone = $.trim(csmPhone);
					// 校验
					if (0 >= csmName.length) {
						MyDialog.alert('客户经理名称不能为空。');
						return false;
					}
					if (0 >= csmPhone.length) {
						MyDialog.alert('客户经理电话不能为空。');
						return false;
					}
					// 执行修改
					$.post("/admin/store/updateCsm.do", {'storeId':storeId, 'csmName':csmName, 'csmPhone':csmPhone}, function(json){
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					}, "json");
				}
			}
		});
	}
	
	//添加员工 jack 2016/3/22
	var saveStaffYes;
	function saveStaff(storeId) {
		saveStaffYes = false;
		BootstrapDialog.show({
			title: '添加员工',
			message: 
				'<form role="form" class="form-horizontal">' +
					'<div class="form-group">' +
					'<label class="col-xs-3 control-label">国家：</label>' +
						'<div class="col-xs-3">' +
							'<select class="form-control" name="countryCode" id="logCountryCode">' +
								'<option value="86" selected>中国</option>' +
							'</select>' +
						'</div>' +
					'</div>' +
					'<div class="form-group">' +
						'<label class="col-xs-3 control-label">电话：</label>' +
						'<div class="col-xs-9">' +
							'<input type="text" name="phone" class="form-control">' +
						'</div>' +
					'</div>' +
					'<div class="form-group">' +
						'<label class="col-xs-3 control-label">姓名：</label>' +
						'<div class="col-xs-9">' +
							'<input type="text" name="name" class="form-control">' +
						'</div>' +
					'</div>' +
					'<div class="form-group">' +
						'<label class="col-xs-3 control-label">员工角色：</label>' +
						'<div class="col-xs-9">' +
							'<label><input name="role" type="checkbox" value="<%=Role.SHOPKEEPER.getValue()%>" id="shopkeeper" />店长</label> &nbsp;&nbsp;' +
							'<label><input name="role" type="checkbox" value="<%=Role.ORDER_ACCEPTER.getValue()%>" id="accepter" />接单员</label> &nbsp;&nbsp;' +
							'<label><input name="role" type="checkbox" value="<%=Role.DELIVER.getValue()%>" id="deliver" />配送员</label> &nbsp;&nbsp;' +
							'<label><input name="role" type="checkbox" value="<%=Role.CUSTOMER_SERVICE.getValue()%>" id="service" />客服</label> &nbsp;&nbsp;' +
						'</div>' +
					'</div>' +
				'</form>' 
				,
			buttons: [{
				label: '确定',
				action: function(dialog) { saveStaffYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { saveStaffYes=false; dialog.close(); }
			}
			],
			onshown: function(dialogRef){
				
			},
			onhide: function(dialogRef){
				if(saveStaffYes) {
					
					var countryCode = $('select[name=countryCode]').val();
					var phone = $.trim(dialogRef.getModalBody().find('input[name=phone]').val());
					var name = $.trim(dialogRef.getModalBody().find('input[name=name]').val());
					
					if(0 >= phone.length) {
						MyDialog.alert('请输入员工的电话。');
						return false;
					}
					
					if(0 >= name.length) {
						MyDialog.alert('请输入员工的姓名。');
						return false;
					} 
					
					var roleIds = '';
					
					$('input[name="role"]:checked').each(function() {
						roleIds += $(this).val() + ',';
					});
					
					if('' == roleIds) {
						alert("请选择员工的角色");
						return false;
					}
					
					// 执行添加
					$.post("/admin/store/adminSaveStaff.do", {'storeId': storeId, 'countryCode': countryCode, 'phone':phone, 'name': name, 'roleIds': roleIds}, function(json){
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					}, "json");
				}
			}
		});
	}
	
	//修改员工信息 jack 2016/3/22
	var updateStaffYes;
	function updateStaff(name, uid, role, storeId, imId) {
		updateStaffYes = false;
		BootstrapDialog.show({
			title: '修改员工信息',
			message: '你确认要修改该员工的信息吗？<br><br>' +
			'<form role="form" class="form-horizontal">' +
				'<div class="form-group">' +
					'<label class="col-xs-3 control-label">员工姓名：</label>' +
					'<div class="col-xs-9">' +
						'<input type="text" id="name" name="newName" class="form-control">' +
					'</div>' +
				'</div>' +
				'<div class="form-group">' +
					'<label class="col-xs-3 control-label">	员工角色：</label>' +
					'<div class="col-xs-9">' +
					'<label><input name="newRole" type="checkbox" value="<%=Role.SHOPKEEPER.getValue()%>" id="shopkeeper" />店长</label> &nbsp;&nbsp;' +
					'<label><input name="newRole" type="checkbox" value="<%=Role.ORDER_ACCEPTER.getValue()%>" id="accepter" />接单员</label> &nbsp;&nbsp;' +
					'<label><input name="newRole" type="checkbox" value="<%=Role.DELIVER.getValue()%>" id="deliver" />配送员</label> &nbsp;&nbsp;' +
					'<label><input name="newRole" type="checkbox" value="<%=Role.CUSTOMER_SERVICE.getValue()%>" id="service" />客服</label> &nbsp;&nbsp;' +
					'</div>' +
				'</div>' +
			'</form>',
			buttons: [{
				label: '确定',
				action: function(dialog) { updateStaffYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { updateStaffYes=false; dialog.close(); }
			}
			],
			onshown: function(dialogRef){
				
				$("#name").val(name);
				
				var roles = $("td:contains(" + imId + ")").prev("td");
				
				for (var i = 0; i < roles.length; i++) {
					
					var roleElement = roles[i]
					var roleText = roleElement.innerHTML;
					if($.trim(roleText) == "店长") {
						$("#shopkeeper").attr("checked", "checked");
					}
					
					if($.trim(roleText) == "接单员") {
						$("#accepter").attr("checked", "checked");
					}
					
					if($.trim(roleText) == "配送员") {
						$("#deliver").attr("checked", "checked");
					}
					
					if($.trim(roleText) == "客服") {
						$("#service").attr("checked", "checked");
					}
				}
				
				
				if(role == <%=Role.SHOPKEEPER.getValue()%>) {
					$("#shopkeeper").attr("checked", "checked");
				}
				
				if(role == <%=Role.ORDER_ACCEPTER.getValue()%>) {
					$("#accepter").attr("checked", "checked");
				}
				
				if(role == <%=Role.DELIVER.getValue()%>) {
					$("#deliver").attr("checked", "checked");
				}
				
				if(role == <%=Role.CUSTOMER_SERVICE.getValue()%>) {
					$("#service").attr("checked", "checked");
				}
				
			},		
			onhide: function(dialogRef){
				if(updateStaffYes) {
					
					var newName = $.trim(dialogRef.getModalBody().find('input[name=newName]').val());
					
					if(0 >= newName.length) {
						MyDialog.alert('请输入新的员工姓名。');
						return false;
					}
					
					
					var newRoleIds = '';
					
					$('input[name="newRole"]:checked').each(function() {
						newRoleIds += $(this).val() + ',';
					});
					
					if('' == newRoleIds) {
						alert("请选择员工的角色");
						return false;
					}
					
					// 执行修改
					$.post("/admin/store/adminUpdateStaff.do", {'storeId': storeId, 'uid': uid, 'name': newName, 'newRoleIds': newRoleIds, 'imId': imId}, function(json){
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					}, "json");
				}
			}
		});
	}
	

	// 修改门店地址 ten 2015/11/26
	var updateAddressYes = false;
	function updateAddress(storeId) {
		updateAddressYes = false;
		BootstrapDialog.show({
			title: '修改餐厅地址',
			message: '你确认要修改这家餐厅的地址吗？<br>' +
			'<form role="form" class="form-horizontal"><div class="form-group"><label class="col-xs-3 control-label">新的餐厅地址：</label><div class="col-xs-9"><input type="text" name="newAddress" class="form-control"></div></div>' +
				'<div class="form-group"><label class="col-xs-3 control-label">经度：</label><div class="col-xs-9"><input type="text" name="newLng" class="form-control" placeholder="经度Longitude"></div></div>' +
				'<div class="form-group"><label class="col-xs-3 control-label">纬度：</label><div class="col-xs-9"><input type="text" name="newLat" placeholder="纬度Latitude" class="form-control"></div></div>' +
				'<div class="form-group"><div class="col-xs-3">&nbsp;</div><div class="col-xs-9"><a href="http://lbs.amap.com/console/show/picker" target="_blank">高德坐标拾取器</a></div></div>' +
				'</form>',
			buttons: [{
				label: '确定',
				action: function(dialog) { updateAddressYes=true; dialog.close(); }
			}, {
				label: '取消',
				action: function(dialog) { updateAddressYes=false; dialog.close(); }
			}
			],
			onhide: function(dialogRef){
				if(updateAddressYes) {
					var newAddress = $.trim(dialogRef.getModalBody().find('input[name=newAddress]').val());
					var newLng = $.trim(dialogRef.getModalBody().find('input[name=newLng]').val());
					var newLat = $.trim(dialogRef.getModalBody().find('input[name=newLat]').val());
					if(0 >= newAddress.length) {
						MyDialog.alert('请输入新的餐厅地址。');
						return false;
					}
					if (newLng > 180.0) {
						MyDialog.alert('经度不能超过180.0。');
						return false;
					}
					if (newLat > 90.0) {
						MyDialog.alert('纬度不能超过90.0。');
						return false;
					}
					// 再次确认  。。。暂无
					// 执行修改
					$.post("/admin/store/updateAddress.do", {'storeId':storeId, 'newAddress':newAddress, 'newLng': newLng, 'newLat': newLat}, function(json){
						MyDialog.alert(json.message);
						// reload 门店信息
						query();
					}, "json");
				}
			}
		});
	}

	// 显示餐厅的提现帐号
	// 2016/3/14 by ten
	function displayStoreAccount(storeId) {
		$.getJSON("/admin/store/getStoreAccount", {'storeId':storeId}, function(json) {
			if (!json.success) {
				MyDialog.alert(json.message);
			}
			else {
				var account = json.object;
				var displayHtml = ''
				if (account == null) {
					displayHtml = '店主还没绑定支付宝帐号';
				}
				else {
					displayHtml = '餐厅绑定了支付宝帐号：<br/>姓名：' + json.object.accountName + '<br/>帐号：' + json.object.accountNo;

				}
				displayHtml += '<br/>请输入新的支付宝帐号：<br/>帐号：<input type="text" name="accountNo" placeholder="比如手机号码、邮箱" class="form-control"  maxlength="30"><br/>' +
					'姓名：<input type="text" name="accountName" placeholder="比如张三、XXX公司" class="form-control"  maxlength="50">';
				BootstrapDialog.show({
					title: '修改餐厅提现帐号',
					message: displayHtml,
					buttons: [{
						label: '确定',
						action: function(dialog) {
							dialog.close();
							var accountNo = $.trim(dialog.getModalBody().find('input[name=accountNo]').val());
							var accountName = $.trim(dialog.getModalBody().find('input[name=accountName]').val());
							if (accountNo == '' || accountName == '') {
								MyDialog.alert('请输入新的帐号和用户名。');
							}
							else {
								// 执行修改
								$.post("/admin/store/modifyStoreAccount.do", {
									'storeId': storeId,
									'accountNo': accountNo,
									'accountName': accountName
								}, function (json) {
									MyDialog.alert(json.message);
								}, "json");
							}
						}
					}, {
						label: '取消',
						action: function(dialog) { dialog.close(); }
					}
					],
					onhide: function(dialogRef){

					}
				});
			}
		});
	}
	
	// 负责状态汇总表各个模块的状态的html标签拼接
	function statusTemplate(status) {
		var html = "";
		switch(status)
		{
		case 0:
			html = "<span style='color:#ff0000;'>未提交</span>";
			break;
		case 1:
			html = "<span style='color:#ff0000;'>审核中</span>";
			break;
		case 2:
			html = "<span style='color:green;'>已通过</span>";
			break;
		case 3:
			html = "<span style='color:#ff0000;'>未通过</span>";
			break;
		}
		return html;
	}

	/**
	 * 根据经纬度，获取两点间的距离，单位：米<br>
	 */
	function distanceByLngLat(lng1, lat1, lng2, lat2) {
		var radLat1 = lat1 * Math.PI / 180;
		var radLat2 = lat2 * Math.PI / 180;
		var a = radLat1 - radLat2;
		var b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
		var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	// 地图
	var _map = null;
	var _rangeColor = ['#7ec95a', '#5ac99a', '#5aa1c9', '#9e5ac9', '#ce881e', '#d7db15', '#db5c15', '#5cef02'];

	// 显示地图
	function displayRange(rangeList) {
		if (_map == null) {
			// 初始化地图
			_map = new AMap.Map('mapContainer', {
				resizeEnable: true,
				zoom: 15
			});
			_map.plugin(["AMap.ToolBar"], function() {
				_map.addControl(new AMap.ToolBar());
			});
		}

		// 最小坐标点和最大坐标点
		var minLng = 180.0, maxLng = -180.0, minLat = 180.0, maxLat = -180.0;
		var lastPolygonMinLat = 180.0; // 上一个配送区域最小的纬度
		for (var i=rangeList.length-1; i >= 0; i--) {
			// cutValue格式 [[23.126197,113.337798],[23.126933,113.3394],[23.124754,113.340434],[23.123335,113.339159],[23.124018,113.337174],[23.125975,113.336842]]
			eval("var coords = " + rangeList[i].cutValue + ";");
			if (coords.length > 0) {
				var polygonArr = new Array();
				var markerLng = 0.0, markerLat = 0.0, tmpMinLat = 180.0, tmpMaxLat = -180.0;
				for (var j=0; j < coords.length; j++) {
					var lng = parseFloat(coords[j][1]);
					var lat = parseFloat(coords[j][0]);
					polygonArr.push([lng, lat]);
					if (lng > maxLng)
						maxLng = lng;
					if (lng < minLng)
						minLng = lng;
					if (lat > maxLat)
						maxLat = lat;
					if (lat < minLat)
						minLat = lat;
					if (markerLng <= 0.0) {
						markerLng = lng;
						markerLat = lat;
					}
					if (tmpMinLat > lat)
						tmpMinLat = lat;
					if (tmpMaxLat < lat)
						tmpMaxLat = lat;
				}
				var polygon = new AMap.Polygon({
					path: polygonArr,//设置多边形边界路径
					strokeColor: "#FF0000", //线颜色
					strokeOpacity: 0.2, //线透明度
					strokeWeight: 3,    //线宽
					fillColor: _rangeColor[i % _rangeColor.length], //填充色
					fillOpacity: 0.35//填充透明度
				});
				polygon.setMap(_map);

				// 设置标记，计算出纬度的值
				// 算法：(当前区域最小lat + 上一个区域最小lat) / 2
				var destMarkerLat = 0;
				if (lastPolygonMinLat >= 180.0) // 未有上一个
					destMarkerLat = (tmpMinLat + tmpMaxLat) / 2;
				else if (lastPolygonMinLat < tmpMinLat)
					destMarkerLat = (tmpMinLat + lastPolygonMinLat) / 2;
				else
					destMarkerLat = tmpMinLat;
				lastPolygonMinLat = tmpMinLat;
				var marker = new AMap.Marker({
					//icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
					position: [markerLng, destMarkerLat],
					content: '<div class="label label-primary">' + rangeList[i].name + '</div>'
				});
				marker.setMap(_map);
			}

		} // end for
		// 计算配送区域左上、右下对角线的距离
		var distance = distanceByLngLat(minLng, minLat, maxLng, maxLat);
		var zoom = 15;
		if (distance >= 50000) {
			zoom = 11;
		}
		else if (distance >= 30000) {
			zoom = 12;
		}
		else if (distance >= 20000) {
			zoom = 13;
		} else if (distance >= 9000) {
			zoom = 14;
		}
		var center = new AMap.LngLat((minLng + maxLng) / 2, (minLat + maxLat) / 2);
		// 设置中心点和缩放
		_map.setZoomAndCenter(zoom, center);
	}

	// 加载标签 tag 数据
	// 2016/1/18 ten
	function loadStoreTag(storeId) {
		$.getJSON("/admin/store/loadStoreTag", {'storeId':storeId}, function(json) {
			if (!json.success) {
				MyDialog.alert(json.message);
			}
			else {
				var htmlTxt = template('storeTagTemplate', json);
				$('#tagContainer').html(htmlTxt);
			}
		});
	}

	// 保存标签 tag
	// 2016/1/18 ten
	function saveStoreTag() {
		var storeId = $.getUrlParam("storeId");

		if (storeId == null || storeId == '' || storeId == '0') {
			MyDialog.alert('请在门店列表界面选择一家门店进行操作');
		}
		else {
			var values = new Array();
			var storeTag = $('input[name=storeTag]:checked').each(function(i, item) {
				values.push(item.value);
			});
			if (values.length > 0) {
				var tagIds = values.join(',');
				console.log('tagIds: ' + tagIds)
				$.post('/admin/store/saveStoreTag.do', {
					'storeId': storeId,
					'tagIds': tagIds
				}, function (json) {
					MyDialog.alert(json.message);
				}, 'json');
			}
			else {
				MyDialog.alert('请至少选择一个标签');
			}
		}
	}
	
	
	
	// 上传文件
	// 有两个组件会调用到
	function uploadPic(a) {
		var input = $(a.target);
		var id = input.attr('id');
		var callback = input.attr('callback');
		//创建FormData对象
		var data = new FormData();
		//为FormData对象添加数据
		$.each($('#'+id)[0].files, function(i, file) { data.append('picFile', file); });
		$.ajax({
			data: data,
			type: 'POST',
			cache: false,
			contentType: false, //不可缺
			processData: false, //不可缺
			url: '/admin/system/uploadPic.do',
			success: function(json){
				if(json.success){
					var img = json.object;
					if(callback) {
						//alert("callback:"+callback+", img:"+img);
						eval(callback+'("'+img+'");');
					}
				} else {
					MyDialog.alert(json.message);
				}
			}
		});
	}

	$(function(){
		// 初始化模版函数
		
		

		// 模版函数：输出图片的完整地址
		template.helper('renderImageUrl', function (uri) {
			return imageDomain + uri;
		});
		// 模版函数：输出配送范围的颜色
		template.helper('renderRangeColor', function (i) {
			var c = _rangeColor[i % _rangeColor.length];
			return c;
		});

		// 模版函数：解析图片url，根据分号；对图片进行分割
		template.helper('sliptImageUrl', function (uri) {
			var html = "";
			var pics = uri.split(";");
			for(var i =0;i<pics.length;i++){
				var pic = "<img src='"+imageDomain+pics[i]+"' width='200px' height='200px'/>"+"&nbsp&nbsp&nbsp"
				html = html +pic;
			}
			return html;
		});

		// 模版函数：找店主的健康认证审核，当作该门店的健康认证审核状态
		template.helper('getHealthStatus', function (healthAudit) {
			// 没有数据，返回［未提交］状态
			if(null==healthAudit || healthAudit.length == 0){
				return statusTemplate(0);
			}
			for(var i =0;i<healthAudit.length;i++){
				// 如果有店主健康信息，返回其审核状态
				if(healthAudit[i].roleId==1){
					return statusTemplate(healthAudit[i].status);
				}
			}
			// 说明没有店主健康信息，返回［未提交］
			return statusTemplate(0);
		});
		
		
		// 模版函数：用tips来输出菜品的说明，需要把双引号 (") 替换为 单引号(')
		template.helper('renderTips', function (content) {
			return content.replace(/"/g, "'");
		});
		
		// 查询门店
		query();
		
	});
	

	//预览 首页展示图
	function previewImg(img) {
		//alert("previewImg");
		$("#imgPreview").attr("src", imageDomain + img);
		$("#imgID").val(img);
	}
	
	//删除首页展示图
	function delIndexShowImg(storeId){
		
		BootstrapDialog.show({
			title: '删除首页展示图',
			message: '你确定要删除首页配置图吗?',
			buttons: [{
				label: '确定',
				action: function(dialog) {
					
					var indexShowImg = "";
					$.post('/admin/store/modifyIndexShowImg.do', {
						'storeId': storeId,
						'indexShowImg': indexShowImg
					}, function (json) {
						MyDialog.alert(json.message);
						if (json.success) {
							$("#imgPreview").attr("src", indexShowImg);
							$("#imgID").val(indexShowImg);
						}
					}, 'json');
					
					dialog.close(); 
					
				}
			}, {
				label: '取消',
				action: function(dialog) {
					dialog.close();
				}
			}]
		});
		
	}
	//保存首页展示图
	function upIndexShowImg(storeId){
		
		var indexShowImg = $("#imgID").val();
		if(indexShowImg){
			$.post('/admin/store/modifyIndexShowImg.do', {
				'storeId': storeId,
				'indexShowImg': indexShowImg
			}, function (json) {
				MyDialog.alert(json.message);
				if (json.success) {
				}
			}, 'json');
		}
	}
	
	function uploadTopicImg(storeId) {
		var fd = new FormData();
		fd.append("upload", 1);
		fd.append("storeId", storeId);
		fd.append("upfile", $("#upfile").get(0).files[0]);
		$.ajax({
			url: "/admin/store/modifyTopicImage.do",
			type: "POST",
			processData: false,
			contentType: false,
			data: fd,
			success: function(d) {
				$("#topicImg").attr("src",imageDomain + d.object);
			}
		});
	}
	
	function openWin(storeId, status, title) {
		BootstrapDialog.show({
			title: title,
			message: '请备注' + title + ":"
					+ '<form role="form" class="form-horizontal">'
					+ '<div class="form-group"><div class="col-xs-9"><input type="text" name="remark" class="form-control"></div></div>'
					+ '</form>',
			buttons: [{
					label: '确定',
					action: function(dialog) { 
						var remark = dialog.getModalBody().find('input[name=remark]').val();
						modifyStoreStatus(storeId, status, remark);
						dialog.close(); 
					}
				}, {
					label: '取消',
					action: function(dialog) {
						dialog.close(); 
					}
				}
			]
		});
	}

	//给营业执照和餐饮资质认证加水印
	function toImageMark(storeId, type){
		var url = "/admin/store/audit/imageMark?storeId=" + storeId + "&type=" + type;
		$.getJSON(url, function(json){
			MyDialog.alert(json.message);
			if(json.success){
				//刷新
				query();
			}
		});
	}
	
	//切换提现类型，1=实时到账，0=T+N
	function changeWithdrawType(storeId, withdrawType){
		$.post('/admin/store/change/withdrawType', {'storeId': storeId, 'withdrawType': withdrawType}, function(result){
			MyDialog.alert(result.message);
			if(result.success){
				//刷新
				query();
			}
		});
	}
	
	//加载直联列表
	function loadPayAccount() {
		$.getJSON("/admin/store/pay/account/list", {'uid':uid}, function(json) {
			if (!json.success) {
				MyDialog.alert(json.message);
			}
			else {
				var htmlTxt = template('payAccountTemplate', json);
				$('#payAccountInfo').html(htmlTxt);
			}
		});
	}
	
	//新增直联信息
	function addPayAccount() {
		var data = {'uid':uid, 'payMode': $('#payMode').val(), 'appId': $('#appId').val(), 'mchId': $('#mchId').val(), 'isOpen': $('#isOpen').val()};
		$.post("/admin/store/pay/account/add", data, function(json) {
			if (!json.success) {
				MyDialog.alert(json.message);
			}
			else {
				loadPayAccount(uid);
			}
			$('#addModal').modal('hide');
		});
	}
	
	//修改直联信息
	function updatePayAccount(payMode, appId, mchId, isOpen) {
		var data = {'uid':uid, 'payMode': $('#payMode2').val(), 'appId': $('#appId2').val(), 'mchId': $('#mchId2').val(), 'isOpen': $('#isOpen2').val()};
		$.post("/admin/store/pay/account/update", data, function(json) {
			if (!json.success) {
				MyDialog.alert(json.message);
			}
			else {
				loadPayAccount(uid);
			}
			$('#updateModal').modal('hide');
		});
	}
	
	function openWin2(payMode, appId, mchId, isOpen){
		$('#payMode2').val(payMode);
		$('#appId2').val(appId);
		$('#mchId2').val(mchId);
		$('#isOpen2').val(isOpen);
		$('#updateModal').modal('show');
	}
	</script>
</body>
</html>
