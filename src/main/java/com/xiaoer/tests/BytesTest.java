package com.xiaoer.tests;

import java.util.TreeSet;

public class BytesTest {
	public static void main(String[] args) {
		try {
			int length1 = new String("110.123456789, 11.123456789 ").getBytes("UTF-8").length;
			System.out.println(length1);
			int length2 = new String("110.123456789, 11.123456789 ").getBytes("GBK").length;
			System.out.println(length2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
