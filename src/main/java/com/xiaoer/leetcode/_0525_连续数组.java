package com.xiaoer.leetcode;

public class _0525_连续数组 {
//	给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
//	 
//
//	示例 1:
//
//	输入: nums = [0,1]
//	输出: 2
//	说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
//	示例 2:
//
//	输入: nums = [0,1,0]
//	输出: 2
//	说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/contiguous-array
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int findMaxLength(int[] nums) {
		int[] sums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			sums[i + 1] = sums[i] + (nums[i] == 0 ? -1 : 1);
		}
		int j = sums.length - 1;
		while (j > 0) {
			int i = 0;
			int k = j;
			while (k < sums.length) {
				if (sums[k] - sums[i] == 0) {
					return k - i;
				} else {
					i++;
					k++;
				}
			}
			j--;
		}

		return 0;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 1, 1, 0, 1, 1, 1, 0 };
		System.out.println(findMaxLength(nums));
	}
}
