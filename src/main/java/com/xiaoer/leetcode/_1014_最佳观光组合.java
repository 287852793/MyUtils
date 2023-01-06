package com.xiaoer.leetcode;

public class _1014_最佳观光组合 {
	public static int maxScoreSightseeingPair(int[] values) {
		int a = values[0], r = Integer.MIN_VALUE;
		for (int i = 1; i < values.length; i++) {
			r = Math.max(r, a + values[i] - i);
			a = Math.max(a, values[i] + i);
		}
		return r;
	}

	public static void main(String[] args) {
		int[] values = { 2, 7, 7, 2, 1, 7, 10, 4, 3, 3 };
		System.out.println(maxScoreSightseeingPair(values));
	}
}
