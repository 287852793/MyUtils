package com.xiaoer.leetcode;

public class _1254_统计封闭岛屿的数目 {
	public static int closedIsland(int[][] grid) {
		int r = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {
					if (f(grid, i, j)) {
						r++;
					}
				}
			}
		}

		return r;
	}

	public static boolean f(int[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return false;
		}
		if (grid[i][j] == 0) {
			grid[i][j] = 1;
			return f(grid, i - 1, j) & f(grid, i + 1, j) & f(grid, i, j + 1) & f(grid, i, j - 1);
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 1, 1, 1, 1, 1, 0 }, { 1, 0, 0, 0, 0, 1, 1, 0 }, { 1, 0, 1, 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 0, 1, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 0 } };
		System.out.println(closedIsland(grid));
	}
}
