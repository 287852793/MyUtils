package com.xiaoer.leetcode;

import java.util.HashMap;

public class _0001_两数之和 {
//	给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//	你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//	示例:
//
//		给定 nums = [2, 7, 11, 15], target = 9
//
//		因为 nums[0] + nums[1] = 2 + 7 = 9
//		所以返回 [0, 1]

	public int[] twoSum(int[] nums, int target) {
		// <目标数与数的差，数的下标>
		HashMap<Integer, Integer> map = new HashMap<>();
		
		
		
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				return new int[] { map.get(nums[i]), i };
			}
			map.put(target - nums[i], i);
		}
		return null;
	}
}
