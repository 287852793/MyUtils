package com.xiaoer.ds;

import java.util.Iterator;

public class TreeAncestor {
	private int[] parent;

	public TreeAncestor(int n, int[] parent) {
		this.parent = parent;
	}

	public int getKthAncestor(int node, int k) {
		while (parent[node] != -1 && k > 0) {
			node = parent[node];
			k--;
		}
		if (k > 0) {
			return -1;
		} else {
			return node;
		}
	}
}
