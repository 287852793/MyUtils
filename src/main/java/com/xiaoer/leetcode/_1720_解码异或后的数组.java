package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1720_解码异或后的数组 {

	public static int[] decode(int[] encoded, int first) {
		int[] res = new int[encoded.length + 1];
		res[0] = first;
		for (int i = 0; i < encoded.length; i++) {
			first = first ^ encoded[i];
			res[1 + i] = first;
		}
		return res;
	}

	public static void main(String[] args) {
		int[] n = { 1, 2, 3 };
		int[] d = decode(n, 1);
		for (int i : d) {
			System.out.println(i);
		}
		System.out.println();
	}
}
