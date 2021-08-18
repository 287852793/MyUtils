package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1310_子数组异或查询 {
//	有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
//
//			对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
//
//			并返回一个包含给定查询 queries 所有结果的数组。
//
//			 
//
//			示例 1：
//
//			输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//			输出：[2,7,14,8] 
//			解释：
//			数组中元素的二进制表示形式是：
//			1 = 0001 
//			3 = 0011 
//			4 = 0100 
//			8 = 1000 
//			查询的 XOR 值为：
//			[0,1] = 1 xor 3 = 2 
//			[1,2] = 3 xor 4 = 7 
//			[0,3] = 1 xor 3 xor 4 xor 8 = 14 
//			[3,3] = 8
//			示例 2：
//
//			输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//			输出：[8,0,4,4]
//
//			来源：力扣（LeetCode）
//			链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray
//			著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int[] xorQueries(int[] arr, int[][] queries) {
		int[] r = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			r[i] = arr[queries[i][0]];
			for (int j = queries[i][0] + 1; j <= queries[i][1]; j++) {
				r[i] ^= arr[j];
			}
		}
		return r;
	}

	public static void main(String[] args) {
//		int[] arr = { 1, 3, 4, 8 };
//		int[][] queries = { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } };

		int[] arr = { 16 };
		int[][] queries = { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } };
		int[] r = xorQueries(arr, queries);
		System.out.println(Arrays.toString(r));
	}
}
