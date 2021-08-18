package com.xiaoer.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0712_两个字符串的最小ASCII删除和 {
	// error
	public static int minimumDeleteSum(String s1, String s2) {
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		for (Character c1 : s1.toCharArray()) {
			if (m.containsKey(c1)) {
				m.put(c1, m.get(c1) + 1);
			} else {
				m.put(c1, 1);
			}
		}
		for (Character c2 : s2.toCharArray()) {
			if (m.containsKey(c2)) {
				int t = m.get(c2) - 1;
				if (t == 0) {
					m.remove(c2);
				} else {
					m.put(c2, t);
				}
			} else {
				m.put(c2, -1);
			}
		}
		int s = 0;
		for (Character c : m.keySet()) {
			s += Math.abs(m.get(c)) * (int) c;
		}

		return s;
	}

	public static void main(String[] args) {
		String s1 = "delete";
		String s2 = "leet";
		System.out.println(minimumDeleteSum(s1, s2));
	}
}
