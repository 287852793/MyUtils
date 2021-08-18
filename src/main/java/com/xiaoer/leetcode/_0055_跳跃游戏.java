package com.xiaoer.leetcode;

public class _0055_跳跃游戏 {

	public static boolean canJump(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		if (nums[0] == 0) {
			return false;
		}
		int t = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] > t) {
				t = 1;
			} else {
				t++;
			}
		}
		return t == 1;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 0, 0, 3, 0, 1, 4, 4 };
		System.out.println(canJump(nums));
	}
}
