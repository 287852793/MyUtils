package com.xiaoer.leetcode;

public class _LCP19_秋叶收藏集 {

	public static int minimumOperations(String leaves) {
		char[] arr = leaves.toCharArray();
		System.out.println(arr.length);
		int s = 0;
		if (arr[0] == 'y') {
			s++;
		}
		if (arr[arr.length - 1] == 'y') {
			s++;
		}

		int y = 0;
		for (int i = 1; i < arr.length - 1; ++i) {
			if (arr[i] == 'y') {
				y++;
			}
		}

		if (y == 0) {
			return s + 1;
		}

		int m = 0, n = 0;
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] == 'y') {
				m++;
			} else {
				if (m > 0) {
					m--;
				}
			}
			n = Math.max(m, n);
		}
		return y- n +s;
	}

	public static void main(String[] args) {
		String leaves = "ryyyrrrryrryyyyrrryrryyyryrryryyrryyyryyryyyyryrrryryyryrryyryryryrryyrryyyryrrryryryrrrryrrrrrryry";
//		System.out.println(l.chars().filter(item -> item == 'r').count());
		System.out.println(minimumOperations(leaves));
	}
	
	public static int f(String s) {
		char[] arr = s.toCharArray();
		int t = 0, r = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 'L') {
				t--;
			}
			if (arr[i] == 'R') {
				t++;
			}
			if (t == 0) {
				r++;
			}
		}
		return r;
	}
	
}
