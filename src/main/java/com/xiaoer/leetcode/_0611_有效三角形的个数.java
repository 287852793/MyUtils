package com.xiaoer.leetcode;

import java.util.Arrays;

public class _0611_有效三角形的个数 {

	public static int triangleNumber(int[] nums) {
		if (nums.length < 3) {
			return 0;
		}
		
		int s = 0;
		
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length - 2; i++) {
			int a = nums[i];
			
			for (int j = i + 1; j < nums.length - 1; j++) {
				int b = nums[j];
				int t = a + b;
				
				for (int k = j + 1; k < nums.length; k++) {
					int c = nums[k];
					
					if (t > c) {
						System.out.println(a + "," + b + "," + c);
						s++;
					} else {
						break;
					}
					
				}
			}
		}
		
		return s;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 3, 4 };
		System.out.println(triangleNumber(nums));
	}
	
}
