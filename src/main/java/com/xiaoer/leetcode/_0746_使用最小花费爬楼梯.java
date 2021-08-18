package com.xiaoer.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _0746_使用最小花费爬楼梯 {
//	数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
//
//	每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
//
//	请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
//
//	 
//
//	示例 1：
//
//	输入：cost = [10, 15, 20]
//	输出：15
//	解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
//	 示例 2：
//
//	输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//	输出：6
//	解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	static Map<Integer, Integer> note = new HashMap<Integer, Integer>();

	public static int minCostClimbingStairs(int[] cost) {
		if (cost.length < 2) {
			return 0;
		}

		int size = cost.length;
		int[] r = new int[size];

		for (int i = size - 1; i >= 0; i--) {
			r[i] = dp(cost, i);
		}

		return Math.min(r[size - 1], r[size - 2]);
	}

	public static int dp(int[] cost, int i) {
		int r;

		if (note.containsKey(i)) {
			return note.get(i);
		}

		if (i == 0 || i == 1) {
			r = cost[i];
		} else {
			r = Math.min(dp(cost, i - 1) + cost[i], dp(cost, i - 2) + cost[i]);
		}
		note.put(i, r);
		return r;
	}

	public static void main(String[] args) {
		int[] cost = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };
		System.out.println(minCostClimbingStairs(cost));
	}
}
