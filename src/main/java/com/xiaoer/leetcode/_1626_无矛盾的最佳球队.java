package com.xiaoer.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class _1626_无矛盾的最佳球队 {
//	假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
//
//	然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
//
//	给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
//
//	 
//
//	示例 1：
//
//	输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//	输出：34
//	解释：你可以选中所有球员。
//	示例 2：
//
//	输入：scores = [4,5,6,5], ages = [2,1,2,1]
//	输出：16
//	解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
//	示例 3：
//
//	输入：scores = [1,2,3,5], ages = [8,9,10,1]
//	输出：6
//	解释：最佳的选择是前 3 名球员。
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/best-team-with-no-conflicts
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int bestTeamScore(int[] scores, int[] ages) {
		int[][] s = new int[scores.length][2];
		for (int i = 0; i < s.length; i++) {
			s[i][0] = ages[i];
			s[i][1] = scores[i];
		}

		Arrays.sort(s, (o1, o2) -> {
			return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
		});
		
		int[] dp = new int[s.length];

		for (int i = 0; i < s.length; i++) {
			dp[i] = s[i][1];

			for (int j = 0; j < i; j++) {
				
				if (s[j][1] <= s[i][1]) {
					dp[i] = Math.max(dp[i], s[i][1] + dp[j]);
				}
			}
		}

		return Arrays.stream(dp).max().getAsInt();
	}

	public static void main(String[] args) {
		int[] scores = { 4, 5, 6, 5 };
		int[] ages = { 2, 1, 2, 1 };
		System.out.println(bestTeamScore(scores, ages));

	}
}
