package com.xiaoer.leetcode;

import java.util.PriorityQueue;

public class _LCP30_魔塔游戏 {
	public static int magicTower(int[] nums) {
		int hp = 1;
	    for(int i = 0 ; i < nums.length ; i ++){
	    	hp += nums[i];
	    }
	    if(hp <= 0) {
	    	return -1; 
	    }

		int res = 0;
		hp = 1;
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		for (int i = 0; i < nums.length; i++) {
			hp += nums[i];
			if (nums[i] < 0) {
				heap.offer(nums[i]);
				if (hp <= 0) {
					res++;
					hp -= heap.poll();
				}
			}
			
		}
		return res;
	}
	

    //开始模拟
//    long blood = 1;
//    PriorityQueue<Integer> pq = new PriorityQueue<>();
//    int last = 0;
//    for(int i = 0 ; i < nums.length ; i ++){
//        if(nums[i] < 0){
//            pq.offer(nums[i]);
//            if(blood + nums[i] <= 0){ //这回合过后就要死了，需要把前面扣最多的血移到最后去
//                last ++; //记录移动的次数
//                blood -= pq.poll(); //加回之前扣除最多的血量
//            }
//        }
//        blood += nums[i];
//    }
//    return last;


	public static void main(String[] args) {
		int[] nums = { -1, -1, 10 };
		System.out.println(magicTower(nums));

//		PriorityQueue<Integer> q = new PriorityQueue<>();
//		q.offer(1);
//		q.offer(5);
//		q.offer(3);
//		
//		System.out.println(q.poll());
//		System.out.println(q.poll());
//		System.out.println(q.poll());
	}
}
