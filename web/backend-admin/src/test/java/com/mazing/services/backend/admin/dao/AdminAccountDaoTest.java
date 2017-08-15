package com.mazing.services.backend.admin.dao;

import com.mazing.services.backend.admin.BaseServiceTest;
import com.yogu.services.backend.admin.dao.AdminAccountDao;
import com.yogu.services.backend.admin.entry.AdminAccountPO;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Date;

/**
 * 测试
 * @author ten
 */
public class AdminAccountDaoTest extends BaseServiceTest {

    @Autowired
    private AdminAccountDao adminAccountDao;

    @Test
    public void testSave() throws Exception {

        final long uid = 1;

        // 创建记录
        AdminAccountPO po = new AdminAccountPO();
        po.setUid(uid);
        po.setCreateTime(new Date());
        po.setLastModify(po.getCreateTime());
        po.setDefaultData((short) 1);
        po.setMobileNo("18620075025");
        po.setOperator(1);
        po.setRealname("Ten");
        po.setStatus((short) 1);

        adminAccountDao.save(po);

        // 读取是否存在
        AdminAccountPO tmp = adminAccountDao.getById(uid);
        Assert.assertNotNull(tmp);

        // 比较数据是否正确
        Assert.assertEquals(po.getMobileNo(), tmp.getMobileNo());
        Assert.assertTrue(tmp.getStatus() == po.getStatus());
        Assert.assertTrue(tmp.getDefaultData() == po.getDefaultData());
        Assert.assertTrue(Math.abs(tmp.getCreateTime().getTime() - po.getCreateTime().getTime()) < 1000);
        Assert.assertTrue(Math.abs(tmp.getLastModify().getTime() - po.getLastModify().getTime()) < 1000);

        // 修改资料
        tmp.setMobileNo("13500001111");
        adminAccountDao.update(tmp);
        AdminAccountPO tmp30 = adminAccountDao.getById(uid);
        Assert.assertEquals(tmp.getMobileNo(), tmp30.getMobileNo());

        // 修改状态
        Date now = new Date();
        short status2 = 2;
        int rows = adminAccountDao.updateStatus(uid, status2, tmp.getStatus(), now);
        Assert.assertTrue(1 == rows);

        // 检查状态是否正确
        AdminAccountPO tmp2 = adminAccountDao.getById(uid);
        Assert.assertTrue(status2 == tmp2.getStatus());

        // 删除记录
        adminAccountDao.deleteById(uid);
        AdminAccountPO tmp3 = adminAccountDao.getById(uid);
        Assert.assertNull(tmp3);
    }
}