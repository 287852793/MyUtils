package com.xiaoer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0797_所有可能的路径 {
	
	public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		
		ArrayList<Integer> base = new ArrayList<Integer>();
		base.add(0);
		
		f(res, base, graph);
		
		return res;
	}
	
	public static void f(List<List<Integer>> res, ArrayList<Integer> path, int[][] graph) {
		int node = path.get(path.size() - 1);
		for (int i = 0; i < graph[node].length; i++) {
			ArrayList<Integer> t = (ArrayList)path.clone();
			t.add(graph[node][i]);
			if (graph[node][i] == graph.length - 1) {
				res.add(t);
			} else {
				f(res, t, graph);
			}
		}
	}
}
