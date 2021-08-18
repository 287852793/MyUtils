package com.xiaoer.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _0887_鸡蛋掉落 {
//	给你 k 枚相同的鸡蛋，并可以使用一栋从第 1
//	层到第 n
//	层共有 n 层楼的建筑。
//
//	已知存在楼层 f，满足 0<=f<=n，
//	任何从 高于
//	f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
//
//	每次操作，
//	你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1<=x<=n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，
//	则可以在之后的操作中 重复使用 这枚鸡蛋。
//
//	请你计算并返回要确定 f
//	确切的值 的
//	最小操作次数 是多少？
//
//	 示例 1：
//
//	输入：k=1,n=2 输出：2 解释：鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0。否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1。如果它没碎，
//	那么肯定能得出 f = 2。因此，在最坏的情况下我们需要移动 2
//	次以确定 f 是多少。示例 2：
//
//	输入：k=2,n=6 输出：3 示例 3：
//
//	输入：k=3,n=14 输出：4
//
//	来源：力扣（LeetCode）链接：https:// leetcode-cn.com/problems/super-egg-drop
//	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

	static Map<String, Integer> note = new HashMap<String, Integer>();

	public static int superEggDrop(int k, int n) {
		if (k == 1) {
			return n;
		}
		if (n == 0) {
			return 0;
		}

		if (note.containsKey(k + "," + n)) {
			return note.get(k + "," + n);
		}

		int r = Integer.MAX_VALUE;
//		for (int i = 1; i <= n; i++) {
//			int t = Math.max(superEggDrop(k - 1, i - 1), superEggDrop(k, n - i)) + 1;
//			r = Math.min(r, t);
//		}

		int low = 1, high = n;
		while (low <= high) {
			int mid = (low + high) / 2;

			int a = superEggDrop(k - 1, mid - 1);
			int b = superEggDrop(k, n - mid);

			if (a > b) {
				high = mid - 1;
				r = Math.min(r, a + 1);
			} else {
				low = mid + 1;
				r = Math.min(r, b + 1);
			}
		}

		note.put(k + "," + n, r);
		return r;
	}

//	public static int superEggDrop(int k, int n) {
//		if (k > 1 && n == 4) {
//			return 3;
//		}
//		int r = 0, t = n;
//		while (k > 1) {
//			int f = t / 2;
//			if (t % 2 == 0 && f > 1) {
//				t = f - 1;
//			} else {
//				t = f;
//			}
//			r++;
//			k--;
//		}
//		r += t;
//		return r;
//	}

	public static void main(String[] args) {
		System.out.println(superEggDrop(1, 2));
		System.out.println(superEggDrop(2, 2));
		System.out.println(superEggDrop(2, 4));
		System.out.println(superEggDrop(1, 3));
		System.out.println(superEggDrop(2, 6));
		System.out.println(superEggDrop(2, 7));
		System.out.println(superEggDrop(2, 8));
		System.out.println(superEggDrop(3, 14));
	}
}
