package com.cric.model;

import java.util.List;

public class Player {
	private String name;
	private String intial;
	private List<String> selectedPlayers;
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
	public List<String> getSelectedPlayers() {
		return selectedPlayers;
	}
	public void setSelectedPlayers(List<String> selectedPlayers) {
		this.selectedPlayers = selectedPlayers;
	}
}
