package com.xiaoer.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
	public static void main(String[] args) {
		Map<Integer, Integer> m1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> m2 = new HashMap<Integer, Integer>();
		m1.put(1, 1);
		m1.put(2, 2);
		m1.put(3, 3);
		m1.put(4, 4);

		m2.put(1, 1);
		m2.put(2, 2);

		Iterator<Map.Entry<Integer, Integer>> it = m1.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = it.next();
			if (!m2.containsKey(entry.getKey())) {
				it.remove();
			}
		}

		System.out.println(m1.toString());

		Map<String, String> m3 = new HashMap<>();
		m3.put("A", "1");
		m3.put("B", "2");
		m3.put("C", "3");
		
		TreeMap<String, Object> m4 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		m4.putAll(m3);
		m3.keySet().stream().forEach(key -> {
//			m3.put(key.toLowerCase(), m3.remove(key));
			key = key.toLowerCase();
		});
//		m3.forEach((key, value) -> {
//			key = key.toLowerCase();
//		});
		System.out.println(m4);
		System.out.println(m4.get("a"));
		
		System.out.println(m4.get("1234"));

	}
}
