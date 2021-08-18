package com.xiaoer.leetcode;

public class _0042_接雨水 {
//	给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//	 
//
//	示例 1：
//
//
//
//	输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//	输出：6
//	解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
//	示例 2：
//
//	输入：height = [4,2,0,3,2,5]
//	输出：9
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/trapping-rain-water
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int trap(int[] height) {
		if (height.length < 1) {
			return 0;
		}
		int size = height.length;
		int[] left = new int[size];
		left[0] = 0;
		int[] right = new int[size];
		right[size - 1] = 0;

		for (int i = 1; i < size; i++) {
			left[i] = Math.max(left[i - 1], height[i - 1]);
		}

		for (int i = size - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], height[i + 1]);
		}

		int r = 0;
		for (int i = 1; i < size - 1; i++) {
			r += Math.max(0, Math.min(left[i], right[i]) - height[i]);
		}

		return r;
	}

	public static void main(String[] args) {
		int[] h = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap(h));
	}
}
