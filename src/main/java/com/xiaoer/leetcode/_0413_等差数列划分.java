package com.xiaoer.leetcode;

import java.util.Arrays;

public class _0413_等差数列划分 {

	public static int numberOfArithmeticSlices(int[] nums) {
		if (nums.length < 3) {
			return 0;
		}

		int[] d = new int[nums.length - 1];
		for (int i = 1; i < nums.length; i++) {
			d[i - 1] = nums[i] - nums[i - 1];
		}

		System.out.println(Arrays.toString(d));

		int sum = 0;
		int t = d[0];
		int count = 1;
		for (int i = 1; i < d.length; i++) {
			if (d[i] == t) {
				sum += count;
				count++;
			} else {
				count = 1;
				t = d[i];
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 7, 8, 9 };
		System.out.println(numberOfArithmeticSlices(nums));
	}
}
