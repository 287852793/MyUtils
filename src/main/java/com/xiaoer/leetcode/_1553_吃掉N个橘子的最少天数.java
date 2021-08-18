package com.xiaoer.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1553_吃掉N个橘子的最少天数 {
	static Map<Integer, Integer> note = new HashMap<Integer, Integer>();

	public static int minDays(int n) {
		if (n < 3) {
			return n;
		}
		if (note.containsKey(n)) {
			return note.get(n);
		}
		
		int r = Math.min(minDays(n / 2) + n%2, minDays(n / 3) + n%3)+1;
		
		note.put(n, r);
		return r;
	}

	public static void main(String[] args) {
		System.out.println(minDays(6));
	}
}
