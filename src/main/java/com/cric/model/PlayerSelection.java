package com.cric.model;

import java.util.List;


public class PlayerSelection {
	private String name;
	private String intial;
	private String selectedPlayers;
	private List<String> playersPerformance;
	private Long totalPerformance;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntial() {
		return intial;
	}
	public void setIntial(String intial) {
		this.intial = intial;
	}
	public String getSelectedPlayers() {
		return selectedPlayers;
	}
	public void setSelectedPlayers(String selectedPlayers) {
		this.selectedPlayers = selectedPlayers;
	}
	public List<String> getPlayersPerformance() {
		return playersPerformance;
	}
	public void setPlayersPerformance(List<String> playersPerformance) {
		this.playersPerformance = playersPerformance;
	}
	public Long getTotalPerformance() {
		return totalPerformance;
	}
	public void setTotalPerformance(Long totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	
	
}
