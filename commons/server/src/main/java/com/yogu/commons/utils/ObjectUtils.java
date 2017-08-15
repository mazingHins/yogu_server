package com.yogu.commons.utils;
///**
// * 
// */
//package com.vip.commons.utils;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonValue;
//import javax.json.JsonValue.ValueType;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * <br>
// * 
// * JFan 2014年12月23日 上午10:43:19
// */
//public final class ObjectUtils {
//
//	private ObjectUtils() {
//	}
//
//	private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
//
//	public static Map<String, String> toMap(Object object) {
//		return toMap(object, new HashMap<String, String>());
//	}
//
//	public static <T extends Map<String, String>> T toMap(Object object, T map) {
//		return toMap(object, map, true);
//	}
//
//	public static <T extends Map<String, String>> T toMap(Object object, T map, boolean ignoreEmpty) {
//		if (null != map && null != object) {
//			Class<? extends Object> cls = object.getClass();
//			Args.check(!(cls.isArray() || Collection.class.isAssignableFrom(cls)), "Multi value is not supported");
//
//			if (Map.class.isAssignableFrom(cls)) {
//				@SuppressWarnings("unchecked")
//				Map<Object, Object> inMap = (Map<Object, Object>) object;
//				for (Entry<Object, Object> entry : inMap.entrySet()) {
//					Object key = entry.getKey();
//					Object value = entry.getValue();
//
//					String valueStr = value.toString();
//					if (ignoreEmpty && StringUtils.isEmpty(valueStr))
//						continue;
//
//					map.put(key.toString(), valueStr);
//				}
//			} else {
//				JsonObject json = JsonUtils.toJsonObject(object);
//				if (!(json.isEmpty()))
//					appendJsonObject(json, map, ignoreEmpty);
//			}
//		}
//		return map;
//	}
//
//	// ####
//	// ## private func
//	// ####
//
//	private static void appendJsonObject(JsonObject json, Map<String, String> map, boolean ignoreEmpty) {
//		if (null == json || json.isEmpty())
//			return;
//
//		for (String name : json.keySet()) {
//			String value = null;
//			JsonValue jsonValue = json.get(name);
//			ValueType valueType = jsonValue.getValueType();
//
//			if (ValueType.NULL.equals(valueType))
//				continue;
//
//			if (ValueType.TRUE.equals(valueType))
//				value = "true";
//			else if (ValueType.FALSE.equals(valueType))
//				value = "false";
//			else if (ValueType.NUMBER.equals(valueType))
//				value = json.getJsonNumber(name).toString();
//			else if (ValueType.STRING.equals(valueType))
//				value = json.getString(name);
//			else if (ValueType.OBJECT.equals(valueType)) {
//				JsonObject jo = json.getJsonObject(name);
//				appendJsonObject(jo, map, ignoreEmpty);
//			} else if (ValueType.ARRAY.equals(valueType)) {
//				JsonArray ja = json.getJsonArray(name);
//				value = ja.toString();
//			} else {
//				logger.warn("Unable to identify the type of '{}' : '{}', skip.", name, jsonValue);
//				continue;
//			}
//
//			if (ignoreEmpty && StringUtils.isEmpty(value))
//				continue;
//
//			map.put(name, value);
//		}
//		return;
//	}
//
//}
