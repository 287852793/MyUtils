package com.xiaoer.leetcode;

public class _0165_比较版本号 {
	public static int compareVersion(String version1, String version2) {
		String[] s1 = version1.split("\\.");
		String[] s2 = version2.split("\\.");
		int index = 0;
		int t1 = Integer.valueOf(s1[index]);
		int t2 = Integer.valueOf(s2[index]);
		int maxLength = Math.max(s1.length, s2.length);
		index++;
		while (t1 == t2 && (index != maxLength)) {
			if (index < s1.length) {
				t1 = Integer.valueOf(s1[index]);
			} else {
				t1 = 0;
			}
			if (index < s2.length) {
				t2 = Integer.valueOf(s2[index]);
			} else {
				t2 = 0;
			}
			index++;
		}
		System.out.println(t1);
		System.out.println(t2);
		if (t1 == t2) {
			return 0;
		} else if (t1 > t2) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		String version1 = "7.5.3.4";
		String version2 = "7.5.3";	
		System.out.println(compareVersion(version1, version2));
	}
}
