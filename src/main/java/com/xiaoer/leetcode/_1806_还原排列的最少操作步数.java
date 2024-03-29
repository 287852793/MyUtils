package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1806_还原排列的最少操作步数 {
//	给你一个偶数 n​​​​​​，已知存在一个长度为 n
//	的排列 perm，其中 perm[i]==i​（
//	下标 从 0
//	开始 计数）。
//
//	一步操作中，
//	你将创建一个新数组 arr，
//	对于每个 i：
//
//	如果 i%2==0，那么 arr[i]=perm[i/2]
//	如果 i%2==1，那么 arr[i]=perm[n/2+(i-1)/2]
//	然后将 arr​​
//	赋值​​给 perm。
//
//	要想使 perm 回到排列初始值，至少需要执行多少步操作？
//	返回最小的 非零 操作步数。
//
//	 
//
//	示例 1：
//
//	输入：n=2 输出：1 解释：最初，perm=[0,1]第 1 步操作后，perm=[0,1]所以，仅需执行 1
//	步操作 示例 2：
//
//	输入：n=4 输出：2 解释：最初，perm=[0,1,2,3]第 1 步操作后，perm=[0,2,1,3]第 2 步操作后，perm=[0,1,2,3]所以，仅需执行 2
//	步操作 示例 3：
//
//	输入：n=6 输出：4
//
//	来源：力扣（LeetCode）链接：https:// leetcode-cn.com/problems/minimum-number-of-operations-to-reinitialize-a-permutation
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int reinitializePermutation(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
		int s = 1;
		int[] t = a;
		while (true) {
			int[] b = new int[n];
			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					b[i] = t[i / 2];
				} else {
					b[i] = t[n / 2 + (i - 1) / 2];
				}
			}
			if (Arrays.equals(a, b)) {
				break;
			} else {
				t = b;
				s++;
			}
		}
		return s;
	}
	
	
	// 题解说 1 能回到最初位置，就代表数组和之前一样了，这题的解法可以按这个思路优化

	public static void main(String[] args) {
		System.out.println(reinitializePermutation(6));
	}
}
