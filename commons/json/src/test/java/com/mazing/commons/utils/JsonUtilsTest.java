/**
 * 
 */
package com.mazing.commons.utils;

import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.Assert;
import org.junit.Test;

import com.mazing.commons.WoeInfo;
import com.mazing.commons.WoeType;
import com.yogu.commons.utils.JsonUtils;

/**
 * <br>
 * 
 * JFan 2014年12月11日 下午5:39:43
 */
public class JsonUtilsTest {

	private String json = "{\"parentid\":23424900,\"name\":\"Mexico City\",\"url\":\"http://where.yahooapis.com/v1/place/116545\",\"placeType\":{\"name\":\"Town\",\"code\":7},\"woeid\":116545}";
	private String array = "[{\"parentid\":23424900,\"name\":\"Mexico City\",\"url\":\"http://where.yahooapis.com/v1/place/116545\",\"placeType\":{\"name\":\"Town\",\"code\":7},\"woeid\":116545},{\"name\":\"Jackson\",\"url\":\"http://where.yahooapis.com/v1/place/2428184\",\"placeType\":{\"name\":\"Town\",\"code\":7},\"parentid\":23424977,\"woeid\":2428184}]";

	/**
	 * {@link com.yogu.commons.utils.JsonUtils#toJsonObject(java.lang.String)} 的测试方法。
	 */
	@Test
    public void testToJsonObject() {
        JsonObject jsonObject = JsonUtils.toJsonObject(json);
        Assert.assertNotNull(jsonObject);

        JsonObjectBuilder job = JsonUtils.toJsonObjectBuilder(jsonObject);
        Assert.assertNotNull(job);
        Assert.assertNotNull(job.build());
    }

	/**
	 * {@link com.yogu.commons.utils.JsonUtils#parseObject(java.lang.String, java.lang.Class)} 的测试方法。
	 */
	@Test
	public void testParseObject() {
		WoeInfo woeInfo = JsonUtils.parseObject(json, WoeInfo.class);
		Assert.assertNotNull(woeInfo);
		Assert.assertNotNull(woeInfo.getPlaceType());

		Assert.assertEquals(woeInfo.getParentid(), 23424900);
		Assert.assertEquals(woeInfo.getPlaceType().getName(), "Town");
	}

	/**
	 * {@link com.yogu.commons.utils.JsonUtils#toJsonArray(java.lang.String)} 的测试方法。
	 */
	@Test
    public void testToJsonArray() {
        JsonArray jsonArray = JsonUtils.toJsonArray(array);
        Assert.assertNotNull(jsonArray);

        JsonArrayBuilder jab = JsonUtils.toJsonArrayBuilder(jsonArray);
        Assert.assertNotNull(jab);
        Assert.assertNotNull(jab.build());
    }

	/**
	 * {@link com.yogu.commons.utils.JsonUtils#parseArray(java.lang.String, java.lang.Class)} 的测试方法。
	 */
	@Test
	public void testParseArray() {
		List<WoeInfo> parseArray = JsonUtils.parseArray(array, WoeInfo.class);
		Assert.assertNotNull(parseArray);
		Assert.assertEquals(2, parseArray.size());

		WoeInfo woeInfo = parseArray.get(0);
		Assert.assertEquals(woeInfo.getParentid(), 23424900);
		Assert.assertEquals(woeInfo.getPlaceType().getName(), "Town");
	}

	/**
	 * {@link com.yogu.commons.utils.JsonUtils#toJSONString(java.lang.Object)} 的测试方法。
	 */
	@Test
	public void testToJSONString() {
		WoeInfo info = new WoeInfo();
		info.setName("NNNN");
		info.setParentid(99);
		info.setUrl("http://localhost:9999/abc/def");
		info.setWoeid(100000);

		WoeType type = new WoeType();
		type.setCode(123);
		type.setName("TYPE");

		info.setPlaceType(type);

		String jsonString = JsonUtils.toJSONString(info);
		Assert.assertNotNull(jsonString);

		WoeInfo woeInfo = JsonUtils.parseObject(jsonString, WoeInfo.class);
		Assert.assertNotNull(woeInfo);
		Assert.assertNotNull(woeInfo.getPlaceType());

		Assert.assertEquals(woeInfo.getParentid(), 99);
		Assert.assertEquals(woeInfo.getPlaceType().getName(), "TYPE");
	}

}
