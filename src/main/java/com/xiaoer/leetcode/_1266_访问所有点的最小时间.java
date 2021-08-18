package com.xiaoer.leetcode;

public class _1266_访问所有点的最小时间 {
	public static int minTimeToVisitAllPoints(int[][] points) {
		if (points.length <= 1) {
			return 0;
		}
		int r = 0;
		int x1 = points[0][0], y1 = points[0][1];
		for (int i = 1; i < points.length; i++) {
			int x2 = points[i][0], y2 = points[i][1];
			r += Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
			x1 = x2;
			y1 = y2;
		}
		return r;
	}

	public static void main(String[] args) {
//		int[][] points = { { 1, 1 }, { 3, 4 }, { -1, 0 } };
		int[][] points = { { 3, 2 }, { -2, 2 } };
		System.out.println(minTimeToVisitAllPoints(points));
	}
}
