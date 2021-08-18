package com.xiaoer.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _0692_前K个高频单词 {
	public static List<String> topKFrequent(String[] words, int k) {
		return Arrays.stream(words) //
				.parallel() //
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) //
				.entrySet() //
				.stream() //
				.sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed().thenComparing(Map.Entry::getKey)) //
				.limit(k) //
				.map(Map.Entry::getKey) //
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		int[] s = { 4, 5, 6, 5, 4, 3, 2 };
		String[] w = { "a", "b", "c" };
		int asInt = Arrays.stream(s).parallel().reduce((a, b) -> {
			System.out.println(a + "," + b);
			return a + b;
		}).getAsInt();

		String string = Arrays.stream(w).parallel().reduce((a, b) -> {
			System.out.println(a + "," + b);
			return a + b;
		}).get();
		System.out.println(asInt);
		System.out.println(string);

		double[] d = { 9, 16, 25, 36, 49 };
		Arrays.stream(s).parallel().mapToDouble(Math::sqrt);
		Arrays.stream(s).parallel().forEach(System.out::println);
	}
}
