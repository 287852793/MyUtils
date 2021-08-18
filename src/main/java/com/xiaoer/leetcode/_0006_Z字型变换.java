package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0006_Z字型变换 {
//	将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
//	比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//	P   A   H   N
//	A P L S I I G
//	Y   I   R
//	之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
//	请你实现这个将字符串进行指定行数变换的函数：
//
//	string convert(string s, int numRows);
//	 
//
//	示例 1：
//
//	输入：s = "PAYPALISHIRING", numRows = 3
//	输出："PAHNAPLSIIGYIR"
//	示例 2：
//	输入：s = "PAYPALISHIRING", numRows = 4
//	输出："PINALSIGYAHRPI"
//	解释：
//	P     I    N
//	A   L S  I G
//	Y A   H R
//	P     I
//	示例 3：
//
//	输入：s = "A", numRows = 1
//	输出："A"
//	 
//
//	提示：
//
//	1 <= s.length <= 1000
//	s 由英文字母（小写和大写）、',' 和 '.' 组成
//	1 <= numRows <= 1000

	public static String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		for (int i = 0; i < numRows; i++) {
			map.put(i, new ArrayList<String>());
		}
		for (int i = 0; i < s.length(); i++) {
			String c = s.charAt(i) + "";
			int k = i % (2 * numRows - 2);
			if (k > numRows - 1) {
				k = 2 * numRows - 2 - k;
			}
			map.get(k).add(c);
		}
		String res = "";
		for (int i = 0; i < numRows; i++) {
			List<String> list = map.get(i);
			for (int j = 0; j < list.size(); j++) {
				res += list.get(j);
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(convert("PAYPALISHIRING", 3));
	}
}
