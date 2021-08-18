package com.xiaoer.leetcode;

import java.util.Arrays;

public class _0198_打家劫舍 {
	public static int rob(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}

		System.out.println(Arrays.toString(dp));
		return dp[nums.length - 1];

	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 1, 3, 100 };
		System.out.println(rob(nums));
	}
}
