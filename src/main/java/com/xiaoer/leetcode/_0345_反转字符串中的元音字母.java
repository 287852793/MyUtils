package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0345_反转字符串中的元音字母 {

	public static String reverseVowels(String s) {
		List<Character> chars = new ArrayList<>();
		chars.add('a');
		chars.add('e');
		chars.add('i');
		chars.add('o');
		chars.add('u');
		chars.add('A');
		chars.add('E');
		chars.add('I');
		chars.add('O');
		chars.add('U');
		

		StringBuilder sb = new StringBuilder(s);
		int i = 0;
		int j = s.length() - 1;
		while (j > i) {
			char a = s.charAt(i);
			char b = s.charAt(j);
			if (!chars.contains(a)) {
				i++;
				continue;
			}
			if (!chars.contains(b)) {
				j--;
				continue;
			}
			sb.replace(i, i + 1, b + "");
			sb.replace(j, j + 1, a + "");
			i++;
			j--;
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String s = "hello";
		System.out.println(reverseVowels(s));
	}
}
