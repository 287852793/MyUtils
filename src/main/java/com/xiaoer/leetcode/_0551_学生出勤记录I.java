package com.xiaoer.leetcode;

public class _0551_学生出勤记录I {

	public static boolean checkRecord(String s) {
		int a = 0, b = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'A') {
				a++;
				b = 0;
			} else if (c == 'L') {
				b++;
			} else {
				b = 0;
			}
			if (a == 2 || b == 3) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s = "LALL";
		System.out.println(checkRecord(s));
	}
}
