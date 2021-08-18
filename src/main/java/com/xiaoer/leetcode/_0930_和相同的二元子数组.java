package com.xiaoer.leetcode;

public class _0930_和相同的二元子数组 {

	public static int numSubarraysWithSum(int[] nums, int goal) {
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			int t = nums[i];
			int j = i + 1;
			while (t < goal && j < nums.length) {
				t += nums[j];
				j++;
			}
			if (t == goal) {
				r++;
				while (j < nums.length) {
					if (nums[j] == 0) {
						r++;
						j++;
					} else {
						break;
					}
				}
			}
		}
		return r;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 0, 0, 0, 0 };
		int goal = 0;
		System.out.println(numSubarraysWithSum(nums, goal));
	}
}
