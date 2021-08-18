package com.xiaoer.tests;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonTest {
	public static void main(String[] args) {
		String jsonStr = "{\"name\":\"seven\",\"password\":null}";

		ObjectMapper mapper = new ObjectMapper();
		try {
			Map json = mapper.readValue(jsonStr, Map.class);
			System.out.println(json.get("password") == null);
			System.out.println(json.get("password").getClass());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		

//		Map<String, String> map = new JSONObject(jsonStr).toBean(Map.class);
//		System.out.println(map.get("password") == null);
//		System.out.println(map.get("password").getClass());
//
//		System.out.println(json.isNull("password"));
	}
}
