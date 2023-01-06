package com.xiaoer.utils;

import java.util.Arrays;

public class QuickSort {

	public static int[] f2(int[] rooms) {
		sort(rooms, 0, rooms.length - 1);
		return rooms;
	}

	public static void sort(int[] s, int l, int r) {
		if (l < r) {
			int i = l, j = r, x = s[l];
			while (i < j) {
				while (i < j && s[j] >= x) {
					j--;
				}
				if (i < j) {
					s[i++] = s[j];
				}
				while (i < j && s[i] < x) {
					i++;
				}
				if (i < j) {
					s[j--] = s[i];
				}
			}
			s[i] = x;
			sort(s, l, i - 1);
			sort(s, i + 1, r);
		}
	}

	public static void main(String[] args) {
		int[] s = { 14, 5, 6, 2, 7, 2, 3, 38, 99, 65, 32 };
		sort(s, 0, s.length - 1);
		System.out.println(Arrays.toString(s));
	}
}
