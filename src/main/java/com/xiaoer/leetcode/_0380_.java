package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class _0380_ {

	private HashSet<Integer> set;
	public _0380_() {
		this.set = new HashSet<>();
    }
    
    public boolean insert(int val) {
    	if (set.contains(val)) {
			return false;
		}
    	set.add(val);
    	return true;
    }
    
    public boolean remove(int val) {
    	if (set.contains(val)) {
			set.remove(val);
			return true;
		}
    	return false;
    }
    
    public int getRandom() {
    	ArrayList<Integer> list = new ArrayList(set);
        int randomIndex = new Random().nextInt(list.size());
        Integer randomItem = list.get(randomIndex);
    	return randomItem;
    }
    
    public static void main(String[] args) {
    	_0380_ randomizedSet = new _0380_();
    	System.out.println(randomizedSet.insert(1)); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
    	System.out.println(randomizedSet.remove(2)); // 返回 false ，表示集合中不存在 2 。
    	System.out.println(randomizedSet.insert(2)); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
    	System.out.println(randomizedSet.getRandom()); // getRandom 应随机返回 1 或 2 。
    	System.out.println(randomizedSet.remove(1)); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
    	System.out.println(randomizedSet.insert(2)); // 2 已在集合中，所以返回 false 。
    	System.out.println(randomizedSet.getRandom());

	}
}
