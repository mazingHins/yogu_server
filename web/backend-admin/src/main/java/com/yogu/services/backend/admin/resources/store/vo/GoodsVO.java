package com.yogu.services.backend.admin.resources.store.vo;

import java.util.List;

import com.yogu.services.store.Goods;
import com.yogu.services.store.GoodsTagsVO;

public class GoodsVO extends Goods {
	
	private static final long serialVersionUID = 5877963308383129893L;
	private List<String> contentImgs;
	
	private List<GoodsTagsVO> tagList;

	public List<String> getContentImgs() {
		return contentImgs;
	}

	public void setContentImgs(List<String> contentImgs) {
		this.contentImgs = contentImgs;
	}

	public List<GoodsTagsVO> getTagList() {
		return tagList;
	}

	public void setTagList(List<GoodsTagsVO> tagList) {
		this.tagList = tagList;
	}
	
	
}
