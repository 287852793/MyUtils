package com.xiaoer.game;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
	private int timeToLive;
	private Map<String, Integer> note;

	public AuthenticationManager(int timeToLive) {
		this.timeToLive = timeToLive;
		this.note = new HashMap<>();
	}

	public void generate(String tokenId, int currentTime) {
		note.put(tokenId, currentTime);
	}

	public void renew(String tokenId, int currentTime) {
		if (note.containsKey(tokenId) && currentTime - note.get(tokenId) < timeToLive) {
			note.put(tokenId, currentTime);
		}
	}

	public int countUnexpiredTokens(int currentTime) {
		int r = 0;
		for (String key : note.keySet()) {
			if (currentTime - note.get(key) < timeToLive) {
				r++;
			}
		}
		return r;
	}

	public int getTimeToLive() {
		return timeToLive;
	}
}
