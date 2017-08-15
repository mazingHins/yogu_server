package com.mazing.commons.json.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON数据反序列化为Java对象或对象集合
 * 
 * @author BOONYACHENGDU@GMAIL.COM
 * @date 2013-8-28
 */
public class JSONSeriaToObject {

	/**
	 * 对象转JSON
	 * 
	 * @param obj
	 * @return
	 */
	public String getJsonFromObject(Object obj) {
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * JSON发序列化为Java对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public Dian getPointByJsonString(String jsonStr) {
		Dian point = null;
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode node = om.readTree(jsonStr);
			point = (Dian) om.readValue(node.toString(), Dian.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("<:success single point:>" + point.toString());
		return point;
	}

	/**
	 * 拼接Json数据的字符串转化为标准Json格式字符串
	 * 
	 * @param str
	 * @return
	 */
	public String getJsonNodeStringByString(String str) {
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode node = om.readTree(str);
			return node.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * JSON发序列化为Java对象集合
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Dian> getPointsByJsonString(String jsonStr) {
		List<Dian> points = new ArrayList<Dian>();
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode node = om.readTree(jsonStr);
			points = (List<Dian>) om.readValue(node.toString(), new TypeReference<List<Dian>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < points.size(); i++) {
			System.out.println("<:success index " + i + ":>" + points.get(i).toString());
		}
		return points;
	}

	/**
	 * JSON数据反序列化为Java对象的测试入口
	 * 
	 * @param args
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		JSONSeriaToObject jsto = new JSONSeriaToObject();

		// 原始数据格式
		System.out.println("----------------------------------Jackson JSON(list<T>)  to Java  Object-----------------------------");
		List<Dian> points = new ArrayList<Dian>();
		points.add(new Dian(34.2332, 104.46664));
		String json = jsto.getJsonFromObject(points);
		System.out.println(json);
		// JSON序列化Java对象
		jsto.getPointsByJsonString(json);

		System.out.println("----------------------------------Jackson JSON(T)  to Java  Object-----------------------------");
		Dian point = new Dian(34.5332, 104.76664);
		String json2 = jsto.getJsonFromObject(point);
		System.out.println(json2);
		// JSON序列化Java对象
		jsto.getPointByJsonString(json2);

		// JSON序列化为Java对象
		System.out.println("----------------------------------Ping string JSON  to Java  Object-----------------------------");
		String latlngs = new String("[{\"lat\":34.232013,\"lng\":103.466002},{\"lat\":34.531939,\"lng\":103.665816}]");
		// 经测试以下数据若调用getJsonFromObject是不行的会产生异常
		String json3 = jsto.getJsonNodeStringByString(latlngs);
		System.out.println(json3);

		// JSON序列化Java对象
		jsto.getPointsByJsonString(json3);

	}

}
