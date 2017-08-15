package com.mazing.commons.json;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonGenerator;

public class JsonApiDemo {

	public static void main(String[] args) {

		buildJsonUsingStreamingApi();
		buildJsonUsingObjectModelApi();
	}

	private static void buildJsonUsingStreamingApi() {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = Json.createGenerator(writer);

		generator.writeStartArray().writeStartObject().write("parentid", 23424900).write("name", "Mexico City").write("url", "http://where.yahooapis.com/v1/place/116545")
				.writeStartObject("placeType").write("name", "Town").write("code", 7).writeEnd().write("woeid", 116545).writeEnd()
				//
				.writeStartObject().write("name", "Jackson").write("url", "http://where.yahooapis.com/v1/place/2428184").writeStartObject("placeType").write("name", "Town").write("code", 7)
				.writeEnd().write("parentid", 23424977).write("woeid", 2428184).writeEnd().writeEnd();
		generator.flush();
		System.out.println(writer.toString());
	}

	private static void buildJsonUsingObjectModelApi() {
		System.out.println("Json Building using Object Model API");
		JsonArray jsonArray = Json
				.createArrayBuilder()
				.add(Json.createObjectBuilder().add("parentid", 23424900).add("name", "Jackson").add("url", "http://where.yahooapis.com/v1/place/2428184")
						.add("placeType", Json.createObjectBuilder().add("name", "Town").add("code", 7)).add("woeid", 116545).build())
				//
				.add(Json.createObjectBuilder().add("name", "Mexico City").add("url", "http://where.yahooapis.com/v1/place/116545")
						.add("placeType", Json.createObjectBuilder().add("name", "Town").add("code", 7)).add("parentid", 23424977).add("woeid", 2428184).build()).build();
		StringWriter writer = new StringWriter();
		Json.createWriter(writer).writeArray(jsonArray);
		System.out.println(writer.toString());

	}
}