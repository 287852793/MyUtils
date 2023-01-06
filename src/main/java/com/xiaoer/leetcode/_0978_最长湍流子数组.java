package com.xiaoer.leetcode;

public class _0978_最长湍流子数组 {
	public static int maxTurbulenceSize(int[] arr) {
//		int[] dp = new int[arr.length];
//		for (int i = 1; i < arr.length; i++) {
//			dp[i] = arr[i] - arr[i - 1];
//		}

		if (arr.length == 1) {
			return 1;
		}
		if (arr.length == 2) {
			return arr[0] == arr[1] ? 1 : 2;
		}
		int r = 1;
		int i = arr[1] > arr[0] ? 1 : -1;
		int t = 1;
		for (int j = 1; j < arr.length; j++) {
			if ((arr[j] - arr[j - 1]) * i > 0) {
				t++;
				i *= -1;
			} else {
				if (arr[j] > arr[j - 1]) {
					i = -1;
					t = 2;
				} else if (arr[j] == arr[j - 1]) {
					if (j + 1 < arr.length) {
						i = arr[j + 1] > arr[j] ? 1 : -1;
					}
					t = 1;
				} else {
					i = 1;
					t = 2;
				}

			}
			r = Math.max(t, r);
		}
		return r;
	}

	public static void main(String[] args) {
		int[] arr = { 9, 4, 2, 10, 7, 8, 8, 1, 9 };
//		int[] arr = { 4, 8, 12, 16 };
		System.out.println(maxTurbulenceSize(arr));
	}
}
