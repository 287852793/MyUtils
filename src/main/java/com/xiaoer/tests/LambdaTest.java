package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaTest {
	public static void main(String[] args) {
		List<A> list = new ArrayList<>();
//		List<A> list2 = new ArrayList<>();
		A a1 = new A();
		a1.setId(1);
		a1.setName("a1");

		A a2 = new A();
		a2.setId(2);
		a2.setName("a2");

		A a3 = new A();
		a3.setId(3);
		a3.setName("a3");

		list.add(a1);
		list.add(a2);
		list.add(a3);
		
		A a = list.stream().filter(item -> item.getId() == 1 && item.getName().equals("a1")).findAny().orElse(null);
		System.out.println(a);
//
//		list2 = list.stream().filter(item -> item.getId() == 1).collect(Collectors.toList());
//
//		System.out.println(list2);
		
//		Map<String, String> map = new HashMap<>();
//		map.put("1", "11");
//		map.put("2", "22");
//		map.put("3", "33");
//		map.put("4", "44");
		
//		String s = ;
//		System.out.println(map.keySet().stream().filter(item -> item.equals("5")));
	}

}

class A {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "A [id=" + id + ", name=" + name + "]";
	}

}
