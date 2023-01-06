package com.xiaoer.leetcode;

public class _1003_检查替换后的词是否有效 {
	public boolean isValid(String s) {
		while (s.contains("abc")) {
			s = s.replace("abc", "");
		}
		if (s.equals("")) {
			return true;
		}
		return false;
	}
}
