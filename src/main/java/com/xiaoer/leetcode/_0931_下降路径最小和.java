package com.xiaoer.leetcode;

public class _0931_下降路径最小和 {
	public static int minFallingPathSum(int[][] matrix) {
		int length = matrix.length;
		int[][] dp = new int[length][length];
		
		for (int i = 0; i < length; i++) {
			dp[0][i] = matrix[0][i];
		}
		
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < length; j++) {
				int a = Integer.MAX_VALUE;
				int b = Integer.MAX_VALUE;
				int c = Integer.MAX_VALUE;
				if (j != 0) {
					a = dp[i - 1][j - 1];
				}
				if (j != length - 1) {
					c = dp[i - 1][j + 1];
				}
				b = dp[i - 1][j];
				dp[i][j] = matrix[i][j] + Math.min(c, Math.min(a, b));
			}
		}
		
		int r = dp[length - 1][0];
		for (int i = 1; i < length; i++) {
			if (dp[length - 1][i] < r) {
				r = dp[length - 1][i];
			}
		}
		
		return r;
	}

	public static void main(String[] args) {

	}
}
