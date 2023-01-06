package com.xiaoer.leetcode;

import java.util.LinkedList;
import java.util.List;

public class _0933_ {
	List list;

	public _0933_() {
		this.list = new LinkedList();
	}

	public int ping(int t) {
		list.add(t);
		int r = 0;
		for (int i = list.size() - 1; i >= 0; i++) {
			if (t - (int)list.get(i) <= 3000) {
				r++;
			}
		}
		return r;
	}
}
