package com.xiaoer.leetcode;

public class _0983_最低票价 {
	public static int mincostTickets(int[] days, int[] costs) {
		if (days.length < 1) {
			return 0;
		}
		int idx = 0;
		int[] dp = new int[days[days.length - 1] + 1];
		for (int i = 1; i <= dp.length; i++) {
			if (i != days[idx]) {
				dp[i] = dp[i - 1];
			} else {
				int a = dp[i - 1] + costs[0];
				int b = dp[Math.max(0, i - 7)] + costs[1];
				int c = dp[Math.max(0, i - 30)] + costs[2];

				dp[i] = Math.min(c, Math.min(a, b));
				if (++idx == days.length) {
					break;
				}
			}
		}

		return dp[days[days.length - 1]];
	}

	public static void main(String[] args) {
		int[] days = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
		int[] costs = { 2, 7, 15 };
		System.out.println(mincostTickets(days, costs));
	}
}
