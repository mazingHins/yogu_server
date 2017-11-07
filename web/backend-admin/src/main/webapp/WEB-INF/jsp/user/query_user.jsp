<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>用户管理</title>
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
      <h1> 用户管理 <small></small> </h1>
      <ol class="breadcrumb">
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <ul id="userTab" class="nav nav-tabs">
            <li class="active"> <a href="#queryUser" data-toggle="tab"> 用户查询 </a> </li>
            <li><a href="#createUser" data-toggle="tab">创建帐号</a></li>
            <li><a href="#logQuery" data-toggle="tab">日志查询</a></li>
            <li><a href="#regQuery" data-toggle="tab">注册查询</a></li>
          </ul>
          <div id="userTabContent" class="tab-content"> 
            <!-- tab start 用户查询 -->
            <div class="tab-pane fade in active" id="queryUser">
              <div class="box box-solid">
                <div class="box-header">
                  <div class="col-sm-1">
                    <select class="form-control" name="countryCode" id="countryCode">
                      <option value="86" selected>中国</option>
                    </select>
                  </div>
                  <div class="col-sm-1">
                    <select class="form-control" name="queryType" id="queryType">
                      <option value="1" selected>帐号</option>
                      <option value="2">UID</option>
                    </select>
                  </div>
                  <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                      <input type="text" id="passport" name="passport" maxlength="30" class="form-control" placeholder="请输入用户帐号或uid">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info btn-flat" onclick="search()">Go!</button>
                      </span> </div>
                  </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                  <div class="row">
                    <div class="col-sm-12">
                      <table id="userTable" class="table table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>属性</th>
                            <th>数值</th>
                            <th>头像</th>
                          </tr>
                        </thead>
                        <tbody id="userInfo">
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- /.box-body --> 
              </div>
              <!-- /.box --> 
              
            </div>
            <!-- /.tab --> 
            <!-- tab end 用户查询 --> 
            
            <!-- tab start 创建帐号 -->
            <div class="tab-pane fade in" id="createUser">
              <div class="box box-solid">
                <div class="box-header">
                  <div class="col-sm-8">
                    <p class="text-left">此功能主要用于，米星帮商家创建帐号。</p>
                  </div>
                </div>
                <!-- /.box-header -->
                <form role="form" id="createUserForm" name="createUserForm" action="/admin/user/createUser.do" method="post">
                  <div class="box-body">
                    <div class="row">
                      <div class="form-horizontal col-sm-12">
                        <div class="form-group">
                          <label class="col-sm-2 control-label"> 国家： </label>
                          <div class="col-sm-2">
                            <select class="form-control" name="countryCode">
                              <option value="86">中国</option>
                            </select>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-sm-2 control-label"> 用户头像： </label>
                          <div class="col-sm-2"> <img id="profilePicPreview" width="100" src=""/><br>
                            <input type="file" id="profilePicFile" value="更换图片..." onChange="updateProfilePic();">
                            <input type="hidden" id="profilePicInput" name="profilePic" class="form-control"/>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-sm-2 control-label"> 手机号码： </label>
                          <div class="col-sm-2">
                            <input type="text" name="passport" class="form-control" placeholder="请输入手机号码" maxlength="11"/>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-sm-2 control-label"> 昵称： </label>
                          <div class="col-sm-2">
                            <input type="text" name="nickname" class="form-control" placeholder="请输入用户昵称" maxlength="20"/>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-sm-2 control-label"> 密码： </label>
                          <div class="col-sm-2">
                            <input type="password" name="password" class="form-control" placeholder="请输入密码" maxlength="16"/>
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-primary">创建</button>
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
            <!-- tab end 创建帐号 --> 
            
            <!-- tab start 日志查询 -->
            <div class="tab-pane fade in" id="logQuery">
              <div class="box box-solid">
                <div class="box-header">
                  <div class="col-sm-1">
                    <select class="form-control" name="logCountryCode" id="logCountryCode">
                      <option value="86" selected>中国</option>
                    </select>
                  </div>
                  <div class="col-sm-2">
                    <input type="text" value="" placeholder="日期yyyy-MM-dd" class="form-control"/>
                  </div>
                  <div class="col-sm-2">
                    <div class="input-group input-group-sm">
                      <input type="text" id="logPassport" name="logPassport" maxlength="30" class="form-control" placeholder="请输入用户帐号">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info btn-flat" onclick="searchLog()">Go!</button>
                      </span> </div>
                  </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                  <div class="row">
                    <div class="col-sm-12">
                      <table id="logTable" class="table table-bordered table-hover">
                        <thead>
                          <tr>
                            <th>ID</th>
                            <th>时间</th>
                            <th>内容</th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- /.box-body --> 
              </div>
              <!-- /.box --> 
            </div>
            <!-- tab end 日志查询 --> 
            
            <!-- tab start 新注册用户查询 -->
            <div class="tab-pane fade in" id="regQuery">
              <div class="box box-solid">
                <div class="box-header">
                  <div class="col-sm-1">
                    <select class="form-control" name="regCountryCode" id="regCountryCode">
                      <option value="86" selected>中国</option>
                    </select>
                  </div>
                  <div class="col-sm-2">
                    <input type="text" name="regDate" id="regDate" value="" placeholder="日期yyyy-MM-dd" class="form-control"/>
                  </div>
                  <div class="col-sm-2">
                    <div class="input-group input-group-sm"> <span class="input-group-btn">
                      <button type="button" class="btn btn-info btn-flat" onclick="searchReg()">GoNew!</button>
                      </span> </div>
                  </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                  <div class="row">
                    <div class="col-sm-12">
                      <table id="logTable" class="table table-bordered table-hover" style="word-break:break-all; word-wrap:break-all;">
                        <thead>
                          <tr>
                            <th>注册总数</th>
                            <th>手机串</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody id="phoneStr">
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- /.box-body --> 
              </div>
              <!-- /.box --> 
            </div>
            <!-- tab end 日志查询 --> 
            
          </div>
          <!-- /.tabContent - --> 
        </div>
        <!-- /.col --> 
      </div>
      <!-- /.row --> 
    </section>
    <!-- /.content --> 
  </div>
  <!-- /.content-wrapper --> 
  
  <!-- footer -->
  <jsp:include page="/include/footer.jsp"/>
  
  <!-- control sidebar -->
  <jsp:include page="/include/control-sidebar.jsp"/>
</div>
<!-- ./wrapper --> 
<script id="userTemplate" type="text/html">
	<tr>
		<td>uid</td>
		<td>{{uid}}</td>
		<td rowspan="8">
			{{if profilePic != null && profilePic != ''}}
			<img width="200px" src="{{renderImageUrl profilePic}}"/> <br/>
			{{/if}}
		</td>
	</tr>
	<tr>
		<td>国家</td>
		<td>{{countryCode}}</td>
	</tr>
	<tr>
		<td>帐号</td>
		<td>{{passport}}</td>
	</tr>
	<tr>
		<td>创建时间</td>
		<td>{{formatDateTime createTime}} &nbsp; &nbsp; &nbsp; 城市：{{cityCode}} &nbsp; &nbsp; &nbsp; 注册IP：{{registerIp}}</td>
	</tr>
	<tr>
		<td>昵称</td>
		<td>
			{{nickname}}
		</td>
	</tr>
	<tr>
		<td>推广码</td>
		<td>
			{{inviteCode}}
		</td>
	</tr>
	<tr>
		<td>状态</td>
		<td>
			{{if status == 1}}
			正常
			{{/if}}
			{{if status == 2}}
			<span style="color:red;">封号</span>
			{{/if}}
		</td>
	</tr>
	<tr>
		<td class="text-right">修改密码</td>
		<td><input class="form-control" type="password" id="newPassword" name="newPassword" maxlength="30"/></td>
		<td><button type="button" class="btn btn-info btn-flat" onclick="changeUserPassword({{uid}})">修改密码</button></td>
	</tr>
</script> 
<script id="regPhoneTemplate" type="text/html">
	<tr>
		<td>{{total}}</td>
		<td>{{phoneStr}}</td>
		<td></td>
	</tr>
</script> 
<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
	// 图片地址
	var imageDomain = '';
	
	// 修改管理员密码
	// uid: 用户的id
	function changeUserPassword(uid) {
		if (uid) {
			var newPassword = $('#newPassword').val();
			if (newPassword == '') {
				MyDialog.alert('请输入新的密码');
			}
			else {
				$.post('/admin/user/changeUserPassword.do', {
					'uid': uid,
					'password': newPassword
				}, function(json) {
					MyDialog.alert(json.message);
				}, 'json');
			}
		}
	}

	// 查询用户提现帐号
	// 2015/12/13 ten
	function showWithdrawAccount(uid) {
		$.getJSON('/admin/user/getUserAccount', {'uid': uid}, function (json) {
			if (json.success) {
				var message = '';
				if (json.object != null) {
					message = '用户已经绑定了支付宝帐号：<br/>姓名：' + json.object.accountName + '<br/>帐号：' + json.object.accountNo;
				}
				else {
					message = '用户还没绑定支付宝帐号';
				}
				MyDialog.alert(message);
			}
			else {
				MyDialog.alert('查询出错：' + json.message);
			}
		});
	}

	// 查询帐号
	function search() {
		var countryCode = $('#countryCode').val();
		var passport = $.trim($('#passport').val());
		var queryType = $('#queryType').val();
		if (passport == '') {
			if (queryType == '1')
				MyDialog.alert('请输入一个帐号，比如手机号码');
			else
				MyDialog.alert('请输入一个uid');
		}
		else {
			var uid = '0';
			if (queryType != '1') {
				uid = passport;
				countryCode = '';
				passport = '';
			}
			$.getJSON('/admin/user/queryUser', {'countryCode': countryCode, 'passport': passport, 'uid': uid}, function (json) {
				if (json.success) {
					render(json.object);
				}
				else {
					MyDialog.alert(json.message);
				}
			});
		}
	}

	// 用模板显示用户的信息
	function render(value) {
		// 图片域名
		imageDomain = value.imgDomain;
		if (imageDomain.substring(imageDomain.length - 1) != '/') {
			imageDomain = imageDomain + '/';
		}

		var htmlTxt = template('userTemplate', value);
		$('#userInfo').html(htmlTxt);
	}

	// 修改帐号状态：封号
	function ban(uid) {
		$.getJSON("/admin/user/ban", {'uid':uid}, function(json) {
			MyDialog.alert(json.message);
			// reload 用户信息
			search();
		});
	}

	// 修改帐号状态：解封
	function unban(uid) {
		$.getJSON("/admin/user/unban", {'uid':uid}, function(json) {
			MyDialog.alert(json.message);
			// reload 用户信息
			search();
		});
	}

	// 查询用户日志
	function searchLog() {
		MyDialog.alert('此功能未实现');
	}
	


	// 检查URL里有没有uid的参数，如果有，进行UID的查询
	function searchByUid() {
		var uid = $.getUrlParam("uid");

		if (uid == null || uid == '' || uid == '0') {
			; // do nothing
		} else {
			$('#passport').val(uid);
			$('#queryType').val(2);
			search();
		}
	}
	
	function updateProfilePic(){
		uploadFile($('#profilePicFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#profilePicPreview').attr('src', url);
			$('#profilePicInput').val(data);
		});
	}

	$(function() {
		// 模版函数：输出图片的完整地址
		template.helper('renderImageUrl', function (uri) {
			return imageDomain + uri;
		});

		$('#createUserForm').ajaxForm({
			complete : function(xhr) {
				try {
					eval('json=' + xhr.responseText);
					MyDialog.alert(json.message);
					if (json.success) {
						// 清一下帐号输入框
						var createUserForm = document.forms['createUserForm'];
						createUserForm['passport'].value = '';
					}

				} catch (e) {
				}
			}
		});

		// 检查URL里有没有uid的参数，如果有，进行UID的查询
		searchByUid();
	});
</script>
</body>
</html>
