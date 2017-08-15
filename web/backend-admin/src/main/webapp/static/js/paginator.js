// 分页代码
// linyi
// 2015/6/8
// artTemplate 模版的分页
// https://github.com/aui/artTemplate

// 参数
// paginator: 显示分页的div
// tpl:       artTemplate模版id
// url:       获取数据的地址
// params:    url的参数
// logPage:   是否记录分页，这样在前进、后退的时候，可以恢复当时的内容
var ArtPaginator = function(opt) {
	this.defaults = {
		'page': 1,
		'pageSize': 20
	},
	this.options = $.extend(true, {}, this.defaults, opt);
};

ArtPaginator.globalPaginator = {}

ArtPaginator.prototype = {
	show: function() {
		this.initFromCookie();
		this.getTBody();
		this.initPaginatorBar();
		this.load(this.options.page);
	},

	getTBody: function() {
		var table = $("#" + this.options.table);
		var tbody = table.children("tbody");
		if (typeof tbody == 'undefined' || tbody.length == 0) {
			table.append("<tbody></tbody>");
			tbody = table.children("tbody");
		}
		return tbody;
	},
	createPageHtml: function(id) {
		if (id) {
			var htmlText = '\
			<div class="col-sm-3">\
				<div class="dataTables_info" role="alert" aria-live="polite" aria-relevant="all" id="' + id + '_total"></div>\
			</div>\
			<div class="col-sm-9">\
				<div class="dataTables_paginate paging_simple_numbers">\
					<ul class="pagination" style="margin: 0 0;" id="' + id + '_pageHolder">\
					</ul>\
				</div>\
			</div>';
			$('#' + id).html(htmlText);
		}
	},
	initPaginatorBar: function() {
		this.createPageHtml(this.options.paginator);
		this.createPageHtml(this.options.dupPaginator);
	},
	load: function(page) {
		var params = (this.options.params || {});
		params.page = page;
		params.pageSize = this.options.pageSize;
		var $this = this;
		/*
		$.getJSON(this.options.url, params, function(json) {
			if(!json.success) {
				MyDialog.alert('结果错误：' + json.message);
				return;
			}
			var htmlTxt = template($this.options.tpl, json);
			$this.getTBody().html(htmlTxt);

			$this.redrawPageBar(page, json);
			$this.options['page'] = page;

			// 2016/1/12
			// 如果 logPage 被设置
			if (typeof $this.options['logPage'] == 'string') {
				$this.logPageData($this.options);
			}

			//if (paginator._tips && json.object && typeof json.object.length != 'undefined' && json.object.length === 0) {
			//	new PopupTips('查询结果为空。').show();
			//}
			$this = null;
		});
		*/
		// jfan 2016-01-20 请求数据，支持设定request type
		var reqType = (this.options.type || 'GET');
		var callback__ = this.options.success;
		$.ajax({headers:{token:$('#token').attr('value').trim()}, url: this.options.url, type: reqType, data: params, dataType: 'json', success: function(json) {
				if(!json.success) {
					MyDialog.alert('结果错误：' + json.message);
					return;
				}
				var htmlTxt = template($this.options.tpl, json);
				$this.getTBody().html(htmlTxt);
	
				$this.redrawPageBar(page, json);
				$this.options['page'] = page;
	
				// 2016/1/12
				// 如果 logPage 被设置
				if (typeof $this.options['logPage'] == 'string') {
					$this.logPageData($this.options);
				}
	
				//if (paginator._tips && json.object && typeof json.object.length != 'undefined' && json.object.length === 0) {
				//	new PopupTips('查询结果为空。').show();
				//}
				$this = null;
				
				// 2016-05-19 jfan
				if (typeof callback__ == 'function') {
					callback__(json);
				}
			}
		});
	},

	// 记录分页内容
	logPageData: function(opt) {
		var cookieId = 'admin_' + opt['logPage'];
		var str = JSON.stringify(opt);
		$.cookie(cookieId, str);
	},

	// 从 cookie 读取参数
	initFromCookie: function () {
		if (typeof this.options['logPage'] == 'string') {
			var cookieId = 'admin_' + this.options['logPage'];
			var value = $.cookie(cookieId);
			if (value) {
				console.log('load cookie: ' + value)
				try {
					eval('var opt=' + value);
					// 比较 opt 和 this.options，只有 page 不同的时候才能从 cookie 加载

					if (this.sameObject(this.options, opt)) {
						this.options = opt;
					}
				} catch (e) {

				}
			}
		}
	},

	// 比较对象是否一致，返回 false=不一致
	sameObject: function(obj1, obj2) {
		for (var prop in obj1) {
			var value1 = obj1[prop];
			var value2 = obj2[prop];
			if (typeof value1 != 'undefined' && typeof value2 != 'undefined') {
				if (typeof value1 == "object" && typeof value2 == "object") {
					return this.sameObject(value1, value2);
				}
				else if (value1 != value2 && prop != 'page') {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	},

	// 生成页码
	redrawPageBar : function(currentPage, json) {
		if (typeof json.totalPage == 'undefined') {
			// 没有总页数，只显示prev, next
			this.drawNextPrevPage(this.options.paginator, currentPage, json);
			if (typeof this.options['dupPaginator'] == 'string' && this.options['dupPaginator'] != '') {
				this.drawNextPrevPage(this.options['dupPaginator'], currentPage, json);
			}
		}
		else {
			var totalPage = (json.totalPage || 1);
			var tmpHtml = '';
			if (totalPage <= 15) {
				tmpHtml = this.drawPageBetween(1, totalPage, currentPage);
			}
			else {
				tmpHtml = this.drawPageBetween(1, 15, currentPage);
				var n1 = totalPage - 2;
				if (n1 <= 15) {
					n1 = totalPage;
				}
				tmpHtml += '<li class="paginate_button" tabindex="0"><a href="javascript:void(0);">……</a></li>';
				tmpHtml += this.drawPageBetween(n1, totalPage, currentPage);
			}
			$('#' + this.options.paginator + '_total').html('当前第' + currentPage + "页，总共" + totalPage + "页");
			$('#' + this.options.paginator + '_pageHolder').html(tmpHtml);
		}
	},
	drawNextPrevPage: function(paginatorId, currentPage, json) {
		$('#' + paginatorId + '_total').html(' ');
		var tmpHtml = '<li class="paginate_button"><a>当前第 ' + currentPage + ' 页</a></li>';
		if (currentPage > 1) {
			tmpHtml = tmpHtml + '<li class="paginate_button " tabindex="0">' +
			'<a href="javascript:'+ this.flipPageFuncName(1) +'">第1页</a></li>';
			tmpHtml = tmpHtml + '<li class="paginate_button " tabindex="0">' +
			'<a href="javascript:'+ this.flipPageFuncName(currentPage-1) +'">上一页</a></li>';
		}
		tmpHtml = tmpHtml + '<li class="paginate_button" tabindex="0">' +
			'<a href="javascript:'+ this.flipPageFuncName(currentPage+1) +'">下一页</a></li>';
		$('#' + paginatorId + '_pageHolder').html(tmpHtml);
	},
	drawPageBetween: function(n1, n2, currentPage) {
		var tmpHtml = '';
		for (var i=n1; i <= n2; i++) {
			tmpHtml = tmpHtml + '<li class="paginate_button ';
			if (i == currentPage) {
				tmpHtml = tmpHtml + 'active ';
			}
			tmpHtml = tmpHtml + '" tabindex="0">' +
			'<a href="javascript:'+ this.flipPageFuncName(i) +'">' + i + '</a></li>';
		}
		return tmpHtml;
	}
	// 返回翻页方法的字符串，例如：toPage(10);   或者： javascript:$('#id').artPaginateNext(10);
	, flipPageFuncName: function(index) {
		//如果指定了方法，那么就用指定的方法
		if(this.options.flipPageFunc)
			return (this.options.flipPageFunc+'('+index+');');
		return ('$(\'#' + this.options.table + '\').artPaginateNext(' + index + ');');
	}
}

/**
 * 用法：
 * $('#table').artPaginate( {
 *     paginator: 'paginator',  // 显示页码的div的ID
 *     url: 'http://xxx',  // 读取数据的URL
 *     tpl: 'tplXX',  // 模板ID
 *     params: {},    // URL提交的参数
 *     page: 1        // 第几页开始显示
 * } );
 * @param options
 */
$.fn.artPaginate = function(options) {
	options["table"] = this.attr('id');
	var ap = new ArtPaginator(options);
	ap.show();
	ArtPaginator.globalPaginator[this.attr('id')] = ap;
};

// 加载指定页码
$.fn.artPaginateNext = function(page) {
	var ap = ArtPaginator.globalPaginator[this.attr('id')];
	ap.load(page);
};

// 返回当前是第几页
$.fn.artPage = function() {
	var ap = ArtPaginator.globalPaginator[this.attr('id')];
	return ap.options.page;
};