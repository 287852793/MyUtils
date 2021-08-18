package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1563_石子游戏V {
	public static int stoneGameV(int[] stoneValue) {
		if (stoneValue.length < 1) {
			return 0;
		}
		int r = 0;
		int[] n = { 0, stoneValue.length - 1 };

		while (n[1] > n[0]) {
			n = dv(stoneValue, n);
			r += Arrays.stream(stoneValue, n[0], n[1] + 1).sum();
		}

		return r;
	}

	public static int[] dv(int[] s, int[] n) {
		int i = n[0];
		int j = n[1];

		int a = s[i];
		int b = s[j];

		while (j > i + 1) {
			if (a > b) {
				b += s[j - 1];
				j--;
			} else {
				a += s[i + 1];
				i++;
			}
		}
		if (a > b) {
			n[0] = j;
		} else if (a == b) {
			if (n[1] - j > i - n[0]) {
				n[0] = j;
			} else {
				n[1] = i;
			}
		} else {
			n[1] = i;
		}
		System.out.println(Arrays.toString(n));
		return n;
	}

	public static void main(String[] args) {
//		int[] s = { 6, 2, 3, 4, 5, 5 };
//		int[] s = { 7, 7, 7, 7, 7, 7, 7 };
		int[] s = { 2, 1, 1 };
		System.out.println(stoneGameV(s));
	}
}
