package com.xiaoer.leetcode;

import java.util.Arrays;

public class _0053_最大子序和 {
	public static int maxSubArray(int[] nums) {
		int res = nums[0];
		int sum = 0;
		for (int num : nums) {
			if (sum > 0)
				sum += num;
			else
				sum = num;
			res = Math.max(res, sum);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maxSubArray(nums));
	}
}
