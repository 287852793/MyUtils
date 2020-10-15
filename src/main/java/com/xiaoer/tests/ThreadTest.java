package com.xiaoer.tests;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
	public static void main(String[] args) {
		String threadName = UUID.randomUUID().toString();
		System.out.println(threadName);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				for (int i = 0; i < 10; i++) {
					System.out.println("t1 : " + count++);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}, threadName);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("stop thread");
		findThread(threadName).stop();

	}

	/**
	 * 通过线程组获得线程
	 *
	 * @param threadId
	 * @return
	 */
	public static Thread findThread(long threadId) {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		while (group != null) {
			Thread[] threads = new Thread[(int) (group.activeCount() * 1.2)];
			int count = group.enumerate(threads, true);
			for (int i = 0; i < count; i++) {
				if (threadId == threads[i].getId()) {
					return threads[i];
				}
			}
			group = group.getParent();
		}
		return null;
	}

	public static Thread findThread(String threadName) {
		if (threadName == null) {
			return null;
		}
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		while (group != null) {
			Thread[] threads = new Thread[(int) (group.activeCount() * 1.2)];
			int count = group.enumerate(threads, true);
			for (int i = 0; i < count; i++) {
				if (threadName.equals(threads[i].getName())) {
					return threads[i];
				}
			}
			group = group.getParent();
		}
		return null;
	}
}
