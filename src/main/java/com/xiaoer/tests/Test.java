package com.xiaoer.tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
//		System.out.println((int) Math.round(1.6));

		List<Double> list = Arrays.asList(1.1, 2d, 3d, 4d);
		Double result = list.stream().collect(Collectors.averagingDouble(d -> d));
		System.out.println(result);
	}
}
