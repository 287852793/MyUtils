package com.xiaoer.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leet code 1172
 * 
 * @author pyf
 * @time 2023-04-28 08:39:53
 */
public class DinnerPlates {

	PriorityQueue<int[]> note;
	private int len;
	int max;

	public DinnerPlates(int capacity) {
		this.note = new PriorityQueue<int[]>((q1, q2) -> {
			return 0;
		});
		this.note.offer(new int[] { 0, 0 });
		this.len = capacity;
		this.max = 0;
	}

	public void push(int val) {

	}

	public int pop() {
		return 0;
	}

	public int popAtStack(int index) {
		return 0;
	}

}
