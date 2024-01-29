package com.xiaoer.game;

/**
 * leetcode 307
 * 
 * @author pyf
 * @time 2023-11-13 08:50:00
 */
public class NumArray {
	int[] nums;
	int[] note;

	public NumArray(int[] nums) {
		this.nums = nums;
		this.note = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			note[i + 1] = nums[i] + note[i];
		}
	}

	public void update(int index, int val) {
		int t = val - this.nums[index];
		for (int i = index + 1; i < note.length; i++) {
			note[i] += t;
		}
	}

	public int sumRange(int left, int right) {
		return note[right + 1] - note[left];
	}
}
