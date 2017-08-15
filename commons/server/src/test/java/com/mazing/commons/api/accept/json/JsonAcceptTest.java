/**
 * 
 */
package com.mazing.commons.api.accept.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.ApiResult.ApiResultBuild;
import com.yogu.commons.api.accept.json.JsonAccept;
import com.yogu.commons.api.accept.json.JsonAccept.JsonArrayAccept;
import com.yogu.commons.api.accept.json.JsonAccept.JsonObjectAccept;
import com.yogu.commons.utils.JsonUtils;

/**
 * <br>
 * 
 * JFan 2015年3月9日 上午10:33:12
 */
@Ignore
public class JsonAcceptTest {

	@Test
	public void object() {
		JsonObjectAccept oa = JsonAccept.isObj();
		Assert.assertNotNull(oa);
		ApiStorage as = null;
		Assert.assertNull(oa.accept(as));
		Assert.assertNull(oa.accept(new ApiStorage("")));

		ApiStorage ok = new ApiStorage(obj(true));

		Assert.assertNotNull(oa.accept(ok));
		Assert.assertNull(oa.accept(new ApiStorage(obj(false))));
		Assert.assertNull(oa.accept(new ApiStorage(array(true, false))));

		// exist
		Assert.assertNotNull(JsonAccept.isObj().attrExist("time").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrExist("qq").accept(ok));
		// eq str
		Assert.assertNotNull(JsonAccept.isObj().attrEq("name", "zhangSan").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrEq("name", "zhangsan").accept(ok));
		// eq int
		Assert.assertNotNull(JsonAccept.isObj().attrEq("date", 20150101).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrEq("date", 2015010100).accept(ok));
		// ne str
		Assert.assertNotNull(JsonAccept.isObj().attrNe("name", "zhangsan").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrNe("name", "zhangSan").accept(ok));
		// ne int
		Assert.assertNotNull(JsonAccept.isObj().attrNe("date", 2015010100).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrNe("date", 20150101).accept(ok));
		// gt
		Assert.assertNotNull(JsonAccept.isObj().attrGt("date", 20150100).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrGt("date", 201501001).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrGt("date", 201501002).accept(ok));
		// ge
		Assert.assertNotNull(JsonAccept.isObj().attrGe("date", 20150100).accept(ok));
		Assert.assertNotNull(JsonAccept.isObj().attrGe("date", 20150101).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrGe("date", 20150102).accept(ok));
		// lt
		Assert.assertNotNull(JsonAccept.isObj().attrLt("date", 20150102).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrLt("date", 20150101).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrLt("date", 20150100).accept(ok));
		// le
		Assert.assertNotNull(JsonAccept.isObj().attrLe("date", 20150102).accept(ok));
		Assert.assertNotNull(JsonAccept.isObj().attrLe("date", 20150101).accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrLe("date", 20150100).accept(ok));

		// attrIsObj
		Assert.assertNotNull(JsonAccept.isObj().attrIsObj("info").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("orders").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("name").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("qq").accept(ok));
		// attrIsArray
		Assert.assertNotNull(JsonAccept.isObj().attrIsArray("orders").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("info").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("name").accept(ok));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("qq").accept(ok));
		// attr accept

	}

	@Test
	public void array() {
		JsonArrayAccept aa = JsonAccept.isArr();
		Assert.assertNotNull(aa);
		ApiStorage as = null;
		Assert.assertNull(aa.accept(as));
		Assert.assertNull(aa.accept(new ApiStorage("")));

		ApiStorage ok = new ApiStorage(array(true, false));
		ApiStorage okArr = new ApiStorage(array(true, true));

		Assert.assertNotNull(aa.accept(ok));
		Assert.assertNotNull(aa.accept(okArr));
		Assert.assertNull(aa.accept(new ApiStorage(array(false, false))));
		Assert.assertNull(aa.accept(new ApiStorage(obj(true))));

		// eq
		Assert.assertNull(JsonAccept.isArr().sizeEq(2).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeEq(4).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeEq(6).accept(ok));
		// ne
		Assert.assertNotNull(JsonAccept.isArr().sizeNe(2).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeNe(4).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeNe(6).accept(ok));
		// gt
		Assert.assertNotNull(JsonAccept.isArr().sizeGt(2).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeGt(4).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeGt(6).accept(ok));
		// ge
		Assert.assertNotNull(JsonAccept.isArr().sizeGe(2).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeGe(4).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeGe(6).accept(ok));
		// lt
		Assert.assertNull(JsonAccept.isArr().sizeLt(2).accept(ok));
		Assert.assertNull(JsonAccept.isArr().sizeLt(4).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeLt(6).accept(ok));
		// le
		Assert.assertNull(JsonAccept.isArr().sizeLe(2).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeLe(4).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().sizeLe(6).accept(ok));
		// indexIsObj
		Assert.assertNull(JsonAccept.isArr().indexIsObj(-1).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(0).accept(ok));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(3).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(4).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(5).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(-1).accept(okArr));// okArr
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(0).accept(okArr));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(3).accept(okArr));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(4).accept(okArr));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(5).accept(okArr));
		// indexIsArray
		Assert.assertNull(JsonAccept.isArr().indexIsArray(-1).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(0).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(3).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(4).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(5).accept(ok));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(-1).accept(okArr));// okArr
		Assert.assertNull(JsonAccept.isArr().indexIsArray(0).accept(okArr));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(3).accept(okArr));
		Assert.assertNotNull(JsonAccept.isArr().indexIsArray(4).accept(okArr));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(5).accept(okArr));
		// index accept
	}

	@Test
	public void jsonObj() {
		JsonObjectAccept oa = JsonAccept.isObj();
		Assert.assertNotNull(oa);
		JsonObject jo = null;
		Assert.assertNull(oa.accept(jo));

		jo = new ApiStorage(obj(true)).getResult(ApiResultBuild.JSON_OBJECT);
		Assert.assertNotNull(jo);

		// exist
		Assert.assertNotNull(JsonAccept.isObj().attrExist("time").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrExist("qq").accept(jo));
		// eq str
		Assert.assertNotNull(JsonAccept.isObj().attrEq("name", "zhangSan").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrEq("name", "zhangsan").accept(jo));
		// eq int
		Assert.assertNotNull(JsonAccept.isObj().attrEq("date", 20150101).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrEq("date", 2015010100).accept(jo));
		// ne str
		Assert.assertNotNull(JsonAccept.isObj().attrNe("name", "zhangsan").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrNe("name", "zhangSan").accept(jo));
		// ne int
		Assert.assertNotNull(JsonAccept.isObj().attrNe("date", 2015010100).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrNe("date", 20150101).accept(jo));
		// gt
		Assert.assertNotNull(JsonAccept.isObj().attrGt("date", 20150100).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrGt("date", 201501001).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrGt("date", 201501002).accept(jo));
		// ge
		Assert.assertNotNull(JsonAccept.isObj().attrGe("date", 20150100).accept(jo));
		Assert.assertNotNull(JsonAccept.isObj().attrGe("date", 20150101).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrGe("date", 20150102).accept(jo));
		// lt
		Assert.assertNotNull(JsonAccept.isObj().attrLt("date", 20150102).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrLt("date", 20150101).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrLt("date", 20150100).accept(jo));
		// le
		Assert.assertNotNull(JsonAccept.isObj().attrLe("date", 20150102).accept(jo));
		Assert.assertNotNull(JsonAccept.isObj().attrLe("date", 20150101).accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrLe("date", 20150100).accept(jo));

		// attrIsObj
		Assert.assertNotNull(JsonAccept.isObj().attrIsObj("info").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("orders").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("name").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsObj("qq").accept(jo));
		// attrIsArray
		Assert.assertNotNull(JsonAccept.isObj().attrIsArray("orders").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("info").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("name").accept(jo));
		Assert.assertNull(JsonAccept.isObj().attrIsArray("qq").accept(jo));
		// attr accept
	}

	@Test
	public void jsonArr() {
		JsonArrayAccept aa = JsonAccept.isArr();
		Assert.assertNotNull(aa);
		JsonArray ja = null;
		Assert.assertNull(aa.accept(ja));

		ja = new ApiStorage(array(true, false)).getResult(ApiResultBuild.JSON_ARRAY);
		JsonArray jaArr = new ApiStorage(array(true, true)).getResult(ApiResultBuild.JSON_ARRAY);

		Assert.assertNotNull(aa.accept(ja));
		Assert.assertNotNull(aa.accept(jaArr));
		Assert.assertNull(aa.accept(new ApiStorage(array(false, false))));
		Assert.assertNull(aa.accept(new ApiStorage(obj(true))));

		// eq
		Assert.assertNull(JsonAccept.isArr().sizeEq(2).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeEq(4).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeEq(6).accept(ja));
		// ne
		Assert.assertNotNull(JsonAccept.isArr().sizeNe(2).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeNe(4).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeNe(6).accept(ja));
		// gt
		Assert.assertNotNull(JsonAccept.isArr().sizeGt(2).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeGt(4).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeGt(6).accept(ja));
		// ge
		Assert.assertNotNull(JsonAccept.isArr().sizeGe(2).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeGe(4).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeGe(6).accept(ja));
		// lt
		Assert.assertNull(JsonAccept.isArr().sizeLt(2).accept(ja));
		Assert.assertNull(JsonAccept.isArr().sizeLt(4).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeLt(6).accept(ja));
		// le
		Assert.assertNull(JsonAccept.isArr().sizeLe(2).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeLe(4).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().sizeLe(6).accept(ja));
		// indexIsObj
		Assert.assertNull(JsonAccept.isArr().indexIsObj(-1).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(0).accept(ja));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(3).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(4).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(5).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(-1).accept(jaArr));// jaArr
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(0).accept(jaArr));
		Assert.assertNotNull(JsonAccept.isArr().indexIsObj(3).accept(jaArr));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(4).accept(jaArr));
		Assert.assertNull(JsonAccept.isArr().indexIsObj(5).accept(jaArr));
		// indexIsArray
		Assert.assertNull(JsonAccept.isArr().indexIsArray(-1).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(0).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(3).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(4).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(5).accept(ja));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(-1).accept(jaArr));// jaArr
		Assert.assertNull(JsonAccept.isArr().indexIsArray(0).accept(jaArr));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(3).accept(jaArr));
		Assert.assertNotNull(JsonAccept.isArr().indexIsArray(4).accept(jaArr));
		Assert.assertNull(JsonAccept.isArr().indexIsArray(5).accept(jaArr));
		// index accept
	}

	private String obj(boolean ok) {// ok: 好的？坏的？
		Map<String, Object> map = new HashMap<String, Object>();
		long time = System.currentTimeMillis();
		map.put("time", time);
		map.put("name", "zhangSan");
		map.put("sex", (time % 2));
		map.put("date", 20150101);
		map.put("address", "广东广州");

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("id", 10);
		info.put("age", 33);
		info.put("createDate", new Date());
		map.put("info", info);

		ApiStorage array = new ApiStorage(array(true, false));
		JsonArray orders = array.getResult(ApiResultBuild.JSON_ARRAY);
		map.put("orders", orders);

		String json = JsonUtils.toJSONString(map);
		if (!ok)
			json = bad(json);

		// System.out.println(json);
		return json;
	}

	private String array(boolean ok, boolean appList) {// ok: 好的？坏的？ appList:是否在最后，追加一个List
		List<Object> orders = new ArrayList<Object>();

		for (int i = 0; i < 4; i++) {
			Map<String, Object> order = new HashMap<String, Object>();
			order.put("id", 100 + i);
			order.put("user", "liSi");
			order.put("newTime", System.currentTimeMillis() + i);
			orders.add(order);
		}

		if (appList) {
			// 前4个是obj，这里追加一个 list
			List<Map<String, Object>> ll = new ArrayList<>();
			Map<String, Object> l = new HashMap<String, Object>();
			l.put("id", 2001);
			l.put("user", "liSi");
			l.put("newTime", System.currentTimeMillis());
			ll.add(l);
			l = new HashMap<String, Object>();
			l.put("id", 2002);
			l.put("user", "wangWu");
			l.put("newTime", System.currentTimeMillis());
			ll.add(l);

			orders.add(ll);
		}

		String json = JsonUtils.toJSONString(orders);
		if (!ok)
			json = bad(json);

		// System.out.println(json);
		return json;
	}

	private String bad(String json) {// 删除第一个逗号
		int i = json.indexOf(",");
		return json.substring(0, i) + json.substring(i + 1);
	}

}
