package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _0695_岛屿的最大面积 {

	public static int maxAreaOfIsland(int[][] grid) {
		int max = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				max = Math.max(max, f(i, j, grid));
			}
		}
		return max;
	}

	private static int f(int i, int j, int[][] grid) {
		if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0 || grid[i][j] == 0) {
			return 0;
		} else {
			grid[i][j] = 0;
			return 1 + f(i, j - 1, grid) + f(i + 1, j, grid) + f(i, j + 1, grid) + f(i - 1, j, grid);
		}
	}

	public static void main(String[] args) {
		int[][] grid = { //
				{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
				{ 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 }, //
				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }//
		};
		System.out.println(grid[3][8]);
//		System.out.println(f(3, 8, grid, new ArrayList<Integer>()));
		System.out.println(maxAreaOfIsland(grid));
	}
}
