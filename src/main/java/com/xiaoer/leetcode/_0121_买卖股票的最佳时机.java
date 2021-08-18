package com.xiaoer.leetcode;

public class _0121_买卖股票的最佳时机 {

	public static int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int min = prices[0];
		int[] dp = new int[prices.length];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], prices[i] - min);
			min = Math.min(min, prices[i]);

		}
		return dp[prices.length - 1];
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2 };
		System.out.println(maxProfit(prices));
	}
}
