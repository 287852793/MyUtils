package com.xiaoer.tests;

import java.util.Set;

import cn.hutool.core.util.ClassUtil;

public class HutoolScanPackageTest {
	public static void main(String[] args) {
		Set<Class<?>> set1 = ClassUtil.scanPackage();
		System.out.println(set1.toString());
		
		
		Set<Class<?>> set2 = ClassUtil.scanPackage("");
		System.out.println(set2.toString());
		
	}
}
