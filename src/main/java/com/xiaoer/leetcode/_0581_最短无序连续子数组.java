package com.xiaoer.leetcode;

public class _0581_最短无序连续子数组 {

	public static int findUnsortedSubarray(int[] nums) {

		if (nums.length < 2) {
			return 0;
		}
		int a = -1, b = -1;
		int max = nums[0], min = nums[nums.length - 1];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < max) {
				b = i;
			}
			if (nums[nums.length - i - 1] > min) {
				a = nums.length - i - 1;
			}
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[nums.length - i - 1]);
		}
		System.out.println(a);
		System.out.println(b);
		if (a == -1) {
			return 0;
		} else {
			return b - a + 1;
		}

	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, 3, 2, 4 };
		System.out.println(findUnsortedSubarray(nums));
	}
}
