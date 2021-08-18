package com.xiaoer.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _1817_查找用户活跃分钟数 {

	public static int[] findingUsersActiveMinutes(int[][] logs, int k) {
		int[] r = new int[k];
		Map<Integer, Set<Integer>> note = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < logs.length; i++) {

			if (note.containsKey(logs[i][0])) {
				note.get(logs[i][0]).add(logs[i][1]);
			} else {
				Set<Integer> s = new HashSet<Integer>();
				s.add(logs[i][1]);
				note.put(logs[i][0], s);
			}
		}

		for (Set<Integer> s : note.values()) {
			r[s.size() - 1]++;
		}

		return r;
	}

	public static void main(String[] args) {
		int[][] logs = { { 0, 5 }, { 1, 2 }, { 0, 2 }, { 0, 5 }, { 1, 3 } };
		int k = 5;
		System.out.println(Arrays.toString(findingUsersActiveMinutes(logs, k)));
	}
}
