package com.xiaoer.leetcode;

public class _0005_最长回文子串 {
	
//	5. 最长回文子串
//	给你一个字符串 s，找到 s 中最长的回文子串。
//
//	 
//
//	示例 1：
//
//	输入：s = "babad"
//	输出："bab"
//	解释："aba" 同样是符合题意的答案。
//	示例 2：
//
//	输入：s = "cbbd"
//	输出："bb"
//	示例 3：
//
//	输入：s = "a"
//	输出："a"
//	示例 4：
//
//	输入：s = "ac"
//	输出："a"
//	 
//
//	提示：
//
//	1 <= s.length <= 1000
//	s 仅由数字和英文字母（大写和/或小写）组成

	// good...
	public static String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
//         保存起始位置，测试了用数组似乎能比全局变量稍快一点
		int[] range = new int[2];
		char[] str = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
//             把回文看成中间的部分全是同一字符，左右部分相对称
//             找到下一个与当前字符不同的字符
			i = findLongest(str, i, range);
		}
		return s.substring(range[0], range[1] + 1);
	}

	public static int findLongest(char[] str, int low, int[] range) {
//         查找中间部分
		int high = low;
		while (high < str.length - 1 && str[high + 1] == str[low]) {
			high++;
		}
//         定位中间部分的最后一个字符
		int ans = high;
//         从中间向左右扩散
		while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
			low--;
			high++;
		}
//         记录最大长度
		if (high - low > range[1] - range[0]) {
			range[0] = low;
			range[1] = high;
		}
		return ans;
	}

	// my...
//	public static String longestPalindrome(String s) {
//		String res = s.charAt(0) + "";
//
//		for (int i = 0; i < s.length(); i++) {
//			// 1
//			String r1 = "";
//			int i1 = i;
//			int i2 = i + 1;
//			while (i1 > -1 && i2 < s.length() && s.charAt(i1) == s.charAt(i2)) {
//				r1 = s.charAt(i1) + r1 + s.charAt(i2);
//				i1--;
//				i2++;
//			}
//			// 2
//			String r2 = s.charAt(i) + "";
//			i1 = i - 1;
//			i2 = i + 1;
//			while (i1 > -1 && i2 < s.length() && s.charAt(i1) == s.charAt(i2)) {
//				r2 = s.charAt(i1) + r2 + s.charAt(i2);
//				i1--;
//				i2++;
//			}
//			if (r1.length() > res.length()) {
//				res = r1;
//			}
//			if (r2.length() > res.length()) {
//				res = r2;
//			}
//		}
//
//		return res;
//	}

	public static void main(String[] args) {
		String s = "aacabdkacaa";
		System.out.println(longestPalindrome(s));
	}
}