package com.xiaoer.leetcode;

import java.util.PriorityQueue;
import java.util.Stack;

public class _0901_股票价格跨度 {

	// 用单调栈
	
	int[] note;
	int i;
	PriorityQueue<Integer> queue;
	public _0901_股票价格跨度() {
		this.note = new int[10001];
		i = 0;
		this.queue = new PriorityQueue<Integer>();
	}

	public int next(int price) {
		return 0;
	}
	
	public static void main(String[] args) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.offer(3);
		q.offer(5);
		q.offer(1);
	}
}
