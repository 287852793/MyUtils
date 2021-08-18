package com.xiaoer.leetcode;

public class _0665_非递减数列 {
	public static boolean checkPossibility(int[] nums) {
		if (nums.length < 3) {
			return true;
		}
		int i = 0, t = nums[0];
		for (int j = 1; j < nums.length; j++) {
			if (nums[j] < t) {
				if (j + 1 >= nums.length) {
					nums[j] = t + 1;
					i++;
				} else if (j < 2) {
					nums[j - 1] = nums[j];
					i++;
				} else if (nums[j] - nums[j - 2] >= 0) {
					nums[j - 1] = nums[j - 2];
					i++;
				} else if (nums[j + 1] - nums[j - 1] >= 0) {
					nums[j] = nums[j - 1];
					i++;
				} else {
					return false;
				}

			}
			t = nums[j];
			if (i > 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] nums = { 5, 7, 1, 8 };
		System.out.println(checkPossibility(nums));
	}
}
