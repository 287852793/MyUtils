package com.xiaoer.leetcode;

public class _0897_递增顺序搜索树 {
	// 花式递归，不需要创建新的树节点
	TreeNode head;
	public TreeNode increasingBST(TreeNode root) {
		if(root==null){
			return null;
		}
		root.right = increasingBST(root.right);
		if (root.left != null) {
			TreeNode node = root.left;
			root.left = null;
			head = node;
			while (node.right != null)
				node = node.right;
			node.right = root;
			return increasingBST(head);
		}
		else
			return root;
    }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}