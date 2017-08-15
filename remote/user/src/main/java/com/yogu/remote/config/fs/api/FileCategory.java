package com.yogu.remote.config.fs.api;

/**
 * 文件目录定义
 * @author linyi 2015/5/28.
 */
public enum FileCategory {
    STORE("st", "商店", true),
    PROFILE("pr", "个人信息", true),
    DISH("di", "菜品", true),
    INDEX("index", "首页相关", true),
    ACTIVITY("activity", "活动相关", true),
    ;

    /**
     * bucket id
     */
    private String id;

    /**
     * bucket chinese name, unused
     */
    private String name;

    /**
     * 是否为图片
     */
    private boolean isImage;

    private FileCategory(String id, String name, boolean isImage) {
        this.id = id;
        this.name = name;
        this.isImage = isImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isImage() {
        return isImage;
    }

	/**
	 * 获取后台管理的分类（只有INDEX、ACTIVITY是）
	 */
	public static FileCategory giveBackendCategory(String id) {
		String ids = id.toLowerCase();
		if (INDEX.id.equals(ids))
			return INDEX;
		if (ACTIVITY.id.equals(ids))
			return ACTIVITY;
		return null;
	}

    public static FileCategory fromId(String id) {
        for (FileCategory fc : FileCategory.values()) {
            if (fc.getId().equals(id)) {
                return fc;
            }
        }
        throw new IllegalArgumentException("Cannot find file category for id: " + id);
    }
}
