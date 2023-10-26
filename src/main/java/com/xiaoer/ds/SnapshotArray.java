package com.xiaoer.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * 快照数组
 * 
 * @author pyf
 * @time 2023-06-29 15:03:30
 */
public class SnapshotArray {

	private List<int[]>[] data;
	private int i;

	public SnapshotArray(int length) {
		this.data = new List[length];
		this.i = 0;
	}

	public void set(int index, int val) {
		if (this.data[index] != null) {
			int[] js = this.data[index].get(this.data[index].size() - 1);
			if (js[0] != i) {
				this.data[index].add(new int[] { i, val });
			} else {
				js[1] = val;
			}
		} else {
			this.data[index] = new ArrayList<int[]>();
			this.data[index].add(new int[] { i, val });
		}
	}

	public int snap() {
		return i++;
	}

	public int get(int index, int snap_id) {
		List<int[]> list = this.data[index];
		if (list == null) {
			return 0;
		}
		for (int i = list.size() -1 ; i >= 0; i--) {
			int[] js = list.get(i);
			if (js[0] <= index) {
				return js[1];
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		SnapshotArray s = new SnapshotArray(2);
		s.snap();
		
	}
}
