package com.mazing.services.backend.menu;

import org.junit.Test;
import org.springframework.util.Assert;

import com.yogu.commons.utils.resource.MenuItem;
import com.yogu.commons.utils.resource.SearchResource;

/**
 * 菜单扫描测试
 * @author ten 2015/8/10.
 */
public class ScanMenuTest {

    @Test
    public void testMenu() {
        MenuItem mi = SearchResource.scanMenuTree(new String[] {
           "com/mazing/services/backend/**/*.class"
        });
        Assert.notNull(mi);
        print(mi, 0);
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
