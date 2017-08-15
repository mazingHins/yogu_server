<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mazing.services.store.RecommendBlockType"%>
<%@ page import="com.mazing.core.enums.BooleanConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/include/meta.html"%>
<title>cblog管理--店铺添加</title>
</head>
<body class="skin-blue sidebar-mini">
<div class="wrapper">

<!-- header -->
<jsp:include page="/include/header.jsp" />

<!-- sidebar -->
<jsp:include page="/include/sidebar.jsp" />
<style>
	label{cursor:pointer;font-weight:normal}label,label input{margin:4px}#supportingModal ul,#supportingModal ul li, #cuisineModal ul,#cuisineModal ul li{list-style:none;float:left;margin-right:10px}#starCommentTabLi,#levelTabLi{display:none}
</style>

<!-- 餐厅列表Modal -->
<div class="modal fade" id="storeModal" tabindex="-1" role="dialog" aria-labelledby="storeModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
        <h4 class="modal-title" id="storeModalLabel">选择餐厅</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            <div class="input-group input-group-sm">
              <input id="storeKeyword" name="storeKeyword" maxlength="30" class="form-control" placeholder="输入关键字进行搜索">
              <span class="input-group-btn">
              <button type="button" class="btn btn-info btn-flat" onclick="searchStore();">Go!</button>
              </span> </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <table id="storeTable" class="table table-bordered table-hover">
              <thead>
                <tr>
                  <th>餐厅名称</th>
                  <th>餐厅评分</th>
                </tr>
              </thead>
              <script id="storeTableTpl" type="text/html">
										{{each object as value i}}
										<tr>
											<td>
												<a href="javascript:selectStore({{value.storeId}})">{{value.storeName}}</a>
											</td>
											<td>
												{{renderStoreStar value.star}}
											</td>
											<input id="store_name_{{value.storeId}}" type="hidden" value="{{value.storeName}}" />
											<input id="store_content_{{value.storeId}}" type="hidden" value="{{value.content}}" />
											<input id="store_logoPath_{{value.storeId}}" type="hidden" value="{{value.logoPath}}" />
											<input id="store_topicImg_{{value.storeId}}" type="hidden" value="{{value.topicImg}}" />
										</tr>
										{{/each}}
									</script>
            </table>
          </div>
        </div>
        <div class="row" id="storePaginator"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>

<!-- 菜系 modal-->
<div class="modal fade" id="cuisineModal" tabindex="-1" role="dialog" aria-labelledby="cuisineModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
        <h4 class="modal-title" id="cuisineModalLabel">选择菜系</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <ul id="cuisineList">
          </ul>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="confirmCuisine()">确认</button>
      </div>
    </div>
  </div>
</div>

<!-- 选择用户 modal-->
<div class="modal fade" id="selectCommenterModal" tabindex="-1" role="dialog" aria-labelledby="selectCommenterModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
        <h4 class="modal-title" id="cuisineModalLabel">选择用户</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            <div class="input-group input-group-sm">
              <input id="userPassport" name="userPassport" maxlength="11" class="form-control" placeholder="输入手机号进行精确搜索...">
              <span class="input-group-btn">
              <button type="button" class="btn btn-info btn-flat" onclick="searchUser();">Go!</button>
              </span> </div>
          </div>
        </div>
        <br>
        <br>
      </div>
      <div class="modal-footer"> </div>
    </div>
  </div>
</div>

<!-- 配套设施 modal-->
<div class="modal fade" id="supportingModal" tabindex="-1" role="dialog" aria-labelledby="supportingModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
        <h4 class="modal-title" id="csupportingModalLabel">选择配套设施</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <ul id="supportingList">
          </ul>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="confirmSupporting()">确认</button>
      </div>
    </div>
  </div>
</div>

<!-- 餐厅标签 modal-->
<div class="modal fade" id="tagsModal" tabindex="-1" role="dialog" aria-labelledby="tagsModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>
        <h4 class="modal-title" id="tagsModalLabel">选择标签</h4>
      </div>
      <!-- tag的模版 --> 
      <script id="storeTagTemplate" type="text/html">
		{{each object as value i}}
		<div class="row margin" style="border-bottom: 1px solid #ddd;">
			<div class="col-xs-3 {{if value.appShow != 1}}text-red{{/if}}">{{value.categoryName}}</div>
			<div class="col-xs-9 checkbox {{if value.appShow != 1}}text-red{{/if}}">
				{{each value.tags as tag j}}
					<label><input type="checkbox" name="storeTag" value="{{tag.tagId}}"/>{{tag.tagName}}</label>
				{{/each}}
			</div>
		</div>
		{{/each}}
	</script>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-12" id="tagContainer"></div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="confirmTags()">确认</button>
      </div>
    </div>
  </div>
</div>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
<!-- Content Header (Page header) -->
<section class="content-header"> <a style="cursor:pointer" onclick="window.location.href='/admin/cblog/brandInfo/info.xhtm?brandId=${brandId}'"> 返回店铺列表</a> </section>

<!-- Main content -->
<section class="content">
<div class="row">
<div class="col-xs-12">
<div class="box">
<div class="box-header"> </div>
<div class="box-body">
<ul id="cacheTab" class="nav nav-tabs">
  <li class="active" id="baseInfoTabLi"><a href="#baseInfo" data-toggle="tab"><strong>基础信息</strong></a></li>
  <li id="starCommentTabLi"><a href="#starComment" data-toggle="tab"><strong>星评</strong></a></li>
  <li id="levelTabLi"><a href="#level" data-toggle="tab"><strong>评级</strong></a></li>
</ul>
<div class="tab-content">
<div class="tab-pane fade in active" id="baseInfo">
<br>
<table class="table" id="langauge-baseInfo">
  <tr>
    <td width="100">语言：</td>
    <td width="100"><select id="langConfig-baseInfo" class="form-control" onChange="switchLang(this.value)">
      </select></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">商户名：</td>
    <td width="300"><input type="text" id="brandName" name="brandName" class="form-control" disabled/></td>
    <td width="100">中文店名：</td>
    <td width="300"><input type="text" id="storeName" name="storeName" class="form-control" placeholder="请输入中文店名..."/></td>
    <td width="100">英文店名：</td>
    <td width="300"><input type="text" id="storeNameEn" name="storeNameEn" class="form-control" placeholder="请输入英文店名..."/></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="130">线上订餐关联：</td>
    <td width="250"><input id="storeIdID" name="storeId" type="hidden"/>
      <a id="showModalName" class="btn btn-default" href="javascript:showModal();">请选择餐厅</a> <a id="cancelBind" class="btn btn-danger" href="javascript:cancelBindStore();" disabled>取消关联餐厅</a></td>
    <td width="230">是否显示米星pay<font color="red">(默认显示)</font>：</td>
    <td width="130"><label>是
        <input type="radio" id="showMazingPayYes" name="showMazingPay" value="1" checked/>
      </label>
      <label>否
        <input type="radio" id="showMazingPayNo" name="showMazingPay" value="0"/>
      </label></td>
    <td width="240">是否显示在线餐厅<font color="red">(默认显示)</font>：</td>
    <td width="130"><label>是
        <input type="radio" id="showOnlineStoreYes" name="showOnlineStore" value="1" checked/>
      </label>
      <label>否
        <input type="radio" id="showOnlineStoreNo" name="showOnlineStore" value="0"/>
      </label></td>
    <td width="200">是否可分享<font color="red">(默认是)</font>：</td>
    <td width="130"><label>是
        <input type="radio" id="canShareYes" name="canShare" value="1" checked/>
      </label>
      <label>否
        <input type="radio" id="canShareNo" name="canShare" value="0"/>
      </label></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">地理位置：</td>
    <td width="150"><select class="form-control" id="provinceCode" name="provinceCode" onChange="loadCityConfig()">
      </select></td>
    <td width="150"><select class="form-control" id="cityCode" name="cityCode" onChange="loadDistrictConfig()">
      </select></td>
    <td width="150"><select class="form-control" id="districtCode" name="districtCode" onChange="getBusinessDistrict();">
      </select></td>
    <td width="150"><select class="form-control" id="businessDistrictCode" name="businessDistrictCode">
      </select></td>
    <td width="400"><input type="text" class="form-control" id="address" name="address" placeholder="请输入详细地址..."/></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">经纬度：</td>
    <td width="400"><input type="text" id="lnglat" name="lnglat" class="form-control" placeholder="请输入经纬度，格式：经度,纬度"/></td>
    <td width="150"><a href="http://lbs.amap.com/console/show/picker" target="_blank">高德坐标拾取器</a></td>
    <td></td>
  </tr>
</table>
<table class="table" style="display:none">
  <tr>
    <td width="100">描述：</td>
    <td width="700"><textarea class="form-control" id="description" name="description" placeholder="请输入描述..." rows="5"></textarea></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">餐厅标签：</td>
    <td width="250" id="tagsTD"><input type="hidden" id="tags" name="tags"/>
      <input type="hidden" id="tagNames" name="tagNames"/>
      <a style="cursor:pointer" href="javascript:void(0)" onClick="selectTags();">选择标签</a></td>
    <td width="65">菜系：</td>
    <td width="250" id="cuisineTD"><input type="hidden" id="cuisine" name="cuisine"/>
      <a style="cursor:pointer" href="javascript:void(0)" onClick="selectCuisine();">选择菜系</a></td>
    <td width="65">消费：</td>
    <td width="50">人均</td>
    <td width="100"><input type="text" id="perConsume" name="perConsume" class="form-control"/></td>
    <td width="30">元</td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">推荐菜：</td>
    <td><input type="text" class="form-control" id="recDishs" name="recDishs" placeholder="请输入推荐菜..."/>
      <br>
      <font color="red">(如多个用英文逗号分隔)</font></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">配套设施：</td>
    <td width="250" id="supportingTD"><input type="hidden" id="supporting" name="supporting"/>
      <a style="cursor:pointer" href="javascript:void(0)" onClick="selectSupporting();">选择配套设施</a></td>
    <td width="80">电话1：</td>
    <td width="200"><input type="text" class="form-control" id="phone1" name="phone1" placeholder="请输入电话..."/></td>
    <td width="80">电话2：</td>
    <td width="200"><input type="text" class="form-control" id="phone2" name="phone2" placeholder="请输入电话..."/></td>
    <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="100">营业时间：</td>
    <td id="openHours"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><a href="javascript:void(0)" onClick="openHours();">增加营业时间</a></td>
  </tr>
</table>
<table class="table">
<tr>
  <td width="130">餐厅logo：</td>
  <td width="70"><img id="logoPreview" width="50"/>
    <input id="logoInput" type="hidden">
    <input id="logoFile" type="file" value="更换图片..." onChange="uploadLogo();"></td>
  <td width="130">首页轮播图：</td>
  <td id="showImgs"></td>
  <td><input id="showImgsFile" type="file" value="更换图片..." onChange="addShowImgs();"></td>
</tr>
<table class="table">
  <tr>
    <td width="130">缩略图：</td>
    <td width="70"><img id="thumbImgPreview" width="50"/>
      <input id="thumbImgInput" type="hidden">
      <input id="thumbImgFile" type="file" value="更换图片..." onChange="uploadThumbImg();"></td>
    <td width="130">ID推荐图：</td>
    <td id="idRecommendImg"></td>
    <td><input id="idRecommendImgFile" type="file" value="更换图片..." onChange="addIdRecommendImgs();"></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="130">地图缩略图(h5用到)：</td>
    <td width="70"><img id="mapImgPreview" width="50"/>
      <input id="mapImgInput" type="hidden">
      <input id="mapImgFile" type="file" value="更换图片..." onChange="uploadMapImg();"></td>
      <td></td>
  </tr>
</table>
<table class="table">
  <tr>
    <td width="130" id="submitButton"></td>
  </tr>
</table>
</div>
<div class="tab-pane fade" id="starComment"><br>
  <table class="table" id="langauge-starComment">
    <tr>
      <td width="100">语言：</td>
      <td width="100"><select id="langConfig-starComment" class="form-control" onChange="switchLang(this.value)">
        </select></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="120">星评人头像：</td>
      <td width="120"><img id="reviewerPicPreview" width="50"/>
        <input id="reviewerPicInput" type="hidden"></td>
      <td width="120"><input id="reviewerPicFile" type="file" value="更换头像..." onChange="uploadReviewerPic();"></td>
      <td id="reviewTips" width="300" style="display:none"><font color="red">(默认使用当前管理员的app头像)</font></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="120">星评人名字：</td>
      <td width="500"><input class="form-control" id="reviewer" name="reviewer" placeholder="请输入星评人名字..."/></td>
      <td id="reviewerTips" width="300" style="display:none"><font color="red">(默认使用当前管理员的app昵称)</font></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="120">星评内容：</td>
      <td width="500"><textarea class="form-control" id="reviews" name="reviews" placeholder="请输入星评内容..." rows="5"></textarea></td>
      <td></td>
    </tr>
  </table>
  <table class="table" id="reviewTimeTable">
    <tr>
      <td width="120">星评时间：</td>
      <td width="170" id="reviewTime"></td>
      <td></td>
    </tr>
  </table>
  <table class="table" id="reviewAgreeCountTable">
    <tr>
      <td width="120">星评点赞数：</td>
      <td width="200"><input type="hidden" class="form-control" id="reviewAgreeCountOrigin" name="reviewAgreeCountOrigin"/>
        <input type="text" class="form-control" id="reviewAgreeCount" name="reviewAgreeCount" placeholder="请输入星评点赞数..."/>
        <font color="red">(只能增多不能减少)</font></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="100">&nbsp;</td>
      <td width="200"><input type="button" id="updateReviewButton" class="btn btn-success" value="保存" onclick="return updateReview();"/></td>
      <td></td>
    </tr>
  </table>
  <div class="box-body">
    <div class="row">
      <div class="col-sm-12">
        <h2>星友评价(<span id="commentCount">0</span>)</h2>
        <table id="commentListTable" class="table table-bordered table-hover">
          <thead>
            <tr>
              <th width="100">评论ID</th>
              <th width="150">评价人</th>
              <th width="250">评分</th>
              <th width="250">条件</th>
              <th>评价</th>
              <th width="170">时间</th>
              <th width="80">操作</th>
            </tr>
          </thead>
          <script id="commentListTableTpl" type="text/html">
				{{each object as comment i}}
					<tr {{if comment.orderNo != 0}}class="danger"{{/if}}>
						<td>{{comment.commentId}}</td>
						<td>{{comment.userName}}</td>
						<td>{{if comment.orderNo != 0}}
							佳肴体验：{{comment.delicious/10}}&nbsp;&nbsp;服务品质：{{comment.quality/10}}<br>
			  				装修布局：{{comment.decoration/10}}&nbsp;&nbsp;环境气氛：{{comment.surround/10}}<br>
			  				价值感受：{{comment.feeling/10}}
			  				{{/if}}</td>
						<td>{{if comment.orderNo != 0}}
							场景：{{comment.scenes}}&nbsp;&nbsp;时段：{{comment.period}}<br>
			  				人数：{{comment.peoples}}&nbsp;人<br>
			  				消费：{{comment.consume}}&nbsp;元
			  				{{/if}}</td>
						<td>{{comment.content}}</td>
						<td>{{comment.createTime}}</td>
						<td><a href="javascript:void(0)" onClick="return deleteComment({{comment.sinfoId}},{{comment.commentId}})">删除</a></td>
					</tr>
				{{/each}}
				</script>
        </table>
      </div>
    </div>
    <div class="row col-sm-12" id="commentListTablePaginator"> </div>
  </div>
</div>
<div class="tab-pane fade" id="level"> <br>
  <table class="table" id="langauge-level">
    <tr>
      <td width="100">语言：</td>
      <td width="100"><select id="langConfig-level" class="form-control" onChange="switchLang(this.value)">
        </select></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="100">大星评：</td>
      <td width="70"><input type="text" id="stars" name="stars" class="form-control"/></td>
      <td>%</td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="100">当前星评：</td>
      <td><table class="table">
          <tr>
            <td width="100">佳肴体验：</td>
            <td width="200"><input type="hidden" class="form-control" id="deliciousOrigin"/>
              <input type="text" id="delicious" name="delicious" class="form-control" placeholder="请输入佳肴体验评分..." onKeyup="ensureInt(this);"/></td>
            <td width="120">&nbsp;&nbsp;&nbsp;&nbsp;服务品质：</td>
            <td width="200"><input type="hidden" class="form-control" id="qualityOrigin"/>
              <input type="text" id="quality" name="quality" class="form-control" placeholder="请输入服务品质评分..." onKeyup="ensureInt(this);"/></td>
            <td></td>
          </tr>
          <tr>
            <td width="100">装饰布局：</td>
            <td width="200"><input type="hidden" class="form-control" id="decorationOrigin"/>
              <input type="text" id="decoration" name="decoration" class="form-control" placeholder="请输入装饰布局评分..." onKeyup="ensureInt(this);"/></td>
            <td width="120">&nbsp;&nbsp;&nbsp;&nbsp;环境气氛：</td>
            <td width="200"><input type="hidden" class="form-control" id="surroundOrigin"/>
              <input type="text" id="surround" name="surround" class="form-control" placeholder="请输入环境气氛评分..." onKeyup="ensureInt(this);"/></td>
            <td></td>
          </tr>
          <tr>
            <td width="100">价值感受：</td>
            <td width="200"><input type="hidden" class="form-control" id="feelingOrigin"/>
              <input type="text" id="feeling" name="feeling" class="form-control" placeholder="请输入价值感受评分..." onKeyup="ensureInt(this);"/></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="100"></td>
      <td><input type="button" id="updateStarsButton" class="btn btn-success" value="保存" onclick="return updateStars();"/></td>
    </tr>
  </table>
  <br>
  <br>
  <table class="table">
    <tr>
      <td width="200"><h3><strong>机器人用户</strong></h3></td>
      <td width="500"><label>pay评论
          <input type="radio" id="payCommentType" name="commentType" onclick="switchCommentType('pay')"/>
        </label>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>普通评论
          <input type="radio" id="normalCommentType" name="commentType" onclick="switchCommentType('normal')"/>
        </label></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="70"><img id="profilePicPreview" width="100" src=""/><br>
        <input type="hidden" id="profilePicInput" class="form-control"/>
        <input type="hidden" id="newComment-uid" class="form-control"/>
        <br>
        <input type="text" id="newComment-nickname" class="form-control"/>
        <br>
        <input type="button" id="selectCommenterButton" class="btn btn-success" value="选择用户" onclick="return selectCommenter();"/></td>
      <td width="350" id="payCommentParams1"><table class="table">
          <tr>
            <td width="100">佳肴体验：</td>
            <td width="80" id="newComment-delicious-td"></td>
            <td></td>
          </tr>
          <tr>
            <td width="120">服务品质：</td>
            <td id="newComment-quality-td"></td>
            <td></td>
          </tr>
          <tr>
            <td width="100">装饰布局：</td>
            <td id="newComment-decoration-td"></td>
            <td></td>
          </tr>
          <tr>
            <td width="120">环境气氛：</td>
            <td id="newComment-surround-td"></td>
            <td></td>
          </tr>
          <tr>
            <td width="100">价值感受：</td>
            <td id="newComment-feeling-td"></td>
            <td></td>
          </tr>
        </table></td>
      <td width="300" id="payCommentParams2"><table class="table">
          <tr>
            <td width="80">场景：</td>
            <td width="150"><select class="form-control" id="newComment-scenes">
                <option value="0">======</option>
              </select></td>
            <td></td>
          </tr>
          <tr>
            <td width="80">人数：</td>
            <td width="150"><input type="text" class="form-control" id="newComment-peoples" placeholder="请输入消费人数..."/></td>
            <td></td>
          </tr>
          <tr>
            <td width="80">消费：</td>
            <td width="150"><input type="text" class="form-control" id="newComment-consume" placeholder="请输入消费金额..."/></td>
            <td></td>
          </tr>
          <tr>
            <td width="80">时段：</td>
            <td width="150"><select class="form-control" id="newComment-period">
                <option value="0">======</option>
              </select></td>
            <td></td>
          </tr>
        </table></td>
      <td><table class="table">
          <tr>
            <td><textarea cols="50" rows="10" id="newComment-content" class="form-control" placeholder="请输入评论内容，限制399字..."></textarea></td>
            <td></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td width="100"></td>
      <td width="100">点赞数：</td>
      <td width="200"><input type="text" id="newComment-agreeCount" class="form-control" placeholder="请输入点赞数..."/></td>
      <td></td>
    </tr>
  </table>
  <table class="table">
    <tr>
      <td align="right"><input type="button" id="addUserCommentButton" class="btn btn-success" value="发表" onclick="return addUserComment();"/></td>
    </tr>
  </table>
</div>
</div>
</div>

<!-- /.box-body -->
</div>
<!-- /.box -->
</div>
<!-- /.col -->
</div>
<!-- /.row -->
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
	var openHoursNum = 1;
	var sinfoId = parseInt('${sinfoId}') || 0;
	var lang = 'zh';
	var commentType = 'pay';
	var baseCommentPoints = [10, 20, 30, 35, 40, 45, 50];
	var isBsdaysAll = false;
	var storeInfo = null, storeInfoTags = null;

	if(sinfoId > 0){
		$('#langauge-baseInfo').show();
		$('#langauge-starComment').show();
		$('#langauge-level').show();
	} else {
		$('#langauge-baseInfo').hide();
		$('#langauge-starComment').hide();
		$('#langauge-level').hide();
		loadProvinceConfig();
	}

	$(function() {
		// 页面加载时必须执行的函数
		getStoreInfo();
		switchCommentType(commentType);
		getCommentConfig();
		getLangConfig();
		openHours();
		getBrandInfo();
		listComment();
		resetNewCommentInput();

		// 模版函数：输出餐厅的星级
		template.helper('renderStoreStar', function(star) { return star / 10; });
		
		$('#selectCommenterModal').on('hidden.bs.modal', function(){
  			$('#userPassport').val('');
		});
	});
	
	function showBaseCommentPoints(id){
		var html = '';
		html += '<select id="'+id.replace('-td','')+'" class="form-control">';
		for(var p of baseCommentPoints){
			html += '<option value="'+p+'">'+(p/10).toFixed(1)+'分</option>';
		}
		html += '</select>';
		$('#'+id).html(html);
	}
	
	function switchCommentType(type){
		// type: pay、normal
		commentType = type;
		
		if(type === 'pay'){
			$('#payCommentType').attr('checked', true);
			$('#normalCommentType').removeAttr('checked', true);
			$('#payCommentParams1').removeAttr('style');
			$('#payCommentParams2').removeAttr('style');
		} 
		
		if(type === 'normal'){
			$('#payCommentType').removeAttr('checked');
			$('#normalCommentType').attr('checked', true);
			$('#payCommentParams1').css('display','none');
			$('#payCommentParams2').css('display','none');
		}
	}

	function getCommentConfig(){
		requestNode('/store/admin/commentconfig', 'get', {}, function(data){
            var scenesHtml = '';
            for(var s of data.scenes){
                scenesHtml += '<option value="'+s.id+'">'+s.name+'</option>';
            }
            $('#newComment-scenes').append(scenesHtml);
			
			var periodHtml = '';
            for(var p of data.period){
                periodHtml += '<option value="'+p.id+'">'+p.name+'</option>';
            }
            $('#newComment-period').append(periodHtml);
		});
	}
	
	function addUserComment(){
		var data = {};
		
		var uid = parseInt($('#newComment-uid').val()) || 0;
		if(uid <= 0){
			MyDialog.alert('请选择用户！');
			return false;
		}
		data.uid = uid;
		data.sinfoId = sinfoId;
		data.orderNo = 0;
		
		if(commentType === 'pay'){
			data.orderNo = -1;
			
			var delicious = parseInt($('#newComment-delicious').val()) || 0;
			if(!ensureCommentPoints(delicious)){
				MyDialog.alert('佳肴体验的评分只能从下面几项里面选择：'+JSON.stringify(baseCommentPoints)+'！');
				return false;
			}
			data.delicious = delicious;

			var quality = parseInt($('#newComment-quality').val()) || 0;
			if(!ensureCommentPoints(quality)){
				MyDialog.alert('服务品质的评分只能从下面几项里面选择：'+JSON.stringify(baseCommentPoints)+'！');
				return false;
			}
			data.quality = quality;

			var decoration = parseInt($('#newComment-decoration').val()) || 0;
			if(!ensureCommentPoints(decoration)){
				MyDialog.alert('装饰布局的评分只能从下面几项里面选择：'+JSON.stringify(baseCommentPoints)+'！');
				return false;
			}
			data.decoration = decoration;

			var surround = parseInt($('#newComment-surround').val()) || 0;
			if(!ensureCommentPoints(surround)){
				MyDialog.alert('环境气氛的评分只能从下面几项里面选择：'+JSON.stringify(baseCommentPoints)+'！');
				return false;
			}
			data.surround = surround;

			var feeling = parseInt($('#newComment-feeling').val()) || 0;
			if(!ensureCommentPoints(feeling)){
				MyDialog.alert('价值感受的评分只能从下面几项里面选择：'+JSON.stringify(baseCommentPoints)+'！');
				return false;
			}
			data.feeling = feeling;

			var scenes = parseInt($('#newComment-scenes').val()) || 0;
			if(scenes <= 0){
				MyDialog.alert('请选择消费场景！');
				return false;
			}
			data.scenes = scenes;

			var peoples = parseInt($('#newComment-peoples').val()) || 0;
			if(peoples <= 0 || $('#newComment-peoples').val() != peoples){
				MyDialog.alert('请输入消费人数，消费人数只能是正整数！');
				return false;
			}
			data.peoples = peoples;

			var consume = parseInt($('#newComment-consume').val()) || 0;
			if(consume <= 0 || $('#newComment-consume').val() != consume){
				MyDialog.alert('请输入消费金额，消费金额只能是正整数！');
				return false;
			}
			data.consume = consume;

			var period = parseInt($('#newComment-period').val()) || 0;
			if(period <= 0){
				MyDialog.alert('请输入消费时段！');
				return false;
			}
			data.period = period;
		}
		
		var agreeCount = parseInt($('#newComment-agreeCount').val()) || -1;
		if(agreeCount <= 0 || parseInt($('#newComment-agreeCount').val()) != agreeCount){
			MyDialog.alert('点赞数必须为正整数！');
			return false;
		}
		data.agreeCount = agreeCount;
		
		var content = $('#newComment-content').val();
		if(!content){
			MyDialog.alert('请输入评论内容！');
			return false;
		}
		if(content.length > 399){
			MyDialog.alert('评论内容长度不可以超过399字，包括标点符号！');
			return false;
		}
		data.content = content;
		
		requestNode('/store/admin/addcomment', 'post', data, function(data){
			MyDialog.alert('评论添加成功！');
			resetNewCommentInput();
			getStoreInfo();
			listComment();
			setLang();
		});
		return true;
	}
	
	function resetNewCommentInput(){
		$('#profilePicPreview').attr('src', '');
		$('#profilePicInput').val('');
		$('#newComment-uid').val(0);
		$('#newComment-nickname').val('');
		$('#newComment-content').val('');
		$('#newComment-agreeCount').val('');
		$('#newComment-peoples').val('');
		$('#newComment-consume').val('');
		showBaseCommentPoints('newComment-delicious-td');
		showBaseCommentPoints('newComment-quality-td');
		showBaseCommentPoints('newComment-decoration-td');
		showBaseCommentPoints('newComment-surround-td');
		showBaseCommentPoints('newComment-feeling-td');
	}

	function selectCommenter(){
		$('#selectCommenterModal').modal();
	}

	function searchUser(){
		var passport = $('#userPassport').val();
		if(!passport){
			MyDialog.alert('请输入手机号！');
			return;
		}
		requestNode('/system/admin/searchuser', 'post', {passport:passport}, function(data){
			if(Object.keys(data).length <= 0){
				MyDialog.alert('查无此用户！');
				return;
			}
			$('#profilePicPreview').attr('src', IMAGE_DOMAIN + data.profile.profilePic);
			$('#profilePicInput').attr('value',data.profile.profilePic);
			$('#newComment-uid').attr('value', data.uid);
			$('#newComment-nickname').val(data.profile.nickname);
			$('#selectCommenterModal').modal('hide');
		});
	}

	function ensureInt(obj){
		var value = parseInt($(obj).val()) || 0;
		value = value < 0 ? 0 : value;
		$(obj).val(value);
	}
	
	function ensureCommentPoints(value){
		return baseCommentPoints.indexOf(value) !== -1;
	}

	function getLangConfig(){
		requestNode('/system/admin/langconfig', 'get', {}, function(data){
			var html = '';
			for(var lang of data){
				html += '<option value="'+lang.name+'">'+lang.description+'</option>';
			}
			$('#langConfig-baseInfo').html(html);
			$('#langConfig-starComment').html(html);
			$('#langConfig-level').html(html);
			setLang();
		});
	}

	function switchLang(value){
		lang = value;
		getStoreInfo();
		listComment();
		setLang();
	}

	function setLang(){
		[$('#langConfig-baseInfo option'), $('#langConfig-starComment option'), $('#langConfig-level option')].filter(obj => {
			obj.each(function(i,e){
				var o = $(e);
				if(o.attr('value') == lang){
					o.attr('selected', 'selected');
				} else {
					o.removeAttr('selected');
				}
			});
		});
	}

	function listComment(){
		$('#commentListTable').artPaginate({
			// 获取数据的地址
			url : "https://napi.mazing.com/store/admin/listcomment",
			// 显示页码的位置
			paginator: 'commentListTablePaginator',
			// 模版ID
			tpl : 'commentListTableTpl',
			// 请求的参数表，默认page=1, pageSize=20
			params : {sinfoId:sinfoId}
		});
	}

	function deleteComment(sinfoId, commentId){
		if(!confirm('你确定要删除这条评论吗？')){
			return false;
		}
		requestNode('/store/admin/deletecomment', 'post', {sinfoId:sinfoId, commentId:commentId}, function(data){
			getStoreInfo();
			listComment();
			MyDialog.alert('评论删除成功！');
		});
		return true;
	}

	function getAdminInfo(){
		requestNode('/oauth/admin/info', 'get', {}, function(data){
			var url = IMAGE_DOMAIN + data.profile.profilePic;
			$('#reviewerPicPreview').attr('src', url);
			$('#reviewerPicInput').val(data.profile.profilePic);
			$('#reviewer').val(data.profile.nickname);
		});
	}

	function getStoreInfo(){
		// 如果sinfoId > 0，请求店铺信息
		if(sinfoId <= 0){
			$('#submitButton').html('<input type="button" class="btn btn-success" value="保存" onclick="return addStoreInfo();"/>');
			return;
		}

		$('#starCommentTabLi').show();
		$('#levelTabLi').show();
		requestNode('/store/admin/getstoreinfo', 'get', {sinfoId:sinfoId,langCode:lang}, function(data){
			storeInfo = data;
			
			$('#storeName').val(data.storeName);
			$('#storeNameEn').val(data.storeNameEn);
			$('#lnglat').val(data.lng+','+data.lat);
			$("#storeIdID").val(data.storeId);
			
			if(data.storeId > 0){
				selectStore(data.storeId);
			} else {
				$('#showModalName').html('请选择餐厅').attr('href', 'javascript:showModal();');
				$('#cancelBind').attr('disabled', 'disabled');
			}

			if(parseInt(data.showMazingPay) === 1){
				$('#showMazingPayYes').attr('checked', 'checked');
				$('#showMazingPayNo').removeAttr('checked');
			} else {
				$('#showMazingPayYes').removeAttr('checked');
				$('#showMazingPayNo').attr('checked', 'checked');
			}

			if(parseInt(data.showOnlineStore) === 1){
				$('#showOnlineStoreYes').attr('checked', 'checked');
				$('#showOnlineStoreNo').removeAttr('checked');
			} else {
				$('#showOnlineStoreYes').removeAttr('checked');
				$('#showOnlineStoreNo').attr('checked', 'checked');
			}

			if(parseInt(data.canShare) === 1){
				$('#canShareYes').attr('checked', 'checked');
				$('#canShareNo').removeAttr('checked');
			} else {
				$('#canShareYes').removeAttr('checked');
				$('#canShareNo').attr('checked', 'checked');
			}

			loadProvinceConfig(data);
			$('#address').val(data.address);

			$('#description').val(data.description);

			$('#stars').val(data.stars);
			$('#delicious').val(data.delicious);
			$('#deliciousOrigin').val(data.delicious);
			$('#quality').val(data.quality);
			$('#qualityOrigin').val(data.quality);
			$('#decoration').val(data.decoration);
			$('#decorationOrigin').val(data.decoration);
			$('#surround').val(data.surround);
			$('#surroundOrigin').val(data.surround);
			$('#feeling').val(data.feeling);
			$('#feelingOrigin').val(data.feeling);

			requestNode('/store/admin/getstoreinfotags', 'get', {sinfoId:sinfoId}, function(data){
				storeInfoTags = data;
				
				$.ajax({
					  url: '/admin/store/loadStoreTag?storeId=16301',
					  method: 'get',
					  error:function(){
							MyDialog.alert('请求java服务器出错！');
					  },
					  success:function(json){
						if (!json.success) {
							MyDialog.alert(json.message);
						} else {
							var tagPool = {};
							for(var category of json.object){
								for(var _tag of category.tags){
									tagPool[_tag.tagId] = _tag.tagName;
								}
							}

							var html = '', tags = '', tagString = '';
							for(var tag of data){
								tags += tag.tagId;
								tags += ',';

								tagString += tagPool[tag.tagId];
								tagString += ',';
							}

							tags = tags.substring(0, tags.length - 1);
							tagString = tagString.substring(0, tagString.length - 1)
							html += '<input type="hidden" id="tags" name="tags" value="' + tags + '"/>';
							html += '<input type="hidden" id="tagNames" name="tagNames" value="' + tagString + '"/>';
							html += tagString;
							html += '<br>';
							html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectTags();">选择标签</a>';
							$('#tagsTD').html(html);
						}
					  }
				  });
			});

			$.ajax({
				url: '/admin/appIndex/storeTag/list?page=1&pageSize=500',
				method: 'get',
				error:function(){
					MyDialog.alert('请求java服务器出错！');
				},
				success:function(res){
					var list = [];
					for(var _data of res.object){
						if(_data.categoryName === '菜系'){
							list = _data;
							break;
						}
					}

					var cuisinePool = {}, cuisineString = '';
					for(var cuisine of list.tags){
						cuisinePool[cuisine.tagId] = cuisine.tagName;
					}

					for(var tagId of data.cuisine.split(',')){
						cuisineString += cuisinePool[tagId];
						cuisineString += '，';
					}

					var html = '';
					html += '<input type="hidden" id="cuisine" name="cuisine" value="' + data.cuisine + '"/>';
					html += cuisineString.substring(0, cuisineString.length - 1);
					html += '<br>';
					html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectCuisine();">选择菜系</a>';
					$('#cuisineTD').html(html);
				}
			});

			$('#perConsume').val(data.perConsume);
			$('#recDishs').val(data.recDishs);

			requestNode('/store/admin/supportingconfig', 'get', {}, function(res){
				var supportingPool = {}, supportingString = '';
				for(var supporting of res){
					supportingPool[supporting.id] = supporting.name;
				}

				for(var supportingId of data.supporting){
					supportingString += supportingPool[supportingId];
					supportingString += '，';
				}

				var html = '';
				html += '<input type="hidden" id="supporting" name="supporting" value="' + data.supporting + '"/>';
				html += supportingString.substring(0, supportingString.length - 1);
				html += '<br>';
				html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectSupporting();">选择配套设施</a>';
				$('#supportingTD').html(html);
			});

			var phones = data.phone;
			$('#phone1').val(phones[0]); 	  
			$('#phone2').val(phones[1]);

			openHoursNum = 1;
			$('#openHours').html('');
			for(var _openHours of data.openHours){
				openHours(_openHours);
			}

			$('#logoPreview').attr('src', IMAGE_DOMAIN + data.logo);
			$('#logoInput').val(data.logo);
			
			$('#mapImgPreview').attr('src', IMAGE_DOMAIN + data.mapImg);
			$('#mapImgInput').val(data.mapImg);

			var showImgsSize = data.showImgs.length;
			$('#showImgs').html('');
			for(var indexImg of data.showImgs){
				if(!indexImg){
					continue;
				}				
				var url = IMAGE_DOMAIN + indexImg;
				var html = '<div style="float:left"><img id="showImgsPreview'+showImgsSize+'" width="50" src="'+url+'" style="cursor:pointer;" title="点击图片删除" onClick="if(confirm(\'你确定要删除吗？\')){$(this).parent().remove();}else{return false;}"/><input type="hidden" id="showImgsInput'+showImgsSize+'" value="'+indexImg+'"/>&nbsp;&nbsp;</div>';
				$('#showImgs').append(html);
				showImgsSize -= 1;
			}

			$('#thumbImgPreview').attr('src', IMAGE_DOMAIN + data.thumbImg);
			$('#thumbImgInput').val(data.thumbImg);

			var idRecommendImgSize = data.idRecommendImg.length;
			$('#idRecommendImg').html('');
			for(var recommendImg of data.idRecommendImg){
				if(!recommendImg){
					continue;
				}
				var url = IMAGE_DOMAIN + recommendImg;
				var html = '<div style="float:left"><img id="idRecommendImgPreview'+idRecommendImgSize+'" width="50" src="'+url+'" style="cursor:pointer;" title="点击图片删除" onClick="if(confirm(\'你确定要删除吗？\')){$(this).parent().remove();}else{return false;}"/><input type="hidden" id="idRecommendImgInput'+idRecommendImgSize+'" value="'+recommendImg+'"/>&nbsp;&nbsp;</div>';
				$('#idRecommendImg').append(html);
				idRecommendImgSize -= 1;
			}

			// 是否显示星评
			$('#reviewAgreeCount').attr('value', data.reviewAgreeCount);
			$('#commentCount').html(data.commentCount);
			if(data.reviewerPic){
				var url = IMAGE_DOMAIN + data.reviewerPic;
				$('#reviewerPicPreview').attr('src', url);
				$('#reviewerPicInput').val(data.reviewerPic);
				$('#reviewer').val(data.reviewer);
				$('#reviews').val(data.reviews);
				$('#reviewTime').html(moment(data.reviewTime * 1000).format('YYYY-MM-DD HH:mm:ss'));
				$('#updateReviewButton').attr('value', '修改');
				$('#reviewAgreeCountOrigin').attr('value', data.reviewAgreeCount);
				$('#reviewTimeTable').css('display','');
				$('#reviewAgreeCountTable').css('display','');
			} else {
				$('#reviewTips').css('display','block');
				$('#reviewerTips').css('display','block');
				$('#reviewTimeTable').css('display','none');
				$('#reviewAgreeCountTable').css('display','none');
				$('#reviewAgreeCountOrigin').attr('value', -1);
				getAdminInfo();
			}

			$('#submitButton').html('<input type="button" class="btn btn-success" value="修改" onclick="return updateStoreInfo();"/>');
		});
	}

	function uploadLogo(){
		uploadFile($('#logoFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#logoPreview').attr('src', url);
			$('#logoInput').val(data);
		});
	}
	
	function uploadMapImg(){
		uploadFile($('#mapImgFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#mapImgPreview').attr('src', url);
			$('#mapImgInput').val(data);
		});
	}
	
	function uploadThumbImg(){
		uploadFile($('#thumbImgFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#thumbImgPreview').attr('src', url);
			$('#thumbImgInput').val(data);
		});
	}

	function uploadMapImg(){
		uploadFile($('#mapImgFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#mapImgPreview').attr('src', url);
			$('#mapImgInput').val(data);
		});
	}

	function uploadReviewerPic(){
		uploadFile($('#reviewerPicFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			$('#reviewerPicPreview').attr('src', url);
			$('#reviewerPicInput').val(data);
		});
	}

	function addShowImgs(){
		var size = $('#showImgs img').length + 1;
		uploadFile($('#showImgsFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			var html = '<div style="float:left"><img id="showImgsPreview'+size+'" width="50" src="'+url+'" style="cursor:pointer;" title="点击图片删除" onClick="if(confirm(\'你确定要删除吗？\')){$(this).parent().remove();}else{return false;}"/><input type="hidden" id="showImgsInput'+size+'" value="'+data+'"/>&nbsp;&nbsp;</div>';
			$('#showImgs').append(html);
		});
	}

	function addIdRecommendImgs(){
		var size = $('#idRecommendImg img').length + 1;
		uploadFile($('#idRecommendImgFile'), function(data){
			var url = IMAGE_DOMAIN + data;
			var html = '<div style="float:left"><img id="idRecommendImgPreview'+size+'" width="50" src="'+url+'" style="cursor:pointer;" title="点击图片删除" onClick="if(confirm(\'你确定要删除吗？\')){$(this).parent().remove();}else{return false;}"/><input type="hidden" id="idRecommendImgInput'+size+'" value="'+data+'"/>&nbsp;&nbsp;</div>';
			$('#idRecommendImg').append(html);
		});
	}

	function loadProvinceConfig(sinfo){
		requestNode('/store/admin/areaconfig', 'get', {level:2,pcode:86}, function(data){
			var html = '';
			for(var province of data){
				if(sinfo && sinfo.provinceCode == province.code){
					html += '<option value="'+province.code+'" selected>'+province.nameCn+'</option>';
				} else {
					html += '<option value="'+province.code+'">'+province.nameCn+'</option>';
				}
			}
			$('#provinceCode').html(html);
			loadCityConfig(sinfo);
		});
	}

	function loadCityConfig(sinfo){
		var provinceId = 0;
		if(sinfo){
			provinceId = sinfo.provinceCode;
		} else {
			provinceId = $('#provinceCode').val();
		}
		requestNode('/store/admin/areaconfig', 'get', {level:3,pcode:provinceId}, function(data){
			var html = '';
			for(var city of data){
				if(sinfo && sinfo.cityCode == city.code){
					html += '<option value="'+city.code+'" selected>'+city.nameCn+'</option>';
				} else {
					html += '<option value="'+city.code+'">'+city.nameCn+'</option>';
				}
			}
			$('#cityCode').html(html);
			loadDistrictConfig(sinfo);
		});
	}

	function loadDistrictConfig(sinfo){
		var cityId = 0;
		if(sinfo){
			cityId = sinfo.cityCode;
		} else {
			cityId = $('#cityCode').val();
		}
		requestNode('/store/admin/areaconfig', 'get', {level:4,pcode:cityId}, function(data){
			var html = '';
			for(var district of data){
				if(sinfo && sinfo.districtCode == district.code){
					html += '<option value="'+district.code+'" selected>'+district.nameCn+'</option>';
				} else {
					html += '<option value="'+district.code+'">'+district.nameCn+'</option>';
				}
			}
			$('#districtCode').html(html);
			getBusinessDistrict(sinfo);
		});
	}

	function getBusinessDistrict(sinfo){
		var cityId = $('#cityCode').val();
		var districtId = $('#districtCode').val();
		requestNode('/store/admin/getbusinessdistrictconfig', 'get', {cityCode:cityId,districtCode:districtId}, function(data){
			var html = '<option value="0">==选择商圈==</option>';
			for(var i = 0; i < data.length; i++){
				var _data = data[i];
				if(sinfo && sinfo.businessDistrictCode == _data.code){
					html += '<option value="' + _data.code + '" selected>' + _data.nameCn +'</option>';
				} else {
					html += '<option value="' + _data.code + '">' + _data.nameCn +'</option>';
				}
			}
			$('#businessDistrictCode').html(html);
		});
	}

	function confirmCuisine(){
		var cuisine = '', cuisineString = '';
		var num = parseInt($('#cuisineList li').length);
		for(var i = 0; i < num; i++){
			var obj = $('#cuisineList li:eq(' + i + ') input[type="checkbox"]');
			if(obj.is(":checked")){
				cuisineString += obj.parent().html().replace(/<[^>]+>/g, "");
				cuisineString += '，';

				cuisine += obj.val();
				cuisine += ',';
			}
		}

		cuisineString = cuisineString.substring(0, cuisineString.length - 1);
		if(cuisineString.length <= 0){
			MyDialog.alert('至少要选择一项菜系！');
			getCuisine();
			return false;
		}

		var html = '';
		html += '<input type="hidden" id="cuisine" name="cuisine" value="' + cuisine.substring(0, cuisine.length - 1) + '"/>';
		html += cuisineString;
		html += '<br>';
		html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectCuisine();">选择菜系</a>';
		$('#cuisineTD').html(html);
	}

	function selectCuisine(){
		getCuisine();
	}

	function getCuisine(){
		$.ajax({
			  url: '/admin/appIndex/storeTag/list?page=1&pageSize=500',
			  method: 'get',
			  error:function(){
					MyDialog.alert('请求java服务器出错！');
			  },
			  success:function(res){
				  var data = [];
				  for(var _data of res.object){
					  if(_data.categoryName === '菜系'){
						  data = _data;
						  break;
					  }
				  }

					var html = '', cuisineArr = [];
					if(storeInfo && storeInfo.cuisine){
						for(var c of storeInfo.cuisine.split(',')){
							cuisineArr.push(parseInt(c));
						}
					}
				  
				  for(var cuisine of data.tags){
					  html += '<li><label><input type="checkbox" name="cuisine[]" value="' + cuisine.tagId + '"'+(parseInt(cuisineArr.indexOf(cuisine.tagId)) > -1 ? ' checked="checked"' : '') +'/>' + cuisine.tagName + '</label></li>';
				  }
				  $('#cuisineList').html(html);
				  $('#cuisineModal').modal();
			  }
		  });
	}

	function selectSupporting(){
			getSupporting();
	}

    function getSupporting(){
		requestNode('/store/admin/supportingconfig', 'get', {}, function(data){
			$('#supportingModal').modal();
			var html = '', supportingArr = [];
			
			if(storeInfo && storeInfo.supporting){
				for(var s of storeInfo.supporting){
					supportingArr.push(parseInt(s));
				}
			}
	
			for(var supporting of data){
				html += '<li><label><input type="checkbox" name="supporting[]" value="' + supporting.id + '"'+(parseInt(supportingArr.indexOf(supporting.id)) > -1 ? ' checked="checked"' : '') +'/>' + supporting.name + '</label></li>';
			}
			$('#supportingList').html(html);
		});
	}

	function confirmSupporting(){
		var supporting = '', supportingString = '';
		var num = parseInt($('#supportingList li').length);
		for(var i = 0; i < num; i++){
			var obj = $('#supportingList li:eq(' + i + ') input[type="checkbox"]');
			if(obj.is(":checked")){
				supportingString += obj.parent().html().replace(/<[^>]+>/g, "");
				supportingString += '，';

				supporting += obj.val();
				supporting += ',';
			}
		}

		supportingString = supportingString.substring(0, supportingString.length - 1);
		if(supportingString.length <= 0){
			MyDialog.alert('至少要选择一项配套！');
			getSupporting();
			return false;
		}
		
		var html = '';
		html += '<input type="hidden" id="supporting" name="supporting" value="' + supporting.substring(0, supporting.length - 1) + '"/>';
		html += supportingString;
		html += '<br>';
		html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectSupporting();">选择配套设施</a>';
		$('#supportingTD').html(html);
	}

	function selectTags(){
		getTags();
	}

	function getTags(){
		$.ajax({
			  url: '/admin/store/loadStoreTag?storeId=16301',
			  method: 'get',
			  error:function(){
					MyDialog.alert('请求java服务器出错！');
			  },
			  success:function(json){
				if (!json.success) {
					MyDialog.alert(json.message);
				} else {
					var htmlTxt = template('storeTagTemplate', json);
					$('#tagContainer').html(htmlTxt);
				}
				  
				// 显示选中项
				var tagsArr = [];
				if(storeInfoTags && storeInfoTags.length > 0){
					for(var t of storeInfoTags){
						tagsArr.push(parseInt(t.tagId));
					}
				}
				  
				$('#tagContainer input[type="checkbox"]').each(function(i,e){
					var value = parseInt($(e).attr('value')) || 0;
					if(parseInt(tagsArr.indexOf(value)) > -1){
						$(e).attr('checked', 'checked');
					}
				});
				  
				$('#tagsModal').modal();
			  }
		  });
	}

	function confirmTags(){
		var tags = '', tagsString = '';
		var num = parseInt($('#tagsModal input[name="storeTag"]').length);
		for(var i = 0; i < num; i++){
			var obj = $('#tagsModal input[name="storeTag"]:eq(' + i + ')');
			if(obj.is(":checked")){
				tagsString += obj.parent().html().replace(/<[^>]+>/g, "");
				tagsString += ',';

				tags += obj.val();
				tags += ',';
			}
		}

		tagsString = tagsString.substring(0, tagsString.length - 1);
		if(tagsString.length <= 0){
			MyDialog.alert('至少要选择一项标签！');
			getTags();
			return false;
		}

		var html = '';
		html += '<input type="hidden" id="tags" name="tags" value="' + tags.substring(0, tags.length - 1) + '"/>';
		html += '<input type="hidden" id="tagNames" name="tagNames" value="' + tagsString + '"/>';
		html += tagsString;
		html += '<br>';
		html += '<a style="cursor:pointer" href="javascript:void(0)" onClick="selectTags();">选择标签</a>';
		$('#tagsTD').html(html);
	}


	function getBrandInfo(){
		requestNode('/store/admin/getbrand', 'get', {brandId:${brandId}}, function(data){
			$('#brandName').val(data.brandName);
		});
	}


	function openHours(data){
		var html = '', openHoursIndex = openHoursNum++;
        html += '<div class="col-xs-12" id="openHours'+openHoursIndex+'">';
		html += '<label>营业日：</label>';

		isBsdaysAll = data && data.days === 'all';
		var isBs24hours = data && parseInt(data.hours) === 24;

		if(isBsdaysAll){
			html += '<label>整周<input class="bsdays" type="checkbox" name="bsdaysAll" value="all" onClick="bsdaysAll($(this).parent().parent());" checked="checked"/></label>';
		} else {
        	html += '<label>整周<input class="bsdays" type="checkbox" name="bsdaysAll" value="all" onClick="bsdaysAll($(this).parent().parent());"/></label>';
		}
        html += '<label>&nbsp;|&nbsp;</label>';
		if(isBsdaysAll){
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="mon" disabled="disabled"/>周一</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="tue" disabled="disabled"/>周二</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="wed" disabled="disabled"/>周三</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="thu" disabled="disabled"/>周四</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="fri" disabled="disabled"/>周五</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sat" disabled="disabled"/>周六</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sun" disabled="disabled"/>周日</label>';
		} else if(data && Array.isArray(data.days)) {
			var weekArray = ['mon','tue','wed','thu','fri','sat','sun'];
			var weekStrArray = ['周一','周二','周三','周四','周五','周六','周日'];
			for(var i = 0; i < 7; i++){
				var week = weekArray[i];
				var weekStr = weekStrArray[i];
				if(data.days.indexOf(week) > -1){
					html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="'+week+'" checked="checked" onClick="weekdaysOnClick(this);"/>'+weekStr+'</label>';
				} else {
					html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="'+week+'" onClick="weekdaysOnClick(this);"/>'+weekStr+'</label>';
				}
			}
		} else {
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="mon" onClick="weekdaysOnClick(this);"/>周一</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="tue" onClick="weekdaysOnClick(this);"/>周二</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="wed" onClick="weekdaysOnClick(this);"/>周三</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="thu" onClick="weekdaysOnClick(this);"/>周四</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="fri" onClick="weekdaysOnClick(this);"/>周五</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sat" onClick="weekdaysOnClick(this);"/>周六</label>';
			html += '<label><input class="weekdays" name="bsdays" type="checkbox" value="sun" onClick="weekdaysOnClick(this);"/>周日</label>';
		}
		html += '<br>';
		html += '<label>营业时间：</label>';

		if(isBs24hours){
			html += '<label>24小时营业<input class="bs24hours" name="bs24hours" type="checkbox" value="24" onClick="bs24hours($(this).parent().parent());"  checked="checked"/></label>';
		} else {
			html += '<label>24小时营业<input class="bs24hours" name="bs24hours" type="checkbox" value="24" onClick="bs24hours($(this).parent().parent());"/></label>';
		}
        html += '<label>&nbsp;|&nbsp;</label><label>';

		if(isBs24hours){
			html += '<select class="form-control st" disabled="disabled">';
			html += '<option value="">========</option>';
			html += showTimePeriod();
			html += '</select>';
			html += '</label>';
			html += '&nbsp;&nbsp;至&nbsp;&nbsp;';
			html += '<label>';
			html += '<select class="form-control et" disabled="disabled">';
			html += '<option value="">========</option>';
			html += showTimePeriod();
			html += '</select>';
			html += '</label>';
		} else {
			var hours = data ? data.hours : '';
			var st = data ? hours.substring(0, hours.indexOf('-')) : '';
			var et = data ? hours.substring(hours.indexOf('-') + 1) : '';
			html += '<select class="form-control st">';
			html += '<option value="">========</option>';
			html += showTimePeriod(st);
			html += '</select>';
			html += '</label>';
			html += '&nbsp;&nbsp;至&nbsp;&nbsp;';
			html += '<label>';
			html += '<select class="form-control et">';
			html += '<option value="">========</option>';
			html += showTimePeriod(et);
			html += '</select>';
			html += '</label>';
		}
        html += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
		if(openHoursNum > 2){
        	html += '<a href="javascript:void(0)" onClick="removeOpenHours();">删除营业时间</a>';
		}
        html += '</div>';
		$('#openHours').append(html);
	}

	function removeOpenHours(){
		openHoursNum--;
		$('#openHours'+openHoursNum).remove();
	}

	function showTimePeriod(time){
		var html = '';
		for(var i = 1451577600; i < 1451664000; i += 1800){
			var t = moment.unix(i).format('HH:mm');
			if(t === time){
				html += '<option value="' + t + '" selected="selected">' + t + '</option>';
			} else {
				html += '<option value="' + t + '">' + t + '</option>';
			}
		}

		// 次日
		for(var i = 1451664000; i <= 1451707200; i += 1800){
			var t = '1:' + moment.unix(i).format('HH:mm');
			if(t === time){
				html += '<option value="' + t + '" selected="selected">' + t.substring(2) + '(次日)</option>';
			} else {
				html += '<option value="' + t + '">' + t.substring(2) + '(次日)</option>';
			}
		}
		return html;
	}

	function bsdaysAll(obj){
		var id = obj.attr('id');
		var checked = $('#'+id+' .bsdays').attr('checked');
		if(checked){
			$('#'+id+' .weekdays').removeAttr('disabled');
			$('#'+id+' .bsdays').removeAttr('checked');
		} else {
			$('#'+id+' .weekdays').attr('disabled', 'disabled');
			$('#'+id+' .weekdays').removeAttr('checked');
			$('#'+id+' .bsdays').attr('checked', 'checked');
		}
	}
	
	function weekdaysOnClick(obj){
		obj = $(obj);
		var checked = obj.attr('checked');
		if(checked){
			obj.removeAttr('checked');
		} else {
			obj.attr('checked', 'checked');
		}
	}

	function bs24hours(obj){
		var id = obj.attr('id');
		obj = $('#'+id+' .form-control');
		var disabled = obj.attr('disabled');
		if(disabled){
			obj.removeAttr('disabled');
		} else {
			obj.attr('disabled', 'disabled');
		}
	}

	function showModal() {
        $("#storeModal").modal("show");
        searchStore();
    }

    function cancelBindStore() {
        $("#storeIdID").val(0);
       // $('#storeName').val('');
       // $('#storeNameEn').val('');
		//$('#lnglat').val('');
        $('#showModalName').html('请选择餐厅').attr('href', 'javascript:showModal();');
		$('#cancelBind').attr('disabled', 'disabled');
        MyDialog.alert("取消关联成功");
    }

    function searchStore() {
        var keyword = $('#storeKeyword').val();
        $('#storeTable').artPaginate({
            type : 'post',
            url : "/admin/store/query",// 获取数据的地址
            paginator : 'storePaginator',// 显示页码的位置
            tpl : 'storeTableTpl',// 模版ID
            params : { 'keyword' : keyword },// 请求的参数表
            pageSize : 10// 每页多少条数据（默认：page=1,pageSize=20）
        });
    }
    // 选中了指定的餐厅
    function selectStore(storeId) {
        $("#storeIdID").val(storeId);

        $.getJSON('/admin/store/storeDetail?storeId=' + storeId, {}, function(json) {
            if (json.success) {
				// 有名字不覆盖，无名字时覆盖
				if(!$('#storeName').val()){
					$('#storeName').val(json.object.store.storeName);
				}
                if(!$('#storeNameEn').val()){
                	$('#storeNameEn').val(json.object.store.storeName);
				}
                $('#showModalName').html(json.object.store.storeName).removeAttr('href');
				$('#lnglat').val(json.object.store.lng+','+json.object.store.lat);
				$('#cancelBind').removeAttr('disabled');
            } else {
                MyDialog.alert(json.message);
            }
            $('#storeModal').modal('hide');
        });
    }

	function check(){
		var data = {};

        var storeName = $('#storeName').val() || '';
        if(!storeName){
            MyDialog.alert('请输中文店名！');
            return false;
        }
		data.storeName = storeName;

		var storeNameEn = $('#storeNameEn').val() || '';
		data.storeNameEn = storeNameEn;

		/*
        var description = $('#description').val().trim();
        if(!description){
            MyDialog.alert('请输入店铺描述！');
            return false;
        }
		if(description.length > 1024){
			MyDialog.alert('店铺描述不能超过1024字！');
            return false;
		}
		data.description = description;
		*/

        var tags = $('#tags').val().trim();
		var tagNames = $('#tagNames').val().trim();
        if(!tags || !tagNames){
            MyDialog.alert('请选择餐厅标签！');
            return false;
        }
		data.tags = tags;
		data.tagNames = tagNames;

        var cuisine = $('#cuisine').val().trim();
        if(!cuisine){
            MyDialog.alert('请选择菜系！');
            return false;
        }
		data.cuisine = cuisine;

        var perConsume = $('#perConsume').val().trim();
        if(!perConsume){
            MyDialog.alert('请输入人均消费！');
            return false;
		}
		if(perConsume != parseInt(perConsume)){
            MyDialog.alert('人均消费必须是正整数！');
            return false;
        }
        perConsume = parseInt(perConsume) || 0;
        if(perConsume <= 0){
            MyDialog.alert('人均消费必须是正整数！');
            return false;
        }
		if(perConsume > 2147483647){
			 MyDialog.alert('人均消费不可以超过2147483647！');
            return false;
		}
		data.perConsume = perConsume;

        var businessDistrictCode = parseInt($('#businessDistrictCode').val()) || 0;
        if(businessDistrictCode <= 0){
            MyDialog.alert('请选择商圈！');
            return false;
        }
		data.provinceCode = $('#provinceCode').val().trim();
		data.cityCode = $('#cityCode').val().trim();
		data.districtCode = $('#districtCode').val().trim();
		data.businessDistrictCode = businessDistrictCode;

		var address = $('#address').val().trim();
        if(!address){
            MyDialog.alert('请输入详细地址！');
            return false;
        }
		if(address.length > 64){
			MyDialog.alert('详细地址不能超过64字！');
            return false;
		}
		data.address = address;

		var lnglat = $('#lnglat').val().trim();
        if(!lnglat){
            MyDialog.alert('请输入经纬度！');
            return false;
        }
		data.lnglat = lnglat;
		
		// 限制录入经度在+-180度内，纬度在+-90度内
		var lnglatArr = lnglat.split(',');
		var lngValue = parseFloat(lnglatArr[0]) || 0;
		var latValue = parseFloat(lnglatArr[1]) || 0;
		if(lngValue > 0 && (lngValue < -180 || lngValue > 180)){
			MyDialog.alert('经度应在+-180度内！');
            return false;
		}
		
		if(latValue > 0 && (latValue < -90 || latValue > 90)){
			MyDialog.alert('纬度应在+-90度内！');
            return false;
		}

        var recDishs = $('#recDishs').val().trim();
        if(!recDishs){
            MyDialog.alert('请输入推荐菜！');
            return false;
        }
		data.recDishs = recDishs;

		var supporting = $('#supporting').val().trim();
        if(!supporting){
            MyDialog.alert('请选择配套设施！');
            return false;
        }
		data.supporting = supporting;

		var regexp = /^([0-9]+\-?[0-9]+){1,30}$/;
		var phone1 = $('#phone1').val().trim().replace(',','');	
		var phone2 = $('#phone2').val().trim().replace(',','');
        if(!phone1 && !phone2){
            MyDialog.alert('至少输入一个电话！');
            return false;
        }
		data.phone = phone1;
		if(phone2){
			data.phone = data.phone + ',' + phone2;
		}
		
		/*
		if(!regexp.test(phone1)){
			MyDialog.alert('电话1输入错误！请输入正确的手机号码：例如：13800138000或有国际区号的：0086-13800138000！');
			return false;
		}
		
		if(phone2 && !regexp.test(phone2)){
			MyDialog.alert('电话2输入错误！请输入正确的手机号码：例如：13800138000或有国际区号的：0086-13800138000！');
			return false;
		}
		*/

		// 验证每一个营业时间段是否正确
		// 同一日期，同一时间段不可以重叠
		data.openHours = [];
		let size = $('#openHours div').length;
		for(var i = 0; i < size; i++){
			var _openHours = {};

			var isBsdaysChecked = $('.bsdays:eq('+i+')').is(":checked");
			var isWeekdaysChecked1 = $('.weekdays:eq('+(0+i*7)+')').is(":checked");
			var isWeekdaysChecked2 = $('.weekdays:eq('+(1+i*7)+')').is(":checked");
			var isWeekdaysChecked3 = $('.weekdays:eq('+(2+i*7)+')').is(":checked");
			var isWeekdaysChecked4 = $('.weekdays:eq('+(3+i*7)+')').is(":checked");
			var isWeekdaysChecked5 = $('.weekdays:eq('+(4+i*7)+')').is(":checked");
			var isWeekdaysChecked6 = $('.weekdays:eq('+(5+i*7)+')').is(":checked");
			var isWeekdaysChecked7 = $('.weekdays:eq('+(6+i*7)+')').is(":checked");
			var isWeekdaysChecked = isWeekdaysChecked1 || isWeekdaysChecked2 || isWeekdaysChecked3 || isWeekdaysChecked4 || isWeekdaysChecked5 || isWeekdaysChecked6 || isWeekdaysChecked7;

			if(!isBsdaysChecked && !isWeekdaysChecked){
				MyDialog.alert('第'+(i+1)+'块营业时间配置未选择营业日！');
				return false;
			}

			if(isBsdaysChecked){
				_openHours.days = 'all';
				isBsdaysAll = true;
			} else {
				isBsdaysAll = isBsdaysAll ? isBsdaysAll : false;// 如果已经是true，则无需理会
				_openHours.days = [];

				if(isWeekdaysChecked1){
					_openHours.days.push('mon');
				}
				if(isWeekdaysChecked2){
					_openHours.days.push('tue');
				}
				if(isWeekdaysChecked3){
					_openHours.days.push('wed');
				}
				if(isWeekdaysChecked4){
					_openHours.days.push('thu');
				}
				if(isWeekdaysChecked5){
					_openHours.days.push('fri');
				}
				if(isWeekdaysChecked6){
					_openHours.days.push('sat');
				}
				if(isWeekdaysChecked6){
					_openHours.days.push('sun');
				}
			}

			var st = parseInt($('.st:eq('+i+')').val().replace(new RegExp(/:/g),'')) || 0;
			var et = parseInt($('.et:eq('+i+')').val().replace(new RegExp(/:/g),'')) || 0;

			if(st >= 10000){
				MyDialog.alert('营业开始时间不可以选次日！');
				return false;
			}

			var isBs24hoursChecked = $('.bs24hours:eq('+i+')').is(":checked");
			if(!isBs24hoursChecked && st >= et){
				MyDialog.alert('第'+(i+1)+'块营业时间配置营业时间配置有误，必须选择“24小时营业”或者起始时间不可小于等于结束时间！');
            	return false;
			}

			if(isBs24hoursChecked){
				_openHours.hours = 24;
			} else {
				_openHours.hours = $('.st:eq('+i+')').val() + '-' + $('.et:eq('+i+')').val();
			}
			
			data.openHours.push(_openHours);
		}
		
		// 如果选择了全部的，就不可以再选择零碎时间
//		if(isBsdaysAll && size > 1){
//			MyDialog.alert('营业时间设置有误！营业日选择了“整周”的，就不可以再选择其他营业时间！');
//            return false;
//		}
		
		// 判断一下，如果同一日期的时间段有重复，就报错
		var cpOpenHours = JSON.parse(JSON.stringify(data.openHours));
		for(var i = 0; i < data.openHours.length; i++){
			var op = data.openHours[i];
			for(var j = 0; j < cpOpenHours.length; j++){
				var _openHours = cpOpenHours[j];
				
				if(i === j){
					continue;
				}
				
				if(op.days.toString() !== _openHours.days.toString()){
					continue;
				}

				var opArr = op.hours.toString().split('-');
				var _opst = parseInt(opArr[0].toString().replace(new RegExp(/:/g),'')) || 0;
				var _opet = opArr.length > 1 ? parseInt(opArr[1].toString().replace(new RegExp(/:/g),'')) || 0 : 24;

				var _openHoursArr = _openHours.hours.toString().split('-');
				var _openHoursst = parseInt(_openHoursArr[0].toString().replace(new RegExp(/:/g),'')) || 0;
				var _openHourset = _openHoursArr.length > 1 ? parseInt(_openHoursArr[1].toString().replace(new RegExp(/:/g),'')) || 0 : 24;
				
				// 只要有一个24小时营业，就要报错
				if((_opst === 24 && _opet === 24) || (_openHoursst === 24 && _openHourset === 24)){
					MyDialog.alert('第'+(i+1)+'块营业时间配置有误，同一天内已设置24小时营业，请勿设置重叠的营业时间！');
            		return false;
				}
				
				// 判断时间段有没有重叠
				if(_opst <= _openHoursst && _opet >= _openHourset){
					alert('第'+(i+1)+'块营业时间配置有误，同一天内的营业时间有重叠，请勿设置重叠的营业时间！');
            		return false;
				}
			}	
		}
		
		var logo = $('#logoInput').val().trim();
		if(!logo){
            MyDialog.alert('请上传餐厅logo！');
            return false;
        }
		data.logo = logo;

		var showImgNums = parseInt($('#showImgs img').length) || 0;
		if(showImgNums <= 0){
            MyDialog.alert('请至少上传一张首页轮播图！');
            return false;
        }
		data.showImgs = [];
		var showImgsElementArray = $('#showImgs div input');
		for(var i=0; i<showImgsElementArray.length;i++){
			var showImgsInput = $(showImgsElementArray[i]).val() || '';
			if(!showImgsInput){
				continue;
			}
			data.showImgs.push(showImgsInput.trim());
		}
		if(data.showImgs.length <= 0){
			MyDialog.alert('请至少上传一张首页轮播图！');
            return false;
		}

		var thumbImg = $('#thumbImgInput').val();
		if(!thumbImg){
            MyDialog.alert('请上传缩略图！');
            return false;
        }
		data.thumbImg = thumbImg;
		
		var mapImg = $('#mapImgInput').val();
		if(!mapImg){
            MyDialog.alert('请上传地图缩略图！');
            return false;
        }
		data.mapImg = mapImg;

		var idRecommendImgNums = parseInt($('#idRecommendImg img').length) || 0;
		if(idRecommendImgNums <= 0){
            MyDialog.alert('请至少上传一张ID推荐图！');
            return false;
        }
		data.idRecommendImg = [];
		var idRecommendImgElementArray = $('#idRecommendImg div input');
		for(var i=0; i<idRecommendImgElementArray.length;i++){
			var idRecommendImgInput = $(idRecommendImgElementArray[i]).val() || '';
			if(!idRecommendImgInput){
				continue;
			}
			data.idRecommendImg.push(idRecommendImgInput.trim());
		}
		if(data.idRecommendImg.length <= 0){
			MyDialog.alert('请至少上传一张ID推荐图！');
            return false;
		}

		data.langCode = lang;
		data.brandId = ${brandId};
		data.storeId = parseInt($("#storeIdID").val().trim()) || 0;
		data.openHours = JSON.stringify(data.openHours);
		data.showImgs = Array.from(data.showImgs).join();
		data.idRecommendImg = Array.from(data.idRecommendImg).join();
		data.showMazingPay = $('#showMazingPayYes').is(':checked')?1:0;
		data.showOnlineStore = $('#showOnlineStoreYes').is(':checked')?1:0;
		data.canShare = $('#canShareYes').is(':checked')?1:0;
		
		// 如果要显示米星pay，必须要绑定miniblog
		if(data.showMazingPay == 1 && data.storeId <= 0){
			MyDialog.alert('如果要显示米星pay，必须要关联线上餐厅！');
            return false;
		}
		return data;
	}

    function addStoreInfo(){
		var data = check();
		if(!data){
			return false;
		}

		requestNode('/store/admin/addstoreinfo', 'post', data, function(data){
			window.location.href = '/admin/cblog/brandInfo/info.xhtm?brandId=${brandId}';
		});
        return true;
    }

	function updateStoreInfo(){
		var data = check();
		if(!data){
			return false;
		}

		data.sinfoId = sinfoId;
		data.storeId = $("#storeIdID").val();
		data.action = 'updateStoreInfo';
		requestNode('/store/admin/updatestoreinfo', 'post', data, function(data){
			getStoreInfo();
			MyDialog.alert('修改成功！');
		});
        return true;
	}

	function updateReview(){
		var data = {};
		data.sinfoId = sinfoId;
		data.storeId = $("#storeIdID").val();

		var reviewerPic = $('#reviewerPicInput').val();
		if(!reviewerPic){
            MyDialog.alert('请上传星评人头像！');
            return false;
        }
		data.reviewerPic = reviewerPic;

		var reviewer = $('#reviewer').val();
		if(!reviewer){
            MyDialog.alert('请输入星评人名字！');
            return false;
        }
		data.reviewer = reviewer;

		var reviews = $('#reviews').val();
		if(!reviews){
			MyDialog.alert('请输入星评内容！');
            return false;
		}
		if(reviews.length > 1000){
			MyDialog.alert('星评内容不可以超过1000字！');
            return false;
		}
		data.reviews = reviews;

		var recDishs = $('#recDishs').val();
        if(!recDishs){
            MyDialog.alert('请输入推荐菜！');
            return false;
        }
		data.recDishs = recDishs;

		var reviewAgreeCount = parseInt($('#reviewAgreeCount').val()) || 0;
		var reviewAgreeCountOrigin = parseInt($('#reviewAgreeCountOrigin').val()) || 0;
		if(reviewAgreeCount < reviewAgreeCountOrigin){
			MyDialog.alert('新设置的星评点赞数不可以少于原来，原来的点赞数为"'+reviewAgreeCountOrigin+'"！');
            return false;
		}
		data.langCode = lang;
		data.reviewAgreeCount = reviewAgreeCount;
		data.action = reviewAgreeCountOrigin === -1 ? 'addReviews' :'updateReviews';

		requestNode('/store/admin/updatestoreinfo', 'post', data, function(data){
			getStoreInfo();
			if(reviewAgreeCountOrigin === -1){
				MyDialog.alert('添加星评成功！');
			} else {
				MyDialog.alert('修改星评成功！');
			}
		});
		return true;
	}

	function updateStars(){
		var data = {};

		data.sinfoId = sinfoId;
		data.storeId = $("#storeIdID").val();
		data.langCode = 'zh';
		data.action = 'updateStars';
		
		var delicious = parseInt($('#delicious').val()) || 0;
		if(delicious < 0 || delicious > 2147483647){
			MyDialog.alert('佳肴体验得分必须是 0 ~ 2147483647 之间的整数！');
			return false;
		}
		data.delicious = delicious;
		
		var quality = parseInt($('#quality').val()) || 0;
		if(quality < 0 || quality > 2147483647){
			MyDialog.alert('服务品质得分必须是 0 ~ 2147483647 之间的整数！');
			return false;
		}
		data.quality = quality;
		
		var decoration = parseInt($('#decoration').val()) || 0;
		if(decoration < 0 || decoration > 2147483647){
			MyDialog.alert('装饰布局得分必须是 0 ~ 2147483647 之间的整数！');
			return false;
		}
		data.decoration = decoration;
		
		var surround = parseInt($('#surround').val()) || 0;
		if(surround < 0 || surround > 2147483647){
			MyDialog.alert('环境气氛得分必须是 0 ~ 2147483647 之间的整数！');
			return false;
		}
		data.surround = surround;
		
		var feeling = parseInt($('#feeling').val()) || 0;
		if(feeling < 0 || feeling > 2147483647){
			MyDialog.alert('价值感受得分必须是 0 ~ 2147483647 之间的整数！');
			return false;
		}
		data.feeling = feeling;

		var stars = parseInt($('#stars').val()) || 0;
		if(stars <= 0 || stars > 100){
			MyDialog.alert('大星评得分必须是100以内的正整数！');
			return false;
		}
		data.stars = stars;
		
		requestNode('/store/admin/updatestoreinfo', 'post', data, function(data){
			getStoreInfo();
			MyDialog.alert('大星评设置成功！');
		});
		return true;
	}

</script>
</body>
</html>