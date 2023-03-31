package com.xiaoer.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

@SuppressWarnings("restriction")
public class _0000_test {
	public static void main(String[] args) {
		_0000_test test = new _0000_test();

		long t = System.currentTimeMillis();

		int[] nums = new int[] { 4, 6, 5, 9, 3, 7 };
		int[] l = new int[] { 0, 0, 2 };
		int[] r = new int[] { 2, 3, 5 };
		System.out.println(test.checkArithmeticSubarrays(nums, l, r));

		System.out.println("cost time : " + (System.currentTimeMillis() - t));
	}
	
	// 2367
	public int arithmeticTriplets(int[] nums, int diff) {
		boolean[] note = new boolean[nums.length + diff + 1];
		int res = 0;
		int t = diff * 2;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= t && note[nums[i] - diff] && note[nums[i] - t]) {
				res++;
			}
			note[nums[i]] = true;
		}
		return res;
    }
	
	// 777
	public boolean canTransform(String start, String end) {
		start = start.replace("X", "");
		end = end.replace("X", "");
		return start.equals(end);
    }

	// offer II 24
	public ListNode reverseList_(ListNode head) {
		ListNode a = null;
		ListNode b = head;
		while (b != null) {
			ListNode t = b.next;
			b.next = a;
			a = b;
			b = t;
		}
		return a;
    }
	
	// 1630
	public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
		int len = nums.length;
		int[] diff = new int[len];
		for (int i = 1; i < len; i++) {
			diff[i] = nums[i] - nums[i - 1];
		}
		System.out.println(Arrays.toString(diff));
		int[] note = new int[len];
		int j = 0;
		int i = 2;
		for (; i < len; i++) {
			if (diff[i] != diff[i - 1]) {
				while (j < i - 1) {
					note[j] = i - 1;
					j++;
				}
			}
		}
		while (j < i - 1) {
			note[j] = i - 1;
			j++;
		}
//		System.out.println(Arrays.toString(note));

		List<Boolean> res = new ArrayList<>();
		for (int k = 0; k < l.length; k++) {
			if (note[l[k]] <= r[k]) {
				res.add(true);
			} else {
				res.add(false);
			}
		}
		return res;
	}

	// 1881
	public String maxValue(String n, int x) {
		char y = String.valueOf(x).charAt(0);
		StringBuilder sb = new StringBuilder();
		if (n.startsWith("-")) {
			int i = 1;
			while (i < n.length() && n.charAt(i) <= y) {
				i++;
			}
			sb.append(n.substring(0, i)).append(x).append(n.substring(i));
		} else {
			int i = 0;
			while (i < n.length() && n.charAt(i) >= y) {
				i++;
			}
			sb.append(n.substring(0, i)).append(x).append(n.substring(i));
		}
		return sb.toString();
	}

	// 2469
	public double[] convertTemperature(double celsius) {
		return new double[] { celsius + 275.15, celsius * 1.8 + 32 };
	}

	// 2389
	public int[] answerQueries(int[] nums, int[] queries) {
		Arrays.sort(nums);
		int[] sums = new int[nums.length];
		sums[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
		int[] res = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int j = Arrays.binarySearch(sums, queries[i]);
			res[i] = j >= 0 ? j + 1 : -1 - j;
		}
		return res;
	}

	// offer31
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for (int i = 0; i < popped.length; i++) {
			int temp = popped[i];
			while (j < popped.length && (stack.isEmpty() || stack.peek() != temp)) {
				stack.push(pushed[j]);
				j++;
			}
			Integer t = stack.pop();
			if (t != temp) {
				return false;
			}
		}
		return true;
	}

	// offer66
	public int[] constructArr(int[] a) {
		int len = a.length;
		int[] left = new int[len + 1];
		left[0] = 1;
		int[] right = new int[len + 1];
		right[len] = 1;
		for (int i = 0; i < len; i++) {
			left[i + 1] = left[i] * a[i];
			right[len - i - 1] = right[len - i] * a[len - i - 1];
		}
		System.out.println(Arrays.toString(left));
		System.out.println(Arrays.toString(right));
		int[] res = new int[len];
		for (int i = 0; i < len; i++) {
			res[i] = left[i] * right[i + 1];
		}
		return res;
	}

	// 79
	public boolean exist(char[][] board, String word) {
		char[] arr = word.toCharArray();
		int m = board.length, n = board[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[0] == board[i][j]) {
					boolean[][] note = new boolean[m][n];
					note[i][j] = true;
					boolean f = f79(board, arr, note, i, j, 1);
					if (f) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean f79(char[][] board, char[] arr, boolean[][] note, int i, int j, int k) {
		if (k >= arr.length) {
			return true;
		}
		if (i - 1 >= 0 && !note[i - 1][j] && board[i - 1][j] == arr[k]) {
			note[i - 1][j] = true;
			boolean f = f79(board, arr, note, i - 1, j, k + 1);
			if (f) {
				return true;
			}
			note[i - 1][j] = false;
		}
		if (j - 1 >= 0 && !note[i][j - 1] && board[i][j - 1] == arr[k]) {
			note[i][j - 1] = true;
			boolean f = f79(board, arr, note, i, j - 1, k + 1);
			if (f) {
				return true;
			}
			note[i][j - 1] = false;
		}
		if (i + 1 < board.length && !note[i + 1][j] && board[i + 1][j] == arr[k]) {
			note[i + 1][j] = true;
			boolean f = f79(board, arr, note, i + 1, j, k + 1);
			if (f) {
				return true;
			}
			note[i + 1][j] = false;
		}
		if (j + 1 < board[0].length && !note[i][j + 1] && board[i][j + 1] == arr[k]) {
			note[i][j + 1] = true;
			boolean f = f79(board, arr, note, i, j + 1, k + 1);
			if (f) {
				return true;
			}
			note[i][j + 1] = false;
		}
		return false;
	}

	// 1615
	public int maximalNetworkRank(int n, int[][] roads) {

		return 0;
	}

	// 1605
	public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int m = rowSum.length, n = colSum.length;
		int[][] mat = new int[m][n];
		for (int i = 0, j = 0; i < m && j < n;) {
			int rs = rowSum[i], cs = colSum[j];
			if (rs < cs) { // 去掉第 i 行，往下走
				colSum[j] -= rs;
				mat[i++][j] = rs;
			} else { // 去掉第 j 列，往右走
				rowSum[i] -= cs;
				mat[i][j++] = cs;
			}
		}
		return mat;
	}

	// 2383
	public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
		int a = 0, b = 0;
		for (int i = 0; i < experience.length; i++) {
			a += energy[i];
			if (experience[i] >= initialExperience) {
				b = b + experience[i] - initialExperience + 1;
				initialExperience = experience[i] + 1 + experience[i];
			} else {
				initialExperience += experience[i];
			}
			System.out.println(initialExperience);
		}
//		System.out.println(a);
//		System.out.println(b);
		int res = 0;
		if (a >= initialEnergy) {
			res = a - initialEnergy + 1;
		}
		res += b;
		return res;
	}

	// 1590
	public int minSubarray(int[] nums, int p) {
		int len = nums.length;
		int[] note = new int[len + 1];
		for (int i = 0; i < len; i++) {
			note[i + 1] = (note[i] + nums[i]) % p;
		}
		int s = note[len];
		if (s % p == 0) {
			return 0;
		}
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < len; j++) {

			}
		}
		return -1;
	}

	// offer63
	public int maxProfit(int[] prices) {
		if (prices.length < 2) {
			return 0;
		}
		int len = prices.length;
		int[] note = new int[len];
		note[len - 1] = prices[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			note[i] = Math.max(note[i + 1], prices[i]);
		}
		int res = 0;
		for (int i = 0; i < len - 1; i++) {
			res = Math.max(res, note[i + 1] - prices[i]);
		}
		return res;
	}

	// offer32_3
	public List<List<Integer>> levelOrder_3(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		List<List<Integer>> list = new ArrayList<>();
		List<TreeNode> nodes = new ArrayList<>();
		nodes.add(root);
		offer32_3(list, nodes, true);
		return list;
	}

	private void offer32_3(List<List<Integer>> list, List<TreeNode> nodes, boolean l2r) {
		if (nodes.size() < 1) {
			return;
		}
		List<Integer> t = new ArrayList<>();
		List<TreeNode> next = new ArrayList<>();
		if (l2r) {
			for (int i = 0; i < nodes.size(); i++) {
				TreeNode n = nodes.get(i);
				t.add(n.val);
				if (n.left != null) {
					next.add(n.left);
				}
				if (n.right != null) {
					next.add(n.right);
				}
			}
		} else {
			for (int i = nodes.size() - 1; i >= 0; i--) {
				TreeNode n = nodes.get(i);
				t.add(n.val);
				if (n.right != null) {
					next.add(0, n.right);
				}
				if (n.left != null) {
					next.add(0, n.left);
				}
			}
		}
		list.add(t);
		l2r = !l2r;
		offer32_3(list, next, l2r);
	}

	// offer32
	public int[] levelOrder_(TreeNode root) {
		if (root == null) {
			return new int[0];
		}
		List<Integer> list = new ArrayList<>();
		List<TreeNode> nodes = new ArrayList<>();
		nodes.add(root);
		offer32(list, nodes);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	public void offer32(List<Integer> list, List<TreeNode> nodes) {
		if (nodes.size() < 1) {
			return;
		}
		List<TreeNode> next = new ArrayList<>();
		for (TreeNode n : nodes) {
			list.add(n.val);
			if (n.left != null) {
				next.add(n.left);
			}
			if (n.right != null) {
				next.add(n.right);
			}
		}
		offer32(list, next);
	}

	// 1653
	public int minimumDeletions(String s) {
		if (s.length() <= 1) {
			return 0;
		}
		char[] arr = s.toCharArray();
		int len = arr.length;
		int[] a = new int[len + 1];
		int[] b = new int[len + 1];
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			if (arr[i] == 'b') {
				b[i + 1] = b[i] + 1;
			} else {
				b[i + 1] = b[i];
			}
			if (arr[len - 1 - i] == 'a') {
				a[len - 1 - i] = a[len - i] + 1;
			} else {
				a[len - 1 - i] = a[len - i];
			}
		}
		for (int i = 0; i < len; i++) {
			res = Math.min(res, b[i + 1] + a[i + 1]);
			res = Math.min(res, b[i] + a[i]);
		}
		return res;
	}

	// 2281
	public int totalStrength(int[] strength) {
		int[] sum = new int[strength.length + 1];
		for (int i = 0; i < strength.length; i++) {
			sum[i + 1] = sum[i] + strength[i];
		}
		long res = 0;
		int m = (int) (1e9 + 7);
		for (int i = 0; i < strength.length; i++) {
			int t = strength[i];
			res += 1l * strength[i] * strength[i];
			res = res % m;
			System.out.println(res);
			for (int j = i - 1; j >= 0; j--) {
				int s = sum[i + 1] - sum[j];
				t = Math.min(t, strength[j]);
				res += 1l * s * t;
				res = res % m;
				System.out.println(res);
			}
		}
		return (int) res;
	}

	// 2280
	public int minimumLines(int[][] stockPrices) {
		if (stockPrices.length == 1) {
			return 0;
		}
		if (stockPrices.length == 2) {
			return 1;
		}
		Arrays.sort(stockPrices, (a, b) -> {
			return a[0] - b[0];
		});
		int res = 1;
		int x1 = stockPrices[0][0], y1 = stockPrices[0][1];
		int x2 = stockPrices[1][0], y2 = stockPrices[1][1];
		int x = x2 - x1, y = y2 - y1;
		for (int i = 2; i < stockPrices.length; i++) {
			int a = stockPrices[i][0] - stockPrices[i - 1][0];
			int b = stockPrices[i][1] - stockPrices[i - 1][1];
			if (x * b != a * y) {
				res++;
			}
			x = a;
			y = b;
		}
		return res;
	}

	// 1487
	public String[] getFolderNames(String[] names) {
		String[] res = new String[names.length];
		Map<String, Integer> note = new HashMap<>();
		for (int i = 0; i < names.length; i++) {
			String s = names[i];
			if (note.containsKey(s)) {
				int k = note.get(s);
				k++;
				while (note.containsKey(s + "(" + k + ")")) {
					k++;
				}
				String t = s + "(" + k + ")";
				res[i] = t;
				note.put(s, k);
				note.put(t, 0);

			} else {
				res[i] = s;
				note.put(s, 0);
			}
		}
		return res;
	}

	// 813
	public double largestSumOfAverages(int[] nums, int k) {
		Arrays.sort(nums);
		double res = 0;
		int s = 0, t = 0, n = nums.length - k + 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (k > 1) {
				s += nums[i];
				k--;
			} else {
				t += nums[i];
			}
		}
		res = s + t * 1.0 / n;
		return res;
	}

	// 2357
	public int minimumOperations(int[] nums) {
		int[] note = new int[101];
		int res = 0;
		for (int i : nums) {
			if (note[i] != 0 && i != 0) {
				res++;
				note[i]++;
			}
		}
		return res;
//		Arrays.sort(nums);
//		int res = 0, t = 0;
//		for (int i : nums) {
//			if (i != t) {
//				res++;
//				t = i;
//			}
//		}
//		return res;
	}

	// 10
	public boolean isMatch(String s, String p) {
		char[] cs = s.toCharArray();
		char[] cp = p.toCharArray();

		// note
		boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

		// 边界条件，s与p都为空时是可以匹配的
		dp[0][0] = true;

		// 当s为空时，p的所有字符如果都带量词*，也是可以匹配的
		for (int i = 1; i <= cp.length; i++) {
			if (cp[i - 1] == '*') {
				dp[0][i] = dp[0][i - 2];
			}
		}

		// dp
		for (int i = 1; i <= cs.length; i++) {
			for (int j = 1; j <= cp.length; j++) {
				if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (cp[j - 1] == '*') {
					if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
						dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
					} else {
						dp[i][j] = dp[i][j - 2];
					}
				}
			}
		}
		return dp[cs.length][cp.length];
	}

	// 1140
	public int stoneGameII(int[] piles) {
		int len = piles.length, sum = 0;
		int[][] dp = new int[len][len + 1];
		for (int i = len - 1; i >= 0; i--) {
			sum += piles[i];
			for (int M = 1; M <= len; M++) {
				if (i + 2 * M >= len) {
					dp[i][M] = sum;
				} else {
					for (int j = 1; j <= 2 * M; j++) {
						dp[i][M] = Math.max(dp[i][M], sum - dp[i + j][Math.max(M, j)]);
					}
				}
			}
		}
		return dp[0][1];
	}

	// 1276
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		List<Integer> res = new ArrayList<>();
		if (tomatoSlices % 2 == 1) {
			return res;
		}
		int l = cheeseSlices * 2, r = cheeseSlices * 4;
		if (tomatoSlices < l || tomatoSlices > r) {
			return res;
		}
		int m = (tomatoSlices - l) / 2;
		int n = cheeseSlices - m;
		res.add(m);
		res.add(n);
		return res;
	}

	// 2341
	public int[] numberOfPairs(int[] nums) {
		int[] note = new int[101];
		int r = 0;
		for (int i : nums) {
			if (note[i] == 1) {
				note[i] = 0;
				r++;
			} else {
				note[i] = 1;
			}
		}
		return new int[] { r, nums.length - r * 2 };
	}

	// 2140
	public long mostPoints(int[][] questions) {
		long[] dp = new long[questions.length + 1];
		for (int i = questions.length - 1; i >= 0; i--) {

			dp[i] = Math.max(dp[i + 1], questions[i][0] + dp[Math.min(questions.length, i + questions[i][1] + 1)]);
		}
		System.out.println(Arrays.toString(dp));
		return dp[0];
//		long[] note = new long[questions.length];
//		note[0] = questions[0][0];
//		for (int i = 1; i < questions.length; i++) {
//			note[i] = questions[i][0];
//			for (int j = 0; j < i; j++) {
//				if (i > questions[j][1] + j) {
//					note[i] = Math.max(note[i], note[j] + questions[i][0]);
//				}
//			}
//			
//		}
//		return Arrays.stream(note).max().getAsLong();
	}

	// 1124
	public int longestWPI(int[] hours) {
		int l = 0, r = 0;
		int c = 0, t = 0, m = 0;

		while (r < hours.length) {
			if (hours[r] > 8) {
				c++;
				if (t >= c) {
					l++;
				}
			} else {
				t++;
				if (t >= c) {
					if (hours[l] > 8) {
						c--;
					} else {
						t--;
					}
					l++;
				}
			}
			r++;
		}

		return r - l;
	}

	// 1604
	public List<String> alertNames(String[] keyName, String[] keyTime) {
		Map<String, int[]> note = new HashMap<>();
		List<String> res = new ArrayList<>();
		for (int i = 0; i < keyName.length; i++) {
			String name = keyName[i];
			if (res.contains(name)) {
				continue;
			}
			Integer time = Integer.valueOf(keyTime[i].substring(0, 2));
			if (note.containsKey(name)) {
				int[] n = note.get(name);
				n[time]++;
				if (n[time] == 3) {
					res.add(name);
				}
			} else {
				int[] n = new int[24];
				n[time]++;
				note.put(name, n);
			}
		}
		Collections.sort(res);
		return res;
	}

	// 2231
	public boolean evaluateTree(TreeNode root) {
		if (root == null) {
			return false;
		}
		if (root.left != null) {
			boolean left = evaluateTree(root.left);
			boolean right = evaluateTree(root.right);
			if (root.val == 2) {
				return left | right;
			} else {
				return left & right;
			}
		} else {
			return root.val == 1 ? true : false;
		}
	}

	// 1145
	private int left1145 = 0;
	private int right1145 = 0;

	public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
		left1145 = 0;
		right1145 = 0;
		f1145(root, x, false, false);
		int s = n - left1145 - right1145 - 1;
		System.out.println(left1145);
		System.out.println(right1145);
		System.out.println(s);
		if (s > left1145 + right1145 || left1145 > s + right1145 || right1145 > s + left1145) {
			return true;
		}
		return false;
	}

	private void f1145(TreeNode n, int x, boolean f, boolean ff) {
		if (n == null) {
			return;
		}
		if (n.val == x) {
			f = true;
			f1145(n.left, x, true, true);
			f1145(n.right, x, true, false);
		} else if (f) {
			if (ff) {
				left1145++;
				f1145(n.left, x, true, true);
				f1145(n.right, x, true, true);
			} else {
				right1145++;
				f1145(n.left, x, true, false);
				f1145(n.right, x, true, true);
			}
		} else {
			f1145(n.left, x, false, false);
			f1145(n.right, x, false, false);
		}
	}

	// 209
	public int minSubArrayLen2(int target, int[] nums) {
		int[] sums = new int[nums.length + 1];
		sums[0] = nums[0];
		for (int i = 1; i < sums.length; i++) {
			sums[i] = sums[i - 1] + nums[i - 1];
		}
		int res = 100001;
		for (int i = 1; i < sums.length; i++) {
			int left = 0, right = i;
			while (left < right) {
				int mid = (right + left) / 2;
				int t = sums[i] - sums[mid];
				if (t < target) {
					right = mid;
				} else {
					left = mid + 1;
					res = Math.min(res, i - mid);
				}
			}
		}
		return res > 100000 ? 0 : res;
	}

	// 1727
	public int largestSubmatrix(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int res = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					matrix[i][j] += matrix[i - 1][j];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			Arrays.sort(matrix[i]);
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[i][j] == 0) {
					break;
				}
				int height = matrix[i][j];
				res = Math.max(res, height * (m - j));
			}
		}
		return res;
	}

	// mianshi 17.21
	public int trap2(int[] height) {
		if (height.length < 3) {
			return 0;
		}
		int len = height.length;
		int[] a = new int[len], b = new int[len];
		a[0] = height[0];
		b[len - 1] = height[len - 1];
		for (int i = 1; i < len; i++) {
			a[i] = Math.max(a[i - 1], height[i]);
			b[len - i - 1] = Math.max(b[len - i], height[len - i - 1]);
		}
		int r = 0;
		for (int i = 1; i < len - 1; i++) {
			int t = Math.min(a[i - 1], b[i + 1]) - height[i];
			r += t > 0 ? t : 0;
		}
		return r;
	}

	// 1233
	public List<String> removeSubfolders(String[] folder) {
		Arrays.sort(folder);
		List<String> r = new ArrayList<>();
		String t = "//";
		for (String s : folder) {
			if (!s.startsWith(t)) {
				r.add(s);
				t = s + "/";
			}
		}
		return r;
	}

	// 1813
	public boolean areSentencesSimilar(String sentence1, String sentence2) {
		String[] arr1 = sentence1.split(" ");
		String[] arr2 = sentence2.split(" ");
		if (arr1.length < arr2.length) {
			String[] tmp = arr1;
			arr1 = arr2;
			arr2 = tmp;
		}
		int i = 0;
		while (i < arr2.length && arr1[i].equals(arr2[i])) {
			i++;
		}
		if (i == arr2.length) {
			return true;
		}
		int j = 0;
		while (j + i < arr2.length && arr1[arr1.length - j - 1].equals(arr2[arr2.length - j - 1])) {
			j++;
		}
		if (i + j == arr2.length) {
			return true;
		}

		return false;
	}

	// 1807
	public String evaluate(String s, List<List<String>> knowledge) {
		Map<String, String> note = new HashMap<>();
		for (int i = 0; i < knowledge.size(); i++) {
			note.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
		}
		int i = s.indexOf("("), j = 0;
		if (i < 0) {
			return s;
		}
		StringBuffer sb = new StringBuffer();
		while (i > -1) {
			if (i > 0)
				sb.append(s.substring(j, i));
			j = s.indexOf(")", i);
			sb.append(note.getOrDefault(s.substring(i + 1, j), "?"));
			i = s.indexOf("(", j);
			j++;
		}
		sb.append(s.substring(j, s.length()));
		return sb.toString();
	}

	// 621
	public int leastInterval(char[] tasks, int n) {
		int len = tasks.length;
		int[] note = new int[26];

		for (int i = 0; i < tasks.length; i++) {
			note[tasks[i] - 'A']++;
		}

		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((i, j) -> {
			return j[1] - i[1];
		});
		for (int i = 0; i < note.length; i++) {
			if (note[i] > 0) {
				queue.offer(new int[] { i, note[i] });
			}
		}
		Queue<int[]> list = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			list.offer(null);
		}

		int r = 0;
		int tmp = 0;

		while (tmp < len) {
			if (!queue.isEmpty()) {
				int[] data = queue.poll();
				data[1]--;
				r++;
				tmp++;
				System.out.println("data : " + data[0]);
				if (data[1] > 0) {
					list.offer(data);
				} else {
					list.offer(null);
				}
			} else {
				System.out.println("data : wait");
				list.offer(null);
				r++;
			}

			int[] p = list.poll();
			if (p != null) {
				queue.offer(p);
			}
			System.out.println(list);

		}

		return r;
	}

	// 1803
	public int countPairs(int[] nums, int low, int high) {
		int r = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int t = nums[i] ^ nums[j];
				if (t >= low && t <= high) {
					r++;
				}
			}
		}
		return r;
	}

	// 1519
	public int[] countSubTrees(int n, int[][] edges, String labels) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.length; i++) {
			graph.get(edges[i][0]).add(edges[i][1]);
		}

		return null;
	}

	// 1750
	public int minimumLength(String s) {
		char[] arr = s.toCharArray();
		int i = 0, j = arr.length - 1;
		while (i < j && arr[i] == arr[j]) {
			char t = arr[i];
			while (i <= j && arr[i] == t) {
				i++;
			}
			while (i <= j && arr[j] == t) {
				j--;
			}
		}

		return j - i + 1;

	}

	// 210 课程表2
	public int[] findOrder2(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		int[] note = new int[numCourses];
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (note[i] == 0) {
				boolean valid = dfs210(i, graph, note, res);
				if (!valid) {
					return new int[0];
				}
			}
		}
		int[] r = new int[numCourses];
		for (int i = 0; i < res.size(); i++) {
			r[i] = res.get(i);
		}
		return r;
	}

	private boolean dfs210(int i, List<List<Integer>> graph, int[] note, List<Integer> res) {
		note[i] = 1;
		List<Integer> list = graph.get(i);
		for (int j = 0; j < list.size(); j++) {
			if (note[list.get(j)] == 0) {
				boolean valid = dfs210(list.get(j), graph, note, res);
				if (!valid) {
					return false;
				}
			} else if (note[list.get(j)] == 1) {
				return false;
			}
		}
		note[i] = 2;
		res.add(0, i);
		return true;
	}

	// 210 课程表2 宽搜
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		int[] note = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
			note[prerequisites[i][0]]++;
		}
		int[] res = new int[numCourses];
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < note.length; i++) {
			if (note[i] == 0) {
				queue.offer(i);
			}
		}
		int visited = 0;
		while (!queue.isEmpty()) {
			Integer i = queue.poll();
			res[visited] = i;
			visited++;
			List<Integer> list = graph.get(i);
			for (int j = 0; j < list.size(); j++) {
				note[list.get(j)]--;
				if (note[list.get(j)] == 0) {
					queue.offer(list.get(j));
				}
			}
		}
		if (visited != numCourses) {
			return null;
		} else {
			return res;
		}
	}

	// 207 课程表 深搜
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		int[] note = new int[numCourses];

		for (int i = 0; i < numCourses; i++) {
			if (note[i] == 0) {
				boolean valid = dfs207(i, graph, note);
				if (!valid) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean dfs207(int i, List<List<Integer>> graph, int[] note) {
		note[i] = 1;
		List<Integer> list = graph.get(i);
		for (int j = 0; j < list.size(); j++) {
			if (note[list.get(j)] == 0) {
				boolean valid = dfs207(list.get(j), graph, note);
				if (!valid) {
					return false;
				}
			} else if (note[list.get(j)] == 1) {
				return false;
			}
		}
		note[i] = 2;
		return true;
	}

	// 207 课程表 宽搜
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		int[] note = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
			note[prerequisites[i][0]]++;
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < note.length; i++) {
			if (note[i] == 0) {
				queue.offer(i);
			}
		}

		int visited = 0;
		while (!queue.isEmpty()) {
			visited++;
			Integer i = queue.poll();
			List<Integer> list = graph.get(i);
			for (int j = 0; j < list.size(); j++) {
				Integer t = list.get(j);
				note[t]--;
				if (note[t] == 0) {
					queue.offer(t);
				}
			}
		}

		return visited == numCourses;
	}

	// 1812
	public boolean squareIsWhite(String coordinates) {
		return ((coordinates.charAt(0) - 'a') % 2 + (coordinates.charAt(1) - '1') % 2) % 2 == 1;
	}

	// 141
	public boolean hasCycle2(ListNode head) {
		ListNode slow = head, fast = head;
		while (true) {
			if (fast == null || fast.next == null) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow) {
				return true;
			}
		}
	}

	// 142
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head, slow = head;
		while (true) {
			if (fast == null || fast.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				break;
			}
		}
		if (fast == null) {
			return null;
		}
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

	// 1775
	public int minOperations(int[] nums1, int[] nums2) {

		int s1 = Arrays.stream(nums1).sum();
		int s2 = Arrays.stream(nums2).sum();

		if (s2 < nums1.length || s1 < nums2.length) {
			return -1;
		}
		if (s1 < s2) {
			int[] t = nums1;
			nums1 = nums2;
			nums2 = t;
		}
		int d = Math.abs(s1 - s2);
		int r = 0;
		Arrays.sort(nums1);
		Arrays.sort(nums2);

		int i = nums1.length - 1, j = 0;
		while (j < nums1.length || i >= 0) {
			if (i >= 0 && nums1[i] == 6) {
				d -= 5;
				i--;
			} else if (j < nums1.length && nums2[j] == 1) {
				d -= 5;
				j++;
			} else if (i >= 0 && nums1[i] == 5) {
				d -= 4;
				i--;
			} else if (j < nums1.length && nums2[j] == 2) {
				d -= 4;
				j++;
			} else if (i >= 0 && nums1[i] == 4) {
				d -= 3;
				i--;
			} else if (j < nums1.length && nums2[j] == 3) {
				d -= 3;
				j++;
			} else if (i >= 0 && nums1[i] == 3) {
				d -= 2;
				i--;
			} else if (j < nums1.length && nums2[j] == 4) {
				d -= 2;
				j++;
			} else if (i >= 0 && nums1[i] == 2) {
				d -= 1;
				i--;
			} else if (j < nums1.length && nums2[j] == 5) {
				d -= 1;
				j++;
			} else {
				return -1;
			}

			r++;

			if (d <= 0) {
				break;
			}
		}
		return r;
	}

	// 1805
	public int numDifferentIntegers(String word) {
		int i = 0, j = 0;
		Set<Integer> note = new HashSet<Integer>();
		char[] arr = word.toCharArray();
		for (int k = 0; k < arr.length; k++) {
			if (arr[k] <= '9' && arr[k] >= '0') {
				j++;
			} else {
				if (i != j) {
					note.add(Integer.valueOf(word.substring(i, j)));
				}
				i = k;
				j = k;
			}
		}
		if (i != j) {
			note.add(Integer.valueOf(word.substring(i, j)));
		}
		return note.size();
	}

	// 1779
	public int nearestValidPoint(int x, int y, int[][] points) {
		int r = -1, d = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			int[] p = points[i];
			if (p[0] == x || p[1] == y) {
				int t = Math.abs(p[0] - x) + Math.abs(p[1] - y);
				if (t < d) {
					r = i;
					d = t;
				}
			}
		}
		return r;
	}

	// 1785
	public int minOperations(String s) {
		int m = 0, n = 0;
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 1) {
				if (arr[i] == '0') {
					m++;
				} else {
					n++;
				}
			} else {
				if (arr[i] == '1') {
					m++;
				} else {
					n++;
				}
			}
		}
		return Math.min(m, n);
	}

	// 795
	public int numSubarrayBoundedMax(int[] nums, int left, int right) {
		int r = 0, t = 0, s = 0, m = 0;
		for (int i = 0; i < nums.length; i++) {
			m = Math.max(m, nums[i]);
			if (m <= right && m >= left) {
				t++;
				s += t;
			} else {
				t = 0;
			}
			System.out.println(t + "," + s);

			if (t == 0 || i == nums.length - 1) {
				r += s;
				s = 0;
				m = 0;
			}
		}
		return r;
	}

	public int numMatchingSubseq(String s, String[] words) {
		int[][] note = new int[26][5];
		int[] index = new int[26];
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			int j = arr[i] - 'a';
			note[j][index[j]] = i;
			index[j]++;
		}

//		for (int i = 0; i < note.length; i++) {
//			System.out.println(Arrays.toString(note[i]));
//		}
		int r = words.length;
		for (String w : words) {
			if (words.length > s.length()) {
				continue;
			}
			char[] ws = w.toCharArray();
			int[] idx = new int[26];
			int t = 0;
			for (char c : ws) {
				int j = c - 'a';
				if (note[j][idx[j]] < t) {
					System.out.println("123");
					r--;
					break;
				} else {
					while (note[j][idx[j]] > 0 && note[j][idx[j]] < t) {
						idx[j]++;
					}
					if (note[j][idx[j]] > t) {
						t = note[j][idx[j]];
					} else {
						System.out.println("456");
						r--;
						break;
					}
				}
			}
			System.out.println(w);
		}

		return r;
	}

	public static Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException, SecurityException {
		Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
		// Field unsafeField = Unsafe.class.getDeclaredFields()[0]; //也可以这样，作用相同
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		return unsafe;
	}

	// code sharing
	public static <A extends Comparable<A>> A max(Collection<A> xs) {
		Iterator<A> xi = xs.iterator();
		A w = xi.next();
		while (xi.hasNext()) {
			A x = xi.next();
			if (w.compareTo(x) < 0)
				w = x;
		}
		return w;
	}

	// 775
	public boolean isIdealPermutation(int[] nums) {
		int m = 0, n = 0, t = -1;
		for (int i = 0; i < nums.length; i++) {
		}
		return m == n;
	}

	// 1822
	public int arraySign(int[] nums) {
		int r = 1;
		for (int i : nums) {
			if (i < 0) {
				r *= -1;
			} else if (i == 0) {
				return 0;
			}
		}
		return r;
	}

	// 915
	public int partitionDisjoint(int[] nums) {
		int[] l = new int[nums.length];
		int[] r = new int[nums.length];
		l[0] = nums[0];
		r[nums.length - 1] = nums[nums.length - 1];

		for (int i = 1; i < r.length; i++) {
			l[i] = Math.max(l[i - 1], nums[i]);
			r[nums.length - 1 - i] = Math.min(r[nums.length - i], nums[nums.length - 1 - i]);
		}
		System.out.println(Arrays.toString(l));
		System.out.println(Arrays.toString(r));
		for (int i = 0; i < r.length; i++) {
			if (l[i] <= r[i + 1]) {
				return i + 1;
			}
		}
		return 0;
	}

	public int kthGrammar(int n, int k) {
		if (n == 1) {
			return 0;
		}
		int t = (int) Math.pow(2, n - 1);
		if (k <= t / 2) {
			return kthGrammar(n - 1, k);
		} else {
			return kthGrammar(n - 1, k - t / 2) ^ 1;
		}
	}

	// 1953
	public long numberOfWeeks(int[] milestones) {
		int max = 0;
		long sum = 0;
		for (int i = 0; i < milestones.length; i++) {
			sum += milestones[i];
			max = Math.max(max, milestones[i]);
		}
		if (max > sum - max) {
			return (sum - max) * 2 + 1;
		} else {
			return sum;
		}
	}

	// 1477
	public int minSumOfLengths(int[] arr, int target) {
		int[] sum = new int[arr.length + 1];

		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + arr[i - 1];
		}

		int r = 0;
		int f = 0;
		for (int i = 1; i <= sum.length - 1; i++) {
			for (int j = 0; j < sum.length - i; j++) {
				if (sum[j + i] - sum[j] == target) {
					r += i;
					f++;
					if (f == 2) {
						return r;
					}
					j = j + i;
				}
			}
		}
		return -1;
	}

	// 1328
	public String breakPalindrome(String palindrome) {
		if (palindrome.length() == 1) {
			return "";
		}
		char[] arr = palindrome.toCharArray();
		boolean f = true;
		for (int i = 0; i < arr.length / 2; i++) {
			if (arr[i] != 'a') {
				arr[i]--;
				f = false;
				break;
			}
		}
		if (f) {
			arr[arr.length - 1] = 'b';
		}
		return new String(arr);

	}

	// 2365
	public long taskSchedulerII(int[] tasks, int space) {
		long r = 0;
		Map<Integer, Long> note = new HashMap<>();
		for (int i = 0; i < tasks.length; i++) {
			if (note.containsKey(tasks[i])) {
				if (r - note.get(tasks[i]) < space) {
					r += space - r + note.get(tasks[i]) + 1;
				}
				note.put(tasks[i], r);
			} else {
				note.put(tasks[i], r);
			}
			r++;
		}
		return r;

//		long r = 0;
//		int i = 0;
//		Deque<Integer> note = new LinkedList<>();
//		while (r <= space && i < tasks.length) {
//			if (note.contains(tasks[i])) {
//				note.offer(0);
//				i--;
//			} else {
//				note.offer(tasks[i]);
//			}
//			r++;
//			i++;
//		}
//		while (i < tasks.length) {
//			note.pollFirst();
//			if (note.contains(tasks[i])) {
//				note.offer(0);
//				i--;
//			} else {
//				note.offer(tasks[i]);
//			}
//			r++;
//			i++;
//		}
//		return r;
	}

	// 856
	public int scoreOfParentheses(String s) {
		Stack<Integer> note = new Stack<>();
		char[] arr = s.toCharArray();
		for (char c : arr) {
			if (c == '(') {
				note.push(0);
			} else {
				Integer t = note.pop();
				if (t == 0) {
					if (!note.isEmpty() && note.peek() != 0) {
						note.push(note.pop() + 1);
					} else {
						note.push(1);
					}
				} else {
					note.pop();
					if (!note.isEmpty() && note.peek() != 0) {
						note.push(note.pop() + t * 2);
					} else {
						note.push(t * 2);
					}
				}
			}
		}
		System.out.println(note);
		return note.peek();
	}

	public int f856(String s) {
		return 0;
	}

	// 2765
	public List<List<Integer>> pairSums(int[] nums, int target) {
		Map<Integer, Integer> note = new HashMap<>();
		List<List<Integer>> r = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int t = target - nums[i];
			if (note.containsKey(t) && note.get(t) > 0) {
				List<Integer> list = new ArrayList<>();
				list.add(t);
				list.add(nums[i]);
				note.put(t, note.get(t) - 1);
				r.add(list);
			} else {
				note.put(nums[i], note.getOrDefault(nums[i], 0) + 1);
			}
		}
		return r;
	}

	// 对角线排序 ii
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
//		int len = 0;
//		int c = 0;
//		for (int i = 0; i < nums.size(); i++) {
//			int t = nums.get(i).size();
//			c = Math.max(c, t);
//			len += t;
//		}
//		int[] r = new int[len];
//		int t = 0;
//		for (int i = 0; i < nums.size(); i++) {
//			int a = i, b = 0;
//			while (a >= 0) {
//				if (a < nums.size() && b < nums.get(a).size()) {
//					r[t] = nums.get(a).get(b);
//					t++;
//				}
//				
//				a--;
//				b++;
//			}
//		}
//		int c = nums.size() - 1;
//		for (int i = 1; i < nums.get(c).size(); i++) {
//			int a = c, b = i;
//			while (a >= 0) {
//				if (a < nums.size() && b < nums.get(a).size()) {
//					r[t] = nums.get(a).get(b);
//					t++;
//				}
//				a--;
//				b++;
//			}
//		}
//
//		return r;
		return null;
	}

	// 2710
	public boolean isValidBST_(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left != null && root.left.val > root.val) {
			return false;
		}
		if (root.right != null && root.right.val < root.val) {
			return false;
		}
		return isValidBST(root.left) && isValidBST(root.right);
	}

	// 2009
	public int minOperations(int[] nums) {
		Arrays.sort(nums);
		int len = nums.length;
		Deque<Integer> stack = new LinkedList<>();
		stack.offer(nums[0]);
		int m = 1;
		int i = 1;
		while (i < len) {
			while (!stack.isEmpty() && nums[i] - stack.peek() >= len) {
				stack.pop();
			}
			if (!stack.isEmpty() && nums[i] != stack.peekLast()) {
				stack.offer(nums[i]);
			}
			m = Math.max(stack.size(), m);
			i++;
		}
		return len - m;
	}

	// 2690
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[] a = new int[m];
		int[] b = new int[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					a[i] = 1;
					b[j] = 1;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			if (a[i] == 1) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (b[i] == 1) {
				for (int j = 0; j < m; j++) {
					matrix[j][i] = 0;
				}
			}
		}
	}

	// 2501
	public int cuttingRope(int n) {
		if (n == 1 || n == 2)
			return 1;
		if (n == 3)
			return 2;
		int sum = 1;
		while (n > 4) {
			sum *= 3;
			n -= 3;
		}

		return sum * n;
	}

	// 1800
	public int maxAscendingSum(int[] nums) {
		// 多此一举，不需要dp，只需要一个变量记录当前的累计即可
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int r = dp[0];
		for (int i = 0; i < dp.length; i++) {
			if (nums[i] > nums[i - 1]) {
				dp[i] = nums[i] + dp[i - 1];
				r = Math.max(dp[i], r);
			} else {
				dp[i] = nums[i];
			}
		}
		return r;
	}

	// 646
	public int findLongestChain(int[][] pairs) {
		int len = pairs.length;
		int[] note1 = new int[len];
		int[] note2 = new int[len];
		note2[0] = pairs[0][1];

		for (int i = 1; i < len; i++) {
			int m = 1;
			note1[i] = pairs[i][1];
			for (int j = 0; j < i; j++) {
				if (pairs[i][0] > note1[j]) {
					m = Math.max(m, note2[j]);
				}
			}
			note2[i] = m;
		}
		return note2[len - 1];
	}

	// 738
	public int monotoneIncreasingDigits(int n) {
		String num = String.valueOf(n);
		char[] arr = num.toCharArray();
		int len = arr.length;
		if (len < 2) {
			return n;
		}
		for (int i = len - 2; i >= 0; i--) {
			if (arr[i] - '0' > arr[i + 1] - '0') {
				arr[i] = (char) (arr[i] - '1' + '0');
				for (int j = i + 1; j < len; j++) {
					arr[j] = '9';
				}
			}
		}
		return Integer.parseInt(new String(arr));
	}

	// 2523
	public List<List<Integer>> pathSum(TreeNode root, int target) {
		List<List<Integer>> r = new ArrayList<>();
		if (root == null) {
			return r;
		}
		List<Integer> t = new ArrayList<>();
		f2523(r, t, root, 0, target);
		return r;
	}

	public void f2523(List<List<Integer>> r, List<Integer> t, TreeNode n, int k, int s) {
		int i = n.val + k;
		List<Integer> tn = new ArrayList<>(t);
		tn.add(n.val);
		if (n.left == null && n.right == null && i == s) {
			r.add(tn);
		} else {
			if (n.left != null) {
				f2523(r, tn, n.left, i, s);
			}
			if (n.right != null) {
				f2523(r, tn, n.right, i, s);
			}
		}
	}

	// 1319
	public int makeConnected(int n, int[][] connections) {
		if (n > connections.length + 1) {
			return -1;
		}
		int[] note = new int[n];
		for (int i = 0; i < n; i++) {
			note[i] = i;
		}

		for (int i = 0; i < connections.length; i++) {
			if (find(note, connections[i][0]) != find(note, connections[i][1])) {
				union(note, connections[i][0], connections[i][1]);
			}
		}
		int r = 0;
		for (int i = 0; i < note.length; i++) {
			if (note[i] == i) {
				r++;
			}
		}
		return r - 1;
	}

	// 684
	public int[] findRedundantConnection(int[][] edges) {
		int len = edges.length;
		int[] note = new int[len + 1];
		for (int i = 0; i < len; i++) {
			note[i] = i;
		}

		for (int i = 0; i < len; i++) {
			if (find(note, edges[i][0]) != find(note, edges[i][1])) {
				union(note, edges[i][0], edges[i][1]);
			} else {
				return new int[] { edges[i][0], edges[i][1] };
			}
		}
		return null;
	}

	// 547
	public int findCircleNum(int[][] isConnected) {
		int len = isConnected.length;
		int[] note = new int[len];
		for (int i = 0; i < len; i++) {
			note[i] = i;
		}
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (isConnected[i][j] == 1) {
					f547_1(note, i, j);
				}
			}
		}
		int r = 0;
		for (int i = 0; i < len; i++) {
			if (note[i] == i) {
				r++;
			}
		}
		return r;
	}

	public void f547_1(int[] note, int i1, int i2) {
		note[f547_2(note, i1)] = f547_2(note, i2);
	}

	public int f547_2(int[] note, int i) {
		while (note[i] != i) {
			note[i] = note[note[i]];
			i = note[i];
		}
		return i;
	}

	// 990
	public boolean equationsPossible(String[] equations) {
		int[] root = new int[26];
		for (int i = 0; i < root.length; i++) {
			root[i] = i;
		}
		for (int i = 0; i < equations.length; i++) {
			if (equations[i].charAt(1) == '=') {
				int i1 = equations[i].charAt(0) - 'a';
				int i2 = equations[i].charAt(3) - 'a';
				union(root, i1, i2);
			}
		}
		for (int i = 0; i < equations.length; i++) {
			if (equations[i].charAt(1) == '!') {
				int i1 = equations[i].charAt(0) - 'a';
				int i2 = equations[i].charAt(3) - 'a';
				if (find(root, i1) == find(root, i2)) {
					return false;
				}
			}
		}
		return true;
	}

	public void union(int[] root, int i1, int i2) {
		root[find(root, i1)] = find(root, i2);
	}

	public int find(int[] root, int i) {
		while (root[i] != i) {
			root[i] = root[root[i]];
			i = root[i];
		}
		return i;
	}

	// 1144
	public int movesToMakeZigzag(int[] nums) {
		int a = 0, b = 0;
		for (int i = 0; i < nums.length; i++) {
			int l = (i - 1) < 0 ? Integer.MAX_VALUE : nums[i - 1];
			int r = (i + 1) >= nums.length ? Integer.MAX_VALUE : nums[i + 1];
			int t = nums[i] - Math.min(l, r) + 1;
			if (t > 0) {
				if (i % 2 == 0) {
					a += t;
				} else {
					b += t;
				}

			}
		}
		return Math.min(a, b);
	}

	// 2683
	public boolean CheckPermutation(String s1, String s2) {
		char[] a1 = s1.toCharArray();
		Arrays.sort(a1);
		char[] a2 = s2.toCharArray();
		Arrays.sort(a2);
		return Arrays.equals(a1, a2);
	}

	// 698
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int[] note = new int[10000];
		int s = 0;
		for (int i : nums) {

		}
		return false;
	}

	// 1636
	public int[] frequencySort(int[] nums) {
		return null;
	}

	// 763
	public List<Integer> partitionLabels(String s) {
		List<Integer> r = new ArrayList<>();
		char[] arr = s.toCharArray();
		Map<Character, Integer> note = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			note.put(arr[i], i);
		}
		int a = 0, b = 0;
		for (int i = 0; i < arr.length; i++) {
			b = Math.max(b, note.get(arr[i]));
			if (i == b) {
				r.add(b - a + 1);
				a = i + 1;
			}
		}
		return r;

//		List<Integer> r = new ArrayList<>();
//		while (s.length() > 0) {
//			int i = 0;
//			int j = -1;
//			while (i < s.length()) {
//				int t = s.lastIndexOf(s.charAt(i));
//				if (t > j) {
//					j = t;
//				}
//				if (i == j) {
//					break;
//				}
//				i++;
//				
//			}
//			r.add(i + 1);
//			s = s.substring(i + 1);
//		}
//		return r;
	}

	// 2552
	public int numSubarrayProductLessThanK_(int[] nums, int k) {
		Queue<Integer> note = new LinkedList<Integer>();
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			int n = note.size();
			for (int j = 0; j < n; j++) {
				int t = note.poll() * nums[i];
				if (t < k) {
					note.offer(t);
					r++;
				}
			}
			if (nums[i] < k) {
				note.offer(nums[i]);
				r++;
			}
		}
		return r;
	}

	// 453
	public int minMoves(int[] nums) {
		int r = 0, t = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > t) {
				r += nums[i] - t;
			} else if (nums[i] < t) {
				r += i * (t - nums[i]);
			}
		}
		return r;

		// 20%
//		int r = 0;
//		Arrays.sort(nums);
//		int t = nums[0];
//		for (int i = 1; i < nums.length; i++) {
//			r += nums[i] - t;
//		}
//		return r;
	}

	// 187
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> r = new ArrayList<>();
		for (int i = 0; i < s.length() - 10; i++) {
			String t = s.substring(i, i + 10);
			if (s.lastIndexOf(t) != i && !r.contains(t)) {
				r.add(t);
			}
		}
		return r;
	}

	// 1464
	public int maxProduct(int[] nums) {
		int a = 0, b = 0;
		for (int i : nums) {
			if (a > b) {
				b = Math.max(b, i);
			} else {
				a = Math.max(a, i);
			}
		}
		return (a - 1) * (b - 1);
//			Arrays.sort(nums);
//			return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
	}

	// 2279
	public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
		int[] arr = new int[rocks.length];
		for (int i = 0; i < rocks.length; i++) {
			arr[i] = capacity[i] - rocks[i];
		}
		Arrays.sort(arr);
		int r = 0;
		for (int i = 0; i < arr.length; i++) {
			if (additionalRocks >= arr[i]) {
				r++;
				additionalRocks -= arr[i];
			} else {
				break;
			}
		}
		return r;

//		int[][] arr = new int[rocks.length][2];
//		for (int i = 0; i < rocks.length; i++) {
//			arr[i][0] = capacity[i];
//			arr[i][1] = rocks[i];
//		}
//		Arrays.sort(arr, (o1, o2) -> {
//			return (o1[0] - o1[1]) - (o2[0] - o2[1]);
//		});
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		return 0;
	}

	// 658
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int i = 0, j = arr.length - 1;
		int t = Integer.MAX_VALUE;
		int m = 0;
		while (i < j && t != x) {
			m = (i + j) / 2;
			t = arr[m];
			if (t < x) {
				i = m + 1;
			} else if (t > x) {
				j = m - 1;
			}
		}

		List<Integer> r = new ArrayList<>();
		for (int l = m - k - 1; l < m + k + 1; l++) {
			if (l < 0 || l >= arr.length) {
				continue;
			}
			if (r.size() < k) {
				r.add(arr[l]);
			} else {
				int a = Math.abs(arr[l] - x);
				if (a < Math.abs(x - r.get(0))) {
					r.remove(0);
					r.add(arr[l]);
				}
			}
		}

		return r;
	}

//	private Map<Integer, Boolean> p464 = new HashMap<>();
	private int[] p464 = new int[1 << 21];

	// 464
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if (maxChoosableInteger >= desiredTotal) {
			return true;
		}
		if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
			return false;
		}
		return f464(0, 0, maxChoosableInteger, desiredTotal);
	}

	private boolean f464(int note, int sum, int maxChoosableInteger, int desiredTotal) {
		if (p464[note] == 1) {
			return true;
		}
		if (p464[note] == 2) {
			return false;
		}
//		if (p464.containsKey(note)) {
//			return p464.get(note);
//		}
		for (int i = 1; i <= maxChoosableInteger; i++) {
			if (((1 << i) & note) != 0) {
				continue;
			}
			if (sum + i >= desiredTotal) {
				p464[note] = 1;
//				p464.put(note, true);
				return true;
			}

			int n = (1 << i) | note;
			if (!f464(n, sum + i, maxChoosableInteger, desiredTotal)) {
				p464[note] = 1;
//				p464.put(note, true);
				return true;
			}
		}
		p464[note] = 2;
//		p464.put(note, false);
		return false;
	}

	// 2264
	public String largestGoodInteger(String num) {
		char[] arr = num.toCharArray();
		char t = '0' - 1;
		char s = t;
		int n = 1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == t) {
				n++;
			} else {
				n = 1;
				t = arr[i];
			}

			if (n == 3) {
				if (t > s) {
					s = t;
				}
			}
		}
		if (s != ('0' - 1)) {
			return "" + s + s + s;
		} else {
			return "";
		}
	}

	// 2590
	public int findMaximumXOR(int[] nums) {
		int r = 0;
		int[] note = new int[nums.length];
		note[0] = nums[0];
		for (int i = 1; i < note.length; i++) {
			note[i] = nums[i] ^ note[i - 1];
		}

		return r;
	}

	// 315
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> r = new ArrayList<>();
		r.add(0);
		int[] note1 = new int[nums.length];
		note1[nums.length - 1] = nums[nums.length - 1];
		int[] note2 = new int[nums.length];
		note2[nums.length - 1] = nums[nums.length - 1];

		for (int i = nums.length - 2; i >= 0; i--) {
			int t = 0;

			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] < note2[j]) {
					break;
				}
				if (nums[i] > note1[j]) {
					t += nums.length - j;
					break;
				}
				if (nums[j] < nums[i]) {
					t++;
				}
			}
			r.add(0, t);
			note1[i] = Math.max(note1[i + 1], nums[i]);
			note2[i] = Math.min(note2[i + 1], nums[i]);
		}
		return r;
	}

	// 1224
	public int maxEqualFreq(int[] nums) {
		Map<Integer, Integer> count = new HashMap<>();
		Map<Integer, Integer> times = new HashMap<>();
		int max = 0, r = 0;
		for (int i = 0; i < nums.length; i++) {
			count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
			Integer n = count.get(nums[i]);
			times.put(n, times.getOrDefault(n, 0) + 1);
			max = Math.max(max, n);
			if (n != 1) {
				System.out.print("-");
				times.put(n - 1, times.get(n - 1) - 1);
			}
			System.out.println("times : " + times);
			if (max == 1 || (times.get(max) * max + times.get(max - 1) * (max - 1) == i + 1 && times.get(max) == 1)
					|| (times.get(max) * max == i && times.get(1) == 1)) {
				r = Math.max(r, i + 1);
			}
			System.out.println("r : " + r);
		}
		return r;
	}

	// 238
	public int[] productExceptSelf(int[] nums) {
		int[] r = new int[nums.length];
		r[nums.length - 1] = nums[nums.length - 1];
		int[] l = new int[nums.length];
		l[0] = nums[0];
		int[] res = new int[nums.length];
		for (int i = 1; i < nums.length; i++) {
			l[i] = l[i - 1] * nums[i];
			r[nums.length - i - 1] = r[nums.length - i] * nums[nums.length - i - 1];
		}
		res[0] = r[1];
		res[nums.length - 1] = l[nums.length - 2];
		for (int i = 1; i < nums.length - 1; i++) {
			res[i] = l[i - 1] * r[i + 1];
		}
		return res;
	}

	// 1302
	public int deepestLeavesSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		List<TreeNode> note = new ArrayList<>();
		note.add(root);
		return f1302(note);
	}

	public int f1302(List<TreeNode> list) {
		List<TreeNode> note = new ArrayList<>();
		int s = 0;
		for (TreeNode n : list) {
			if (n.left != null) {
				note.add(n.left);
			}
			if (n.right != null) {
				note.add(n.right);
			}
			if (note.isEmpty()) {
				s += n.val;
			}
		}
		if (note.isEmpty()) {
			return s;
		} else {
			return f1302(note);
		}
	}

	// 623
	public TreeNode addOneRow(TreeNode root, int val, int depth) {
		if (depth == 0 || depth == 1) {
			TreeNode t = new TreeNode(val);
			if (depth == 1) {
				t.left = root;
			} else {
				t.right = root;
			}
			return t;
		}
		if (root != null && depth > 1) {
			root.left = addOneRow(root.left, val, depth > 2 ? depth - 1 : 1);
			root.right = addOneRow(root.right, val, depth > 2 ? depth - 1 : 0);
		}
		return root;
	}

	// 1374
	public String generateTheString(int n) {
		if (n % 2 == 0) {
			n = n - 1;
		}
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < n; i++) {
			sb.append('a');
		}
		sb.append('b');
		return sb.toString();
	}

	// 2054
	public int maxTwoEvents(int[][] events) {
		Arrays.sort(events, (e1, e2) -> {
			if (e1[0] != e2[0]) {
				return e1[0] - e2[0];
			} else {
				return e1[1] - e2[1];
			}
		});
		for (int i = 0; i < events.length; i++) {
			System.out.println(Arrays.toString(events[i]));
		}
		int len = events.length;
		int[][] note = new int[len][2];
		for (int i = 0; i < len; i++) {
			int[] t = events[i];
			int e = t[1], v = t[2];
			for (int j = 0; j < i; j++) {
				if (note[j][0] < t[0] && note[j][1] + t[2] > v) {
					e = t[1];
					v = note[j][1] + t[2];
				} else if (note[j][0] >= t[0] && note[j][1] > v) {
					e = note[j][0];
					v = note[j][1];
				}
			}
			note[i][0] = e;
			note[i][1] = v;
		}

		for (int i = 0; i < events.length; i++) {
			System.out.println(Arrays.toString(note[i]));
		}
		return note[len - 1][1];
	}

	// 2211
	public int countCollisions(String directions) {
		char[] arr = directions.toCharArray();

		int l = 0, r = arr.length - 1;
		while (l < r && arr[l] == 'L') {
			l++;
		}
		while (l < r && arr[r] == 'R') {
			r--;
		}
		int res = 0;
		for (int i = l; i <= r; i++) {
			if (arr[i] != 'S') {
				res++;
			}
		}
		return res;
	}

	// 2518
	public int[] dailyTemperatures(int[] temperatures) {
		int[] r = new int[temperatures.length];
		Deque<Integer> stack = new LinkedList<Integer>();
		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekLast()]) {
				Integer t = stack.pollLast();
				r[t] = i - t;
			}
			stack.offer(i);
		}
		return r;
	}

	// 287
	public int findDuplicate(int[] nums) {
//		int s = 0;
//		for (int i = 0; i < nums.length; i++) {
//			s = s ^ i ^ nums[i];
//		}
//		return s;
		int fast = 0, slow = 0;
		while (true) {
			fast = nums[nums[fast]];
			slow = nums[slow];
			if (fast == slow) {
				break;
			}
		}
		slow = 0;
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}

	// 757
	public int intersectionSizeTwo(int[][] intervals) {
		int n = intervals.length;
		int res = 0;
		int m = 2;
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			}
			return a[0] - b[0];
		});

//        for (int[] is : intervals) {
//			System.out.println(Arrays.toString(is));
//		}
		List<Integer>[] temp = new List[n];
		for (int i = 0; i < n; i++) {
			temp[i] = new ArrayList<Integer>();
		}

		for (int i = n - 1; i >= 0; i--) {
			for (int j = intervals[i][0], k = temp[i].size(); k < m; j++, k++) {
				res++;
				f757(intervals, temp, i - 1, j);
				for (int o = 0; o < temp.length; o++) {
					System.out.print(temp[o]);
				}
				System.out.println();
			}

			System.out.println(res);
		}
		return res;
	}

	private void f757(int[][] intervals, List<Integer>[] temp, int pos, int num) {
		for (int i = pos; i >= 0; i--) {
			if (intervals[i][1] < num) {
				break;
			}
			temp[i].add(num);
		}
	}

	// 814
	public TreeNode pruneTree(TreeNode root) {

		boolean b1 = f814(root.left);
		boolean b2 = f814(root.right);

		if (b1) {
			root.left = null;
		}
		if (b2) {
			root.right = null;
		}

		if (root.val == 0 && b1 && b2) {
			return null;
		}
		return root;
	}

	private boolean f814(TreeNode n) {
		if (n == null) {
			return true;
		}
		boolean b1 = f814(n.left);
		boolean b2 = f814(n.right);

		if (b1) {
			n.left = null;
		}
		if (b2) {
			n.right = null;
		}

		if (n.val == 0 && b1 && b2) {
			return true;
		}
		return false;
	}

	public int[] asteroidCollision(int[] asteroids) {
		List<int[]> note = new ArrayList<>();
		List<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < asteroids.length; i++) {
			if (asteroids[i] < 0) {
				note.add(new int[] { i, asteroids[i] });
			}
		}
		for (int i = 0; i < note.size(); i++) {
			int[] t = note.get(i);
			for (int j = t[0] - 1; j >= 0; j--) {
				if (asteroids[j] <= 0) {
					continue;
				} else if (asteroids[j] == t[1] * -1) {
					System.out.println(456);
					asteroids[t[0]] = Integer.MIN_VALUE;
					asteroids[j] = Integer.MIN_VALUE;
				} else if (asteroids[j] > t[1]) {
					System.out.println(123);
					asteroids[t[0]] = Integer.MIN_VALUE;
				} else {
					asteroids[j] = Integer.MIN_VALUE;
				}
			}
		}
		System.out.println(Arrays.toString(asteroids));
		for (int i = 0; i < asteroids.length; i++) {
			if (asteroids[i] != Integer.MIN_VALUE) {
				r.add(asteroids[i]);
			}
		}
		int[] res = new int[r.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = r.get(i);
		}
		return res;
	}

	// 1071
	public String gcdOfStrings(String str1, String str2) {
		if (!(str1 + str2).equals(str2 + str1)) {
			return "";
		}
		return str1.substring(0, f1071(str1.length(), str2.length()));
	}

	private int f1071(int a, int b) {
		return b == 0 ? a : f1071(b, a % b);
	}

	// 1200
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		List<List<Integer>> r = new ArrayList<>();
		Arrays.sort(arr);
		int t = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i + 1] - arr[i] < t) {
				r.clear();
				ArrayList<Integer> o = new ArrayList<Integer>();
				o.add(arr[i]);
				o.add(arr[i + 1]);
				r.add(o);
				t = arr[i + 1] - arr[i];
			} else if (arr[i + 1] - arr[i] == t) {
				ArrayList<Integer> o = new ArrayList<Integer>();
				o.add(arr[i]);
				o.add(arr[i + 1]);
				r.add(o);
			}
		}
		return r;
	}

	// 30
	public List<Integer> findSubstring(String s, String[] words) {
		int len = words[0].length();
		List<Integer> r = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			String t = s.substring(i);
			boolean flag = false;
			for (int j = 0; j < words.length; j++) {
				if (t.startsWith(words[j])) {
					r.add(i);
					i += len;
					flag = true;
					break;
				}
			}
			while (flag) {
				String t2 = s.substring(i);
				flag = false;
				for (int j = 0; j < words.length; j++) {
					if (t2.startsWith(words[j])) {
						i += len;
						flag = true;
						break;
					}
				}
			}
		}
		return r;
	}

	private int r513 = 0, d513 = 0, t513 = 0;

	// 513
	public int findBottomLeftValue(TreeNode root) {
		f513(root, 0, 0);
		return r513;
	}

	private void f513(TreeNode n, int d, int t) {
		if (n == null) {
			return;
		}
		if (d > d513) {
			d513 = d;
			t513 = t;
			r513 = n.val;
		} else if (d == d513 && t < t513) {
			t513 = t;
			r513 = n.val;
		}
		f513(n.left, d + 1, t);
		f513(n.right, d + 1, t + 1);
	}

	// 1089
	public void duplicateZeros(int[] arr) {
		int i = 0, j = 1;
		while (j <= arr.length) {
			if (arr[i] == 0) {

			}
		}
	}

	// 1051
	public int heightChecker(int[] heights) {
		int[] arr = Arrays.copyOf(heights, heights.length);
		Arrays.sort(arr);
		int r = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != heights[i]) {
				r++;
			}
		}
		return r;
	}

	// 875
	public int minEatingSpeed(int[] piles, int h) {
		int l = 1, r = Arrays.stream(piles).max().getAsInt(), t = 0;
		while (l < r) {
			t = (int) (((long) l + r) / 2);
			if (f875(piles, h, t)) {
				r = t;
			} else {
				l = t + 1;
			}
		}
		return l;
	}

	private boolean f875(int[] piles, int h, int k) {
		int s = 0;
		for (int i : piles) {
			s += (i % k > 0 ? i / k + 1 : i / k);
		}
		return s <= h;
	}

	// 2633
	public int findClosest(String[] words, String word1, String word2) {
		int a = Integer.MIN_VALUE;
		int b = Integer.MAX_VALUE;
		int r = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			boolean f = false;
			if (words[i].equals(word1)) {
				a = i;
				f = true;
			}
			if (words[i].equals(word2)) {
				b = i;
				f = true;
			}
			if (f) {
				r = Math.min(r, Math.abs(b - a));
			}
		}
		return r;
	}

	// 1547
	public int minCost(int n, int[] cuts) {
		int m = cuts.length;
		Arrays.sort(cuts);
		int[] arr = new int[m + 2];
		for (int i = 1; i <= m; ++i) {
			arr[i] = cuts[i - 1];
		}
		arr[m + 1] = n;
		int[][] f = new int[m + 2][m + 2];
		for (int i = m; i >= 1; --i) {
			for (int j = i; j <= m; ++j) {
				f[i][j] = i == j ? 0 : Integer.MAX_VALUE;
				for (int k = i; k <= j; ++k) {
					f[i][j] = Math.min(f[i][j], f[i][k - 1] + f[k + 1][j]);
				}
				f[i][j] += arr[j + 1] - arr[i - 1];
			}
		}
		return f[1][m];

	}

	// 699
	public List<Integer> fallingSquares(int[][] positions) {
		List<Integer> r = new ArrayList<>();

		List<int[]> note = new ArrayList<>();
		int a = 0;
		for (int i = 0; i < positions.length; i++) {
			int[] t = positions[i];
			int left = t[0];
			int right = t[0] + t[1] - 1;
			int h = t[1];
			int s = h;
			for (int j = 0; j < note.size(); j++) {
				int[] n = note.get(j);
				if (!(n[1] < left || n[0] > right)) {
					s = Math.max(s, h + n[2]);
				}
			}
			note.add(new int[] { left, right, s });
			a = Math.max(a, s);
			r.add(a);

			for (int[] o : note) {
				System.out.print(Arrays.toString(o));
			}
			System.out.println();
		}

		return r;
	}

	// 467
	public int findSubstringInWraproundString(String p) {
		char[] arr = p.toCharArray();
		int[] note = new int[26];
		int t = 0;
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] - c == 1 || c - arr[i] == 25) {
				t++;
			} else {
				t = 1;
			}
			c = arr[i];
			note[arr[i] - 'a'] = Math.max(t, note[arr[i] - 'a']);
		}
		int r = 0;
		for (int i = 0; i < note.length; i++) {
			r += note[i];
		}
		return r;
	}

	// 965
	public boolean isUnivalTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left != null && root.left.val != root.val) {
			return false;
		}
		if (root.right != null && root.right.val != root.val) {
			return false;
		}
		return isUnivalTree(root.right) && isUnivalTree(root.left);
	}

	// 436
	public int[] findRightInterval(int[][] intervals) {
		int len = intervals.length;
		int[] r = new int[len];
		Arrays.fill(r, -1);
		for (int i = 0; i < len; i++) {
			int end = intervals[i][1];
			for (int j = 0; j < len; j++) {
				if (i == j) {
					continue;
				}
				if (r[i] < 0 && intervals[j][0] >= end) {
					r[i] = j;
				} else if (r[i] >= 0 && intervals[j][0] >= end && intervals[j][0] < intervals[r[i]][0]) {
					r[i] = j;
				}
			}
		}

		return r;
	}

	// 462
//	public int minMoves2(int[] nums) {
//		for (int i : nums) {
//			
//		}
//    }

	// 2562
//	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
//		if (root == null) {
//			return null;
//		}
//		TreeNode t = inorderSuccessor2(root.left, p);
//		if (t == p) {
//			return root;
//		}
//		inorderSuccessor2(root.right, p);
//		return null;
//    }
//	
//	private boolean f2562(TreeNode n, TreeNode p) {
//		
//		return false; 
//	}

	// 2530
	public boolean oneEditAway(String first, String second) {
		if (Math.abs(first.length() - second.length()) > 1) {
			return false;
		}
		if (first.length() == second.length()) {
			boolean flag = false;
			for (int i = 0; i < first.length(); i++) {
				if (first.charAt(i) != second.charAt(i)) {
					if (flag) {
						return false;
					} else {
						flag = true;
					}
				}
			}
			return true;
		} else {
			int len = Math.max(first.length(), second.length());
			int i = 0, j = 0;
			while (len > i + j && first.charAt(i) == second.charAt(i)) {
				i++;
			}
			while (len > i + j && first.charAt(first.length() - j - 1) == second.charAt(second.length() - j - 1)) {
				j++;
			}
			return len == i + j;
		}

	}

	// 944
	public int minDeletionSize(String[] strs) {
		int len = strs[0].length();
		int r = 0;
		for (int i = 0; i < len; i++) {
			int t = Integer.MIN_VALUE;
			for (int j = 0; j < strs.length; j++) {
				char c = strs[j].charAt(i);
				if (c < t) {
					r++;
					break;
				} else {
					t = c;
				}
			}
		}
		return r;
	}

	// 2017
	public long gridGame(int[][] grid) {
		int len = grid[0].length;
		long[] r1 = new long[len + 1];
		long[] r2 = new long[len + 1];
		for (int i = 0; i < len; i++) {
			r1[i + 1] = r1[i] + grid[0][i];
			r2[i + 1] = r2[i] + grid[1][i];
		}
		System.out.println(Arrays.toString(r1));
		System.out.println(Arrays.toString(r2));

		long r = Long.MAX_VALUE;

		for (int i = 0; i < len; i++) {
			long t1 = r1[len] - r1[i + 1];
			long t2 = r2[i] - r2[0];
			r = Math.min(r, Math.max(t1, t2));
		}

		return r;
	}

	// 435
	public int eraseOverlapIntervals(int[][] intervals) {
		Arrays.sort(intervals, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o1[0] - o2[0];
		});
		int r = 0;
		int m = Integer.MIN_VALUE, n = Integer.MIN_VALUE;
		for (int i = 0; i < intervals.length; i++) {
			int[] t = intervals[i];
			System.out.println(Arrays.toString(t));
			if (t[0] >= n) {
				r++;
				m = t[0];
				n = t[1];
			} else if (t[0] >= m && t[1] < n) {
				m = t[0];
				n = t[1];
			}
		}
		return intervals.length - r;
	}

	// 583
	public int minDistance(String word1, String word2) {
		int n = word1.length();
		int m = word2.length();
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i < n; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < m; j++) {
				if (c1 == word2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		return (n - dp[n][m]) + (m - dp[n][m]);
	}

	// 1143
	public int longestCommonSubsequence(String text1, String text2) {
		int n = text1.length();
		int m = text2.length();
		int[][] dp = new int[n + 1][m + 1];

		for (int i = 0; i < n; i++) {
			char c1 = text1.charAt(i);
			for (int j = 0; j < m; j++) {
				if (c1 == text2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		return dp[n][m];
	}

	// 942
	public int[] diStringMatch(String s) {
		int[] r = new int[s.length() + 1];
		char[] arr = s.toCharArray();
		int a = 0;
		int b = s.length();
		if (arr[0] == 'I') {
			r[0] = 0;
			a++;
		} else {
			r[0] = s.length();
			b--;
		}
		for (int i = 1; i < arr.length; i++) {
			if (arr[0] == 'I') {
				r[i] = a;
				a++;
			} else {
				r[i] = b;
				b--;
			}
		}
		r[s.length()] = a;
		return r;
	}

	// 1109
	public int[] corpFlightBookings(int[][] bookings, int n) {
		// 差分
		int[] t = new int[n];
		for (int i = 0; i < bookings.length; i++) {
			int[] data = bookings[i];
			t[data[0] - 1] += data[2];
			t[data[1]] -= data[2];
		}
		int[] r = new int[n];
		r[0] = t[0];
		for (int i = 1; i < n; i++) {
			r[i] = r[i - 1] + t[i];
		}
		return r;

		// 暴力
//		int[] r = new int[n];
//		for (int i = 0; i < bookings.length; i++) {
//			int[] data = bookings[i];
//			for (int j = data[0]; j <= data[1]; j++) {
//				
//				r[j - 1] += data[2];
//			}
//		}
//		return r;
	}

	// 853
	public int carFleet(int target, int[] position, int[] speed) {
		int len = position.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return 1;
		}
		double[][] arr = new double[len][3];
		for (int i = 0; i < len; i++) {
			arr[i][0] = position[i];
			arr[i][1] = speed[i];
			double d = target - arr[i][0];
			arr[i][2] = d / arr[i][1];
		}
		Arrays.sort(arr, (o1, o2) -> {//
			if (o2[0] - o1[0] > 1) {
				return 1;
			} else if (o2[0] - o1[0] < 1) {
				return -1;
			} else {
				return 0;
			}
		});
		for (double[] is : arr) {
			System.out.println(Arrays.toString(is));
		}
		int r = 0;
		double t = 0;
		for (int i = 0; i < len; i++) {
			if (arr[i][2] > t) {
				r++;
				t = arr[i][2];
			}
		}
		return r;
	}

	// 1300
	public int findBestValue(int[] arr, int target) {
//		Arrays.sort(arr);
//		int[] dp = new int[arr.length];
//		int len = arr.length;
//		int s = 0;
//		int r = Integer.MAX_VALUE;
//		for (int i = 0; i < len; i++) {
//			dp[i] = Math.abs(target - (s + arr[i] * (len - i)));
//			if (dp[i] < r) {
//				r = dp[i];
//			} else {
//				return arr[i - 1];
//			}
//			s += arr[i];
//		}
//		return arr[len - 1];
		return 0;
	}

	// 1672
	public int maximumWealth(int[][] accounts) {
		int max = 0;
		for (int i = 0; i < accounts.length; i++) {
			int t = 0;
			for (int j = 0; j < accounts[i].length; j++) {
				t += accounts[i][j];
			}
			max = Math.max(max, t);
		}
		return max;

//		return Arrays.stream(Arrays.stream(accounts).max((o1, o2) -> {
//			return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
//		}).get()).sum();
	}

	// 870
	public int[] advantageCount(int[] nums1, int[] nums2) {
//		Arrays.sort(nums1);
//		int min = 0;
//		for (int i = 0; i < nums1.length; i++) {
//			boolean flag = true;
//			for (int j = i; j < nums2.length; j++) {
//				if (nums1[j] > nums2[i]) {
//					int t = nums1[j];
//					nums1[j] = nums1[i];
//					nums1[i] = t;
//					min++;
//					flag = false;
//					break;
//				}
//			}
//			if (flag) {
//				int t = nums1[min];
//				nums1[min] = nums1[i];
//				nums1[i] = t;
//				min++;
//			}
//		}
		return nums1;
	}

	// 806
	public int[] numberOfLines(int[] widths, String s) {
		char[] arr = s.toCharArray();
		int i = 1;
		int n = 0;
		for (char c : arr) {
			int t = n + widths[c - 'a'];
			if (t > 100) {
				i++;
				n = widths[c - 'a'];
			} else {
				n = t;
			}
		}
		int[] r = new int[] { i, n };
		return r;
	}

	// 429
	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		if (root == null) {
			return r;
		}
		List<Node> t = new ArrayList<>();
		t.add(root);
		f429(t, r);
		return r;
	}

	private void f429(List<Node> list, List<List<Integer>> r) {
		if (!list.isEmpty()) {
			List<Node> next = new ArrayList<Node>();
			List<Integer> t = new ArrayList<>();
			for (Node n : list) {
				t.add(n.val);
				if (!n.children.isEmpty()) {
					next.addAll(n.children);
				}
			}
			r.add(t);
			f429(next, r);
		}
	}

	// 648
	public String predictPartyVictory(String senate) {
//		char[] arr = senate.toCharArray();
//		int r = 0;
//		int d = 0;
//		for (char c : arr) {
//			if (c == 'R') {
//				r++;
//			} else {
//				d++;
//			}
//		}
//		if (r > d) {
//			return "Radiant";
//		} else if (d > r) {
//			return "Dire";
//		} else {
//			if (arr[arr.length - 1] == 'R') {
//				return "Dire";
//			} else {
//				return "Radiant";
//			}
//		}
		return "";
	}

	// 2296
	public double myPow(double x, int n) {
		if (x == 1) {
			return 1;
		}
		if (n == -2147483648)
			return 0;
		if (x == -1) {
			if (n % 2 == 1) {
				return -1;
			} else {
				return 1;
			}
		}
		if (n == 0) {
			return 1;
		}

		boolean f = false;
		if (n < 0) {
			f = true;
			n = -1 * n;
		}
		double t = x;
		while (n > 1) {
			x *= t;
			n--;
		}
		if (f) {
			x = 1d / x;
		}
		return x;
	}

	// 796
	public boolean rotateString(String s, String goal) {
		if (s.length() == goal.length() && (goal + goal).contains(s)) {
			return true;
		}
		return false;
	}

	// 2423
	public ListNode mergeKLists(ListNode[] lists) {
		ArrayList<ListNode> arr = new ArrayList<>();
		for (ListNode node : lists) {
			while (node != null) {
				arr.add(node);
				node = node.next;
			}
		}
		arr.sort((n1, n2) -> {
			return n1.val - n2.val;
		});
		if (arr.isEmpty()) {
			return null;
		}
		ListNode r = arr.get(0);
		ListNode t = r;
		for (int i = 1; i < arr.size(); i++) {
			t.next = arr.get(i);
			t = t.next;
		}
		return r;
	}

	// 2563
	public int massage(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
		}
		return dp[dp.length - 1];
	}

	// 2360
	public List<Integer> findAnagrams2(String s, String p) {
		List<Integer> r = new ArrayList<>();
		if (p.length() > s.length()) {
			return r;
		}
		int[] a1 = new int[26];
		int[] a2 = new int[26];
		for (int i = 0; i < p.length(); i++) {
			a1[p.charAt(i) - 'a']++;
			a2[s.charAt(i) - 'a']++;
		}
		if (Arrays.equals(a1, a2)) {
			r.add(0);
		}
		for (int i = p.length(); i < s.length(); i++) {
			a2[s.charAt(i) - 'a']++;
			a2[s.charAt(i - p.length()) - 'a']--;
			if (Arrays.equals(a1, a2)) {
				r.add(i - p.length() + 1);
			}
		}
		return r;
	}

	// 2359
	public boolean checkInclusion2(String s1, String s2) {
		if (s2.length() < s1.length()) {
			return false;
		}
		int[] a1 = new int[26];
		int[] a2 = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			a1[s1.charAt(i) - 'a']++;
			a2[s2.charAt(i) - 'a']++;
		}
		if (Arrays.equals(a1, a2)) {
			return true;
		}
		for (int i = s1.length(); i < s2.length(); i++) {
			a2[s2.charAt(i) - 'a']++;
			a2[s2.charAt(i - s1.length()) - 'a']--;
			if (Arrays.equals(a1, a2)) {
				return true;
			}
		}
		return false;
	}

	// 1331
	public int[] arrayRankTransform(int[] arr) {

		return null;
	}

	// 661
	public int[][] imageSmoother(int[][] img) {
		int m = img.length, n = img[0].length;
		int[][] r = new int[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				int sum = img[i][j];
				int num = 1;
				if (i > 0 && j > 0) {
					sum += img[i - 1][j - 1];
					num++;
				}
				if (i > 0) {
					sum += img[i - 1][j];
					num++;
				}
				if (i > 0 && j < n - 1) {
					sum += img[i - 1][j + 1];
					num++;
				}
				if (j > 0) {
					sum += img[i][j - 1];
					num++;
				}
				if (j < n - 1) {
					sum += img[i][j + 1];
					num++;
				}
				if (i < m - 1 && j > 0) {
					sum += img[i + 1][j - 1];
					num++;
				}
				if (i < m - 1) {
					sum += img[i + 1][j];
					num++;
				}
				if (i < m - 1 && j < n - 1) {
					sum += img[i + 1][j + 1];
					num++;
				}
				r[i][j] = sum / num;
			}
		return r;
	}

	// 409
	public int longestPalindrome(String s) {
		char[] arr = s.toCharArray();
		int[] n = new int[128];
		for (char c : arr) {
			n[c - 'A']++;
		}
		boolean f = true;
		int r = 0;
		for (int i : n) {
			if (i % 2 == 0) {
				r += i;
			} else if (f && i % 2 == 1) {
				r += i;
				f = false;
			} else if (i % 2 == 1 && i > 2) {
				r += i - 1;
			}
		}
		return r;
	}

	// 2038
	public boolean winnerOfGame(String colors) {
		char[] arr = colors.toCharArray();
		char c = arr[0];
		int t = 1;
		int a = 0, b = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == c) {
				t++;
			} else {
				if (c == 'A' && t > 2) {
					a += t - 2;
				} else if (c == 'B' && t > 2) {
					b += t - 2;
				}
				t = 1;
				c = arr[i];
			}
		}
		if (c == 'A' && t > 2) {
			a += t - 2;
		} else if (c == 'B' && t > 2) {
			b += t - 2;
		}
		return a > b;
	}

	// 2441
	public int longestConsecutive(int[] nums) {
		if (nums.length < 1) {
			return 0;
		}
		Arrays.sort(nums);
		int t = 1, r = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1] + 1) {
				t++;
			} else if (nums[i] != nums[i - 1]) {
				r = Math.max(r, t);
				t = 1;
			}
		}
		return Math.max(r, t);
	}

	// 201
	public int rangeBitwiseAnd(int left, int right) {
		int n = 0;
		while (left != right) {
			left >>= 1;
			right >>= 1;
			n++;
		}
		return right << n;
	}

	// 961
	public int repeatedNTimes(int[] nums) {
		int t = nums[0];
		int c = 1;
		for (int i = 1; i < nums.length; i++) {
			if (c == 0) {
				t = nums[i];
				c++;
			} else if (nums[i] != t) {
				c--;
			} else {
				c++;
			}
		}
		return t;
	}

	// 2325
	public int singleNumbe2325(int[] nums) {
		int r = 0;
		for (int i : nums) {
			r ^= i;
		}
		return r;
	}

	// 1346
	public boolean checkIfExist(int[] arr) {
		Set<Integer> note = new HashSet<>();
		for (Integer i : arr) {
			note.add(i);
			if (note.contains(i * 2)) {
				return true;
			}
		}
		note.remove(0);
		for (Integer i : note) {
			if (note.contains(i * 2)) {
				return true;
			}
		}
		return false;
	}

	// 2414
	public int lenLongestFibSubseq(int[] arr) {
		Set<Integer> note = new HashSet<>();
		for (Integer i : arr) {
			note.add(i);
		}
		int r = 2;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int a = arr[i], b = arr[j];
				int t = 2;
				int c = a + b;
				while (note.contains(c)) {
					t++;
					a = b;
					b = c;
					c = a + b;
				}
				r = Math.max(r, t);
			}
		}
		System.out.println(r);
		return r >= 3 ? r : 3;
	}

	// 2055
	public int[] platesBetweenCandles(String s, int[][] queries) {
		char[] arr = s.toCharArray();

		int len = queries.length;
		int[] r = new int[len];
		return r;
//		int len = queries.length;
//		int[] r = new int[len];
//		for (int i = 0; i < len; i++) {
//			String t = s.substring(queries[i][0], queries[i][1] + 1);
//			System.out.println(t);
//			int a = t.indexOf("|");
//			int b = t.lastIndexOf("|");
//			if (a != b) {
//				String tt = t.substring(a, b);
//				r[i] = tt.replace("|", "").length();
//			}
//		}
//		return r;
	}

	// 1541
	public int minInsertions(String s) {
		char[] arr = s.toCharArray();
		int n = 0, r = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				n++;
			} else {
				if (i < arr.length - 1) {
					if (arr[i + 1] == ')') {
						if (n > 0) {
							n--;
						} else {
							r++;
						}
						i++;
					} else {
						if (n > 0) {
							n--;
							r++;
						} else {
							r += 2;
						}
					}
				} else {
					if (n > 0) {
						n--;
						r++;
					} else {
						r += 2;
					}
				}
			}
		}
		return r + n * 2;
	}

	// 2491
	public boolean searchMatrix2491(int[][] matrix, int target) {
		if (matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int m = matrix.length, n = matrix[0].length;
		int i = 0, j = n - 1;
		while (matrix[i][j] != target) {
			if (matrix[i][j] > target) {
				j--;
				if (j < 0) {
					return false;
				}
			} else if (matrix[i][j] < target) {
				i++;
				if (i >= m) {
					return false;
				}
			}
		}
		return true;
	}

	// 1029
	public int twoCitySchedCost(int[][] costs) {
		int len = costs.length / 2;
		Arrays.sort(costs, (a, b) -> {
			return (b[1] - b[0]) - (a[1] - a[0]);
		});
		int r = 0;
		for (int i = 0; i < len; i++) {
			r += costs[i][0] + costs[i + len][1];
		}
		return r;
	}

	// 1354
	public boolean isPossible1354(int[] target) {
//		int len = target.length;
//		Arrays.sort(target);
//		int i = 0;
//		while (i < len) {
//			int t = target[i];
//		}
		return false;
	}

	// 504
	public String convertToBase7(int num) {
		String r = "";
		if (num < 0) {
			r = "-";
			num *= -1;
		}
		int s = 0;
		int x = 1;
		while (num > 0) {
			int t = num % 7;
			num = num / 7;
			s += t * x;
			x *= 10;
		}
		return r + s;
	}

	// 2240
	public int halfQuestions(int[] questions) {
		int n = questions.length / 2;
		int[] note = new int[1001];
		for (int i : questions) {
			note[i]++;
		}
		Arrays.sort(note);
		int t = 0;
		int r = 0;
		for (int i = 1000; i >= 0; i--) {
			t += note[i];
			r++;
			if (t >= n) {
				break;
			}
		}
		return r;
	}

	// 2260
	public int[] exchange(int[] nums) {
		int i = 0, j = nums.length - 1;
		while (i < j) {
			while (i < j && nums[i] % 2 == 1) {
				i++;
			}
			System.out.println(i);
			while (i < j && nums[j] % 2 == 0) {
				j--;
			}
			if (i < j) {
				int t = nums[i];
				nums[i] = nums[j];
				nums[j] = t;
				i++;
				j--;
			}
		}
		return nums;
	}

	// 1306
	public boolean canReach(int[] arr, int start) {
//		Set<Integer> note = new HashSet<>();
//		for (int i = 0; i < arr.length; i++) {
//			if (arr[i] == 0) {
//				note.add(i);
//			}
//		}
//		Set<Integer> t = new HashSet<>();
//		t.addAll(note);
//		while (t.size() > 0) {
//			if (t.contains(start)) {
//				return true;
//			}
//			Set<Integer> n = new HashSet<>();
//			for (int i = 0; i < arr.length; i++) {
//				int a = i + arr[i];
//				int b = i - arr[i];
//				if ((t.contains(a) || t.contains(b)) && !note.contains(i)) {
//					n.add(i);
//				}
//			}
//			note.addAll(n);
//			t = n;
//		}
//		return false;

		return f1306(arr, start);
	}

	private boolean f1306(int[] arr, int i) {
		if (i < 0 || i >= arr.length || arr[i] == -1) {
			return false;
		}
		int t = arr[i];
		arr[i] = -1;
		return t == 0 || f1306(arr, i + t) || f1306(arr, i - t);
	}

	// 2104
	public long subArrayRanges(int[] nums) {
		int len = nums.length;
		long r = 0;
		for (int i = 0; i < len; i++) {
			int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
			for (int j = i; j < len; j++) {
				minVal = Math.min(minVal, nums[j]);
				maxVal = Math.max(maxVal, nums[j]);
				r += maxVal - minVal;
			}
		}
		return r;

	}

	// 116
	public int numDistinct(String s, String t) {

		return 0;
	}

	// 2536
	public int trap(int[] height) {
		int len = height.length;
		if (len < 3) {
			return 0;
		}
		int[] left = new int[len], right = new int[len];
		int lm = height[0];
		int rm = height[len - 1];
		for (int i = 1; i < len; i++) {
			left[i] = lm;
			lm = Math.max(lm, height[i]);
			right[len - i - 1] = rm;
			rm = Math.max(rm, height[len - i - 1]);
		}
		int r = 0;
		for (int i = 1; i < len - 1; i++) {
			int t = Math.min(left[i], right[i]);
			if (t > height[i]) {
				r += t - height[i];
			}
		}
		return r;
	}

	// 1338
	public int minSetSize(int[] arr) {
		int[] note = new int[100001];
		for (int i : arr) {
			note[i]++;
		}
		Arrays.sort(note);
		int r = 0, s = 0, len = arr.length / 2;
		for (int i = note.length - 1; i >= 0; i--) {
			r++;
			s += note[i];
			if (s >= len) {
				break;
			}
		}
		return r;
	}

	// 2024
	public int maxConsecutiveAnswers(String answerKey, int k) {
		char[] arr = answerKey.toCharArray();
		int i = 0, j = 1;
		int t = 0, f = 0;
		if (arr[0] == 'T') {
			t = 1;
		} else {
			f = 1;
		}
		while (j < arr.length) {
			if (arr[j] == 'T') {
				t++;
			} else {
				f++;
			}

			if (Math.min(t, f) > k) {
				if (arr[i] == 'T') {
					t--;
				} else {
					f--;
				}
				i++;
			}
			j++;
		}
		return j - i;
	}

	// 881
	public int numRescueBoats(int[] people, int limit) {
		int r = 0;
		Arrays.sort(people);
		int i = 0, j = people.length - 1;
		while (i < j) {
			int t = limit - people[i];
			if (people[j] <= t) {
				r++;
				i++;
				j--;
			} else {
				r++;
				j--;
			}
		}
		if (i == j) {
			r++;
		}
		return r;
	}

	// 1493
	public int longestSubarray(int[] nums) {
		int len = nums.length;
		int[] as = new int[len];
		int a = 0;
		int[] bs = new int[len];
		int b = 0;

		for (int i = 0; i < len; i++) {
			if (nums[i] == 1) {
				a++;
			} else {
				as[i] = a;
				a = 0;
			}
			if (nums[len - i - 1] == 1) {
				b++;
			} else {
				bs[len - i - 1] = b;
				b = 0;
			}
		}

		if (a == len) {
			return len - 1;
		}

		int r = 0;
		for (int i = 0; i < len; i++) {
			r = Math.max(r, as[i] + bs[i]);
		}
		return r;
	}

	// 1926
	public int nearestExit(char[][] maze, int[] entrance) {
		int m = maze.length;
		int n = maze[0].length;
		int r = 0;
		int t = 0;
		while (true) {
			break;
		}
		return r;
	}

	// 2408
	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < m; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < n; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		return dp[m - 1][n - 1];
	}

	// 633
	public boolean judgeSquareSum(int c) {
		Set<Integer> note = new HashSet<Integer>();
		for (int i = 1; i < (int) Math.sqrt(c); i++) {
			int t = i * i;
			note.add(t);
			if (note.contains(c - t)) {
				return true;
			}
		}
		return false;
	}

	// 2139
	public int minMoves(int target, int maxDoubles) {
		int r = 0;
		while (target > 0 && maxDoubles > 0) {
			if (target % 2 == 1) {
				target--;
			} else {
				target /= 2;
				maxDoubles--;
			}
			r++;
		}
		r = r + target - 1;
		return r;
	}

	// 1299
	public int[] replaceElements(int[] arr) {
		int m = -1;
		for (int i = arr.length - 1; i >= 0; i--) {
			int t = arr[i];
			arr[i] = m;
			m = Math.max(m, t);
		}
		return arr;
	}

	// 2144
	public int minimumCost(int[] cost) {
		Arrays.sort(cost);
		int t = 1, r = 0;
		for (int i = cost.length - 1; i >= 0; i--) {
			if (t % 3 != 0) {
				r += cost[i];
			}
			t++;
		}
		return r;
	}

	// 713
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k == 0 || k == 1) {
			return 0;
		}
//		int r = 0;
//		for (int i = 0; i < nums.length; i++) {
//			int s = 1;
//			for (int j = i; j < nums.length; j++) {
//				s *= nums[j];
//				if (s < k) {
//					r++;
//				} else {
//					break;
//				}
//			}
//
//		}

		int r = 0;

		int a = 0;
		int s = 1;

		for (int b = 0; b < nums.length; b++) {
			s *= nums[b];
			while (s >= k) {
				s /= nums[a];
				a++;
			}
			r += b - a + 1;
		}

		return r;
	}

//	public int f713(int[] nums, int k, int i, int s, int r) {
//		for (int j = i; j < nums.length; j++) {
//			int t = s * nums[i];
//			if (s * nums[i] < k) {
//				r++;
//				System.out.println(t);
//				r = f713(nums, k, i + 1, t, r);
//			} else {
//				return r;
//			}
//		}
//		return r;
//	}

	// 389
	public char findTheDifference(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		Arrays.sort(a);
		Arrays.sort(b);
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return b[i];
			}
		}
		return b[b.length - 1];
	}

	// 1791
	public int findCenter(int[][] edges) {
		int[] a = edges[0];
		int[] b = edges[1];
		if (a[0] == b[0] || a[0] == b[1]) {
			return a[0];
		} else {
			return a[1];
		}
	}

	// 560
	public int subarraySum(int[] nums, int k) {
		Map<Integer, Integer> note = new HashMap<>();
		note.put(0, 1);
		int s = 0, r = 0;
		for (int i = 0; i < nums.length; i++) {
			s += nums[i];
			if (note.containsKey(s - k)) {
				r += note.get(s - k);
			}
			note.put(s, note.getOrDefault(s, 0) + 1);
		}
		return r;
	}

	// 1380
	public List<Integer> luckyNumbers(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		int[] t = new int[m];
		Arrays.fill(t, Integer.MAX_VALUE);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] < t[i]) {
					t[i] = j;
				}
			}
		}

		List<Integer> r = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			boolean f = true;
			for (int j = 0; j < n; j++) {
				if (matrix[j][t[i]] > matrix[i][t[i]]) {
					f = false;
					break;
				}
			}
			if (f) {
				r.add(matrix[i][t[i]]);
			}
		}

		return r;

	}

	// 2091
	public int minimumDeletions(int[] nums) {
		int max = nums[0], i = 0;
		int min = nums[0], j = 0;
		for (int k = 1; k < nums.length; k++) {
			if (nums[k] < min) {
				min = nums[k];
				j = k;
			} else if (nums[k] > max) {
				max = nums[k];
				i = k;
			}
		}
		if (i > j) {
			int t = i;
			i = j;
			j = t;
		}
		int r = j + 1;
		r = Math.min(r, nums.length - i);
		r = Math.min(r, i + 1 + nums.length - j);
		return r;

	}

	// 258
	public int addDigits(int num) {
		if (num > 9) {
			num = num % 9;
			if (num == 0) {
				num = 9;
			}
		}
		return num;
	}

	// 459
	public boolean repeatedSubstringPattern(String s) {
		String t = s.concat(s).substring(1, s.length() * 2 - 1);
		return t.contains(s);

	}

	// 1911
	public long maxAlternatingSum(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		long r = 0;
		long t = 0;
		boolean f = true;
		for (int i = 0; i < nums.length; i++) {
			if ((f && i == 0 && nums[0] >= nums[1]) || (f && i > 0 && i < nums.length - 1 && nums[i] >= nums[i - 1] && nums[i] >= nums[i + 1])
					|| (f && i == nums.length - 1 && nums[i] > nums[i - 1])) {
				r += (long) nums[i];
				f = false;
			}
			if (!f && i > 0 && i < nums.length - 1 && nums[i] <= nums[i - 1] && nums[i] <= nums[i + 1]) {
				r -= (long) nums[i];
				f = true;
				t = (long) nums[i];
			}
		}
		if (f) {
			r += t;
		}
		return r;
	}

	// 1047
	public String removeDuplicates(String s) {
		int now = s.length();
		int next = 1;
		while (now != next) {
			now = s.length();
			s = s.replace("aa", "").replace("bb", "").replace("cc", "").replace("dd", "").replace("ee", "").replace("ff", "").replace("gg", "")
					.replace("hh", "").replace("ii", "").replace("jj", "").replace("kk", "").replace("ll", "").replace("mm", "").replace("nn", "")
					.replace("oo", "").replace("pp", "").replace("qq", "").replace("rr", "").replace("ss", "").replace("tt", "").replace("uu", "")
					.replace("vv", "").replace("ww", "").replace("xx", "").replace("yy", "").replace("zz", "");
			next = s.length();

		}
		return s;
	}

	// 1871
	public boolean canReach(String s, int minJump, int maxJump) {
		char[] arr = s.toCharArray();
		int[] r = new int[arr.length];
		r[0] = 1;
		for (int i = 1; i < r.length; i++) {
			if (arr[i] != '1') {
				int min = Math.max(0, i - maxJump);
				int max = Math.min(r.length - 1, i - minJump);
				boolean t = true;
				for (int j = min; j <= max; j++) {
					if (arr[j] == '0') {
						r[i] = 1;
						t = false;
						break;
					}
				}
				if (t) {
					arr[i] = '1';
				}
			}
		}
		return r[r.length - 1] == 1;
	}

	// 1572
	public int diagonalSum(int[][] mat) {
		int r = 0;
		int len = mat.length;
		for (int i = 0; i < len; i++) {
			r += mat[i][i];
			r += mat[i][len - i - 1];
		}
		if (len % 2 == 1) {
			r -= mat[len / 2][len / 2];
		}
		return r;
	}

	// 1688
	public int numberOfMatches(int n) {
		int r = 0;
		while (n != 1) {
			if (n % 2 == 1) {
				n = n / 2;
				r += n;
				n += 1;
			} else {
				n = n / 2;
				r += n;
			}
		}
		return r;
	}

	// 2467
	public int[] divingBoard(int shorter, int longer, int k) {
		if (k == 0) {
			return new int[] { 0 };
		}
		if (shorter == longer) {
			return new int[] { shorter * k };
		}
		int[] r = new int[k + 1];
		for (int i = k; i >= 0; i--) {
			r[k - i] = shorter * i + longer * (k - i);
		}
		return r;
	}

	// 925
	public boolean isLongPressedName(String name, String typed) {
		if (typed.length() < name.length()) {
			return false;
		}
		if (name.length() == 0) {
			return true;
		} else if (typed.length() == 0) {
			return false;
		}
		int i = 1, j = 1;
		char[] arr = name.toCharArray();
		char[] trr = typed.toCharArray();
		char a = arr[0];
		if (a != trr[0]) {
			return false;
		}
		while (i < name.length()) {
			if (j == trr.length) {
				return false;
			}
			if (arr[i] != trr[j]) {
				if (trr[j] == a) {
					j++;
				} else {
					return false;
				}
			} else {
				a = arr[i];
				i++;
				j++;
			}
		}
		for (int k = j; k < trr.length; k++) {
			if (trr[k] != a) {
				return false;
			}
		}

		return true;
	}

	// 921
	public int minAddToMakeValid(String s) {
		char[] arr = s.toCharArray();
		int r = 0, t = 0;
		for (Character c : arr) {
			if (c == '(') {
				t++;
			} else {
				if (t > 0) {
					t--;
				} else {
					r++;
				}
			}
		}
		r += t;
		return r;
	}

	// 1345
	public int minJumps(int[] arr) {
		if (arr.length == 1) {
			return 0;
		}

		// 先遍历一次，记录每个数值出现的位置 map<数值,出现的位置集合>
		Map<Integer, List<Integer>> note = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (note.containsKey(arr[i])) {
				note.get(arr[i]).add(i);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				note.put(arr[i], list);
			}
		}

		// 记录抵达每一个位置的最小步数，并初始化为int.max，最后一个位置为0
		int[] steps = new int[arr.length];
		Arrays.fill(steps, Integer.MAX_VALUE);
		steps[arr.length - 1] = 0;

		// 从最后一个位置开始，计算每一步可以达到的位置，记录于temp<位置索引，位置数值>
		int r = 1;
		Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
		temp.put(arr.length - 1, arr[arr.length - 1]);

		// 每一次循环更新当前步能到达的位置temp，步数r++，并判断steps[0]是否有被更新，有的话及时返回结果
		while (true) {
			// 新的记录，在一次循环之后替代temp
			Map<Integer, Integer> m = new HashMap<Integer, Integer>();

			for (int i : temp.keySet()) {
				int j = temp.get(i);

				// 找当前位置前后的位置
				if (i + 1 < arr.length && r < steps[i + 1]) {
					steps[i + 1] = r;
					m.put(i + 1, arr[i + 1]);
				}
				if (i - 1 >= 0 && r < steps[i - 1]) {
					steps[i - 1] = r;
					m.put(i - 1, arr[i - 1]);
				}

				// 找当前位置数值相同的位置
				List<Integer> list = note.get(j);
				for (int k : list) {
					if (k != i && r < steps[k]) {
						steps[k] = r;
						m.put(k, arr[k]);
					}
				}

				// 返回结果
				if (steps[0] < Integer.MAX_VALUE) {
					return steps[0];
				}

			}
			// 更新记录及步数，进入下一次循环
			temp = m;
			r++;
		}
	}

	// 1387
	public int getKth(int lo, int hi, int k) {
		List<int[]> res = new ArrayList<int[]>();
		Map<Integer, Integer> note = new HashMap<>();

		for (int i = lo; i <= hi; i++) {
			res.add(new int[] { i, f1387(note, i) });
		}

		Collections.sort(res, (a, b) -> {
			if (a[1] != b[1]) {
				return a[1] - b[1];
			} else {
				return a[0] - b[0];
			}
		});

		System.out.println(f1387(note, 14));
		return res.get(k)[0];
	}

	private int f1387(Map<Integer, Integer> note, int n) {
		if (n == 1) {
			return 0;
		}
		if (note.containsKey(n)) {
			return note.get(n);
		}
		int next;
		if (n % 2 == 1) {
			next = n * 3 + 1;
		} else {
			next = n / 2;
		}
		int t = 1 + f1387(note, next);
		note.put(n, t);
		return t;

	}

	// 2086
	public int minimumBuckets(String street) {
		if (street.contains("HHH") || street.startsWith("HH") || street.endsWith("HH") || street.equals("H")) {
			return -1;
		}
		int r = 0;
		int n = street.length();
		street = street.replace("H.H", "");
		if (n != street.length()) {
			r += (n - street.length()) / 3;
			n = street.length();
		}
		System.out.println(r);
		street = street.replace("H", "");
		r += n - street.length();
		return r;
	}

	// 1608
	public int specialArray(int[] nums) {
		if (nums.length == 1) {
			if (nums[0] >= 1) {
				return 1;
			} else {
				return -1;
			}
		}
		Arrays.sort(nums);
		int n = 0;
		for (int i = 0; i < nums.length; i++) {
			int t = nums.length - i;
			if (nums[i] >= t && t > n) {
				return t;
			}
			n = nums[i];
		}
		return -1;
	}

	// 2474
	public int missingNumber(int[] nums) {
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			r ^= i + 1;
			r ^= nums[i];
		}
		return r;
	}

	// 539
	public int findMinDifference(List<String> timePoints) {
		int[] arr = new int[timePoints.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(timePoints.get(i).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(i).substring(3));
		}
		Arrays.sort(arr);
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			min = Math.min(min, arr[i] - arr[i - 1]);
		}
		return Math.min(min, arr[0] + 1440 - arr[arr.length - 1]);
	}

	// 1220
	public int countVowelPermutation(int n) {
		int mod = 1000000007;
		long a = 1, e = 1, i = 1, o = 1, u = 1;
		for (int j = 2; j <= n; j++) {
			long an = (e + i + u) % mod;
			long en = (a + i) % mod;
			long in = (e + o) % mod;
			long on = i;
			long un = (i + o) % mod;
			a = an;
			e = en;
			i = in;
			o = on;
			u = un;
		}
		return (int) ((a + e + i + o + u) % mod);
	}

	// 373
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		int i = 1;
		int j = 1;
		int a = nums1[0];
		int b = nums2[0];
		List<List<Integer>> r = new ArrayList<>();
		ArrayList<Integer> f = new ArrayList<Integer>();
		f.add(a);
		f.add(b);
		r.add(f);
		k--;
		while (k > 0) {
			int m = nums1[i];
			int n = nums2[j];
			if (m > n) {

			}
		}
		return r;
	}

	// 462
	public int minMoves2(int[] nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println(sum);
		System.out.println(nums.length);
		int t = (int) Math.round(sum * 1.0 / nums.length);
		System.out.println(t);
		int r = 0;
		for (int i : nums) {
			r += Math.abs(t - i);
		}
		return r;
	}

	// 41
	public int firstMissingPositive(int[] nums) {

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < nums.length && nums[i] > 0) {
				int t = nums[nums[i]];
				nums[nums[i] - 1] = i;
				nums[i] = t;
			}
		}

		for (int i = 1; i < nums.length; i++) {
			if (i + 1 != nums[i]) {
				return i;
			}
		}
		return nums.length;
	}

	// 747
	public int dominantIndex(int[] nums) {
		if (nums.length == 1) {
			return 0;
		}
		int a = nums[0], b = nums[1];
		int t = 0;
		if (a < b) {
			a = nums[1];
			b = nums[0];
			t = 1;
		}
		for (int i = 2; i < nums.length; i++) {
			if (nums[i] > a) {
				b = a;
				a = nums[i];
				t = i;
			} else if (nums[i] > b) {
				b = nums[i];
			}
		}
		if (a >= b * 2) {
			return t;
		} else {
			return -1;
		}

	}

	// 1614
	public int maxDepth(String s) {
		int r = 0;
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.add(c);
				r = Math.max(r, stack.size());
			} else if (c == ')') {
				stack.pop();
			}
		}
		return r;
	}

	// 1576
	public String modifyString(String s) {
		char[] arr = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '?') {
				char a = 'a';
				while ((i > 0 && arr[i - 1] == a) || (i < s.length() - 1 && arr[i + 1] == a)) {
					a++;
				}
				arr[i] = a;
			}
		}
		return new String(arr);
	}

	// 2402
	private TreeNode tn2402 = null;
	private boolean b2402 = false;

	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		f2402(root, p);
		return tn2402;
	}

	private void f2402(TreeNode root, TreeNode p) {
		if (b2402 == true) {
			tn2402 = root;
			return;
		} else if (root != null) {
			if (root.left != null) {
				f2402(root.left, p);
			}
			if (b2402 == true) {
				tn2402 = root;
				return;
			}
			if (root == p) {
				b2402 = true;
			}
			if (root.right != null) {
				f2402(root.right, p);
			}
		}
	}

	// 1967
	public int numOfStrings(String[] patterns, String word) {
		int r = 0;
		for (String s : patterns) {
			if (s.length() > word.length()) {
				continue;
			}
			if (word.contains(s)) {
				r++;
			}
		}
		return r;
	}

	// 1680
	public int concatenatedBinary(int n) {
		long t = 1;
		for (int i = 2; i <= n; i++) {
			t = (t << Integer.toBinaryString(i).length()) + i;
			t = t % 1000000009;
		}
		return (int) t;
	}

	// 2099
	public int[] maxSubsequence(int[] nums, int k) {
		if (nums.length < k) {
			return nums;
		}
		PriorityQueue<Integer> note = new PriorityQueue<>();
		Deque<Integer> q = new ArrayDeque<Integer>(k);
		for (int i = 0; i < k; i++) {
			q.offer(nums[i]);
			note.offer(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			if (nums[i] > note.peek()) {
				q.remove(note.peek());
				note.poll();
				q.offer(nums[i]);
				note.offer(nums[i]);
			}
		}
		int[] r = new int[k];
		for (int i = 0; i < r.length; i++) {
			r[i] = q.pollFirst();
		}
		return r;
	}

	// 1418
	public List<List<String>> displayTable(List<List<String>> orders) {
		List<String> foods = new ArrayList<String>();
		Map<String, Map<String, Integer>> note = new HashMap<String, Map<String, Integer>>();

		for (int i = 0; i < orders.size(); i++) {
			List<String> t = orders.get(i);
			String table = t.get(1);
			String food = t.get(2);
			if (!foods.contains(food)) {
				foods.add(food);
			}
			if (note.containsKey(table)) {
				Map<String, Integer> m = note.get(table);
				m.put(food, m.getOrDefault(food, 0) + 1);
			} else {
				Map<String, Integer> m = new HashMap<String, Integer>();
				m.put(food, 1);
				note.put(table, m);
			}

		}

		Collections.sort(foods);
		List<List<String>> r = new ArrayList<>();
		List<String> head = new ArrayList<String>();
		head.add("Table");
		for (String s : foods) {
			head.add(s);
		}
		for (String s : note.keySet()) {
			List<String> line = new ArrayList<String>();
			line.add(s);

			Map<String, Integer> m = note.get(s);
			for (int i = 0; i < foods.size(); i++) {
				if (m.containsKey(foods.get(i))) {
					line.add(m.get(foods.get(i)) + "");
				} else {
					line.add(0 + "");
				}
			}
			r.add(line);
		}
		r.sort((a, b) -> {
			return Integer.valueOf(a.get(0)) - Integer.valueOf(b.get(0));
		});
		r.add(0, head);

		return r;
	}

	// 1392
	public String longestPrefix(String s) {
		int len = s.length();
		if (len == 1) {
			return "";
		}
		for (int i = 1; i < len; i++) {
			String t = s.substring(0, len - i);
			if (s.endsWith(t)) {
				return t;
			}
		}
		return "";
	}

	// 686
	public int repeatedStringMatch(String a, String b) {
		int r = 1;
		String t = a;
		int len = a.length() * 2 + b.length();
		while (t.length() < len) {
			if (t.contains(b)) {
				return r;
			} else {
				r++;
				t += a;
			}
		}
		return -1;
	}

	// 1154
	public int dayOfYear(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8));

		int[] amount = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			++amount[1];
		}

		int ans = 0;
		for (int i = 0; i < month - 1; ++i) {
			ans += amount[i];
		}
		return ans + day;

	}

	// 97
	public boolean isInterleave(String s1, String s2, String s3) {
		int m = s1.length(), n = s2.length();
		if (s3.length() != m + n)
			return false;
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++)
			dp[i][0] = true;
		for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++)
			dp[0][j] = true;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1)) || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
			}
		}
		return dp[m][n];
	}

	int v968 = 0;

	public int minCameraCover(TreeNode root) {
		return f968(root) == 0 ? v968 + 1 : v968;
	}

	public int f968(TreeNode root) {
		if (root == null)
			return 2;
		int left = f968(root.left);
		int right = f968(root.right);
		if (left == 0 || right == 0) {
			v968++;
			return 1;
		}
		if (left == 1 || right == 1) {
			return 2;
		}
		return 0;
	}

	// 230
	public int kthSmallest(TreeNode root, int k) {
		Stack<Integer> stack = new Stack<>();
		f230(root, stack, k);
		return stack.peek();
	}

	private void f230(TreeNode root, Stack<Integer> stack, int k) {
		if (root == null) {
			return;
		}
		f230(root.left, stack, k);
		if (stack.size() == k) {
			return;
		}
		stack.push(root.val);
		f230(root.right, stack, k);
	}

	// 456
	public boolean find132pattern(int[] nums) {
		if (nums.length < 3) {
			return false;
		}
		int min = nums[0];
		int mi = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < min) {
				min = nums[i];
				mi = i;
			} else if (nums[i] > min + 1) {
				int t = min;
				int ti = mi;
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[j] > min & nums[j] < nums[i]) {
						return true;
					} else if (nums[j] < min) {
						t = nums[j];
						ti = j;
					} else if (nums[j] > nums[i] && ti > mi) {
						i = j;
						mi = ti;
						min = t;
					}

				}
			}
		}

		return false;
	}

	// 509
	public int fib_(int n) {
		if (n == 0) {
			return 0;
		}
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		return dp[n];
	}

	// 807
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int len = grid.length;
		int[] v = new int[len], h = new int[len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				h[i] = Math.max(h[i], grid[i][j]);
				v[j] = Math.max(v[j], grid[j][i]);
			}
		}

		int r = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				r += Math.min(h[i], v[j]) - grid[i][j];
			}
		}

		return r;

	}

	// 811
	public List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> note = new HashMap<String, Integer>();
		for (String s : cpdomains) {
			String[] data = s.split(" ");
			Integer times = Integer.valueOf(data[0]);

			String addr = data[1];
			note.put(addr, note.getOrDefault(addr, 0) + times);
			while (addr.indexOf(".") > -1) {
				addr = addr.substring(addr.indexOf(".") + 1, addr.length());

			}

		}
		List<String> r = new ArrayList<>();
		return r;
	}

	// 47
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> r = new ArrayList<>();
		ArrayList<Integer> s = new ArrayList<>();
		f47(r, new LinkedList<>(), nums, s);
		return r;
	}

	private void f47(List<List<Integer>> r, LinkedList<Integer> p, int[] nums, ArrayList<Integer> s) {
		if (p.size() == nums.length) {
			r.add(new ArrayList<>(p));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (s.contains(i)) {
				continue;
			}
			LinkedList<Integer> t = new LinkedList<>(p);
			ArrayList<Integer> ts = new ArrayList<>(s);
			t.offer(nums[i]);
			ts.add(i);
			f47(r, p, nums, ts);
			t.removeLast();
			ts.remove(Integer.valueOf(i));
		}
	}

	// 18
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		Set<String> note = new HashSet<String>();
		List<List<Integer>> r = new ArrayList<>();
		f18(r, note, new LinkedList<>(), nums, 0, 4, target);
		return r;
	}

	private void f18(List<List<Integer>> r, Set<String> note, LinkedList<Integer> p, int[] nums, int i, int k, int target) {
		if (k == 0) {
			if (target == 0 && !note.contains(p.toString())) {
				r.add(new ArrayList<>(p));
				note.add(p.toString());
			}
			return;
		}

		for (int j = i; j < nums.length - k; j++) {
			LinkedList<Integer> t = new LinkedList<>(p);
			t.add(nums[j]);
			f18(r, note, t, nums, j + 1, k - 1, target - nums[j]);
			t.removeLast();
		}
	}

	// 150
	public int evalRPN(String[] tokens) {
		Stack<Integer> s = new Stack<>();
		for (String i : tokens) {
			if (i.equals("+")) {
				int b = s.pop();
				int a = s.pop();
				Integer r = a + b;
				s.push(r);
			} else if (i.equals("-")) {
				int b = s.pop();
				int a = s.pop();
				Integer r = a - b;
				s.push(r);
			} else if (i.equals("*")) {
				int b = s.pop();
				int a = s.pop();
				Integer r = a * b;
				s.push(r);
			} else if (i.equals("/")) {
				int b = s.pop();
				int a = s.pop();
				Integer r = a / b;
				s.push(r);
			} else {
				s.push(Integer.valueOf(i));
			}
		}
		return s.pop();
	}

	// 2421
	public int[] findSwapValues(int[] array1, int[] array2) {
		Set<Integer> note2 = new HashSet<Integer>();
		int s1 = 0, s2 = 0;
		for (Integer i : array1) {
			s1 += i;
		}
		for (Integer i : array2) {
			s2 += i;
			note2.add(i);
		}
		if (s1 == s2) {
			return new int[0];
		}
		int t = s2 - s1;
		if ((t & 1) == 1) {
			return new int[0];
		}

		t = t / 2;
		for (int i = 0; i < array1.length; i++) {
			if (note2.contains(array1[i] + t)) {
				return new int[] { array1[i], array1[i] + t };
			}
		}

		return new int[0];
	}

	// 794
	public boolean validTicTacToe(String[] board) {
		int x = 0, o = 0, sx = 0, so = 0;

		for (int i = 0; i < board.length; i++) {

			if (board[i].equals("XXX")) {
				sx++;
			}
			if (board[i].equals("OOO")) {
				so++;
			}
			for (int j = 0; j < 3; j++) {
				if (board[i].charAt(j) == 'X') {
					x++;
				} else if (board[i].charAt(j) == 'O') {
					o++;
				}
			}
		}
		if (o > x || x > o + 1) {
			return false;
		}
		if (so > 0 && sx > 0) {
			return false;
		}
		String[] t = new String[5];
		t[0] = "" + board[0].charAt(0) + board[1].charAt(0) + board[2].charAt(0);
		t[1] = "" + board[0].charAt(1) + board[1].charAt(1) + board[2].charAt(1);
		t[2] = "" + board[0].charAt(2) + board[1].charAt(2) + board[2].charAt(2);
		t[3] = "" + board[0].charAt(0) + board[1].charAt(1) + board[2].charAt(2);
		t[4] = "" + board[0].charAt(2) + board[1].charAt(1) + board[2].charAt(0);
		for (int i = 0; i < t.length; i++) {
			if (t[i].equals("XXX")) {
				sx++;
			}
			if (t[i].equals("OOO")) {
				so++;
			}
		}
		if (so > 0 && sx > 0) {
			return false;
		}
		if (so > 0 && x != o) {
			return false;
		}
		if (sx > 0 && x != o + 1) {
			return false;
		}

		return true;
	}

	// 260
	public int[] singleNumber260(int[] nums) {
		int s = 0;
		for (int i : nums) {
			s ^= i;
		}
		int t = 1;
		while ((s & t) != 1) {
			t <<= 1;
		}
		int a = 0, b = 0;
		for (int i : nums) {
			if ((i & t) == 0) {
				a ^= i;
			} else {
				b ^= i;
			}
		}
		return new int[] { a, b };
	}

	// 2196
	public char firstUniqChar2196(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.lastIndexOf(s.charAt(i)) == s.indexOf(s.charAt(i))) {
				return s.charAt(i);
			}
		}
		return ' ';
	}

	// 137
	public int singleNumber137(int[] nums) {
		Set<Integer> note = new HashSet<>();
		Set<Integer> t = new HashSet<>();
		for (Integer i : nums) {
			if (note.contains(i)) {
				continue;
			} else {
				if (t.contains(i)) {
					t.remove(i);
					note.add(i);
				} else {
					t.add(i);
				}
			}
		}
		return (int) t.toArray()[0];
	}

	// 689
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		// 前缀和
		int[] sum = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			sum[i + 1] = sum[i] + nums[i];
		}
		// 组合和
		int[] n = new int[nums.length - k + 1];
		for (int i = 0; i < n.length; i++) {
			n[i] = sum[i + k] - sum[i];
		}
		int a = 0, b = k, c = 2 * k;
		int s = n[a] + n[b] + n[c];
		for (int i = 0; i < n.length; i++) {
			for (int j = i + k; j < n.length; j++) {
				for (int l = j + k; l < n.length; l++) {
					int t = n[i] + n[j] + n[l];
					if (n[i] + n[j] + n[l] > s) {
						a = i;
						b = j;
						c = l;
						s = t;
					}
				}
			}
		}
		return new int[] { a, b, c };

	}

	// 216
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> r = new ArrayList<>();
		f216(r, new LinkedList<>(), 1, k, n);
		return r;
	}

	private void f216(List<List<Integer>> r, LinkedList<Integer> p, int i, int k, int t) {
		if (k == 0 && t == 0) {
			r.add(new ArrayList<>(p));
			return;
		}
		if (t < 0 || k <= 0) {
			return;
		}

		for (int j = i; j <= 9 && j <= t; j++) {
			LinkedList<Integer> list = new LinkedList<>(p);
			list.add(j);
			f216(r, list, j + 1, k - 1, t - j);
			list.removeLast();
		}
	}

	// 40
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		f40(r, new LinkedList<>(), candidates, 0, target);
		return r;
	}

	private void f40(List<List<Integer>> r, LinkedList<Integer> l, int[] n, int i, int t) {
		if (t == 0) {
			r.add(new LinkedList<Integer>(l));
			return;
		}
		if (i >= n.length || n[i] > t) {
			return;
		}
		if (n[i] == t) {
			LinkedList<Integer> list = new LinkedList<Integer>(l);
			list.add(n[i]);
			r.add(list);
			return;
		}

		Integer m = null;
		for (int j = i; j < n.length; j++) {
			LinkedList<Integer> list = new LinkedList<>(l);
			if ((m == null || n[j] != m) && n[j] <= t) {
				list.add(n[j]);
				f40(r, list, n, j + 1, t - n[j]);
				list.removeLast();
				m = n[j];
			}
		}

	}

	// 39
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		f39(r, new LinkedList<>(), candidates, 0, target);
		return r;
	}

	private void f39(List<List<Integer>> r, LinkedList<Integer> l, int[] n, int i, int t) {
		if (t == 0) {
			r.add(new LinkedList<Integer>(l));
			return;
		}
		if (i >= n.length || n[i] > t) {
			return;
		}
		if (n[i] == t) {
			LinkedList<Integer> list = new LinkedList<Integer>(l);
			list.add(n[i]);
			r.add(list);
			return;
		}

		for (int j = i; j < n.length; j++) {
			LinkedList<Integer> list = new LinkedList<>(l);
			list.add(n[j]);
			f39(r, list, n, j, t - n[j]);
			list.removeLast();
		}

	}

	// 190
	public int reverseBits(int n) {
//		return Integer.reverse(n);
		int r = 0;
		for (int i = 0; i < 32; i++) {
			r = (r << 1) + (n & 1);
			n >>= 1;
		}
		return r;
	}

	// 191
	public int hammingWeight(int n) {
//		Integer.bitCount(n);
		int count = 0;
		for (int i = 1; i <= 32; i++) {
			count += n & 1;
			n >>= 1;
		}
		return count;
	}

	// 231
	public boolean isPowerOfTwo(int n) {
		return n == 0 ? false : (n & n - 1) == 0;
	}

	// 206
	public ListNode reverseList(ListNode head) {
		ListNode a = null;
		ListNode b = head;
		while (b != null) {
			ListNode t = b.next;
			b.next = a;
			a = b;
			b = t;
		}
		return a;
	}

	// 784
	public List<String> letterCasePermutation(String s) {
		List<String> r = new ArrayList<String>();
		char[] arr = s.toCharArray();
		f784(r, new StringBuffer(), arr, 0);
		return r;
	}

	private void f784(List<String> r, StringBuffer t, char[] arr, int i) {
		if (t.length() == arr.length) {
			r.add(t.toString());
			return;
		}

		for (int j = i; j < arr.length; j++) {
			StringBuffer s = new StringBuffer(t);
			char c = arr[j];

			s.append(c);
			f784(r, s, arr, j + 1);
			s.deleteCharAt(s.length() - 1);

			if (c >= 'a' && c <= 'z') {
				s = s.append(Character.toUpperCase(c));
				f784(r, s, arr, j + 1);
				s.deleteCharAt(s.length() - 1);
			} else if (c >= 'A' && c <= 'Z') {
				s = s.append(Character.toLowerCase(c));
				f784(r, s, arr, j + 1);
				s.deleteCharAt(s.length() - 1);
			}
		}
	}

	// 46
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		LinkedList<Integer> n = new LinkedList<Integer>();
		for (Integer i : nums) {
			n.add(i);
		}
		f46(r, n, new LinkedList<>());
		return r;
	}

	private void f46(List<List<Integer>> r, LinkedList<Integer> n, LinkedList<Integer> t) {
		if (n.size() == 0) {
			r.add(new ArrayList<>(t));
			return;
		}

		for (int i = 0; i < n.size(); i++) {
			LinkedList<Integer> l = new LinkedList<>(t);
			Integer s = n.remove(i);
			l.add(s);
			f46(r, n, l);
			n.add(i, s);
		}
	}

	// 77
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		f77(r, new LinkedList<>(), 1, n, k);
		return r;
	}

	private void f77(List<List<Integer>> r, List<Integer> t, int s, int n, int k) {
		if (t != null && t.size() == k) {
			r.add(new ArrayList<>(t));
			System.out.println(r.get(r.size() - 1));
			return;
		}

		for (int i = s; i <= n + 1 - (k - t.size()); i++) {
			LinkedList<Integer> l = new LinkedList<Integer>(t);
			l.add(i);
			f77(r, l, i + 1, n, k);
			l.removeLast();
		}

	}

	// 994
	public int orangesRotting(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int s = 0, r = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					s++;
				} else if (grid[i][j] == 2) {
					q.offer(new int[] { i, j });
				}
			}
		}

		while (!q.isEmpty() && s > 0) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] t = q.poll();
				if (t[0] - 1 >= 0 && grid[t[0] - 1][t[1]] == 1) {
					grid[t[0] - 1][t[1]] = 2;
					s--;
					q.offer(new int[] { t[0] - 1, t[1] });
				}
				if (t[0] + 1 < n && grid[t[0] + 1][t[1]] == 1) {
					grid[t[0] + 1][t[1]] = 2;
					s--;
					q.offer(new int[] { t[0] + 1, t[1] });
				}
				if (t[1] - 1 >= 0 && grid[t[0]][t[1] - 1] == 1) {
					grid[t[0]][t[1] - 1] = 2;
					s--;
					q.offer(new int[] { t[0], t[1] - 1 });
				}
				if (t[1] + 1 < m && grid[t[0]][t[1] + 1] == 1) {
					grid[t[0]][t[1] + 1] = 2;
					s--;
					q.offer(new int[] { t[0], t[1] + 1 });
				}
			}
			r++;
		}
		if (s > 0) {
			return -1;
		} else {
			return r;
		}
	}

	// 136
	public int singleNumber(int[] nums) {
		int r = 0;
		for (int i : nums) {
			r ^= i;
		}
		return r;
	}

	// 542
	public int[][] updateMatrix(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		int s = n + m;
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == 0) {
					q.offer(new int[] { i, j });
				} else {
					mat[i][j] = s;
				}
			}
		}
		while (q.size() > 0) {
			int[] t = q.poll();
			if (t[0] - 1 >= 0 && mat[t[0] - 1][t[1]] > mat[t[0]][t[1]] + 1) {
				mat[t[0] - 1][t[1]] = mat[t[0]][t[1]] + 1;
				q.offer(new int[] { t[0] - 1, t[1] });
			}
			if (t[0] + 1 < n && mat[t[0] + 1][t[1]] > mat[t[0]][t[1]] + 1) {
				mat[t[0] + 1][t[1]] = mat[t[0]][t[1]] + 1;
				q.offer(new int[] { t[0] + 1, t[1] });
			}
			if (t[1] - 1 >= 0 && mat[t[0]][t[1] - 1] > mat[t[0]][t[1]] + 1) {
				mat[t[0]][t[1] - 1] = mat[t[0]][t[1]] + 1;
				q.offer(new int[] { t[0], t[1] - 1 });
			}
			if (t[1] + 1 < m && mat[t[0]][t[1] + 1] > mat[t[0]][t[1]] + 1) {
				mat[t[0]][t[1] + 1] = mat[t[0]][t[1]] + 1;
				q.offer(new int[] { t[0], t[1] + 1 });
			}
			for (int[] i : mat) {
				System.out.print(Arrays.toString(i));
			}
			System.out.println();
		}
		return mat;
	}

	// 617
	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		TreeNode root = new TreeNode();
		if (root1 == null && root2 == null) {
			return null;
		} else if (root1 == null) {
			root.val = root2.val;
			root.left = mergeTrees(null, root2.left);
			root.right = mergeTrees(null, root2.right);
		} else if (root2 == null) {
			root.val = root1.val;
			root.left = mergeTrees(root1.left, null);
			root.right = mergeTrees(root1.right, null);
		} else {
			root.val = root1.val + root2.val;
			root.left = mergeTrees(root1.left, root2.left);
			root.right = mergeTrees(root1.right, root2.right);
		}
		return root;

	}

	// 1792
	public double maxAverageRatio(int[][] classes, int extraStudents) {
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
			double avg1 = (o1[0] + 0.0) / o1[1];
			double avg2 = (o2[0] + 0.0) / o2[1];

			double avg1_add = (o1[0] + 1.0) / (o1[1] + 1.0);
			double avg2_add = (o2[0] + 1.0) / (o2[1] + 1.0);

			double res = Double.compare(avg1_add - avg1, avg2_add - avg2);
			if (res > 0) {
				return -1;
			} else {
				return 1;
			}
		});
		for (int i = 0; i < classes.length; i++) {
			q.offer(classes[i]);
		}
		while (extraStudents > 0) {
			int[] t = q.poll();
			t[0]++;
			t[1]++;
			q.offer(t);
			extraStudents--;
		}
		int i = 0;
		double s = 0;
		while (!q.isEmpty()) {
			int[] r = q.poll();
			System.out.println(Arrays.toString(r));
			s += r[0] * 1.0d / r[1];
			i++;
		}
		return s / i;
	}

	// 1408
	public List<String> stringMatching(String[] words) {
		List<String> r = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			for (int j = 0; j < words.length; j++) {
				if (i != j && words[j].contains(s)) {
					r.add(s);
					break;
				}
			}
		}
		return r;
	}

	// 2312
	public int rob(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}
		return dp[dp.length - 1];
	}

	// 659
	public boolean isPossible(int[] nums) {
		int[] ns = new int[10001];
		for (int i : nums) {
			ns[i]++;
		}
		List<Integer> note = new ArrayList<Integer>();
		for (int i = 0; i < ns.length; i++) {
			if (ns[i] == 0) {
				continue;
			}
			while (ns[i] > 0) {
				if (note.contains(i - 1)) {
					note.remove(Integer.valueOf(i - 1));
					note.add(i);
					ns[i]--;
				} else {
					if (ns[i] > 0 && ns[i + 1] > 0 && ns[i + 2] > 0) {
						ns[i]--;
						ns[i + 1]--;
						ns[i + 2]--;
						note.add(i + 2);
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	// 1497
	public boolean canArrange(int[] arr, int k) {
		int[] note = new int[k + 1];
		for (int i : arr) {
			int t = (arr[i] % k + k) % k;
			note[t] = note[t] + 1;
		}
		for (int i = 1; i < note.length; i++) {
			if (note[k - i] != note[i]) {
				return false;
			}
		}
		return note[0] % 2 == 0;

//		Map<Integer, Integer> note = new HashMap<>();
//		for (int i = 0; i < arr.length; i++) {
//			int t = arr[i] % k;
//			int n = (k - t) % k;
//			if (note.containsKey(n)) {
//				Integer m = note.get(n);
//				if (m == 1) {
//					note.remove(n);
//				} else {
//					note.put(n, m - 1);
//				}
//			} else if (note.containsKey(n - k)) {
//				Integer m = note.get(n - k);
//				if (m == 1) {
//					note.remove(n - k);
//				} else {
//					note.put(n - k,  m - 1);
//				}
//			} else {
//				if (note.containsKey(t)) {
//					note.put(t, note.get(t) + 1);
//				} else {
//					note.put(t, 1);
//				}
//			}
//		}
//		return note.size() == 0;
	}

	// 1816
	public String truncateSentence(String s, int k) {
		String[] arr = s.split(" ");
		String[] srr = Arrays.copyOfRange(arr, 0, k);
		String r = String.join(" ", srr);
		return r;
	}

	// 1584
	public int minCostConnectPoints(int[][] points) {
		return 0;
	}

	// 2095
	public boolean robot(String command, int[][] obstacles, int x, int y) {
		Set<String> note = new HashSet<>();
		for (int i = 0; i < obstacles.length; i++) {
			if (obstacles[i][0] < x && obstacles[i][1] < y) {
				note.add(obstacles[i][0] + "," + obstacles[i][1]);
			}
		}
		int i = 0;
		int len = command.length();
		int a = 0, b = 0;
		while (true) {
			char c = command.charAt(i % len);
			if (c == 'U') {
				b++;
			} else {
				a++;
			}
			i++;
			System.out.println(a + "," + b);
			if (note.contains(a + "," + b)) {
				return false;
			} else if (b > y || a > x) {
				return false;
			} else if (a == x && b == y) {
				return true;
			} else {
				continue;
			}
		}
	}

	// 1005
	public int largestSumAfterKNegations(int[] nums, int k) {
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0 && k > 0) {
				nums[i] *= -1;
				k--;
			} else {
				break;
			}
		}
		Arrays.sort(nums);
		if (k % 2 == 1) {
			nums[0] *= -1;
		}
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			r += nums[i];
		}
		return r;
	}

	// 1539
	public int findKthPositive(int[] arr, int k) {
		int t = 0;
		int s = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > t + 1) {
				s += arr[i] - t - 1;
			}
			t = arr[i];
			if (s >= k) {
				break;
			}
		}
		if (s >= k) {
			return t - (s - k) - 1;
		} else {
			return t - (s - k);
		}

	}

	// 506
	public String[] findRelativeRanks(int[] score) {
		int n = score.length;
		String[] desc = { "Gold Medal", "Silver Medal", "Bronze Medal" };
		int[][] arr = new int[n][2];

		for (int i = 0; i < n; ++i) {
			arr[i][0] = score[i];
			arr[i][1] = i;
		}
		Arrays.sort(arr, (a, b) -> b[0] - a[0]);
		String[] r = new String[n];
		for (int i = 0; i < n; ++i) {
			if (i >= 3) {
				r[arr[i][1]] = Integer.toString(i + 1);
			} else {
				r[arr[i][1]] = desc[i];
			}
		}
		return r;

	}

	// 2086
	public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
		int a1 = startPos[0], a2 = homePos[0], b1 = startPos[1], b2 = homePos[1];
		int count = 0;
		if (a1 < a2) {
			for (int i = a1 + 1; i <= a2; i++)
				count += rowCosts[i];
		} else if (a1 > a2) {
			for (int i = a1 - 1; i >= a2; i--)
				count += rowCosts[i];
		}
		if (b1 < b2) {
			for (int i = b1 + 1; i <= b2; i++)
				count += colCosts[i];
		} else if (b1 > b2) {
			for (int i = b1 - 1; i >= b2; i--)
				count += colCosts[i];
		}
		return count;
	}

	// 554
	public int leastBricks(List<List<Integer>> wall) {
		Map<Integer, Integer> note = new HashMap<Integer, Integer>();
		for (int i = 0; i < wall.size(); i++) {
			List<Integer> ws = wall.get(i);
			int t = 0;
			for (int j = 0; j < ws.size() - 1; j++) {
				t += ws.get(j);
				if (note.containsKey(t)) {
					note.put(t, note.get(t) + 1);
				} else {
					note.put(t, 1);
				}
			}
		}
		int max = 0;
		for (Integer m : note.values()) {
			max = Math.max(max, m);
		}
		return wall.size() - max;
	}

	// 1695
	public int maximumUniqueSubarray(int[] nums) {
		int[] note = new int[10001];
		int[] sum = new int[nums.length + 1];
		int r = sum[0];
		int s = 0;
		for (int i = 0; i < nums.length; i++) {
			sum[i + 1] = sum[i] + nums[i];
			s = Math.max(note[nums[i]], s);
			r = Math.max(sum[i + 1] - sum[s], r);
			note[nums[i]] = i + 1;
		}
		return r;
	}

	// 844
	public boolean backspaceCompare(String s, String t) {
		s = f844(s);
		t = f844(t);
		return s.equals(t);
	}

	private String f844(String s) {
		int i = s.indexOf("#");
		System.out.println(i);
		while (i > -1) {
			if (i == 0) {
				s = s.replaceFirst("#", "");
			} else {
				s = s.substring(0, i - 1) + s.substring(i + 1, s.length());
			}
			i = s.indexOf("#");
		}
		return s;
	}

	// 938
	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		} else if (root.val < low) {
			return rangeSumBST(root.right, low, high);
		} else if (root.val > high) {
			return rangeSumBST(root.left, low, high);
		} else {
			return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
		}
	}

	// 1446
	public int maxPower(String s) {
		int m = 1;
		int n = 1;
		char t = s.charAt(0);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == t) {
				n++;
				m = Math.max(m, n);
			} else {
				t = s.charAt(i);
				n = 1;
			}
		}
		return m;
	}

	// 486
	public boolean PredictTheWinner(int[] nums) {
		int len = nums.length;
		if (len % 2 == 0) {
			return true;
		} else {
			int a = 0;
			int b = 0;
			for (int i = 0; i < nums.length; i++) {
				if (i % 2 == 0) {
					b += nums[i];
				} else {
					a += nums[i];
				}
			}
			if (a > b) {
				return false;
			} else {
				return true;
			}
		}

	}

	// 1910
	public String removeOccurrences(String s, String part) {
		while (s.contains(part)) {
			s = s.replaceFirst(part, "");
		}
		return s;
	}

	// 2190
	public int translateNum(int num) {
		String s = num + "";
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= s.length(); i++) {
			int t = Integer.parseInt(s.substring(i - 2, i));
			if (t >= 10 && t <= 25) {
				dp[i] = dp[i - 1] + dp[i - 2];
			} else {
				dp[i] = dp[i - 1];
			}
		}
		return dp[s.length()];
	}

	// 733
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int t = image[sr][sc];
		if (t == newColor) {
			return image;
		}
		image[sr][sc] = newColor;
		if (sr > 0 && image[sr - 1][sc] == t) {
			image = floodFill(image, sr - 1, sc, newColor);
		}
		if (sr < image.length - 1 && image[sr + 1][sc] == t) {
			image = floodFill(image, sr + 1, sc, newColor);
		}
		if (sc > 0 && image[sr][sc - 1] == t) {
			image = floodFill(image, sr, sc - 1, newColor);
		}
		if (sc < image[0].length - 1 && image[sr][sc + 1] == t) {
			image = floodFill(image, sr, sc + 1, newColor);
		}
		return image;
	}

	// 567
	public boolean checkInclusion(String s1, String s2) {
		if (s2.length() < s1.length()) {
			return false;
		}
		int[] note = new int[26];
		int[] t = new int[26];
		int len = s1.length();
		for (int i = 0; i < s1.length(); i++) {
			note[s1.charAt(i) - 'a']++;
		}

		for (int i = 0; i < len; i++) {
			t[s2.charAt(i) - 'a']++;
		}
		if (Arrays.equals(note, t)) {
			return true;
		}
		int i = len;
		while (i < s2.length()) {
			t[s2.charAt(i - len) - 'a']--;
			t[s2.charAt(i) - 'a']++;
			if (Arrays.equals(note, t)) {
				return true;
			}
			i++;
		}
		return false;

	}

	// 557
	public String reverseWords(String s) {
		String[] arr = s.split(" ");
		for (int i = 0; i < arr.length; i++) {
			StringBuffer sb = new StringBuffer(arr[i]);
			arr[i] = sb.reverse().toString();
		}
		return String.join(" ", arr);
	}

	public void rotate(int[] nums, int k) {
		int[] r = new int[nums.length * 2];
		for (int i = 0; i < nums.length; i++) {
			r[i] = nums[i];
			r[i + nums.length] = nums[i];
		}
		System.out.println(Arrays.toString(r));
		for (int i = nums.length - k; i < nums.length - k + nums.length; i++) {
			nums[i - nums.length + k] = r[i];
		}
		System.out.println(Arrays.toString(nums));

	}

	// 278
	public int firstBadVersion(int n) {
		int a = 1, b = n;
		int t = (int) ((1l * a + b) / 2);
		while (a != b) {
			if (isBadVersion(t)) {
				b = t;
			} else {
				a = t + 1;
			}
			t = (int) ((1l * a + b) / 2);
		}
		return t;
	}

	boolean isBadVersion(int version) {
		if (version == 1702766719) {
			return true;
		}
		return false;
	};

	// 786
	public int[] kthSmallestPrimeFraction(int[] arr, int k) {
		List<Double> list = new ArrayList<Double>();
		Map<Double, int[]> note = new HashMap<>();
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 1; j < arr.length; j++) {
				double t = 1.0 * arr[i] / arr[j];
				list.add(t);
				note.put(t, new int[] { arr[i], arr[j] });
			}
		}
		Collections.sort(list);
		double t = list.get(k - 1);
		return note.get(t);

	}

	// 896
	public boolean isMonotonic(int[] nums) {
		int f = 0;
		int t = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (t == nums[i]) {
				continue;
			} else if (f == 1 && nums[i] > t) {
				t = nums[i];
			} else if (f == -1 && nums[i] < t) {
				t = nums[i];
			} else if (f == 0) {
				if (nums[i] > t) {
					f = 1;
				} else {
					f = -1;
				}
				t = nums[i];
			} else {
				return false;
			}
		}
		return true;
	}

	// 219
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> note = new HashSet<Integer>();
		for (int i = 0; i < k + 1 && i < nums.length; i++) {
			if (note.contains(nums[i])) {
				return true;
			}
			note.add(nums[i]);
		}
		System.out.println(note);
		for (int i = k + 1; i < nums.length; i++) {
			note.remove(nums[i - k - 1]);
			if (note.contains(nums[i])) {
				return true;
			}
			note.add(nums[i]);
		}
		return false;
	}

	public boolean canReorderDoubled(int[] arr) {
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		for (int x : arr)
			count.put(x, count.getOrDefault(x, 0) + 1);

		Integer[] arr2 = new Integer[arr.length];
		for (int i = 0; i < arr.length; ++i)
			arr2[i] = arr[i];
		Arrays.sort(arr2, Comparator.comparingInt(Math::abs));

		for (int x : arr2) {
			if (count.get(x) == 0)
				continue;
			if (count.getOrDefault(2 * x, 0) <= 0)
				return false;

			count.put(x, count.get(x) - 1);
			count.put(2 * x, count.get(2 * x) - 1);
		}

		return true;
	}

	// 438
	public List<Integer> findAnagrams(String s, String p) {

		int n = s.length();
		int m = p.length();

		char[] arr = p.toCharArray();

		Arrays.sort(arr);
		List<Integer> r = new ArrayList<Integer>();
		for (int i = 0; i < n - m; i++) {
			char[] t = s.substring(i, i + m).toCharArray();
			Arrays.sort(t);
			if (Arrays.equals(t, arr)) {
				r.add(i);
			}
		}
		return r;

	}

	// 1648(超时)
	public int maxProfit(int[] inventory, int orders) {
		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
			return o2 - o1;
		});
		int r = 0;
		for (Integer i : inventory) {
			q.add(i);
		}
		while (orders > 0) {
			Integer t = q.poll();
			r += t % 1000000007;
			q.offer(t - 1);
			orders--;
		}
		return r;
	}

	// 465
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		int t = timeSeries[0];
		int r = 0;
		for (int i = 1; i < timeSeries.length; i++) {
			if (timeSeries[i] - t >= duration) {
				r += duration;
			} else {
				r += timeSeries[i] - t;
			}
			t = timeSeries[i];
		}
		r += duration;
		return r;
	}

	// 945
	public int minIncrementForUnique(int[] nums) {

		int[] dp = new int[80000];
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			if (dp[nums[i]] == 0) {
				dp[nums[i]]++;
			} else {
				int t = 1;
				int n = nums[i] + 1;
				while (dp[n] != 0) {
					t++;
					n++;
				}
				dp[n] = 1;
				r += t;
			}
		}
		return r;
	}

	// 299
	public String getHint(String secret, String guess) {
		char[] a1 = secret.toCharArray();
		char[] a2 = guess.toCharArray();
		int len = secret.length();

		int a = 0, b = 0;
		List<Character> n1 = new ArrayList<Character>();
		List<Character> n2 = new ArrayList<Character>();
		for (int i = 0; i < len; i++) {
			if (a1[i] == a2[i]) {
				a++;
			} else {
				n1.add(a1[i]);
				n2.add(a2[i]);
			}
		}
		System.out.println(n1);
		System.out.println(n2);
		for (int i = 0; i < n1.size(); i++) {
			if (n2.contains((Character) n1.get(i))) {
				b++;
				n2.remove((Character) n1.get(i));
			}
		}
		return a + "A" + b + "B";

	}

	// 2251
	public String replaceWords(List<String> dictionary, String sentence) {
		Collections.sort(dictionary, Comparator.comparingInt(String::length));
		String[] arr = sentence.split(" ");
		for (int i = 0; i < arr.length; i++) {
			for (String d : dictionary) {
				if (arr[i].contains(d)) {
					System.out.println(d);
					arr[i] = d;
					break;
				}
			}
		}
		return String.join(" ", arr);
	}

	// 1689
	public int minPartitions(String n) {
		char[] arr = n.toCharArray();
		int r = 0;
		for (char c : arr) {
			r = Math.max(r, c);
			if (r == '9') {
				return 9;
			}
		}

		return Integer.valueOf(new String((char) r + ""));
	}

	// 2363
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> note = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			char[] arr = strs[i].toCharArray();
			Arrays.sort(arr);
			String s = new String(arr);
			if (note.containsKey(s)) {
				note.get(s).add(strs[i]);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(strs[i]);
				note.put(s, list);
			}
		}
		List<List<String>> r = new ArrayList<List<String>>();
		for (String key : note.keySet()) {
			List<String> list = note.get(key);
			r.add(list);
		}
		return r;
	}

	// 1218(动态规划超时)
	public int longestSubsequence(int[] arr, int difference) {
		int[] dp = new int[arr.length];
		dp[0] = 1;
		int r = 1;
		for (int i = 1; i < dp.length; i++) {
			int t = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] - arr[j] == difference) {
					t = Math.max(t, dp[j] + 1);
				}
			}
			dp[i] = t;
			r = Math.max(r, t);
		}
		return r;

	}

	// 826
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		int len = difficulty.length;
		int[][] note = new int[len][2];
		for (int i = 0; i < len; i++) {
			note[i][0] = difficulty[i];
			note[i][1] = profit[i];
		}
		Arrays.sort(note, (o1, o2) -> {
			return o2[1] - o1[1];
		});
		for (int i = 0; i < note.length; i++) {
			System.out.println(note[i][0] + ":" + note[i][1]);
		}
		int r = 0;
		for (int i = 0; i < worker.length; i++) {
			for (int j = 0; j < len; j++) {
				if (worker[i] >= note[j][0]) {
					r += note[j][1];
				}
			}
		}
		return r;
	}

	// 1451
	public String arrangeWords(String text) {
		String[] arr = text.split(" ");
		arr[0] = arr[0].toLowerCase();
		Arrays.sort(arr, (s1, s2) -> {
			return s1.length() - s2.length();
		});
		arr[0] = arr[0].substring(0, 1).toUpperCase() + arr[0].substring(1, arr[0].length());
		return String.join(" ", arr);
	}

	// 2154
	public int maxSubArray(int[] nums) {
		int t = -10;
		int r = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int a = t + nums[i];
			if (a > 0) {
				t = a;
				r = Math.max(t, r);
			} else {
				t = 0;
			}
		}
		return r;
	}

	// 2257
	public int singleNonDuplicate(int[] nums) {
		Integer t = null;
		for (int i : nums) {
			if (t == null) {
				t = i;
				continue;
			} else if (t == i) {
				t = null;
				continue;
			} else {
				break;
			}
		}
		return t;
	}

	// 2135
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode root = new ListNode();
		ListNode t = root;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				t.next = l2;
				l2 = l2.next;
			} else if (l2 == null) {
				t.next = l1;
				l1 = l1.next;
			} else if (l1.val > l2.val) {
				t.next = l2;
				l2 = l2.next;
			} else {
				t.next = l1;
				l1 = l1.next;
			}
			t = t.next;
			continue;

		}
		return root.next;
	}

	// 2397
	public int bestSeqAtIndex(int[] height, int[] weight) {
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i; j < height.length; j++) {
				if (height[i] > height[j]) {
					int t = height[i];
					height[i] = height[j];
					height[j] = t;
					t = weight[i];
					weight[i] = weight[j];
					weight[j] = t;
				} else if (height[i] == height[j] && weight[i] > weight[j]) {
					weight[i] = Integer.MAX_VALUE;
				}
			}
		}
		int[] dp = new int[weight.length];
		int r = 0;
		for (int i = 0; i < weight.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (weight[i] > weight[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			r = Math.max(r, dp[i]);
		}
		return r;
	}

	// 1765
	public int[][] highestPeak(int[][] isWater) {
		int[][] r = new int[isWater.length][isWater[0].length];
		for (int i = 0; i < r.length; i++) {
			Arrays.fill(r[i], -1);
		}
		List<int[]> p = new ArrayList<>();
		for (int i = 0; i < isWater.length; i++) {
			for (int j = 0; j < isWater[i].length; j++) {
				if (isWater[i][j] == 1) {
					p.add(new int[] { i, j });
				}
			}
		}

		f1765(r, p, 0);

		return r;
	}

	public void f1765(int[][] r, List<int[]> p, int h) {
		if (p.size() == 0) {
			return;
		}
		List<int[]> pn = new ArrayList<>();

		for (int i = 0; i < p.size(); i++) {
			int x = p.get(i)[0], y = p.get(i)[1];
			r[x][y] = h;
		}
		for (int i = 0; i < p.size(); i++) {
			int x = p.get(i)[0], y = p.get(i)[1];
			if (f1765_(r, x + 1, y)) {
				r[x + 1][y] = -2;
				pn.add(new int[] { x + 1, y });
			}
			if (f1765_(r, x - 1, y)) {
				r[x - 1][y] = -2;
				pn.add(new int[] { x - 1, y });
			}
			if (f1765_(r, x, y + 1)) {
				r[x][y + 1] = -2;
				pn.add(new int[] { x, y + 1 });
			}
			if (f1765_(r, x, y - 1)) {
				r[x][y - 1] = -2;
				pn.add(new int[] { x, y - 1 });
			}
		}

		h++;
		f1765(r, pn, h);
	}

	private boolean f1765_(int[][] r, int x, int y) {
		if (x < 0 || x >= r.length || y < 0 || y >= r[0].length || r[x][y] != -1) {
			return false;
		}
		return true;
	}

	// 947
	public int removeStones(int[][] stones) {
		Set<Set<Integer>> note = new HashSet<Set<Integer>>();
		for (int i = 0; i < stones.length; i++) {
			int n = stones[i][0] + 10000;
			int m = stones[i][1];
			if (!f947(n, m, note)) {
				Set<Integer> set = new HashSet<Integer>();
				set.add(n);
				set.add(m);
				note.add(set);
			}
		}

		return stones.length - note.size();
	}

	public boolean f947(int n, int m, Set<Set<Integer>> note) {
		for (Set<Integer> set : note) {
			if (set.contains(n) || set.contains(m)) {
				set.add(n);
				set.add(m);
				return true;
			}
		}
		return false;
	}

	public int thirdMax(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		PriorityQueue<Integer> q = new PriorityQueue<>((m, n) -> {
			return n - m;
		});
		for (int i = 0; i < nums.length; i++) {
			if (!q.contains(nums[i])) {
				q.offer(nums[i]);
			}
		}
		Integer r = q.poll();
		Integer t = q.poll();
		if (t == null) {
			return r;
		}
		r = t;
		t = q.poll();
		if (t == null) {
			return r;
		}
		r = t;
		return r;
	}

	// 1006
	public int clumsy(int n) {
		int r = 0;
		int i = n;
		if (i > 2) {
			r += (int) Math.ceil(i * (i - 1) / (i - 2));
			i -= 3;
		}
		while (i > 0) {
			if (i == 1) {
				r += 1;
			} else if (i == 2) {
				r += 1;
			} else if (i == 3) {
				r += 1;
			} else if (i == 4) {
				r -= 2;
			} else {
				r += i - (int) Math.ceil((i - 1) * (i - 2) / (i - 3));
			}
			i -= 4;
		}
		return r;
	}

	// 496
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[] r = new int[nums1.length];
		Map<Integer, Integer> note = new HashMap<>();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums2.length; i++) {
			int t = nums2[i];
			List<Integer> l = new ArrayList<Integer>();
			for (Integer n : list) {
				if (t > n) {
					note.put(n, t);
					l.add(n);

				}
			}
			for (Integer m : l) {
				list.remove(m);
			}
			list.add(t);
		}
		for (Integer n : list) {
			note.put(n, -1);
		}

		for (int i = 0; i < nums1.length; i++) {
			r[i] = note.get(nums1[i]);
		}
		return r;
	}

	// 1488
	public int[] avoidFlood(int[] rains) {
		int[] r = new int[rains.length];
		Set<Integer> note = new HashSet<Integer>();
		Queue<Integer> free = new ArrayDeque<Integer>();

		for (int i = 0; i < rains.length; i++) {
			if (rains[i] > 0) {
				r[i] = -1;
				if (note.contains(rains[i])) {
					Integer t = free.poll();
					if (t == null) {
						return new int[] {};
					} else {
						r[t] = rains[i];
					}
				} else {
					note.add(rains[i]);
				}
			} else {
				free.offer(i);
				r[i] = 1;
			}
		}
		return r;
	}

	// 1748
	public int sumOfUnique(int[] nums) {
		int[] note = new int[101];
		int r = 0;
		for (int i = 0; i < nums.length; i++) {
			if (note[nums[i]] == 0) {
				r += nums[i];
				note[nums[i]]++;
			} else if (note[nums[i]] == 1) {
				r -= nums[i];
				note[nums[i]]++;
			}
		}
		return r;
	}

	// 240
	public boolean searchMatrix240(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;

		int i = 0;
		int j = n - 1;
		while (i < m && j > -1) {
			int t = matrix[i][j];
			if (t < target) {
				i++;
			} else if (t > target) {
				j--;
			} else {
				return true;
			}
		}
		return false;
	}

	// 973
	public int[][] kClosest(int[][] points, int k) {
		List<int[]> list = new ArrayList<int[]>();
		int[][] r = new int[k][2];

		int[][] arr = (int[][]) Arrays.stream(points).sorted((a, b) -> {
			return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
		}).limit(k).toArray();
		for (int i = 0; i < k; i++) {
			r[i][0] = arr[i][0];
			r[i][1] = list.get(i)[1];
		}

//		Arrays.stream(points).sorted((a, b) -> {
//			return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
//		}).limit(k).forEach(item -> {
//			list.add(item);
//		});

//		for (int i = 0; i < k; i++) {
//			r[i][0] = list.get(i)[0];
//			r[i][1] = list.get(i)[1];
//		}
//		System.out.println(r[0][0] + "," + r[0][1]);
		return r;
	}

	// 407
	public int trapRainWater(int[][] heightMap) {
//		for (int[] is : heightMap) {
//
//		}
//		int m = heightMap.length;
//		int n = heightMap[0].length;
//		int[] a = new int[m], b = new int[m], c = new int[n], d = new int[n];
//
		return 0;
	}

	// 846
	public boolean isNStraightHand(int[] hand, int groupSize) {
		int len = hand.length;
		if (len % groupSize != 0) {
			return false;
		}
		Arrays.sort(hand);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < hand.length; i++) {
			list.add(hand[i]);
		}
		int i = 0;
		Integer t = null;
		while (list.size() > 0) {
			System.out.println(list);
			if (i == 0) {
				t = list.get(0);
				list.remove(t);
			} else if (!list.contains(Integer.valueOf(t + 1))) {
				return false;
			} else {
				t = Integer.valueOf(t + 1);
				list.remove(t);
			}
			i++;
			if (i == groupSize) {
				i = 0;
			}
		}
		return true;
	}

	public int[] sortArray(int[] nums) {
		Arrays.sort(nums);
		return nums;
	}

	// 1052
	public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
		int len = customers.length;
		int r = 0;
		for (int i = 0; i < len; i++) {
			if (grumpy[i] == 0) {
				r += customers[i];
				customers[i] = 0;
			}
		}
		int[] n = new int[len + 1];
		for (int i = 1; i < n.length; i++) {
			n[i] = customers[i - 1] + n[i - 1];
		}
		if (minutes > 0) {
			int m = n[minutes];
			for (int i = 0; i < n.length - minutes; i++) {
				int t = n[i + minutes] - n[i];
				if (t > m) {
					m = t;
				}
			}
			return r + m;
		}
		return r;
	}

	// 378
	public int kthSmallest(int[][] matrix, int k) {
		int len = matrix.length;
		int i = k / len;
		int n = k % len;

		int t = i == 0 ? Integer.MIN_VALUE : matrix[i - 1][len - 1];
		System.out.println(t);

		if (i == len) {
			return t;
		}
		if (n == 0) {
			Integer r = null;
			for (int j = 0; j < len; j++) {
				if (matrix[i][j] < t) {
					r = matrix[i][j];
				}
			}
			if (r != null) {
				return r;
			}
		}
		for (int j = 0; j < len; j++) {
			if (n > 0) {
				int a = matrix[i][j];
				if (a > t) {
					t = a;
				}
				n--;
			} else {
				break;
			}
		}
		return t;

	}

	// 2062
	public int circleGame(int[][] toys, int[][] circles, int r) {
		int res = 0;
		Set<Integer> note = new HashSet<>();
		for (int i = 0; i < circles.length; i++) {
			for (int j = 0; j < toys.length; j++) {
				if (note.contains(j)) {
					continue;
				}
				if (f2062(toys[j][0], toys[j][1], toys[j][2], circles[i][0], circles[i][1], r)) {
					res++;
					note.add(j);
				}
			}
		}
		return res;
	}

	private boolean f2062(int x1, int y1, int r1, int x2, int y2, int r2) {
		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		if (r2 >= d + r1) {
			return true;
		}
		return false;
	}

	// 458
	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int r = 0;
		int c = minutesToTest / minutesToDie;
		double t = Math.pow(c + 1, r);
		while (t < buckets) {
			r++;
			t = Math.pow(c + 1, r);
		}
		return r;
	}

	// 860
	public boolean lemonadeChange(int[] bills) {
		int n = 0, m = 0;
		for (int i = 0; i < bills.length; i++) {
			int t = bills[i];
			if (t == 5) {
				n++;
			} else if (t == 10) {
				if (n < 1) {
					return false;
				}
				n--;
				m++;
			} else {
				if (m > 0 && n > 0) {
					m--;
					n--;
				} else if (n > 0) {
					if (n < 3) {
						return false;
					}
					n = n - 3;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	// 1652
	public int[] decrypt(int[] code, int k) {
		int[] r = new int[code.length];
		for (int i = 0; i < code.length; i++) {
			int t = 0;
			if (k >= 0) {
				for (int j = i + 1; j < i + 1 + k; j++) {
					t += code[j % code.length];
				}
			} else {
				for (int j = i - 1; j > i - 1 + k; j--) {
					int l = j;
					if (l < 0) {
						l += code.length;
					}
					t += code[l];
				}
			}
			r[i] = t;
		}
		return r;
	}

	// 475
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int r = 0;
		for (int i = 0; i < houses.length; i++) {
			int t = houses[i];
			int a = Math.abs(t - heaters[0]);
			for (int j = 1; j < heaters.length; j++) {
				int b = Math.abs(t - heaters[j]);
				if (b > a) {
					break;
				} else {
					a = b;
				}
			}
			r = Math.max(r, a);
		}
		return r;
	}

	// 1395
	public int numTeams(int[] rating) {
		int r = 0;
		for (int i = 1; i < rating.length - 1; i++) {
			int t = rating[i];
			int a = 0, b = 0, c = 0, d = 0;
			for (int j = 0; j < i; j++) {
				if (rating[j] < t) {
					a++;
				}
				if (rating[j] > t) {
					c++;
				}
			}
			for (int j = i + 1; j < rating.length; j++) {
				if (rating[j] > t) {
					b++;
				}
				if (rating[j] < t) {
					d++;
				}
			}
			r += a * b + c * d;
		}
		return r;
	}

	// 1671
	public int minimumMountainRemovals(int[] nums) {
		int n = nums.length;
		int[] dp1 = new int[n], dp2 = new int[n];
		for (int i = 0; i < n; i++) {
			dp1[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp1[i] = Math.max(dp1[i], dp1[j] + 1);
				}
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = n - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}

		int r = 0;
		for (int i = 0; i < n; i++) {
			if (dp1[i] > 1 && dp2[i] > 1) {
				r = Math.max(r, dp1[i] + dp2[i] - 1);
			}
		}
		return n - r;
	}

	// 1010
	public int numPairsDivisibleBy60(int[] time) {
		if (time.length < 2) {
			return 0;
		}
		int[] dp = new int[60];
		for (int i = 0; i < time.length; i++) {
			dp[time[i] % 60]++;
		}
		int r = 0;
		for (int i = 1; i < 30; i++) {
			r += dp[i] * dp[60 - i];
		}
		if (dp[0] > 1) {
			r += dp[0] * (dp[0] - 1) / 2;
		}
		if (dp[30] > 1) {
			r += dp[30] * (dp[30] - 1) / 2;
		}

		return r;
	}

	// 1725
	public int countGoodRectangles(int[][] rectangles) {
		int r = 0;
		int t = 0;
		for (int i = 0; i < rectangles.length; i++) {
			int n = Math.min(rectangles[i][0], rectangles[i][1]);
			if (n > t) {
				r = 1;
				t = n;
			} else if (n == t) {
				r++;
			}
		}
		return r;
	}

	public String compressString(String s) {
		if (s.length() < 2) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		Character t = s.charAt(0);
		int n = 1;
		sb.append(t);
		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == t) {
				n++;
			} else {
				sb.append(n);
				sb.append(c);
				n = 1;
				t = c;
			}
		}
		sb.append(n);
		String r = sb.toString();
		return r.length() < s.length() ? r : s;

	}

	// 1020
	public int numEnclaves(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		for (int i = 0; i < n; i++) {
			if (grid[i][0] == 1) {
				f1020(grid, i, 0);
			}
			if (grid[i][m - 1] == 1) {
				f1020(grid, i, m - 1);
			}
		}
		for (int i = 1; i < m - 1; i++) {
			if (grid[0][i] == 1) {
				f1020(grid, 0, i);
			}
			if (grid[n - 1][i] == 1) {
				f1020(grid, n - 1, i);
			}
		}

		int r = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					r++;
				}
			}
		}
		return r;
	}

	private void f1020(int[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
			return;
		}
		grid[i][j] = 0;
		f1020(grid, i + 1, j);
		f1020(grid, i - 1, j);
		f1020(grid, i, j + 1);
		f1020(grid, i, j - 1);
	}

	// 448
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		int[] dp = new int[10001];
		for (int i = 0; i < nums.length; i++) {
			dp[nums[i]] = 1;
		}
		for (int i = 1; i <= nums.length; i++) {
			if (dp[i] == 0) {
				res.add(i);
			}
		}
		return res;

		// 15.87%
//		int n = nums.length;
//		List<Integer> res = new ArrayList<Integer>();
//		Set<Integer> note = new HashSet<Integer>();
//		for (Integer i : nums) {
//			note.add(i);
//		}
//		for (int i = 1; i <= n; i++) {
//			if (!note.contains(i)) {
//				res.add(i);
//			}
//		}
//		return res;
	}

	// 209
	public int minSubArrayLen(int target, int[] nums) {
		int[] dp = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			dp[i + 1] = dp[i] + nums[i];
		}
		if (dp[nums.length] < target) {
			return 0;
		}
		int a = 0, b = 1;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] >= target) {
				b = i;
				break;
			}
		}
		int r = 0;
		while (b <= nums.length) {
			int t = dp[b] - dp[a];
			System.out.println(t);
			if (t > target) {
				if (a < b - 1) {
					a++;
				} else {
					a = b - r;
				}

			} else if (t < target) {
				b++;
				a = r == 0 ? 0 : b - r;
			} else {
				System.out.println(a + "," + b);
				if (r == 0) {
					r = b - a;
				} else {
					r = Math.min(r, b - a);
				}
				b++;
				a = b - r;
			}
		}

		return r;
	}

	// 199
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> r = new ArrayList<Integer>();
		if (root == null) {
			return r;
		}
		List<TreeNode> t = new ArrayList<TreeNode>();
		t.add(root);
		while (t.size() > 0) {
			r.add(t.get(t.size() - 1).val);
			List<TreeNode> tt = new ArrayList<TreeNode>();
			for (int i = 0; i < t.size(); i++) {
				TreeNode n = t.get(i);
				if (n.left != null) {
					tt.add(n.left);
				}
				if (n.right != null) {
					tt.add(n.right);
				}
			}
			t = tt;
		}
		return r;
	}

	// 103
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> r = new ArrayList<List<Integer>>();
		if (root == null) {
			return r;
		}
		List<TreeNode> t = new ArrayList<TreeNode>();
		t.add(root);
		int i = 0;
		while (t.size() > 0) {
			List<Integer> l = new ArrayList<Integer>();

			List<TreeNode> tt = new ArrayList<TreeNode>();
			for (int j = 0; j < t.size(); j++) {
				TreeNode n = t.get(j);
				TreeNode n2 = t.get(t.size() - 1 - j);
				l.add(n.val);
				if (i % 2 == 1) {
					if (n2.left != null) {
						tt.add(n2.left);
					}
					if (n2.right != null) {
						tt.add(n2.right);
					}
				} else {
					if (n2.right != null) {
						tt.add(n2.right);
					}
					if (n2.left != null) {
						tt.add(n2.left);
					}
				}
			}
			r.add(l);
			t = tt;
			i++;
		}
		return r;
	}

	// 876
	public ListNode middleNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode root = new ListNode(0, head);
		ListNode t = root;
		while (t != null && t.next != null) {
			root = root.next;
			t = t.next.next;
		}
		if (t == null) {
			root = root.next;
		}
		return root;
	}

	// 430
//	public Node flatten(Node head) {
//		Node root = head;
//		while (head != null) {
//			if (head.child != null) {
//				head = f430(head);
//			}
//			head = head.next;
//		}
//		return root;
//	}
//
//	private Node f430(Node node) {
//		Node t = node.next;
//		node.next = node.child;
//		node.child.prev = node;
//		node.child = null;
//		Node n = node;
//		while (n.next != null) {
//			if (n.next.child != null) {
//				n.next = f430(n.next);
//			}
//			n = n.next;
//		}
//		n.next = t;
//		return node;
//	}

	// 74
	public boolean searchMatrix(int[][] matrix, int target) {
		int i = matrix.length - 1, j = 0, t;
		while (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
			t = matrix[i][j];
			if (t > target) {
				i--;
			} else if (t < target) {
				j++;
			} else {
				return true;
			}
		}
		return false;
	}

	// 82
	public ListNode deleteDuplicates82(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode root = new ListNode(0, head);
		ListNode a = root;
		ListNode b = head;
		boolean f = false;
		while (b != null) {
			if (b.next == null || b.val != b.next.val) {
				if (f) {
					a.next = b.next;
					b = b.next;
					f = false;
				} else {
					a = b;
					b = b.next;
				}
			} else {
				f = true;
				b = b.next;
			}
		}
		return root.next;
	}

	// 31
	public void nextPermutation(int[] nums) {
		if (nums.length < 2) {
			return;
		}
		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i] > nums[i - 1]) {
				int t = nums[i];
				nums[i] = nums[i - 1];
				nums[i - 1] = t;
				return;
			}
		}
		for (int i = 0; i < nums.length / 2; i++) {
			int t = nums[i];
			nums[i] = nums[nums.length - 1 - i];
			nums[i - 1 - i] = t;
		}
	}

	public String reverseLeftWords(String s, int n) {
		return s.substring(n, s.length()) + s.substring(0, n);
	}

	// 67
	public String addBinary(String a, String b) {
		int la = a.length();
		int lb = b.length();
		int l = Math.max(a.length(), b.length());
		StringBuilder s = new StringBuilder();
		char t = '0';
		for (int i = 0; i < l; i++) {
			System.out.println(t);
			char t1 = i >= la ? '0' : a.charAt(la - i - 1);
			char t2 = i >= lb ? '0' : b.charAt(lb - i - 1);
			if (t == '0' && t1 == '0' && t2 == '0') {
				s.insert(0, '0');
				t = '0';
			} else if (t == '0' && t1 == '0' && t2 == '1') {
				s.insert(0, '1');
				t = '0';
			} else if (t == '0' && t1 == '1' && t2 == '0') {
				s.insert(0, '1');
				t = '0';
			} else if (t == '0' && t1 == '1' && t2 == '1') {
				s.insert(0, '0');
				t = '1';
			} else if (t == '1' && t1 == '0' && t2 == '0') {
				s.insert(0, '1');
				t = '0';
			} else if (t == '1' && t1 == '0' && t2 == '1') {
				s.insert(0, '0');
				t = '1';
			} else if (t == '1' && t1 == '1' && t2 == '0') {
				s.insert(0, '0');
				t = '1';
			} else if (t == '1' && t1 == '1' && t2 == '1') {
				s.insert(0, '1');
				t = '1';
			}
		}
		if (t == '1') {
			s.insert(0, '1');
		}
		return s.toString();
	}

	// 111
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return f111(root, 1, Integer.MAX_VALUE);
	}

	private int f111(TreeNode node, int d, int r) {
		if (node.left == null && node.right == null) {
			return d;
		}
		if (d >= r) {
			return r;
		}
		if (node.left != null) {
			int t = f111(node.left, d + 1, r);
			r = Math.min(r, t);
		}
		if (d >= r) {
			return r;
		}
		if (node.right != null) {
			int t = f111(node.right, d + 1, r);
			r = Math.min(r, t);
		}
		return r;

	}

	public int[] getLeastNumbers(int[] arr, int k) {
		Arrays.sort(arr);
		int[] r = new int[k];
		for (int i = 0; i < r.length; i++) {
			r[i] = arr[i];
		}
		return r;
	}

	// 125
	public boolean isPalindrome(String s) {
		Deque<Character> dq = new ArrayDeque<>();
		s = s.toLowerCase();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c <= 'z' && c >= 'a') || (c <= '9' && c >= '0')) {
				dq.add(c);
			}
		}
		while (dq.size() > 1) {
			if (dq.pollFirst() != dq.pollLast()) {
				return false;
			}
		}
		return true;
	}

	public int fib(int n) {
		int[] dp = new int[101];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
		}
		return dp[n];
	}

	// 100
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null || p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	// 167
	public int[] twoSum(int[] numbers, int target) {
		Map<Integer, Integer> note = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (note.containsKey(numbers[i])) {
				return new int[] { note.get(numbers[i]), i + 1 };
			}
			note.put(target - numbers[i], i + 1);
		}
		return null;
	}

	// 344
	public void reverseString(char[] s) {
		char t;
		for (int i = 0; i < s.length / 2; i++) {
			t = s[i];
			s[i] = s[s.length - 1 - i];
			s[s.length - 1 - i] = t;
		}
	}

	// 326
	public boolean isPowerOfThree(int n) {
		if (n == 0) {
			return false;
		}
		while (n != 0 && n != 1) {
			if (n % 3 != 0) {
				return false;
			}
			n = n / 3;
		}
		return true;
	}

	// 234
	public boolean isPalindrome(ListNode head) {
		if (head.next == null) {
			return true;
		}
		Stack<Integer> s = new Stack<>();
		ListNode t = head;
		while (t != null) {
			s.push(t.val);
			t = t.next;
		}
		t = head;
		while (t != null) {
			if (t.val != s.pop()) {
				return false;
			}
			t = t.next;
		}
		return true;

	}

	// 61
	public ListNode rotateRight(ListNode head, int k) {
		List<ListNode> list = new ArrayList<>();
		while (head != null) {
			list.add(head);
			head = head.next;
		}
		k = k % list.size();
		k = list.size() - k;
		ListNode root = new ListNode();
		ListNode t = root;
		for (int i = 0; i < list.size(); i++) {
			t.next = list.get((i + k) % k);
			if (i != list.size() - 1) {
				t = t.next;
			} else {
				t.next = null;
			}

		}
		return root.next;
	}

	// 54
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> r = new ArrayList<Integer>();
		int d = 0;
		int i = 0, j = 0;
		int f = 0;

		while (true) {
			if (f == 2) {
				break;
			}
			if (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length && matrix[i][j] != Integer.MIN_VALUE) {
				r.add(matrix[i][j]);
				matrix[i][j] = Integer.MIN_VALUE;
				f = 0;
			}

			if (d % 4 == 0) {
				if (j + 1 >= matrix[i].length || matrix[i][j + 1] == Integer.MIN_VALUE) {
					f++;
					d++;
					i++;
				} else {
					j++;
				}
			} else if (d % 4 == 1) {
				if (i + 1 >= matrix.length || matrix[i + 1][j] == Integer.MIN_VALUE) {
					f++;
					d++;
					j--;
				} else {
					i++;
				}
			} else if (d % 4 == 2) {
				if (j - 1 < 0 || matrix[i][j - 1] == Integer.MIN_VALUE) {
					f++;
					d++;
					i--;
				} else {
					j--;
				}
			} else if (d % 4 == 3) {
				if (i - 1 < 0 || matrix[i - 1][j] == Integer.MIN_VALUE) {
					f++;
					d++;
					j++;
				} else {
					i--;
				}
			}
		}
		return r;
	}

	// 200
	public int numIslands(char[][] grid) {
		int r = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					r++;
					f200(grid, i, j);
				}
			}
		}
		return r;
	}

	private void f200(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return;
		}
		if (grid[i][j] == '1') {
			grid[i][j] = '0';
			f200(grid, i + 1, j);
			f200(grid, i - 1, j);
			f200(grid, i, j + 1);
			f200(grid, i, j - 1);
		}
	}

	// 169
	public int majorityElement(int[] nums) {
		int n = 0;
		int c = 0;
		for (int i = 0; i < nums.length; i++) {
			if (c == 0) {
				n = nums[i];
				c++;
			} else {
				if (n == nums[i]) {
					c++;
				} else {
					c--;
				}
			}
		}
		return n;
	}

	// 16
	public int threeSumClosest(int[] nums, int target) {
		int[] sums = new int[nums.length];
		sums[0] = nums[0];
		for (int i = 1; i < sums.length; i++) {
			sums[i] = nums[i] + sums[i - 1];
		}
		int r = Integer.MIN_VALUE;
		for (int i = 0; i < sums.length - 2; i++) {
			int t = sums[i + 2] - sums[i];
			if (Math.abs(target - t) < Math.abs(target - r)) {
				r = t;
			}
		}
		return r;
	}

	// 25
	public ListNode reverseKGroup(ListNode head, int k) {
		List<ListNode> list = new ArrayList<ListNode>();
		ListNode r = null;
		ListNode c = null;
		while (head != null) {
			list.add(head);
			if (list.size() == k) {
				ListNode t = list.get(k - 1).next;
				if (c != null) {
					c.next = list.get(k - 1);
				}
				for (int i = k - 1; i >= 1; i--) {
					list.get(i).next = list.get(i - 1);
				}
				list.get(0).next = t;
				if (r == null) {
					r = list.get(k - 1);
				}
				c = list.get(0);
				for (int i = 0; i < k - 1; i++) {
					head = head.next;
				}
				list.clear();
			}
			head = head.next;
		}
		return r;
	}

	// 24
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode root = head.next;

		ListNode a = null;
		ListNode b = null;
		ListNode c = null;
		while (head != null) {
			if (a == null) {
				a = head;
			} else if (b == null) {
				b = head;
				if (c != null) {
					System.out.println(c.val);
					System.out.println(b.val);
					c.next = b;
				}
				ListNode t = b.next;
				b.next = a;
				a.next = t;
				c = a;
				a = null;
				b = null;
				head = head.next;
			}
			head = head.next;
		}
		return root;
	}

	// 33
	public int search(int[] nums, int target) {
		if (target >= nums[0]) {
			for (int i = 0; i < nums.length; i++) {
				if (target == nums[i]) {
					return i;
				} else if (target < nums[i]) {
					return -1;
				}
			}
		} else {
			for (int i = nums.length - 1; i >= 0; i--) {
				if (target == nums[i]) {
					return i;
				} else if (target > nums[i]) {
					return -1;
				}
			}
		}
		return -1;
	}

	// 56
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		List<int[]> list = new ArrayList<>();
		int[] t = intervals[0];
		list.add(t);
		for (int i = 1; i < intervals.length; i++) {
			int[] n = intervals[i];
			if (n[0] <= t[1] && n[1] > t[1]) {
				t[1] = n[1];
			} else if (n[0] > t[1]) {
				list.add(n);
				t = n;
			}
		}
		int[][] res = new int[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}

		return res;
	}

	// 215
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums.length - i == k) {
				return nums[i];
			}
		}
		return nums[nums.length - 1];
	}

	// 283
	public void moveZeroes(int[] nums) {
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[j++] = nums[i];
			}
		}
		while (j < nums.length) {
			nums[j++] = 0;
		}

	}

	// 35
	public int searchInsert(int[] nums, int target) {
		int a = 0, b = nums.length - 1;
		while (a != b) {
			System.out.println(a + "," + b);
			int t = (a + b) / 2;
			if (nums[t] > target) {
				b = t;
			} else if (nums[t] < target) {
				a = ++t;
			} else {
				return t;
			}
		}
		if (target > nums[b]) {
			return b + 1;
		} else {
			return b;
		}

	}

	// 27
	public int removeElement(int[] nums, int val) {
		int n = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == val) {
				n++;
			} else {
				nums[i - n] = nums[i];
			}
		}
		return nums.length - n;
	}

	// 26
	public int removeDuplicates(int[] nums) {
		int t = Integer.MAX_VALUE;
		int n = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == t) {
				n++;
			} else {
				t = nums[i];
				nums[i - n] = t;
			}
		}
		return nums.length - n;
	}

//	public ListNode reverseList(ListNode head) {
//		ListNode t = null;
//		ListNode f = head;
//		while (f != null) {
//			ListNode n = f.next;
//			f.next = t;
//			t = f;
//			f = n;
//		}
//		return t;
//	}

	// 235
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.val < Math.min(p.val, q.val)) {
			return lowestCommonAncestor(root.right, p, q);
		} else if (root.val > Math.max(p.val, q.val)) {
			return lowestCommonAncestor(root.left, p, q);
		} else {
			return root;
		}
	}

	// 653
	public boolean findTarget(TreeNode root, int k) {
		if (root == null) {
			return false;
		}
		Set<Integer> note = new HashSet<Integer>();
		note.add(root.val);
		return f653(root.left, note, k) || f653(root.right, note, k);
	}

	private boolean f653(TreeNode node, Set<Integer> note, int k) {
		if (node == null) {
			return false;
		}
		if (note.contains(k - node.val)) {
			return true;
		}
		note.add(node.val);
		return f653(node.left, note, k) || f653(node.right, note, k);
	}

	// 98
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return f98(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean f98(TreeNode node, long a, long b) {
		if (node.val >= b || node.val <= a) {
			return false;
		}
		if (node.left == null && node.right == null) {
			return true;
		} else if (node.left != null && node.right == null) {
			return f98(node.left, a, node.val);
		} else if (node.left == null && node.right != null) {
			return f98(node.right, node.val, b);
		} else {
			return f98(node.left, a, node.val) && f98(node.right, node.val, b);
		}
	}

	// 701
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}
		TreeNode r = root;

		if (r.val > val) {
			r.left = insertIntoBST(r.left, val);
		} else {
			r.right = insertIntoBST(r.right, val);
		}
		return root;
	}

	// 700
	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null) {
			return null;
		}
		if (root.val == val) {
			return root;
		} else if (root.val > val) {
			return searchBST(root.left, val);
		} else {
			return searchBST(root.right, val);
		}
	}

	// 112
	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {
			return false;
		}
		if (root.val == targetSum && root.left == null && root.right == null) {
			return true;
		}
		return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
	}

	// 226
	public TreeNode invertTree(TreeNode root) {
		f226(root);
		return root;
	}

	private void f226(TreeNode node) {
		if (node != null) {
			f226(node.left);
			f226(node.right);
			TreeNode t = node.left;
			node.left = node.right;
			node.right = t;
		}

	}

	// 101
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		} else {
			return f101(root.left, root.right);
		}
	}

	private boolean f101(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		} else if ((left == null && right != null) || (right == null && left != null)) {
			return false;
		} else {
			TreeNode a = left.left;
			TreeNode b = left.right;
			TreeNode c = right.left;
			TreeNode d = right.right;
			if (f101_1(a, d) && f101_1(b, c)) {
				return f101_1(a, d) && f101_1(b, c);
			} else {
				return false;
			}
		}
	}

	private boolean f101_1(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		} else if (a != null && b != null && a.val == b.val) {
			return true;
		} else {
			return false;
		}
	}

	// 104
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int r = f104(root, 1);
		return r;
	}

	private int f104(TreeNode node, int i) {
		if (node.left == null && node.right == null) {
			return i;
		} else if (node.left == null) {
			return f104(node.right, i + 1);
		} else if (node.right == null) {
			return f104(node.left, i + 1);
		} else {
			return Math.max(f104(node.left, i + 1), f104(node.right, i + 1));
		}
	}

	// 102
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(root.val);
		res.add(l1);
		List<TreeNode> t = new ArrayList<TreeNode>();
		t.add(root);
		t = f102(t);

		while (t.size() > 0) {
			List<Integer> n = new ArrayList<Integer>();
			for (int i = 0; i < t.size(); i++) {
				n.add(t.get(i).val);
			}
			res.add(n);
			t = f102(t);
		}

		return res;
	}

	private List<TreeNode> f102(List<TreeNode> nodes) {
		List<TreeNode> res = new ArrayList<TreeNode>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).left != null) {
				res.add(nodes.get(i).left);
			}
			if (nodes.get(i).right != null) {
				res.add(nodes.get(i).right);
			}
		}
		return res;
	}

	// 212
	public List<String> findWords(char[][] board, String[] words) {
//		Map<String, Character> note = new HashMap<String, Character>();
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				note.put(i + "," + j, board[i][j]);
//			}
//		}
//		for (int i = 0; i < words.length; i++) {
//			char[] arr = words[i].toCharArray();
//			
//		}
		List<String> list = new ArrayList<String>();

		return list;
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}
		Set<Integer> note = new HashSet<>();
		note.add(head.val);

		ListNode root = head;
		ListNode p = root;
		ListNode t = head.next;
		while (t != null) {
			if (note.contains(t.val)) {
				p.next = t.next;
			} else {
				note.add(t.val);
				p = t;
			}
			t = t.next;
		}
		return root;
	}

	// 203
	public ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return head;
		head.next = removeElements(head.next, val);
		return head.val == val ? head.next : head;
	}

	// 21
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		List<ListNode> list = new ArrayList<>();
		while (l1 != null) {
			list.add(l1);
			l1 = l1.next;
		}
		while (l2 != null) {
			list.add(l2);
			l2 = l2.next;
		}
		list.sort((o1, o2) -> {
			return o1.val - o2.val;
		});
		ListNode root = new ListNode();
		ListNode t = root;
		for (int i = 0; i < list.size(); i++) {
			t.next = list.get(i);
			t = t.next;
		}
		return root.next;
	}

	// 141
	public boolean hasCycle(ListNode head) {
		Set<ListNode> note = new HashSet<>();
		while (head != null) {
			if (note.contains(head)) {
				return true;
			} else {
				note.add(head);
			}
			head = head.next;
		}
		return false;
	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		Map<Character, Integer> note = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (note.containsKey(s.charAt(i))) {
				note.put(s.charAt(i), note.get(s.charAt(i)) + 1);
			} else {
				note.put(s.charAt(i), 1);
			}
		}
		System.out.println(note.toString());
		for (int i = 0; i < t.length(); i++) {
			if (note.containsKey(t.charAt(i))) {
				Integer k = note.get(t.charAt(i));
				if (k > 1) {
					note.put(t.charAt(i), --k);
				} else {
					note.remove(t.charAt(i));
				}
			} else {
				return false;
			}
		}
		System.out.println(note.toString());
		return true;
	}

	// 383
	public boolean canConstruct(String ransomNote, String magazine) {
		for (int i = 0; i < ransomNote.length(); i++) {
			int k = magazine.indexOf(ransomNote.charAt(i));
			if (k < 0) {
				return false;
			} else {
				magazine = magazine.substring(0, k) + magazine.substring(k + 1, magazine.length());
			}
		}
		return true;
	}

	// 387
	public int firstUniqChar(String s) {
		Map<Character, Integer> note = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (note.containsKey(s.charAt(i))) {
				note.put(s.charAt(i), note.get(s.charAt(i)) + 1);
			} else {
				note.put(s.charAt(i), 1);
			}
		}
		for (int i = 0; i < s.length(); i++) {
			if (note.get(s.charAt(i)) == 1) {
				return i;
			}
		}
		return -1;
	}

	// 350
	public int[] intersect(int[] nums1, int[] nums2) {
//		List<Integer> n1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
//		for (int i = 0; i < nums2.length; i++) {
//
//		}
		return null;
	}

	// 217
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> note = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (note.contains(nums[i])) {
				return true;
			} else {
				note.add(nums[i]);
			}
		}
		return false;
	}

	// 392
	public boolean isSubsequence(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		int j = 0;
		for (int i = 0; i < a.length; i++) {
			boolean f = false;
			for (; j < b.length; j++) {
				if (b[j] == a[i]) {
					f = true;
					j++;
					break;
				}
			}
			if (!f) {
				return false;
			}
		}
		return true;
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
		if (obstacleGrid[0][0] != 1) {
			dp[0][0] = 1;
		}
		for (int i = 1; i < dp.length; i++) {
			if (obstacleGrid[i][0] != 1) {
				dp[i][0] = dp[i - 1][0];
			} else {
				dp[i][0] = 0;
			}
		}
		for (int j = 1; j < dp[0].length; j++) {
			if (obstacleGrid[0][j] != 1) {
				dp[0][j] = dp[0][j - 1];
			} else {
				dp[0][j] = 0;
			}
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (obstacleGrid[i][j] == 1) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];

	}

	// 62
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 1;
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[m - 1][n - 1];
	}

	// 941
	public boolean validMountainArray(int[] arr) {
		if (arr.length < 3 || arr[1] < arr[0]) {
			return false;
		}
		int top = 0;
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i + 1] < arr[i]) {
				top = i;
				break;
			}
		}
		if (top == arr.length - 1) {
			return false;
		}
		for (int i = top; i < arr.length - 1; i++) {
			if (arr[i + 1] > arr[i]) {
				return false;
			}
		}
		return true;
	}

	// 670
	public int maximumSwap(int num) {
		char[] arr = new Integer(num).toString().toCharArray();
		int a = -1, b = -1;
		char c = '0', d = '9';
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] < arr[i + 1] && arr[i + 1] >= c) {
				c = arr[i + 1];
				a = i + 1;
			}
			if (arr[arr.length - i - 1] > arr[arr.length - i - 2] && arr[arr.length - i - 2] <= d) {
				d = arr[arr.length - i - 2];
				b = arr.length - i - 2;
			}
		}
		if (a > -1 && b > -1) {
			char t = arr[a];
			arr[a] = arr[b];
			arr[b] = t;
		}
		String s = new String(arr);
		return Integer.valueOf(s);
	}

//	public int numberOfWeakCharacters(int[][] properties) {
//		Arrays.stream(properties).forEach(item -> {
//			System.out.println(Arrays.toString(item));
//			if (Arrays.stream(properties).filter(e -> e[0] > item[0] && e[1] > item[1]).findAny().orElseGet(null) != null) {
//				r++;
//			}
//			
//		});
//		return r;
//    }

	// 1962
	public int minStoneSum(int[] piles, int k) {
		PriorityQueue<Integer> s = new PriorityQueue<>((o1, o2) -> {
			return o2.compareTo(o1);
		});
		for (int i = 0; i < piles.length; i++) {
			s.add(piles[i]);
		}
		for (int i = 0; i < k; i++) {
			Integer t = s.remove();
			t = t - t / 2;
			s.add(t);
		}
		Integer r = s.stream().parallel().reduce((o1, o2) -> {
			return o1 + o2;
		}).get();
		return r;
	}

	// 143
	public void reorderList(ListNode head) {
		ListNode a = head;
		ListNode a1 = a;
		ListNode b = new ListNode();
		ListNode t = head.next;
		int i = 0;
		while (t != null) {
			if (i % 2 == 0) {
				if (b.next == null) {
					b.next = t;
				} else {
					ListNode n = b.next;
					b.next = t;
					t.next = n;
				}
			} else {
				a1.next = t;
				a.next = a;
			}
		}
	}

	// 416
	public boolean canPartition(int[] nums) {
		if (nums.length == 1) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 2 == 1) {
			return false;
		}
		int t = sum / 2;
		int[] dp = new int[t];
		dp[0] = 1;
		for (int num : nums) {
			for (int i = t; i >= num; i--) {
				dp[i] += dp[i - num];
			}
		}

		return dp[t] != 0;
	}

	public boolean increasingTriplet(int[] nums) {
		int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= a) {
				a = nums[i];
			} else if (nums[i] <= b) {
				b = nums[i];
			} else {
				return true;
			}
		}
		return false;
	}

	// 1550
	public boolean threeConsecutiveOdds(int[] arr) {
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 1) {
				c++;
			} else {
				c = 0;
			}
			if (c == 3) {
				return true;
			}
		}
		return false;
	}
}

class Node {
	public int val;
	public Node left;
	public Node right;
	public Node next;
	public List<Node> children;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
};