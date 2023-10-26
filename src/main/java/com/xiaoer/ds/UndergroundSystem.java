package com.xiaoer.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 1396
 * 
 * @author pyf
 * @time 2023-08-09 13:47:45
 */
public class UndergroundSystem {
	
	private Map<String, List<Integer>> note;
	private Map<Integer, Object[]> temp;
	
	public UndergroundSystem() {
		this.note = new HashMap<>();
		this.temp = new HashMap<>();
	}

	public void checkIn(int id, String stationName, int t) {
		Object[] r = new Object[2];
		r[0] = stationName;
		r[1] = t;
		temp.put(id, r);
	}

	public void checkOut(int id, String stationName, int t) {
		Object[] r = temp.get(id);
		String key = (String)r[0] + "-" + stationName;
		int time = t - (Integer)r[1];
		if (note.containsKey(key)) {
			List<Integer> list = note.get(key);
			list.add(time);
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(time);
			note.put(key, list);
		}
	}

	public double getAverageTime(String startStation, String endStation) {
		String key = startStation + "-" + endStation; 
		List<Integer> list = note.get(key);
		int sum = 0;
		for (Integer t : list) {
			sum += t;
		}
		return sum * 1.0 / list.size();
	}
}
