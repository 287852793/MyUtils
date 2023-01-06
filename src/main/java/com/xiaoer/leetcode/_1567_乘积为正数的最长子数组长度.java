package com.xiaoer.leetcode;

public class _1567_乘积为正数的最长子数组长度 {

	public static int getMaxLen(int[] nums) {
		int res = 0;
		int z = 0, f = 0;
		for (int n : nums) {
			if (n == 0) {
				z = 0;
				f = 0;
			} else if (n > 0) {
				z++;
				if (f > 0)
					f++;
				res = Math.max(res, z);
			} else {
				int temp = z;
				z = f;
				f = temp;
				f++;
				if (z > 0)
					z++;
				res = Math.max(res, z);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2 };
		System.out.println(getMaxLen(nums));
	}
}
