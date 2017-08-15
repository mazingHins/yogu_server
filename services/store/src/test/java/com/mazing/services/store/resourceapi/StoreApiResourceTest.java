package com.mazing.services.store.resourceapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition.FormDataContentDispositionBuilder;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Test;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * StoreApiResource的测试类 <br>
 * 
 * @author JFan 2015年7月20日 下午2:32:36
 */
public class StoreApiResourceTest extends HttpResourceTest {

	public StoreApiResourceTest(){
		host = "http://storeapi.mazing.com";
		userHost = "http://userapi.mazing.com";
//		host = "http://localhost:8090";
//		userHost = "http://localhost:8090";
	}
	
	/**
	 * 对门店收藏数进行+- 1操作
	 */
	@Test
	public void favNum() {
		int id = 100;

		ApiReq<RestResult<?>> req = build("api/store/get");
		req.putGet("storeId", "" + id);

		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);
		assertEquals(id, map.get("storeId"));
	}

	/**
	 * 对门店评论数进行+1操作
	 */
	@Test
	public void additiveCommentNum() {
		ApiReq<RestResult<?>> req = build("api/store/comment/additiveNum");
		req.putGet("id", "100");

		RestResult<?> result = req.doGet();
		boolean ok = assertObject(result);
		assertTrue(ok);
	}
	
	@Test
	public void testStoreBanned(){
		ApiReq<RestResult<?>> req = build("api/store/isStoreBanned");
		req.putGet("storeId", "1");
		
		RestResult<?> result = req.doGet();
		
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}
	
	@Test
	public void testGetStoreAmount(){
		ApiReq<RestResult<?>> req = build("/api/v1/store/getStoreAmount");
//		req.putGet("storeId", "1");
		RestResult<?> result = req.doGet();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}
	
	@Test
	public void updateStoreStaffRoleImId(){
		ApiReq<RestResult<?>> req = build("/api/store/updateStoreStaffRoleImId.do");
//		req.putGet("storeId", "1");
		RestResult<?> result = req.doPost();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}
	
	@Test
	public void testGetAllIds(){
		ApiReq<RestResult<?>> req = build("/api/store/getAllStoreIds");
		RestResult<?> result = req.doGet();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}

	@Test
	public void testGetStoreDetails(){
		ApiReq<RestResult<?>> req = build("/api/store/detail");
		req.putGet("storeId", "10007");
		RestResult<?> result = req.doGet();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}
	
	@Test
	public void queryByType(){
		ApiReq<RestResult<?>> req = build("/api/store/queryByType");
		RestResult<?> result = req.doGet();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
	}
	
	@Test
	public void testAdminUpload() throws Exception {
		
		MultiPart multiPart = new MultiPart();
	    multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
	    
	    FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("pic",
	            new File("C:/data/2wvyxnw6r5.jpg"),
	            MediaType.APPLICATION_OCTET_STREAM_TYPE);
	    multiPart.bodyPart(fileDataBodyPart);
		
		ApiReq<RestResult<?>> req = build("/api/store/topicImg/upload.do");
		
		req.putPost("storeId", "10008");
		req.doMultipartPost();
		req.putFile("pic", new File("C:/data/2wvyxnw6r5.jpg"));
		
		RestResult<?> result = req.doPost();
		assertTrue(result.getCode() == ResultCode.SUCCESS);
//		
		
	}

}
