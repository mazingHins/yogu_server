// 1. 显示城市列表、行政区列表
// 2. 显示大类、小类列表
// linyi 2015/6/25
// 依赖接口：
//     /admin/common/listCities.do
//     /admin/common/listDistricts.do?cityCode=


/*
 1. 显示城市列表、行政区级联菜单

 使用方式:
    var cityArea = new CityAreaList({
        cityId: 'cityId',
        districtId: 'districtId',
        defaultCity: '',
        defaultDistrict: '',
        cityChange: function() {}
    });
    cityArea.load();
 */
var CityAreaList = function(opt) {
    var defaults = {
        // 城市input field的id
        cityId: '',
        // 行政区input field的id
        districtId: '',
        // 默认选择的cityCode,
        defaultCity: '',
        // 默认选择的行政区,
        defaultDistrict: '',
        // 父接口URL，不能有参数
        cityUrl : '/admin/common/listCities.do',
        // 子接口URL，只能有一个参数
        districtUrl : '/admin/common/listDistricts.do',
        // 子接口的参数名,
        districtUrlParam : 'cityCode'

    };
    var tmp = $.extend({}, defaults, opt);
    var options = {};
    options['parentId'] = tmp['cityId'];
    options['childId'] = tmp['districtId'];
    options['parentDefault'] = (typeof tmp['defaultCity'] == 'undefined' ? '' : tmp['defaultCity']);
    options['childDefault'] = (typeof tmp['defaultDistrict'] == 'undefined' ? '' : tmp['defaultDistrict']);
    options['parentUrl'] = tmp['cityUrl'];
    options['childUrl'] = tmp['districtUrl'];
    options['childParamName'] = tmp['districtUrlParam'];
    if (typeof tmp['cityChange'] == 'function') {
        options['parentChange'] = tmp['cityChange'];
    }

    this.menuLoader = new Cascading2MenuLoader(options);
    this.load();
};

CityAreaList.prototype = {

    // 加载城市、行政区数据
    load: function () {
        this.menuLoader.load();
    }
};

/*
 2. 大类、小类列表的级联菜单
 使用方式:
    var category = new CategoryList({
        bigCateId: 'bigCateId',
        smallCateId: 'smallCateId',
        defaultBigCate: '',
        defaultSmallCate: '',
        bigCateChange: function() {}
    });
    category.load();
 */
var CategoryList = function(opt) {
    var defaults = {
        // 大类input field的id
        bigCateId: '',
        // 小类input field的id
        smallCateId: '',
        // 默认选择的大类,
        defaultBigCate: '',
        // 默认选择的下列,
        defaultSmallCate: '',
        // 父接口URL，不能有参数
        bigCateUrl : '/admin/common/listBigCategories.do',
        // 子接口URL，只能有一个参数
        smallCateUrl : '/admin/common/listSmallCategories.do',
        // 子接口的参数名,
        smallCateParam : 'bigCode'

    };
    var tmp = $.extend({}, defaults, opt);
    var options = {};
    options['parentId'] = tmp['bigCateId'];
    options['childId'] = tmp['smallCateId'];
    options['parentDefault'] = (typeof tmp['defaultBigCate'] == 'undefined' ? '' : tmp['defaultBigCate']);
    options['childDefault'] = (typeof tmp['defaultSmallCate'] == 'undefined' ? '' : tmp['defaultSmallCate']);
    options['parentUrl'] = tmp['bigCateUrl'];
    options['childUrl'] = tmp['smallCateUrl'];
    options['childParamName'] = tmp['smallCateParam'];

    if (typeof tmp['bigCateChange'] == 'function') {
        options['parentChange'] = tmp['bigCateChange'];
    }

    this.menuLoader = new Cascading2MenuLoader(options);
    this.load();
};

CategoryList.prototype = {

    // 加载城市、行政区数据
    load: function () {
        this.menuLoader.load();
    }
};

// 两级的级联菜单通用类
var Cascading2MenuLoader = function(opt) {
    this.defaults = {
        // 父input field的id
        parentId: '',
        // 子input field的id
        childId: '',
        // 父input默认值,
        parentDefault: '',
        // 子input默认值,
        childDefault: '',
        // 父接口URL，不能有参数
        parentUrl : '',
        // 子接口URL，只能有一个参数
        childUrl : '',
        // 子接口的参数名,
        childParamName : '',
        // 父下拉框的提示
        parentTips : 'Please choose ...',
        // 子下拉框的提示
        childTips : 'Please choose ...',
        // id field的名称，比如Area.code
        idField : 'code',
        // text field的名称，比如Area.name，用于显示
        textField : 'name'
    };
    this.options = $.extend({}, this.defaults, opt);
};

Cascading2MenuLoader.prototype = {

    /**
     * 加载父、子菜单数据
     */
    load : function() {
        if (this.options.parentId && this.options.parentUrl) {
            this.loadParentList();
        }

    },

    // 加载城市列表
    loadParentList: function() {
        var self = this;
        // 加载父列表数据
        $.getJSON(this.options.parentUrl, {}, function(json) {
            if(json.success) {
                var obj = $('#' + self.options.parentId), list = json.object;
                obj.empty();
                var data = [];
                if (list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        var item = list[i];
                        data.push({
                            id: item[self.options.idField],
                            text: item[self.options.textField]
                        });
                    }
                }
                obj.select2({
                    'allowClear' : true,
                    'data': data,
                    'placeholder': self.options.parentTips
                });

                // 把子输入框变成一个下拉框显示
                if (self.options.childId) {
                    var childClearOptions = {
                        'allowClear': true,
                        'data': [],
                        'placeholder': self.options.childTips
                    };
                    $('#' + self.options.childId).select2(childClearOptions);

                    // 事件处理
                    $(obj).on("change", function (e) {
                        // 改变父菜单的时候加载子菜单列表
                        self.loadChildren();
                        if (typeof self.options.parentChange == 'function') {
                            self.options.parentChange(e.val);
                        }
                    }).on("select2-removed", function(e) {
                        // 清空父菜单选择的时候，清空子菜单列表
                        $('#' + self.options.childId).select2(childClearOptions);
                    });
                }
                // 触发onchange
                obj.select2('val', self.options.parentDefault, true);
            } // end if (json.success)
        });
    },

    // 加载行政区列表
    loadChildren: function() {
        var code = $('#' + this.options.parentId).val();
        if (code) {
            var self = this;
            var params = {};
            params[this.options.childParamName] = code;
            $.getJSON(this.options.childUrl, params, function(json) {
                if (json.success) {
                    var obj = $('#' + self.options.childId), list = json.object;
                    obj.empty();
                    var data = [];
                    if (list.length > 0) {
                        for (var i = 0; i < list.length; i++) {
                            var item = list[i];
                            data.push({
                                id: item[self.options.idField],
                                text: item[self.options.textField]
                            });
                        }
                    }
                    obj.select2({
                        'allowClear' : true,
                        'data': data,
                        'placeholder': self.options.childTips
                    });
                    obj.select2('val', self.options.childDefault);
                }
            });
        } // end if (code)
    }
};

var OneMenuLoader = function(opt) {
    this.defaults = {
        // input field的id
        inputId: '',
        // 接口地址
        url : '',
        // 下拉框的提示
        tips : 'Please choose ...',
        // 默认选中的值
        defaultValue: '',

        idField : 'code',
        // text field的名称，比如Area.name，用于显示
        textField : 'name',
        // url 参数
        params: {}
    };
    this.options = $.extend({}, this.defaults, opt);
    this.load();
};

OneMenuLoader.prototype = {
    load : function() {
        var self = this;
        // 加载列表数据
        if (this.options.url) {
            $.getJSON(this.options.url, this.options.params, function (json) {
                if (json.success) {
                    var obj = $('#' + self.options.inputId), list = json.object;
                    obj.empty();
                    var data = [];
                    if (list.length > 0) {
                        for (var i = 0; i < list.length; i++) {
                            var item = list[i];
                            data.push({
                                id: item[self.options.idField],
                                text: item[self.options.textField]
                            });
                        }
                    }
                    obj.select2({
                        'allowClear': true,
                        'data': data,
                        'placeholder': self.options.tips
                    });

                    // 触发onchange
                    obj.select2('val', self.options.defaultValue, true);
                } // end if (json.success)
            });
        }
        else {
            $('#' + this.options.inputId).select2({
                'allowClear': true,
                'data': [],
                'placeholder': this.options.tips
            });
        }
    }
};