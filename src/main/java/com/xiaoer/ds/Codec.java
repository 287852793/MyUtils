package com.xiaoer.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Codec {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}
		List<String> list = new ArrayList<>();
		dfs1(root, list);
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			s.append(list.get(i));
			s.append(",");
		}
		s.deleteCharAt(s.length() - 1);
		return s.toString();
	}

	private void dfs1(TreeNode node, List<String> list) {
		if (node == null) {
			return;
		}
		list.add(String.valueOf(node.val));
		dfs1(node.left, list);
		dfs1(node.right, list);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == "") {
			return null;
		}
		int[] arr = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
		TreeNode res = dfs2(0, arr.length - 1, arr);
		return res;
	}

	private TreeNode dfs2(int l, int r, int[] arr) {
		if (l > r) {
			return null;
		}
		int n = Integer.valueOf(arr[l]);
		TreeNode t = new TreeNode(n);
//		int i = l + 1;
//		for (; i <= r; i++) {
//			if (arr[i] > n) {
//				break;
//			}
//		}

		int ll = l + 1, rr = r;
		while (ll < rr) {
			int mid = ll + rr >> 1;
			if (arr[mid] > n)
				rr = mid;
			else
				ll = mid + 1;
		}

		t.left = dfs2(l + 1, ll - 1, arr);
		t.right = dfs2(ll, r, arr);
		return t;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
