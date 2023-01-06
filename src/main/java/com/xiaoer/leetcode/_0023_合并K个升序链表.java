package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0023_合并K个升序链表 {

	public ListNode mergeKLists(ListNode[] lists) {
		List<ListNode> list = new ArrayList<ListNode>();
		for (int i = 0; i < lists.length; i++) {
			while (lists[i] != null) {
				list.add(lists[i]);
				lists[i] = lists[i].next;
			}
		}
		if (list.size() < 1) {
			return null;
		}
		list.sort((o1, o2) -> {
			return Integer.compare(o1.val, o2.val);
		});
		ListNode r = list.get(0);
		ListNode t = r;
		for (int i = 1; i < list.size(); i++) {
			t.next = list.get(i);
			t = t.next;
		}

		return r;
	}

//	public ListNode mergeKLists(ListNode[] lists) {
//		if (lists.length < 1) {
//			return null;
//		}
//		
//		List<ListNode> l = new ArrayList<>();
//		
//		for (int i = 0; i < lists.length; i++) {
//			if (lists[i] != null) {
//				l.add(lists[i]);
//			}
//		}
//		if (l.size() < 1) {
//			return null;
//		}
//		
//		final ListNode[] list = new ListNode[l.size()];
//		for (int i = 0; i < l.size(); i++) {
//			list[i] = l.get(i);
//		}
//		
//		ListNode root = new ListNode();
//		Integer i = IntStream.range(0, list.length).boxed().min((o1, o2) -> {
//			return Integer.compare(list[o1].val, list[o2].val);
//		}).get();
//		
//		ListNode n = root;
//		while (i != null && i > -1 && list[i] != null) {
//			n.next = new ListNode(list[i].val);
//			n = n.next;
//			list[i] = list[i].next;
//			i = IntStream.range(0, list.length).boxed().min((o1, o2) -> {
////				System.out.println(o1 + "," + o2);
////				System.out.println(lists[o1] + "," + lists[o2]);
//				if (list[o1] == null && list[o2] != null) {
//					return 1;
//				}
//				if (list[o1] != null && list[o2] == null) {
//					return -1;
//				}
//				if (list[o1] == null && list[o2] == null) {
//					return 1;
//				}
//				return Integer.compare(list[o1].val, list[o2].val);
//			}).get();
//			
//		}
//		return root.next;
//	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1, new ListNode(4, new ListNode(5)));
		ListNode n2 = new ListNode(1, new ListNode(3, new ListNode(4)));
		ListNode n3 = new ListNode(2, new ListNode(6));

		ListNode[] lists = new ListNode[3];
		lists[0] = n1;
		lists[1] = n2;
		lists[2] = n3;

		n1 = n1.next;
//		System.out.println(lists[0].val);

//		System.out.println(Arrays.stream(lists).min((o1, o2) -> {
//				return Integer.compare(o1.val, o2.val);
//			}).get().val);
//		n1 = n1.next;
//		System.out.println(Arrays.stream(lists).min((o1, o2) -> {
//			return Integer.compare(o1.val, o2.val);
//		}).get().val);
//		n2 = n2.next;
//		System.out.println(Arrays.stream(lists).min((o1, o2) -> {
//			return Integer.compare(o1.val, o2.val);
//		}).get().val);

		ListNode n = new _0023_合并K个升序链表().mergeKLists(lists);
		while (n != null) {
			System.out.print(n.val);
			n = n.next;
		}
	}

}

class ListNode {
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
