package com.xiaoer.ds;

import java.util.Arrays;

public class KMP {

	public static int indexOf(String ts, String ps) {
		return -1;
	}

	public static int[] getNext(String ps) {
		char[] p = ps.toCharArray();
		int[] next = new int[ps.length()];
		int k = -1;
		next[0] = k;
		int j = 0;

		while (j < ps.length() - 1) {
			if (k == -1 || p[j] == p[k]) {
				if (p[++j] == p[++k]) {
					next[j] = next[k];
				} else {
					next[j] = k;
				}
			} else {
				k = next[k];
			}
			System.out.println(Arrays.toString(next));
		}

		return next;
	}
	
	public static void main(String[] args) {
		String ps = "ABACDABABC";
		int[] next = getNext(ps);
		System.out.println(Arrays.toString(next));
	}
}
