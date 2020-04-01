package com.xiaoer.tests;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) {
		
//		List<Integer> list = Collections.synchronizedList(new LinkedList<Integer>());
		
		ConcurrentLinkedQueue<Integer> list = new ConcurrentLinkedQueue<>();
		new Thread() {
			public void run() {
				for (int i = 0; i < 200; i++) {
					list.offer(i);
					try {
						TimeUnit.MILLISECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		//
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//
		for (int i = 0; i < 3; i++) {
			final int idx = i;
			new Thread() {
				public void run() {
					try {
						while (true) {
							Integer v = list.poll();
							if (v == null) {
								throw new Exception();
							}
							System.out.println(v);
							TimeUnit.MILLISECONDS.sleep(50);
						}
					} catch (Exception e) {
						System.out.println(idx + "结束");
						interrupt();
					}
				};
			}.start();
		}

//		ExecutorService executor = Executors.newFixedThreadPool(5);
//		for (int i = 0; i < 10; i++) {
//			final int idx = i;
//			executor.submit(() -> {
//				System.out.println("thread id is: " + Thread.currentThread().getId() + " :::: " + idx);
//				try {
//					Thread.sleep(1000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			});
//		}
	}
}
