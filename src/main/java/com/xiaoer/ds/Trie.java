package com.xiaoer.ds;

/**
 * 前缀树
 * 
 * @author pyf
 * @time 2023-01-05 11:06:51
 */
public class Trie {

	private Trie[] next;
	private boolean isEnd;

	public Trie() {
		this.next = new Trie[26];
		this.isEnd = false;
	}

	public void insert(String word) {
		Trie node = this;
		char[] arr = word.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			int t = arr[i] - 'a';
			if (node.next[t] == null) {
				node.next[t] = new Trie();
			}
			node = node.next[t];
		}
		node.isEnd = true;
	}

	public boolean search(String word) {
		Trie node = searchPrefix(word);
		return node != null && node.isEnd;
	}

	public boolean startsWith(String prefix) {
		return searchPrefix(prefix) != null;
	}

	public Trie searchPrefix(String prefix) {
		Trie node = this;
		char[] arr = prefix.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			int t = arr[i] - 'a';
			if (node.next[t] == null) {
				return null;
			}
			node = node.next[t];
		}
		return node;
	}
}
