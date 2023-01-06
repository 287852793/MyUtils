package com.xiaoer.leetcode;

import java.util.List;

public class _0120_三角形最小路径和 {
	public int minimumTotal(List<List<Integer>> triangle) {
		for (int i = 1; i < triangle.size(); i++) {
			List<Integer> n = triangle.get(i);
			for (int j = 0; j < n.size(); j++) {
				List<Integer> m = triangle.get(i - 1);
				Integer t = n.get(j);
				if (j == 0) {
					n.set(j, t + m.get(j));
				} else if (j == n.size() - 1) {
					n.set(j, t + m.get(j - 1));
				} else {
					n.set(j, t + Math.min(m.get(j), m.get(j - 1)));
				}
			}
		}
		List<Integer> list = triangle.get(triangle.size() - 1);
		int r = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) < r) {
				r = list.get(i);
			}
		}
		return r;
    }
}
