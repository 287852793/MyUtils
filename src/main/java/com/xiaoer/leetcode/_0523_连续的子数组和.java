package com.xiaoer.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _0523_连续的子数组和 {
//	给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
//	子数组大小 至少为 2 ，且
//	子数组元素总和为 k 的倍数。
//	如果存在，返回 true ；否则，返回 false 。
//
//	如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
//
//	 
//
//	示例 1：
//
//	输入：nums = [23,2,4,6,7], k = 6
//	输出：true
//	解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//	示例 2：
//
//	输入：nums = [23,2,6,4,7], k = 6
//	输出：true
//	解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
//	42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//	示例 3：
//
//	输入：nums = [23,2,6,4,7], k = 13
//	输出：false
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/continuous-subarray-sum
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	// 超时算法
//	public static boolean checkSubarraySum(int[] nums, int k) {
//		int[] sums = new int[nums.length + 1];
//		for (int i = 0; i < nums.length; i++) {
//			sums[i + 1] = sums[i] + nums[i];
//		}
//		
//		for (int i = 0; i < sums.length - 2; i++) {
//			int a = sums[i];
//			for (int j = i + 2; j < sums.length; j++) {
//				int b = sums[j];
//				if ((b - a) % k == 0) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	public static boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> note = new HashMap<>();
		note.put(0, -1);
		int rem = 0;
		for (int i = 0; i < nums.length; i++) {
			rem = (rem + nums[i]) % k;
			if (note.containsKey(rem)) {
				if (i - note.get(rem) > 1) {
					return true;
				}
			} else {
				note.put(rem, i);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 5, 0, 0, 0 };
		int k = 3;
		System.out.println(checkSubarraySum(nums, k));
	}
}
