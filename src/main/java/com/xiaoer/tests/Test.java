package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
//		System.out.println((int) Math.round(1.6));

//		List<Double> list = Arrays.asList(1.1, 2d, 3d, 4d);
//		Double result = list.stream().collect(Collectors.averagingDouble(d -> d));
//		System.out.println(result);

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add(i + "");
		}
	}

	public boolean f1(int[] rooms) {
		int hp = 1;
		for (int i = 0; i < rooms.length; i++) {
			hp += rooms[i];
		}
		if (hp <= 0) {
			return false;
		} else {
			return true;
		}
	}
}
