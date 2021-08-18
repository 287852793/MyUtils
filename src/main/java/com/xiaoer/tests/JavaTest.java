package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadUtil;

public class JavaTest {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();

		try {
			for (int i = 0; i < 1000; i++) {
				new JavaTest().f();
				TimeUnit.MILLISECONDS.sleep(10);
				if (i % 10 == 0) {
					System.out.println("finish " + i);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

	}

	public void f() {
		List<Integer> list = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 2000000; i++) {
			Integer n = r.nextInt();
			list.add(n);
		}
	}
}
