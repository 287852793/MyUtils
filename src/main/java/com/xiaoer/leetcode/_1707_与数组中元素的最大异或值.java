package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1707_与数组中元素的最大异或值 {
	public static int[] maximizeXor(int[] nums, int[][] queries) {
		int[] r = new int[queries.length];
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		for (int i = 0; i < r.length; i++) {
			int n = queries[i][0];
			int m = queries[i][1];
			int s = -1;
//			int s = nums[0] ^ n;

			System.out.println(n + "," + m + "," + s);
			for (int j = 0; j < nums.length; j++) {
				if (m < nums[j]) {
					break;
				}
				int t = nums[j] ^ n;
				if (t > s) {
					s = t;
				}
			}
			r[i] = s;

		}
		return r;
	}

	public static void main(String[] args) {
		int[] nums = { 5, 2, 4, 6, 6, 3 };
		int[][] queries = { { 12, 4 }, { 8, 1 }, { 6, 3 } };
		System.out.println(Arrays.toString(maximizeXor(nums, queries)));
	}
}
