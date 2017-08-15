package com.mazing.services.user.resourceapi;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

import org.junit.Test;

import java.util.Map;

/**
 * 测试 /api/ 的接口
 * @author ten 2015/9/17
 */
public class LocalUserApiResourceTest extends HttpResourceTest {

    public LocalUserApiResourceTest() {
        host = "http://localhost:8090";
    }

    /**
     * 测试读取用户帐号的详细信息
     * @author ten 2015/9/17
     * @throws Exception
     */
    @Test
    public void testGetUserProfileByPassport() throws Exception {
        ApiReq<RestResult<?>> req = build("api/v1/user/profile");
        req.login();

        // 注：省去创建帐号的过程，这个帐号必须存在
        req.putGet("countryCode", "86");
        req.putGet("passport", "18620076122");

        RestResult<?> result = req.doGet();
        assertTrue(result.isSuccess());
        assertNotNull(result.getObject());
    }
    
    @Test
    public void testGetUserInfo(){
    	ApiReq<RestResult<?>> req = build("api/v1/user/listInfo");
    	req.login();
    	
    	req.putGet("uids", "10251");
    	RestResult<?> result = req.doGet();
    	assertTrue(result.isSuccess());
    }

    /**
     * 测试封号、解封
     * @author ten 2015/9/17
     * @throws Exception
     */
    @Test
    public void testBanUser() throws Exception {
        ApiReq<RestResult<?>> req = build("api/v1/user/profile");
        req.login();

        // 注：省去创建帐号的过程，这个帐号必须存在
        req.putGet("countryCode", "86");
        req.putGet("passport", "18620076122");

        RestResult<Map<String, Object>> result = (RestResult<Map<String, Object>>) req.doGet();
        assertTrue(result.isSuccess());
        Number uid = (Number) result.getObject().get("uid");

        // 封号
        req = build("api/v1/user/ban");
        req.login();
        req.putGet("uid", uid + "");
        RestResult<?> banResult = req.doGet();
        assertTrue(banResult.isSuccess());

        // 解封
        req = build("api/v1/user/unban");
        req.login();
        req.putGet("uid", uid + "");
        RestResult<?> unbanResult = req.doGet();
        assertTrue(unbanResult.isSuccess());
    }
    
    @Test
    public void testUserBanned(){
    	ApiReq<RestResult<?>> req = build("api/user/isUserBanned");
    	req.putGet("uid", "10001");
    	
    	RestResult<?> result = req.doGet();
    	
    	assertTrue(result.getCode() == ResultCode.SUCCESS);
    }
    
    @Test
    public void testGetByImid() {
    	ApiReq<RestResult<?>> req = build("api/v1/user/getProfileByImid");
    	req.putGet("imid", "12000");
    	
    	RestResult<?> result = req.doGet();
    	
    	assertTrue(result.getCode() == ResultCode.SUCCESS);
    }
    
}