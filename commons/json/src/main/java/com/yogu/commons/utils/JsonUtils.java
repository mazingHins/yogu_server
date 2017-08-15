package com.yogu.commons.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

public final class JsonUtils {
	
	private JsonUtils() {
	}

	public final static String EMPTY_Object = "{}";
	public final static String EMPTY_ARRAY = "[]";

	public static ObjectMapper mapper = new ObjectMapper();
	static {
		JSR353Module module = new JSR353Module();
		mapper.registerModule(module);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	// ####
	// ## JSON-API
	// ####

	/**
	 * 把json文本parse成JsonObject
	 */
	public static JsonObject toJsonObject(String text) {
		Reader reader = new StringReader(text);
		JsonReader jsonReader = Json.createReader(reader);
		try {
			return jsonReader.readObject();
		} finally {
			try {
				if (null != jsonReader)
					jsonReader.close();
				if (null != reader)
					reader.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	/**
	 * 把对象转成JsonObject
	 */
	public static JsonObject toJsonObject(Object object) {
		String json = toJSONString(object);// 寻找 JSON-API 自身解决方法
		return toJsonObject(json);
	}

	/**
	 * 把json文本parse成JsonArray
	 */
	public static JsonArray toJsonArray(String text) {
		Reader reader = new StringReader(text);
		JsonReader jsonReader = Json.createReader(reader);
		try {
			return jsonReader.readArray();
		} finally {
			try {
				if (null != jsonReader)
					jsonReader.close();
				if (null != reader)
					reader.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	public static <T> T parseObject(JsonObject json, Class<T> clazz) {
		if (null == json)
			return null;
		return parseObject(json.toString(), clazz);// 寻找 JSON-API 自身的解决方法
	}

	public static <T> List<T> parseArray(JsonArray json, Class<T> clazz) {
		if (null == json)
			return null;
		return parseArray(json.toString(), clazz);// 寻找 JSON-API 自身的解决方法
	}

	// ####
	// ## jsonText 2 Object
	// ####

	public static <T> T parseObject(String text, TypeReference<T> tr) {
		try {
			return mapper.readValue(text, tr);
		} catch (Exception e) {
			throw new RuntimeException(e);// 查看 mapper 成员变量上的注释
		}
	}

	/**
	 * 把JSON文本parse为JavaBean
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static <T> T parseObject(String text, Class<T> clazz) {
		try {
			return mapper.readValue(text, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);// 查看 mapper 成员变量上的注释
		}
	}

	/**
	 * 把JSON文本parse成JavaBean集合
	 */
	public static <T> List<T> parseArray(String text, final Class<T> clazz) {
		try {
//			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
			return mapper.readValue(text, new ArrayT<T>(clazz));
		} catch (Exception e) {
			throw new RuntimeException(e);// 查看 mapper 成员变量上的注释
		}
	}
	
	public static <T> Map<String, T> parseMap(String text, final Class<T> valueClazz){
	    try {
            return mapper.readValue(text, new MapT<T>(valueClazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public static <K, V> Map<K, V> parseMap(String text, final Class<K> keyClazz, final Class<V> valueClazz) {
		try {
			return mapper.readValue(text, new MapKV<K, V>(keyClazz, valueClazz));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isJSON(final String json) {
	    boolean valid = false;
	    try {
	       final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
	       while (parser.nextToken() != null) {
	       }
	       valid = true;
	    } catch (JsonParseException jpe) {
	       jpe.printStackTrace();
	    } catch (IOException ioe) {
	       ioe.printStackTrace();
	    }

	    return valid;
	 }

	// ####
	// ## Object 2 JsonText
	// ####

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 * @throws JsonProcessingException
	 */
	public static String toJSONString(Object object) {
		if (null == object)
			return null;
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);// 查看 mapper 成员变量上的注释
		}

//		Writer writer = new StringWriter();
//		JsonWriter createWriter = Json.createWriter(writer);
//		try {
//			Class<? extends Object> clz = object.getClass();
//			if (clz.isArray()) {
//				Json.createReader(in);
//				createWriter.write(value);
//			} else {
//				createWriter.writeObject(object);
//			}
//
//			return writer.toString();
//		} finally {
//			try {
//				if (null != createWriter)
//					createWriter.close();
//				if (null != writer)
//					writer.close();
//			} catch (IOException e) {
//				// ignore
//			}
//		}
	}

    // ####
    // ## static class Update Json
    // ####

    public static JsonObjectBuilder toJsonObjectBuilder(String text) {
        JsonObject json = toJsonObject(text);
        return toJsonObjectBuilder(json);
    }

    public static JsonObjectBuilder toJsonObjectBuilder(JsonObject json, String name) {
        return toJsonObjectBuilder(json.getJsonObject(name));
    }

    public static JsonObjectBuilder toJsonObjectBuilder(JsonObject json) {
        JsonObjectBuilder jsonObjBuild = Json.createObjectBuilder();
        if (null != json)
            for (String key : json.keySet())
                jsonObjBuild.add(key, json.get(key));
        return jsonObjBuild;
    }

    public static JsonArrayBuilder toJsonArrayBuilder(String text) {
        JsonArray json = toJsonArray(text);
        return toJsonArrayBuilder(json);
    }

    public static JsonArrayBuilder toJsonArrayBuilder(JsonObject json, String name) {
        return toJsonArrayBuilder(json.getJsonArray(name));
    }

    public static JsonArrayBuilder toJsonArrayBuilder(JsonArray array) {
        JsonArrayBuilder jsonArrayBuild = Json.createArrayBuilder();
        if (null != array)
            for (int i = 0; i < array.size(); i++)
                jsonArrayBuild.add(array.get(i));
        return jsonArrayBuild;
    }

    // ####
	// ## static class T 2 Type
	// ####

	public static class ObjectT<T> extends TypeReference<T> {

		private Class<T> clz;

		public ObjectT(Class<T> clz) {
			this.clz = clz;
		}

		/*
		 * （非 Javadoc）
		 * 
		 * @see com.fasterxml.jackson.core.type.TypeReference#getType()
		 */
		@Override
		public Type getType() {
			return clz;
		}

	}

	public static class ArrayT<T> extends TypeReference<T> {

		private Class<T> clz;

		public ArrayT(Class<T> clz) {
			this.clz = clz;
		}

		/*
		 * （非 Javadoc）
		 * 
		 * @see com.fasterxml.jackson.core.type.TypeReference#getType()
		 */
		@Override
		public Type getType() {
			return new ParameterizedTypeImpl(ArrayList.class, new Class[] { clz });
		}

	}
	
	public static class MapT<T> extends TypeReference<T>{
	    
	    private Class<T> valueClz;

        public MapT(Class<T> valueClz) {
            this.valueClz = valueClz;
        }
        
	    @Override
	    public Type getType() {
	        return new ParameterizedTypeImpl(Map.class, new Class[] { String.class, valueClz });
	    }
	}

	public static class MapKV<K, V> extends TypeReference<V> {

		private Class<K> keyClz;
		private Class<V> valueClz;

		public MapKV(Class<K> keyClz, Class<V> valueClz) {
			this.valueClz = valueClz;
			this.keyClz = keyClz;
		}

		@Override
		public Type getType() {
			return new ParameterizedTypeImpl(Map.class, new Class[] { keyClz, valueClz });
		}
	}

}
