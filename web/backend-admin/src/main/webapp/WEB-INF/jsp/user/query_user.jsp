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
            <li><a href="#createUser" data-toggle="tab">创建经销商</a></li>
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
                    <p class="text-left">此功能主要用于，帮商家创建帐号。</p>
                  </div>
                </div>
                <!-- /.box-header -->
                <form role="form" id="createUserForm" name="createUserForm" action="/admin/user/createUser.do" method="post">
                  <div class="box-body">
                    <div class="row">
                      <div class="form-horizontal col-sm-12">
                        
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
			<img width="200px" src="{{profilePic}}"/> <br/>
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

<!-- bottom js -->
<%@ include file="/include/bottom-js.jsp"%>
<script type="text/javascript">
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
		var htmlTxt = template('userTemplate', value);
		$('#userInfo').html(htmlTxt);
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
	

	$(function() {
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
