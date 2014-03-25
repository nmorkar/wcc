package com.cric.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private String intial;
	private List<String> selectedPlayers;
	private int selectionOrder;
	
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
	public void add(String s){
		if( selectedPlayers	== null || selectedPlayers.isEmpty() ){
			selectedPlayers = new ArrayList<>();
		}
		selectedPlayers.add(s);
	}
	public int getSelectionOrder() {
		return selectionOrder;
	}
	public void setSelectionOrder(int selectionOrder) {
		this.selectionOrder = selectionOrder;
	}
	
}
