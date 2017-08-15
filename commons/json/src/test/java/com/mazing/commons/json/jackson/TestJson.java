package com.mazing.commons.json.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;

public class TestJson {

	private ObjectMapper objectMapper = new ObjectMapper();

	// 单个对象的序列化
	public void testTojson() {
		Person testPerson = new Person();
		testPerson.setAddress("dsfsdf");
		testPerson.setAge(10);
		testPerson.setName("name");
		testPerson.setSex(null);
		try {
			String resultStr = objectMapper.writeValueAsString(testPerson);
			System.out.println(resultStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 将json反序列化为对象
	public void testFromJson() {
		String content = "{\"n\":\"name\",\"sex\":\"sec\",\"a\":\"null\"}";
		try {
			Person resultPerson = objectMapper.readValue(content, Person.class);
			System.out.println(resultPerson.getAddress().trim());
			System.out.println(resultPerson.getAge());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testFromList() {
		Person testPerson1 = new Person();
		testPerson1.setAddress("dsfsdf111");
		testPerson1.setAge(10);
		testPerson1.setName("name11");
		testPerson1.setSex("sec11");
		Person testPerson2 = new Person();
		testPerson2.setAddress("dsfsdf222");
		testPerson2.setAge(10);
		testPerson2.setName("name22");
		testPerson2.setSex("sec22");
		Person testPerson3 = new Person();
		testPerson3.setAddress("dsfsdf333");
		testPerson3.setAge(10);
		testPerson3.setName("name33");
		testPerson3.setSex("sec33");
		try {
			List<Person> personList = new ArrayList<Person>();
			personList.add(testPerson1);
			personList.add(testPerson2);
			personList.add(testPerson3);
			String resultStr = objectMapper.writeValueAsString(personList);
			System.out.println(resultStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testListFromJson() {
		String content = "[{\"sex\":\"sec11\",\"n\":\"name11\",\"a\":\"dsfsdf111\"}," + "{\"sex\":\"sec22\",\"n\":\"name22\",\"a\":\"dsfsdf222\"},"
				+ "{\"sex\":\"sec33\",\"n\":\"name33\",\"a\":\"dsfsdf333\"}]";
		try {
			// 这个有问题
			// List<Person> resultPerson = objectMapper.readValue(content, ArrayList.class);
			// System.out.println(resultPerson.get(0).getAddress());
			List<Person> d = objectMapper.readValue(content, new TypeReference<List<Person>>() {
			});
			System.out.println(d.get(0).getAddress());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestJson test = new TestJson();
		test.testTojson();
		test.testFromJson();
		test.testFromList();
		test.testListFromJson();
	}

}
