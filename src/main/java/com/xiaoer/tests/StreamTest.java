package com.xiaoer.tests;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
	public static void main(String[] args) {
		String[] s = new String[] { "11", "22", "33" };
		List<String> list = Arrays.asList(s);
		long k = list.stream().filter(item -> item.contains("4")).count();
		System.out.println(k);
	
	}
	
}
