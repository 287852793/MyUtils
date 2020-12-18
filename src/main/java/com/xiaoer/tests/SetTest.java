package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class SetTest {
	public static void main(String[] args) {
//		Set<Integer> set = new HashSet<>();
//		for (int i = 0; i < 10; i++) {
//			set.add(i);
//		}
//
//		for (int i = 0; i < 10; i++) {
//			Optional<Integer> any = set.stream().findAny();
//			System.out.println(any.get());
//		}
//		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			System.out.println(list.get(r.nextInt(list.size())));
		}

		String s = "http://1,http://2,http://3";
		String[] split = s.split(",|，");
		for (String a : split) {
			System.out.println(a);
		}

	}
}
