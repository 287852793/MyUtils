package com.xiaoer.game;

public class Num421 {
	public static void main(String[] args) {
		int n = 27;
		System.out.print(n + ",");
		while (n != 1) {
			if (n % 2 == 1) {
				n = 3 * n + 1;
			} else {
				n = n / 2;
			}
			System.out.print(n + ",");
		}
	}
}
