package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1224_最大相等频率 {
//	给出一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回其长度：
//
//	从前缀中 删除一个 元素后，使得所剩下的每个数字的出现次数相同。
//	如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
//
//	 
//
//	示例 1：
//
//	输入：nums = [2,2,1,1,5,3,3,5]
//	输出：7
//	解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4]=5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
//	示例 2：
//
//	输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
//	输出：13
//	示例 3：
//
//	输入：nums = [1,1,1,2,2,2]
//	输出：5
//	示例 4：
//
//	输入：nums = [10,2,8,9,3,8,1,5,2,3,7,6]
//	输出：8
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/maximum-equal-frequency
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int maxEqualFreq(int[] nums) {
		if (nums.length < 3) {
			return nums.length;
		}
		Map<Integer, Integer> note = new HashMap<Integer, Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for (int n : nums) {
			if (note.containsKey(n)) {
				note.put(n, note.get(n) + 1);
			} else {
				note.put(n, 1);
			}
			list.add(n);
		}
		while (!isValid(note)) {
			int n = list.remove(list.size() - 1);
			if (note.get(n) > 1) {
				note.put(n, note.get(n) - 1);
			} else {
				note.remove(n);
			}
		}
		return list.size();
	}

	public static boolean isValid(Map<Integer, Integer> note) {
		if (note.keySet().size() == 1) {
			return true;
		}
		int a = -1;
		int b = -1;
		boolean la = false;
		boolean lb = false;

		for (Integer n : note.values()) {
			if (a < 0) {
				a = n;
			} else if (a == n) {
				la = true;
			} else if (b < 0) {
				b = n;
			} else if (b == n) {
				lb = true;
			} else {
				return false;
			}
			if (a > 0 && b > 0 && a != 1 && b != 1 && Math.abs(a - b) > 1) {
				return false;
			}
			if (la && lb) {
				return false;
			}
		}
		if (a > 1 && b < 0) {
			return false;
		}
		if (la && b != 1 && b > 0 && b - a != 1) {
			return false;
		}
		if (lb && a != 1 && a > 0 &&  a - b != 1) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
//		int[] nums = new int[] { 2, 2, 1, 1, 5, 3, 3, 5 };
//		int[] nums = new int[] {1,1,1,2,2,2,3,3,3,4,4,4,5};
		int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		int[] nums = new int[] { 1, 1, 1, 2, 2, 2, 3, 3, 3 };

		System.out.println(maxEqualFreq(nums));
	}

}
