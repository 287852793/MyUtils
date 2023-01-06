package com.xiaoer.tests;

import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		
		tree.add(5);
		tree.add(3);
		tree.add(7);
		tree.add(9);
		tree.add(1);
		
		System.out.println(tree.first());
		System.out.println(tree.last());
	}
}
