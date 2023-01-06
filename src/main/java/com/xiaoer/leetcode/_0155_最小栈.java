package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _0155_最小栈 {
	
	public _0155_最小栈() {

	}

	List<Integer> note = new ArrayList<Integer>();
	
	public void push(int val) {
		note.add(val);
	}

	public void pop() {
		note.remove(note.size() - 1);
	}

	public int top() {
		return note.get(note.size() - 1);
	}

	public int getMin() {
		return note.stream().min(Integer::compare).get();
	}
}
