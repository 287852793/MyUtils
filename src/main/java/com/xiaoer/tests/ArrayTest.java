package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[10]);
		Collections.fill(list, "?");
		System.out.println(String.join(",", list));
	}
}
