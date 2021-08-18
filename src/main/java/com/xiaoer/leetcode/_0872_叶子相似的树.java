package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0872_叶子相似的树 {

	public static boolean leafSimilar(TreeNode0872 root1, TreeNode0872 root2) {
		List<Integer> list1 = new ArrayList<Integer>();
		list1 = makeval(list1, root1);
		List<Integer> list2 = new ArrayList<Integer>();
		list2 = makeval(list2, root2);
		System.out.println(list1.toString());
		System.out.println(list2.toString());
		
		return list1.toString().equals(list2.toString());
	}

	public static List<Integer> makeval(List<Integer> list, TreeNode0872 node) {
		if (node.left == null && node.right == null) {
			list.add(node.val);
		} else {
			if (node.left != null) {
			    list = makeval(list, node.left);
			}
			if (node.right != null) {
				list = makeval(list, node.right);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		TreeNode0872 root1 = new TreeNode0872(1);
		TreeNode0872 node11 = new TreeNode0872(2);
		TreeNode0872 node12 = new TreeNode0872(3);
		root1.left = node11;
		root1.right = node12;
		
		TreeNode0872 root2 = new TreeNode0872(1);
		TreeNode0872 node21 = new TreeNode0872(3);
		TreeNode0872 node22 = new TreeNode0872(2);
		root2.left = node21;
		root2.right = node22;
		
		System.out.println(leafSimilar(root1, root2));
		
	}
}

class TreeNode0872 {
	int val;
	TreeNode0872 left;
	TreeNode0872 right;

	TreeNode0872() {
	}

	TreeNode0872(int val) {
		this.val = val;
	}

	TreeNode0872(int val, TreeNode0872 left, TreeNode0872 right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
