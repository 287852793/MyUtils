package com.xiaoer.leetcode;

public class _OFFERII012_左右两边子数组的和相等 {
	public int pivotIndex(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = nums[i] + dp[i - 1];
		}
		if (dp[dp.length - 1] - dp[0] == 0) {
			return 0;
		}
		for (int i = 1; i < dp.length; i++) {
			if (dp[i - 1] == dp[dp.length - 1] - dp[i]) {
				return i;
			}
		}
		return -1;
	}
}
