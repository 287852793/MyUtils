package com.xiaoer.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _0817_链表组件 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public int numComponents(ListNode head, int[] nums) {
		Set<Integer> note = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			note.add(nums[i]);
		}
		int r = 0;
		int t = 0;
		
		while (head != null) {
			if (note.contains(head.val)) {
				if (t == 0) {
					r++;
				}
				t++;
			} else {
				t = 0;
			}
			head = head.next;
		}
		
		return r;
	}
}
