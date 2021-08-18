package com.xiaoer.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0070_爬楼梯 {

	static Map<Integer, Integer> note = new HashMap<Integer, Integer>();
	public static int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (note.containsKey(n)) {
			return note.get(n);
		}
		
		int r = climbStairs(n - 1) + climbStairs(n - 2);
		note.put(n, r);
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(climbStairs(4));
	}
}
