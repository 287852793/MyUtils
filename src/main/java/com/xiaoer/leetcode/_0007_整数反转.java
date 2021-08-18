package com.xiaoer.leetcode;

public class _0007_整数反转 {
//	给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
//
//	如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
//
//	假设环境不允许存储 64 位整数（有符号或无符号）。
//	 
//
//	示例 1：
//
//	输入：x = 123
//	输出：321
//	示例 2：
//
//	输入：x = -123
//	输出：-321
//	示例 3：
//
//	输入：x = 120
//	输出：21
//	示例 4：
//
//	输入：x = 0
//	输出：0
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/reverse-integer
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static int reverse(int x) {

		// good..
		long n = 0;
		while (x != 0) {
			n = n * 10 + x % 10;
			x = x / 10;
		}
		return (int) n == n ? (int) n : 0;

		// my...
//		String s, n = "";
//		if (x >= 0) {
//			s = x + "";
//		} else {
//			s = (-x) + "";
//		}
//		for (int i = s.length() - 1; i >= 0; i--) {
//			n += s.charAt(i);
//		}
//		try {
//			int r = Integer.valueOf(n);
//			if (x < 0) {
//				r *= -1;
//			}
//			return r;
//		} catch (NumberFormatException e) {
//			return 0;
//		}

	}

	public static void main(String[] args) {
		System.out.println(reverse(1534236469));
	}
}
