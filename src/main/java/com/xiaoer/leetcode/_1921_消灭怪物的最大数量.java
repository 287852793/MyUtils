package com.xiaoer.leetcode;

import java.util.Arrays;

public class _1921_消灭怪物的最大数量 {
	public static int eliminateMaximum(int[] dist, int[] speed) {

		for (int i = 0; i < dist.length; i++) {
			dist[i] = (int) Math.ceil(1.0 * dist[i] / speed[i]);
		}
		Arrays.sort(dist);
		int s = 1;
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] - i < 1) {
				break;
			} else {
				s++;
			}
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(Math.ceil(7 / 4.0));

		int[] dist = { 3, 2, 4 };
		int[] speed = { 5, 3, 2 };
		System.out.println(eliminateMaximum(dist, speed));
	}
}
