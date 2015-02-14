package com.cric.model;

import java.util.List;

public class Performance {

	private String matchName;
	private List<PlayerSelection> players;
	private List<String> performance;
	
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public List<PlayerSelection> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerSelection> players) {
		this.players = players;
	}
	public List<String> getPerformance() {
		return performance;
	}
	public void setPerformance(List<String> performance) {
		this.performance = performance;
	}
	
	
	
}
