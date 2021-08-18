package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1049_最后一块石头的重量II {

	public static int lastStoneWeightII(int[] stones) {
		int sum = Arrays.stream(stones).sum();
		int[] dp = new int[sum / 2 + 1];
		for (int i = 0; i < stones.length; i++) {
			for (int j = sum / 2; j >= stones[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
			}
		}
		return sum - 2 * dp[sum / 2];
	}

	public static void main(String[] args) {

	}
}
