package com.mazing.services.backend.admin.service.impl;

import com.mazing.services.backend.admin.BaseServiceTest;
import com.yogu.commons.utils.resource.MenuItem;
import com.yogu.services.backend.admin.service.MenuService;
import com.yogu.services.backend.admin.util.AppConstants;
import com.yogu.services.backend.admin.util.HtmlMenuGenerator;

import org.junit.Test;

import javax.inject.Inject;

/**
 * 测试菜单
 */
public class MenuServiceImplTest extends BaseServiceTest {

    @Inject
    private MenuService menuService;

    @Test
    public void testGetMenuTree() throws Exception {
//        String[] scanPath = new String[] {SearchResource.DEFAULT_RESOURCE_PATH};
//        int appId = AppConstants.APP_ID_DEFAULT;
//        MenuItem root = SearchResource.scanMenuTree(scanPath);
//
//        menuService.updateMenu(appId, root);

        MenuItem item = menuService.getMenuTree(AppConstants.APP_ID_DEFAULT);
        print(item, 0);
        String html = HtmlMenuGenerator.outputHtml(item);
        System.out.println(html);
    }

    /**
     * 打印树结构
     * @param item
     * @param level
     */
    private void print(MenuItem item, int level) {
        for (int i=0; i < level; i++)
            System.out.print("\t");
        System.out.println(item.getName() + ":" + item.getUrl());
        if (item.getChildren() != null && !item.getChildren().isEmpty()) {
            for (MenuItem m : item.getChildren()) {
                print(m, level + 1);
            }
        }
    }
}