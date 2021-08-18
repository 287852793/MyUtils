package com.xiaoer.tests;

public class ClassTest {
	public static void main(String[] args) {
		try {
//			System.out.println(Integer.class);
//			System.out.println(Class.forName("java.lang.Long"));
			
//			System.out.println(name);
			System.out.println(ClassTest.class.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
