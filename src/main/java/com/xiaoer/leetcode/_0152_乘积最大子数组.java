package com.xiaoer.leetcode;

public class _0152_乘积最大子数组 {
//	给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
//	 
//
//	示例 1:
//
//	输入: [2,3,-2,4]
//	输出: 6
//	解释: 子数组 [2,3] 有最大乘积 6。
//	示例 2:
//
//	输入: [-2,0,-1]
//	输出: 0
//	解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/maximum-product-subarray
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	
	public static int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE, imax = 1, imin = 1; // 一个保存最大的，一个保存最小的。
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				int tmp = imax;
				imax = imin;
				imin = tmp;
			}
			imax = Math.max(imax * nums[i], nums[i]);
			imin = Math.min(imin * nums[i], nums[i]);

			System.out.println(imax + "," + imin);
			max = Math.max(max, imax);
			
//			System.out.println(max);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] n = { 1, 2, 0, 3, 4 };
		System.out.println(maxProduct(n));
	}
}
