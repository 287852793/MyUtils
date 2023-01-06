package com.xiaoer.leetcode;

public class _0162_寻找峰值 {
	public int findPeakElement(int[] nums) {
		int r = 0;
		int t = nums[0];
		boolean a = false, b = false;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > t) {
				r = i;
				a = true;
			} else if (a && !b && nums[i] < t) {
				return i - 1;
			}
			t = nums[i];
		}
		return r;
	}
}
