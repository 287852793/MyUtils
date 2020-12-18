package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _0003_无重复字符的最长子串 {
//	给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//	 
//
//	示例 1:
//
//	输入: s = "abcabcbb"
//	输出: 3 
//	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//	示例 2:
//
//	输入: s = "bbbbb"
//	输出: 1
//	解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//	示例 3:
//
//	输入: s = "pwwkew"
//	输出: 3
//	解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//	     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//	示例 4:
//
//	输入: s = ""
//	输出: 0

	static class Solution {
		public int lengthOfLongestSubstring(String s) {
			// good...
			// 记录字符上一次出现的位置
	        int[] last = new int[128];
	        for(int i = 0; i < 128; i++) {
	            last[i] = -1;
	        }
	        int n = s.length();

	        int res = 0;
	        int start = 0; // 窗口开始位置
	        for(int i = 0; i < n; i++) {
	            int index = s.charAt(i);
	            start = Math.max(start, last[index] + 1);
	            res   = Math.max(res, i - start + 1);
	            last[index] = i;
	        }

	        return res;
			
			
			// my...
//			if (s == null || s.length() < 1) {
//				return 0;
//			}
//
//			Set<Character> set = new HashSet<Character>();
//			int n = 0;
//			int max = 0;
//			boolean reset = true;
//			for (int m = 0; m <= s.length(); m++) {
//				String sub = s.substring(n, m);
//				System.out.println(sub);
//				
//				if (reset) {
//					set.clear();
//					sub.chars().forEach(c -> {
//						set.add((char) c);
//					});
//				} else {
//					set.add(sub.charAt(sub.length() - 1));
//				}
//				
//				if (sub.length() > set.size()) {
//					reset = true;
//					n++;
//				} else {
//					max = set.size();
//					reset = false;
//				}
//				System.out.println(set.toString());
//			}
//			return max;
		}
		
		
	}
	
	public static void main(String[] args) {
		int n = new Solution().lengthOfLongestSubstring("abcabcbb");
		System.out.println(n);
	}
}
