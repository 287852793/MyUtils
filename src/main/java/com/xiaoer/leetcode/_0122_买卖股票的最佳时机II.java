package com.xiaoer.leetcode;

public class _0122_买卖股票的最佳时机II {

	public static int maxProfit(int[] prices) {
		int[] dp = new int[prices.length - 1];
		for (int i = 0; i < prices.length - 1; i++) {
			int t = prices[i + 1] - prices[i];
			if (t > 0) {
				dp[i] = t;
			}
		}
		int s = 0;
		for (int i = 0; i < dp.length; i++) {
			s += dp[i];
		}
		return s;
	}

	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 4, 5 };
		System.out.println(maxProfit(prices));
	}
}
