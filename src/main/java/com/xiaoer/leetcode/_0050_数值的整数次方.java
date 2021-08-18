package com.xiaoer.leetcode;

public class _0050_数值的整数次方 {
//	实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
//
//	 
//
//	示例 1：
//
//	输入：x = 2.00000, n = 10
//	输出：1024.00000
//	示例 2：
//
//	输入：x = 2.10000, n = 3
//	输出：9.26100
//	示例 3：
//
//	输入：x = 2.00000, n = -2
//	输出：0.25000
//	解释：2-2 = 1/22 = 1/4 = 0.25
//
//	来源：力扣（LeetCode）
//	链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	public static double myPow(double x, int n) {

		if (n == 0 || x == 1d) {
			return 1d;
		} else {
			double s = x;
			if (n > 0) {
				for (int i = 1; i < n; i++) {
					s *= x;
				}
			} else if (n < 0) {
				for (int i = 1; i < -n; i++) {
					s *= x;
				}
				s = 1d / s;
			}
			return s;
		}
	}

	public static void main(String[] args) {
		System.out.println();
//		System.out.println(Math.pow(1.0, 33333));
//		System.out.println(myPow(1.0, 333333333));
	}
}
