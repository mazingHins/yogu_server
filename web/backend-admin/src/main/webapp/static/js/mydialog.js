// 对dialog的简单包装，实现自己想要的样式、文字
// linyi 2015/6/13
// http://nakupanda.github.io/bootstrap3-dialog/

var MyDialog = function() {
};

/**
 * Alert window
 *
 * @returns the created dialog instance
 */
MyDialog.alert = function() {
	var options = {};
	var defaultOptions = {
		type: BootstrapDialog.TYPE_PRIMARY,
		title: '结果',
		message: null,
		closable: false,
		draggable: false,
		buttonLabel: BootstrapDialog.DEFAULT_TEXTS.OK,
		callback: null
	};

	if (typeof arguments[0] === 'object' && arguments[0].constructor === {}.constructor) {
		options = $.extend(true, defaultOptions, arguments[0]);
	} else {
		options = $.extend(true, defaultOptions, {
			message: arguments[0],
			callback: typeof arguments[1] !== 'undefined' ? arguments[1] : null
		});
	}

	return new BootstrapDialog({
		type: options.type,
		title: options.title,
		message: options.message,
		closable: options.closable,
		draggable: options.draggable,
		data: {
			callback: options.callback
		},
		onhide: function(dialog) {
			!dialog.getData('btnClicked') && dialog.isClosable() && typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(false);
		},
		buttons: [{
			label: options.buttonLabel,
			action: function(dialog) {
				dialog.setData('btnClicked', true);
				typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(true);
				dialog.close();
			}
		}]
	}).open();
};

/**
 * Shortcut function: show
 *
 * @param {type} options
 * @returns the created dialog instance
 */
MyDialog.show = function(options) {
	return new BootstrapDialog(options).open();
};