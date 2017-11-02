<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yogu.core.remote.config.AppLanguage" %>
<!-- REQUIRED JS SCRIPTS -->
<!-- Bootstrap 3.3.2 JS -->
<script src="/static/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/json2.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="/static/tpl/js/app.min.js" type="text/javascript"></script>
<!-- DATA TABES SCRIPT -->
<script src="/static/js/template.js"  type="text/javascript"></script>
<script src="/static/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="/static/plugins/jquery.form.js" type="text/javascript"></script>
<script src="/static/plugins/bootstrap-dialog/js/bootstrap-dialog.min.js"></script>
<script src="/static/js/mydialog.js"></script>
<script src="/static/js/paginator.js?v=8" type="text/javascript"></script>
<script src="/static/js/area.js" type="text/javascript"></script>
<script src="/static/plugins/daterangepicker/moment.js" type="text/javascript"></script>
<script src="/static/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="/static/plugins/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript">
      var IMAGE_DOMAIN = 'https://img.mazing.com/';
	 
      // 日期格式化
      Date.prototype.format = function (fmt) { //author: meizz
            var o = {
                  "M+": this.getMonth() + 1, //月份
                  "d+": this.getDate(), //日
                  "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
                  "H+" : this.getHours(), //小时
                  "m+": this.getMinutes(), //分
                  "s+": this.getSeconds(), //秒
                  "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                  "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
      };

      // 获取昨天的日期
      Date.prototype.getYesterday = function() {
            var time = this.getTime() - 24 * 3600 * 1000;
            return new Date(time);
      };

      //获取明天的日期
      Date.prototype.getNextDay = function() {
            var time = this.getTime() + 24 * 3600 * 1000;
            return new Date(time);
      };

      // 获取上周同一天的日期
      Date.prototype.getLastWeekSameDay = function() {
            var time = this.getTime() - 24 * 3600 * 1000 * 7;
            return new Date(time);
      };

      $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
      };

      // 把string解析为Date
      function parseDate(str) {
            if (typeof str == 'string'){
                  var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
                  if(results && results.length>3)
                        return new Date(parseInt(results[1]),parseInt(results[2].replace('0', '')) -1,parseInt(results[3]));
                  results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
                  if(results && results.length>6)
                        return new Date(parseInt(results[1]),parseInt(results[2].replace('0', '')) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]));
                  results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
                  if(results && results.length>7)
                        return new Date(parseInt(results[1]),parseInt(results[2].replace('0', '')) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]),parseInt(results[7]));
            }
            return null;
      };

      $(function(){
            // ie8下会缓存ajax数据，此处去掉缓存
            $.ajaxSetup({ cache: false });
      });

      // template 增加日期、时间格式化 start
      template.helper('formatDateTime', function (content) {
            var d = new Date(content).format('yyyy-MM-dd HH:mm:ss');
            return d;
      });

      template.helper('formatDate', function (content) {
            var d = new Date(content).format('yyyy-MM-dd');
            return d;
      });

      function cent2yuan(content) {
            var i = parseFloat(content);
            if(isNaN(i)) { i = 0.00; }
            i = i / 100.0;
            var minus = '';
            if(i < 0) { minus = '-'; }
            i = Math.abs(i);
            i = parseInt((i + .005) * 100);
            i = i / 100;
            s = new String(i);
            if(s.indexOf('.') < 0) { s += '.00'; }
            if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
            s = minus + s;
            return s;
      }
      // 金额格式化：分 -> 元
      template.helper('cent2yuan', cent2yuan);

      // 格式化百分数
      template.helper('percentFormat', function(content, n) {
            n = n || 2;
            var d = (content == 0 ? '0%' : (Math.round(content * 10000)/100).toFixed(n) + '%');
            return d;
      });
      
      // 显示城市名称
      function cityName(cityCode, n) {
            if('020' == cityCode) return '广州市';// 目前只有广州，如果开通了其他城市，这里需要增加
            return cityCode;
      }
      template.helper('cityName', cityName);
      
      // 显示语言环境的中文名称
      // type List >> [{name: '', value: ''}, {..} ..]
      var langArr = [<%boolean langOne__ = true;for(AppLanguage lang : AppLanguage.values()) {if(!langOne__) {%><%=", "%><%}%>{zhName: '<%=lang.getZhName()%>', code: '<%=lang.getCode()%>'}<%langOne__ = false;}%>];
      function langName(langCode, n) {
          var zhName = langCode;
          $.each(langArr, function(i, d) { if(langCode == d.code) zhName = d.zhName; });
          return zhName;
      }
      template.helper('langName', langName);
      // end

      $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
      };



      $(function() {
            // 高亮菜单
            var path = window.location.href;
            if (path.indexOf("?") > 0) {
                  path = path.substring(0, path.indexOf("?"));
            }
            $(".sidebar-menu a").each(function(key, value) {
                  if (path == value) {
                        var el = $(this);
                        while (true) {
                              var parent = el.parent();
                              if (parent.hasClass('sidebar-menu')) {
                                    break;
                              }
                              if (parent.is("li")) {
                                    parent.addClass("active");
                              }

                              el = parent;
                        }

                  }
            });
      });

      /**
	   * 请求node服务器
	   * @param url 请求的接口url
	   * @param method http method
	   * @param data 要传输的数据(json格式)
	   * @param callback 接口调用成功的回调函数
	   */
	  function requestNode(url, method, data, callback){
		  $.ajax({
			  url: 'https://napi.mazing.com' + url,
			  method: method,
			  cache: false,
			  headers:{
				  token:$('#token').attr('value').trim()
			  },
			  data:data,
			  error:function(){
					MyDialog.alert('请求node服务器出错！');  
			  },
			  success:function(res){
				  if(typeof callback === 'function'){
					  if(res.code == 80){
						   alert('登录信息已失效，请重新登录！'); 
						   window.location.href = 'http://admin.mazing.com/static/login.html';
						   return false;
					  }
					  if(res.code != 1 || !res.success){
						  MyDialog.alert(res.message);  
					  } else {
					  	  callback(res.object);
					  }
				  }
			  }
		  });
	  }
		
	/**
	 * 上传文件
	 * @param file 文件上传域对象
	 * @param callback 文件上传成功的回调函数
	 * @param width 图片宽度限制
	 * @param height 图片高度限制
	 */
	function uploadFile(file, callback, width, height) {
		if(typeof callback !== 'function'){
			MyDialog.alert(callback+' 不是回调函数！');  
			return;
		}
	
		// 限制文件大小
		var data = new FormData();
		file = file[0].files[0];
		var size = parseInt(file.size) || 0;
		if(size <= 0){
			MyDialog.alert('上传的图片不能为空！'); 
			return;
		}
		if(size > 1048576){
			MyDialog.alert('上传的图片体积不能超过1M！'); 
			return;
		}
		
		// 限制文件类型
		var type = ['image/png', 'image/jpg', 'image/jpeg'];
		if(parseInt(type.indexOf(file.type)) <= -1){
			MyDialog.alert('只能上传png、jpg、jpeg三种格式的图片！'); 
			return;
		}
		
		// 限制图片尺寸
		width = parseInt(width) || 0;
		height = parseInt(height) || 0;
		if(width > 0 && height > 0){
			var reader = new FileReader();
			reader.onload = function (e) {
				var data = e.target.result;
				var image = new Image();
				image.onload = function(){
					var _width = image.width;
					var _height = image.height;
					if(_width > width) {
						MyDialog.alert('规定的图片宽度为 '+width+' 像素但实际上传的图片宽度为 '+_width+' 像素！'); 
						return;
					}
					if(_height > height) {
						MyDialog.alert('规定的图片高度为 '+width+' 像素但实际上传的图片高度为 '+_width+' 像素！'); 
						return;
					}
				};
				image.src = data;
		   };
		   reader.readAsDataURL(file);
		}
		
		data.append('picFile', file);

		$.ajax({
			data: data,
			type: 'POST',
			cache: false,
			contentType: false, //不可缺
			processData: false, //不可缺
			url: '/admin/system/uploadPic.do',
			success: function(json){
				if(json.success){
					callback(json.object);
				} else {
                    // 本地不可以上传图片，暂时写死
                    //callback('index/2016/12/2aep-q6sorbI_350x420.jpg');
					///MyDialog.alert(json.message);
				}
			}
		});
	}
</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
      Both of these plugins are recommended to enhance the
      user experience. Slimscroll is required when using the
      fixed layout. -->