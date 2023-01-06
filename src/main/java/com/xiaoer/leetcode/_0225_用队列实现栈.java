package com.xiaoer.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _0225_用队列实现栈 {

}

class MyStack {
	Queue<Integer> a = new LinkedList<Integer>();
	Queue<Integer> b = new LinkedList<Integer>();
	
	
	/** Initialize your data structure here. */
	public MyStack() {
		
	}

	/** Push element x onto stack. */
	public void push(int x) {
		while (!a.isEmpty()) {
			b.offer(a.poll());
		}
		a.offer(x);
		while (!b.isEmpty()) {
			a.offer(b.poll());
		}
	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		return a.poll();
	}

	/** Get the top element. */
	public int top() {
		return a.peek();
	}

	/** Returns whether the stack is empty. */
	public boolean empty() {
		return a.isEmpty();
	}
}
