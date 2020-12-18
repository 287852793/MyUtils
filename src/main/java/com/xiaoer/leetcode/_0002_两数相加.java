package com.xiaoer.leetcode;

public class _0002_两数相加 {
//	给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
//	如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//	您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//	示例：
//
//	输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//	输出：7 -> 0 -> 8
//	原因：342 + 465 = 807

	// bad....
	public static class ListNode {
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

	static class Solution {

		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

			// my...
			ListNode res = new ListNode();
			ListNode tmp = res;

			int c = 0;
			while (l1 != null || l2 != null || c != 0) {
				int v1 = l1 == null ? 0 : l1.val;
				int v2 = l2 == null ? 0 : l2.val;

				int sum = v1 + v2 + c;
				c = sum / 10;
				tmp.next = new ListNode(sum % 10);
				tmp = tmp.next;

				l1 = l1 == null ? null : l1.next;
				l2 = l2 == null ? null : l2.next;
			}

			return res.next;

			// good...
//			ListNode root = new ListNode(0);
//	        ListNode cursor = root;
//	        int carry = 0;
//	        while(l1 != null || l2 != null || carry != 0) {
//	            int l1Val = l1 != null ? l1.val : 0;
//	            int l2Val = l2 != null ? l2.val : 0;
//	            int sumVal = l1Val + l2Val + carry;
//	            carry = sumVal / 10;
//	            
//	            ListNode sumNode = new ListNode(sumVal % 10);
//	            cursor.next = sumNode;
//	            cursor = sumNode;
//	            
//	            if(l1 != null) l1 = l1.next;
//	            if(l2 != null) l2 = l2.next;
//	        }
//	        
//	        return root.next;

			// bad...
//			long v1, v2;
//			v1 = getVal(l1);
//			System.out.println(v1);
//			v2 = getVal(l2);
//			System.out.println(v2);
//			System.out.println("===");
//
//			long s = v1 + v2;
//			System.out.println(s);
//			ListNode res = new ListNode((int)(s % 10));
//			System.out.println("+" +  res.val);
//			s = s / 10;
//			ListNode tmp = res;
//			while (s > 0) {
//				System.out.println("-" + s);
//				ListNode t = new ListNode((int)s % 10);
//				System.out.println("+" +  t.val);
//				tmp.next = t;
//				tmp = t;
//				s = s / 10;
//			}
//			
//			System.out.println("fin : " + getVal(res));
//
//			return res;
		}

//		public long getVal(ListNode n) {
//			long i = 10;
//			ListNode t = n;
//			long v = t.val;
//			while (t.next != null) {
//				v += t.next.val * i;
//				i *= 10;
//				t = t.next;
//			}
//			return v;
//		}

	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(1);
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(1);
		ListNode n6 = new ListNode(1);
		ListNode n7 = new ListNode(1);
		ListNode n8 = new ListNode(1);
		ListNode n9 = new ListNode(1);
		ListNode n10 = new ListNode(1);
		ListNode n11 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n10;
		n10.next = n11;

		new Solution().addTwoNumbers(l1, n1);

	}

}
