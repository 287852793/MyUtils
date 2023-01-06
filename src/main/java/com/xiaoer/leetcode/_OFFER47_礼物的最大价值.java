package com.xiaoer.leetcode;

public class _OFFER47_礼物的最大价值 {
	public int maxValue(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] res = new int[m][n];
		res[0][0] = grid[0][0];
		
		for (int i = 1; i < m; i++) {
			res[i][0] = res[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < n; i++) {
			res[0][i] = res[0][i - 1] + grid[0][i];
		}
		
		
		for (int i = 1; i < m; i++) {
			
		}
		return 0;
	}
}
