package com.xiaoer.leetcode;

public class _0045_跳跃游戏II {

	public static int jump(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 0;
		for (int i = 1; i < nums.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (nums[j] + j >= i && dp[j] + 1 < min) {
					min = dp[j] + 1;
				}
			}
			dp[i] = min;
		}

		return dp[nums.length - 1];
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1 };
		System.out.println(jump(nums));
	}
}
